package com.example.operationswapfrag.journals;

import com.example.operationswapfrag.R;
import com.example.operationswapfrag.R.id;
import com.example.operationswapfrag.R.layout;
import com.example.operationswapfrag.database.ItemContract;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.widget.CursorAdapter;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ItemListFragment extends ListFragment 
	implements LoaderManager.LoaderCallbacks<Cursor> {

	private static final String ARG_SECT_NUM = "section_number";
	private static ManagementTabsFragmentListener mShowFragment;
	
	private CursorAdapter adapter;
	
	public static ItemListFragment newInstance(int sectionNumber) {
			ItemListFragment fragment = new ItemListFragment();
			
			Bundle args = new Bundle();
			args.putInt(ARG_SECT_NUM, sectionNumber);
			fragment.setArguments(args);
			
			return fragment;
	}
	
	public static ItemListFragment newInstance(int sectionNumber, Bundle args) {
		ItemListFragment fragment = new ItemListFragment();
		return fragment;
	}
	
	public static Fragment newInstance(ManagementTabsFragmentListener listener) {
		ItemListFragment fragment = new ItemListFragment();
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
			String[] from = new String[] { ItemContract.COL_NAME, ItemContract.COL_DETAILS };
			int[] to = new int[] { R.id.listTitle, R.id.listDetails };
		
			getLoaderManager().initLoader(0, null, this);
			adapter = new SimpleCursorAdapter(getActivity(), R.layout.item_row, null, from, to, 0);
			setListAdapter(adapter);
	}
	
	


	@Override
	public Loader<Cursor> onCreateLoader(int arg0, Bundle arg1) {
		String[] projection = ItemContract.projectionFull;
		
		CursorLoader loader = new CursorLoader(getActivity(), ItemContract.CONTENT_URI, projection, null, null, null);
		
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
