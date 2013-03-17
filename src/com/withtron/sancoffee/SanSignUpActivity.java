package com.withtron.sancoffee;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class SanSignUpActivity extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Log.d("SanSignUpActivity", "onCreate");
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_san_sign_up);
		
		
        final Button button_back = (Button) findViewById(R.id.san_sign_up_back);
        button_back.setOnClickListener(new View.OnClickListener() {
        	public void onClick(View view) { 
                Intent intent = new Intent(SanSignUpActivity.this, LoginActivity.class);
                startActivity(intent);	   
        	}
        });
        
        final Button button_register = (Button) findViewById(R.id.register);
        button_register.setOnClickListener(new View.OnClickListener() {
        	public void onClick(View view) { 
        		 new AlertDialog.Builder(SanSignUpActivity.this).setMessage(R.string.sign_up_success).setPositiveButton(R.string.ok,new DialogInterface.OnClickListener(){public void onClick(DialogInterface dialog, int which) {
                     Intent intent = new Intent(SanSignUpActivity.this, FragmentTabs.class);
                     startActivity(intent);	       			 
        		 }}).show();
        	}
        });
        
        final Button button_fb_login = (Button) findViewById(R.id.sign_up_fb_login);
        button_fb_login.setOnClickListener(new View.OnClickListener() {
        	public void onClick(View view) { 
                Intent intent = new Intent(SanSignUpActivity.this, FacebookLoginActivity.class);
                startActivity(intent);	
        	}
        });
	}

}
