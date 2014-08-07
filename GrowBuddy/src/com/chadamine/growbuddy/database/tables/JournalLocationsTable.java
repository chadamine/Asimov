package com.chadamine.growbuddy.database.tables;

import android.database.sqlite.SQLiteDatabase;
import com.chadamine.growbuddy.database.DatabaseContract.JournalLocations;

public class JournalLocationsTable {

		public static final String CREATE = "create table "
				+ JournalLocations.TABLE_NAME + "("
				+ JournalLocations.COL_ID + " integer primary key autoincrement, "
				+ JournalLocations.COL_NAME + " text not null, "
				+ JournalLocations.COL_LOCATION + " text not null)";

		public static void onCreate(SQLiteDatabase db) {
			//db.execSQL(CREATE);
		}
		
		public static void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			//db.execSQL("DROP TABLE IF EXISTS " + JournalLocations.TABLE_NAME);
			onCreate(db);
		}
}
