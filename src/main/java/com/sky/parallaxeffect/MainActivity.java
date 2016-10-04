package com.sky.parallaxeffect;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView () {
        ParallaxeListView lv = (ParallaxeListView) findViewById(R.id.lv);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, Cheeses.NAMES);
        View view = View.inflate(this, R.layout.header, null);
        lv.addHeaderView(view);
        ImageView headView = (ImageView) view.findViewById(R.id.head);
        lv.setImageView(headView);

        lv.setAdapter(adapter);
    }

}
