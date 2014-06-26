package com.chadamine.growbuddy.cultivation;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.widget.CursorAdapter;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.chadamine.growbuddy.R;
import com.chadamine.growbuddy.database.DatabaseContract;
import com.chadamine.growbuddy.database.DatabaseContract.Journals;
import android.view.*;
import android.widget.*;
import android.widget.RadioGroup.*;
import android.support.v4.app.*;
import android.app.*;
import com.chadamine.growbuddy.cultivation.plants.*;

public class CultivationListFragment extends ListFragment {
	
	android.support.v4.app.FragmentManager manager;
	Activity activity;
	View root;
	
	
	
	@Override 
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		root = inflater.inflate(R.layout.fragment_cultivation_nav_list, container, false);
		activity = getActivity();
		
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
		final View theView = r;
				
		ListView lvCultivation = getListView();
		lvCultivation.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
			
				//FrameLayout frameList = (FrameLayout) theView.findViewById(R.id.frameList);
				manager = activity.getSupportFragmentManager();	
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
