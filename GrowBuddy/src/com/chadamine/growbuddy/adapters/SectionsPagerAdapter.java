package com.chadamine.growbuddy.adapters;

import com.chadamine.growbuddy.fragments.AnalysisOverviewFragment;
import com.chadamine.growbuddy.fragments.CultivationOverviewFragmentContainer;
import com.chadamine.growbuddy.fragments.JournalDetailsFragment;
import com.chadamine.growbuddy.fragments.JournalsOverviewContainer;
import com.chadamine.growbuddy.fragments.ScheduleOverviewFragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

public class SectionsPagerAdapter extends FragmentPagerAdapter {

	public SectionsPagerAdapter(FragmentManager fm) {
		super(fm);
	}

	@Override
	public Fragment getItem(int position) {
		// getItem is called to instantiate the fragment for the given page.
		
		Fragment itemFragment = new Fragment();
			/*if(listContainer instanceof JournalDetailsFragment) {
				Log.d("detailsFragmentFound", "+ journal details found in listContainer");
				return itemFragment;
			}*/
			
		switch(position) {
			case 0:	// Journals
				
				itemFragment = new JournalsOverviewContainer();
				break;
				
			case 1:	// Schedule
				itemFragment = new ScheduleOverviewFragment();
				break;
				
			case 2:	// Cultivation
				itemFragment = new CultivationOverviewFragmentContainer();
				break;
			
			case 3:	// Analysis
				itemFragment = new AnalysisOverviewFragment();
				break;
				
		}
		return itemFragment;
	}

	@Override
	public int getCount() {
		// Show 4 total pages.
		return 4;
	}

	@Override
	public CharSequence getPageTitle(int position) {
		
		//Locale l = Locale.getDefault();
		
		switch (position) {
			case 0:
				return "Journals";
			case 1:
				//return getString(R.string.title_tab_irrigation).toUpperCase(l);
				return "Schedule";
			case 2:
				return "Cultivation";
				//return getString(R.string.title_tab_genetics).toUpperCase(l);
			case 3:
				return "Analysis";
		}
		return null;
	}
}
