package com.chadamine.growbuddy.cultivation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.chadamine.growbuddy.R;
import com.chadamine.growbuddy.cultivation.environment.EnvironmentActivity;
import com.chadamine.growbuddy.cultivation.irrigation.IrrigationActivity;
import com.chadamine.growbuddy.cultivation.pestdisease.PestDiseaseActivity;
import com.chadamine.growbuddy.cultivation.plants.PlantsActivity;
import com.chadamine.growbuddy.cultivation.recipes.RecipesActivity;
import com.chadamine.growbuddy.management.locations.LocationsListActivity;

public class NutrientsListFragment extends ListFragment {

	View root;
	@Override 
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		root = inflater.inflate(R.layout.fragment_nutrients_list, container, false);

		return root;
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		
	}

}		
