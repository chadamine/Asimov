package com.chadamine.growbuddy;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import android.view.*;
import android.content.res.*;
import java.util.*;
import android.graphics.*;
import android.support.v7.app.*;
import javax.crypto.*;

public class OverviewFragment extends Fragment {
	
	private static final String ARG_SECTION_NUMBER = "section_number";
	private FragmentManager fragmentManager;
	private FragmentActivity activity;
	private int width;
	private int height;
	
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
				
		super.onCreateView(inflater, container, savedInstanceState);
		
		activity = getActivity();
		activity.getActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
		
		fragmentManager = activity.getSupportFragmentManager();
		
		Display display = activity.getWindowManager().getDefaultDisplay();
		width = display.getWidth();
		height = display.getHeight();
		
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
		//int navBoxHeight = 0;
		//int newsBoxHeight = 0;
		//int newsBoxWidth = 0;
		
		FrameLayout frameNav = new FrameLayout(activity);
		FrameLayout frameDetails = new FrameLayout(activity);
		
		//RelativeLayout rlMain = (RelativeLayout) activity.findViewById(R.layout.fragment_main);
		
		//RelativeLayout rlNavList = (RelativeLayout) activity.findViewById(R.id.rlNavList);
		//RelativeLayout rlNews = (RelativeLayout) activity.findViewById(R.id.rlNews);
		
		frameNav.setId(100);
		frameDetails.setId(101);
		
		List<Integer> rules = new ArrayList<Integer>();
		Map<Integer, Integer> navIntRules = new HashMap<Integer, Integer>();
		Map<Integer, Integer> detailsIntRules = new HashMap<Integer, Integer>();
		
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
				
				navBoxWidth = (int) (.5 * frameNavWidth);
				
				detailsIntRules.put(RelativeLayout.BELOW, frameNav.getId());
				
				break;
				
			case undefined:
				// square
				if(width == height) {
   					frameNavWidth = FrameLayout.LayoutParams.MATCH_PARENT;
   					frameNavHeight = (int) (.25 * height);
   					frameDetWidth = frameNavWidth;
   					frameDetHeight = height - frameNavHeight;
   
   					navBoxWidth = (int) (.5 * frameNavWidth);
   					detailsIntRules.put(RelativeLayout.BELOW, frameNav.getId());
				}
				
				// portrait
				if(height > width) {
   					frameNavWidth = FrameLayout.LayoutParams.MATCH_PARENT;
   					frameNavHeight = (int) (.25 * height);
   					frameDetWidth = frameNavWidth;
   					frameDetHeight = height - frameNavHeight;
					
   					navBoxWidth = (int) (.5 * frameNavWidth);
					
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
				
				navBoxWidth = (int) (.5 * frameNavWidth);
				
				detailsIntRules.put(RelativeLayout.BELOW, frameNav.getId());
				break;
		}
		
		RelativeLayout.LayoutParams frameNavLayout = new RelativeLayout.LayoutParams(
			frameNavWidth, frameNavHeight);
			
		RelativeLayout.LayoutParams frameDetailsLayout = new RelativeLayout.LayoutParams(
			frameDetWidth, frameDetHeight);
			
		RelativeLayout.LayoutParams newsBoxParams;
		
		if (navBoxWidth != 0) {
			android.view.ViewGroup.LayoutParams navBoxParams;
			
			int navBoxHeight = RelativeLayout.LayoutParams.MATCH_PARENT;
			navBoxHeight = 200;
			navBoxWidth = 200;
			newsBoxParams = new RelativeLayout.LayoutParams(
				navBoxHeight, navBoxHeight);
			
			//rlNavList.setLayoutParams(newsBoxParams);
			//rlNews.setLayoutParams(newsBoxParams);
		}
			
		for(int rule: rules) {
			frameNavLayout.addRule(rule);
		}
		
		frameNavLayout.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		frameNavLayout.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		
		Iterator<Map.Entry<Integer, Integer>> itr;

		if( !navIntRules.isEmpty()) {
			
			itr = navIntRules.entrySet().iterator();
		 
			while(itr.hasNext()) {
				Map.Entry<Integer, Integer> pairs = (Map.Entry<Integer, Integer>)itr.next();
				frameNavLayout.addRule(pairs.getKey(), pairs.getValue());
			}
		}
		
		if( !detailsIntRules.isEmpty()) {
			
			itr = detailsIntRules.entrySet().iterator();
		 
			while(itr.hasNext()) {
				Map.Entry<Integer, Integer> pairs = (Map.Entry<Integer, Integer>)itr.next();
				frameDetailsLayout.addRule(pairs.getKey(), pairs.getValue());
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
