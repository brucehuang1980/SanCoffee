package com.withtron.sancoffee;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import com.withtron.sancoffee.NewsFragment.NewsData;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class LazyAdapter extends BaseAdapter {
    
    private Activity activity;
    private LinkedHashMap<String, NewsData> mData;
    private static LayoutInflater inflater=null;
    public ImageLoader imageLoader; 
    
    public LazyAdapter(Activity a, LinkedHashMap<String, NewsData> d) {
        activity = a;
        mData=d;
        inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        imageLoader=new ImageLoader(activity.getApplicationContext());
    }

    public int getCount() {
        return mData.size();
    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }
    
    public View getView(int position, View convertView, ViewGroup parent) {
        View vi=convertView;
        if(convertView==null)
            vi = inflater.inflate(R.layout.list_row_no_image, null);

        TextView title = (TextView)vi.findViewById(R.id.no_image_title); // title
        //TextView artist = (TextView)vi.findViewById(R.id.artist); // artist name
        //ImageView thumb_image=(ImageView)vi.findViewById(R.id.list_image); // thumb image
        
        LinkedHashMap<String, String> item = new LinkedHashMap<String, String>();
        //item = data.get(position);
        List keys = new ArrayList(mData.keySet());
        NewsData data = mData.get(keys.get(position));
        
        // Setting all values in listview
        title.setText(data.news_title);
        //artist.setText(item.get(CustomizedListView.KEY_ARTIST));
        //duration.setText(item.get(CustomizedListView.KEY_DURATION));
        //imageLoader.DisplayImage(item.get(SqlOpenHelper.NEWS_COLUMN_THUMBNAIL_URI), thumb_image);
        return vi;
    }
}