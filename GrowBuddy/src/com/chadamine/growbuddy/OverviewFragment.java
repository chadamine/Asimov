package com.chadamine.growbuddy;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class OverviewFragment extends Fragment {
	
	private static final String ARG_SECTION_NUMBER = "section_number";
	private FragmentManager fragmentManager;
	private FragmentActivity activity;
		
	public OverviewFragment() { 
		
	}
	
	public static OverviewFragment newInstance(int sectionNumber) {
		
	
		OverviewFragment fragment = new OverviewFragment();
		Bundle args = new Bundle();
		
		args.putInt(ARG_SECTION_NUMBER, sectionNumber);
		fragment.setArguments(args);
		
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		activity = getActivity();
		fragmentManager = activity.getSupportFragmentManager();
		View view = inflater.inflate(R.layout.fragment_overview,
				container, false);
		
		/*fragmentManager
			.beginTransaction()
			.add(R.id.framePaneNavigation, new MainActivity.PlaceholderFragment())
			.commit();
		fragmentManager
			.beginTransaction()
			.add(R.id.framePaneDetails, new BlankFragment());
			*/
		return view;
	}
}
