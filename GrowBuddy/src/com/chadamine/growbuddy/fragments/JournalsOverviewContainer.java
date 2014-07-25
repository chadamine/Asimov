package com.chadamine.growbuddy.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.chadamine.growbuddy.R;

public class JournalsOverviewContainer extends Fragment {
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container , Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		Log.d("onCreateViewCalled", "+ onCreateView called in journalsFragment (line 15)");
		
		String currentFragment = "";
		FragmentManager manager = getActivity().getSupportFragmentManager();
		
		View view = inflater.inflate(R.layout.fragment_journals_overview, container, false);
	
		manager
			.beginTransaction()
			.replace(R.id.journalFrameList, new JournalsListFragment())
			.addToBackStack("journalsFragment")
			.commit();
		
		if(savedInstanceState != null && savedInstanceState.getString("currentFragment") != null)
			if(savedInstanceState.getString("currentFragment") == "details")
				manager.beginTransaction()
				.replace(R.id.journalFrameList, new JournalDetailsFragment())
				.addToBackStack("journalDetails")
				.commit();
				
			
		return view;
	}
	
	@Override
	public void onPause() {
		super.onPause();
	}
	
	@Override 
	public void onSaveInstanceState(Bundle savedInstanceState) {
		if(getActivity().getSupportFragmentManager().findFragmentById(R.id.journalFrameList) instanceof JournalDetailsFragment)
			savedInstanceState.putString("currentFragment", "details");
		Toast.makeText(getActivity(), "bundle saved in journals overview activity", Toast.LENGTH_SHORT).show();
	}
}
