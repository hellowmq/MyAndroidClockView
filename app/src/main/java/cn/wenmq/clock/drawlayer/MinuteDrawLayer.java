package cn.wenmq.clock.drawlayer;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;

import java.util.Calendar;

public class MinuteDrawLayer implements DrawLayer {
    public static final int ARC_START_ANGLE = -90;
    public static final float LINE_WIDTH = 10f;
    public static final int SECOND_PER_MINUTE = 60;
    public static final int MINUTE_PER_HOUR = 60;
    public static final int LAYER_COLOR = 0x7FFF0000;
    public static final float PI = 3.14159f;
    public static final float ARC_WHOLE = PI * 2;
    private final Paint mPaint;
    private final RectF mRectF;
    private float[] mDialPosition;

    public MinuteDrawLayer() {
        mPaint = new Paint();
        mPaint.setColor(LAYER_COLOR);
        mPaint.setStrokeWidth(LINE_WIDTH);
        mPaint.setAntiAlias(true);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mRectF = new RectF();
        mDialPosition = new float[4];
    }

    @Override
    public void onDraw(Canvas canvas, int cX, int cY, int w, int h, Calendar calendar) {
        float progress = calendar.get(Calendar.MINUTE) * SECOND_PER_MINUTE + calendar.get(Calendar.SECOND);
        float maxRadius = (Math.min(w, h) - LINE_WIDTH) / 2 * 0.6f;
        mRectF.left = cX - maxRadius;
        mRectF.right = cX + maxRadius;
        mRectF.top = cY - maxRadius;
        mRectF.bottom = cY + maxRadius;
        float sweepAngle = progress / SECOND_PER_MINUTE / MINUTE_PER_HOUR * ARC_WHOLE;
        mDialPosition[0] = cX;
        mDialPosition[1] = cY;
        mDialPosition[2] = (float) (cX + maxRadius * Math.sin(sweepAngle));
        mDialPosition[3] = (float) (cY - maxRadius * Math.cos(sweepAngle));
        canvas.drawLines(mDialPosition, mPaint);
    }
}
