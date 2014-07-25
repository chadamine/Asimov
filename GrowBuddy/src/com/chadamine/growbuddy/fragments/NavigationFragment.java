package com.chadamine.growbuddy.fragments;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.chadamine.growbuddy.R;
import com.chadamine.growbuddy.R.id;
import com.chadamine.growbuddy.R.layout;

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
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), R.layout.row_nav, R.id.textNavItemTitle, navs);
		
		lvNav.setAdapter(adapter);
		
		} catch (NullPointerException e) {
			Toast.makeText(getActivity(), "no list view found", Toast.LENGTH_SHORT).show();
			
			final ToggleButton btnJournals = (ToggleButton) r.findViewById(R.id.buttonJournals);
			final ToggleButton btnSchedules = (ToggleButton) r.findViewById(R.id.buttonSchedule);
			final ToggleButton btnCultivation = (ToggleButton) r.findViewById(R.id.buttonCultivation);
			final ToggleButton btnAnalysis = (ToggleButton) r.findViewById(R.id.buttonAnalysis);
			final Fragment container = manager.findFragmentById(R.id.frameList);
			
			btnJournals.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					btnJournals.setEnabled(false);
					
					btnSchedules.setChecked(false);
					btnSchedules.setEnabled(true);
					
					btnAnalysis.setChecked(false);
					btnAnalysis.setEnabled(true);
					
					btnCultivation.setChecked(false);
					btnCultivation.setEnabled(true);
					
					if(container instanceof JournalsListFragment == false) 
						manager
							.beginTransaction()
							.replace(R.id.frameList, new JournalsListFragment())
							.addToBackStack("journalList")
							.commit();
				}
			});
			btnSchedules.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					btnSchedules.setEnabled(false);
					
					btnJournals.setChecked(false);
					btnJournals.setEnabled(true);
					
					btnAnalysis.setChecked(false);
					btnAnalysis.setEnabled(true);
					
					btnCultivation.setChecked(false);
					btnCultivation.setEnabled(true);
					
					manager
					.beginTransaction()
					.replace(R.id.frameList, new PlantsListFragment())
					.addToBackStack("plantsList")
					.commit();	
				}
			});
			
			btnCultivation.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					
					((ToggleButton) v).setEnabled(false);
					
					btnJournals.setChecked(false);
					btnJournals.setEnabled(true);
					
					btnAnalysis.setChecked(false);
					btnAnalysis.setEnabled(true);
					
					btnSchedules.setChecked(false);
					btnSchedules.setEnabled(true);
					
					manager
					.beginTransaction()
					.replace(R.id.frameList, new CultivationListFragment())
					.addToBackStack("cultivationList")
					.commit();	
				}
			});
			
			btnAnalysis.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					btnJournals.setChecked(false);
					btnSchedules.setChecked(false);
					btnCultivation.setChecked(false);
					
					
				}
			});
		}
		
		Spinner spNavOptions = (Spinner) r.findViewById(R.id.spNewsFilter);
		//ListView lvNews = (ListView) r.findViewById(R.id.lvNews);
		//lvNews.setBackgroundColor(Color.WHITE);
		
		String[] newsSort = { "Date Due", "Name" };

		
		//ArrayAdapter<CharSequence> spinnerAdapter = ArrayAdapter.createFromResource(getActivity(), R.array.navSort, android.R.layout.simple_spinner_item);	
	
		
		//spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		//spNavOptions.setAdapter(spinnerAdapter);
		
	}
}
