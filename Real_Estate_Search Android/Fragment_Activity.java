package csci571.real_estate_search_ahp;

import csci571.real_estate_search_ahp.MainActivity;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.os.Build;

public class Fragment_Activity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_fragment);
		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
		Intent intent = getIntent();
	    String amessage = intent.getStringExtra("ADDR_MESSAGE");
	    String cmessage = intent.getStringExtra("CITY_MESSAGE");
	    // Create the text view with address
	    TextView textView = new TextView(this);
	    textView.setTextSize(20);
	    textView.setText(amessage);
	    
	    //with city
	    TextView textcity = new TextView(this);
	    textcity.setTextSize(20);
	    textcity.setText(cmessage);
	    
	    // Set the text view as the activity layout
	    setContentView(textView);
	    
	    //enable app icon up - to go back to parent screen
	    
	    getActionBar().setDisplayHomeAsUpEnabled(true);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.fragment_, menu);
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

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_fragment,
					container, false);
			return rootView;
		}
	}
}
