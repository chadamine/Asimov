package com.chadamine.growbuddy;
import android.support.v4.app.Fragment;
import android.view.*;
import android.os.*;
import android.widget.*;
import android.content.*;
import com.chadamine.growbuddy.analysis.*;
import com.chadamine.growbuddy.cultivation.*;
import com.chadamine.growbuddy.schedule.*;
import com.chadamine.growbuddy.journal.*;
import android.app.*;
import android.graphics.*;

public class MainFragment extends Fragment {

	public MainFragment() {
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

		Spinner spNavOptions = (Spinner) r.findViewById(R.id.spNewsFilter);
		ListView lvNews = (ListView) r.findViewById(R.id.lvNews);
		lvNews.setBackgroundColor(Color.WHITE);

		String[] newsSort = { "Date Due", "Name" };

		String[] navs = new String[] {"Journals",  "Schedule", "Cultivation", "Analysis" };

		//ArrayAdapter<CharSequence> spinnerAdapter = ArrayAdapter.createFromResource(getActivity(), R.array.navSort, android.R.layout.simple_spinner_item);	
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(activity, R.layout.row_nav, R.id.tvNavItemTitle, navs);

		lvNav.setAdapter(adapter);

		//spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		//spNavOptions.setAdapter(spinnerAdapter);


	}


}
