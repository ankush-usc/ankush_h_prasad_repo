package csci571.real_estate_search_ahp;

import java.io.InputStream;
import java.net.URI;
import java.net.URL;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import android.widget.ViewSwitcher.ViewFactory;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.Toast;

public class ZestActivity extends Activity {

	private Button btn;
	private ImageSwitcher imageSwitcher1;
	private TextSwitcher textSwitcher1;
	String year_1;
	String year_5;
	String year_10;
	String text_1;
	String text_5,text_10;
	int current_index = 0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_zest);
		Intent intent=getIntent();
		year_1 = intent.getStringExtra("year_1");
		year_5 = intent.getStringExtra("year_5");
		year_10 = intent.getStringExtra("year_10");
		text_1="<h3>Historical Zestimate for the past 1 year </h3>\n"+intent.getStringExtra("street")+", "+intent.getStringExtra("city")+", "+intent.getStringExtra("state")+", "+intent.getStringExtra("zipcode");
		text_5="<h3>Historical Zestimate for the past 5 years </h3>\n"+intent.getStringExtra("street")+", "+intent.getStringExtra("city")+", "+intent.getStringExtra("state")+", "+intent.getStringExtra("zipcode");
		text_10="<h3>Historical Zestimate for the past 10 years </h3>\n"+intent.getStringExtra("street")+", "+intent.getStringExtra("city")+", "+intent.getStringExtra("state")+", "+intent.getStringExtra("zipcode");
		System.out.println("Assigned strings 1 => "+year_1+" and " + year_5+" and "+year_10);
		btn = (Button)findViewById(R.id.Button_next);
		imageSwitcher1 = (ImageSwitcher)findViewById(R.id.imageSwitcher);
		textSwitcher1 = (TextSwitcher)findViewById(R.id.textSwitcher);
		System.out.println("Switcher received");
		imageSwitcher1.setFactory(new ViewFactory(){
			public View makeView(){
				ImageView myview = new ImageView(getApplicationContext());
				myview.setScaleType(ImageView.ScaleType.FIT_CENTER);
				myview.setLayoutParams(new ImageSwitcher.LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT));
				return myview;
			}
		});
		//System.out.println("PASS1");
		textSwitcher1.setFactory(new ViewFactory(){

			@Override
			public View makeView() {
				TextView myText = new TextView(getApplicationContext());
				myText.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL);
				myText.setPadding(0, 0, 0, 0);
				myText.setTextColor(000000);
				System.out.println("TEXT1 inside switcher = "+text_1);
				myText.setText(Html.fromHtml(text_1));
				return myText;
			}			
		});
		//System.out.println("PASS2");
		Animation in = AnimationUtils.loadAnimation(this,  android.R.anim.slide_in_left);
	    Animation out = AnimationUtils.loadAnimation(this, android.R.anim.slide_out_right);
	    imageSwitcher1.setInAnimation(in);
	    imageSwitcher1.setOutAnimation(out);
	    textSwitcher1.setInAnimation(in);
	    textSwitcher1.setOutAnimation(out);
	    /* On default, these are displayer */
	    //System.out.println("TEXT1 = "+text_1+"YEAR1 = "+year_1);
	    textSwitcher1.setText(Html.fromHtml(text_1));
	    try{
			
			Drawable drw =  new Get_Image(year_1).execute(year_1).get();
			imageSwitcher1.setImageDrawable(drw);
			
		}
		catch(Exception e){
			System.out.println("Exception on retrieving images"+e.getMessage());
		}
	    System.out.println("Initial Pass");
	   /* btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				current_index++;
				if(current_index==3)current_index=0;
				//System.out.println("URI being parsed for string = "+year_1);
				URI uri = URI.create(year_1);
				//System.out.println(uri);
				//imageSwitcher1.setImageURI(uri);
				try{
										
					Drawable drw =  new Get_Image(year_1).execute(year_1).get();
					imageSwitcher1.setImageDrawable(drw);
					
				}
				catch(Exception e){
					System.out.println("Exception on retrieving images"+e.getMessage());
				}
				System.out.println("uri set");
			}
		});*/
	}

	public void next_event(View view){
	      Toast.makeText(getApplicationContext(), "Next Image", 
	      Toast.LENGTH_LONG).show();
	      Animation in = AnimationUtils.loadAnimation(this,
	      android.R.anim.slide_in_left);
	      Animation out = AnimationUtils.loadAnimation(this,
	      android.R.anim.slide_out_right);
	      imageSwitcher1.setInAnimation(in);
	      imageSwitcher1.setOutAnimation(out);
	      textSwitcher1.setInAnimation(in);
	      textSwitcher1.setOutAnimation(out);
	      
	      current_index++;
			if(current_index==3)current_index=0;
			//System.out.println("URI being parsed for string = "+year_1);
			String year=year_10;
			String text=text_10;
			if(current_index==0){
				text=text_1;year=year_1;
			}
			else if(current_index==1){
				year=year_5;text=text_5;
			}
			else{
				year=year_10;text=text_10;
			}
			textSwitcher1.setText(Html.fromHtml(text));
			//URI uri = URI.create(year);
			//System.out.println(uri);
			//imageSwitcher1.setImageURI(uri);
			try{
									
				Drawable drw =  new Get_Image(year).execute(year).get();
				imageSwitcher1.setImageDrawable(drw);
				
			}
			catch(Exception e){
				System.out.println("Exception on retrieving images"+e.getMessage());
			}
	   }
	   public void previous_event(View view){
	      Toast.makeText(getApplicationContext(), "previous Image", 
	      Toast.LENGTH_LONG).show();
	      Animation in = AnimationUtils.loadAnimation(this,
	      android.R.anim.slide_out_right);
	      Animation out = AnimationUtils.loadAnimation(this,
	      android.R.anim.slide_in_left);
	      imageSwitcher1.setInAnimation(in);
	      imageSwitcher1.setOutAnimation(out);
	      current_index--;
			if(current_index==-1)current_index=2;
			//System.out.println("URI being parsed for string = "+year_1);
			String year=year_10;
			String text=text_10;
			if(current_index==0){
				text=text_1;year=year_1;
			}
			else if(current_index==1){
				year=year_5;text=text_5;
			}
			else{
				year=year_10;text=text_10;
			}
			textSwitcher1.setText(Html.fromHtml(text));
			//URI uri = URI.create(year_1);
			//System.out.println(uri);
			//imageSwitcher1.setImageURI(uri);
			try{
									
				Drawable drw =  new Get_Image(year).execute(year).get();
				imageSwitcher1.setImageDrawable(drw);
				
			}
			catch(Exception e){
				System.out.println("Exception on retrieving images"+e.getMessage());
			}
	   }
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.zest, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
