package ru.pvolan.sampleconsole;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

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

    private Bitmap bitmap;

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
        invalidate();
    }


    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        canvas.drawColor(0xffcccccc);

        if(bitmap != null) {
            canvas.drawBitmap(bitmap, 0, 0, null);

        }
    }
}
