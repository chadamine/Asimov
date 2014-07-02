package com.chadamine.growbuddy.database.DatabaseHelpers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.chadamine.growbuddy.database.DatabaseContract;
import com.chadamine.growbuddy.database.tables.JournalLocationsTable;

public class JournalLocationsHelper extends SQLiteOpenHelper {

	public JournalLocationsHelper(Context c) {
		super(c, DatabaseContract.DATABASE_NAME, null, DatabaseContract.DB_VERSION);
		
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		JournalLocationsTable.onCreate(db);	
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}

}
