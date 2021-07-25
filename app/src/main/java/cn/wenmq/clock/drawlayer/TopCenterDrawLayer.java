package cn.wenmq.clock.drawlayer;

import android.graphics.Canvas;
import android.graphics.Paint;
import java.util.Calendar;

public class TopCenterDrawLayer implements DrawLayer {

    public static final float CENTER_CORE_RADIUS = 0.02f;
    Paint mPaint = new Paint();

    public TopCenterDrawLayer() {
        mPaint.setColor(HourDrawLayer.LAYER_COLOR | 0xFF000000);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setAntiAlias(true);
    }

    @Override
    public void onDraw(Canvas canvas, int centerX, int centerY, int width, int height, Calendar calendar) {
        canvas.drawCircle(centerX, centerY, width * CENTER_CORE_RADIUS, mPaint);
    }
}
