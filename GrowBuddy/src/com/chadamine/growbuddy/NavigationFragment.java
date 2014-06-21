package com.chadamine.growbuddy;

import com.chadamine.growbuddy.analysis.AnalysisActivity;
import com.chadamine.growbuddy.cultivation.CultivationListActivity;
import com.chadamine.growbuddy.journal.JournalListActivity;
import com.chadamine.growbuddy.schedule.ScheduleActivity;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

public class NavigationFragment extends Fragment {
	Activity activity;
	
	public NavigationFragment() {
	}

	@TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_main, container,
				false);
		activity = getActivity();
		// Use window manager and systemService to get display
		WindowManager wm = (WindowManager) activity.getSystemService(activity.WINDOW_SERVICE);
		Display display = wm.getDefaultDisplay();
		//Display display = activity.getWindowManager().getDefaultDisplay();
		
		int width;
		int height;
		int currentApi = android.os.Build.VERSION.SDK_INT;
		
		// Get display dimensions based on API 
		if (currentApi <= android.os.Build.VERSION_CODES.HONEYCOMB) {
			width = display.getWidth();
			height = display.getHeight();
		} else {
			Point displaySize = new Point();
			display.getSize(displaySize);
			
			width = displaySize.x;
			height = displaySize.y;
		}
			
		int rotation = display.getRotation();
		final int undefined = Configuration.ORIENTATION_UNDEFINED;
		final int landscape = Configuration.ORIENTATION_LANDSCAPE;
		final int portrait = Configuration.ORIENTATION_PORTRAIT;
		final int square = Configuration.ORIENTATION_SQUARE;
		int navBoxWidth = 0;
		
		switch(rotation) {
			case landscape:
				navBoxWidth = (int) (.5 * width);
		}
		
		//RelativeLayout rlMain = (RelativeLayout) inflater.inflate(R.layout.fragment_main, null);
		//RelativeLayout rlNavList = (RelativeLayout) rlMain.findViewById(R.id.rlNavList);
		//RelativeLayout rlNews = (RelativeLayout) rlMain.findViewById(R.id.rlNews);
		//RelativeLayout.LayoutParams newsBoxParams = (RelativeLayout.LayoutParams) rlNavList.getLayoutParams();
		/*
		if (navBoxWidth != 0) {
			//android.view.ViewGroup.LayoutParams navBoxParams;
		
			
			int navBoxHeight = RelativeLayout.LayoutParams.MATCH_PARENT;
			//navBoxHeight = 200;
			navBoxWidth = 200;
			//newsBoxParams = new RelativeLayout.LayoutParams(navBoxWidth, navBoxHeight);
			newsBoxParams.width = navBoxWidth;
			newsBoxParams.height = navBoxHeight;
				
			try {
				rlNavList.getLayoutParams().width = 200;
				rlNavList.setLayoutParams(newsBoxParams);
				rlNews.setLayoutParams(newsBoxParams);
			
			} catch (NullPointerException e) {
				Toast.makeText(activity, "failed to set layoutParams", Toast.LENGTH_SHORT).show();
			}
		}
		
		Toast.makeText(activity, "navBoxParamWidth:" + newsBoxParams.width, Toast.LENGTH_SHORT).show();
		
	*/
		setUp(rootView);
		//return rootView;
		
		 
		return rootView;
	}
	
	@TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
	private void setUp(View r) {
		
		activity = getActivity();
		
		
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
