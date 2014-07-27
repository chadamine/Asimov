package com.chadamine.growbuddy;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.chadamine.growbuddy.adapters.MainPageChangeListener;
import com.chadamine.growbuddy.adapters.SectionsPagerAdapter;
import com.chadamine.growbuddy.fragments.JournalDetailsFragment;
import com.chadamine.growbuddy.fragments.JournalsListFragment;
import com.chadamine.growbuddy.fragments.JournalsOverviewContainer;

public class Main extends ActionBarActivity implements
ActionBar.TabListener {

	/**
	 * The {@link android.support.v4.view.PagerAdapter} that will provide
	 * fragments for each of the sections. We use a {@link FragmentPagerAdapter}
	 * derivative, which will keep every loaded fragment in memory. If this
	 * becomes too memory intensive, it may be best to switch to a
	 * {@link android.support.v4.app.FragmentStatePagerAdapter}.
	 */
	SectionsPagerAdapter mSectionsPagerAdapter;
	
	int frameWidth = 100;
	int frameHeight = 100;
	
	//private Fragment listContainer, detailsContainer, currentFragment;
	
	
	FragmentManager manager;
	/**
	 * The {@link ViewPager} that will host the section contents.
	 */
	public ViewPager mViewPager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.d("activityCreated", "+ main activity created");
		
		setContentView(R.layout.activity_main);
		Log.d("activityContentViewSet", "+ main activity content view set");
		
		setUpMain();

	}

	private void setUpMain() {
		manager = getSupportFragmentManager();
		//listContainer = manager.findFragmentById(R.id.frameList);
		//detailsContainer = manager.findFragmentById(R.id.frameDetails);	
		
		// Set up the action bar.
		final ActionBar actionBar = getSupportActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		// Create the adapter that will return a fragment for each of the three
		// primary sections of the activity.
		mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
		Log.d("sectionsPagerAdapterCreated", "+ sectionsPagerAdapter created in Main activity (line 65)");

		// Set up the ViewPager with the sections adapter.
		mViewPager = (ViewPager) findViewById(R.id.pager);
		Log.d("viewPagerSet", "+ viewPager set in Main activity (line 69)");
		
		mViewPager.setAdapter(mSectionsPagerAdapter);
		Log.d("viewPagerAdapterSet", "+ viewPagerAdapterSet in Main activity (line 72)");

		// When swiping between different sections, select the corresponding
		// tab. We can also use ActionBar.Tab#select() to do this if we have
		// a reference to the Tab.
		
		mViewPager
			.setOnPageChangeListener(new MainPageChangeListener(actionBar, this));
			
		//For each of the sections in the app, add a tab to the action bar.
		for (int i = 0; i < mSectionsPagerAdapter.getCount(); i++) {
			// Create a tab with text corresponding to the page title defined by
			// the adapter. Also specify this Activity object, which implements
			// the TabListener interface, as the callback (listener) for when
			// this tab is selected.
			actionBar.addTab(actionBar.newTab()
							 .setText(mSectionsPagerAdapter.getPageTitle(i))
							 .setTabListener(this));
		}	
		
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void onToggleClicked(View v) {
		ToggleButton toggle = (ToggleButton) v;
		
		if (toggle.isChecked())
			toggle.setChecked(true);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		ActionBar actionBar = getSupportActionBar();
		
		if (id == R.id.action_settings) {
			return true;
		}
		
		if (id == R.id.tabs_display) {
			if (actionBar.getNavigationMode() == ActionBar.NAVIGATION_MODE_STANDARD) { 
				actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
				item.setTitle("Hide Tabs");
			}
			
			else {
				actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
				item.setTitle("Show Tabs");
			}
		}
		return super.onOptionsItemSelected(item);
	}
	
	@Override
	public void onBackPressed() {
		//swap();
		int id;
	
		Fragment fragment = manager.findFragmentById(R.id.pager);
		
		if (fragment instanceof JournalsOverviewContainer) {
			
			if (manager.findFragmentById(R.id.journalFrameList) instanceof JournalsListFragment) {
				manager.popBackStack("journalList", FragmentManager.POP_BACK_STACK_INCLUSIVE);
			}
			
			if(manager.findFragmentById(R.id.journalFrameList) instanceof JournalDetailsFragment)
				manager.popBackStack("journalDetails", FragmentManager.POP_BACK_STACK_INCLUSIVE);
		}
				
		/*
		if (listContainer instanceof JournalsListFragment)
			manager.popBackStack("journalList", FragmentManager.POP_BACK_STACK_INCLUSIVE);
		if (listContainer instanceof PlantsListFragment)
			manager.popBackStack("plantsList", FragmentManager.POP_BACK_STACK_INCLUSIVE);
		if (listContainer instanceof CultivationListFragment)
			manager.popBackStack("cultivationList", FragmentManager.POP_BACK_STACK_INCLUSIVE);
		if (listContainer instanceof LocationsListFragment)
			manager.popBackStack("locationsList", FragmentManager.POP_BACK_STACK_INCLUSIVE);
		if (listContainer instanceof NutrientsListFragment)
			manager.popBackStack("nutrientsList", FragmentManager.POP_BACK_STACK_INCLUSIVE);
		if (listContainer instanceof BlankFragment)
			finish();
		
		if (detailsContainer instanceof NutrientsFragment)
			manager.popBackStack("nutrientDetails", FragmentManager.POP_BACK_STACK_INCLUSIVE);
		if(detailsContainer instanceof JournalDetailsFragment)
			manager.popBackStack("journalDetails",  FragmentManager.POP_BACK_STACK_INCLUSIVE); 
		*/
	}

	@Override
	public void onTabSelected(ActionBar.Tab tab,
							  FragmentTransaction fragmentTransaction) {
		// When the given tab is selected, switch to the corresponding page in
		// the ViewPager.
		mViewPager.setCurrentItem(tab.getPosition());
	}

	@Override
	public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
		
	}

	@Override
	public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
		
	}


	// Custom View Pager to disable swipe as needed
	public class CustomViewPager extends ViewPager {
		private boolean enabled;
		
		public CustomViewPager(Context context, AttributeSet attrs) {
			super(context, attrs);
			this.enabled = true;
		}
		
		@Override
		public boolean onTouchEvent(MotionEvent event) {
			if (this.enabled) 
				return super.onTouchEvent(event);
			
			return false;
		}
		
		@Override
		public boolean onInterceptTouchEvent(MotionEvent event) {
			if (this.enabled) {
				return super.onInterceptTouchEvent(event)	;
			}
			
			return false;
		}
		
		public void setPaginEnabled(boolean enabled) {
			this.enabled = enabled;
		}
	}
	
	/*public class PageChangeListener extends ViewPager.OnPageChangeListener {
		private int current;
		
		@Override
		public void onPageSelected(int position) {
			current = p;
		}
		
		public final int getCurrentPage() {
			return current;
		}
	}
	*/
}
