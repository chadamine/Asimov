package com.chadamine.growbuddy.cultivation.pestdisease;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chadamine.growbuddy.R;

public class PestDiseaseListFragment extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
		
		View root = inflater.inflate(R.layout.fragment_pest_disease_list, parent);
		return root;
	}
}
