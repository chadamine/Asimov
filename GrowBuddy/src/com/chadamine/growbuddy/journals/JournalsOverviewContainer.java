package com.chadamine.growbuddy.journals;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chadamine.growbuddy.R;

public class JournalsOverviewContainer extends Fragment {
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container , Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		Log.d("onCreateViewCalled", "+ onCreateView called in journalsFragment (line 15)");
		
		View view = inflater.inflate(R.layout.fragment_journals_overview, container, false);
	
		getActivity()
			.getSupportFragmentManager()
				.beginTransaction()
				.replace(R.id.journalFrameList, new JournalsListFragment())
				.addToBackStack("journalsFragment")
				.commit();
		
		return view;
	}
}
