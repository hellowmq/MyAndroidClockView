package cn.wenmq.clock.drawlayer;

import android.graphics.Canvas;
import android.graphics.Paint;
import java.util.Calendar;

public class PanelDrawLayer implements DrawLayer {

    public static final int LAYER_COLOR = 0x1F00FF00;
    final Paint mGreenPaint = new Paint();

    public PanelDrawLayer() {
        mGreenPaint.setColor(LAYER_COLOR);
        mGreenPaint.setStyle(Paint.Style.FILL);
    }

    @Override
    public void onDraw(Canvas canvas, int cX, int cY, int w, int h, Calendar calendar) {
        canvas.drawCircle(cX, cY, Math.min(w, h) >> 1, mGreenPaint);
    }
}
