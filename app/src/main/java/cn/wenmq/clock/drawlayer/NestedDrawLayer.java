package cn.wenmq.clock.drawlayer;

import android.graphics.Canvas;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class NestedDrawLayer implements DrawLayer {
    private final List<DrawLayer> mDrawLayers = new ArrayList<>();

    @Override
    public void onDraw(Canvas canvas, int centerX, int centerY, int width, int height, Calendar calendar) {
        for (DrawLayer drawLayer : mDrawLayers) {
            onLayerDraw(drawLayer, canvas, centerX, centerY, width, height, calendar);
        }
    }

    protected void onLayerDraw(DrawLayer drawLayer, Canvas canvas,
                                        int centerX, int centerY, int width, int height, Calendar calendar){
        drawLayer.onDraw(canvas, centerX, centerY, width, height, calendar);
    }

    public void addDrawLayer(DrawLayer drawLayer) {
        mDrawLayers.add(drawLayer);
    }
}
