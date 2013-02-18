package san.coffee;


import java.util.Arrays;
import java.util.LinkedList;

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

public class Fragment1 extends ListFragment {
	private LinkedList<String> mListItems;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		Log.d("Fragment1", "onCreate");
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		Log.d("Fragment1", "onCreateView");
		/*
        Context ctx = getApplicationContext();
		Resources res = ctx.getResources();

		String[] options = res.getStringArray(R.array.country_names);
		TypedArray icons = res.obtainTypedArray(R.array.country_icons);
		
		setListAdapter(new ImageAndTextAdapter(ctx, R.layout.main_list_item,
				options, icons));
		
		String[] str = {"123", "345"}; 
		PullToRefreshListView listView = new PullToRefreshListView(getActivity());
		listView.setAdapter(new ImageAndTextAdapter(ctx, R.layout.main_list_item, {"string ", "345"} , R.layout.countries));
		return listView;
		*/
		/*
		View view = inflater.inflate(R.layout.pull_to_refresh, container, false);
		
		
		String[] data = new String[0];	// empty list!
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, data);
        final ListView myList = (ListView) view.findViewById(android.R.id.list);
        myList.setAdapter(adapter);
        myList.setFastScrollEnabled(true);
        myList.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, 
					int pos, long id) {
			}
		});
        myList.setTextFilterEnabled(true);
        Drawable d = new GradientDrawable(
        		GradientDrawable.Orientation.BOTTOM_TOP,
        		new int[]{0x00ff00, 0x008888, 0x0000ff});

		return view;
		*/
		
		View view =  inflater.inflate(R.layout.pull_to_refresh, container, false);
		
        // Set a listener to be invoked when the list should be refreshed.

        ((PullToRefreshListView) view.findViewById(android.R.id.list)).setOnRefreshListener(new OnRefreshListener() {
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
        /*
		PullToRefreshListView listView = new PullToRefreshListView(getActivity());
		//ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1, mListItems);
        mListItems = new LinkedList<String>();
        mListItems.addAll(Arrays.asList(mStrings));
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1, mListItems);
		listView.setAdapter(adapter);
		return listView;
		*/
		
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
            ((PullToRefreshListView) getListView()).onRefreshComplete();

            super.onPostExecute(result);
        }
    }

    private String[] mStrings = {
            "Abbaye de Belloc", "Abbaye du Mont des Cats", "Abertam",
            "Abondance", "Ackawi", "Acorn", "Adelost", "Affidelice au Chablis",
            "Afuega'l Pitu", "Airag", "Airedale", "Aisy Cendre",
            "Allgauer Emmentaler"};
}