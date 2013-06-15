package com.withtron.sancoffee;

import java.util.ArrayList;
import java.util.HashMap;

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
import android.widget.ScrollView;
import android.widget.TextView;

public class DetailsFragment extends Fragment {
    /**
     * Create a new instance of DetailsFragment, initialized to
     * show the text at 'index'.
     */
	static boolean show_DetailsFragment = false; // ISSUE, why receive user click event after setTransition
	static ArrayList<HashMap<String, String>> m_list;
	static int m_position = 0;
	TextView m_tv;
	ImageView m_iv;
	ImageView m_left_image;
	ImageView m_right_image;
    public static DetailsFragment newInstance(ArrayList<HashMap<String, String>> list, int position ) {
    	Log.d("DetailsFragment", "newInstance");
    	
    	m_list = list;
    	m_position = position;
    	
        DetailsFragment f = new DetailsFragment();
        return f;
    }

    public int getShownIndex() {
        return getArguments().getInt("index", 0);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
    	Log.d("DetailsFragment", "onCreateView");
        super.onCreate(savedInstanceState);
        
        show_DetailsFragment = true;
        View view = inflater.inflate(R.layout.item_detail, container, false);
        m_tv = (TextView) view.findViewById(R.id.item_detail_title);
        m_tv.setText(m_list.get(m_position-1).get(SqlOpenHelper.NEWS_COLUMN_TITLE));
        m_iv = (ImageView) view.findViewById(R.id.item_detail_image);
        m_iv.setImageURI(Uri.parse("file:" + (m_list.get(m_position-1).get(SqlOpenHelper.NEWS_COLUMN_THUMBNAIL_URI))));
		
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
        		m_tv.setText(m_list.get(m_position-1).get(SqlOpenHelper.NEWS_COLUMN_TITLE));
        		m_iv.setImageURI(Uri.parse("file:" + (m_list.get(m_position-1).get(SqlOpenHelper.NEWS_COLUMN_THUMBNAIL_URI))));
        		ShowHideArrowImage();
        	}
        });
		m_right_image = (ImageView)view.findViewById(R.id.image_right_arrow);
		m_right_image.setOnClickListener(new View.OnClickListener() {
        	public void onClick(View view) {
        		m_position++;
        		m_tv.setText(m_list.get(m_position-1).get(SqlOpenHelper.NEWS_COLUMN_TITLE));
        		m_iv.setImageURI(Uri.parse("file:" + (m_list.get(m_position-1).get(SqlOpenHelper.NEWS_COLUMN_THUMBNAIL_URI))));
        		ShowHideArrowImage();
        	}
        });
		ShowHideArrowImage();
        return view;
    }
    
    private void ShowHideArrowImage()
    {
		if (m_position == 1) 
			m_left_image.setVisibility(View.GONE);
		else
			m_left_image.setVisibility(View.VISIBLE);
		if (m_position == m_list.size()) 
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