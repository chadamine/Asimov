package com.chadamine.growbuddy.adapters;

import java.util.HashMap;
import java.util.List;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.chadamine.growbuddy.R;

public class ExpandableListAdapter extends BaseExpandableListAdapter {

	private Context context;
	private List<String> listTitles;
	private HashMap<String, List<String>> listChildren;
	
	public ExpandableListAdapter(Context c, List<String> l, HashMap<String, List<String>> h) {
		context = c;
		listTitles = l;
		listChildren = h;
	}

	@Override
	public int getGroupCount() {
		// TODO Auto-generated method stub
		return listTitles.size();
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		// TODO Auto-generated method stub
		return listChildren.get(listTitles.get(groupPosition)).size();
	}

	@Override
	public Object getGroup(int groupPosition) {
		
		return listTitles.get(groupPosition);
	}

	@Override
	public Object getChild(int groupPosition, int childPosition) {
		return listChildren.get(listTitles.get(groupPosition)).get(childPosition);
	}

	@Override
	public long getGroupId(int groupPosition) {
		// TODO Auto-generated method stub
		return groupPosition;
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		return childPosition;
	}

	@Override
	public boolean hasStableIds() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {
	
		String headerTitle = (String) getGroup(groupPosition);
		
		if(convertView == null) {
			LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			
			convertView = inflater.inflate(R.layout.list_group, null);
		}
		
		TextView header = (TextView) convertView.findViewById(R.id.textNavItemTitle);
		header.setTypeface(null, Typeface.BOLD);
		header.setText(headerTitle);
		
		return null;
	}

	@Override
	public View getChildView(int groupPosition, int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {
		
		final String childText = (String) getChild(groupPosition, childPosition);
		
		if (convertView == null) {
			LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(R.layout.row_nav, null);
		}
		
		TextView txtListChild = (TextView) convertView.findViewById(R.id.textNavItemTitle);
		txtListChild.setText(childText);
	
		return convertView;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return true;
	}

}
