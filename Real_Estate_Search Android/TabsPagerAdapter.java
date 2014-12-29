package csci571.real_estate_search_ahp;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class TabsPagerAdapter extends FragmentPagerAdapter {

	public TabsPagerAdapter(FragmentManager fm) {
		super(fm);
	}

	@Override
	public Fragment getItem(int index) {

		switch (index) {
		case 0:
			// Basic Info fragment activity
			return new tab_fragment1_22();
		case 1:
			// Historic Zestimates fragment activity
			return new tab_fragment2_22();
		}

		return new tab_fragment2_22();
	}

	@Override
	public int getCount() {
		// get item count - equal to number of tabs
		return 2;
	}

}
