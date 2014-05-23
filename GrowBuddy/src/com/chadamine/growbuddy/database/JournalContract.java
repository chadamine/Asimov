package com.chadamine.growbuddy.database;

import android.content.ContentResolver;
import android.content.UriMatcher;
import android.net.Uri;
import android.provider.SyncStateContract.Columns;

/* 
 * Items Contract
 * 
 * Defines constants that help applications 
 * work with features of content provider
 *
 */

public class JournalContract {

	public static final String BASE_PATH = "items";
	public static final String AUTHORITY = "com.chadamine.growbuddy.provider";
	public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" + BASE_PATH);
	
	public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/items";
	public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/item";
	
	public static final String DATABASE_NAME = "items.db";
	public static final int DB_VERSION = 1;
	
	public static final String TABLE_ITEM = "item";
	
	public static final String COL_ID = "_id";
	public static final String COL_NAME = "name";
	public static final String COL_DETAILS = "details";
	
	public static final String DATABASE_CREATE = "create table "
			+ TABLE_ITEM + "("
			+ COL_ID + " integer primary key autoincrement, "
			+ COL_NAME + " text not null, "
			+ COL_DETAILS + " text not null)";
	
	public static final String DATABASE_DELETE = "drop table if exists item";
	
	public static final int ITEMS = 1;
	public static final int ITEM_ID = 2;
	
	public static final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
	
	
	public static final String[] projectionSummary = { COL_NAME,  };
	public static final String[] projectionFull = { COL_ID, COL_NAME, COL_DETAILS };
	
	// static initialization ("class constructor")
	static {
		uriMatcher.addURI(AUTHORITY, BASE_PATH, ITEMS);
		uriMatcher.addURI(AUTHORITY, BASE_PATH + "/#", ITEM_ID);
	}
	
	public JournalContract() {
		
	}
	
	public static final class Items implements Columns {
		public static final Uri contentUri = Uri.withAppendedPath(
				JournalContract.CONTENT_URI, BASE_PATH);
	}
}
