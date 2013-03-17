package com.withtron.sancoffee;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import com.facebook.Session;
import com.withtron.sancoffee.R;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Window;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;

/**
 * This demonstrates how you can implement switching between the tabs of a
 * TabHost through fragments.  It uses a trick (see the code below) to allow
 * the tabs to switch between fragments instead of simple views.
 */
public class FragmentTabs extends FragmentActivity {
    private TabHost mTabHost;
    private TabManager mTabManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	Log.d("FragmentTabs", "onCreate");
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        setContentView(R.layout.fragment_tabs);
        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.titlebar);
        
        mTabHost = (TabHost)findViewById(android.R.id.tabhost);
        mTabHost.setup();
        
        mTabManager = new TabManager(this, mTabHost, R.id.realtabcontent);
        
        mTabHost.setCurrentTab(0);
        mTabManager.addTab(mTabHost.newTabSpec("NewsFragment").setIndicator(getString(R.string.news_tab)),
        		NewsFragment.class, null);
        mTabManager.addTab(mTabHost.newTabSpec("CookFragment").setIndicator(getString(R.string.cook_tab)),
        		CookFragment.class, null);
        mTabManager.addTab(mTabHost.newTabSpec("ScanFragment").setIndicator(getString(R.string.scan_tab)),
        		ScanFragment.class, null);
        mTabManager.addTab(mTabHost.newTabSpec("MyFragment").setIndicator(getString(R.string.my_tab)),
        		MyFragment.class, null);
        mTabManager.addTab(mTabHost.newTabSpec("SetupFragment").setIndicator(getString(R.string.setup_tab)),
        		SetupFragment.class, null);

        DisplayMetrics dm = new DisplayMetrics();   
        getWindowManager().getDefaultDisplay().getMetrics(dm); //先取得螢幕解析度  
        int screenWidth = dm.widthPixels;   //取得螢幕的寬
           
        TabWidget tabWidget = mTabHost.getTabWidget();   //取得tab的物件
        int count = tabWidget.getChildCount();   //取得tab的分頁有幾個
        for (int i = 0; i < count; i++) {   
            tabWidget.getChildTabViewAt(i).setMinimumWidth((screenWidth / count)+8);//設定每一個分頁最小的寬度   
        }   
        
        Session session = Session.getActiveSession();
        if (session != null && session.isOpened()) {
            String token = session.getAccessToken();
            Log.d("facebook token = " + token, "FragmentTabs");
            String url = "https://graph.facebook.com/me?fields=id,name&access_token=" + token;
            Log.d("Query facebook user name, url = " + url, "FragmentTabs");
        	new UserNameRequestTask().execute(url);
        } 
        else{
        	Log.d("Get session fail!, session = " + session, "FragmentTabs");
        }
    }
    
    public class UserNameRequestTask extends RequestTask{

        @Override
        protected void onPostExecute(String result) {
        	super.onPostExecute(result);
            //Do anything with response..
            try {
            	Log.d("FragmentRequestTask resutl = " + result, "FragmentRequestTask");
            	if (result == null){
    				TextView text = (TextView)findViewById(R.id.fb_user_name);
    				text.setText("");
            	}
            	else{
    				JSONObject jObject = new JSONObject(result);
    				String userName = jObject.getString("name");
    				TextView text = (TextView)findViewById(R.id.fb_user_name);
    				text.setText(userName);
            	}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Log.d("Parse JSON fail", "FragmentRequestTask");
			}
        }
    }
    
	public void onActivityResult(int requestCode, int resultCode, Intent intent) {
		IntentResult scanResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
		if (scanResult != null) {
			// handle scan result
			ScanFragment scanFrag = (ScanFragment)getSupportFragmentManager().findFragmentByTag("ScanFragment");
			scanFrag.setQRCode(scanResult.getContents());
		}
		// else continue with any other code you need in the method
	}
}