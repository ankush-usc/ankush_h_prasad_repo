package csci571.real_estate_search_ahp;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.TextView;



public class TabsFragmentMainActivity extends FragmentActivity implements ActionBar.TabListener {

	private ViewPager viewPager;
	private TabsPagerAdapter mAdapter;
	private ActionBar actionBar;
	//Activity i = getActivity();
	// Tab titles
	private String[] tabs = { "BASIC INFO", "HISTORICAL ZESTIMATE" };
	String tag[]={"T1","T2"};
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tabs_layout_22);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		// Initilization
		viewPager = (ViewPager) findViewById(R.id.pager);
		actionBar = getActionBar();
		mAdapter = new TabsPagerAdapter(getSupportFragmentManager());

		viewPager.setAdapter(mAdapter);
		actionBar.setHomeButtonEnabled(false);
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);		
		
		// Adding Tabs
		//int i=0;
		for (String tab_name : tabs) {
			actionBar.addTab(actionBar.newTab().setText(tab_name)
					.setTabListener(this));
			//System.out.println("TAG[i]="+ tag[i]+ "   from the tab = "+getSupportFragmentManager().findFragmentByTag(tag[i]));
			//i++;
		}
		

		/**
		 * on swiping the viewpager make respective tab selected
		 * */
		viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

			@Override
			public void onPageSelected(int position) {
				// on changing the page
				// make respected tab selected
				actionBar.setSelectedNavigationItem(position);
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
			}
		});
	}

	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		// on tab selected
		// show respected fragment view
		viewPager.setCurrentItem(tab.getPosition());
		
		
	}

	/*public void previous_event(View view){
		
		 //getSupportFragmentManager().findFragmentByTag(tabs[1])).previous_event(view);
		FragmentManager fm = getSupportFragmentManager();
		System.out.println("Here");
		tab_fragment2_22 fragment = (tab_fragment2_22)fm.findFragmentByTag(tag[1]);
		System.out.println("entered and did I get fragment? = "+ fm.findFragmentByTag(tag[1]));
		System.out.println("Tag selected = "+fragment.getTag());
		fragment.previous_event(view);
	}
	
	public void next_event(View view){
		FragmentManager fm = getSupportFragmentManager();
		tab_fragment2_22 fragment = (tab_fragment2_22)fm.findFragmentByTag("T2");
		fragment.next_event(view);
	}*/
	
	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
	}
	
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
	}
}
