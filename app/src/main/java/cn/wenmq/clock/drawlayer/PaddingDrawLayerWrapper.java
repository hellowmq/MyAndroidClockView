package cn.wenmq.clock.drawlayer;

import android.graphics.Canvas;
import java.util.Calendar;

public class PaddingDrawLayerWrapper implements DrawLayer {

    private final DrawLayer mDrawLayer;
    private final int mPadding;

    public PaddingDrawLayerWrapper(DrawLayer drawLayer, int padding) {
        mDrawLayer = drawLayer;
        mPadding = padding;

    }

    @Override
    public void onDraw(Canvas canvas, int centerX, int centerY, int width, int height, Calendar calendar) {
        width -= mPadding << 1;
        height -= mPadding << 1;
        mDrawLayer.onDraw(canvas, centerX, centerY, width, height, calendar);
    }

}
