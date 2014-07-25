package com.chadamine.growbuddy.fragments;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.widget.CursorAdapter;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.chadamine.growbuddy.R;
import com.chadamine.growbuddy.database.DatabaseContract;
import com.chadamine.growbuddy.database.DatabaseContract.Locations;

import android.view.*;
import android.widget.Toast;

public class LocationsListFragment extends ListFragment 
implements LoaderManager.LoaderCallbacks<Cursor> {
	
	private CursorAdapter adapter;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_locations_list, container,
										 false);

		//setUp(view);

		return view;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState)
	{
		setHasOptionsMenu(true);
		super.onActivityCreated(savedInstanceState);
	}
	
	@Override 
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		fillData();
	}
	
	private void fillData() {
			String[] from = new String[] { Locations.COL_NAME, Locations.COL_ADDRESS};
			int[] to = new int[] { R.id.textNavItemTitle, R.id.textNavItemDetails };
			
			try {
				getActivity().getSupportLoaderManager().initLoader(0, null, this);
			
			} catch (NullPointerException e) {
				Toast.makeText(getActivity(), "error adapting database", Toast.LENGTH_SHORT).show();
			}
			adapter = new SimpleCursorAdapter(getActivity(), R.layout.row_item_checkable, null, from, to, 0);
			setListAdapter(adapter);
	}

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		super.onCreateOptionsMenu(menu, inflater);
		inflater.inflate(R.menu.locations, menu);
		
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem i) {

		int id = i.getItemId();

		int add = R.id.addLocation;
		int del = R.id.delLocation;

		if (id == add) 
			getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.locationsListContainer, new LocationDetailsFragment()).commit();

		return super.onOptionsItemSelected(i);
	}
	

	@Override
	public Loader<Cursor> onCreateLoader(int arg0, Bundle arg1) {
		
		String[] projection = { Locations.COL_ID, Locations.COL_NAME, Locations.COL_ADDRESS};
		
		CursorLoader loader = new CursorLoader(getActivity(), Locations.CONTENT_URI, projection, null, null, null);
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
