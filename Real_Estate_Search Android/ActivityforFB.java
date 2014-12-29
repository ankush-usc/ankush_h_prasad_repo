package csci571.real_estate_search_ahp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.Toast;


import com.facebook.FacebookException;
import com.facebook.FacebookOperationCanceledException;
import com.facebook.android.DialogError;
import com.facebook.android.Facebook;
import com.facebook.android.Facebook.DialogListener;
import com.facebook.android.FacebookError;
import com.facebook.widget.WebDialog;
import com.facebook.widget.WebDialog.OnCompleteListener;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class ActivityforFB extends Activity {

	private static final String application_fb_id = <id>;
	private static final String[] PERMISSIONS = new String[] {"publish_stream"};

	private static final String TOKEN = "access_token";
        private static final String EXPIRES = "expires_in";
        private static final String KEY = "facebook-credentials";

	private Facebook facebook;
	String message;

	@SuppressWarnings("deprecation")
	public boolean saveCredentials(Facebook facebook) {
        	Editor editor = getApplicationContext().getSharedPreferences(KEY, Context.MODE_PRIVATE).edit();
        	editor.putString(TOKEN, facebook.getAccessToken());
        	editor.putLong(EXPIRES, facebook.getAccessExpires());
        	return editor.commit();
    	}

    	@SuppressWarnings("deprecation")
		public boolean restoreCredentials(Facebook facebook) {
        	SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences(KEY, Context.MODE_PRIVATE);
        	facebook.setAccessToken(sharedPreferences.getString(TOKEN, null));
        	facebook.setAccessExpires(sharedPreferences.getLong(EXPIRES, 0));
        	return facebook.isSessionValid();
    	}
	

	
	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		facebook = new Facebook(application_fb_id);
		restoreCredentials(facebook);
		//requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		/*message = getIntent().getStringExtra("city");
		if (message == null){
			message = "Test wall post";
		}*/
	    	
		postToWall(message);
	    	
			finishActivity(0);
	}

	public void doNotShare(View button){
		finish();
	}
	@SuppressWarnings("deprecation")
	public void share(View button){
		if (! facebook.isSessionValid()) {
			loginAndPostToWall();
		}
		else {
			postToWall(message);
		}
	}

	@SuppressWarnings("deprecation")
	public void loginAndPostToWall(){
		 facebook.authorize(ActivityforFB.this, PERMISSIONS, Facebook.FORCE_DIALOG_AUTH,new LoginDialogListener());
	}


	
	@SuppressWarnings("deprecation")
	public void postToWall(String message){
		Bundle params = new Bundle();
		 params.putString("name", DataTransporter.intent.getStringExtra("street")+", "+DataTransporter.intent.getStringExtra("city")+", "+DataTransporter.intent.getStringExtra("state")+", "+DataTransporter.intent.getStringExtra("zipcode"));
		    params.putString("caption", "Property Information from Zillow.com");
		    String descr="Last Sold Price: "+DataTransporter.intent.getStringExtra("lastSoldPrice")+", 30 days Overall Change: "+DataTransporter.intent.getStringExtra("estimateValueChangeSign")+DataTransporter.intent.getStringExtra("estimateValueChange");
		    params.putString("description",descr);
		    params.putString("link", DataTransporter.intent.getStringExtra("homedetails"));
		    params.putString("picture", DataTransporter.intent.getStringExtra("year_1"));
		 if(facebook.isSessionValid()){
			    WebDialog feedDialog = (
			            new WebDialog.FeedDialogBuilder(ActivityforFB.this,
			                facebook.getSession(),
			                params))
			            .setOnCompleteListener(new OnCompleteListener(){

							@Override
							public void onComplete(Bundle values,
									FacebookException error) {
								if (error == null) {
									String postid=values.getString("post_id");
									if(postid != null)
										Toast.makeText(getApplicationContext(), "Posted Story, ID:"+postid, Toast.LENGTH_SHORT).show();
									else
										Toast.makeText(getApplicationContext(), "Post Cancelled", Toast.LENGTH_SHORT).show();
								}else if (error instanceof FacebookOperationCanceledException) {
			                        // User clicked the "x" button
									Toast.makeText(getApplicationContext(), "Post Cancelled", Toast.LENGTH_SHORT).show();
								}else {
			                        // Generic, ex: network error
									Toast.makeText(getApplicationContext(), "Error occured during Posting", Toast.LENGTH_SHORT).show();
								}
								finishActivity(0);
								finish();
							}})
			            .build();
			        feedDialog.show();
			    }else{
			    	facebook.authorize(this, PERMISSIONS, Facebook.FORCE_DIALOG_AUTH,new LoginDialogListener());
			    }
		}


       

	class LoginDialogListener implements DialogListener {
	    public void onComplete(Bundle values) {
	    	saveCredentials(facebook);
	    	postToWall(message);
	    }
	    public void onFacebookError(FacebookError error) {
	    	Toast.makeText(getApplicationContext(), "Authentication with Facebook failed!", Toast.LENGTH_SHORT).show();
	        finish();
	    }
	    public void onError(DialogError error) {
	    	Toast.makeText(getApplicationContext(), "Authentication with Facebook failed!", Toast.LENGTH_SHORT).show();
	        finish();
	    }
	    public void onCancel() {
	    	Toast.makeText(getApplicationContext(), "Post Cancelled!", Toast.LENGTH_SHORT).show();
	        finish();
	    }
	}

	
	
	 @java.lang.SuppressWarnings("deprecation")
	@Override
	    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	        super.onActivityResult(requestCode, resultCode, data);
	        facebook.authorizeCallback(requestCode, resultCode, data);
	    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		/*getMenuInflater().inflate(R.menu.post, menu);*/
		return true;
	}
}
