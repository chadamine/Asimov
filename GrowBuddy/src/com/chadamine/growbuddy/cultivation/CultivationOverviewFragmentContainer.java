/**
 * 
 */
package com.chadamine.growbuddy.cultivation;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chadamine.growbuddy.R;

/**
 * @author chadwick
 *
 */
public class CultivationOverviewFragmentContainer extends Fragment {

	/**
	 * 
	 */
	public CultivationOverviewFragmentContainer() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		super.onCreateView(inflater, container, savedInstanceState);
		Log.d("onCreateViewCalled", "+ onCreateView called in cultivationFragment (line 29)");
		
		
		View view = inflater.inflate(R.layout.fragment_cultivation_overview, null);
		
		//TextView text = (TextView) view.findViewById(R.id.textBlankFragment);
		//text.setText("Cultivation");
		
		
		getActivity()
			.getSupportFragmentManager()
			.beginTransaction()
			.replace(R.id.cultivationFrameList, new CultivationListFragment())
			.addToBackStack("cultivationListFragment")
			.commit();
		
		
		return view;
	}

}
