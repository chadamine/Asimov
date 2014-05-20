package com.example.operationswapfrag.journals;

import com.example.operationswapfrag.R;
import com.example.operationswapfrag.R.id;
import com.example.operationswapfrag.R.layout;
import com.example.operationswapfrag.database.ItemContract;

import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class ItemDetailsFragment extends Fragment {
	
	private static ItemDetailsFragment fragment;
	private static ManagementTabsFragmentListener mShowFragment;
	private static EditText itemName;
	private static EditText itemDetails;
	private static Uri itemUri;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle args) {

		Bundle extras = getActivity().getIntent().getExtras();
		itemUri = ItemContract.CONTENT_URI;
		
		View rootView = inflater.inflate(R.layout.fragment_item_details, container, false);
		
		Button btnSubmit = (Button) rootView.findViewById(R.id.btnSubmit);
		btnSubmit.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				ItemListFragment.callShowFragment();
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
	
	public static ItemDetailsFragment newInstance(Bundle bundle) {
		fragment = new ItemDetailsFragment();
		
		itemUri = (Uri) bundle.getParcelable(ItemContract.CONTENT_ITEM_TYPE);
		// fillData(itemUri);
		
		return fragment;
	}
	
	public static ItemDetailsFragment newInstance() {
		return new ItemDetailsFragment();
	}
	
	public static ItemDetailsFragment newInstance(ManagementTabsFragmentListener listener) {
		fragment = new ItemDetailsFragment();
		
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
			values.put(ItemContract.COL_NAME, name);
			values.put(ItemContract.COL_DETAILS, details);
		
			itemUri = getActivity().getContentResolver().insert(ItemContract.CONTENT_URI, values);
			//itemUri.notify();
		}
		
		
	}

}
