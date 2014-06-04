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

public class JournalListFragment extends ListFragment 
	implements LoaderManager.LoaderCallbacks<Cursor> {

	private static final String ARG_SECT_NUM = "section_number";
	private static JournalDetailsActivity.ManagementTabsFragmentListener mShowFragment;
	
	private CursorAdapter adapter;
	
	private View view;
	
	public static JournalListFragment newInstance(int sectionNumber) {
			JournalListFragment fragment = new JournalListFragment();
			
			Bundle args = new Bundle();
			args.putInt(ARG_SECT_NUM, sectionNumber);
			fragment.setArguments(args);
			
			return fragment;
	}
	
	public static JournalListFragment newInstance(int sectionNumber, Bundle args) {
		JournalListFragment fragment = new JournalListFragment();
		return fragment;
	}
	
	public static Fragment newInstance(JournalDetailsActivity.ManagementTabsFragmentListener listener) {
		JournalListFragment fragment = new JournalListFragment();
		//ManagementTabsFragmentListener.onShowFragment();
		mShowFragment = listener;
		return fragment;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState)
	{
		// TODO: Implement this method
		//registerForContextMenu(this.getView());
		setHasOptionsMenu(true);
		super.onActivityCreated(savedInstanceState);
	}
	
	@Override 
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);;
		
		//getActivity().setContentView(R.layout.fragment_item_list);	
		
		fillData();
	}
	
	public static void callShowFragment() {
		mShowFragment.onShowFragment();
	}


	
	private void fillData() {
			String[] from = new String[] { Journals.COL_NAME, Journals.COL_LOCATION };
			int[] to = new int[] { R.id.tvNavItemTitle, R.id.tvNavItemDetails };
		
			getLoaderManager().initLoader(0, null, this);
			
			adapter = new SimpleCursorAdapter(getActivity(), R.layout.row_nav, null, from, to, 0);
			setListAdapter(adapter);
			
			if(adapter == null) throw new IllegalArgumentException("Error Adapting Database");
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
			getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.journalListContainer, new JournalDetailsFragment()).commit();
		//Toast.makeText(this, "second frag added", Toast.LENGTH_SHORT).show();
		//return true;
		//}

		//if (id == del) {

		//}


		return super.onOptionsItemSelected(i);
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
