package com.example.sergedesmedt.yaad;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.sergedesmedt.yaad.R;

import java.util.ArrayList;
import java.util.HashMap;

public class ComplexAnimationSampleActivity extends Activity {
    ImageView imageView;
    AnimatableCellAdapter mAdapter;
    ArrayList<CheeseViewModel> mCheeseList;
    ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_complex_animation_sample);

        imageView = (ImageView) findViewById(R.id.imageView1);

        mListView = (ListView) findViewById(R.id.listView1);
        //android.util.Log.d("Debug", "d=" + mListView.getDivider());
        mCheeseList = new ArrayList<CheeseViewModel>();
        for (int i = 0; i < Cheeses.sCheeseStrings.length; ++i) {
            mCheeseList.add(new CheeseViewModel(i, Cheeses.sCheeseStrings[i]));
        }
        mAdapter = new AnimatableCellAdapter(this,R.layout.opaque_text_view, mCheeseList);
        mListView.setAdapter(mAdapter);

    }

    public void startHeightAnimation(View arg0) {
        //CustomAnimation anim = new CustomAnimation(imageView, 200, true);
        //anim.setDuration(2000);
        ////anim.startNow();
        //
        //imageView.startAnimation(anim);

        removeRow();
    }

    HashMap<Long,Integer> mCellTopMap = new HashMap<Long,Integer>();
    private static int ANIMATION_DURATION = 500;

    public void removeRow()
    {
        // save the current top positions of all the visible cells
        int firstVisiblePosition = mListView.getFirstVisiblePosition();
        for (int i = 0; i < mListView.getChildCount(); ++i) {
            View child = mListView.getChildAt(i);
            //if (child != viewToRemove) {
                int position = firstVisiblePosition + i;
                long itemId = mAdapter.getItemId(position);
                mCellTopMap.put(itemId, child.getTop());
            //}
        }

        //mAdapter.remove(mAdapter.getItem(1));
        //mCheeseList.remove(3);
        mCheeseList.remove(1);
        mAdapter.notifyDataSetChanged();


        final ViewTreeObserver observer = mListView.getViewTreeObserver();
        observer.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            public boolean onPreDraw() {
                observer.removeOnPreDrawListener(this);

                boolean firstAnimation = true;
                int firstVisiblePosition = mListView.getFirstVisiblePosition();
                for (int i = 0; i < mListView.getChildCount(); ++i) {
                    View child = mListView.getChildAt(i);
                    int position = firstVisiblePosition + i;
                    long itemId = mAdapter.getItemId(position);

                    Integer newTop = child.getTop();
                    if(mCellTopMap.containsKey(itemId)) {
                        // the cell allready existed before the deletion
                        Integer oldTop = mCellTopMap.get(itemId);
                        int delta = oldTop - newTop;
                        child.setTranslationY(delta);
                        child.animate().setDuration(ANIMATION_DURATION).translationY(0);
                        if (firstAnimation) {
                            child.animate().withEndAction(new Runnable() {
                                public void run() {
                                    //mBackgroundContainer.hideBackground();
                                    //mSwiping = false;
                                    mListView.setEnabled(true);
                                }
                            });
                            firstAnimation = false;
                        }
                    }
                    else {
                        // the cell became visible because of the deletion of other cells
                        // calculate where it was offscreen
                        // REMARK: following code makes the assumption that all children have the same height
                        Integer oldTop = newTop + child.getHeight();
                        int delta = oldTop - newTop;
                        child.setTranslationY(delta);
                        child.animate().setDuration(ANIMATION_DURATION).translationY(0);
                        if (firstAnimation) {
                            child.animate().withEndAction(new Runnable() {
                                public void run() {
                                    //mBackgroundContainer.hideBackground();
                                    //mSwiping = false;
                                    mListView.setEnabled(true);
                                }
                            });
                            firstAnimation = false;
                        }
                    }
                }

                return true;
            }
        });
    }

    public void addRow()
    {

    }

}
