package sde.android.yadd;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

/**
 * Created by sergedesmedt on 18/02/15.
 */
public class CustomDrawableGroupViewChildView extends View {

    public CustomDrawableGroupViewChildView(int childId, Context context, int backgroundColor) {
        super(context);

        this.childId = childId;

        this.backgroundColor = backgroundColor;
        paint.setColor(this.backgroundColor);
    }

    @Override
    public void onDraw(Canvas canvas) {
        // draw a border so we can differentiate the children in thei parent
        paint.setStyle(Paint.Style.STROKE);
        paint.setStyle(Paint.Style.FILL);

        canvas.drawRect(0, 0, this.getWidth()-1, this.getHeight()-1, paint);

    }

    private int childId;
    private int backgroundColor;
    private Paint paint = new Paint();
}
