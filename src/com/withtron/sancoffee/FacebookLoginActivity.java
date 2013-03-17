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
	private Session.StatusCallback statusCallback = new SessionStatusCallback();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
        Settings.addLoggingBehavior(LoggingBehavior.INCLUDE_ACCESS_TOKENS);

        Session session = Session.getActiveSession();
        if (session == null) {
            if (savedInstanceState != null) {
                session = Session.restoreSession(this, null, statusCallback, savedInstanceState);
            }
            if (session == null) {
                session = new Session(this);
            }
            Session.setActiveSession(session);
            if (session.getState().equals(SessionState.CREATED_TOKEN_LOADED)) {
                session.openForRead(new Session.OpenRequest(this).setCallback(statusCallback));
            }
        }
        else if (!session.isOpened() && !session.isClosed()) {
            session.openForRead(new Session.OpenRequest(this).setCallback(statusCallback));
        } else {
        	// start Facebook Login
            Session.openActiveSession(this, true, statusCallback);
        }
	}
	
    @Override
    public void onStart() {
        super.onStart();
        Session.getActiveSession().addCallback(statusCallback);
    }

    @Override
    public void onStop() {
        super.onStop();
        Session.getActiveSession().removeCallback(statusCallback);
    }
    
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		Session.getActiveSession().onActivityResult(this, requestCode, resultCode, data);
	}
	
    private class SessionStatusCallback implements Session.StatusCallback {
        @Override
        public void call(Session session, SessionState state, Exception exception) {
        	updateView();
        }
    }
    
    private void updateView() {
        Session session = Session.getActiveSession();
        if (session.isOpened()) {
        	((SanCoffeeApp) getApplication()).setFacebookAccessToken(session.getAccessToken());
            Intent intent = new Intent(this, FragmentTabs.class);
            startActivity(intent);		
        } else {
        }
    }


}
