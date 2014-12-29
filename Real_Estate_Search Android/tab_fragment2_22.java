package csci571.real_estate_search_ahp;


import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewSwitcher.ViewFactory;
import android.widget.Toast;
import android.text.Html;
import android.text.method.LinkMovementMethod;

public class tab_fragment2_22 extends Fragment {
	private Button btn;
	private static ImageSwitcher imageSwitcher1;
	private static TextSwitcher textSwitcher1;
	static String year_1;
	static String year_5;
	static String year_10;
	static String text_1;
	static String text_5,text_10;
	static int current_index = 0;
	static Context thiscontext;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.tab_frag2_22, container, false);
		
		thiscontext=container.getContext();
		Intent intent=DataTransporter.intent;
		year_1 = intent.getStringExtra("year_1");
		year_5 = intent.getStringExtra("year_5");
		year_10 = intent.getStringExtra("year_10");
		text_1="<h4>Historical Zestimate for the past 1 year </h4>"+intent.getStringExtra("street")+", "+intent.getStringExtra("city")+", "+intent.getStringExtra("state")+", "+intent.getStringExtra("zipcode");
		text_5="<h4>Historical Zestimate for the past 5 years </h4>"+intent.getStringExtra("street")+", "+intent.getStringExtra("city")+", "+intent.getStringExtra("state")+", "+intent.getStringExtra("zipcode");
		text_10="<h4>Historical Zestimate for the past 10 years </h4>"+intent.getStringExtra("street")+", "+intent.getStringExtra("city")+", "+intent.getStringExtra("state")+", "+intent.getStringExtra("zipcode");
		//System.out.println("Assigned strings 1 => "+year_1+" and " + year_5+" and "+year_10);
		//btn = (Button)rootView.findViewById(R.id.Button_next);
		String toplogo = "\u00a9 Zillow, Inc., 2006-2014.<br> Use is subject to <a href='http://www.zillow.com/corp/Terms.htm'>Terms of Use</a><br><a href='http://www.zillow.com/zestimate/' >What's a Zestimate?</a>";
		((TextView) rootView.findViewById(R.id.logotop)).setText(Html.fromHtml(toplogo));
		((TextView) rootView.findViewById(R.id.logotop)).setMovementMethod(LinkMovementMethod.getInstance());
		imageSwitcher1 = (ImageSwitcher)rootView.findViewById(R.id.imageSwitcher);
		textSwitcher1 = (TextSwitcher)rootView.findViewById(R.id.textSwitcher);
		//System.out.println("Switcher received="+((rootView.findViewById(R.id.textSwitcher))).getAlpha());
		textSwitcher1.setFactory(new ViewFactory(){

			@Override
			public View makeView() {
				TextView myText = new TextView(thiscontext);
				myText.setLayoutParams(new TextSwitcher.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
				myText.setGravity(Gravity.TOP|Gravity.CENTER);
				myText.setPadding(0, 0, 0, 0);
				myText.setTextColor(Color.BLACK);
				//System.out.println("TEXT1 inside switcher = "+text_1);
				//myText.setText(text_1);
				return myText;
			}			
		});
		imageSwitcher1.setFactory(new ViewFactory(){
			public View makeView(){
				ImageView myview = new ImageView(thiscontext);
				myview.setScaleType(ImageView.ScaleType.FIT_CENTER);
				myview.setLayoutParams(new ImageSwitcher.LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT));
				return myview;
			}
		});
		//System.out.println("PASS1");
		//System.out.println("PASS2");
		Animation in = AnimationUtils.loadAnimation(getActivity(),android.R.anim.slide_in_left);
	    Animation out = AnimationUtils.loadAnimation(getActivity(),android.R.anim.slide_out_right);
	    imageSwitcher1.setInAnimation(in);
	    imageSwitcher1.setOutAnimation(out);
	    textSwitcher1.setInAnimation(in);
	    textSwitcher1.setOutAnimation(out);
	    /* On default, these are displayed */
	    textSwitcher1.setText(Html.fromHtml(text_1));
	    //textSwitcher1.setText(text_1);
	    try{
			
			Drawable drw =  new Get_Image(year_1).execute(year_1).get();
			imageSwitcher1.setImageDrawable(drw);
			
		}
		catch(Exception e){
			System.out.println("Exception on retrieving images"+e.getMessage());
		}
	    Button button_next = (Button)rootView.findViewById(R.id.Button_next);
	    button_next.setOnClickListener( new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				next_event(v);
			}
		
	    });
	    Button button_previous = (Button)rootView.findViewById(R.id.Button_previous);
	    button_previous.setOnClickListener( new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				previous_event(v);
			}
		
	    });
		return rootView; 
	}

	public void next_event(View view){

	      Animation in = AnimationUtils.loadAnimation(getActivity(),
	      android.R.anim.slide_in_left);
	      Animation out = AnimationUtils.loadAnimation(getActivity(),
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
			 //Toast.makeText(thiscontext, "Content loaded", Toast.LENGTH_LONG).show();
	   }
	   public void previous_event(View view){
		   
	     
	      Animation in = AnimationUtils.loadAnimation(getActivity(),android.R.anim.slide_out_right);
	      Animation out = AnimationUtils.loadAnimation(getActivity(),android.R.anim.slide_in_left);
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
			//Toast.makeText(thiscontext, "Content loaded", Toast.LENGTH_LONG).show();
	   }
}
