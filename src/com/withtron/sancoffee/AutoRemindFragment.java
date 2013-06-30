package com.withtron.sancoffee;


import com.withtron.sancoffee.R;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

public class AutoRemindFragment extends Fragment {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		Log.d("AutoRemindFragment", "onCreate");
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		Log.d("AutoRemindFragment", "onCreateView");
		View view = inflater.inflate(R.layout.auto_remind_fragment, container, false);
		
		ImageView back_image = (ImageView)getActivity().findViewById(R.id.titel_bar_back_image);
		back_image.setOnClickListener(new View.OnClickListener() {
        	public void onClick(View view) {
        		getFragmentManager().popBackStack();
        	}
        });
		
		RelativeLayout rl = (RelativeLayout)view.findViewById(R.id.auto_remind_layout);
		
        TextView tv_autoremine = new TextView(getActivity());
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(200, 100);
        params.leftMargin = 200;
        params.topMargin = 30;
        tv_autoremine.setLayoutParams(params);
        tv_autoremine.setText(getString(R.string.setup_auto_remind));
        tv_autoremine.setTextColor(android.graphics.Color.WHITE);
        rl.addView(tv_autoremine);
        
        TextView tv_push_notification = new TextView(getActivity());
        params = new RelativeLayout.LayoutParams(200, 100);
        params.leftMargin = 30;
        params.topMargin = 130;
        tv_push_notification.setLayoutParams(params);
        tv_push_notification.setText(getString(R.string.setup_push_notification));
        tv_push_notification.setTextColor(android.graphics.Color.WHITE);
        rl.addView(tv_push_notification);
        
        ToggleButton  tb_push_notify = new ToggleButton (getActivity());
        params = new RelativeLayout.LayoutParams(200, 100);
        params.leftMargin = 200;
        params.topMargin = 130;
        tb_push_notify.setLayoutParams(params);
        tb_push_notify.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            	Log.d("AutoRemindFragment", "push_notification onCheckedChanged");
                if (isChecked) {
                } else {
                }
            }
        });
        rl.addView(tb_push_notify);
        
        TextView tv_email = new TextView(getActivity());
        params = new RelativeLayout.LayoutParams(200, 100);
        params.leftMargin = 30;
        params.topMargin = 230;
        tv_email.setLayoutParams(params);
        tv_email.setText(getString(R.string.setup_email));
        tv_email.setTextColor(android.graphics.Color.WHITE);
        rl.addView(tv_email);
        
        ToggleButton  tb_email = new ToggleButton (getActivity());
        params = new RelativeLayout.LayoutParams(200, 100);
        params.leftMargin = 200;
        params.topMargin = 230;
        tb_email.setLayoutParams(params);
        tb_email.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            	Log.d("AutoRemindFragment", "email onCheckedChanged");
                if (isChecked) {
                } else {
                }
            }
        });
        rl.addView(tb_email);
        
        TextView tv_auto_remind_news = new TextView(getActivity());
        params = new RelativeLayout.LayoutParams(200, 100);
        params.leftMargin = 30;
        params.topMargin = 330;
        tv_auto_remind_news.setLayoutParams(params);
        tv_auto_remind_news.setText(getString(R.string.setup_auto_remind_news));
        tv_auto_remind_news.setTextColor(android.graphics.Color.WHITE);
        rl.addView(tv_auto_remind_news);
        
        ToggleButton  tb_auto_remind_news = new ToggleButton (getActivity());
        params = new RelativeLayout.LayoutParams(200, 100);
        params.leftMargin = 200;
        params.topMargin = 330;
        tb_auto_remind_news.setLayoutParams(params);
        tb_auto_remind_news.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            	Log.d("AutoRemindFragment", "auto_remind_news onCheckedChanged");
                if (isChecked) {
                } else {
                }
            }
        });
        rl.addView(tb_auto_remind_news);
        
		return view;
	}
	
	@Override
	public void onDestroyView() {
		// TODO Auto-generated method stub
		Log.d("AutoRemindFragment", "onDestroyView");
		super.onDestroyView();
		ImageView back_image = (ImageView)getActivity().findViewById(R.id.titel_bar_back_image);
		back_image.setVisibility(View.GONE);	
		ImageView image = (ImageView)getActivity().findViewById(R.id.titel_bar_image);
		image.setVisibility(View.VISIBLE);
	}
}
