package com.chadamine.growbuddy.journal;
import android.content.*;
import android.content.pm.*;
import android.os.*;
import android.provider.*;
import android.support.v4.app.*;
import android.support.v7.app.*;
import android.widget.*;
import com.chadamine.growbuddy.*;
import java.io.*;
import android.view.*;


public class JournalListActivity extends ActionBarActivity {
	
	FragmentManager manager;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_journal_list);
		
		manager = getSupportFragmentManager();
		if(savedInstanceState == null) {
			//getSupportFragmentManager()
			manager
			.beginTransaction()
			.add(R.id.journalListContainer, new JournalListFragment())
			.commit();	
		}
		
		//checkHardware();
	}
	
	@Override
	public void onBackPressed() {
		//swap();
		if(manager.findFragmentById(R.id.journalListContainer) instanceof JournalDetailsFragment)
			manager.beginTransaction().replace(R.id.journalListContainer, new JournalListFragment()).commit();
		else
			finish();
	}


	
	
}
