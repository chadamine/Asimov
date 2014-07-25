package com.chadamine.growbuddy.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager.SimpleOnPageChangeListener;
import android.support.v7.app.ActionBar;

import com.chadamine.growbuddy.R;

public class MainPageChangeListener extends SimpleOnPageChangeListener {
	private int current;
	
	private FragmentManager manager;
	private ActionBar actionBar;
	private FragmentActivity activity;
	
	public MainPageChangeListener(ActionBar b, FragmentActivity a) {
		actionBar = b;
		activity = a;
		
	}
	
	@Override
	public void onPageSelected(int position) {
		
		FragmentManager manager = activity.getSupportFragmentManager();
		Fragment list = manager.findFragmentById(R.id.frameList);
		Fragment details = manager.findFragmentById(R.id.frameDetails);
		
		current = position;
		
		if(actionBar.getNavigationMode() == ActionBar.NAVIGATION_MODE_TABS)
			actionBar.setSelectedNavigationItem(position);
		
		/*
		if(actionBar.getNavigationMode() == ActionBar.NAVIGATION_MODE_TABS)
			actionBar.setSelectedNavigationItem(position);
		else
			if (list instanceof JournalsListFragment)
				actionBar.setSelectedNavigationItem(0);
			if (list instanceof SchedulesFragment) 
				actionBar.setSelectedNavigationItem(1);
			if (list instanceof JournalsListFragment)
				actionBar.setSelectedNavigationItem(2);
			if (list instanceof AnalysisOverviewFragment)
				actionBar.setSelectedNavigationItem(3);
		*/
	}
	
	public final int getCurrentPage() {
		return current;
	}
}
