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
			
			//Toast.makeText(this, "first frag added", Toast.LENGTH_SHORT).show();
			
		}
		
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu m) {
		getMenuInflater().inflate(R.menu.journal_list, m);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem i) {
		
		int id = i.getItemId();
		
		int add = R.id.addJournal;
		int del = R.id.delJournal;
		
		//if (id == add) {
			getSupportFragmentManager().beginTransaction().replace(R.id.journalListContainer, new JournalDetailsFragment()).commit();
		//Toast.makeText(this, "second frag added", Toast.LENGTH_SHORT).show();
			//return true;
		//}
		
		//if (id == del) {
			
		//}
		
		
		return super.onOptionsItemSelected(i);
	}

}
