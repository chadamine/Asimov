package com.chadamine.growbuddy.journal;
import android.app.*;
import android.os.*;
import android.view.*;
import com.chadamine.growbuddy.*;
import android.support.v7.app.*;
import android.widget.*;

public class JournalListActivity extends ActionBarActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_journal_list);
		
		if(savedInstanceState == null) {
			getSupportFragmentManager()
			.beginTransaction()
			.add(R.id.journalListContainer, new JournalListFragment())
			.commit();	
		}
		
		
	}
	
	

}
