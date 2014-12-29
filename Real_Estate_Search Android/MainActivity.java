package csci571.real_estate_search_ahp;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.facebook.AppEventsLogger;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends Activity implements OnItemSelectedListener  {

	//public final static String ADDR_MESSAGE = "csci571.real_estate_search_ahp.MESSAGE";
	//public final static String CITY_MESSAGE = "csci571.real_estate_search_ahp.MESSAGE";

	Spinner spinner;
	Spinner spStates;
	String selected="";
	JSONObject req_json;
	int aflag=1,cflag=1,sflag=1;
//Spinner spStateType;
	ArrayAdapter<CharSequence> adapterState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,paths);
        TextView nojsonerror = (TextView) findViewById(R.id.error_content);
        //Initialize spinners
        spStates = (Spinner)findViewById(R.id.spstates);
        //spinner = (Spinner)findViewById(R.id.spinner1);
        adapterState = ArrayAdapter.createFromResource(this,R.array.state_arrays, android.R.layout.simple_spinner_item);
        adapterState.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spStates.setAdapter(adapterState);
        spStates.setOnItemSelectedListener(this);
        nojsonerror.setVisibility(View.INVISIBLE);
        //Making the image hyperlink to a url
        
        ImageView img = (ImageView)findViewById(R.id.zillow_image);
        img.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("http://www.zillow.com/"));
                startActivity(intent);
            }
        });
        
    ///////////////////////////////////////////////////////////////
    }
    
    //On click event handler for button search
    
    public void validate_send(View view){
    	//Intent intent = new Intent(this, Tabbed_Control.class);
    	
    	//Test purpose- check individual tabs
    	
    	Intent intent1 = new Intent(this, TabsFragmentMainActivity.class);
    	Intent intent = new Intent(this,Result.class);
    	DataTransporter.intent = intent;
    	TextView nojsonerror = (TextView) findViewById(R.id.error_content);
    	//nojsonerror.setText("No exact match found--Verify that the given address is correct.");
    	nojsonerror.setVisibility(View.INVISIBLE);
    	//get address
    	EditText editAddr = (EditText) findViewById(R.id.address_message);
    	String smessage = editAddr.getText().toString();
    	//intent.putExtra("ADDR_MESSAGE", smessage);
    	//get city
    	EditText editCity = (EditText) findViewById(R.id.city_message);
    	String cmessage = editCity.getText().toString();
    	//intent.putExtra("CITY_MESSAGE", cmessage);
    	//get state
    	//intent.putExtra("state_sel", selected);
    	// Validating the user input
    	TextView Adr_ivd = (TextView) findViewById(R.id.invalid_address);
    	if(smessage.isEmpty()){
    		Adr_ivd.setVisibility(View.VISIBLE);
    		aflag=0;
    	}
    	else{
    		Adr_ivd.setVisibility(View.INVISIBLE);
    		aflag=1;
    	}
    	Adr_ivd = (TextView) findViewById(R.id.invalid_city);
    	if(cmessage.isEmpty()){
    		Adr_ivd.setVisibility(View.VISIBLE);
    		cflag=0;
    	}
    	else{
    		Adr_ivd.setVisibility(View.INVISIBLE);
    		cflag=1;
    	}
    	Adr_ivd = (TextView) findViewById(R.id.invalid_state);
    	if(selected.contains("Choose State")){
    		Adr_ivd.setVisibility(View.VISIBLE);
    		sflag=0;
    	}
    	else{
    		Adr_ivd.setVisibility(View.INVISIBLE);
    		sflag=1;
    	}
    	if(aflag==1 && cflag==1 && sflag==1){
    	//construct URL
    	Uri.Builder b = Uri.parse("http://<id>.elasticbeanstalk.com/?").buildUpon();
    	b.appendQueryParameter("address", smessage);
    	b.appendQueryParameter("mycity",cmessage);
    	b.appendQueryParameter("mystate",selected);
    	String url = b.build().toString();
    	intent.putExtra("URL", url);
    	
    	//req_json = p.getJSONFromUrl();
    	try{
    	req_json = new Processjson(url).execute(url).get();
    	}
    	catch(Exception e){
    	System.out.println("Exception while calling = "+e.toString());
    	}
    	if(req_json!=null){
    	try{
    		try{
    		JSONObject result= req_json.getJSONObject("result");
    		/*Populate data*/
    		intent.putExtra("homedetails",result.getString("homedetails"));
    		intent.putExtra("street",result.getString("street"));
    		intent.putExtra("city",result.getString("city"));
    		intent.putExtra("state",result.getString("state"));
    		intent.putExtra("zipcode",result.getString("zipcode"));
    		intent.putExtra("latitude",result.getString("latitude"));
    		intent.putExtra("longitude",result.getString("longitude"));
    		intent.putExtra("useCode",result.getString("useCode"));
    		intent.putExtra("lastSoldPrice",result.getString("lastSoldPrice"));
    		intent.putExtra("yearBuilt",result.getString("yearBuilt"));
    		intent.putExtra("lastSoldDate",result.getString("lastSoldDate"));
    		intent.putExtra("lotSizeSqFt",result.getString("lotSizeSqFt"));
    		intent.putExtra("estimateLastUpdate",result.getString("estimateLastUpdate"));
    		intent.putExtra("estimateAmount",result.getString("estimateAmount"));
    		intent.putExtra("finishedSqFt",result.getString("finishedSqFt"));
    		intent.putExtra("estimateValueChangeSign",result.getString("estimateValueChangeSign"));
    		intent.putExtra("imgn",result.getString("imgn"));
    		intent.putExtra("imgp",result.getString("imgp"));
    		intent.putExtra("bed",result.getString("bedrooms"));
    		intent.putExtra("bath",result.getString("bathrooms"));
    		//System.out.println("bath = "+result.getString("bathrooms")+"and bed="+result.getString("bedrooms"));
    		intent.putExtra("estimateValueChange",result.getString("estimateValueChange"));
    		intent.putExtra("estimateValuationRangeLow",result.getString("estimateValuationRangeLow"));
    		intent.putExtra("estimateValuationRangeHigh",result.getString("estimateValuationRangeHigh"));
    		intent.putExtra("restimateLastUpdate",result.getString("restimateLastUpdate"));
    		intent.putExtra("restimateAmount",result.getString("restimateAmount"));
    		intent.putExtra("taxAssessmentYear",result.getString("taxAssessmentYear"));
    		intent.putExtra("restimateValueChangeSign",result.getString("restimateValueChangeSign"));
    		intent.putExtra("restimateValueChange",result.getString("restimateValueChange"));
    		intent.putExtra("taxAssessment",result.getString("taxAssessment"));
    		intent.putExtra("restimateValuationRangeLow",result.getString("restimateValuationRangeLow"));
    		intent.putExtra("restimateValuationRangeHigh",result.getString("restimateValuationRangeHigh"));
    		result= req_json.getJSONObject("chart");
    		intent.putExtra("year_1",result.getString("year_1"));
    		intent.putExtra("year_5",result.getString("year_5"));
    		intent.putExtra("year_10",result.getString("year_10"));
    		//==========================================================
    		}
    		catch(Exception e){
    			System.out.println("Elle problem " + e.toString());
    		}
    	}
    	catch(Exception e){
    	System.out.println("Error in reading JSAON sub object");
    	}
    	startActivity(intent1);
    	}
    	else{
    		//nojsonerror.setText("No exact match found--Verify that the given address is correct.");
    		nojsonerror.setVisibility(View.VISIBLE);

    	}
    	}
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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


	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
		selected = parent.getItemAtPosition(position).toString();
	}


	@Override
	public void onNothingSelected(AdapterView<?> parent) {
		
	}
	
	@Override
	protected void onResume() {
	  super.onResume();

	  // Logs 'install' and 'app activate' App Events.
	  AppEventsLogger.activateApp(this);
	}
	
	@Override
	protected void onPause() {
	  super.onPause();

	  // Logs 'app deactivate' App Event.
	  AppEventsLogger.deactivateApp(this);
	}
	
}
