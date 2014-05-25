package com.chadamine.growbuddy.journal;

import android.content.ContentValues;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.chadamine.growbuddy.R;
import com.chadamine.growbuddy.database.DatabaseContract;

public class JournalDetailsFragment extends Fragment {
	
	private static JournalDetailsFragment fragment;
	private static JournalDetailsActivity.ManagementTabsFragmentListener mShowFragment;
	private static EditText itemName;
	private static EditText itemDetails;
	private static Uri itemUri;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle args) {

		Bundle extras = getActivity().getIntent().getExtras();
		itemUri = DatabaseContract.JOURNAL_CONTENT_URI;
		
		View rootView = inflater.inflate(R.layout.fragment_add_journal, container, false);
		
		Button btnSubmit = (Button) rootView.findViewById(R.id.btnSubmit);
		btnSubmit.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				JournalListFragment.callShowFragment();
			}
		});
	
		/* MUST HAVE THE FALSE FOR THIS FRAGMENT TO LOAD */
		return rootView;//inflater.inflate(R.layout.fragment_item_details, container, false);
	}
	
	@Override 
	public void onViewCreated(View view, Bundle savedInstanceState) {
		
	}
	
	private void fillData(Uri uri) {

	}
	
	public static JournalDetailsFragment newInstance(Bundle bundle) {
		fragment = new JournalDetailsFragment();
		
		itemUri = (Uri) bundle.getParcelable(DatabaseContract.JOURNAL_CONTENT_ITEM_TYPE);
		// fillData(itemUri);
		
		return fragment;
	}
	
	public static JournalDetailsFragment newInstance() {
		return new JournalDetailsFragment();
	}
	
	public static JournalDetailsFragment newInstance(JournalDetailsActivity.ManagementTabsFragmentListener listener) {
		fragment = new JournalDetailsFragment();
		
		mShowFragment = listener;
		return fragment;
	}
	
	public static void callShowFragment() {
		mShowFragment.onShowFragment();
	}
	
	@Override
	public void onPause() {
		super.onPause();
		saveState();
	}
	
	private void saveState() {
		String name = ((EditText) getActivity().findViewById(R.id.etName)).getText().toString();
		String details = ((EditText) getActivity().findViewById(R.id.etDetails)).getText().toString();
		
		if(name.length() > 0 || details.length() > 0) {
			ContentValues values = new ContentValues();
			values.put(DatabaseContract.COL_NAME, name);
			values.put(DatabaseContract.COL_DETAILS, details);
		
			itemUri = getActivity().getContentResolver().insert(DatabaseContract.JOURNAL_CONTENT_URI, values);
			
		}
		
		
	}

}
