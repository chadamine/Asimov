package com.chadamine.growbuddy.adapters;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.widget.SimpleCursorAdapter;

import com.chadamine.growbuddy.database.DatabaseContract.Journals;
import com.chadamine.growbuddy.database.DatabaseContract.Nutrients;

public class CursorLoaderCallbacks implements LoaderManager.LoaderCallbacks<Cursor> {

	SimpleCursorAdapter adapter;
	Context context;
	Uri uri;
	
	public CursorLoaderCallbacks() {
		
	}
	
	public CursorLoaderCallbacks(Context c, SimpleCursorAdapter a) {
		adapter = a;
		context = c;
	}

	@Override
	public Loader<Cursor> onCreateLoader(int id, Bundle arg1) {
		
		CursorLoader loader = null;
		
		switch(id) {
			case 1:
				loader = new CursorLoader(context, Journals.CONTENT_URI,
						new String[] { Journals.COL_ID, Journals.COL_NAME, Journals.COL_LOCATION }, 
						null, null, null);
			
			case 2:
				loader = new CursorLoader(context, Nutrients.CONTENT_URI,
						new String[] { Nutrients.COL_ID, Nutrients.COL_MANUFACTURER, Nutrients.COL_PRODUCT }, 
						null, null, null);
		}
		
		return loader;
	}

	@Override
	public void onLoadFinished(Loader<Cursor> l, Cursor c) {
		
		if(l.getId() == 2)
			adapter.swapCursor(c);
		else
			adapter.swapCursor(null);
		
		
	}

	@Override
	public void onLoaderReset(Loader<Cursor> l) {
		adapter.swapCursor(null);
	}

}
