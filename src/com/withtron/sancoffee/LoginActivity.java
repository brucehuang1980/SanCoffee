package com.withtron.sancoffee;

import com.facebook.Session;
import com.facebook.SessionState;
import com.facebook.model.GraphUser;
import com.withtron.sancoffee.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;


public class LoginActivity extends Activity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Log.d("LoginActivity", "onCreate");
		super.onCreate(savedInstanceState);
		
        if(((SanCoffeeApp) getApplication()).getFirstRun()){
    		setContentView(R.layout.activity_login);
            final Button fb_button = (Button) findViewById(R.id.facebooklogin);
            fb_button.setOnClickListener(new View.OnClickListener() {
            	public void onClick(View view) {
            		// start Facebook Login
            	    Session.openActiveSession(LoginActivity.this, true, new Session.StatusCallback() {
            	      // callback when session changes state
            	      @Override
            	      public void call(Session session, SessionState state, Exception exception) {
            	        if (session.isOpened()) {
            	        	((SanCoffeeApp) getApplication()).setFacebookAccessToken(session.getAccessToken());
            	        	((SanCoffeeApp) getApplication()).setRunned();
            	            Intent intent = new Intent(LoginActivity.this, FragmentTabsActivity.class);
            	            startActivity(intent);		
            	        }
            	      }
            	    });
            	}
            });
            
            final Button button_sanlogin = (Button) findViewById(R.id.sanlogin);
            button_sanlogin.setOnClickListener(new View.OnClickListener() {
            	public void onClick(View view) { 
            		AlertDialog ad = new AlertDialog.Builder(LoginActivity.this).create();
            		ad.setMessage("Not Ready");
            		ad.setButton(getString(R.string.ok), new DialogInterface.OnClickListener() {
            	          public void onClick(DialogInterface dialog, int which) {
                              Intent intent = new Intent(LoginActivity.this, FragmentTabsActivity.class);
                              startActivity(intent);		
                          }
            		});
            		ad.show();
            	}
            });
            
            final Button button_signup = (Button) findViewById(R.id.signup);
            button_signup.setOnClickListener(new View.OnClickListener() {
            	public void onClick(View view) {                     
            		AlertDialog ad = new AlertDialog.Builder(LoginActivity.this).create();
            		ad.setMessage("Not Ready");
            		ad.setButton(getString(R.string.ok), new DialogInterface.OnClickListener() {
            	          public void onClick(DialogInterface dialog, int which) {
                              Intent intent = new Intent(LoginActivity.this, FragmentTabsActivity.class);
                              startActivity(intent);		
                          }
            		});
            		ad.show();
            	}
            });
            
            final Button button_dontlogin = (Button) findViewById(R.id.dontlogin);
            button_dontlogin.setOnClickListener(new View.OnClickListener() {
            	public void onClick(View view) {
                    Intent intent = new Intent(LoginActivity.this, FragmentTabsActivity.class);
                    startActivity(intent);	        		
            	}
            });
            
		}
        else{
            Intent intent = new Intent(this, FragmentTabsActivity.class);
            startActivity(intent);		
        }
	}
	

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
	    super.onActivityResult(requestCode, resultCode, data);
	    Session.getActiveSession().onActivityResult(this, requestCode, resultCode, data);
	}
}
