package com.withtron.sancoffee;


import com.withtron.sancoffee.R;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class SetupFragment extends Fragment {
	String[] m_list_setup = new String[4];
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		Log.d("SetupFragment", "onCreate");
		super.onCreate(savedInstanceState);
		m_list_setup[0] = getActivity().getString(R.string.setup_auto_remind);
		m_list_setup[1] = getActivity().getString(R.string.setup_logout);
		m_list_setup[2] = getActivity().getString(R.string.setup_copyright);
		m_list_setup[3] = getActivity().getString(R.string.setup_about_us);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		Log.d("SetupFragment", "onCreateView");
		View view = inflater.inflate(R.layout.activity_setup, container, false);
		ListView listview = (ListView)view.findViewById(R.id.listview_setup);
		listview.setAdapter(new BaseAdapter(){

			@Override
			public int getCount() {
				// TODO Auto-generated method stub
				return m_list_setup.length;
			}

			@Override
			public Object getItem(int arg0) {
				// TODO Auto-generated method stub
				return m_list_setup[arg0];
			}

			@Override
			public long getItemId(int arg0) {
				// TODO Auto-generated method stub
				return arg0;
			}

			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				// TODO Auto-generated method stub
				LayoutInflater inflater = (LayoutInflater)getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				View view = inflater.inflate(R.layout.list_row_setup, null);
				TextView textView = (TextView)view.findViewById(R.id.setup_title);
				textView.setText(m_list_setup[position]);
				return view;
			}
			
		});
		return view;
	}
}
