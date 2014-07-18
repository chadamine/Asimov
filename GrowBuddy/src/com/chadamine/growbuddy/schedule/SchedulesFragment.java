package com.chadamine.growbuddy.schedule;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chadamine.growbuddy.R;

public class SchedulesFragment extends Fragment {
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View view = inflater.inflate(R.layout.fragment_blank,
				container, false);
		
		TextView text = (TextView) view.findViewById(R.id.textBlankFragment);
		text.setText("Schedules");
		
		return view;
	}
}
