package com.withtron.sancoffee;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.LinkedList;

import org.json.JSONException;
import org.json.JSONObject;

import com.withtron.sancoffee.R;

import com.markupartist.android.widget.PullToRefreshListView;
import com.markupartist.android.widget.PullToRefreshListView.OnRefreshListener;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class NewsFragment extends ListFragment {
	private LinkedList<String> mListItems;
	private PullToRefreshListView mPullToRefreshListView;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		Log.d("NewsFragment", "onCreate");
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		new NewsRequestTask().execute("http://chopchop.corel.com/api/trends?type=2");
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
                // Do work to refresh the list here.
                new GetDataTask().execute();
            }
        });

        mListItems = new LinkedList<String>();
        mListItems.addAll(Arrays.asList(mStrings));

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1, mListItems);

        ((PullToRefreshListView) view.findViewById(android.R.id.list)).setAdapter(adapter);
        
        return view;
		
	}
	
    public class NewsRequestTask extends RequestTask{

        @Override
        protected void onPostExecute(String result) {
        	super.onPostExecute(result);
            //Do anything with response..
            try {
            	Log.d("NewsRequestTask resutl = " + result, "FragmentRequestTask");
            	if (result == null){
            	}
            	else{
    				JSONObject jObject = new JSONObject(result);
    				int count = jObject.getInt("count");
    				for(int i = 0; i< count; i ++){
    					JSONObject item = jObject.getJSONObject(Integer.toString(i));
        				String id = item.getString("id");
        				String title = item.getString("title");
        				String description = item.getString("description");
        				String thumbnailUrl = item.getString("thumbnail");
        				String FILENAME = id + ".jpg";

        				// save thumbnail to local
        				File file = getActivity().getFileStreamPath(FILENAME);
        				if (file != null && file.exists()) {
        				}
        				else{
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
        				}
    				}
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
        }
    }
	
	private class GetDataTask extends AsyncTask<Void, Void, String[]> {

        @Override
        protected String[] doInBackground(Void... params) {
            // Simulates a background job.
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                ;
            }
            return mStrings;
        }

        @Override
        protected void onPostExecute(String[] result) {
            mListItems.addFirst("Added after refresh...");

            // Call onRefreshComplete when the list has been refreshed.
            //((PullToRefreshListView) view.findViewById(R.id.pulltorefreshlist)).onRefreshComplete();
            mPullToRefreshListView.onRefreshComplete();

            super.onPostExecute(result);
        }
    }

    private String[] mStrings = {
            "Abbaye de Belloc", "Abbaye du Mont des Cats", "Abertam",
            "Abondance", "Ackawi", "Acorn", "Adelost", "Affidelice au Chablis",
            "Afuega'l Pitu", "Airag", "Airedale", "Aisy Cendre",
            "Allgauer Emmentaler"};
}