package space.almoder.therhombus;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import androidx.core.content.ContextCompat;

public class Shape extends View {

    private Paint backgroundPaint;
    private Rect backgroundRect;

    public Shape(Context context) {
        super(context, null);
    }

    public Shape(Context context, AttributeSet attrs) {
        super(context, attrs);
        backgroundPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        backgroundPaint.setColor(ContextCompat.getColor(context, R.color.shapeDefault));
        backgroundRect = new Rect();
        backgroundRect.set(getLeft(), getTop(), getRight(), getBottom());
    }

    @Override
    protected void onDraw(Canvas canvas) {
       canvas.drawColor(Color.BLACK);
       canvas.drawRect(backgroundRect, backgroundPaint);
    }
}
