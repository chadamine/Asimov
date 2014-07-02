package com.chadamine.growbuddy.database.DatabaseHelpers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.chadamine.growbuddy.database.DatabaseContract;
import com.chadamine.growbuddy.database.tables.JournalsTable;
import com.chadamine.growbuddy.database.tables.NutrientsTable;

public class DatabaseHelper extends SQLiteOpenHelper {
	
	SQLiteDatabase database;

	public DatabaseHelper(Context context) {
		super(context, DatabaseContract.DATABASE_NAME, null, DatabaseContract.DB_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		database = db;
		//JournalsTable.onCreate(db);
		//Log.d("helperCreated", "journal helper created");
		JournalsTable.onCreate(db);
		Log.d("tableCreated", "journals table created in helper");
		
		//NutrientsTable.onCreate(db);
		db.execSQL(NutrientsTable.CREATE);
		Log.d("tableCreated", "nutrients table created in helper");
	}
	
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}
}
