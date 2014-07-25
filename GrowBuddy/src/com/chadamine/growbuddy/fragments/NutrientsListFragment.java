package com.chadamine.growbuddy.fragments;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import com.chadamine.growbuddy.R;
import com.chadamine.growbuddy.database.DatabaseContract.Nutrients;
import com.chadamine.growbuddy.database.DatabaseContract.NutrientSolubilities;
import com.chadamine.growbuddy.database.DatabaseContract.NutrientFormulas;
import android.support.v4.widget.*;
import android.support.v4.app.*;
import android.annotation.TargetApi;
import android.database.*;
import android.support.v4.content.*;
import android.database.sqlite.*;
import android.graphics.Point;
import android.widget.Toast;


public class NutrientsListFragment extends ListFragment 
	implements LoaderManager.LoaderCallbacks<Cursor>{

	FragmentActivity activity;
	private CursorAdapter adapter;
	//private boolean isNewInstance;
	
	@Override
	public void onResume( ) {
		super.onResume();
		//isNewInstance = false;
		fillData();
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//isNewInstance = true;
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
	}
	
	
	@Override 
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		
		View view = inflater.inflate(R.layout.fragment_nutrients_list, container, false);

		activity = getActivity();
		
		Button btnAdd = (Button) view.findViewById(R.id.buttonAddNutrient);
		Button btnDel = (Button) view.findViewById(R.id.buttonDeleteNutrient);
		
		final FragmentManager manager = activity.getSupportFragmentManager();
		
		btnAdd.setOnClickListener(new View.OnClickListener() {
			
			@TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
			@Override
			public void onClick(View v) {
				
				WindowManager wm = (WindowManager) activity.getSystemService(activity.WINDOW_SERVICE);
				Display display = wm.getDefaultDisplay();
				Point displaySize = new Point();
				display.getSize(displaySize);
				
				int width = displaySize.x;
				int height = displaySize.y;
			//}
		
				//Toast.makeText(getActivity(), "width is: " + width + "; height is: " + height, Toast.LENGTH_SHORT).show();
				
		if (width > 1500 || height > 1500)
				
			manager
				.beginTransaction()
				.replace(R.id.cultivationFrameDetails, new NutrientsFragment())
				.addToBackStack("nutrientDetails")
				.commit();
			}
		});
		
		btnDel.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
			}
		});
		
		return view;
	}
	
	private void fillData() {
		String[] from = new String[] { 
			Nutrients.COL_MANUFACTURER, 
			Nutrients.COL_PRODUCT
		};
		
		int[] to = new int[] { R.id.textTitle, R.id.textDetails };
			
			adapter = new SimpleCursorAdapter(getActivity(), R.layout.row_item_checkable, null, from, to, 0);
			Log.d("adapterAssigned", "+ - cursorAdapter assigned to adapter");
			
			setListAdapter(adapter);
			Log.d("listAdapterSet", "+ - list adapter set");
			
			/*try {
				if(isNewInstance) {	*/
					getActivity().getSupportLoaderManager().initLoader(1, null, this);
			/*		Log.d("loaderInitialized", "+ - nutrients loader manager initialized (new instance)");
				} else {
					getActivity().getSupportLoaderManager().restartLoader(1, null, this);
					Log.d("loaderInitialized", "+ - nutrients loader manager initialized (old instance)");
				}
				
		} catch (NullPointerException e) {
			Toast.makeText(getActivity(), "! - adapter could not be set", Toast.LENGTH_SHORT).show();
		}
		*/
	}
	
	@Override
	public Loader<Cursor> onCreateLoader(int arg0, Bundle arg1) {

		String[] projection = { Nutrients.COL_ID, Nutrients.COL_MANUFACTURER, Nutrients.COL_PRODUCT };

		CursorLoader loader = new CursorLoader(getActivity(), Nutrients.CONTENT_URI, projection, null, null, null);
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
