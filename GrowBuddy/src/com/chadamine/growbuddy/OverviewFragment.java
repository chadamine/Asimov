package com.chadamine.growbuddy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import android.annotation.TargetApi;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.util.SparseIntArray;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

@TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
public class OverviewFragment extends Fragment {
	
	private static final String ARG_SECTION_NUMBER = "section_number";
	private FragmentManager fragmentManager;
	private FragmentActivity activity;
	private int width;
	private int height;
	int currentApi;
	
	public OverviewFragment() { 
		
	}
	
	public static OverviewFragment newInstance(int displayWidth, int displayHeight) {
		OverviewFragment fragment = new OverviewFragment();
		
		//Bundle args = new Bundle();
		//fragment.setArguments(args);
		
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		currentApi = android.os.Build.VERSION.SDK_INT;
				
		super.onCreateView(inflater, container, savedInstanceState);
		
		activity = getActivity();
		activity.getActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
		
		fragmentManager = activity.getSupportFragmentManager();
		
		// Use window manager and systemService to get display
		WindowManager wm = (WindowManager) activity.getSystemService(activity.WINDOW_SERVICE);
		Display display = wm.getDefaultDisplay();
		//Display display = activity.getWindowManager().getDefaultDisplay();
		
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
		
		int frameNavWidth = 100;
		int frameNavHeight = 100;
		int frameDetWidth = 100;
		int frameDetHeight = 100;
		
		int navBoxWidth = 0;
		
		FrameLayout frameNav = new FrameLayout(activity);
		FrameLayout frameDetails = new FrameLayout(activity);
		
		//RelativeLayout rlMain = (RelativeLayout) inflater.inflate(R.layout.fragment_main, null);
		//RelativeLayout rlNavList = (RelativeLayout) rlMain.findViewById(R.id.rlNavList);
		//RelativeLayout rlNews = (RelativeLayout) rlMain.findViewById(R.id.rlNews);
		
		frameNav.setId(100);
		frameDetails.setId(101);
		
		List<Integer> rules = new ArrayList<Integer>();
		
		SparseIntArray navIntRules = new SparseIntArray();
		SparseIntArray detailsIntRules = new SparseIntArray();
		
		SparseIntArray navBoxRules = new SparseIntArray();
		SparseIntArray newsBoxRules = new SparseIntArray();
		
		rules.add(RelativeLayout.ALIGN_PARENT_TOP);
		rules.add(RelativeLayout.ALIGN_PARENT_LEFT);
		
		switch(rotation) {
			case landscape:
				
				frameNavWidth = (int) (.25 * width);
				frameNavHeight = FrameLayout.LayoutParams.MATCH_PARENT;
				frameDetWidth = width - frameNavWidth;
				frameDetHeight = frameNavHeight;
			
				detailsIntRules.put(RelativeLayout.RIGHT_OF, frameNav.getId());
				
				break;
				
			case portrait:
				
				frameNavWidth = FrameLayout.LayoutParams.MATCH_PARENT;
				frameNavHeight = (int) (.25 * height);
				frameDetWidth = frameNavWidth;
   				frameDetHeight = height - frameNavHeight;
				
				navBoxWidth = (int) (.5 * width);
				
   				Toast.makeText(activity, "portrait: navBox current width:" + navBoxWidth, Toast.LENGTH_SHORT).show();
				detailsIntRules.put(RelativeLayout.BELOW, frameNav.getId());
				
				break;
				
			case undefined:
				// square
				if(width == height) {
   					frameNavWidth = FrameLayout.LayoutParams.MATCH_PARENT;
   					frameNavHeight = (int) (.25 * height);
   					frameDetWidth = frameNavWidth;
   					frameDetHeight = height - frameNavHeight;
   
   					Toast.makeText(activity, "undefined square: navBox current width:" + navBoxWidth, Toast.LENGTH_SHORT).show();
   					navBoxWidth = (int) (.5 * width);
   					detailsIntRules.put(RelativeLayout.BELOW, frameNav.getId());
				}
				
				// portrait
				if(height > width) {
   					frameNavWidth = FrameLayout.LayoutParams.MATCH_PARENT;
   					frameNavHeight = (int) (.25 * height);
   					frameDetWidth = frameNavWidth;
   					frameDetHeight = height - frameNavHeight;
					
   					navBoxWidth = (int) (.5 * width);
					
   					Toast.makeText(activity, "undefined portrait: navBox current width:" + navBoxWidth, Toast.LENGTH_SHORT).show();
  				 	detailsIntRules.put(RelativeLayout.BELOW, frameNav.getId());
				}
				
				// landscape
				else {
   					frameNavWidth = (int) (.25 * width);
   					frameNavHeight = FrameLayout.LayoutParams.MATCH_PARENT;
   					frameDetWidth = width - frameNavWidth;
   					frameDetHeight = frameNavHeight;
					
   					detailsIntRules.put(RelativeLayout.RIGHT_OF, frameNav.getId());
				}
				break;
				
			case square:
				frameNavWidth = FrameLayout.LayoutParams.MATCH_PARENT;
				frameNavHeight = (int) (.25 * height);
				frameDetWidth = frameNavWidth;
   				frameDetHeight = height - frameNavHeight;
				
				navBoxWidth = (int) (.5 * width);
				//newsBoxRules.put(RelativeLayout.BELOW, rlNavList.getId());
				
				detailsIntRules.put(RelativeLayout.BELOW, frameNav.getId());
				break;
		}
		
		
		RelativeLayout.LayoutParams frameNavLayout = new RelativeLayout.LayoutParams(
			frameNavWidth, frameNavHeight);
			
		RelativeLayout.LayoutParams frameDetailsLayout = new RelativeLayout.LayoutParams(
			frameDetWidth, frameDetHeight);
			
		//RelativeLayout.LayoutParams newsBoxParams = (RelativeLayout.LayoutParams) rlNavList.getLayoutParams();
		
		if (navBoxWidth != 0) {
			//android.view.ViewGroup.LayoutParams navBoxParams;
		
			
			int navBoxHeight = RelativeLayout.LayoutParams.MATCH_PARENT;
			//navBoxHeight = 200;
			navBoxWidth = 200;
			//newsBoxParams = new RelativeLayout.LayoutParams(navBoxWidth, navBoxHeight);
			//newsBoxParams.width = navBoxWidth;
			//newsBoxParams.height = navBoxHeight;
				
			try {
				//rlNavList.getLayoutParams().width = 200;
				//rlNavList.setLayoutParams(newsBoxParams);
				//rlNews.setLayoutParams(newsBoxParams);
			
			} catch (NullPointerException e) {
				Toast.makeText(activity, "failed to set layoutParams", Toast.LENGTH_SHORT).show();
			}
		}
		
		//Toast.makeText(activity, "navBoxParamWidth:" + newsBoxParams.width, Toast.LENGTH_SHORT).show();
		
		for(int rule: rules) {
			frameNavLayout.addRule(rule);
		}
		
		frameNavLayout.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		frameNavLayout.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		
		if(navIntRules.size() > 0) {
			for(int i = 0; i < navIntRules.size(); i++) {
				frameNavLayout.addRule(navIntRules.keyAt(i), navIntRules.valueAt(i));
			}
		}
		
		if(detailsIntRules.size() > 0) {
			for(int i = 0; i < detailsIntRules.size(); i++) {
				frameDetailsLayout.addRule(detailsIntRules.keyAt(i), detailsIntRules.valueAt(i));
			}
		}
		
		
		
		RelativeLayout rlOverView = new RelativeLayout(activity);
		
		rlOverView.setLayoutParams(new RelativeLayout.LayoutParams(
			RelativeLayout.LayoutParams.MATCH_PARENT, 
			RelativeLayout.LayoutParams.MATCH_PARENT));

		frameDetails.setBackgroundColor(Color.BLACK);
		
		rlOverView.addView(frameNav, frameNavLayout);
		rlOverView.addView(frameDetails, frameDetailsLayout);
			
		fragmentManager
			.beginTransaction()
			.add(frameNav.getId(), new MainActivity.PlaceholderFragment())
			.commit();
		fragmentManager
			.beginTransaction()
			.add(frameDetails.getId(), new BlankFragment());
			
		return rlOverView;
	}
}
