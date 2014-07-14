package com.chadamine.growbuddy;

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.chadamine.growbuddy.cultivation.CultivationListFragment;
import com.chadamine.growbuddy.cultivation.plants.PlantsListFragment;
import com.chadamine.growbuddy.journals.JournalsListFragment;

public class NavigationFragment extends Fragment {
	
	public NavigationFragment() {
	}

	@TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_main, container,
				false);
		
		setUp(rootView);
		 
		return rootView;
	}
	
	@TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
	private void setUp(View r) {
		
		//activity = getActivity();
		final FragmentManager manager = getActivity().getSupportFragmentManager();
		
		ListView lvNav;
		
		try {
			lvNav = (ListView) r.findViewById(R.id.lvNavigation);
			
			lvNav.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				
				//Intent intent;
				
				switch(position) {
				case 0:	// Journals
					manager
					.beginTransaction()
					.replace(R.id.frameList, new JournalsListFragment())
					.addToBackStack("journalList")
					.commit();
					break;
				case 1:	// Schedule
					manager
					.beginTransaction()
					.replace(R.id.frameList, new PlantsListFragment())
					.addToBackStack("plantsList")
					.commit();
					break;
				case 2: // Cultivation
					manager
					.beginTransaction()
					.replace(R.id.frameList, new CultivationListFragment())
					.addToBackStack("cultivationList")
					.commit();
					break;
				case 3:
					
					
					break;
				default:
					
					break;
				}
			}
		});
		
		String[] navs = new String[] {"Journals",  "Schedule", "Cultivation", "Analysis" };
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), R.layout.row_nav, R.id.tvNavItemTitle, navs);
		
		lvNav.setAdapter(adapter);
		
		} catch (NullPointerException e) {
			Toast.makeText(getActivity(), "no list view found", Toast.LENGTH_SHORT).show();
		}
		
		Spinner spNavOptions = (Spinner) r.findViewById(R.id.spNewsFilter);
		ListView lvNews = (ListView) r.findViewById(R.id.lvNews);
		lvNews.setBackgroundColor(Color.WHITE);
		
		String[] newsSort = { "Date Due", "Name" };

		
		//ArrayAdapter<CharSequence> spinnerAdapter = ArrayAdapter.createFromResource(getActivity(), R.array.navSort, android.R.layout.simple_spinner_item);	
	
		
		//spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		//spNavOptions.setAdapter(spinnerAdapter);
		
	}
}
