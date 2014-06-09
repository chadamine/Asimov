package com.chadamine.growbuddy;

import java.util.Locale;
import android.annotation.SuppressLint;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import com.chadamine.growbuddy.cultivation.environment.EnvironmentActivity.PlaceholderFragment;

public class OverviewActivity extends ActionBarActivity 
	implements ActionBar.TabListener {
	
	private Display display;
	private int displayHeight;
	private int displayWidth;
	private int orientation;
	private SectionsPagerAdapter sectionsPagerAdapter;
	private ViewPager mViewPager;
	private WindowManager windowManager;
	private ActionBar actionBar;
	
	@Override
	@SuppressWarnings("deprecation")
	@SuppressLint("NewApi")
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		
		int contentView = R.layout.activity_overview;

		windowManager =  (WindowManager) getSystemService(WINDOW_SERVICE);
	    display = windowManager.getDefaultDisplay();
		
		if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.FROYO){
			Point point = new Point();
			
			orientation = display.getRotation();
		    display.getSize(point);
		    displayWidth = point.x;
		    displayHeight = point.y;
		    
		} else {
		    orientation = display.getOrientation();
		    displayWidth = display.getWidth();
		    displayHeight = display.getHeight();
		}
		
		setContentView(contentView);

		actionBar = getSupportActionBar();
		
		if (displayWidth > 600)
			actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
		else {
			actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
			pagerSetup();
		}
	}

	private void pagerSetup() {
		
		sectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

		mViewPager = (ViewPager) findViewById(R.id.pager);
		mViewPager.setAdapter(sectionsPagerAdapter);

		mViewPager.setOnPageChangeListener(
				new ViewPager.SimpleOnPageChangeListener() {
					@Override
					public void onPageSelected(int position) {
						actionBar.setSelectedNavigationItem(position);
					}
				});

		for (int i = 0; i < sectionsPagerAdapter.getCount(); i++) {
			actionBar.addTab(actionBar.newTab()
					.setText(sectionsPagerAdapter.getPageTitle(i))
					.setTabListener(this));
		}
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		getMenuInflater().inflate(R.menu.environment, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onTabSelected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
		mViewPager.setCurrentItem(tab.getPosition());
	}

	@Override
	public void onTabUnselected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
	}

	@Override
	public void onTabReselected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
	}

	/**
	 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
	 * one of the sections/tabs/pages.
	 */
	public class SectionsPagerAdapter extends FragmentPagerAdapter {

		public SectionsPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			
			switch(position) {
				case 0:
					return new OverviewFragment();
				case 1:
					return new OverviewFragment();
				case 2:
					return new OverviewFragment();
				default:
					return new OverviewFragment();
			}
		}

		@Override
		public int getCount() {
			return 3;
		}

		@Override
		public CharSequence getPageTitle(int position) {
			Locale l = Locale.getDefault();
			switch (position) {
			case 0:
				return getString(R.string.title_section1).toUpperCase(l);
			case 1:
				return getString(R.string.title_section2).toUpperCase(l);
			case 2:
				return getString(R.string.title_section3).toUpperCase(l);
			}
			return null;
		}
	}
}
