package com.withtron.sancoffee;

import com.google.gson.Gson;
import com.withtron.sancoffee.SanCoffeeApp.LoginType;
import com.withtron.sancoffee.SanSignUpActivity.SignUpRequestTask;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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
				EditText editAccount = (EditText) findViewById(R.id.san_account);
				EditText editPassword = (EditText) findViewById(R.id.san_password);
        		if (editAccount.length() == 0 || editPassword.length() == 0 ){
					new AlertDialog.Builder(SanLoginActivity.this).setMessage(R.string.invalid_account_password).setPositiveButton(R.string.ok,new DialogInterface.OnClickListener(){public void onClick(DialogInterface dialog, int which) {
					}}).show();
        		}
        		else{
        			new SanLoginRequestTask().execute( String.format("http://coffee.trendy-co.com/mobile.php?action=login&acc=%s&pwd=%s", 
        					editAccount.getText().toString(), editPassword.getText().toString()));
        		}
				 //new AlertDialog.Builder(SanLoginActivity.this).setMessage(R.string.invalid_account_password).setPositiveButton(R.string.ok,new DialogInterface.OnClickListener(){public void onClick(DialogInterface dialog, int which) {}}).show();
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
	class SanLogin {
		  public int result = 0;
		  public String msg = "";
		  public String sid = "";
		  SanLogin() {
		  }
		}


    public class SanLoginRequestTask extends RequestTask{

        @Override
		protected String doInBackground(String... uri) {
			// TODO Auto-generated method stub
        	Log.d("SanLoginActivity", "SanLoginRequestTask uri = " + uri);
        	String body = getHttpRequest(uri);
        	Log.d("SanLoginActivity", "SanLoginRequestTask body = " + body);
            return body;
		}

		@Override
        protected void onPostExecute(String result) {
        	super.onPostExecute(result);
    		Gson gson = new Gson();
    		SanLogin login = gson.fromJson(result, SanLogin.class);
			if (login.result == 0){ // Success,
				((SanCoffeeApp) getApplication()).setSanSessionID(login.sid);
				((SanCoffeeApp) getApplication()).setLoginType(LoginType.SanApp.ordinal());
				((SanCoffeeApp) getApplication()).setRunned();
				Intent intent = new Intent(SanLoginActivity.this, FragmentTabsActivity.class);
				startActivity(intent);	 
			}else if (login.result ==1){ // msg=此帳號尚未啟用
				new AlertDialog.Builder(SanLoginActivity.this).setMessage(login.msg).setPositiveButton(R.string.ok,new DialogInterface.OnClickListener(){public void onClick(DialogInterface dialog, int which) {
				}}).show();
			}
			else{
				new AlertDialog.Builder(SanLoginActivity.this).setMessage(login.msg).setPositiveButton(R.string.ok,new DialogInterface.OnClickListener(){public void onClick(DialogInterface dialog, int which) {
				}}).show();
			}
        }
    }
}
