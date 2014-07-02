package com.chadamine.growbuddy.database.DatabaseHelpers;

import com.chadamine.growbuddy.database.DatabaseContract;
import com.chadamine.growbuddy.database.tables.NutrientsTable;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class NutrientsHelper extends SQLiteOpenHelper {
	
	public NutrientsHelper(Context c) {
		super(c, DatabaseContract.DATABASE_NAME, null, DatabaseContract.DB_VERSION);
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		NutrientsTable.onCreate(db);
	}
	
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		
	}
}
