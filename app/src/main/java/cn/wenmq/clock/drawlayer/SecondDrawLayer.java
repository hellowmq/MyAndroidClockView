package cn.wenmq.clock.drawlayer;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;

import java.util.Calendar;

public class SecondDrawLayer implements DrawLayer {
    public static final int ANGLE_WHOLE = 360;
    public static final int ARC_START_ANGLE = -90;
    public static final float LINE_WIDTH = 20f;

    private final Paint mPaint = new Paint();
    private final RectF mRectF = new RectF();
    private final float[] mDrawParams = new float[2];

    private boolean enableEndAnimate = false;
    private float mEndAniSeg = 0;

    public SecondDrawLayer() {
        initLayer();
    }

    private void initLayer() {
        mPaint.setColor(0x3FFF0000);
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

    public void enableEndAnimate(boolean enableEndAnimate, float endAniTriggerSeg) {
        this.enableEndAnimate = enableEndAnimate;
        this.mEndAniSeg = endAniTriggerSeg;
    }

    @Override
    public void onDraw(Canvas canvas, int cX, int cY, int w, int h, Calendar calendar) {
        int second = calendar.get(Calendar.SECOND);
        int millisecond = calendar.get(Calendar.MILLISECOND);
        float maxRadius = (Math.min(w, h) - LINE_WIDTH) / 2;
        mRectF.left = cX - maxRadius;
        mRectF.right = cX + maxRadius;
        mRectF.top = cY - maxRadius;
        mRectF.bottom = cY + maxRadius;
        mDrawParams[0] = ARC_START_ANGLE;
        mDrawParams[1] = (second + millisecond / 1000f) * 6f;
        transformWithEndAnimate();
        canvas.drawArc(mRectF, mDrawParams[0], mDrawParams[1], false, mPaint);
    }

    private void transformWithEndAnimate() {
        if (!enableEndAnimate) {
            return;
        }
        float endAngle = mDrawParams[1];
        float v = ANGLE_WHOLE * (1 - mEndAniSeg);
        if (endAngle > v) {
            mDrawParams[0] = (endAngle - v) / (ANGLE_WHOLE - v) * ANGLE_WHOLE + ARC_START_ANGLE;
            mDrawParams[1] = endAngle - mDrawParams[0] + ARC_START_ANGLE;
        }
    }
}
