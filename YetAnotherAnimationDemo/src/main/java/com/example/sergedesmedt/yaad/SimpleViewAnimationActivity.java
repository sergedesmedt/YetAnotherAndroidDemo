package com.example.sergedesmedt.yaad;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class SimpleViewAnimationActivity extends Activity {
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_view_animation);

        imageView = (ImageView) findViewById(R.id.imageView1);
    }

    public void startAnimationScale(View arg0) {
        Animation scaleAnimation = AnimationUtils.loadAnimation(SimpleViewAnimationActivity.this, R.anim.simple_view_scale);
        imageView.startAnimation(scaleAnimation);
    }

    public void startAnimationTranslate(View arg0) {
        Animation scaleAnimation = AnimationUtils.loadAnimation(SimpleViewAnimationActivity.this, R.anim.simple_view_translate);
        imageView.startAnimation(scaleAnimation);
    }

    public void startAnimationScaleAndRotate(View arg0) {
        Animation scaleAnimation = AnimationUtils.loadAnimation(SimpleViewAnimationActivity.this, R.anim.simple_view_scaleandrotate_inparallel);
        imageView.startAnimation(scaleAnimation);
    }

}
