package com.withtron.sancoffee;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;

import org.json.JSONException;
import org.json.JSONObject;

import com.withtron.sancoffee.R;
import com.withtron.sancoffee.FragmentTabsActivity.UserNameRequestTask;

import com.markupartist.android.widget.PullToRefreshListView;
import com.markupartist.android.widget.PullToRefreshListView.OnRefreshListener;

import android.app.ActivityGroup;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class NewsFragment extends Fragment {
	private PullToRefreshListView mPullToRefreshListView;
	private ArrayList<HashMap<String, String>> mNewsList;
	private boolean mLoading = false;  // ISSUE, avoid dup source code (parse news and download image)
	@Override
	public void onCreate(Bundle savedInstanceState) {
		Log.d("NewsFragment", "onCreate");
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		mNewsList = new ArrayList<HashMap<String, String>>();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		Log.d("NewsFragment", "onCreateView");
		View view =  inflater.inflate(R.layout.pull_to_refresh, container, false);
		
        // Set a listener to be invoked when the list should be refreshed.

		mPullToRefreshListView = ((PullToRefreshListView) view.findViewById(android.R.id.list));
		mPullToRefreshListView.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
            	mLoading = true;
            	new NewsRequestTask().execute("http://chopchop.corel.com/api/trends?type=1");
            }
        });
		mPullToRefreshListView.setOnItemClickListener(new OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView parent, View view, int position,long id) {
        		Log.d("NewsFragment", "onItemClick");
        		
        		if (DetailsFragment.show_DetailsFragment)
        			return;
        		
        		mPullToRefreshListView.setSelection(position);
        		ImageView image = (ImageView)getActivity().findViewById(R.id.titel_bar_image);
        		image.setVisibility(View.GONE);
        		ImageView back_image = (ImageView)getActivity().findViewById(R.id.titel_bar_back_image);
        		back_image.setVisibility(View.VISIBLE);
        		
        		DetailsFragment details = DetailsFragment.newInstance(mNewsList, position);
        		FragmentTransaction ft = getFragmentManager().beginTransaction();
        		ft.replace(R.id.list_container, details);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                ft.addToBackStack(null);
                ft.commit();
            }
		});
		UpdateListItems();
		
        int count = mNewsList.size();
        if (count ==0){
        	new NewsRequestTask().execute("http://chopchop.corel.com/api/trends?type=1");
        }
        
        LazyAdapter adapter = new LazyAdapter(getActivity(), mNewsList); 
        ((PullToRefreshListView) view.findViewById(android.R.id.list)).setAdapter(adapter);
        
        return view;
		
	}
	
	private void UpdateListItems(){
		mNewsList.clear();
        // Query db
		SqlOpenHelper helper = new SqlOpenHelper(getActivity());
		SQLiteDatabase database = helper.getWritableDatabase();        
        Cursor listCursor = database.query(SqlOpenHelper.TABLE_NEWS, null, null, null, null, null, null, null);
        listCursor.moveToFirst();
        if (! listCursor.isAfterLast()){
        	do{
        		HashMap<String, String> map = new HashMap<String, String>();
        		map.put(SqlOpenHelper.NEWS_COLUMN_ID, listCursor.getString(0));
        		map.put(SqlOpenHelper.NEWS_COLUMN_TEXTID, listCursor.getString(1));
        		map.put(SqlOpenHelper.NEWS_COLUMN_TITLE, listCursor.getString(2));
        		map.put(SqlOpenHelper.NEWS_COLUMN_DESCRIPTION, listCursor.getString(3));
        		map.put(SqlOpenHelper.NEWS_COLUMN_THUMBNAIL_URI, listCursor.getString(4));
        		mNewsList.add(map);
        	}while (listCursor.moveToNext());
        }
        listCursor.close();
        database.close();
	}
	
    public class NewsRequestTask extends RequestTask{

        @Override
		protected String doInBackground(String... uri) {
			// TODO Auto-generated method stub
        	String result = getHttpRequest(uri);
            //Do anything with response..
            try {
            	Log.d("NewsRequestTask resutl = " + result, "FragmentRequestTask");
            	if (result == null){
            	}
            	else{
            		SqlOpenHelper helper = new SqlOpenHelper(getActivity());
            		SQLiteDatabase database = helper.getWritableDatabase();
            		
    				JSONObject jObject = new JSONObject(result);
    				int count = jObject.getInt("count");
    				for(int i = 0; i< count; i ++){
    					JSONObject item = jObject.getJSONObject(Integer.toString(i));
        				String id = item.getString("id");
        				String title = item.getString("title");
        				String description = item.getString("description");
        				String thumbnailUrl = item.getString("thumbnail");
        				String FILENAME = id + ".jpg";

        				File file = getActivity().getFileStreamPath(FILENAME);
        				if (file != null && file.exists()) 
        					continue;
        				
        				// save thumbnail to local
        				try{
            				FileOutputStream fos = getActivity().openFileOutput(FILENAME, Context.MODE_PRIVATE);
            				URL url = new URL(thumbnailUrl);
            				HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            				urlConnection.setRequestMethod("GET");
            				urlConnection.setDoOutput(true);                   
            				urlConnection.connect();      
            				InputStream inputStream = urlConnection.getInputStream();
            				int totalSize = urlConnection.getContentLength();
            				int downloadedSize = 0;   
            				byte[] buffer = new byte[1024];
            				int bufferLength = 0;
            				while ( (bufferLength = inputStream.read(buffer)) > 0 ) 
            				{                 
            					fos.write(buffer, 0, bufferLength);                  
            					downloadedSize += bufferLength;                 
            					Log.i("Progress:","downloadedSize:"+downloadedSize+"totalSize:"+ totalSize) ;
            				}             
            				inputStream.close();
            				fos.close();
            				
            				// insert to db
            				ContentValues values = new ContentValues();
            				values.put(SqlOpenHelper.NEWS_COLUMN_TEXTID, id);
            				values.put(SqlOpenHelper.NEWS_COLUMN_TITLE, title);
            				values.put(SqlOpenHelper.NEWS_COLUMN_DESCRIPTION, description);
            				values.put(SqlOpenHelper.NEWS_COLUMN_THUMBNAIL_URI, file.getAbsolutePath());
            				
            				long insertId = database.insert(SqlOpenHelper.TABLE_NEWS, null, values);
        				}catch (FileNotFoundException e){
        					e.printStackTrace();
        					Log.d("Save image not find, url = " + thumbnailUrl, "FragmentRequestTask");
        				}
    				}
    				database.close();
            	}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Log.d("Parse JSON fail, e = " + e, "FragmentRequestTask");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Log.d("IOException, e = " + e , "FragmentRequestTask");
			}
            return result;
		}

		@Override
        protected void onPostExecute(String result) {
        	super.onPostExecute(result);
            //Do anything with response..
            if (mLoading){ 
            	mLoading = false;
            	UpdateListItems();
            	mPullToRefreshListView.onRefreshComplete();
            }
        }
    }
}