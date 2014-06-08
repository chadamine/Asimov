package com.chadamine.growbuddy.database;

import android.content.*;
import android.database.*;
import android.database.sqlite.*;
import android.net.*;
import com.chadamine.growbuddy.database.DatabaseContract.*;
import com.chadamine.growbuddy.database.tables.*;
import android.widget.*;
import com.chadamine.growbuddy.journal.*;

public class DatabaseContentProvider extends ContentProvider {
	
	private JournalDBHelper helper;
	DatabaseContract dc;

	@Override
	public boolean onCreate() {
		helper = new JournalDBHelper(getContext());
		
		return false;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		
		SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
		int uriType = DatabaseContract.URI_MATCHER.match(uri);
		String columnName = getColumnName(uri, uriType);
		
		// checkColumns(projection);
		
		queryBuilder.setTables(getTableName(uri));
		
		if(columnName != "")
			queryBuilder.appendWhere(columnName + "=" + uri.getLastPathSegment());
		
		Cursor cursor = null;
		
		try {
			SQLiteDatabase database = helper.getWritableDatabase();
			cursor = queryBuilder.query(database, projection, selection, selectionArgs, null, null, sortOrder);
			cursor.setNotificationUri(getContext().getContentResolver(), uri);	
			
			return cursor;
		} catch (SQLiteException e) {
			throw new SQLException("Database Creation Failed. UriType = " + Integer.toString(uriType));
		}
	}
	
	private String getColumnName(Uri uri, int type) {
		
		String col = "";
		
		
		switch(type) {

			case DatabaseContract.JOURNALS:
				break;
			case DatabaseContract.JOURNALS_ID:
				col = Journals.COL_ID;
				break;
			case DatabaseContract.LOCATIONS:
				break;
			case DatabaseContract.LOCATIONS_ID:
				col = Locations.COL_ID;
				break;
			case DatabaseContract.NUTRIENTS:
				break;
			case DatabaseContract.NUTRIENTS_ID:
				col = Nutrients.COL_ID;
				break;
			default:
				throw new IllegalArgumentException("Unknown URI: " + uri);
		}
		
		return col;
	}

	@Override
	public String getType(Uri uri) {
		/*switch(DatabaseContract.URI_MATCHER.match(uri)) {
			case DatabaseContract.JOURNALS:
				return Journals.CONTENT_TYPE;
			case DatabaseContract.JOURNAL_LOCATIONS_ID:
				return Journals.CONTENT_ITEM_TYPE;
			default:
				throw new IllegalArgumentException("Unsupported URI: " + uri);
		}*/
		return null;
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		
		long id = 0;
		SQLiteDatabase db = helper.getWritableDatabase();
		
		id = db.insert(getTableName(uri), null, values);
		getContext().getContentResolver().notifyChange(uri, null);
		
		return Uri.parse(getTableName(uri)/*Journals.BASE_PATH*/ + "/" + id);
	}
	
	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		SQLiteDatabase db = helper.getWritableDatabase();
		
		db.execSQL("vacuum");
		return 0;
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	private String getTableName(Uri uri) {
		int uriType = DatabaseContract.URI_MATCHER.match(uri);

		String name = "";

		switch(uriType) {
			case DatabaseContract.JOURNALS:
				name = Journals.TABLE_NAME;
				break;
			case DatabaseContract.JOURNALS_HISTORY:
				name = JournalsHistory.TABLE_NAME;
				break;
			case DatabaseContract.LOCATIONS:
				name = Locations.TABLE_NAME;
				break;
			case DatabaseContract.BATCHES:
				name = Batches.TABLE_NAME;
				break;
			case DatabaseContract.BATCH_PLANTS:
				
			default:
				throw new IllegalArgumentException("Unknown URI: " + uri);
		}

		return name;
	}
	
	/** Journal DB Helper **/
	
	public static class JournalDBHelper extends SQLiteOpenHelper {

		public JournalDBHelper(Context context) {
			super(context, DatabaseContract.DATABASE_NAME, null, DatabaseContract.DB_VERSION);
			// TODO Auto-generated constructor stub
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			TableJournals.onCreate(db);
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub

		}
	}
	
	
	/** Nutrient Table **/
	public static class NutrientDBHelper extends SQLiteOpenHelper {
		
		public NutrientDBHelper(Context c) {
			super(c, DatabaseContract.DATABASE_NAME, null, DatabaseContract.DB_VERSION);
			
		}
		
		@Override
		public void onCreate(SQLiteDatabase db) {
			TableNutrients.onCreate(db);
		}
		
		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			
		}
	}
	
	public static class LocationsDBHelper extends SQLiteOpenHelper {
		public LocationsDBHelper(Context c) {
			super(c, DatabaseContract.DATABASE_NAME, null, DatabaseContract.DB_VERSION);
		}
		
		@Override
		public void onCreate(SQLiteDatabase db) {
			LocationsTable.onCreate(db);	
		}	
		
		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			
		}
	}		
}
