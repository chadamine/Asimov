package com.chadamine.growbuddy.cultivation.nutrients;

import com.chadamine.growbuddy.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class NutrientsFragment extends Fragment {
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_nutrients_list, container, false);

		return view;
	}
}
