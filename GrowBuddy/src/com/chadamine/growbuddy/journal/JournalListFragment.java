package com.chadamine.growbuddy.journal;

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
import com.chadamine.growbuddy.database.DatabaseContract.Journals;
import android.view.*;
import android.widget.*;
import android.widget.RadioGroup.*;

public class JournalListFragment extends ListFragment 
	implements LoaderManager.LoaderCallbacks<Cursor> {
	
	private CursorAdapter adapter;

	@Override 
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		//getActivity().setContentView(R.layout.fragment_item_list);	
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		super.onCreateView(inflater, container, savedInstanceState);
		fillData();
		setHasOptionsMenu(true);
		View view = inflater.inflate(R.layout.fragment_journal_list, container, false);
		// TODO: Implement this method
		return view;
		
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState)
	{
		// TODO: Implement this method
		//registerForContextMenu(this.getView());
		
		
		super.onActivityCreated(savedInstanceState);
		
		
	}
	
	private void fillData() {
			String[] from = new String[] { Journals.COL_NAME };
			int[] to = new int[] { R.id.tvNavItemTitle };
		
			getLoaderManager().initLoader(0, null, this);
			
			adapter = new SimpleCursorAdapter(getActivity(), R.layout.row_nav, null, from, to, 0);
			setListAdapter(adapter);
			Toast.makeText(getActivity(), "list adapter set", Toast.LENGTH_SHORT).show();;
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
		
		String[] projection = { Journals.COL_ID, Journals.COL_NAME };
		
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
