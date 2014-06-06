package com.chadamine.growbuddy.management.locations;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.chadamine.growbuddy.R;
import com.chadamine.growbuddy.database.DatabaseContract.Locations;

public class LocationDetailsFragment extends Fragment {
	private static LocationDetailsFragment fragment;
	private static EditText itemName;
	private static EditText itemDetails;
	private static Uri uri;
	private static Activity activity;
	private static View view;

	static final int REQUEST_IMAGE_CAPTURE = 1;
	private Uri imageUri;
	final int PIC_CROP = 2;
	private static FrameLayout frame;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle args) {

		activity = getActivity();
		Bundle extras = getActivity().getIntent().getExtras();
		uri = Locations.CONTENT_URI;

		/* MUST HAVE THE FALSE FOR THIS FRAGMENT TO LOAD */
		View rootView = inflater.inflate(R.layout.fragment_add_location, container, false);

		Button btnSubmit = (Button) rootView.findViewById(R.id.btnSubmit);
		btnSubmit.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					//ListFragment.callShowFragment();
				}
			});

		registerForContextMenu(rootView.findViewById(R.id.flImage));

		frame = (FrameLayout) rootView.findViewById(R.id.flImage);

		TextView addImage = new TextView(activity);

		int left = 0;
		int top = 35;
		int right = 0;
		int bottom = 0;

		frame.setPadding(left, top, right, bottom);

		addImage.setText("ADD\nIMAGE");
		addImage.setGravity(Gravity.CENTER);

		frame.addView(addImage);

		return rootView;
	}

	private void populateSpinner(View v) {
		Spinner sprLocations = (Spinner) v.findViewById(R.id.spinnerLocation);

		uri = Locations.CONTENT_URI;
		String[] from = { Locations.COL_NAME, Locations.COL_LOCATION, };
		int[] to = new int[] { android.R.id.text1 };
		//Cursor cursor = activity.getContentResolver().query(uri, from, null, null, null);
		SimpleCursorAdapter adapter = new SimpleCursorAdapter(activity, android.R.layout.simple_spinner_item, null, from, to);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	}

	@Override 
	public void onViewCreated(View view, Bundle savedInstanceState) {

		frame.setOnClickListener(new View.OnClickListener() {
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
				//openFile();
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

		if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == Activity.RESULT_OK) {
			imageUri = data.getData();
			cropImage();
		}

		if (requestCode == PIC_CROP) {
			ImageView image = new ImageView(activity);

			Bundle extras = data.getExtras();
			Bitmap imageBitmap = (Bitmap) extras.get("data");
			image.setImageBitmap(imageBitmap);
			frame.addView(image);			
		}
	}

	private void cropImage() {

		try {
			Intent cropIntent = new Intent("com.android.camera.action.CROP");
			cropIntent
				.setDataAndType(imageUri, "image/*")
				.putExtra("aspectX", 1)
				.putExtra("aspectY", 1)
				.putExtra("outputX", 256)
				.putExtra("outputY", 256)
				.putExtra("return-data", true);
			startActivityForResult(cropIntent, PIC_CROP);

		}

		catch(ActivityNotFoundException exception) {
			String error = "Image capture not supported on this device";
			Toast.makeText(activity, error, Toast.LENGTH_SHORT).show();
		}
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
			values.put(Locations.COL_NAME, name);
			//values.put(DatabaseContract.COL_DETAILS, details);

			uri = getActivity().getContentResolver().insert(Locations.CONTENT_URI, values);

		}

	}
}
