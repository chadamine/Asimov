package com.chadamine.growbuddy.database;

import android.database.sqlite.SQLiteDatabase;

public class ItemTable {
	public static void onCreate(SQLiteDatabase db) {
		db.execSQL(JournalContract.DATABASE_CREATE);
	}
	
	public static void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + JournalContract.TABLE_ITEM);
		onCreate(db);
	}
}


