package com.withtron.sancoffee;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

public class DetailsFragment extends Fragment {
    /**
     * Create a new instance of DetailsFragment, initialized to
     * show the text at 'index'.
     */
    public static DetailsFragment newInstance(String title, String url ) {
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
    	
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.item_detail, container, false);
		TextView tv = (TextView) view.findViewById(R.id.item_detail_title);
		tv.setText(getArguments().getString("title"));
		ImageView iv = (ImageView) view.findViewById(R.id.item_detail_image);
		iv.setImageURI(Uri.parse("file:" + getArguments().getString("url")));

        return view;
    }
}