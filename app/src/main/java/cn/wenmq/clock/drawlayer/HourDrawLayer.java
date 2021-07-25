package cn.wenmq.clock.drawlayer;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;

import java.util.Calendar;

public class HourDrawLayer implements DrawLayer {
    public static final float LINE_WIDTH = 20f;
    public static final int MINUTE_PER_HOUR = 60;
    public static final int HOUR_PER_PANEL = 12;
    public static final float DIAL_LENGTH = 0.4f;
    public static final int LAYER_COLOR = 0x7FFF0000;
    public static final float PI = 3.1415926f;
    public static final float ARC_WHOLE = PI * 2;
    private final Paint mPaint = new Paint();
    private final RectF mRectF = new RectF();
    private final float[] mDialPosition = new float[4];

    public HourDrawLayer() {
        mPaint.setColor(LAYER_COLOR);
        mPaint.setStrokeWidth(LINE_WIDTH);
        mPaint.setAntiAlias(true);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
    }

    @Override
    public void onDraw(Canvas canvas, int cX, int cY, int w, int h, Calendar calendar) {
        float progress = calendar.get(Calendar.HOUR) * MINUTE_PER_HOUR
                + calendar.get(Calendar.MINUTE);
        float maxRadius = (Math.min(w, h) - LINE_WIDTH) / 2 * DIAL_LENGTH;
        mRectF.left = cX - maxRadius;
        mRectF.right = cX + maxRadius;
        mRectF.top = cY - maxRadius;
        mRectF.bottom = cY + maxRadius;
        float sweepAngle = progress / MINUTE_PER_HOUR / HOUR_PER_PANEL * ARC_WHOLE;
        mDialPosition[0] = cX;
        mDialPosition[1] = cY;
        mDialPosition[2] = (float) (cX + maxRadius * Math.sin(sweepAngle));
        mDialPosition[3] = (float) (cY - maxRadius * Math.cos(sweepAngle));
        canvas.drawLines(mDialPosition, mPaint);
    }
}
