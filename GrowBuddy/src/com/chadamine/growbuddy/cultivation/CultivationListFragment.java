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

public class CultivationListFragment extends ListFragment {
	
	View root;
	@Override 
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		root = inflater.inflate(R.layout.fragment_cultivation_nav_list, container, false);
		
		String[] values = new String[] { "Plants", "Nutrients", "Recipes", "Irrigation", "Pest & Disease", "Environment", "Locations" };
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), R.layout.row_nav, R.id.tvNavItemTitle, values);
	
		setListAdapter(adapter);
	
		return root;
	}
	
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		populateList(view);
	}
			
	private void populateList(View r) {
				
		final Activity activity = getActivity();
				
		ListView lvCultivation = getListView();
		lvCultivation.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
						
			Intent intent;
						
			switch(position) {
				case 0:
					intent = new Intent(activity, PlantsActivity.class);
					startActivity(intent);
					break;
				case 1: 
					//	mgmt
					intent = new Intent(activity, NutrientsListActivity.class);
					startActivity(intent);
					break;
				case 2:
					intent = new Intent(activity, RecipesActivity.class);
					startActivity(intent);
					break;
				case 3:
					intent = new Intent(activity, IrrigationActivity.class);
					startActivity(intent);
					break;
				case 4:
					intent = new Intent(activity, PestDiseaseActivity.class);
					startActivity(intent);
					break;
				case 5:
					intent = new Intent(activity, EnvironmentActivity.class);
					startActivity(intent);
					break;
				case 6:
					//	mgmt
					intent = new Intent(activity, LocationsListActivity.class);
					startActivity(intent);
					break;
				default:
					intent = new Intent(activity, CultivationListActivity.class);
					startActivity(intent);
					break;
				}
			}
		});
				
	}		
}
