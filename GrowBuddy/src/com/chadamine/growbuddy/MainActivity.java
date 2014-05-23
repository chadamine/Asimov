package com.chadamine.growbuddy;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import com.chadamine.growbuddy.analysis.AnalysisActivity;
import com.chadamine.growbuddy.cultivation.CultivationListActivity;
import com.chadamine.growbuddy.journal.*;
import com.chadamine.growbuddy.schedule.ScheduleActivity;

public class MainActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			
			setUp(rootView);
		
			return rootView;
		}
		
		private void setUp(View r) {
			
			final Activity activity = getActivity();
			
			Spinner spNavOptions = (Spinner) r.findViewById(R.id.spNewsFilter);
			ListView lvNav = (ListView) r.findViewById(R.id.lvNavigation);
			lvNav.setOnItemClickListener(new AdapterView.OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					
					Intent intent;
					
					switch(position) {
					case 0:
						intent = new Intent(activity, JournalListActivity.class);
						startActivity(intent);
						break;
					case 1:
						intent = new Intent(activity, ScheduleActivity.class);
						startActivity(intent);
						break;
					case 2: 
						intent = new Intent(activity, CultivationListActivity.class);
						startActivity(intent);
						break;
					case 3:
						intent = new Intent(activity, AnalysisActivity.class);
						startActivity(intent);
						break;
					default:
						intent = new Intent(activity, MainActivity.class);
						startActivity(intent);
						break;
					}
					
					//startActivity(intent);
				}
				
			});
			
			
			ListView lvNews = (ListView) r.findViewById(R.id.lvNews);
			lvNews.setBackgroundColor(Color.WHITE);
			
			String[] navs = new String[] {"Journals",  "Schedule", "Cultivation", "Analysis" };
			
			ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(activity, android.R.layout.simple_spinner_item);
			ArrayAdapter<String> adapter = new ArrayAdapter<String>(activity, R.layout.nav_row, R.id.tvNavItemTitle, navs);
			
			lvNav.setAdapter(adapter);
			adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			spNavOptions.setAdapter(spinnerAdapter);
		}
		
		
	}

}
