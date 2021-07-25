package cn.wenmq.clock.drawlayer;

import static cn.wenmq.clock.CalendarConstants.ARC_WHOLE_PANEL;
import static cn.wenmq.clock.CalendarConstants.HOUR_PER_PANEL;
import static cn.wenmq.clock.CalendarConstants.MINUTE_PER_HOUR;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import java.util.Calendar;

public class HourDrawLayer implements DrawLayer {
    public static final float LINE_WIDTH = 20f;
    public static final float FRONT_INDICATOR_LENGTH = 0.4f;
    public static final float BACK_INDICATOR_LENGTH = -0.1f;
    public static final int LAYER_COLOR = 0x7FFF0000;

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
        float progress = calendar.get(Calendar.HOUR) * MINUTE_PER_HOUR + calendar.get(Calendar.MINUTE);
        float maxRadius = (Math.min(w, h) - LINE_WIDTH) / 2 * FRONT_INDICATOR_LENGTH;
        mRectF.left = cX - maxRadius;
        mRectF.right = cX + maxRadius;
        mRectF.top = cY - maxRadius;
        mRectF.bottom = cY + maxRadius;
        float theta = progress / MINUTE_PER_HOUR / HOUR_PER_PANEL * ARC_WHOLE_PANEL;
        float dX = (float) (maxRadius * Math.sin(theta));
        float dY = (float) (maxRadius * Math.cos(theta));
        mDialPosition[0] = cX + dX * BACK_INDICATOR_LENGTH;
        mDialPosition[1] = cY - dY * BACK_INDICATOR_LENGTH;
        mDialPosition[2] = cX + dX;
        mDialPosition[3] = cY - dY;
        canvas.drawLines(mDialPosition, mPaint);
    }
}
