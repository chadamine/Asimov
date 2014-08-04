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
	
	public DatabaseContract() {}

	public static final String DATABASE_NAME = "growbuddy.db";
	public static final int DB_VERSION = 1;
	public static final String AUTHORITY = "com.chadamine.growbuddy.provider";
	public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY);
	
	//public static final String NUTRIENT_AUTHORITY = "com.chadamine.growbuddy.nutrients_provider";
	//public static final Uri NUTRIENT_CONTENT_URI = Uri.parse("content://" + NUTRIENT_AUTHORITY);
	
	//	Journals
	public static final class Journals implements Columns { 
		
		public static final String TABLE_NAME = "journals";
		public static final Uri CONTENT_URI = Uri.withAppendedPath(DatabaseContract.CONTENT_URI, TABLE_NAME);
		
		// mime type of directory of items
		public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/journals";
		
		// mime type of single item
		public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/journals";
		
		public static final String COL_ID = "_id";
		public static final String COL_NAME = "name";	
		public static final String COL_LOCATION = "location";
	}
	
	public static final class JournalsHistory implements Columns { 
	
		public static final String TABLE_NAME = "journals_history";
		public static final Uri CONTENT_URI = Uri.withAppendedPath(DatabaseContract.CONTENT_URI, TABLE_NAME);

		// mime type of directory of items
		public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/journals_history";

		// mime type of single item
		public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/journals_history";
		
		public static final String COL_ID = "_id";
		public static final String COL_NAME = "name";
		public static final String COL_DATE = "date";
		public static final String COL_ACTION = "action";
		public static final String COL_ACTION_DETAILS = "action_details";
	}
	
	//	Locations
	public static final class Locations implements Columns {
		
		public static final String TABLE_NAME = "locations";
		public static final Uri CONTENT_URI = Uri.withAppendedPath(DatabaseContract.CONTENT_URI, TABLE_NAME);
		public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + TABLE_NAME;
		public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/locations";
		
		public static final String COL_ID = "_id";
		public static final String COL_TYPE = "type";
		public static final String COL_NAME = "name";
		public static final String COL_DETAILS = "details";
		public static final String COL_DATE_CREATED = "date_created";
		public static final String COL_ADDRESS = "address";
		public static final String COL_BATCH = "batch";
		
	}
	
	/*
	//	Journal Locations
	public static final class JournalLocations implements Columns {
		
		
		public static final String TABLE_NAME = "journal_locations";
		public static final Uri CONTENT_URI = Uri.withAppendedPath(DatabaseContract.CONTENT_URI, TABLE_NAME);
		public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + TABLE_NAME;
		public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/journal_location";
		
		public static final String COL_ID = "_id";
		public static final String COL_NAME = "name";
		public static final String COL_DETAILS = "details";
		public static final String COL_DATE_CREATED = "date_created";
		public static final String COL_LOCATION = "location";
		public static final String COL_BATCH = "batch";
		public static final String COL_TYPE = "type";
		
		}
	
	*/
	//	Nutrients
	public static final class Nutrients implements Columns {
		
		public static final String TABLE_NAME = "nutrients";
		public static final Uri CONTENT_URI = Uri.withAppendedPath(DatabaseContract.CONTENT_URI, TABLE_NAME);
		public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/nutrients";
		public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/nutrients";
		
		public static final String COL_ID = "_id";
		public static final String COL_NAME = "name";
		public static final String COL_MANUFACTURER = "manufacturer";
		public static final String COL_PRODUCT = "product";
		public static final String COL_MOL_WEIGHT = "mol_weight";
		public static final String COL_DENSITY = "density";
		public static final String COL_PHASE = "phase";
		public static final String COL_DATE_CREATED = "date_created";
		public static final String COL_TYPE = "type";
	
	}
	
//	Nutrient Formulas
	public static final class NutrientFormulas implements Columns {
		
		public static final String TABLE_NAME = "nutrient_formulas";
		public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" + TABLE_NAME);
		public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/nutrient_formulas";
		public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/nutrient_formulas";
		
		public static final String COL_ID = "_id";
		public static final String COL_NAME = "name";	// Foreign Key
		public static final String COL_C = "C";
		public static final String COL_H = "H";
		public static final String COL_O = "O";
		public static final String COL_N = "N";
		public static final String COL_P = "P";
		public static final String COL_K = "K";
		public static final String COL_CA = "Ca";
		public static final String COL_MG = "Mg";
		public static final String COL_SI = "Si";
	
		public static final Uri contentUri = Uri.withAppendedPath(CONTENT_URI, TABLE_NAME);
	}
	
//	Nutrients
	public static final class NutrientSolubilities implements Columns {
		
		public static final String TABLE_NAME = "nutrient_solubilities";
		public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" + TABLE_NAME);
		public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/nutrient_solubilities";
		public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/nutrient_solubilities";
		
		public static final String COL_ID = "_id";
		public static final String COL_NAME = "name";
		public static final String COL_0 = "0";
		public static final String COL_10 = "10";
		public static final String COL_20 = "20";
		public static final String COL_25 = "25";
		public static final String COL_30 = "30";
		public static final String COL_40 = "40";
		public static final String COL_50 = "50";
		public static final String COL_60 = "60";
		public static final String COL_70 = "70";
		public static final String COL_80 = "80";
		public static final String COL_90 = "90";
		public static final String COL_100 = "100";
				
		public static final String COL_TYPE = "type";
	
		public static final Uri contentUri = Uri.withAppendedPath(CONTENT_URI, TABLE_NAME);
	}
	
	
	// Nutrient Components
		public static final class NutrientComponents implements Columns {
			
			public static final String BASE_PATH = "nutrients";
			public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" + BASE_PATH);
			public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/nutrients";
			public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/nutrient";
			public static final String TABLE_NAME = "nutrient_compoments";
			
			public static final String COL_ID = "_id";
			public static final String COL_NAME = "name";
			public static final String COL_DETAILS = "details";
			public static final String COL_DATE_CREATED = "date_created";
			public static final String COL_LOCATION = "location";
			public static final String COL_BATCH = "batch";
			public static final String COL_TYPE = "type";
			
			public static final String DATABASE_CREATE_NUTRIENT_COMPONENTS = "create table "
					+ TABLE_NAME + "("
					+ COL_ID + " integer primary key autoincrement, "
					+ COL_NAME + " text not null, "
					+ COL_DETAILS + " text not null)";
			
			public static final Uri contentUri = Uri.withAppendedPath(
					CONTENT_URI, 
					BASE_PATH);
		}

	//	Batches
	public static final class Batches implements Columns {
		
		public static final String BASE_PATH = "batches";
		public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" + BASE_PATH);
		public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/batches";
		public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/batch";
		
		public static final String COL_ID = "_id";
		public static final String COL_NAME = "name";
		public static final String COL_DETAILS = "details";
		public static final String COL_DATE_CREATED = "date_created";
		public static final String COL_LOCATION = "location";
		public static final String COL_BATCH = "batch";
		public static final String COL_TYPE = "type";
		
		public static final String TABLE_NAME = "batches";
		
		
		public static final Uri contentUri = Uri.withAppendedPath(
				CONTENT_URI, 
				BASE_PATH);
	}
	

	
	//	Plants
	public static final class Plants implements Columns {
		
		public static final String BASE_PATH = "plants";
		public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" + BASE_PATH);
		public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/plants";
		public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/plant";
		public static final String TABLE_NAME = "plants";
		
		public static final String COL_ID = "_id";
		public static final String COL_NAME = "name";
		public static final String COL_SPECIES = "species";
		public static final String COL_DATE_CREATED = "date_created";
		public static final String COL_LOCATION = "location";
		public static final String COL_BATCH = "batch";
		public static final String COL_TYPE = "type";
		public static final String COL_ORIGIN = "origin";
		public static final String COL_SOURCE = "source";
		public static final String COL_PROPAGULE = "propagule";
		public static final String COL_MOTHER = "mother";
		public static final String COL_PROP_TIME = "propagation_time";
		public static final String COL_SEASON = "season";
		public static final String COL_HARVEST_DAYS = "harvest_days";
		public static final String COL_SUN_SHADE = "sun_shade";
		public static final String COL_INDOOR_OUTDOOR = "indoor_outdoor";
		
		
		public static final Uri contentUri = Uri.withAppendedPath(
				CONTENT_URI, 
				BASE_PATH);
	}
	
	
	public static final class Species implements Columns {

		public static final String BASE_PATH = "plants";
		public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" + BASE_PATH);
		public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/plants";
		public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/plant";
		public static final String TABLE_NAME = "plants";

		public static final String COL_ID = "_id";
		public static final String COL_NAME = "name";
		public static final String COL_SPECIES = "species";
		public static final String COL_LOCATION = "location";
		public static final String COL_BATCH = "batch";
		public static final String COL_TYPE = "type";
		public static final String COL_ORIGIN = "origin";
		public static final String COL_SOURCE = "source";
		public static final String COL_MOTHER = "mother";
		public static final String COL_PROP_TIME = "propagation_time";
		public static final String COL_SEASON = "season";
		public static final String COL_HARVEST_DAYS = "harvest_days";
		public static final String COL_SUN_SHADE = "sun_shade";
		public static final String COL_INDOOR_OUTDOOR = "indoor_outdoor";


		public static final Uri contentUri = Uri.withAppendedPath(
			CONTENT_URI, 
			BASE_PATH);
	}
	// Batch Plants

	
	public static final class BatchPlants implements Columns {
		
		public static final String BASE_PATH = "batch_plants";
		public static final Uri  CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" +  BASE_PATH);
		public static final String  CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/batch_plants";
		public static final String  CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/batch_plant";
		public static final String  TABLE_NAME = "batch_plants";
		
		public static final String COL_ID = "_id";
		public static final String COL_NAME = "name";
		public static final String COL_DETAILS = "details";
		public static final String COL_DATE_CREATED = "date_created";
		public static final String COL_LOCATION = "location";
		public static final String COL_BATCH = "batch";
		public static final String COL_TYPE = "type";
		
		public static final String DATABASE_CREATE_BATCH_PLANTS = "create table "
				+ TABLE_NAME + "("
				+ COL_ID + " integer primary key autincrement, "
				+ COL_NAME + " text not null)";
		
		public static final Uri contentUri = Uri.withAppendedPath(
				CONTENT_URI, 
				BASE_PATH);
	}
			
	
	// 	Tasks
	public static final class Tasks implements Columns {
		
		public static final String BASE_PATH = "tasks";
		public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" + BASE_PATH);
		public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/tasks";
		public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/task";
		public static final String TABLE_NAME = "task";
		
		public static final String COL_ID = "_id";
		public static final String COL_NAME = "name";
		public static final String COL_DETAILS = "details";
		public static final String COL_DATE_CREATED = "date_created";
		public static final String COL_LOCATION = "location";
		public static final String COL_BATCH = "batch";
		public static final String COL_TYPE = "type";
		
		public static final String DATABASE_CREATE_TASK = "create table "
				+ TABLE_NAME + "("
				+ COL_ID + " integer primary key autoincrement, "
				+ COL_NAME + " text not null)";
		
		public static final Uri contentUri = Uri.withAppendedPath(
				CONTENT_URI, 
				BASE_PATH);
	}
	
	public static final class Measurements implements Columns {
		
		public static final String BASE_PATH = "measurements";
		public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" + BASE_PATH);
		public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/measurements";
		public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/measurement";
		public static final String TABLE_NAME = "task";
		
		public static final String COL_ID = "_id";
		public static final String COL_NAME = "name";
		public static final String COL_DETAILS = "details";
		public static final String COL_DATE_CREATED = "date_created";
		public static final String COL_LOCATION = "location";
		public static final String COL_BATCH = "batch";
		public static final String COL_TYPE = "type";
		public static final String COL_VALUE = "value";
		
		public static final String DATABASE_CREATE_TASK = "create table "
				+ TABLE_NAME + "("
				+ COL_ID + " integer primary key autoincrement, "
				+ COL_NAME + " text not null)";
		
		public static final Uri contentUri = Uri.withAppendedPath(
				CONTENT_URI, 
				BASE_PATH);
	}
	
	
	public static final String TABLE_JOURNAL_BATCHES = "journal_batches";
	public static final String TABLE_JOURNAL_TASKS = "journal_tasks";
	public static final String TABLE_JOURNAL_ENTRIES = "journal_entries";
	
	public static final String TABLE_BATCH_NUTRIENTS = "batch_nutrients";
	
	// _ID's MUST be EVEN (and nothing can be 0) !!! -- see GrowJournalPlusContentProvider delete() method 
	public static final int JOURNALS = 1;
	public static final int JOURNALS_ID = 2;
	public static final int JOURNALS_HISTORY = 19;
	public static final int JOURNALS_HISTORY_ID = 20;
	//public static final int JOURNAL_LOCATIONS = 5;
	//public static final int JOURNAL_LOCATIONS_ID = 6;
	
	public static final int LOCATIONS = 3;
	public static final int LOCATIONS_ID = 4;
	
	public static final int NUTRIENTS = 7;
	public static final int NUTRIENTS_ID = 8;
	public static final int NUTRIENT_FORMULAS = 21;
	public static final int NUTRIENT_FORMULA_ID = 22;
	public static final int NUTRIENT_SOLUBILITIES = 23;
	public static final int NUTRIENT_SOLUBILITY_ID = 24;
	public static final int NUTRIENT_COMPONENTS = 9;
	public static final int NUTRIENT_COMPONENTS_ID = 10;
	
	public static final int BATCHES = 11;
	public static final int BATCHES_ID = 12;
	public static final int BATCH_PLANTS = 15;
	public static final int BATCH_PLANTS_ID = 16;
	
	public static final int PLANTS = 13;
	public static final int PLANTS_ID = 14;
	
	public static final int TASKS = 17;
	public static final int TASKS_ID = 18;
	
	public static final UriMatcher URI_MATCHER = new UriMatcher(UriMatcher.NO_MATCH);
	// static initialization ("class constructor")
	static {
		
		URI_MATCHER.addURI(AUTHORITY, Journals.TABLE_NAME, JOURNALS);
		URI_MATCHER.addURI(AUTHORITY, Journals.TABLE_NAME + "/#", JOURNALS_ID);
		URI_MATCHER.addURI(AUTHORITY, JournalsHistory.TABLE_NAME, JOURNALS_HISTORY);
		URI_MATCHER.addURI(AUTHORITY, JournalsHistory.TABLE_NAME + "/#", JOURNALS_HISTORY_ID);
		//URI_MATCHER.addURI(AUTHORITY, JournalLocations.TABLE_NAME, JOURNAL_LOCATIONS);
		//URI_MATCHER.addURI(AUTHORITY, JournalLocations.TABLE_NAME + "/#", JOURNAL_LOCATIONS);
		
		URI_MATCHER.addURI(AUTHORITY, Nutrients.TABLE_NAME, NUTRIENTS);
		URI_MATCHER.addURI(AUTHORITY, Nutrients.TABLE_NAME + "/#", NUTRIENTS_ID);
		URI_MATCHER.addURI(AUTHORITY, NutrientFormulas.TABLE_NAME, NUTRIENT_FORMULAS);
		URI_MATCHER.addURI(AUTHORITY, NutrientFormulas.TABLE_NAME + "/#", NUTRIENT_FORMULA_ID);
		URI_MATCHER.addURI(AUTHORITY, NutrientSolubilities.TABLE_NAME, NUTRIENT_SOLUBILITIES);
		URI_MATCHER.addURI(AUTHORITY, NutrientSolubilities.TABLE_NAME + "/#", NUTRIENT_SOLUBILITY_ID);
		
		URI_MATCHER.addURI(AUTHORITY, Locations.TABLE_NAME, LOCATIONS);
		URI_MATCHER.addURI(AUTHORITY, Locations.TABLE_NAME + "/#", LOCATIONS_ID);
	}
	
	
	
	
	

}
