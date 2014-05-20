package com.example.operationswapfrag.database;

import android.database.sqlite.SQLiteDatabase;

public class ItemTable {
	public static void onCreate(SQLiteDatabase db) {
		db.execSQL(ItemContract.DATABASE_CREATE);
	}
	
	public static void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + ItemContract.TABLE_ITEM);
		onCreate(db);
	}
}


