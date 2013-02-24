package com.withtron.sancoffee;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

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
			return mPrefs.getString("fbAccessToken", "");
		}
		
		public void setFacebookAccessToken(String token){
			SharedPreferences.Editor edit = mPrefs.edit();
			edit.putString("fbAccessToken", token);
			edit.commit();
		}
}
