package cn.wenmq.clock.drawlayer;

import static cn.wenmq.clock.CalendarConstants.ANGLE_START_POSITION;
import static cn.wenmq.clock.CalendarConstants.ANGLE_WHOLE_PANEL;
import static cn.wenmq.clock.CalendarConstants.MILLISECOND_PER_SECOND;
import static cn.wenmq.clock.CalendarConstants.SECOND_PER_MINUTE;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import java.util.Calendar;

public class SecondDrawLayer implements DrawLayer {
    public static final float LINE_WIDTH = 20f;
    public static final int LAYER_COLOR = 0x3FFF0000;

    private final Paint mPaint = new Paint();
    private final RectF mRectF = new RectF();
    private final float[] mDrawParams = new float[2];

    private boolean enableEndAnimate = false;
    private float mEndAniSeg = 0;

    public SecondDrawLayer() {
        initLayer();
    }

    private void initLayer() {
        mPaint.setColor(LAYER_COLOR);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(LINE_WIDTH);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setAntiAlias(true);
    }

    public SecondDrawLayer(boolean enableEndAnimate, float endAnimateSeg) {
        this.enableEndAnimate = enableEndAnimate;
        this.mEndAniSeg = endAnimateSeg;
        initLayer();
    }

    @Override
    public void onDraw(Canvas canvas, int cX, int cY, int w, int h, Calendar calendar) {
        float progress = calendar.get(Calendar.SECOND) * MILLISECOND_PER_SECOND + calendar.get(Calendar.MILLISECOND);
        float maxRadius = (Math.min(w, h) - LINE_WIDTH) / 2;
        mRectF.left = cX - maxRadius;
        mRectF.right = cX + maxRadius;
        mRectF.top = cY - maxRadius;
        mRectF.bottom = cY + maxRadius;
        mDrawParams[0] = ANGLE_START_POSITION;
        mDrawParams[1] = progress / MILLISECOND_PER_SECOND / SECOND_PER_MINUTE * ANGLE_WHOLE_PANEL;
        transformWithEndAnimate();
        canvas.drawArc(mRectF, mDrawParams[0], mDrawParams[1], false, mPaint);
    }

    private void transformWithEndAnimate() {
        if (!enableEndAnimate) {
            return;
        }
        float endAngle = mDrawParams[1];
        float v = ANGLE_WHOLE_PANEL * (1 - mEndAniSeg);
        if (endAngle > v) {
            mDrawParams[0] = (endAngle - v) / (ANGLE_WHOLE_PANEL - v) * ANGLE_WHOLE_PANEL + ANGLE_START_POSITION;
            mDrawParams[1] = endAngle - mDrawParams[0] + ANGLE_START_POSITION;
        }
    }
}
