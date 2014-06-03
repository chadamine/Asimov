package com.chadamine.growbuddy.database.tables;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.chadamine.growbuddy.database.DatabaseContract.Nutrients;

public class TableNutrients
{
	public static final String CREATE = "create table "
		+ Nutrients.TABLE_NAME + "("
		+ Nutrients.COL_ID + " integer primary key autoincrement, "
		+ Nutrients.COL_NAME + " text not null, "
		+ Nutrients.COL_DETAILS + " text not null)";
	
	public static void onCreate(SQLiteDatabase db) throws SQLException {
		db.execSQL(CREATE);
	}

	public static void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) throws SQLException {
		db.execSQL("DROP TABLE IF EXISTS " + Nutrients.TABLE_NAME);
		onCreate(db);
	}
}
