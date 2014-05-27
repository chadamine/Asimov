package com.chadamine.growbuddy.cultivation;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.chadamine.growbuddy.R;
import com.chadamine.growbuddy.cultivation.plants.PlantsActivity;

public class CultivationListFragment extends ListFragment {
	

	@Override 
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View root = inflater.inflate(R.layout.fragment_cultivation_list, container, false);
		
		String[] values = new String[] { "Plants", "Nutrients", "Irrigation", "Genetics", "Pest & Disease" };
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), R.layout.nav_row, R.id.tvNavItemTitle, values);
		
	
		setListAdapter(adapter);
		return root;
		
		
	}
			
			private void setUp(View r) {
				
				final Activity activity = getActivity();
				
				
				ListView lvCultivation = getListView();
						/*(ListView) r.findViewById(R.id.lvCultivation);*/
				lvCultivation.setOnItemClickListener(new AdapterView.OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> parent, View view,
							int position, long id) {
						
						Intent intent;
						
						switch(position) {
						case 0:
							intent = new Intent(activity, PlantsActivity.class);
							break;
						case 1:
							break;
						case 2:
							break;
						case 3:
							break;
						case 4:
							break;
						case 5:
							break;
						}
					
						
					}
				});
			}
				
				
}
