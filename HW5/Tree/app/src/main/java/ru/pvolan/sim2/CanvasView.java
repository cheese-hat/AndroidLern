package ru.pvolan.sim2;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class CanvasView extends View {
    public CanvasView(@NonNull Context context) {
        super(context);
    }

    public CanvasView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CanvasView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private Callback callback;


    public void setCallback(Callback callback) {
        this.callback = callback;
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        canvas.drawColor(0xffcccccc);

        if(callback != null){
            callback.onDraw(canvas);
        }
    }

    public interface Callback{
        void onDraw(Canvas canvas);
    }
}
