package com.chadamine.growbuddy.database.tables;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.chadamine.growbuddy.database.DatabaseContract.Nutrients;

public class NutrientsTable
{
	public static final String CREATE = "create table "
		+ Nutrients.TABLE_NAME + "("
		+ Nutrients.COL_ID + " integer primary key autoincrement, "
		+ Nutrients.COL_MANUFACTURER + " text, "
		+ Nutrients.COL_PRODUCT + " text"
		//+ Nutrients.COL_TYPE + " text, "
		//+ Nutrients.COL_MOL_WEIGHT + " text, "
		//+ Nutrients.COL_DENSITY + " text, "
		//+ Nutrients.COL_PHASE + " text)";
		+ ")";
	
	public static void onCreate(SQLiteDatabase db) throws SQLException {
		db.execSQL(CREATE);
		Log.d("tableCreated", "nutrients table created");
	}

	public static void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) throws SQLException {
		db.execSQL("DROP TABLE IF EXISTS " + Nutrients.TABLE_NAME);
		onCreate(db);
	}
}
