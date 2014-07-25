package com.chadamine.growbuddy.fragments;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.FragmentManager;
import android.content.ActivityNotFoundException;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.database.MergeCursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.chadamine.growbuddy.Main;
import com.chadamine.growbuddy.R;
import com.chadamine.growbuddy.database.DatabaseContract.Journals;
import com.chadamine.growbuddy.database.DatabaseContract.Nutrients;
import com.chadamine.growbuddy.database.DatabaseContract;
import com.chadamine.growbuddy.database.GrowJournalPlusContentProvider;

public class JournalDetailsFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {

	private static Uri itemUri;
	private static Activity activity;
	
	private static final int REQUEST_IMAGE_CAPTURE = 1;
	
	private Uri imageUri;
	private final int PIC_CROP = 2;
	private FrameLayout frame;
	
	private Intent takePictureIntent;
	private PackageManager pManager;
	
	private String currentPhotoPath;
	private Uri capturedImageUri;
	private View view;

	private final static String CAPTURED_PHOTO_PATH_KEY = "currentPhotoPath";
	private final static String CAPTURED_PHOTO_URI_KEY = "capturedImageUri";
	
	private SimpleCursorAdapter locationsAdapter;
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle args) {

		activity = getActivity();
		activity.getActionBar().setTitle("Add New Journal");
		itemUri = Journals.CONTENT_URI;
		
		
		
		/* MUST HAVE THE FALSE FOR THIS FRAGMENT TO LOAD */
		view = inflater.inflate(R.layout.fragment_add_journal, container, false);
		
		Button btnSubmit = (Button) view.findViewById(R.id.btnSubmit);
		btnSubmit.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				getActivity().getSupportFragmentManager().popBackStack("journalDetails", FragmentManager.POP_BACK_STACK_INCLUSIVE);
				//begin Transaction().remove(new JournalsListFragment()).commit();
				
				// Hide Keyboard
				FragmentActivity activity = getActivity();
				View view = activity.getCurrentFocus();
				
			    //check if no view has focus:
			    if(view == null)
			        return;

			    ((InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
				
			}
		});
		
		registerForContextMenu(view.findViewById(R.id.frameDetailImage));
		
		frame = (FrameLayout) view.findViewById(R.id.frameDetailImage);
		/*
		TextView addImage = new TextView(activity);
		
		int left = 0;
		int top = 35;
		int right = 0;
		int bottom = 0;
		
		frame.setPadding(left, top, right, bottom);
		
		addImage.setText("ADD\nIMAGE");
		addImage.setGravity(Gravity.CENTER);
		*/
		//frame.addView(addImage);
		frame.addView(inflater.inflate(R.layout.add_image_filler, null));
	
		return view;
	}
	
	private void populateSpinner(View v) {
		
		Spinner sprLocations = (Spinner) v.findViewById(R.id.spinnerLocation);
		
		String[] projection = new String[] { "_id", "name" }; 
		String[] from = { Journals.COL_NAME };
		int[] to = new int[] { android.R.id.text1 };
		
		Cursor cursor = getActivity().getContentResolver().query(Journals.CONTENT_URI, projection, null, null, null);
		MatrixCursor extras = new MatrixCursor(projection);
		
		extras.addRow(new String[] { "-1", "Add New Location" });
		//extras.addRow(new String[] { "-2", "Empty Template" });
		
		Cursor[] cursors = { extras, cursor };
		Cursor extendedCursor = new MergeCursor(cursors);
		Log.d("cursorsMerged", "+ cursors merged in journal details: " + extendedCursor.getCount());
		
		//SimpleCursorAdapter adapter = new SimpleCursorAdapter(getActivity(), android.R.layout.simple_spinner_item, null, from, to, 0);
		locationsAdapter = new SimpleCursorAdapter(getActivity(), android.R.layout.simple_spinner_item, extendedCursor, from, to, 0);
		locationsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		
		locationsAdapter.changeCursor(extendedCursor);
		sprLocations.setAdapter(locationsAdapter);
		Log.d("cursorIs", "+ Cursor is: " + locationsAdapter.getCursor().toString());
		
		Log.d("cursorItem", "+ Cursor item 0: " + extendedCursor.getString(0));
		//Log.d("cursorItem", "+ Cursor item 1: " + extendedCursor.getString(1));
		//Log.d("cursorItem", "+ Cursor item 2: " + extendedCursor.getString(2));
		
		getActivity().getSupportLoaderManager().initLoader(1, null, this);
	}
	
	@Override 
	public void onViewCreated(View view, Bundle savedInstanceState) {
		
		populateSpinner(view);
		
		frame.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				activity.openContextMenu(v);
			}
		});
		
		if(activity.getPackageManager() == null) 
			throw new NullPointerException("no pckg mgr!");
		else {
			pManager = activity.getPackageManager();
			//Toast.makeText(activity, "pm not null", Toast.LENGTH_SHORT).show();
		
		}
		
		takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		checkHardware();

	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
		if (savedInstanceState != null) {
		//	onRestoreInstanceState
			if (savedInstanceState.containsKey(CAPTURED_PHOTO_PATH_KEY)) {
				currentPhotoPath = savedInstanceState.getString(CAPTURED_PHOTO_PATH_KEY);
			}
		
			if (savedInstanceState.containsKey(CAPTURED_PHOTO_URI_KEY)) {
				capturedImageUri = Uri.parse(savedInstanceState.getString(CAPTURED_PHOTO_URI_KEY));
			}
		}
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
	
	/*****************	TAKE PICTURE 	*************************************/
	
	private void checkHardware() {
		if(pManager.hasSystemFeature(PackageManager.FEATURE_CAMERA) == false) {
			//	set icons for all images

			//	disable camera menu
			activity.findViewById(R.id.camera).setEnabled(false);
		}
	}
	

	public void startCamera() { 
		ContentValues values = new ContentValues();
				
		String fileName = "temp.jpg";

		values.put(MediaStore.Images.Media.TITLE, fileName);
		imageUri = activity.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
				
		takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
		startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		
		if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == Activity.RESULT_OK) {
			
			//	Get the name of the file
			String[] projection = { MediaStore.Images.Media.DATA };
			//Cursor cursor = activity.managedQuery(imageUri, projection, null, null, null);
			Cursor cursor = activity.getContentResolver().query(imageUri, projection, null, null, null);
			int column_index_data = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
			cursor.moveToFirst();
			String filePath = cursor.getString(column_index_data);
			Toast.makeText(activity, filePath, Toast.LENGTH_SHORT).show();
			
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

	
	
	/************ save and restore overridables ************************/
	
	@Override
	public void onSaveInstanceState(Bundle outState)
	{
		if (currentPhotoPath != null) {
			outState.putString(CAPTURED_PHOTO_PATH_KEY, currentPhotoPath);
		}
		
		if (capturedImageUri != null) {
			outState.putString(CAPTURED_PHOTO_URI_KEY, capturedImageUri.toString());
		}
		
		//outState.putSerializable("view", );
		super.onSaveInstanceState(outState);
	}
	
	//@Override
	//protected void onRestoreInstanceState (Bundle savedInstanceState) {
		
		
		//super.onRestoreInstanceState(savedInstanceState);
	//}
	
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
			String error = "Cropping application not found";
			Toast.makeText(activity, error, Toast.LENGTH_SHORT).show();
		}
	}
	
	/* 	Save the data	*/
	
	@Override
	public void onPause() {
		super.onPause();
		saveState();
	}
	
	private void saveState() {
		
		String name = "";
		
		try {
			//name = ((EditText) view.findViewById(R.id.etName)).getText().toString();
			name = ((EditText) getActivity().findViewById(R.id.etName)).getText().toString();
		} catch (NullPointerException e) {
			Toast.makeText(getActivity(), "name could not be created", Toast.LENGTH_SHORT).show();
		}
		//String location = ((EditText) getActivity().findViewById(R.id.etLocation)).getText().toString();
		
		AlertDialog alert = new AlertDialog.Builder(getActivity()).create();
		
		if(name.length() > 0 ) {
			ContentValues values = new ContentValues();
			
			if (name != "")
				values.put(Journals.COL_NAME, name);
			else {
				alert.setTitle("Name Cannot Be Blank");
				alert.setMessage("You must provide a name to save your journal");
				alert.show();
			}
		
			values.put(Journals.COL_LOCATION, "location filler");
		
			itemUri = getActivity().getContentResolver().insert(Journals.CONTENT_URI, values);
			Toast toastSubmit = Toast.makeText(getActivity(), "Journal Added to Database: " + values.toString(), Toast.LENGTH_SHORT);
			toastSubmit.setGravity(Gravity.TOP, Gravity.CENTER, 0);
			toastSubmit.show();
		}
		
	}

	@Override
	public Loader<Cursor> onCreateLoader(int id, Bundle savedInstanceState) {

		if(id == 1) {
			String[] projection = { Journals.COL_ID, Journals.COL_NAME, Journals.COL_LOCATION };

			CursorLoader loader = new CursorLoader(getActivity(), Journals.CONTENT_URI, projection, null, null, null);
			return loader;
		}
		
		return null;
	}

	@Override
	public void onLoadFinished(Loader<Cursor> l, Cursor c) {
		
		if(l.getId() == 1)
			locationsAdapter.swapCursor(c);
	}

	@Override
	public void onLoaderReset(Loader<Cursor> l) {
		
		if(l.getId() == 2)
			locationsAdapter.swapCursor(null);
	}


}
