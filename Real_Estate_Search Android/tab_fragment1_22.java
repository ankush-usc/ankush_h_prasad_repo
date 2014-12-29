package csci571.real_estate_search_ahp;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


import com.facebook.FacebookOperationCanceledException;
import com.facebook.Session;
import com.facebook.SessionState;
import com.facebook.UiLifecycleHelper;
import com.facebook.widget.FacebookDialog;
import com.facebook.widget.WebDialog;
import com.facebook.widget.WebDialog.OnCompleteListener;
import com.facebook.FacebookException;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.Signature;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class tab_fragment1_22 extends Fragment {
	private UiLifecycleHelper uiHelper;
	static Context thiscontext;
	
	private Session.StatusCallback callback = new Session.StatusCallback() {
	    @Override
	    public void call(Session session, SessionState state, Exception exception) {
	        onSessionStateChange(session, state, exception);
	    }
	};
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		uiHelper = new UiLifecycleHelper(getActivity(), callback);
	    uiHelper.onCreate(savedInstanceState);

	    
	    thiscontext=container.getContext();
	    
		View rootView = inflater.inflate(R.layout.tab_frag1_22, container, false);

		Intent intent = DataTransporter.intent;
		String homedetial_traversal="<a href=\""+intent.getStringExtra("homedetails")+"\">"+intent.getStringExtra("street")+", "+intent.getStringExtra("city")+", "+intent.getStringExtra("state")+", "+intent.getStringExtra("zipcode")+" </a>";
		((TextView) rootView.findViewById(R.id.capt)).setText(Html.fromHtml(homedetial_traversal));
		((TextView) rootView.findViewById(R.id.capt)).setMovementMethod(LinkMovementMethod.getInstance());
		((TextView) rootView.findViewById(R.id.ptype)).setText(intent.getStringExtra("useCode")+" ");
		if(intent.getStringExtra("lastSoldPrice").contentEquals("$0.00")){
			((TextView) rootView.findViewById(R.id.soldp)).setText("N/A");
		}
		else
			((TextView) rootView.findViewById(R.id.soldp)).setText(intent.getStringExtra("lastSoldPrice")+" ");
		((TextView) rootView.findViewById(R.id.yearb)).setText(intent.getStringExtra("yearBuilt")+" ");
		if((intent.getStringExtra("lastSoldDate")).contentEquals("01-Jan-1970")){
			((TextView) rootView.findViewById(R.id.soldd)).setText("N/A");
		}
		else
			((TextView) rootView.findViewById(R.id.soldd)).setText(intent.getStringExtra("lastSoldDate")+" ");
		((TextView) rootView.findViewById(R.id.lots)).setText(intent.getStringExtra("lotSizeSqFt")+" ");
		((TextView) rootView.findViewById(R.id.zest)).setText(intent.getStringExtra("estimateAmount")+" ");
		((TextView) rootView.findViewById(R.id.zest_left)).setText("Zestimate \u00AE Property Estimate as of "+ intent.getStringExtra("estimateLastUpdate")+" ");
		((TextView) rootView.findViewById(R.id.finish)).setText(intent.getStringExtra("finishedSqFt")+" ");
		//System.out.println(intent.getStringExtra("estimateValueChangeSign"));
		if(intent.getStringExtra("estimateValueChangeSign").contentEquals("+")){
			((TextView) rootView.findViewById(R.id.overall_change)).setCompoundDrawablesWithIntrinsicBounds( R.drawable.up_g, 0, 0, 0);
			//((ImageView)rootView.findViewById(R.id.for_30_overall)).setImageResource(R.drawable.up_g);
			//((TextView) rootView.findViewById(R.id.overall_change)).setText(intent.getStringExtra("estimateValueChange"));
		}
		else {
			//((ImageView)rootView.findViewById(R.id.for_30_overall)).setImageResource(R.drawable.down_r);
			
			//((TextView) rootView.findViewById(R.id.overall_change)).setText(Html.fromHtml(for_overall_change));
			((TextView) rootView.findViewById(R.id.overall_change)).setCompoundDrawablesWithIntrinsicBounds( R.drawable.down_r, 0, 0, 0);
		}
		((TextView) rootView.findViewById(R.id.overall_change)).setText(intent.getStringExtra("estimateValueChange")+" ");
		if(intent.getStringExtra("restimateValueChangeSign").contentEquals("+")){
			((TextView) rootView.findViewById(R.id.rent_change)).setCompoundDrawablesWithIntrinsicBounds( R.drawable.up_g, 0, 0, 0);
			//((TextView) rootView.findViewById(R.id.rent_change)).setPaddingRelative(0, 3, 0, 3);
			//((ImageView)rootView.findViewById(R.id.for_30_rent)).setImageResource(R.drawable.up_g);
		}
		else {
			//((ImageView)rootView.findViewById(R.id.for_30_rent)).setImageResource(R.drawable.down_r);
			((TextView) rootView.findViewById(R.id.rent_change)).setCompoundDrawablesWithIntrinsicBounds(R.drawable.down_r, 0, 0,  0);
			//((TextView) rootView.findViewById(R.id.rent_change)).setPaddingRelative(0, 3, 0, 3);
		}
		((TextView) rootView.findViewById(R.id.rent_change)).setText(intent.getStringExtra("restimateValueChange")+" ");
		String bathroom_numbers=intent.getStringExtra("bath");
		//System.out.println("from string = "+bathroom_numbers);
		((TextView) rootView.findViewById(R.id.batroom_number)).setText(bathroom_numbers+" ");
		//System.out.println("from assignment ="+((TextView) rootView.findViewById(R.id.bedroom_number)).getText());
		((TextView) rootView.findViewById(R.id.range)).setText(intent.getStringExtra("estimateValuationRangeLow")+ " - "+intent.getStringExtra("estimateValuationRangeHigh")+" ");
		((TextView) rootView.findViewById(R.id.bedroom_number)).setText(intent.getStringExtra("bed")+" ");
		if((intent.getStringExtra("restimateLastUpdate")).contentEquals("01-Jan-1970")){
			((TextView) rootView.findViewById(R.id.rent_left)).setText("N/A");
		}
		else {
			((TextView) rootView.findViewById(R.id.rent_left)).setText("Rent Zestimate \u00AE Valuation of " + intent.getStringExtra("restimateLastUpdate")+" ");
		}
		((TextView) rootView.findViewById(R.id.tay)).setText(intent.getStringExtra("taxAssessmentYear")+" ");
		((TextView) rootView.findViewById(R.id.ta)).setText(intent.getStringExtra("taxAssessment")+" ");
		((TextView) rootView.findViewById(R.id.rentval)).setText(intent.getStringExtra("restimateAmount")+" ");
		((TextView) rootView.findViewById(R.id.rentrange)).setText(intent.getStringExtra("restimateValuationRangeLow")+" - "+intent.getStringExtra("restimateValuationRangeHigh")+" ");
		String toplogo = "\u00a9 Zillow, Inc., 2006-2014.<br> Use is subject to <a href='http://www.zillow.com/corp/Terms.htm'>Terms of Use</a><br><a href='http://www.zillow.com/zestimate/' >What's a Zestimate?</a>";
		((TextView) rootView.findViewById(R.id.logotop)).setText(Html.fromHtml(toplogo));
		((TextView) rootView.findViewById(R.id.logotop)).setMovementMethod(LinkMovementMethod.getInstance());
		
		
		/* Generating key hash programmatically
	    try {
	        PackageInfo info = thiscontext.getPackageManager().getPackageInfo(thiscontext.getPackageName(),PackageManager.GET_SIGNATURES);
	        for (Signature signature : info.signatures) {
	        MessageDigest md = MessageDigest.getInstance("SHA");
	        md.update(signature.toByteArray());
	        System.out.println("KeyHash : "+ Base64.encodeToString(md.digest(), Base64.DEFAULT));
	        }
	        } catch (NameNotFoundException e) {
	        } catch (NoSuchAlgorithmException e) {
	        }*/
		
		 ImageView img = (ImageView)rootView.findViewById(R.id.fbshare);
	        img.setOnClickListener(new View.OnClickListener(){
	            public void onClick(View v){
	            	
	            	AlertDialog.Builder getresp = new AlertDialog.Builder(thiscontext);
	        		getresp.setMessage("Post to Facebook?");
	        		
	        		//Defining positive response
	        		getresp.setPositiveButton("Post Property Details",
	            	        new DialogInterface.OnClickListener() {
	                    public void onClick(DialogInterface dialog, int which) {
	                    	/*if (FacebookDialog.canPresentShareDialog(thiscontext, 
	                                FacebookDialog.ShareDialogFeature.SHARE_DIALOG)) {
	                    		String descr="Last Sold Price: "+DataTransporter.intent.getStringExtra("lastSoldPrice")+" 30 days Overall Change: "+DataTransporter.intent.getStringExtra("estimateValueChangeSign")+DataTransporter.intent.getStringExtra("estimateValueChange");
	                    	FacebookDialog shareDialog = new FacebookDialog.ShareDialogBuilder(getActivity())
	                    	.setName(DataTransporter.intent.getStringExtra("street")+", "+DataTransporter.intent.getStringExtra("city")+", "+DataTransporter.intent.getStringExtra("state")+", "+DataTransporter.intent.getStringExtra("zipcode"))
	                    	.setCaption("Property Information from Zillow.com")
	                        .setLink(DataTransporter.intent.getStringExtra("homedetails"))
	                        .setPicture(DataTransporter.intent.getStringExtra("year_1"))
	                        .setDescription(descr)
	                        .build();
	                    	uiHelper.trackPendingDialogCall(shareDialog.present());
	                    	System.out.println("##########################################");*/
	                    	Intent facebook = new Intent( thiscontext, ActivityforFB.class);
	                    	startActivity(facebook);
	                    //}
	                    }
	                });
	        		getresp.setNegativeButton("Cancel",
	            	        new DialogInterface.OnClickListener() {
	                    public void onClick(DialogInterface dialog, int which) {
	                        // Write your code here to execute after dialog
	                        Toast.makeText(thiscontext,
	                                "Post Cancelled", Toast.LENGTH_SHORT)
	                                .show();
	                        dialog.cancel();
	                    }
	                });
	        		getresp.show();
	            	
	            	
	            }
	        });
		
		return rootView;
	}
	
	public void postToFacebook(){
		if (FacebookDialog.canPresentShareDialog(thiscontext, 
                FacebookDialog.ShareDialogFeature.SHARE_DIALOG)) {
    		String descr="Last Sold Price: "+DataTransporter.intent.getStringExtra("lastSoldPrice")+" 30 days Overall Change: "+DataTransporter.intent.getStringExtra("estimateValueChangeSign")+DataTransporter.intent.getStringExtra("estimateValueChange");
    	FacebookDialog shareDialog = new FacebookDialog.ShareDialogBuilder(getActivity())
    	.setName(DataTransporter.intent.getStringExtra("street")+", "+DataTransporter.intent.getStringExtra("city")+", "+DataTransporter.intent.getStringExtra("state")+", "+DataTransporter.intent.getStringExtra("zipcode"))
    	.setCaption("Property Information from Zillow.com")
        .setLink(DataTransporter.intent.getStringExtra("homedetails"))
        .setPicture(DataTransporter.intent.getStringExtra("year_1"))
        .setDescription(descr)
        .build();
    	uiHelper.trackPendingDialogCall(shareDialog.present());
    	
    	
    	/*Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.addCategory(Intent.CATEGORY_BROWSABLE);
        intent.setData(Uri.parse("http://www.zillow.com/"));
        startActivity(intent); */
    }
    	else{
    		        	            					
    		publishFeedDialog();
    	}
    	
	}
	
	private void onSessionStateChange(Session session, SessionState state, Exception exception) {
	    if (state.isOpened()) {
	        System.out.println("FROM SESSION STATE Logged in...");
	    } else if (state.isClosed()) {
	        System.out.println(" FROM SESSION STATE : Logged out...");
	    }
	}
	
	private void publishFeedDialog() {
	    Bundle params = new Bundle();
	    params.putString("name", DataTransporter.intent.getStringExtra("street")+", "+DataTransporter.intent.getStringExtra("city")+", "+DataTransporter.intent.getStringExtra("state")+", "+DataTransporter.intent.getStringExtra("zipcode"));
	    params.putString("caption", "Property Information from Zillow.com");
	    String descr="Last Sold Price: "+DataTransporter.intent.getStringExtra("lastSoldPrice")+" 30 days Overall Change: "+DataTransporter.intent.getStringExtra("estimateValueChangeSign")+DataTransporter.intent.getStringExtra("estimateValueChange");
	    params.putString("description",descr);
	    params.putString("link", DataTransporter.intent.getStringExtra("homedetails"));
	    params.putString("picture", DataTransporter.intent.getStringExtra("year_1"));
	   
		Session session = Session.getActiveSession();
		if (!session.isOpened())
		{	
			session.openForRead(new Session.OpenRequest(this));
		}
		Session.setActiveSession(session);
	    WebDialog feedDialog = (
	        new WebDialog.FeedDialogBuilder(getActivity(),
	            session,
	            params))
	        .setOnCompleteListener(new OnCompleteListener() {

	            @Override
	            public void onComplete(Bundle values,
	                FacebookException error) {
	                if (error == null) {
	                    // When the story is posted, echo the success
	                    // and the post Id.
	                    final String postId = values.getString("post_id");
	                    if (postId != null) {
	                        Toast.makeText(getActivity(),
	                            "Posted Story, ID: "+postId,
	                            Toast.LENGTH_SHORT).show();
	                    } else {
	                        // User clicked the Cancel button
	                        Toast.makeText(getActivity().getApplicationContext(), 
	                            "Post cancelled", 
	                            Toast.LENGTH_SHORT).show();
	                    }
	                } else if (error instanceof FacebookOperationCanceledException) {
	                    // User clicked the "x" button
	                    Toast.makeText(getActivity().getApplicationContext(), 
	                        "Post cancelled", 
	                        Toast.LENGTH_SHORT).show();
	                } else {
	                    // Generic, ex: network error
	                    Toast.makeText(getActivity().getApplicationContext(), 
	                        "Error posting story", 
	                        Toast.LENGTH_SHORT).show();
	                }
	            }

	        })
	        .build();
	    feedDialog.show();
	}
	
	 @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        uiHelper = new UiLifecycleHelper(getActivity(), callback);
	        uiHelper.onCreate(savedInstanceState);
	    }
	  
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
	    super.onActivityResult(requestCode, resultCode, data);
	   //uiHelper.onActivityResult(requestCode, resultCode, data);
	    //for share dialog
	    uiHelper.onActivityResult(requestCode, resultCode, data, new FacebookDialog.Callback() {
	    	
	    	@Override
	        public void onError(FacebookDialog.PendingCall pendingCall, Exception error, Bundle data) {
	            Log.e("Activity", String.format("Error: %s", error.toString()));
	            Toast.makeText(thiscontext, "Post Cancelled", Toast.LENGTH_LONG).show();
	        }
	        //
	        @Override
	        public void onComplete(FacebookDialog.PendingCall pendingCall, Bundle data) {
	            Log.i("Activity", "Success!");
	            String postId = FacebookDialog.getNativeDialogPostId(data);
	            Toast.makeText(thiscontext, "Posted Story,ID: "+postId, Toast.LENGTH_LONG).show();
	        }
	    }); 
	} 
	
	//configure other methods on uiHelper to handle Activity lifecycle callbacks correctly.
	
	@Override
	public void onResume() {
	    super.onResume();
	    /*
	    Session session = Session.getActiveSession();
	    if (session != null &&
	           (session.isOpened() || session.isClosed()) ) {
	        onSessionStateChange(session, session.getState(), null);
	    }
		*/
	    
	    uiHelper.onResume();
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
	    super.onSaveInstanceState(outState);
	    uiHelper.onSaveInstanceState(outState);
	    
	}

	@Override
	public void onPause() {
	    super.onPause();
	    uiHelper.onPause();
	}

	@Override
	public void onDestroy() {
	    super.onDestroy();
	    uiHelper.onDestroy();
	}
}
