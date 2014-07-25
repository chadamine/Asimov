package com.chadamine.growbuddy.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chadamine.growbuddy.R;

public class EnvironmentListFragment extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
		View root = inflater.inflate(R.layout.fragment_environment_list, parent);
		return root;
		
		
	}
	
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		populateList(view);
	}
	
	private void populateList(View v) {
		
	}
}
