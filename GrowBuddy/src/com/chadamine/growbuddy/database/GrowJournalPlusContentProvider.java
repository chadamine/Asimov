package com.chadamine.growbuddy.database;

import android.annotation.TargetApi;
import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.os.Build;
import android.util.Log;

import com.chadamine.growbuddy.database.DatabaseContract.Journals;
import com.chadamine.growbuddy.database.DatabaseContract.Locations;
import com.chadamine.growbuddy.database.DatabaseContract.Nutrients;
import com.chadamine.growbuddy.database.DatabaseHelpers.DatabaseHelper;
import com.chadamine.growbuddy.database.DatabaseHelpers.LocationsHelper;
import com.chadamine.growbuddy.database.DatabaseHelpers.NutrientsHelper;
import com.chadamine.growbuddy.database.tables.LocationsTable;

@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
public class GrowJournalPlusContentProvider extends ContentProvider {
	
	private DatabaseHelper databaseHelper;
	private LocationsHelper locationsHelper;
	@Override
	public boolean onCreate() {
		Log.d("helperCreated", "journals helper created");
		databaseHelper = new DatabaseHelper(getContext());
		locationsHelper = new LocationsHelper(getContext());
		return false;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		
		Cursor cursor = null;
		int uriType = DatabaseContract.URI_MATCHER.match(uri);
		SQLiteDatabase database = databaseHelper.getWritableDatabase();
		SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
		//database.execSQL(NutrientsTable.CREATE);
		
		switch(uriType) {
		//switch(DatabaseContract.URI_MATCHER.match(uri)) {
		case DatabaseContract.JOURNALS_ID:
			queryBuilder.appendWhere(Journals.COL_ID + "=" + uri.getLastPathSegment());
			Log.d("setDatabase", "+ querybuilder appended: journals_id");
			
		case DatabaseContract.JOURNALS:
			queryBuilder.setTables(Journals.TABLE_NAME);
			Log.d("setDatabase", "+ querybuilder set: journals");
			break;
		
		case DatabaseContract.NUTRIENTS_ID:
			//database = new NutrientsHelper(getContext()).getWritableDatabase();
			queryBuilder.appendWhere(Nutrients.COL_ID + "=" + uri.getLastPathSegment());
			Log.d("setDatabase", "+ type: nutrients_id");
			
		case DatabaseContract.NUTRIENTS:
			queryBuilder.setTables(Nutrients.TABLE_NAME);
			Log.d("setDatabase", "+ query builder table set: nutrients");
			break;
		
		case DatabaseContract.LOCATIONS_ID:
			//database = locationsHelper.getWritableDatabase();
			//database.execSQL(LocationsTable.CREATE);
			queryBuilder.appendWhere(Locations.COL_ID + "=" + uri.getLastPathSegment());
			Log.d("setDatabase", "+ querybuilder appended, type: locations_id");
		case DatabaseContract.LOCATIONS:
			//database = locationsHelper.getWritableDatabase();
			queryBuilder.setTables(Locations.TABLE_NAME);
			Log.d("setDatabase", "+ querybuilder table set: locations");
			break;
			
		default:
			throw new IllegalArgumentException ("Unknown Uri: " + uri);
			
		}
		
		try {
			
			cursor = queryBuilder.query(database, projection, selection, selectionArgs, null, null, sortOrder);
			
		} catch (SQLiteException e) {
			throw new SQLiteException(
					"Database Query failed; Database: " + database.toString() 
					+ "; Projection: " + projection[0] 
					//+ "; ColumnName: " + columnName
					+ "; table " + queryBuilder.getTables()
					+ "; Uri: " + uri
					+ "; UriType " + uriType
					+ "; queryBuilder: " + queryBuilder.getTables());
		}
		
		cursor.setNotificationUri(getContext().getContentResolver(), uri);	
	
		return cursor;
	}

	@Override
	public String getType(Uri uri) {
		
		switch(DatabaseContract.URI_MATCHER.match(uri)) {
			case DatabaseContract.JOURNALS:
				return Journals.CONTENT_TYPE;
			case DatabaseContract.NUTRIENTS:
				return Nutrients.CONTENT_TYPE;
			case DatabaseContract.NUTRIENTS_ID:
				return Nutrients.CONTENT_ITEM_TYPE;
			case DatabaseContract.LOCATIONS:
				return Locations.CONTENT_TYPE;
			case DatabaseContract.LOCATIONS_ID:
				return Locations.CONTENT_ITEM_TYPE;
			default:
				throw new IllegalArgumentException("Unsupported URI: " + uri);
		}
		
		//return null;
		 
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		//TODO: Add uriType switch
		SQLiteDatabase database;
		//= databaseHelper.getWritableDatabase();
		int uriType = DatabaseContract.URI_MATCHER.match(uri);
		long id = 0;
		//SQLiteDatabase db = database;
				//databaseHelper.getWritableDatabase();
		
		switch(uriType) {
		case DatabaseContract.JOURNALS:
			database = new DatabaseHelper(getContext()).getWritableDatabase();
			id = database.insert(Journals.TABLE_NAME, null, values);
			getContext().getContentResolver().notifyChange(uri, null);
			return Uri.parse(Journals.TABLE_NAME + "/" + id);
			
		case DatabaseContract.LOCATIONS:
			database = locationsHelper.getWritableDatabase();
			id = database.insert(Locations.TABLE_NAME, null, values);
			getContext().getContentResolver().notifyChange(uri, null);
			return Uri.parse(Locations.TABLE_NAME + "/" + id);
			
		case DatabaseContract.NUTRIENTS:
			database = new NutrientsHelper(getContext()).getWritableDatabase();
			id = database.insert(Nutrients.TABLE_NAME, null, values);
			getContext().getContentResolver().notifyChange(uri, null);
			return Uri.parse(Nutrients.TABLE_NAME + "/" + id);
			
		default:
			throw new IllegalArgumentException ("Unknown Uri: " + uri);
			
		}
	
//		long id = 0;
//		
//		id = db.insert(getTableName(uri), null, values);
//		
//		return Uri.parse(getTableName(uri)/*Journals.BASE_PATH*/ + "/" + id);
	}
	
	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		
		SQLiteDatabase db = databaseHelper.getWritableDatabase();
		int uriType = DatabaseContract.URI_MATCHER.match(uri);
		int rowsDeleted = 0;
		
		String id = uri.getLastPathSegment();

		getContext().getContentResolver().notifyChange(uri, null);
		
		db.execSQL("vacuum");
		return rowsDeleted;
	}
	
	private boolean isId(int type) {
		
		if(type % 2 == 0)
			return true;
		else
			return false;
	}
	
	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
