package com.withtron.sancoffee;


import com.withtron.sancoffee.R;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

public class MyFragment extends Fragment {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		Log.d("MyFragment", "onCreate");
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		Log.d("MyFragment", "onCreateView");
		//View view = inflater.inflate(R.layout.pull_to_refresh, container, false);
		View view = inflater.inflate(R.layout.relative_layout, container, false);
		RelativeLayout rl = (RelativeLayout)view.findViewById(R.id.relative_layout_container);
		Button testBtn = new Button(getActivity());
		testBtn.setText("Click to single item");
		testBtn.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
        		FragmentTransaction ft = getFragmentManager().beginTransaction();
        		ft.replace(R.id.relative_layout_container, new MyDetailFragment());
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                ft.addToBackStack(null);
                ft.commit();
			}});
		rl.addView(testBtn);
		return view;
	}
}
