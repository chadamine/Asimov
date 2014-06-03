package com.chadamine.growbuddy.database;

import android.content.ContentResolver;
import android.content.UriMatcher;
import android.net.Uri;
import android.provider.SyncStateContract.Columns;

/* 
 * Journals Contract
 * 
 * Defines constants that help applications 
 * work with features of content provider
 *
 */

public class DatabaseContract {
	public static final String DATABASE_NAME = "growbuddy.db";
	public static final int DB_VERSION = 1;
	public static final String AUTHORITY = "com.chadamine.growbuddy.provider";
	
	// 	Columns
	public static final String COL_ID = "_id";
	public static final String COL_NAME = "name";
	public static final String COL_DETAILS = "details";

	public static final String COL_DATE_CREATED = "date_created";

	public static final String COL_LOCATION = "location";
	public static final String COL_BATCH = "batch";
	
	//	Journals
	
	public static final String JOURNAL_BASE_PATH = "journals";
	public static final Uri JOURNAL_CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" + JOURNAL_BASE_PATH);
	public static final String JOURNAL_CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/journals";
	public static final String JOURNAL_CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/journal";
	public static final String TABLE_JOURNALS = "journal";
	
	// Create Journal Table
	public static final String DATABASE_CREATE_JOURNALS = "create table "
	+ TABLE_JOURNALS + "("
	+ COL_ID + " integer primary key autoincrement, "
	+ COL_NAME + " text not null, "
	+ COL_DETAILS + " text not null)";
	
	
	
	// Journal Constants
	public static final int JOURNALS = 1;
	public static final int JOURNAL_ID = 2;
	
	
	//	Nutrients
	
	public static final String NUTRIENT_BASE_PATH = "nutrients";
	
	public static final String BATCH_BASE_PATH = "batches";
	
	public static final String TASK_BASE_PATH = "tasks";
	
	public static final String JOURNAL_LOCATIONS_BASE_PATH = "journal_locations";
	
	public static final String LOCATION_BASE_PATH = "location";

	
	public static final Uri NUTRIENT_CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" + NUTRIENT_BASE_PATH);
	
	public static final Uri BATCH_CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" + BATCH_BASE_PATH);
	
	public static final Uri TASK_CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" + TASK_BASE_PATH);
	
	public static final Uri LOCATION_CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" + LOCATION_BASE_PATH);
	
	public static final Uri JOURNAL_LOCATIONS_CONTENT_URI = Uri.parse("content://" +AUTHORITY + "/" + JOURNAL_LOCATIONS_BASE_PATH);
	
	
	
	public static final String LOCATION_CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/locations";
	public static final String LOCATION_CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/location";
	
	public static final String JOUNRAL_LOCATIONS_CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/journal_locations";
	public static final String JOURNAL_LOCATIONS_CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/journal_location";
	
	public static final String NUTRIENT_CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/nutrients";
	public static final String NUTRIENT_CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/nutrient";
	
	public static final String BATCH_CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/batches";
	public static final String BATCH_CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/batch";
	
	public static final String TASK_CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/tasks";
	public static final String TASK_CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/task";
	
	
	public static final String TABLE_JOURNAL_HISTORY = "journal_history";
	public static final String TABLE_JOURNAL_BATCHES = "journal_batches";
	public static final String TABLE_JOURNAL_TASKS = "journal_tasks";
	public static final String TABLE_JOURNAL_LOCATIONS = "journal_locations";
	public static final String TABLE_JOURNAL_ENTRIES = "journal_entries";
	
	public static final String TABLE_LOCATIONS = "location";
	public static final String TABLE_LOCATION_HISTORY = "location_history";
	
	public static final String TABLE_NUTRIENTS = "nutrient";
	public static final String TABLE_NUTRIENT_HISTORY = "nutrient_history";
	public static final String TABLE_NUTRIENT_COMPONENTS = "nutrient_compoments";
	
	public static final String TABLE_BATCHES = "batch";
	public static final String TABLE_BATCH_HISTORY = "batches_history";
	public static final String TABLE_BATCH_NUTRIENTS = "batch_nutrients";
	
	public static final String TABLE_TASKS = "task";
	public static final String TABLE_TASK_HISTORY = "task_history";
	public static final String TABLE_TASK_NEW = "task_new";
	
	
	
	
	/**	Create Tables	**/
	
				
	public static final String DATABASE_CREATE_JOURNAL_LOCATIONS = "create table "
		+ TABLE_JOURNAL_LOCATIONS + "("
		+ COL_ID + " integer primary key autoincrement, "
		+ COL_NAME + " text not null, "
		+ COL_LOCATION + " text not null)";
		
	// Create Nutrient Table
	public static final String DATABASE_CREATE_NUTRIENT = "create table "
		+ TABLE_NUTRIENTS + "("
		+ COL_ID + " integer primary key autoincrement, "
		+ COL_NAME + " text not null, "
		+ COL_DETAILS + " text not null)";
	
	public static final String DATABASE_DELETE = "drop table if exists item";
	
	
	// Nutrient Constants
	public static final int NUTRIENTS = 3;
	public static final int NUTRIENT_ID = 4;
	
	
	public static final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
	
	// Journal Projections
	public static final String[] projectionSummary = { COL_NAME, COL_DETAILS };
	public static final String[] projectionFull = { COL_ID, COL_NAME, COL_DETAILS };
	
	// static initialization ("class constructor")
	static {
		uriMatcher.addURI(AUTHORITY, JOURNAL_BASE_PATH, JOURNALS);
		uriMatcher.addURI(AUTHORITY, JOURNAL_BASE_PATH + "/#", JOURNAL_ID);
		
		uriMatcher.addURI(AUTHORITY, NUTRIENT_BASE_PATH, NUTRIENTS);
		uriMatcher.addURI(AUTHORITY, NUTRIENT_BASE_PATH + "/#", NUTRIENT_ID);
		
		
	}
	
	public DatabaseContract() {
		
	}
	
	public static final class Journals implements Columns { 
		public static final Uri contentUri = Uri.withAppendedPath(
				DatabaseContract.JOURNAL_CONTENT_URI, JOURNAL_BASE_PATH);
	}
	
	public static final class Nutrients implements Columns {
		public static final Uri contentUri = Uri.withAppendedPath(
				DatabaseContract.NUTRIENT_CONTENT_URI, NUTRIENT_BASE_PATH);
	}
}
