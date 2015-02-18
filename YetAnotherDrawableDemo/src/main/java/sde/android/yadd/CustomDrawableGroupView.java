package sde.android.yadd;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by sergedesmedt on 18/02/15.
 */
public class CustomDrawableGroupView extends ViewGroup {

    public CustomDrawableGroupView(Context context) {
        super(context);

        child1 = new CustomDrawableGroupViewChildView(1, context, Color.YELLOW);
        child2 = new CustomDrawableGroupViewChildView(2, context, Color.RED);
        child3 = new CustomDrawableGroupViewChildView(3, context, Color.GREEN);
        child4 = new CustomDrawableGroupViewChildView(4, context, Color.BLUE);

        this.addView(child1);
        this.addView(child2);
        this.addView(child3);
        this.addView(child4);

        beforePaint = new Paint();
        beforePaint.setColor(Color.MAGENTA);
        beforePaint.setStyle(Paint.Style.STROKE);
        beforePaint.setStrokeWidth(10);

        afterPaint = new Paint();
        afterPaint.setColor(Color.CYAN);
        afterPaint.setStyle(Paint.Style.FILL);

    }

    @Override
    public void onDraw(Canvas canvas) {
        //super.onDraw(canvas);
    }

    @Override
    public void dispatchDraw(Canvas canvas) {
        canvas.drawCircle(this.getWidth()/2, this.getHeight()/2, 100, beforePaint);

        super.dispatchDraw(canvas);

        canvas.drawCircle(this.getWidth()/2, this.getHeight()/2, 50, afterPaint);
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

    Paint beforePaint;
    Paint afterPaint;

    static int padding = 40;

    int left = 0;
    int top = 0;
    int right = 0;
    int bottom = 0;
    int childWidth = 0;
    int childHeight = 0;

    private CustomDrawableGroupViewChildView child1;
    private CustomDrawableGroupViewChildView child2;
    private CustomDrawableGroupViewChildView child3;
    private CustomDrawableGroupViewChildView child4;
}
