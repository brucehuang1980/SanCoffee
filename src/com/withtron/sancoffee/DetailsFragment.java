package com.withtron.sancoffee;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import com.withtron.sancoffee.NewsFragment.NewsData;
import com.withtron.sancoffee.NewsFragment.NewsItem;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class DetailsFragment extends Fragment {
    /**
     * Create a new instance of DetailsFragment, initialized to
     * show the text at 'index'.
     */
	static boolean show_DetailsFragment = false; // ISSUE, why receive user click event after setTransition
	static LinkedHashMap<String, ArrayList<NewsItem>> m_list;
	static int m_position = 0;
	ImageView m_left_image;
	ImageView m_right_image;
	LinearLayout m_ll;
    public static DetailsFragment newInstance(LinkedHashMap<String, ArrayList<NewsItem>> map, int position ) {
    	Log.d("DetailsFragment", "newInstance");
    	
    	m_list = map;
    	m_position = position -1;
    	
        DetailsFragment f = new DetailsFragment();
        return f;
    }

    public int getShownIndex() {
    	Log.d("DetailsFragment", "getShownIndex");
        return getArguments().getInt("index", 0);
    }
    
    public void updateView(){
    	Log.d("DetailsFragment", "updateView");
    	m_ll.removeAllViews();
        List keys = new ArrayList(m_list.keySet());
        ArrayList<NewsItem> array = m_list.get(keys.get(m_position));
        for (int i=0;i<array.size();i++){
        	if (array.get(i).data_type == 1) {// pic
        		ImageView iv = new ImageView(getActivity());
        		Uri imgUri = Uri.parse("file:///data/data/com.withtron.sancoffee/files/" + keys.get(m_position) + "_" + array.get(i).data_content.substring( array.get(i).data_content.lastIndexOf('/')+1, array.get(i).data_content.length()));
        		iv.setImageURI(imgUri);
        		m_ll.addView(iv);
        	}
        	else if (array.get(i).data_type == 2){ // text
        		TextView tv = new TextView(getActivity());
        		tv.setText(array.get(i).data_content);
        		tv.setTextColor(android.graphics.Color.WHITE);
        		m_ll.addView(tv);
        	}
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
    	Log.d("DetailsFragment", "onCreateView");
        super.onCreate(savedInstanceState);
       
        show_DetailsFragment = true;
        View view = inflater.inflate(R.layout.item_detail, container, false);
        m_ll = (LinearLayout)view.findViewById(R.id.item_detail_container);
        updateView();
        
		ImageView back_image = (ImageView)getActivity().findViewById(R.id.titel_bar_back_image);
		back_image.setOnClickListener(new View.OnClickListener() {
        	public void onClick(View view) {
        		getFragmentManager().popBackStack();
        	}
        });
		m_left_image = (ImageView)view.findViewById(R.id.image_left_arrow);
		m_left_image.setOnClickListener(new View.OnClickListener() {
        	public void onClick(View view) {
        		m_position--;
        		updateView();
        		ShowHideArrowImage();
        	}
        });
		m_right_image = (ImageView)view.findViewById(R.id.image_right_arrow);
		m_right_image.setOnClickListener(new View.OnClickListener() {
        	public void onClick(View view) {
        		m_position++;
        		updateView();
        		ShowHideArrowImage();
        	}
        });
		ShowHideArrowImage();
        return view;
    }
    
    private void ShowHideArrowImage()
    {
    	Log.d("DetailsFragment", "ShowHideArrowImage");
		if (m_position == 0) 
			m_left_image.setVisibility(View.GONE);
		else
			m_left_image.setVisibility(View.VISIBLE);
		if (m_position == m_list.size()-1) 
			m_right_image.setVisibility(View.GONE);
		else
			m_right_image.setVisibility(View.VISIBLE);
    }

	@Override
	public void onDestroyView() {
		// TODO Auto-generated method stub
		Log.d("DetailsFragment", "onDestroyView");
		super.onDestroyView();
		show_DetailsFragment = false;
		ImageView back_image = (ImageView)getActivity().findViewById(R.id.titel_bar_back_image);
		back_image.setVisibility(View.GONE);	
		ImageView image = (ImageView)getActivity().findViewById(R.id.titel_bar_image);
		image.setVisibility(View.VISIBLE);
	}

}