package com.withtron.sancoffee;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

public class SanCoffeeApp extends Application {

		SharedPreferences mPrefs;

		@Override
		public void onCreate() {
			// TODO Auto-generated method stub
			super.onCreate();
			Context context = this.getApplicationContext();
			mPrefs = context.getSharedPreferences("SanAppPrefs", 0);
		}
		
		public boolean getFirstRun(){
			//return mPrefs.getBoolean("firstRun", true);
			return true;
		}
		
		public void setRunned(){
			SharedPreferences.Editor edit = mPrefs.edit();
			edit.putBoolean("firstRun", false);
			edit.commit();
		}
		
		public String getFacebookAccessToken(){
			String token = mPrefs.getString("fbAccessToken", "");
			Log.d("getFacebookAccessToken = " + token, "SanCoffeeApp");
			return token;
		}
		
		public void setFacebookAccessToken(String token){
			Log.d("setFacebookAccessToken = " + token, "SanCoffeeApp");
			SharedPreferences.Editor edit = mPrefs.edit();
			edit.putString("fbAccessToken", token);
			edit.commit();
		}
}
