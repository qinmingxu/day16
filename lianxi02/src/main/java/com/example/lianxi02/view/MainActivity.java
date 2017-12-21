package com.example.lianxi02.view;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.example.lianxi02.R;

public class MainActivity extends AppCompatActivity {

    private ImageView mImage;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if(msg.what==0){
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(intent);
                finish();
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        handler.sendEmptyMessageDelayed(0,3000);

    }

    private void initView() {
        mImage = (ImageView) findViewById(R.id.image);
        method3();

    }
    private void method3() {
        ObjectAnimator transY = ObjectAnimator.ofFloat(mImage, View.TRANSLATION_Y, 0f, 300f);
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(mImage, View.SCALE_X, 2.0f, 1.0f);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(mImage, View.SCALE_Y, 2.0f, 1f);

        ObjectAnimator alpha = ObjectAnimator.ofFloat(mImage, View.ALPHA, 0, 1);
        ObjectAnimator rotateX  = ObjectAnimator.ofFloat(mImage, View.ROTATION, 0, 360);
        //用于存储其它的动画
        AnimatorSet set = new AnimatorSet();
        //就是把所有的动画，一起执行
     set.playTogether(transY, scaleX,alpha,scaleY,rotateX);
//        set.playSequentially(transX, scaleX);
        //set.play(transX).after(scaleX);
        set.setDuration(3000);

        set.start();

    }
}
