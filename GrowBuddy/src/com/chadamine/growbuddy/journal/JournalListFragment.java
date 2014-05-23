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

import com.chadamine.growbuddy.R;
import com.chadamine.growbuddy.database.DatabaseContract;

public class JournalListFragment extends ListFragment 
	implements LoaderManager.LoaderCallbacks<Cursor> {

	private static final String ARG_SECT_NUM = "section_number";
	private static JournalDetailsActivity.ManagementTabsFragmentListener mShowFragment;
	
	private CursorAdapter adapter;
	
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
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);;
		
		//getActivity().setContentView(R.layout.fragment_item_list);
		
		fillData();
	}
	
	public static void callShowFragment() {
		mShowFragment.onShowFragment();
	}


	
	private void fillData() {
			String[] from = new String[] { DatabaseContract.COL_NAME, DatabaseContract.COL_DETAILS };
			int[] to = new int[] { R.id.tvListTitle, R.id.tvListDetails };
		
			getLoaderManager().initLoader(0, null, this);
			adapter = new SimpleCursorAdapter(getActivity(), R.layout.item_row, null, from, to, 0);
			setListAdapter(adapter);
	}
	
	


	@Override
	public Loader<Cursor> onCreateLoader(int arg0, Bundle arg1) {
		String[] projection = DatabaseContract.projectionFull;
		
		CursorLoader loader = new CursorLoader(getActivity(), DatabaseContract.JOURNAL_CONTENT_URI, projection, null, null, null);
		
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
