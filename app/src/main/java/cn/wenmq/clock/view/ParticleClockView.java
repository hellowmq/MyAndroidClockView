package cn.wenmq.clock.view;

import android.content.Context;
import android.graphics.Canvas;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;
import androidx.annotation.Nullable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import cn.wenmq.clock.drawlayer.DrawLayer;
import cn.wenmq.clock.drawlayer.HourDrawLayer;
import cn.wenmq.clock.drawlayer.MinuteDrawLayer;
import cn.wenmq.clock.drawlayer.PanelDrawLayer;
import cn.wenmq.clock.drawlayer.SecondDrawLayer;

public class ParticleClockView extends View {


    public static final int FRAME = 30;
    public static final int UPDATE_INTERNAL = 1000 / FRAME;
    List<DrawLayer> mDrawLayers = new ArrayList<>();
    Calendar mCalendar;
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
        mDrawLayers.add(new PanelDrawLayer());
        mDrawLayers.add(new HourDrawLayer());
        mDrawLayers.add(new MinuteDrawLayer());
        mDrawLayers.add(new SecondDrawLayer(true, 0.05f));
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
        final int availW = mRight - mLeft;
        final int availH = mBottom - mTop;
        final int drawRect = Math.min(availW, availH);
        final int cX = (mRight + mLeft) >> 1;
        final int cY = drawRect >> 1;
        for (DrawLayer overlay : mDrawLayers) {
            overlay.onDraw(canvas, cX, cY, drawRect, drawRect, mCalendar);
        }
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
