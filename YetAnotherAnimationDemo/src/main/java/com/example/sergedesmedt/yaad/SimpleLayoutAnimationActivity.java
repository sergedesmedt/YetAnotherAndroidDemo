package com.example.sergedesmedt.yaad;

import android.animation.AnimatorInflater;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.sergedesmedt.yaad.R;

public class SimpleLayoutAnimationActivity extends Activity {
    private ViewGroup containerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_layout_animation);

        containerView = (ViewGroup) findViewById(R.id.container);
    }

    public void startInsertionAtStart(View arg0) {
        View itemView = (View) LayoutInflater.from(this).inflate(R.layout.layout_animation_item, containerView, false);
        containerView.addView(itemView, 0);
    }

    public void startInsertionAtEnd(View arg0) {
        View itemView = (View) LayoutInflater.from(this).inflate(R.layout.layout_animation_item, containerView, false);
        containerView.addView(itemView);
    }

}
