package com.chadamine.growbuddy.fragments;

import android.annotation.TargetApi;
import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Toast;

import com.chadamine.growbuddy.R;
import com.chadamine.growbuddy.R.id;
import com.chadamine.growbuddy.R.layout;

@TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
public class OverviewFragment extends Fragment {
	
	//private static final String ARG_SECTION_NUMBER = "section_number";
	//private static FragmentManager fragmentManager;
	//private static FragmentActivity activity;
	int currentApi;
	private int position;
	
	/*
	public OverviewFragment() { 
		activity = getActivity();
		//onResume();
		Log.d("fragmentCreated", "+ OverviewFragment created (constructor)");
	}*/
	
	public static OverviewFragment newInstance(int displayWidth, int displayHeight) {
		OverviewFragment frag = new OverviewFragment();
		
		//Bundle args = new Bundle();
		//fragment.setArguments(args);
		
		return frag;
	}
	
	public static OverviewFragment newInstance(int p) {
		//position = p;
		
		OverviewFragment frag = new OverviewFragment();
		
		return frag;
	}
	
	@Override
	public void onResume() {
		super.onResume();
		Log.d("onResumeCalled", "+ onResume called in OverviewFragment (line 56)");
		
		//position = Main.mViewPager.getCurrentItem();
		Log.d("positionSet", "+ position set from Main.mViewPager.getCurrentItem() in onResume() in OverviewFragment (line 60)");
		/*
		switch(position) {
		case 0:	// Journals
			
			fragmentManager
				.beginTransaction()
				.replace(R.id.frameList, new JournalsListFragment())
				.addToBackStack("journalsFragment")
				.commit();
			
			//swapListFragment(new JournalsListFragment(), "journalsFragment");
			break;
			
		case 1:	// Schedule
			fragmentManager
				.beginTransaction()
				.replace(R.id.frameList, new SchedulesFragment())
				.addToBackStack("schedulesFragment")
				.commit();
			
			//swapListFragment(new SchedulesFragment(), "schedulesFragment");
			break;
			
		case 2:	// Cultivation
			fragmentManager
				.beginTransaction()
				.replace(R.id.frameList, new CultivationListFragment())
				.addToBackStack("cultivationListFragment")
				.commit();
			
			//swapListFragment(new CultivationListFragment(), "cultivationListFragment");
			break;
			
		case 3:	// Analysis
			fragmentManager
				.beginTransaction()
				.replace(R.id.frameList, new AnalysisOverviewFragment())
				.addToBackStack("analysisFragment")
				.commit();
			
			//swapListFragment(new AnalysisOverviewFragment(), "anlaysisFragment");
			break;
		}
		*/
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		super.onCreateView(inflater, container, savedInstanceState);
		Log.d("onCreateViewCalled", "+ onCreateView called in OverviewFragment (line 110)");
		
		currentApi = android.os.Build.VERSION.SDK_INT;
		
		FragmentManager manager = getActivity().getSupportFragmentManager();
		
		//fragmentManager = activity.getSupportFragmentManager();
		
		Toast.makeText(getActivity(), "position is: " + position, Toast.LENGTH_LONG).show();
		
		switch(position) {
		case 0:	// Journals
			
			manager
				.beginTransaction()
				.replace(R.id.frameList, new JournalsListFragment())
				.addToBackStack("journalsFragment")
				.commit();
			
			//swapListFragment(new JournalsListFragment(), "journalsFragment");
			break;
			
		case 1:	// Schedule
			manager
				.beginTransaction()
				.replace(R.id.frameList, new SchedulesFragment())
				.addToBackStack("schedulesFragment")
				.commit();
			
			//swapListFragment(new SchedulesFragment(), "schedulesFragment");
			break;
			
		case 2:	// Cultivation
			manager
				.beginTransaction()
				.replace(R.id.frameList, new CultivationListFragment())
				.addToBackStack("cultivationListFragment")
				.commit();
			
			//swapListFragment(new CultivationListFragment(), "cultivationListFragment");
			break;
			
		case 3:	// Analysis
			manager
				.beginTransaction()
				.replace(R.id.frameList, new AnalysisOverviewFragment())
				.addToBackStack("analysisFragment")
				.commit();
			
			//swapListFragment(new AnalysisOverviewFragment(), "anlaysisFragment");
			break;
		}
		
		// Use window manager and systemService to get display
		WindowManager wm = (WindowManager) getActivity().getSystemService(FragmentActivity.WINDOW_SERVICE);
		Display display = wm.getDefaultDisplay();
	
			Point displaySize = new Point();
			display.getSize(displaySize);
			
			int width = displaySize.x;
			int height = displaySize.y;
			
	if (width > 1500 || height > 1500) {
		
	}

		View r = inflater.inflate(R.layout.fragment_overview, null);
		
		manager
			.beginTransaction()
			.replace(R.id.frameNav, new NavigationFragment())
			.addToBackStack("navigationFragment")
			.commit();
		
		return r;
	}
	
	private void swapListFragment(Fragment f, String s) {
		
		FragmentManager fMan = getActivity().getSupportFragmentManager();
		
		fMan.beginTransaction()
			.replace(R.id.frameList, f)
			.addToBackStack(s)
			.commit();
	}
	
	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		
	}
}
