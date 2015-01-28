package sde.android.yatv;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import java.util.HashMap;

/**
 * Created by SDesmedt on 27/01/2015.
 */
public class TouchVisualizerViewGroupView extends ViewGroup {

    private boolean interceptTouchEvent;
    private float stopChild1CaptureTimeOut;
    private long startChild1CaptureTime;

    public TouchVisualizerViewGroupView(Context context) {
        super(context);

        child1 = new TouchVisualizerSingleTouchGraphicView(context, true, Color.YELLOW);
        child2 = new TouchVisualizerSingleTouchGraphicView(context, true, Color.RED);
        child3 = new TouchVisualizerSingleTouchGraphicView(context, true, Color.GREEN);
        child4 = new TouchVisualizerSingleTouchGraphicView(context, true, Color.BLUE);

        this.addView(child1);
        this.addView(child2);
        this.addView(child3);
        this.addView(child4);

        interceptPaint.setColor(Color.RED);
        markerPaint.setColor(Color.WHITE);
    }

    @Override
    public boolean onInterceptTouchEvent (MotionEvent ev)
    {
        lastInterceptX = ev.getX();
        lastInterceptY = ev.getY();

        this.invalidate();

        return interceptTouchEvent;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(callBaseClass)
        {
            super.onTouchEvent(event);
        }

        if(!handleOnTouchEvent)
        {
            return false;
        }

        int action = event.getAction();

        boolean result = false;
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                downX = event.getX();
                downY = event.getY();
                if (returnValueOnActionDown)
                {
                    result = returnValueOnActionDown;
                }
                break;
            case MotionEvent.ACTION_MOVE:
                downX = event.getX();
                downY = event.getY();
                if (returnValueOnActionMove)
                {
                    result = returnValueOnActionMove;
                }
                break;
            case MotionEvent.ACTION_UP:
                downX = -1;
                downY = -1;
                if (returnValueOnActionUp)
                {
                    result = returnValueOnActionUp;
                }
                break;
            case MotionEvent.ACTION_OUTSIDE:
                break;
        }
        invalidate();
        return result;
    }

    @Override
    public void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);

        interceptPaint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(padding / 2, lastInterceptY, padding / 2, interceptPaint);
        canvas.drawCircle(lastInterceptX, padding/2, padding/2, interceptPaint);

        if(downX > 0)
        {
            markerPaint.setStyle(Paint.Style.FILL);
            canvas.drawCircle(downX, downY, getScreenSize(touchCircleRadius), markerPaint);
        }

    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        final int count = getChildCount();

        int childWidth = (Math.abs(right - left) - (3*padding))/2;
        int childHeight = (Math.abs(bottom - top) - (3*padding))/2;

        for (int i = 0; i < count; i++) {
            final View child = getChildAt(i);

            if (child == child1) {
                child.layout(left + padding,
                        top + padding,
                        right - (childWidth + 2*padding),
                        bottom - (childHeight + 2*padding));
            }

            if (child == child2) {
                child.layout(left + (childWidth + 2*padding),
                        top + padding,
                        right - padding,
                        bottom - (childHeight + 2*padding));
            }

            if (child == child3) {
                child.layout(left + padding,
                        top + (childHeight + 2*padding),
                        right - (childWidth + 2*padding),
                        bottom - padding);
            }

            if (child == child4) {
                child.layout(left + (childWidth + 2*padding),
                        top + (childHeight + 2*padding),
                        right - padding,
                        bottom - padding);
            }
        }
    }

    public boolean getInterceptTouchEvent() {
        return interceptTouchEvent;
    }

    public void setInterceptTouchEvent(boolean interceptTouchEvent) {
        this.interceptTouchEvent = interceptTouchEvent;
    }

    public float getStopChild1CaptureTimeOut() {
        return stopChild1CaptureTimeOut;
    }

    public void setStopChild1CaptureTimeOut(float stopChild1CaptureTimeOut) {
        this.stopChild1CaptureTimeOut = stopChild1CaptureTimeOut;
        this.startChild1CaptureTime = System.currentTimeMillis();
    }

    public float getScreenSize(float lengthInMm)
    {
        //Size_in_mm = Size_in_inches * 25.4;
        //Size_in_inches = Size_in_mm / 25.4;
        //Size_in_dp = Size_in_inches * 160;
        //Size_in_dp = (Size_in_mm / 25.4) * 160;
        //Size_in_inches = Size_in_dp / 160;
        return TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                (float) (lengthInMm * 160 / 25.4),
                getResources().getDisplayMetrics());
    }

    static int padding = 40;

    private float touchCircleRadius = (float) DefaultValues.TouchCircleRadius;

    private Paint interceptPaint = new Paint();
    private Paint markerPaint = new Paint();

    private float lastInterceptX = -1;
    private float lastInterceptY = -1;

    private float downX = -1;
    private float downY = -1;

    private TouchVisualizerSingleTouchGraphicView child1;
    private TouchVisualizerSingleTouchGraphicView child2;
    private TouchVisualizerSingleTouchGraphicView child3;
    private TouchVisualizerSingleTouchGraphicView child4;

    private boolean callBaseClass = true;
    private boolean handleOnTouchEvent = true;
    private boolean returnValueOnActionDown = true;
    private boolean returnValueOnActionMove = true;
    private boolean returnValueOnActionUp = true;
}
