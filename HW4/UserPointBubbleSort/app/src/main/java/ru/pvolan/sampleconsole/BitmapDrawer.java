package ru.pvolan.sampleconsole;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class BitmapDrawer implements Drawer {
    private Canvas canvas;

    public BitmapDrawer(Bitmap bitmap) {
        this.canvas = new Canvas(bitmap);
    }


    @Override
    public void drawPoint(float cx, float cy) {
        drawPoint(cx, cy, 0xff000000);
    }

    @Override
    public void drawPoint(float cx, float cy, int color) {
        canvas.drawCircle(cx, cy, 3, createFillPaint(color));
    }

    @Override
    public void drawLine(float x1, float y1, float x2, float y2) {
        drawLine(x1, y1, x2, y2, 0xff000000);
    }

    @Override
    public void drawLine(float x1, float y1, float x2, float y2, int color) {
        canvas.drawLine(x1, y1, x2, y2, createStrokePaint(color));
    }

    public void drawText(String string, float cx, float cy) {
        canvas.drawText(string, cx, cy, createText());
    }


    private Paint createFillPaint(int color){
        Paint p = new Paint();
        p.setAntiAlias(true);
        p.setStyle(Paint.Style.FILL);
        p.setColor(color);
        return p;
    }


    private Paint createStrokePaint(int color){
        Paint p = new Paint();
        p.setAntiAlias(true);
        p.setStyle(Paint.Style.STROKE);
        p.setStrokeWidth(2);
        p.setColor(color);
        return p;
    }

    private Paint createText(){
        Paint paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setStyle(Paint.Style.FILL);
        //canvas.drawPaint(paint);

        paint.setColor(Color.BLACK);
        paint.setTextSize(100);
        return paint;
    }

    @Override
    public Canvas getCanvas() {
        return canvas;
    }
}
