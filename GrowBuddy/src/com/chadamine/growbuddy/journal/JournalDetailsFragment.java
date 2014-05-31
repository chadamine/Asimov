package com.chadamine.growbuddy.journal;

import android.app.*;
import android.content.*;
import android.graphics.*;
import android.net.*;
import android.os.*;
import android.provider.*;
import android.support.v4.app.*;
import android.view.*;
import android.view.ContextMenu.*;
import android.widget.*;
import com.chadamine.growbuddy.*;
import com.chadamine.growbuddy.database.*;

import android.support.v4.app.Fragment;

public class JournalDetailsFragment extends Fragment {
	
	private static JournalDetailsFragment fragment;
	private static JournalDetailsActivity.ManagementTabsFragmentListener mShowFragment;
	private static EditText itemName;
	private static EditText itemDetails;
	private static Uri itemUri;
	private static Activity activity;
	private static View view;
	static final int REQUEST_IMAGE_CAPTURE = 1;
	static final int RESULT_OK = 0;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle args) {

		activity = getActivity();
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
		
		registerForContextMenu(rootView.findViewById(R.id.flImage));
	
		/* MUST HAVE THE FALSE FOR THIS FRAGMENT TO LOAD */
		return rootView;//inflater.inflate(R.layout.fragment_item_details, container, false);
	}
	
	@Override 
	public void onViewCreated(View view, Bundle savedInstanceState) {
		FrameLayout picBox = (FrameLayout) view.findViewById(R.id.flImage);
		//ImageView picture = new ImageView();
		
		picBox.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				activity.openContextMenu(v);
			}
		});
	}
	
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);
		MenuInflater inflater = activity.getMenuInflater();
		inflater.inflate(R.menu.image_source, menu);
	}
	
	
	@Override
	public boolean onContextItemSelected(MenuItem item) {
			
		switch(item.getItemId()) {
			case R.id.camera:
				startCamera();
				return true;
				
			case R.id.file:
				return true;
			default:
				return super.onContextItemSelected(item);
			
		}
	}
	
	private void startCamera() { 
		Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		
		if (takePictureIntent.resolveActivity(activity.getPackageManager()) != null) {
			startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
		}
	}
	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		Toast.makeText(activity, Integer.toString(resultCode), Toast.LENGTH_LONG);
		
		if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == Activity.RESULT_OK) {
			FrameLayout frame = (FrameLayout) activity.findViewById(R.id.flImage);
			ImageView image = new ImageView(activity);
			
			Bundle extras = data.getExtras();
			Bitmap imageBitmap = (Bitmap) extras.get("data");
			image.setImageBitmap(imageBitmap);
			frame.addView(image);
			
			
		}
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
		//String details = ((EditText) getActivity().findViewById(R.id.etDetails)).getText().toString();
		
		if(name.length() > 0 ) {
			ContentValues values = new ContentValues();
			values.put(DatabaseContract.COL_NAME, name);
			//values.put(DatabaseContract.COL_DETAILS, details);
		
			itemUri = getActivity().getContentResolver().insert(DatabaseContract.JOURNAL_CONTENT_URI, values);
			
		}
		
		
	}

}
