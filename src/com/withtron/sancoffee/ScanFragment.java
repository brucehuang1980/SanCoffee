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

		IntentIntegrator integrator = new IntentIntegrator(getActivity());
		integrator.initiateScan();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		Log.d("ScanFragment", "onCreateView");
		return inflater.inflate(R.layout.activity_scan, container, false);
	}
}
