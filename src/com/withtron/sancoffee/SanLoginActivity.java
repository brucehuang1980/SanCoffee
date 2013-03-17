package com.withtron.sancoffee;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class SanLoginActivity extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Log.d("SanLoginActivity", "onCreate");
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_san_login);
		
        final Button button_back = (Button) findViewById(R.id.san_login_back);
        button_back.setOnClickListener(new View.OnClickListener() {
        	public void onClick(View view) { 
                Intent intent = new Intent(SanLoginActivity.this, LoginActivity.class);
                startActivity(intent);	   
        	}
        });
        
        final Button button_san_login = (Button) findViewById(R.id.san_login);
        button_san_login.setOnClickListener(new View.OnClickListener() {
        	public void onClick(View view) { 
        		 new AlertDialog.Builder(SanLoginActivity.this).setMessage(R.string.invalid_account_password).setPositiveButton(R.string.ok,new DialogInterface.OnClickListener(){public void onClick(DialogInterface dialog, int which) {}}).show();
        	}
        });
        
        final Button button_fb_login = (Button) findViewById(R.id.san_login_fb_login);
        button_fb_login.setOnClickListener(new View.OnClickListener() {
        	public void onClick(View view) { 
                Intent intent = new Intent(SanLoginActivity.this, FacebookLoginActivity.class);
                startActivity(intent);	
        	}
        });
        
        final Button button_forget_password = (Button) findViewById(R.id.forget_password);
        button_forget_password.setOnClickListener(new View.OnClickListener() {
        	public void onClick(View view) { 
        		new AlertDialog.Builder(SanLoginActivity.this).setMessage(R.string.send_password_to_mail).setPositiveButton(R.string.ok,new DialogInterface.OnClickListener(){public void onClick(DialogInterface dialog, int which) {}}).show();
        	}
        });
	}

}
