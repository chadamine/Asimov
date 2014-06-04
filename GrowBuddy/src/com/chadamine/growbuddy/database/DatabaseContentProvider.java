package com.chadamine.growbuddy.database;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;

import com.chadamine.growbuddy.database.DatabaseContract.Journals;
import com.chadamine.growbuddy.database.DatabaseContract.Nutrients;
import com.chadamine.growbuddy.database.tables.TableJournals;
import com.chadamine.growbuddy.database.tables.TableNutrients;

public class DatabaseContentProvider extends ContentProvider {
	
	private JournalDBHelper helper;

	@Override
	public boolean onCreate() {
		helper = new JournalDBHelper(getContext());
		
		return false;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		
		SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
		
		// checkColumns(projection);
		
		queryBuilder.setTables(Journals.TABLE_NAME);
		
		int uriType = DatabaseContract.URI_MATCHER.match(uri);
		
		switch(uriType) {
				
			case DatabaseContract.JOURNALS:
				queryBuilder.appendWhere(Journals.COL_ID + "=" + uri.getLastPathSegment());
				break;
			case DatabaseContract.NUTRIENTS:
				break;
			case DatabaseContract.NUTRIENTS_ID:
				queryBuilder.appendWhere(Nutrients.COL_ID + "=" + uri.getLastPathSegment());
				break;
		default:
			throw new IllegalArgumentException("Unknown URI: " + uri);
		}
		
		SQLiteDatabase database = helper.getWritableDatabase();
		Cursor cursor = queryBuilder.query(database, projection, selection, selectionArgs, null, null, sortOrder);
		cursor.setNotificationUri(getContext().getContentResolver(), uri);
		
		return cursor;
	}

	@Override
	public String getType(Uri uri) {
		switch(DatabaseContract.URI_MATCHER.match(uri)) {
			
		}
		return null;
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		int uriType = DatabaseContract.URI_MATCHER.match(uri);
		
		SQLiteDatabase db = helper.getWritableDatabase();
		long id = 0;
		
		switch(uriType) {
		case DatabaseContract.JOURNALS:
			id = db.insert(Journals.TABLE_NAME, null, values);
			break;
		default:
			throw new IllegalArgumentException("Unknown URI: " + uri);
		}
		getContext().getContentResolver().notifyChange(uri, null);
		
		return Uri.parse(Journals.TABLE_NAME + "/" + id);
	}

	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		// TODO Auto-generated method stub
		return 0;
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
		
}
