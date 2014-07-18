package com.chadamine.growbuddy;

import android.annotation.TargetApi;
import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Toast;

import com.chadamine.growbuddy.analysis.AnalysisOverviewFragment;
import com.chadamine.growbuddy.cultivation.CultivationListFragment;
import com.chadamine.growbuddy.journals.JournalsListFragment;
import com.chadamine.growbuddy.schedule.SchedulesFragment;

@TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
public class OverviewFragment extends Fragment {
	
	private static final String ARG_SECTION_NUMBER = "section_number";
	private static FragmentManager fragmentManager;
	private static FragmentActivity activity;
	int currentApi;
	private static int position;
	
	/*
	public OverviewFragment() { 
		activity = getActivity();
		//onResume();
		Log.d("fragmentCreated", "+ OverviewFragment created (constructor)");
	}*/
	
	public static OverviewFragment newInstance(int displayWidth, int displayHeight) {
		OverviewFragment frag = new OverviewFragment();
		
		//Bundle args = new Bundle();
		//fragment.setArguments(args);
		
		return frag;
	}
	
	public static OverviewFragment newInstance(int p) {
		//position = p;
		
		OverviewFragment frag = new OverviewFragment();
		
		return frag;
	}
	
	@Override
	public void onResume() {
		super.onResume();
		Log.d("onResumeCalled", "+ onResume called in OverviewFragment (line 56)");
		
		//position = Main.mViewPager.getCurrentItem();
		Log.d("positionSet", "+ position set from Main.mViewPager.getCurrentItem() in onResume() in OverviewFragment (line 60)");
		/*
		switch(position) {
		case 0:	// Journals
			
			fragmentManager
				.beginTransaction()
				.replace(R.id.frameList, new JournalsListFragment())
				.addToBackStack("journalsFragment")
				.commit();
			
			//swapListFragment(new JournalsListFragment(), "journalsFragment");
			break;
			
		case 1:	// Schedule
			fragmentManager
				.beginTransaction()
				.replace(R.id.frameList, new SchedulesFragment())
				.addToBackStack("schedulesFragment")
				.commit();
			
			//swapListFragment(new SchedulesFragment(), "schedulesFragment");
			break;
			
		case 2:	// Cultivation
			fragmentManager
				.beginTransaction()
				.replace(R.id.frameList, new CultivationListFragment())
				.addToBackStack("cultivationListFragment")
				.commit();
			
			//swapListFragment(new CultivationListFragment(), "cultivationListFragment");
			break;
			
		case 3:	// Analysis
			fragmentManager
				.beginTransaction()
				.replace(R.id.frameList, new AnalysisOverviewFragment())
				.addToBackStack("analysisFragment")
				.commit();
			
			//swapListFragment(new AnalysisOverviewFragment(), "anlaysisFragment");
			break;
		}
		*/
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		super.onCreateView(inflater, container, savedInstanceState);
		Log.d("onCreateViewCalled", "+ onCreateView called in OverviewFragment (line 110)");
		
		currentApi = android.os.Build.VERSION.SDK_INT;
		
		activity = getActivity();
	
		fragmentManager = activity.getSupportFragmentManager();
		
		//fragmentManager = activity.getSupportFragmentManager();
		
		Toast.makeText(activity, "position is: " + position, Toast.LENGTH_LONG).show();
		
		switch(position) {
		case 0:	// Journals
			
			fragmentManager
				.beginTransaction()
				.replace(R.id.frameList, new JournalsListFragment())
				.addToBackStack("journalsFragment")
				.commit();
			
			//swapListFragment(new JournalsListFragment(), "journalsFragment");
			break;
			
		case 1:	// Schedule
			fragmentManager
				.beginTransaction()
				.replace(R.id.frameList, new SchedulesFragment())
				.addToBackStack("schedulesFragment")
				.commit();
			
			//swapListFragment(new SchedulesFragment(), "schedulesFragment");
			break;
			
		case 2:	// Cultivation
			fragmentManager
				.beginTransaction()
				.replace(R.id.frameList, new CultivationListFragment())
				.addToBackStack("cultivationListFragment")
				.commit();
			
			//swapListFragment(new CultivationListFragment(), "cultivationListFragment");
			break;
			
		case 3:	// Analysis
			fragmentManager
				.beginTransaction()
				.replace(R.id.frameList, new AnalysisOverviewFragment())
				.addToBackStack("analysisFragment")
				.commit();
			
			//swapListFragment(new AnalysisOverviewFragment(), "anlaysisFragment");
			break;
		}
		
		// Use window manager and systemService to get display
		WindowManager wm = (WindowManager) getActivity().getSystemService(FragmentActivity.WINDOW_SERVICE);
		Display display = wm.getDefaultDisplay();
		//Display display = activity.getWindowManager().getDefaultDisplay();
	/*	
		// Get display dimensions based on API 
		if (currentApi <= android.os.Build.VERSION_CODES.HONEYCOMB) {
			width = display.getWidth();
			height = display.getHeight();
		} else {
		*/
			Point displaySize = new Point();
			display.getSize(displaySize);
			
			int width = displaySize.x;
			int height = displaySize.y;
		//}
	
			//Toast.makeText(getActivity(), "width is: " + width + "; height is: " + height, Toast.LENGTH_SHORT).show();
			
	if (width > 1500 || height > 1500) {
		//activity.getActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
		
	}
			/*
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
*/
		
		View r = inflater.inflate(R.layout.fragment_overview, null);
		
		fragmentManager
			.beginTransaction()
			.replace(R.id.frameNav, new NavigationFragment())
			//.add(frameNav.getId(), new MainActivity.PlaceholderFragment())
			.addToBackStack("navigationFragment")
			.commit();
		
	
		
		//if (position == 0)
		/*
		fragmentManager
			.beginTransaction()
			.replace(R.id.frameList, new BlankFragment())
			.addToBackStack("blankFragment")
			.commit();
		*/
		return r;
				//rlOverView;
	}
	
	private void swapListFragment(Fragment f, String s) {
		
		FragmentManager fMan = getActivity().getSupportFragmentManager();
		
		fMan.beginTransaction()
			.replace(R.id.frameList, f)
			.addToBackStack(s)
			.commit();
	}
	
	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		
	}
}
