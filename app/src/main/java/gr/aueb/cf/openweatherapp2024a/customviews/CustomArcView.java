package gr.aueb.cf.openweatherapp2024a.customviews;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

public class CustomArcView extends View {

    private Paint arcPaint;
    private Paint fillPaint;

    public CustomArcView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        // Paint for dashed arc
        arcPaint = new Paint();
        arcPaint.setStyle(Paint.Style.STROKE);
        arcPaint.setColor(Color.LTGRAY);
        arcPaint.setStrokeWidth(24);
        arcPaint.setPathEffect(new DashPathEffect(new float[]{10, 20}, 0));
        arcPaint.setAntiAlias(true);

        // Paint for shading
        fillPaint = new Paint();
        fillPaint.setStyle(Paint.Style.FILL);
        fillPaint.setColor(Color.parseColor("#28608BC1")); // Semi-transparent
        fillPaint.setAntiAlias(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // Calculate dimensions
        int width = getWidth();
        int height = getHeight();
        int radius = Math.min(width, height) / 2;

        // Draw shaded background
        @SuppressLint("DrawAllocation") RectF oval = new RectF(0, 0, width, height * 2); // Adjust for semicircle
        canvas.drawArc(oval, 180, 180, true, fillPaint);

        // Draw dashed arc
        canvas.drawArc(oval, 180, 180, false, arcPaint);
    }
}
