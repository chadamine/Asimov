package com.chadamine.growbuddy.fragments;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.chadamine.growbuddy.R;
import com.chadamine.growbuddy.R.color;
import com.chadamine.growbuddy.database.DatabaseContract.Journals;

public class JournalsListFragment extends Fragment 
	implements LoaderManager.LoaderCallbacks<Cursor> {
	
	private FragmentActivity activity;
	private SimpleCursorAdapter adapter;

	@Override 
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRetainInstance(true);
	}

	@Override
	public void onResume() {
		super.onResume();
		fillData();
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		super.onCreateView(inflater, container, savedInstanceState);
		View view = inflater.inflate(R.layout.fragment_journal_list, container, false);
		registerForContextMenu(view);
		setHasOptionsMenu(true);
		
		return view;
	}
	
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		
	}
	
	@Override
	public void onStart() {
		super.onStart();
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
	
		// Set image icon to add image
		// TODO: if (cursor for list_item_icon_location) { setIcon(); } else { setFillerIcon(); }
		LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View view = inflater.inflate(R.layout.add_image_filler, null);
		View item = inflater.inflate(R.layout.list_item_checkable, null);
		
		FrameLayout image = //(FrameLayout) inflater.inflate(R.id.frameImage, null, false);
				(FrameLayout) item.findViewById(R.id.frameImage);
			
			if (image == null)
				Toast.makeText(activity, "image frame is null", Toast.LENGTH_SHORT).show();
			else if (view == null)
				Toast.makeText(activity, "filler image null", Toast.LENGTH_SHORT).show();
			else
				image.addView(view);
			
	}
	
	private void fillData() {
		
			String[] from = new String[] { 
					Journals.COL_NAME, 
					Journals.COL_LOCATION 
			};
			
			int[] to = new int[] { R.id.textTitle, R.id.textDetails };
			
			// Adapter must be initialized before initLoader is called
			adapter = new SimpleCursorAdapter(getActivity(), R.layout.list_item_checkable, null, from, to, 0);
			//setListAdapter(adapter);
			
			FrameLayout listFrame = (FrameLayout) getActivity().findViewById(R.id.list_frame_journals);
		
				ListView list = new ListView(getActivity());
				
					listFrame.addView(list);
					list.setBackgroundResource(color.light_blue);
					
					list.setAdapter(adapter);
					
					// initLoader can now be called 
					getActivity().getSupportLoaderManager().initLoader(1, null, this);
			
	}
	
	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		super.onCreateOptionsMenu(menu, inflater);
		inflater.inflate(R.menu.journal_list, menu);
		
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem i) {

		int id = i.getItemId();

		int add = R.id.addJournal;
		int del = R.id.delJournal;

		if (id == add) 
			getActivity()
			.getSupportFragmentManager()
			.beginTransaction()
			.replace(R.id.frameDetails, new JournalDetailsFragment())
			.addToBackStack("journalDetails")
			.commit();
		if (id == del)
			makeDeleteStyle();
			
		return super.onOptionsItemSelected(i);
	}
	
	
	private void makeDeleteStyle() {
		//this.getListView().setLayoutParams();
		//this.getListView().setBackground();
	}

	@Override
	public Loader<Cursor> onCreateLoader(int arg0, Bundle arg1) {
		
		String[] projection = { Journals.COL_ID, Journals.COL_NAME, Journals.COL_LOCATION };
		
		CursorLoader loader = new CursorLoader(getActivity(), Journals.CONTENT_URI, projection, null, null, null);
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

