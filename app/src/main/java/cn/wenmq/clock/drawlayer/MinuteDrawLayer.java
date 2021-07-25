package cn.wenmq.clock.drawlayer;

import static cn.wenmq.clock.CalendarConstants.ARC_WHOLE_PANEL;
import static cn.wenmq.clock.CalendarConstants.MINUTE_PER_HOUR;
import static cn.wenmq.clock.CalendarConstants.SECOND_PER_MINUTE;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import java.util.Calendar;

public class MinuteDrawLayer implements DrawLayer {
    public static final float LINE_WIDTH = 10f;
    public static final int LAYER_COLOR = 0x7FFF0000;
    public static final float FRONT_INDICATOR_LENGTH = 0.6f;
    public static final float BACK_INDICATOR_LENGTH = -0.1f;
    private final Paint mPaint = new Paint();
    private final RectF mRectF = new RectF();
    private final float[] mDialPosition = new float[4];

    public MinuteDrawLayer() {
        mPaint.setColor(LAYER_COLOR);
        mPaint.setStrokeWidth(LINE_WIDTH);
        mPaint.setAntiAlias(true);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
    }

    @Override
    public void onDraw(Canvas canvas, int cX, int cY, int w, int h, Calendar calendar) {
        float progress = calendar.get(Calendar.MINUTE) * SECOND_PER_MINUTE + calendar.get(Calendar.SECOND);
        float maxRadius = (Math.min(w, h) - LINE_WIDTH) / 2f * FRONT_INDICATOR_LENGTH;
        mRectF.left = cX - maxRadius;
        mRectF.right = cX + maxRadius;
        mRectF.top = cY - maxRadius;
        mRectF.bottom = cY + maxRadius;
        float theta = progress / SECOND_PER_MINUTE / MINUTE_PER_HOUR * ARC_WHOLE_PANEL;
        float dX = (float) (maxRadius * Math.sin(theta));
        float dY = (float) (maxRadius * Math.cos(theta));
        mDialPosition[0] = cX + dX * BACK_INDICATOR_LENGTH;
        mDialPosition[1] = cY - dY * BACK_INDICATOR_LENGTH;
        mDialPosition[2] = cX + dX;
        mDialPosition[3] = cY - dY;
        canvas.drawLines(mDialPosition, mPaint);
    }
}
