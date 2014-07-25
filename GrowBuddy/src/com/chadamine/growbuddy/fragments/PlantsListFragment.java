package com.chadamine.growbuddy.fragments;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.ListFragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.chadamine.growbuddy.R;
import com.chadamine.growbuddy.database.DatabaseContract.Nutrients;


public class PlantsListFragment extends ListFragment 
	implements LoaderManager.LoaderCallbacks<Cursor> {
		
	FragmentManager manager;
	Activity activity;
	View root;

	private SimpleCursorAdapter adapter;

	@Override 
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		root = inflater.inflate(R.layout.fragment_plants_list, container, false);
		activity = getActivity();

		String[] values = new String[] { "Plants", "Nutrients", "Recipes", "Irrigation", "Pest & Disease", "Environment", "Locations" };
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), R.layout.row_nav, R.id.textNavItemTitle, values);

		setListAdapter(adapter);

		return root;
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		populateList(view);
	}

	private void populateList(View r) {
		
		String[] from = new String[] { Nutrients.COL_NAME };
		int[] to = new int[] { R.id.textNavItemTitle };

		getLoaderManager().initLoader(0, null, this);

		adapter = new SimpleCursorAdapter(getActivity(), R.layout.row_nav, null, from, to, 0);
		setListAdapter(adapter);
		
	}		
	
	@Override
	public Loader<Cursor> onCreateLoader(int arg0, Bundle arg1) {

		String[] projection = { Nutrients.COL_ID, Nutrients.COL_NAME };

		CursorLoader loader = new CursorLoader(getActivity(), Nutrients.CONTENT_URI, projection, null, null, null);
		return loader;
	}

	@Override
	public void onLoadFinished(Loader<Cursor> l, Cursor c) {
		adapter.swapCursor(c);
	}

	@Override
	public void onLoaderReset(Loader<Cursor> arg0) {
		adapter.swapCursor(null);
	}
}
