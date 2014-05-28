package com.chadamine.growbuddy.cultivation.recipes;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chadamine.growbuddy.R;

public class RecipeListFragment extends ListFragment {
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_recipes_list,
				container, false);
		
		return rootView;
	}
}
