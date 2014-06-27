package com.chadamine.growbuddy.cultivation;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.chadamine.growbuddy.R;
import com.chadamine.growbuddy.cultivation.nutrients.NutrientsListFragment;
import com.chadamine.growbuddy.cultivation.plants.PlantsListFragment;

public class CultivationListFragment extends ListFragment {
	
	FragmentManager manager;
	View root;
	
	@Override 
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		root = inflater.inflate(R.layout.fragment_cultivation_list, container, false);
		
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
				
		final View theView = r;
				
		ListView lvCultivation = getListView();
		lvCultivation.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
			
				//FrameLayout frameList = (FrameLayout) theView.findViewById(R.id.frameList);
				manager = getActivity().getSupportFragmentManager();	
				//BlankFragment blank = (BlankFragment) theView.findViewById(R.layout.fragment_blank);
				switch(position) {
				
				case 0:	// Plants
					manager
						.beginTransaction()
						.replace(R.id.frameList, new PlantsListFragment())
						.addToBackStack("plantsList")
						.commit();
					
					break;
				case 1: // Nutrients
					manager
						.beginTransaction()
						.replace(R.id.frameList, new NutrientsListFragment())
						.addToBackStack("nutrientsList")
						.commit();
					break;
				case 2:	// Recipes
					break;
				case 3:	// Irrigation
					break;
				case 4:	// Pest and Disease
					break;
				case 5:	// Environment
					break;
				case 6:	// Locations
					break;
				default:
					break;
				}
			}
		});
				
	}		
}
