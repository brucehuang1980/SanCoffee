package com.withtron.sancoffee;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import com.withtron.sancoffee.R;
import com.withtron.sancoffee.FragmentTabsActivity.UserNameRequestTask;
import com.withtron.sancoffee.SanLoginActivity.SanLogin;

import com.google.gson.Gson;
import com.markupartist.android.widget.PullToRefreshListView;
import com.markupartist.android.widget.PullToRefreshListView.OnRefreshListener;

import android.R.array;
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
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
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
	private HashMap<String, NewsData> mNewsMap;
	private HashMap<String, ArrayList<NewsItem>> mNewsDetailMap;
	private boolean mLoading = false;  // ISSUE, avoid dup source code (parse news and download image)
	@Override
	public void onCreate(Bundle savedInstanceState) {
		Log.d("NewsFragment", "onCreate");
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		mNewsMap = new HashMap<String, NewsData>();
		mNewsDetailMap = new HashMap<String, ArrayList<NewsItem>>();
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
            	if (mLoading)
            		return;

            	if (Utils.isOnline(getActivity())){
            		mLoading = true;
                	new NewsRequestTask().execute("http://coffee.trendy-co.com/mobile.php?action=news");
            	}
            	else{
                	UpdateListItems();
            	}
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
        		
        		DetailsFragment details = DetailsFragment.newInstance(mNewsDetailMap, position);
        		FragmentTransaction ft = getFragmentManager().beginTransaction();
        		ft.replace(R.id.list_container, details);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                ft.addToBackStack(null);
                ft.commit();
            }
		});
		UpdateListItems();
		
        int count = mNewsMap.size();
        if (count ==0){
        	if (Utils.isOnline(getActivity())){
        		mLoading = true;
        		new NewsRequestTask().execute("http://coffee.trendy-co.com/mobile.php?action=news");
        	}
        }
        
        LazyAdapter adapter = new LazyAdapter(getActivity(), mNewsMap); 
        ((PullToRefreshListView) view.findViewById(android.R.id.list)).setAdapter(adapter);
        
        return view;
		
	}
	
	private void UpdateListItems(){
		Log.d("NewsFragment", "UpdateListItems");
		mNewsMap.clear();
		mNewsDetailMap.clear();
        // Query db
		SqlOpenHelper helper = new SqlOpenHelper(getActivity());
		SQLiteDatabase database = helper.getWritableDatabase();    
        
		Cursor newsDetailCursor = database.query(SqlOpenHelper.TABLE_NEWS_DETAIL, null, null, null, null, null, null, null);
		newsDetailCursor.moveToFirst();
        if (! newsDetailCursor.isAfterLast()){
        	do{
        		NewsItem item = new NewsItem();
        		item.data_type = newsDetailCursor.getInt(2);
        		item.data_content = newsDetailCursor.getString(3);
        		item.data_link = newsDetailCursor.getString(4);
        		String newsId = Integer.toString(newsDetailCursor.getInt(1));
        		ArrayList<NewsItem> array;
        		if (!mNewsDetailMap.containsKey(newsId)){
        			array = new ArrayList<NewsItem>();
        			mNewsDetailMap.put(newsId, array);
        		}
        		else{
        			array = mNewsDetailMap.get(newsId);
        		}
        		array.add(item);
        	}while (newsDetailCursor.moveToNext());
        }
        newsDetailCursor.close();
        
        Cursor newsCursor = database.query(SqlOpenHelper.TABLE_NEWS, null, null, null, null, null, null, null);
        newsCursor.moveToFirst();
        if (! newsCursor.isAfterLast()){
        	do{
        		NewsData data = new NewsData();
        		data.news_id = newsCursor.getInt(0);
        		data.news_title = newsCursor.getString(1);
        		data.update_date = newsCursor.getString(4);
        		mNewsMap.put(Integer.toString(newsCursor.getInt(0)), data);
        	}while (newsCursor.moveToNext());
        }
        newsCursor.close();
        database.close();
	}
	
	class News {
		  public int result = 0;
		  public String msg = "";
		  public NewsData[] data ;
		  News() {
		  }
		}
	
	class NewsData {
		  public int news_id = 0;
		  public String news_title = "";
		  public String update_date = "";
		  public NewsItem[] item;
		  NewsData() {
		  }
		}
	
	class NewsItem {
		public String data_content = "";
		public String data_link = "";
		public int data_type = 0;
		NewsItem(){
		}
	}
	
    public class NewsRequestTask extends RequestTask{

        @Override
		protected String doInBackground(String... uri) {
			// TODO Auto-generated method stub
        	Log.d("NewsRequestTask", "doInBackground uri = " + uri );
        	String result = getHttpRequest(uri);
            //Do anything with response..
            try {
            	Log.d("NewsRequestTask", "getHttpRequest resutl = " + result);
            	if (result == null){
            	}
            	else{
            		SqlOpenHelper helper = new SqlOpenHelper(getActivity());
            		SQLiteDatabase database = helper.getWritableDatabase();
            		
            		Gson gson = new Gson();
            		News news = gson.fromJson(result, News.class);
            		if (news.result == 0) // success
            		{
	    				for(int i = 0; i< news.data.length; i ++){
	    					String id= Integer.toString(news.data[i].news_id);
	    					if (mNewsMap.containsKey(id) && mNewsMap.get(id).update_date.equals(news.data[i].update_date))
	    						continue;
	    					if (mNewsMap.containsKey(id) ){ // remove update item 
	    					//if (true){
	    						database.delete(SqlOpenHelper.TABLE_NEWS_DETAIL, SqlOpenHelper.NEWS_DETAIL_COLUMN_NEWS_ID + " = " + id, null);
	    						database.delete(SqlOpenHelper.TABLE_NEWS, SqlOpenHelper.NEWS_COLUMN_ID + " = " + id, null);
	    					}
	    					
	    					for (int j = 0;j < news.data[i].item.length; j++){
		        				if (news.data[i].item[j].data_type == 1) {// image
			        				// save thumbnail to local
			        				try{
			        					String FILENAME = news.data[i].news_id + "_" + news.data[i].item[j].data_content.substring( news.data[i].item[j].data_content.lastIndexOf('/')+1, news.data[i].item[j].data_content.length());
			            				FileOutputStream fos = getActivity().openFileOutput(FILENAME, Context.MODE_PRIVATE);
			            				URL url = new URL(news.data[i].item[j].data_content);
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
			        				}catch (FileNotFoundException e){
			        					e.printStackTrace();
			        					Log.d("Save image not find, url = " + news.data[i].item[j].data_content, "FragmentRequestTask");
			        				}
		        				}
	        					ContentValues values2 = new ContentValues();
	        					values2.put(SqlOpenHelper.NEWS_DETAIL_COLUMN_NEWS_ID, news.data[i].news_id);
	        					values2.put(SqlOpenHelper.NEWS_DETAIL_COLUMN_TYPE, news.data[i].item[j].data_type);
	        					values2.put(SqlOpenHelper.NEWS_DETAIL_COLUMN_CONTENT, news.data[i].item[j].data_content);
	        					values2.put(SqlOpenHelper.NEWS_DETAIL_COLUMN_LINK, news.data[i].item[j].data_link);
		        				long insertDetailId = database.insert(SqlOpenHelper.TABLE_NEWS_DETAIL, null, values2);
		        				//Log.d("NewsRequestTask",  "insertDetailId  = " + insertDetailId);
	        				}
	        				ContentValues values = new ContentValues();
	        				values.put(SqlOpenHelper.NEWS_COLUMN_ID, news.data[i].news_id);
	        				values.put(SqlOpenHelper.NEWS_COLUMN_TITLE, news.data[i].news_title);
	        				values.put(SqlOpenHelper.NEWS_COLUMN_UPDATE_TIME, news.data[i].update_date);
	        				long insertId = database.insert(SqlOpenHelper.TABLE_NEWS, null, values);
	        				//Log.d("NewsRequestTask",  "insertId = " + insertId);
	    				}
	    				database.close();
            		}
            	}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Log.d("Exception, e = " + e , "FragmentRequestTask");
			}
            return result;
		}

		@Override
        protected void onPostExecute(String result) {
			Log.d("NewsRequestTask",  "onPostExecute result = " + result);
        	super.onPostExecute(result);
            //Do anything with response..
            if (mLoading){ 
            	mLoading = false;
            }
            
        	UpdateListItems();
        	mPullToRefreshListView.onRefreshComplete();
        }
    }
}