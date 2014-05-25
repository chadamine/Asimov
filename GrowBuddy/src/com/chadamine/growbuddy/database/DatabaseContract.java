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
	
	public static final String JOURNAL_BASE_PATH = "journals";
	public static final String NUTRIENT_BASE_PATH = "nutrients";

	public static final Uri JOURNAL_CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" + JOURNAL_BASE_PATH);
	public static final Uri NUTRIENT_CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" + NUTRIENT_BASE_PATH);
	
	public static final String JOURNAL_CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/journals";
	public static final String JOURNAL_CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/journal";
	
	public static final String NUTRIENT_CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/nutrients";
	public static final String NUTRIENT_CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/nutrient";
	
	public static final String TABLE_JOURNAL = "journal";
	public static final String TABLE_JOURNAL_HISTORY = "journalHistory";
	public static final String TABLE_JOURNAL_BATCHES = "journalBatches";
	public static final String TABLE_JOURNAL_TASKS = "journal_tasks";
	
	public static final String TABLE_NUTRIENT = "nutrient";
	public static final String TABLE_NUTRIENT_HISTORY = "nutrientHistory";
	public static final String TABLE_NUTRIENT_COMPONENTS = "nutrient_compoments";
	
	public static final String TABLE_BATCH = "batch";
	public static final String TABLE_BATCH_HISTORY = "batches_history";
	public static final String TABLE_BATCH_NUTRIENTS = "batch_nutrients";
	
	// Journal Columns
	public static final String COL_ID = "_id";
	public static final String COL_NAME = "name";
	public static final String COL_DETAILS = "details";
	
	public static final String COL_DATE_CREATED = "dateCreated";
	
	public static final String COL_LOCATION = "location";
	
	
	/**	Create Tables	**/
	
	// Create Journal Table
	public static final String DATABASE_CREATE_JOURNAL = "create table "
			+ TABLE_JOURNAL + "("
			+ COL_ID + " integer primary key autoincrement, "
			+ COL_NAME + " text not null, "
			+ COL_DETAILS + " text not null)";
			
	// Create Nutrient Table
	public static final String DATABASE_CREATE_NUTRIENT = "create table "
		+ TABLE_NUTRIENT + "("
		+ COL_ID + " integer primary key autoincrement, "
		+ COL_NAME + " text not null, "
		+ COL_DETAILS + " text not null)";
	
	public static final String DATABASE_DELETE = "drop table if exists item";
	
	// Journal Constants
	public static final int JOURNALS = 1;
	public static final int JOURNAL_ID = 2;
	
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
