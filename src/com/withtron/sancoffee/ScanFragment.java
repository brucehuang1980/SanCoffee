package com.withtron.sancoffee;


import com.withtron.sancoffee.R;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ScanFragment extends Fragment {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		Log.d("ScanFragment", "onCreate");
		super.onCreate(savedInstanceState);
		
		Intent intent = new Intent("com.google.zxing.client.android.SCAN");
		intent.putExtra("SCAN_MODE", "QR_CODE_MODE");
		startActivityForResult(intent, 0);
		//IntentIntegrator integrator = new IntentIntegrator(yourActivity);
		//integrator.initiateScan();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		Log.d("ScanFragment", "onCreateView");
		return inflater.inflate(R.layout.list, container, false);
	}
	
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == 0) {

        }
    }
}
