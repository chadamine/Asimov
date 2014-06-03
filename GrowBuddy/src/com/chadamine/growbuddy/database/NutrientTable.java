package com.chadamine.growbuddy.database;
import android.database.sqlite.*;
import android.database.*;
import android.database.SQLException.*;

public class NutrientTable
{
	public static void onCreate(SQLiteDatabase db) throws SQLException {
		db.execSQL(DatabaseContract.DATABASE_CREATE_JOURNALS);
	}

	public static void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) throws SQLException {
		db.execSQL("DROP TABLE IF EXISTS " + DatabaseContract.TABLE_NUTRIENTS);
		onCreate(db);
	}
}
