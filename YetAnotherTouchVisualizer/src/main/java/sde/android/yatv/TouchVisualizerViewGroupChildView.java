package sde.android.yatv;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

/**
 * Created by sdesmedt on 29/01/2015.
 */
public class TouchVisualizerViewGroupChildView extends View /*implements View.OnLongClickListener, View.OnClickListener*/ {

    public TouchVisualizerViewGroupChildView(int childId, Context context, boolean drawBorder, int markerColor, TouchListener touchListener) {
        super(context);

        this.childId = childId;
        this.touchListener = touchListener;

        this.drawBorder = drawBorder;

        //this.setOnLongClickListener(this);
        //this.setOnClickListener(this);

        this.markerColor = markerColor;
        paint.setColor(this.markerColor);
    }

    @Override
    public void onDraw(Canvas canvas) {
        // draw a border so we can differentiate the children in thei parent
        paint.setStyle(Paint.Style.STROKE);
        if(isCancelled) {
            paint.setStyle(Paint.Style.FILL);
        }
        canvas.drawRect(0, 0, this.getWidth()-1, this.getHeight()-1, paint);

        if(downX > 0)
        {
            paint.setStyle(Paint.Style.FILL);
            canvas.drawCircle(downX, downY, getScreenSize(touchCircleRadius), paint);
        }

        // show the time left for the timeout to expire
        if(stopChild1CaptureTimeOut != -1) {
            canvas.drawText(String.valueOf(remainderChild1CaptureTime) + "?" + String.valueOf(stopChild1CaptureTimeOut), 0, 10, paint);
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

        if(this.touchListener != null)
        {
            this.touchListener.onTouchHappened(childId, action, event.getX(), event.getY());
        }

        remainderChild1CaptureTime = Math.abs(beginChild1CaptureTime - System.currentTimeMillis());
        if((stopChild1CaptureTimeOut != -1)
                && (remainderChild1CaptureTime > stopChild1CaptureTimeOut)) {
            stopChild1CaptureTimeOut = -1;
            return false;
        }

        boolean result = false;
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                isCancelled = false;
                downX = event.getX();
                downY = event.getY();
                if (returnValueOnActionDown)
                {
                    result = returnValueOnActionDown;
                }
                break;
            case MotionEvent.ACTION_MOVE:
                isCancelled = false;
                downX = event.getX();
                downY = event.getY();
                if (returnValueOnActionMove)
                {
                    result = returnValueOnActionMove;
                }
                break;
            case MotionEvent.ACTION_UP:
                isCancelled = false;
                downX = -1;
                downY = -1;
                if (returnValueOnActionUp)
                {
                    result = returnValueOnActionUp;
                }
                break;
            case MotionEvent.ACTION_CANCEL:
                isCancelled = true;
                downX = event.getX();
                downY = event.getY();
                result = false;
                break;
            case MotionEvent.ACTION_OUTSIDE:
                isCancelled = false;
                break;
        }
        invalidate();

        return result;
    }

//    @Override
//    public void onClick(View v) {
//        Toast msg = Toast.makeText(TouchVisualizerViewGroupChildView.this.getContext(), "onClick", Toast.LENGTH_SHORT);
//        msg.setGravity(Gravity.CENTER, msg.getXOffset() / 2, msg.getYOffset() / 2);
//        msg.show();
//    }
//
//    @Override
//    public boolean onLongClick(View v) {
//        Toast msg = Toast.makeText(TouchVisualizerViewGroupChildView.this.getContext(), "onLongClick", Toast.LENGTH_SHORT);
//        msg.setGravity(Gravity.CENTER, msg.getXOffset() / 2, msg.getYOffset() / 2);
//        msg.show();
//        return returnValueOnLongClick;
//    }

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

    public void setCallBaseClass(boolean process)
    {
        callBaseClass = process;
    }

    public boolean getCallBaseClass()
    {
        return callBaseClass;
    }

    public void setHandleTouchEvent(boolean process)
    {
        handleOnTouchEvent = process;
    }

    public boolean getHandleTouchEvent()
    {
        return handleOnTouchEvent;
    }

    public void setReturnValueOnActionDown(boolean value)
    {
        returnValueOnActionDown = value;
    }

    public boolean getReturnValueOnActionDown()
    {
        return returnValueOnActionDown;
    }

    public void setReturnValueOnActionMove(boolean value)
    {
        returnValueOnActionMove = value;
    }

    public boolean getReturnValueOnActionMove()
    {
        return returnValueOnActionMove;
    }

    public void setReturnValueOnActionUp(boolean value)
    {
        returnValueOnActionUp = value;
    }

    public boolean getReturnValueOnActionUp()
    {
        return returnValueOnActionUp;
    }

    public void setReturnValueOnLongClick(boolean value)
    {
        returnValueOnLongClick = value;
    }

    public boolean getReturnValueOnLongClick()
    {
        return returnValueOnLongClick;
    }

    public float getStopChild1CaptureTimeOut() {
        return stopChild1CaptureTimeOut;
    }

    public void setStopChild1CaptureTimeOut(float stopChild1CaptureTimeOut) {
        this.stopChild1CaptureTimeOut = stopChild1CaptureTimeOut;
        this.beginChild1CaptureTime = System.currentTimeMillis();
    }

    private int childId;

    private float touchCircleRadius = (float) DefaultValues.TouchCircleRadius;

    private int markerColor;
    private Paint paint = new Paint();

    private float downX = -1;
    private float downY = -1;
    private float pressure = 1;
    private boolean isCancelled = false;

    private float stopChild1CaptureTimeOut = -1;
    private long beginChild1CaptureTime = -1;
    private long remainderChild1CaptureTime = 0;

    private boolean drawBorder = false;

    private boolean callBaseClass = true;
    private boolean handleOnTouchEvent = true;
    private boolean returnValueOnActionDown = true;
    private boolean returnValueOnActionMove = true;
    private boolean returnValueOnActionUp = true;
    private boolean returnValueOnLongClick = false;

    private TouchListener touchListener;
}
