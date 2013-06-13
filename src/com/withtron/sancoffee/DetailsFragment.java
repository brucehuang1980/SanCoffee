package com.withtron.sancoffee;

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
    public static DetailsFragment newInstance(String title, String url ) {
    	Log.d("DetailsFragment", "newInstance");
    	
        DetailsFragment f = new DetailsFragment();
        // Supply index input as an argument.
        Bundle args = new Bundle();
        //args.putInt("index", index);
        args.putString("title", title);
        args.putString("url", url);
        f.setArguments(args);

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
        View view = inflater.inflate(R.layout.item_detail, container, false);
		TextView tv = (TextView) view.findViewById(R.id.item_detail_title);
		tv.setText(getArguments().getString("title"));
		ImageView iv = (ImageView) view.findViewById(R.id.item_detail_image);
		iv.setImageURI(Uri.parse("file:" + getArguments().getString("url")));
		
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
		Log.d("DetailsFragment", "onDestroyView");
		super.onDestroyView();
		ImageView back_image = (ImageView)getActivity().findViewById(R.id.titel_bar_back_image);
		back_image.setVisibility(View.GONE);	
		ImageView image = (ImageView)getActivity().findViewById(R.id.titel_bar_image);
		image.setVisibility(View.VISIBLE);
	}

}