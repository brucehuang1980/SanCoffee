package com.withtron.sancoffee;


import com.withtron.sancoffee.R;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class ScanProductFragment extends Fragment {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		Log.d("ScanProductFragment", "onCreate");
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		Log.d("ScanProductFragment", "onCreateView");
		View view = inflater.inflate(R.layout.scan_product_fragment, container, false);
		RelativeLayout rl = (RelativeLayout)view.findViewById(R.id.scan_product_layout);

        TextView tvScanName = new TextView(getActivity());
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(200, 100);
        params.leftMargin = 30; 
        params.topMargin = 30;
        tvScanName.setLayoutParams(params);
        tvScanName.setTextColor(Color.parseColor("#ffffff"));
        tvScanName.setText(R.string.scan_name);
        rl.addView(tvScanName);
        
        TextView tvBakeDegree = new TextView(getActivity());
        params = new RelativeLayout.LayoutParams(200, 100);
        params.leftMargin = 30; 
        params.topMargin = 130;
        tvBakeDegree.setLayoutParams(params);
        tvBakeDegree.setTextColor(Color.parseColor("#ffffff"));
        tvBakeDegree.setText(R.string.scan_bake_degree);
        rl.addView(tvBakeDegree);
        
        TextView tvScanOrigin = new TextView(getActivity());
        params = new RelativeLayout.LayoutParams(200, 100);
        params.leftMargin = 30; 
        params.topMargin = 230;
        tvScanOrigin.setLayoutParams(params);
        tvScanOrigin.setTextColor(Color.parseColor("#ffffff"));
        tvScanOrigin.setText(R.string.scan_origin);
        rl.addView(tvScanOrigin);
        
        TextView tvOpenDate = new TextView(getActivity());
        params = new RelativeLayout.LayoutParams(200, 100);
        params.leftMargin = 30; 
        params.topMargin = 330;
        tvOpenDate.setLayoutParams(params);
        tvOpenDate.setTextColor(Color.parseColor("#ffffff"));
        tvOpenDate.setText(R.string.scan_open_date);
        rl.addView(tvOpenDate);
        
        Button btnAddToMyList = new Button(getActivity());
        params = new RelativeLayout.LayoutParams(200, 100);
        params.leftMargin = 130; 
        params.topMargin = 430;
        btnAddToMyList.setLayoutParams(params);
        btnAddToMyList.setTextColor(Color.parseColor("#ffffff"));
        btnAddToMyList.setText(R.string.scan_add_to_my_list);
        rl.addView(btnAddToMyList);
		return view;
	}
}
