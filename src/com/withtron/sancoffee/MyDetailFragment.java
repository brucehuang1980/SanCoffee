package com.withtron.sancoffee;


import com.withtron.sancoffee.R;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class MyDetailFragment extends Fragment {
	String[] m_item_detail = new String[5];
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		Log.d("MyDetailFragment", "onCreate");
		super.onCreate(savedInstanceState);
		m_item_detail[0] = getActivity().getString(R.string.my_best_drink_date);
		m_item_detail[1] = getActivity().getString(R.string.my_beans_introduction);
		m_item_detail[2] = getActivity().getString(R.string.my_manor_introduction);
		m_item_detail[3] = getActivity().getString(R.string.my_suggestion);
		m_item_detail[4] = getActivity().getString(R.string.my_rating);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		Log.d("MyDetailFragment", "onCreateView");
		View view = inflater.inflate(R.layout.relative_layout, container, false);
		RelativeLayout rl = (RelativeLayout)view.findViewById(R.id.relative_layout_container);
		ListView listview = new ListView(getActivity());
		rl.addView(listview);
		
		listview.setAdapter(new BaseAdapter(){

			@Override
			public int getCount() {
				// TODO Auto-generated method stub
				return m_item_detail.length;
			}

			@Override
			public Object getItem(int arg0) {
				// TODO Auto-generated method stub
				return m_item_detail[arg0];
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
				View view = inflater.inflate(R.layout.list_row_no_image, null);
				TextView textView = (TextView)view.findViewById(R.id.no_image_title);
				textView.setText(m_item_detail[position]);
				return view;
			}
			
		});
		
		listview.setOnItemClickListener(new OnItemClickListener(){
			@Override
			public void onItemClick(AdapterView parent, View view, int position,long id) {
				// TODO Auto-generated method stub
				Log.d("MyDetailFragment", "onItemClick");
        		ImageView image = (ImageView)getActivity().findViewById(R.id.titel_bar_image);
        		image.setVisibility(View.GONE);
        		ImageView back_image = (ImageView)getActivity().findViewById(R.id.titel_bar_back_image);
        		back_image.setVisibility(View.VISIBLE);

        		FragmentTransaction ft = getFragmentManager().beginTransaction();
				if (position == 0) {// 
					ft.replace(R.id.relative_layout_container, new BestDrinkDateFragment());
				}
				else if (position == 1) {// 
					ft.replace(R.id.relative_layout_container, new BeansInstroductionFragment());
				}
				else if (position == 2) {// 
					ft.replace(R.id.relative_layout_container, new ManorInstroductionFragment());
				}
				else if (position == 3) {// 
					ft.replace(R.id.relative_layout_container, new SuggestionFragment());
				}
				else if (position == 4) {
					ft.replace(R.id.relative_layout_container, new RatingFragment());
				}
        		
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                ft.addToBackStack(null);
                ft.commit();
			}});
		
		ImageView back_image = (ImageView)getActivity().findViewById(R.id.titel_bar_back_image);
		back_image.setOnClickListener(new View.OnClickListener() {
        	public void onClick(View view) {
        		getFragmentManager().popBackStack();
        	}
        });
		return view;
	}
	
	@Override
	public void onDestroyView() {
		// TODO Auto-generated method stub
		Log.d("MyDetailFragment", "onDestroyView");
		super.onDestroyView();
		ImageView back_image = (ImageView)getActivity().findViewById(R.id.titel_bar_back_image);
		back_image.setVisibility(View.GONE);	
		ImageView image = (ImageView)getActivity().findViewById(R.id.titel_bar_image);
		image.setVisibility(View.VISIBLE);
	}
}
