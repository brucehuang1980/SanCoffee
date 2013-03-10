package com.withtron.sancoffee;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class SingleListItemActivity extends Activity{
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_item_detail);
 
        Bundle extras = getIntent().getExtras();
        if (extras != null){
        	String title = extras.getString(SqlOpenHelper.NEWS_COLUMN_TITLE);
        	if (title != null && title.length() > 0){
        		TextView tv = (TextView) findViewById(R.id.item_detail_title);
        		tv.setText(title);
        	}
        	
        	String thumbnailUri = extras.getString(SqlOpenHelper.NEWS_COLUMN_THUMBNAIL_URI);
        	if (thumbnailUri != null && thumbnailUri.length() > 0){
        		ImageView iv = (ImageView) findViewById(R.id.item_detail_image);
        		iv.setImageURI(Uri.parse("file:" + thumbnailUri));
        	}
        }
    }
}
