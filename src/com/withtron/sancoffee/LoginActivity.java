package com.withtron.sancoffee;

import com.withtron.sancoffee.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.facebook.*;
import com.facebook.model.*;

public class LoginActivity extends Activity{

	private Session.StatusCallback statusCallback = new SessionStatusCallback();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Log.d("LoginActivity", "onCreate");
		
        if(((SanCoffeeApp) getApplication()).getFirstRun()){
        	((SanCoffeeApp) getApplication()).setRunned();
    		super.onCreate(savedInstanceState);
    		setContentView(R.layout.activity_login);
    		
            final Button button = (Button) findViewById(R.id.facebooklogin);
            button.setOnClickListener(new View.OnClickListener() {
            	public void onClick(View view) { onClickLogin(); }
            });
            
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
		}
        else{
            Intent intent = new Intent(this, FragmentTabs.class);
            startActivity(intent);		
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
	
    private void updateView() {
        Session session = Session.getActiveSession();
        if (session.isOpened()) {
        	((SanCoffeeApp) getApplication()).setFacebookAccessToken(session.getAccessToken());
            Intent intent = new Intent(this, FragmentTabs.class);
            startActivity(intent);		
        } else {
        }
    }
	
    private void onClickLogin() {
        Session session = Session.getActiveSession();
        if (!session.isOpened() && !session.isClosed()) {
            session.openForRead(new Session.OpenRequest(this).setCallback(statusCallback));
        } else {
            Session.openActiveSession(this, true, statusCallback);
        }
    }
    
    private class SessionStatusCallback implements Session.StatusCallback {
        @Override
        public void call(Session session, SessionState state, Exception exception) {
        	updateView();
        }
    }

}
