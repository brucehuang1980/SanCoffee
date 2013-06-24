package com.withtron.sancoffee;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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
        		EditText editAccount = (EditText) findViewById(R.id.sign_up_account);
        		EditText editPassword = (EditText) findViewById(R.id.sign_up_password);
        		EditText editPassword2 = (EditText) findViewById(R.id.sign_up_password2);
        		EditText editNickname = (EditText) findViewById(R.id.sign_up_nickname);
        		if (editAccount.length() == 0 || editPassword.length() == 0 || editPassword2.length() == 0 || editNickname.length() == 0 || 
        				editPassword.getText().toString().equals(editPassword2.getText().toString()) == false){
					new AlertDialog.Builder(SanSignUpActivity.this).setMessage(R.string.sign_up_confirm).setPositiveButton(R.string.ok,new DialogInterface.OnClickListener(){public void onClick(DialogInterface dialog, int which) {
					}}).show();
        		}
        		else{
        			new SignUpRequestTask().execute( String.format("http://coffee.trendy-co.com/mobile.php?action=reg&account=%s&pwd=%s&cpwd=%s&member_name=%s", 
        					editAccount.getText().toString(), editPassword.getText().toString(), editPassword2.getText().toString(), editNickname.getText().toString()));
        		}
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
	
	class Login {
		  public int result = 0;
		  public String msg = "";
		  Login() {
		  }
		}

    public class SignUpRequestTask extends RequestTask{

        @Override
		protected String doInBackground(String... uri) {
			// TODO Auto-generated method stub
        	Log.d("SanSignUpActivity", "SignUpRequestTask uri = " + uri);
        	String body = getHttpRequest(uri);
        	Log.d("SanSignUpActivity", "SignUpRequestTask body = " + body);
            return body;
		}

		@Override
        protected void onPostExecute(String result) {
        	super.onPostExecute(result);
    		Gson gson = new Gson();
    		Login login = gson.fromJson(result, Login.class);
			if (login.result == 0){ // Success,
				new AlertDialog.Builder(SanSignUpActivity.this).setMessage(R.string.sign_up_success).setPositiveButton(R.string.ok,new DialogInterface.OnClickListener(){public void onClick(DialogInterface dialog, int which) {
					Intent intent = new Intent(SanSignUpActivity.this, FragmentTabsActivity.class);
					startActivity(intent);	       			 
				}}).show();
			}else if (login.result ==1){
				new AlertDialog.Builder(SanSignUpActivity.this).setMessage(R.string.sign_up_already_registed).setPositiveButton(R.string.ok,new DialogInterface.OnClickListener(){public void onClick(DialogInterface dialog, int which) {
				}}).show();
			}
			else{
				new AlertDialog.Builder(SanSignUpActivity.this).setMessage(login.msg).setPositiveButton(R.string.ok,new DialogInterface.OnClickListener(){public void onClick(DialogInterface dialog, int which) {
				}}).show();
			}
        }
    }
}
