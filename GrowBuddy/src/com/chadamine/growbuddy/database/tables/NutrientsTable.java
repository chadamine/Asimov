package com.chadamine.growbuddy.database.tables;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.chadamine.growbuddy.database.DatabaseContract.Nutrients;

public class NutrientsTable
{
	public static final String CREATE = "create table "
		+ Nutrients.TABLE_NAME + "("
		+ Nutrients.COL_ID + " integer primary key autoincrement, "
		+ Nutrients.COL_NAME + " text not null, "
		+ Nutrients.COL_MANUFACTURER + " text not null, "
		+ Nutrients.COL_PRODUCT + " text not null, "
		+ Nutrients.COL_TYPE + " text not null, "
		+ Nutrients.COL_MOL_WEIGHT + " text not null, "
		+ Nutrients.COL_DENSITY + " text not null, "
		+ Nutrients.COL_PHASE + " text not null)";
	
	public static void onCreate(SQLiteDatabase db) throws SQLException {
		db.execSQL(CREATE);
	}

	public static void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) throws SQLException {
		db.execSQL("DROP TABLE IF EXISTS " + Nutrients.TABLE_NAME);
		onCreate(db);
	}
}
