package com.withtron.sancoffee;


import com.withtron.sancoffee.R;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class AboutUsFragment extends Fragment {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO AboutUsFragment-generated method stub
		Log.d("MyFragment", "onCreate");
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		Log.d("AboutUsFragment", "onCreateView");
		View view = inflater.inflate(R.layout.about_us_fragment, container, false);
		
		ImageView back_image = (ImageView)getActivity().findViewById(R.id.titel_bar_back_image);
		back_image.setOnClickListener(new View.OnClickListener() {
        	public void onClick(View view) {
        		getFragmentManager().popBackStack();
        	}
        });
		return view;
	}
	
	@Override
	public void onDestroyView() {
		// TODO Auto-generated method stub
		Log.d("AboutUsFragment", "onDestroyView");
		super.onDestroyView();
		ImageView back_image = (ImageView)getActivity().findViewById(R.id.titel_bar_back_image);
		back_image.setVisibility(View.GONE);	
		ImageView image = (ImageView)getActivity().findViewById(R.id.titel_bar_image);
		image.setVisibility(View.VISIBLE);
	}
}
