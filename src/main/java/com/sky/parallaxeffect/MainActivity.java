package com.sky.parallaxeffect;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (Build.VERSION.SDK_INT >= 19) {
            //透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        }

        initView();
    }

    private void initView () {
        ParallaxeListView lv = (ParallaxeListView) findViewById(R.id.lv);

        BaseAdapter adapter = new MyAdapter();

        View view = View.inflate(this, R.layout.header, null);
        lv.addHeaderView(view);
        ImageView headView = (ImageView) view.findViewById(R.id.head);
        lv.setImageView(headView);

        lv.setAdapter(adapter);

    }

}
