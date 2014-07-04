package com.chadamine.growbuddy.database.tables;

import com.chadamine.growbuddy.database.DatabaseContract.Locations;

import android.database.sqlite.SQLiteDatabase;


public class LocationsTable {

		public static final String CREATE = "create table "
		+ Locations.TABLE_NAME + "("
		+ Locations.COL_ID + " integer primary key autoincrement, "
		+ Locations.COL_NAME + " text not null, "
		+ Locations.COL_ADDRESS + " text not null)";

		public static void onCreate(SQLiteDatabase db) {
			db.execSQL(CREATE);
		}

		public static void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			db.execSQL("DROP TABLE IF EXISTS " + Locations.TABLE_NAME);
			onCreate(db);
		}
}
