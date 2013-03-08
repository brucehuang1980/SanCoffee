package com.withtron.sancoffee;

import android.app.Activity;
import android.os.Bundle;

public class SingleListItem extends Activity{
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_item_detail);
 
        //TextView txtProduct = (TextView) findViewById(R.id.product_label);
 
        //Intent i = getIntent();
        // getting attached intent data
        //String product = i.getStringExtra("product");
        // displaying selected product name
        //txtProduct.setText(product);
 
    }
}
