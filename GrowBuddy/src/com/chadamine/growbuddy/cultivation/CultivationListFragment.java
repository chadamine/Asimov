package com.chadamine.growbuddy.cultivation;
import android.support.v4.app.*;
import android.view.*;
import android.os.*;
import com.chadamine.growbuddy.*;
import android.widget.*;

public class CultivationListFragment extends ListFragment {
	@Override 
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View root = inflater.inflate(R.layout.fragment_cultivation, container, false);
		
		String[] values = new String[] { "Plants", "Nutrients", "Irrigation", "Genetics", "Pest & Disease" };
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), R.layout.nav_row, R.id.tvNavItemTitle, values);
		
		setListAdapter(adapter);
		return root;
		
		
	}
}
