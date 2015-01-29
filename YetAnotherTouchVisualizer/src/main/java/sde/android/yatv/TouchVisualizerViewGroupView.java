package sde.android.yatv;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by SDesmedt on 27/01/2015.
 */
public class TouchVisualizerViewGroupView extends ViewGroup implements TouchListener {

    private boolean interceptTouchEvent;
    private float startReturnTrueTimeOut = -1;
    private long beginReturnTrueTimeOut = -1;
    private long remainderReturnTrueTimeOut = 0;

    public TouchVisualizerViewGroupView(Context context) {
        super(context);

        child1 = new TouchVisualizerViewGroupChildView(1, context, true, Color.YELLOW, this);
        child2 = new TouchVisualizerViewGroupChildView(2, context, true, Color.RED, this);
        child3 = new TouchVisualizerViewGroupChildView(3, context, true, Color.GREEN, this);
        child4 = new TouchVisualizerViewGroupChildView(4, context, true, Color.BLUE, this);

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

        remainderReturnTrueTimeOut = Math.abs(beginReturnTrueTimeOut - System.currentTimeMillis());
        if((startReturnTrueTimeOut != -1)
                && (interceptTouchEvent == false)
                && (remainderReturnTrueTimeOut > startReturnTrueTimeOut)) {
            startReturnTrueTimeOut = -1;
            interceptTouchEvent = !interceptTouchEvent;
        }

        return interceptTouchEvent;
    }

    @Override
    public void onTouchHappened(int child, int action, float x, float y) {
        childId = child;
        childAction = action;
        if(action != MotionEvent.ACTION_UP && action != MotionEvent.ACTION_CANCEL) {
            childDownX = x;
            childDownY = y;
        }
        else
        {
            childDownX = -1;
            childDownY = -1;
        }
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

        if(childDownX > 0)
        {
            Point ulCorner = getChildULCorner(childId, left, top, right, bottom);
            interceptPaint.setStyle(Paint.Style.STROKE);
            canvas.drawCircle(ulCorner.x + childDownX, ulCorner.y + childDownY, getScreenSize(touchCircleRadius + pressureRingOffset), interceptPaint);
        }

        if(beginReturnTrueTimeOut != -1) {
            canvas.drawText(String.valueOf(remainderReturnTrueTimeOut) + "?" + String.valueOf(startReturnTrueTimeOut), 0, 10, markerPaint);
        }

    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        final int count = getChildCount();

        this.left = left;
        this.top = top;
        this.right = right;
        this.bottom = bottom;

        childWidth = (Math.abs(right - left) - (3*padding))/2;
        childHeight = (Math.abs(bottom - top) - (3*padding))/2;

        for (int i = 0; i < count; i++) {
            final View child = getChildAt(i);
            Point ulCorner = getChildULCorner(i + 1, left, top, right, bottom);

            if (child == child1) {
                child.layout(ulCorner.x,
                        ulCorner.y,
                        right - (childWidth + 2*padding),
                        bottom - (childHeight + 2*padding));
            }

            if (child == child2) {
                child.layout(ulCorner.x,
                        ulCorner.y,
                        right - padding,
                        bottom - (childHeight + 2*padding));
            }

            if (child == child3) {
                child.layout(ulCorner.x,
                        ulCorner.y,
                        right - (childWidth + 2*padding),
                        bottom - padding);
            }

            if (child == child4) {
                child.layout(ulCorner.x,
                        ulCorner.y,
                        right - padding,
                        bottom - padding);
            }
        }
    }

    private Point getChildULCorner(int childId, int left, int top, int right, int bottom)
    {
        int childWidth = (Math.abs(right - left) - (3*padding))/2;
        int childHeight = (Math.abs(bottom - top) - (3*padding))/2;

        if(childId == 1)
            return new Point(left + padding, top + padding);
        if(childId == 2)
            return new Point(left + (childWidth + 2*padding), top + padding);
        if(childId == 3)
            return new Point(left + padding, top + (childHeight + 2*padding));
        if(childId == 4)
            return new Point(left + (childWidth + 2*padding), top + (childHeight + 2*padding));

        return null;
    }

    public boolean getInterceptTouchEvent() {
        return interceptTouchEvent;
    }

    public void setInterceptTouchEvent(boolean interceptTouchEvent) {
        this.interceptTouchEvent = interceptTouchEvent;
    }

    public float getStopChild1CaptureTimeOut() {
        return child1.getStopChild1CaptureTimeOut();
    }

    public void setStopChild1CaptureTimeOut(float stopChild1CaptureTimeOut) {
        this.child1.setStopChild1CaptureTimeOut(stopChild1CaptureTimeOut);
    }

    public void setStartReturnTrueTimeOut(float startReturnTrueTimeOut) {
        this.startReturnTrueTimeOut = startReturnTrueTimeOut;
        this.beginReturnTrueTimeOut = System.currentTimeMillis();
    }

    public float getStartReturnTrueTimeOut() {
        return startReturnTrueTimeOut;
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

    int left = 0;
    int top = 0;
    int right = 0;
    int bottom = 0;
    int childWidth = 0;
    int childHeight = 0;

    private float touchCircleRadius = (float) DefaultValues.TouchCircleRadius;
    private float pressureRingOffset = (float) DefaultValues.PressureRingOffset;

    private Paint interceptPaint = new Paint();
    private Paint markerPaint = new Paint();

    private float lastInterceptX = -1;
    private float lastInterceptY = -1;

    private float downX = -1;
    private float downY = -1;

    private int childId = -1;
    private int childAction = -1;
    private float childDownX = -1;
    private float childDownY = -1;

    private TouchVisualizerViewGroupChildView child1;
    private TouchVisualizerViewGroupChildView child2;
    private TouchVisualizerViewGroupChildView child3;
    private TouchVisualizerViewGroupChildView child4;

    private boolean callBaseClass = true;
    private boolean handleOnTouchEvent = true;
    private boolean returnValueOnActionDown = true;
    private boolean returnValueOnActionMove = true;
    private boolean returnValueOnActionUp = true;
}
