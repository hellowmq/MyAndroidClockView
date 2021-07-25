package cn.wenmq.clock.drawlayer;

import android.graphics.Canvas;

import java.util.Calendar;

/**
 *
 */
public interface DrawLayer {

    /**
     * Outer View should call this when {@link android.view.View#onDraw}
     *
     * @param canvas   the canvas onto which you must draw
     * @param centerX  the x of the center
     * @param centerY  the y of the center
     * @param width    the width of the canvas
     * @param height   the height of the canvas
     * @param calendar the desired date/time
     */
    void onDraw(Canvas canvas, int centerX, int centerY, int width, int height, Calendar calendar);

}
