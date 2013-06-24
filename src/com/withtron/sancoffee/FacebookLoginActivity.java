package com.withtron.sancoffee;

import com.facebook.LoggingBehavior;
import com.facebook.Session;
import com.facebook.SessionState;
import com.facebook.Settings;

import com.facebook.*;
import com.facebook.model.*;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class FacebookLoginActivity extends Activity{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		// start Facebook Login
	    Session.openActiveSession(FacebookLoginActivity.this, true, new Session.StatusCallback() {
	      // callback when session changes state
	      @Override
	      public void call(Session session, SessionState state, Exception exception) {
	        if (session.isOpened()) {
	        	((SanCoffeeApp) getApplication()).setFacebookAccessToken(session.getAccessToken());
	        	((SanCoffeeApp) getApplication()).setRunned();
	            Intent intent = new Intent(FacebookLoginActivity.this, FragmentTabsActivity.class);
	            startActivity(intent);		
	        }
	      }
	    });
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
	    super.onActivityResult(requestCode, resultCode, data);
	    Session.getActiveSession().onActivityResult(this, requestCode, resultCode, data);
	}
}
