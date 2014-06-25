package com.chadamine.growbuddy.journal;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBarActivity;

import com.chadamine.growbuddy.R;


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
