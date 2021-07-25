package cn.wenmq.clock.drawlayer;

import android.graphics.Canvas;
import android.graphics.Paint;

import java.util.Calendar;

public class PanelDrawLayer implements DrawLayer {

    Paint mGreenPaint;

    public PanelDrawLayer() {
        mGreenPaint = new Paint();
        mGreenPaint.setARGB(0x1F, 0, 0xFF, 0);
        mGreenPaint.setStyle(Paint.Style.FILL);
    }

    @Override
    public void onDraw(Canvas canvas, int cX, int cY, int w, int h, Calendar calendar) {
        float maxRadius = Math.min(w, h) >> 1;
        canvas.drawCircle(cX, cY, maxRadius, mGreenPaint);
    }
}
