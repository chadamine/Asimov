package com.chadamine.growbuddy.database.tables;
import com.chadamine.growbuddy.database.DatabaseContract.JournalsHistory;
import android.database.sqlite.*;
public class JournalsHistoryTable
{
		public static final String CREATE = "create table "
		+ JournalsHistory.TABLE_NAME + "("
		+ JournalsHistory.COL_ID + " integer primary key autoincrement, "
		+ JournalsHistory.COL_NAME + " text not null, "
		+ JournalsHistory.COL_DATE + " text not null, "
		+ JournalsHistory.COL_ACTION + " text not null, "
		+ JournalsHistory.COL_ACTION_DETAILS + " text not null)";
	
		public static void onCreate(SQLiteDatabase db) {
			db.execSQL(CREATE);
		}

		public static void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			db.execSQL("DROP TABLE IF EXISTS " + JournalsHistory.TABLE_NAME);
			onCreate(db);
		}
}
