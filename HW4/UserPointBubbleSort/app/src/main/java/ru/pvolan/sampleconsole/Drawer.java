package ru.pvolan.sampleconsole;

import android.graphics.Canvas;

public interface Drawer {

    void drawPoint(float cx, float cy);
    void drawPoint(float cx, float cy, int color);
    void drawLine(float x1, float y1, float x2, float y2);
    void drawLine(float x1, float y1, float x2, float y2, int color);
    Canvas getCanvas();

    void drawText(String string, float cx, float cy);

}
