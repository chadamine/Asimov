package com.chadamine.growbuddy.database.tables;

import com.chadamine.growbuddy.database.DatabaseContract.Journals;

import android.database.sqlite.SQLiteDatabase;

public class JournalsTable {
	
	public static final String CREATE = "create table "
			+ Journals.TABLE_NAME + "("
			+ Journals.COL_ID + " integer primary key autoincrement, "
			+ Journals.COL_NAME + " text not null, "
			+ Journals.COL_LOCATION + " text not null)";
	
	public static void onCreate(SQLiteDatabase db) {
		db.execSQL(CREATE);
	}
	
	public static void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + Journals.TABLE_NAME);
		onCreate(db);
	}
}


