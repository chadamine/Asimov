package com.chadamine.growbuddy.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chadamine.growbuddy.R;

public class AnalysisOverviewFragment extends Fragment {
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		Log.d("onCreateViewCalled", "+ onCreateView called in analysisFragment (line 15)");
		
		View view = inflater.inflate(R.layout.fragment_analysis_overview, container, false);
		
		getActivity().getSupportFragmentManager()
		.beginTransaction()
		.replace(R.id.analysisFrameList, new BlankFragment())
		.addToBackStack("analysisFragment")
		.commit();
		
		
		return view;
	}
}
