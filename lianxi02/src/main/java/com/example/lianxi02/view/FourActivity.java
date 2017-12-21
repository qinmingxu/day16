package com.example.lianxi02.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;


import com.example.lianxi02.R;

public class FourActivity extends AppCompatActivity {

    private TextView mText;
    /**
     * 立即下单
     */
    private TextView mTextdd;
    private String price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_four);

        final Intent intent = getIntent();
        price = intent.getStringExtra("price");
        initView();
        mTextdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(FourActivity.this, FiveActivity.class);
                startActivity(intent1);
            }
        });
    }

    private void initView() {
        mText = (TextView) findViewById(R.id.text);
        mTextdd = (TextView) findViewById(R.id.textdd);
        mText.setText("实付款¥"+price);
    }
}
