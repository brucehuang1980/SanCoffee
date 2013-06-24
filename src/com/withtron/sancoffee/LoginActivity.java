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
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;


public class LoginActivity extends Activity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Log.d("LoginActivity", "onCreate");
		super.onCreate(savedInstanceState);
		
        if(((SanCoffeeApp) getApplication()).getFirstRun()){
    		setContentView(R.layout.activity_login);
            
    		RelativeLayout rl = (RelativeLayout)findViewById(R.id.login_layout);
            DisplayMetrics dm = new DisplayMetrics();   
            getWindowManager().getDefaultDisplay().getMetrics(dm); //先取得螢幕解析度  
            int screenWidth = dm.widthPixels;   //取得螢幕的寬
            int screenHeight = dm.heightPixels;
            int buttonWidth = 268*screenWidth/400;
            int buttonHeight = 46*screenHeight/800;
            int title_bar_height = 76*screenHeight/800;
            
            Button fb_button = new Button(this);
            // 400*800, 66,522, w:268 h:46
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(buttonWidth, buttonHeight);
            params.leftMargin = 66*screenWidth/400; // 66/400
            params.topMargin = 522*screenHeight/800 - title_bar_height; // 522/800
            fb_button.setLayoutParams(params);
            //fb_button.setText(getString(R.string.facebook_login));
            fb_button.setBackgroundDrawable(getResources().getDrawable(R.drawable.button_fb_login));
            fb_button.setOnClickListener(new View.OnClickListener() {
            	public void onClick(View view) {
                    Intent intent = new Intent(LoginActivity.this, FacebookLoginActivity.class);
                    startActivity(intent);	
            	}
            });
            rl.addView(fb_button);
            

            Button button_sanlogin = new Button(this);
            RelativeLayout.LayoutParams params1 = new RelativeLayout.LayoutParams(buttonWidth, buttonHeight);
            params1.leftMargin = 66*screenWidth/400; 
            params1.topMargin = 590*screenHeight/800 - title_bar_height;
            button_sanlogin.setLayoutParams(params1);
            //fb_button.setText(getString(R.string.san_login));
            button_sanlogin.setBackgroundDrawable(getResources().getDrawable(R.drawable.button_san_login));
            button_sanlogin.setOnClickListener(new View.OnClickListener() {
            	public void onClick(View view) { 
                    Intent intent = new Intent(LoginActivity.this, SanLoginActivity.class);
                    startActivity(intent);		
            	}
            });
            rl.addView(button_sanlogin);
            
            Button button_signup = new Button(this);
            RelativeLayout.LayoutParams params2 = new RelativeLayout.LayoutParams(buttonWidth, buttonHeight);
            params2.leftMargin = 66*screenWidth/400; 
            params2.topMargin = 654*screenHeight/800 - title_bar_height;
            button_signup.setLayoutParams(params2);
            //fb_button.setText(getString(R.string.sign_up));
            button_signup.setBackgroundDrawable(getResources().getDrawable(R.drawable.button_san_signup));
            button_signup.setOnClickListener(new View.OnClickListener() {
            	public void onClick(View view) {                     
                    Intent intent = new Intent(LoginActivity.this, SanSignUpActivity.class);
                    startActivity(intent);		
            	}
            });
            rl.addView(button_signup);
            
            Button button_dontlogin = new Button(this);
            RelativeLayout.LayoutParams params3 = new RelativeLayout.LayoutParams(buttonWidth, buttonHeight);
            params3.leftMargin = 66*screenWidth/400; 
            params3.topMargin = 720*screenHeight/800 - title_bar_height;
            button_dontlogin.setLayoutParams(params3);
            button_dontlogin.setBackgroundDrawable(getResources().getDrawable(R.drawable.button_do_not_login));
            //fb_button.setText(getString(R.string.dont_login));
            button_dontlogin.setOnClickListener(new View.OnClickListener() {
            	public void onClick(View view) {
                    Intent intent = new Intent(LoginActivity.this, FragmentTabsActivity.class);
                    startActivity(intent);	        		
            	}
            });
            rl.addView(button_dontlogin);
            
		}
        else{
            Intent intent = new Intent(this, FragmentTabsActivity.class);
            startActivity(intent);		
        }
	}
}
