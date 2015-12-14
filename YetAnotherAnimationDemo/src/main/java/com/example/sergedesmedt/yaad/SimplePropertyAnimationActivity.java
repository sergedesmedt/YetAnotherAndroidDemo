package com.example.sergedesmedt.yaad;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.example.sergedesmedt.yaad.R;

public class SimplePropertyAnimationActivity extends AppCompatActivity {
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_property_animation);

        imageView = (ImageView) findViewById(R.id.imageView1);
    }

    public void startPosXAnimation(View arg0) {
        //ObjectAnimator.ofFloat(imageView, "x", 100f).setDuration(4000).start();
        ObjectAnimator set = (ObjectAnimator) AnimatorInflater.loadAnimator(SimplePropertyAnimationActivity.this,
                R.anim.simple_property_posx);
        set.setTarget(imageView);
        set.start();
    }

}
