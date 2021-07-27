package cn.wenmq.clock.view;

import android.content.Context;
import android.graphics.Canvas;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;
import androidx.annotation.Nullable;
import java.util.Calendar;
import cn.wenmq.clock.CalendarConstants;
import cn.wenmq.clock.drawlayer.HourDrawLayer;
import cn.wenmq.clock.drawlayer.MinuteDrawLayer;
import cn.wenmq.clock.drawlayer.NestedDrawLayer;
import cn.wenmq.clock.drawlayer.PanelDrawLayer;
import cn.wenmq.clock.drawlayer.SecondDrawLayer;
import cn.wenmq.clock.drawlayer.TopCenterDrawLayer;

public class ParticleClockView extends View {

    public static final int FRAME = 30;
    public static final int UPDATE_INTERNAL = CalendarConstants.MILLISECOND_PER_SECOND / FRAME;
    private final NestedDrawLayer mDrawLayer = new NestedDrawLayer();
    private Calendar mCalendar;
    private int mBottom;
    private int mTop;
    private int mLeft;
    private int mRight;

    public ParticleClockView(Context context) {
        super(context);
        init();
    }

    public ParticleClockView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ParticleClockView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mDrawLayer.addDrawLayer(new PanelDrawLayer());
        mDrawLayer.addDrawLayer(new HourDrawLayer());
        mDrawLayer.addDrawLayer(new MinuteDrawLayer());
        mDrawLayer.addDrawLayer(new SecondDrawLayer(true, 0.05f));
        mDrawLayer.addDrawLayer(new TopCenterDrawLayer());
        mCalendar = Calendar.getInstance();
    }

    public void start() {
        setTime(Calendar.getInstance());
    }

    private void setTime(Calendar calendar) {
        mCalendar = calendar;
        invalidate();
        new Handler().postDelayed(() -> setTime(Calendar.getInstance()), UPDATE_INTERNAL);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        final int drawRect = Math.min(mRight - mLeft, mBottom - mTop);
        final int cX = (mRight + mLeft) >> 1;
        final int cY = drawRect >> 1;
        mDrawLayer.onDraw(canvas, cX, cY, drawRect, drawRect, mCalendar);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        mRight = right;
        mLeft = left;
        mTop = top;
        mBottom = bottom;
    }

}
