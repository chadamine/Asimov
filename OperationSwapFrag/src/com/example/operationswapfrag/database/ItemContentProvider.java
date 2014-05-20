package com.example.operationswapfrag.database;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;

public class ItemContentProvider extends ContentProvider {
	
	private ItemDBHelper helper;

	@Override
	public boolean onCreate() {
		helper = new ItemDBHelper(getContext());
		
		return false;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		
		SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
		
		// checkColumns(projection);
		
		queryBuilder.setTables(ItemContract.TABLE_ITEM);
		
		int uriType = ItemContract.uriMatcher.match(uri);
		
		switch(uriType) {
		case ItemContract.ITEMS:
			break;
		case ItemContract.ITEM_ID:
			queryBuilder.appendWhere(ItemContract.COL_ID + "=" + uri.getLastPathSegment());
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
		return null;
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		int uriType = ItemContract.uriMatcher.match(uri);
		
		SQLiteDatabase db = helper.getWritableDatabase();
		long id = 0;
		
		switch(uriType) {
		case ItemContract.ITEMS:
			id = db.insert(ItemContract.TABLE_ITEM, null, values);
			break;
		default:
			throw new IllegalArgumentException("Unknown URI: " + uri);
		}
		getContext().getContentResolver().notifyChange(uri, null);
		
		return Uri.parse(ItemContract.BASE_PATH + "/" + id);
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

}
