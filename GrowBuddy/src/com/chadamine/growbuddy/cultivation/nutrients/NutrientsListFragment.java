package com.chadamine.growbuddy.cultivation.nutrients;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.chadamine.growbuddy.R;

public class NutrientsListFragment extends ListFragment {

	FragmentActivity activity;
	
	@Override 
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_nutrients_list, container, false);

		activity = getActivity();
		
		Button btnAdd = (Button) view.findViewById(R.id.buttonAddNutrient);
		Button btnDel = (Button) view.findViewById(R.id.buttonDeleteNutrient);
		
		final FragmentManager manager = activity.getSupportFragmentManager();
		
		btnAdd.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				manager
				.beginTransaction()
				.replace(R.id.frameDetails, new NutrientsFragment())
				.addToBackStack("nutrientDetails")
				.commit();
			}
		});
		
		btnDel.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
			}
		});
		return view;
	}

}		
