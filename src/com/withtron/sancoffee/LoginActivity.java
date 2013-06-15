package com.withtron.sancoffee;

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
		
        if(((SanCoffeeApp) getApplication()).getFirstRun()){
        	((SanCoffeeApp) getApplication()).setRunned();
    		super.onCreate(savedInstanceState);
    		setContentView(R.layout.activity_login);
    		
            final Button fb_button = (Button) findViewById(R.id.facebooklogin);
            fb_button.setOnClickListener(new View.OnClickListener() {
            	public void onClick(View view) {
                    Intent intent = new Intent(LoginActivity.this, FacebookLoginActivity.class);
                    startActivity(intent);	
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
}
