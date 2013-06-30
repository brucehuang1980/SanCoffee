package com.withtron.sancoffee;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

public class SanCoffeeApp extends Application {

		public enum LoginType{
		    SanApp,
		    Facebook
		}
		
		SharedPreferences mPrefs;

		@Override
		public void onCreate() {
			// TODO Auto-generated method stub
			super.onCreate();
			Context context = this.getApplicationContext();
			mPrefs = context.getSharedPreferences("SanAppPrefs", 0);
		}
		
		public boolean getFirstRun(){
			return mPrefs.getBoolean("firstRun", true);
			//return true;
		}
		
		public void setFirstRun(boolean b){
			SharedPreferences.Editor edit = mPrefs.edit();
			edit.putBoolean("firstRun", b);
			edit.commit();
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
		
		public String getSanSessionID(){
			String sid = mPrefs.getString("sanSessionID", "");
			Log.d("getSanSessionID = " + sid, "SanCoffeeApp");
			return sid;
		}
		
		public void setSanSessionID(String sid){
			Log.d("setSanSessionID = " + sid, "SanCoffeeApp");
			SharedPreferences.Editor edit = mPrefs.edit();
			edit.putString("sanSessionID", sid);
			edit.commit();
		}
		
		public int getLoginType(){
			int type = mPrefs.getInt("loginType", LoginType.SanApp.ordinal());
			Log.d("getLoginType = " + type, "SanCoffeeApp");
			return type;
		}
		
		public void setLoginType(int type){
			Log.d("setgetLoginType = " + type, "SanCoffeeApp");
			SharedPreferences.Editor edit = mPrefs.edit();
			edit.putInt("loginType", type);
			edit.commit();
		}
}
