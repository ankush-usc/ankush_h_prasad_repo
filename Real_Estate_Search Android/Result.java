package csci571.real_estate_search_ahp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class Result extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_result);
		//Intent intent = getIntent();
		Intent intent = DataTransporter.intent;
		//Assigning variables
		System.out.println("Assigning Variables");
		String homedetial_traversal="<a href=\""+intent.getStringExtra("homedetails")+"\">"+intent.getStringExtra("street")+", "+intent.getStringExtra("city")+", "+intent.getStringExtra("state")+", "+intent.getStringExtra("zipcode")+" </a>";
		((TextView) findViewById(R.id.capt)).setText(Html.fromHtml(homedetial_traversal));
		((TextView) findViewById(R.id.capt)).setMovementMethod(LinkMovementMethod.getInstance());
		((TextView) findViewById(R.id.ptype)).setText(intent.getStringExtra("useCode"));
		if(intent.getStringExtra("lastSoldPrice").contentEquals("$0.00")){
			((TextView) findViewById(R.id.soldp)).setText("N/A");
		}
		else
			((TextView) findViewById(R.id.soldp)).setText(intent.getStringExtra("lastSoldPrice"));
		((TextView) findViewById(R.id.yearb)).setText(intent.getStringExtra("yearBuilt"));
		if((intent.getStringExtra("lastSoldDate")).contentEquals("01-Jan-1970")){
			((TextView) findViewById(R.id.soldd)).setText("N/A");
		}
		else
			((TextView) findViewById(R.id.soldd)).setText(intent.getStringExtra("lastSoldDate"));
		((TextView) findViewById(R.id.lots)).setText(intent.getStringExtra("lotSizeSqFt"));
		((TextView) findViewById(R.id.zest)).setText(intent.getStringExtra("estimateAmount"));
		((TextView) findViewById(R.id.zest_left)).setText("Zestimate \u00AE Property Estimate as of "+ intent.getStringExtra("estimateLastUpdate"));
		((TextView) findViewById(R.id.finish)).setText(intent.getStringExtra("finishedSqFt"));
		//System.out.println(intent.getStringExtra("estimateValueChangeSign"));
		if(intent.getStringExtra("estimateValueChangeSign").contentEquals("+")){
			//((TextView) findViewById(R.id.overall_change)).setCompoundDrawablesWithIntrinsicBounds( R.drawable.up_g, 0, 0, 0);
			String for_overall_change="<img src=\"CSCI_Real_Estate_Search_AHP/res/drawable/up_g.gif\" />"+intent.getStringExtra("estimateValueChange");
			System.out.println(for_overall_change);
			((TextView) findViewById(R.id.overall_change)).setText(Html.fromHtml(for_overall_change));
		}
		else {
			String for_overall_change="<img src=\""+getResources().getResourceName(R.drawable.down_r)+"\" />"+intent.getStringExtra("estimateValueChange");
			System.out.println("for_overall_change");
			((TextView) findViewById(R.id.overall_change)).setText(Html.fromHtml(for_overall_change));
			//((TextView) findViewById(R.id.overall_change)).setCompoundDrawablesWithIntrinsicBounds( R.drawable.down_r, 0, 0, 0);
		}
		//((TextView) findViewById(R.id.overall_change)).setText(intent.getStringExtra("estimateValueChange"));
		if(intent.getStringExtra("restimateValueChangeSign").contentEquals("+")){
			((TextView) findViewById(R.id.rent_change)).setCompoundDrawablesWithIntrinsicBounds( R.drawable.up_g, 0, 0, 0);
			((TextView) findViewById(R.id.rent_change)).setPaddingRelative(0, 3, 0, 3);
		}
		else {
			((TextView) findViewById(R.id.rent_change)).setCompoundDrawablesWithIntrinsicBounds(R.drawable.down_r, 0, 0,  0);
			((TextView) findViewById(R.id.rent_change)).setPaddingRelative(0, 3, 0, 3);
		}
		((TextView) findViewById(R.id.rent_change)).setText(intent.getStringExtra("restimateValueChange"));
		String bathroom_numbers=intent.getStringExtra("bath");
		//System.out.println("from string = "+bathroom_numbers);
		((TextView) findViewById(R.id.batroom_number)).setText(bathroom_numbers);
		//System.out.println("from assignment ="+((TextView) findViewById(R.id.bedroom_number)).getText());
		((TextView) findViewById(R.id.range)).setText(intent.getStringExtra("estimateValuationRangeLow")+ " - "+intent.getStringExtra("estimateValuationRangeHigh"));
		((TextView) findViewById(R.id.bedroom_number)).setText(intent.getStringExtra("bed"));
		if((intent.getStringExtra("restimateLastUpdate")).contentEquals("01-Jan-1970")){
			((TextView) findViewById(R.id.rent_left)).setText("N/A");
		}
		else {
			((TextView) findViewById(R.id.rent_left)).setText("Rent Zestimate \u00AE Valuation of " + intent.getStringExtra("restimateLastUpdate"));
		}
		((TextView) findViewById(R.id.tay)).setText(intent.getStringExtra("taxAssessmentYear"));
		((TextView) findViewById(R.id.ta)).setText(intent.getStringExtra("taxAssessment"));
		((TextView) findViewById(R.id.rentval)).setText(intent.getStringExtra("restimateAmount"));
		((TextView) findViewById(R.id.rentrange)).setText(intent.getStringExtra("restimateValuationRangeLow")+" - "+intent.getStringExtra("restimateValuationRangeHigh"));
		String toplogo = "\u00a9 Zillow, Inc., 2006-2014.<br> Use is subject to <a href='http://www.zillow.com/corp/Terms.htm'>Terms of Use</a><br><a href='http://www.zillow.com/zestimate/' >What's a Zestimate?</a>";
		((TextView) findViewById(R.id.logotop)).setText(Html.fromHtml(toplogo));
		((TextView) findViewById(R.id.logotop)).setMovementMethod(LinkMovementMethod.getInstance());
		System.out.println("Variables assigned");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.result, menu);
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
