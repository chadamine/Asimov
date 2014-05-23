package com.chadamine.growbuddy.database;
import android.net.*;
import android.content.*;
import android.provider.SyncStateContract.Columns;

public class NutrientContract {
	public static final String BASE_PATH = "nutrients";
	public static final String AUTHORITY = "com.chadamine.growbuddy.provider";
	public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" + BASE_PATH);
	
	public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/nutrients";
	public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/nutrient";
	
	public static final String DATABASE_NAME = "nutrients.db";
	public static final int DB_VERSION = 1;
	
	public static final String TABLE_NUTRIENT = "nutrients.db";
	
	public static final String COL_ID = "_id";
	public static final String COL_NAME = "name";
	public static final String COL_TYPE = "type";
	
	public static final String DATABASE_CREATE = 
		"create table " + 
		TABLE_NUTRIENT + "(" + 
		COL_ID + " integer primary key autoincrement, " + 
		COL_NAME + "text not null, " + 
		COL_TYPE + " text not null)";
		
	public static final int NUTRIENTS = 1;
	public static final int NUTRIENT_ID = 2;
	
	public static final UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
	
	public static final String[] projectionSummary = { COL_NAME, COL_TYPE };
	public static final String[] projectionFull = { COL_ID, COL_NAME, COL_TYPE };
	
	static {
		matcher.addURI(AUTHORITY, BASE_PATH, NUTRIENTS);
		matcher.addURI(AUTHORITY, BASE_PATH + "/#", NUTRIENT_ID);
	}
	
	public static final class Nutrients implements Columns {
		public static final Uri contentUri = Uri.withAppendedPath(NutrientContract.CONTENT_URI, BASE_PATH);
	}
}
