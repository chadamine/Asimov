package com.chadamine.growbuddy.database;

import android.database.sqlite.SQLiteDatabase;

public class JournalTable {
	public static void onCreate(SQLiteDatabase db) {
		db.execSQL(DatabaseContract.DATABASE_CREATE_JOURNALS);
	}
	
	public static void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + DatabaseContract.TABLE_JOURNALS);
		onCreate(db);
	}
}


