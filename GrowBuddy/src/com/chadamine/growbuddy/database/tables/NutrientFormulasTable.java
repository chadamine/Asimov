package com.chadamine.growbuddy.database.tables;

import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.Fragment;

import com.chadamine.growbuddy.database.DatabaseContract.NutrientFormulas;

public class NutrientFormulasTable extends Fragment {
	public static final String CREATE = "create table "
			+  NutrientFormulas.TABLE_NAME + " text not null" + 
			
			")";
		
		public static void onCreate(SQLiteDatabase db) throws SQLException {
			db.execSQL(CREATE);
		}

		public static void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) throws SQLException {
			db.execSQL("DROP TABLE IF EXISTS " + NutrientFormulas.TABLE_NAME);
			onCreate(db);
		}
}
