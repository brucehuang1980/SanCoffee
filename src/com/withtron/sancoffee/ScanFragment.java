package com.withtron.sancoffee;

import com.withtron.sancoffee.R;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class ScanFragment extends Fragment {
	private EditText m_et;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		Log.d("ScanFragment", "onCreate");
		super.onCreate(savedInstanceState);


	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		Log.d("ScanFragment", "onCreateView");
		View view = inflater.inflate(R.layout.activity_scan, container, false);
		
		RelativeLayout rl = (RelativeLayout)view.findViewById(R.id.scan_layout);
        DisplayMetrics dm = new DisplayMetrics();   
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm); //先取得螢幕解析度  
        int screenWidth = dm.widthPixels;   //取得螢幕的寬
        int screenHeight = dm.heightPixels;
        
        Button button_camera = new Button(getActivity());
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(100, 100);
        params.leftMargin = 100; 
        params.topMargin = 0;
        button_camera.setLayoutParams(params);
        button_camera.setBackgroundDrawable(getResources().getDrawable(R.drawable.btn_scan_camera));
        button_camera.setOnClickListener(new View.OnClickListener() {
        	public void onClick(View view) {
        		IntentIntegrator integrator = new IntentIntegrator(getActivity());
        		integrator.initiateScan();
        	}
        });
        rl.addView(button_camera);
        

        TextView tv_or = new TextView(getActivity());
        RelativeLayout.LayoutParams params3 = new RelativeLayout.LayoutParams(200, 100);
        params3.leftMargin = 100; // 66/400
        params3.topMargin = 200;
        tv_or.setLayoutParams(params3);
        tv_or.setTextColor(Color.parseColor("#ffffff"));
        tv_or.setText(R.string.scan_or);
        rl.addView(tv_or);
        
        TextView tv_input_sn = new TextView(getActivity());
        RelativeLayout.LayoutParams params4 = new RelativeLayout.LayoutParams(200, 100);
        params4.leftMargin = 100; // 66/400
        params4.topMargin = 300;
        tv_input_sn.setLayoutParams(params4);
        tv_input_sn.setTextColor(Color.parseColor("#ffffff"));
        tv_input_sn.setText(R.string.scan_input_sn);
        rl.addView(tv_input_sn);
        
        m_et = new EditText(getActivity());
        RelativeLayout.LayoutParams params5 = new RelativeLayout.LayoutParams(300, 100);
        params5.leftMargin = 100; // 66/400
        params5.topMargin = 400;
        m_et.setLayoutParams(params5);
        rl.addView(m_et);
                
        Button button_ok = new Button(getActivity());
        RelativeLayout.LayoutParams params2 = new RelativeLayout.LayoutParams(200, 100);
        params2.leftMargin = 100; // 66/400
        params2.topMargin = 500;
        button_ok.setLayoutParams(params2);
        //button_ok.setBackgroundDrawable(getResources().getDrawable(R.drawable.btn_scan_camera));
        button_ok.setText(R.string.scan_ok);
        button_ok.setOnClickListener(new View.OnClickListener() {
        	public void onClick(View view) {

        	}
        });
        rl.addView(button_ok);
        return view;
        
	}
	
	public void setQRCode(String code){
		m_et.setText(code);
	}
}
