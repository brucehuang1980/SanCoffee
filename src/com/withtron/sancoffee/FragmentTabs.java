package com.withtron.sancoffee;

import com.withtron.sancoffee.R;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Window;
import android.widget.TabHost;
import android.widget.TabWidget;

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
    }
}