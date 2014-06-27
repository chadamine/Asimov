package com.chadamine.growbuddy.database.tables;

import com.chadamine.growbuddy.database.DatabaseContract.NutrientSolubilities;

import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.Fragment;

public class NutrientSolubilitiesTable extends Fragment {
	public static final String CREATE = "create table "
			+ NutrientSolubilities.TABLE_NAME + " text not null, " 
			+ NutrientSolubilities.COL_0 + " int, "
			+
			")";
		
		public static void onCreate(SQLiteDatabase db) throws SQLException {
			db.execSQL(CREATE);
		}

		public static void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) throws SQLException {
			db.execSQL("DROP TABLE IF EXISTS " + NutrientSolubilities.TABLE_NAME);
			onCreate(db);
		}
}
