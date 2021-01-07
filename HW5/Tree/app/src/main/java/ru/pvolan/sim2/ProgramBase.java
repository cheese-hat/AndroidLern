package ru.pvolan.sim2;

import android.graphics.Canvas;

public class ProgramBase {

    private Callback callback;


    protected final void redraw(){
        callback.onRedraw();
    };

    protected final void showToast(String message){
        callback.showToast(message);
    };


    public final void doInit(Callback callback){
        this.callback = callback;
        init();
    }

    public void init() {

    }

    public void onButtonAClick(){

    }

    public void onButtonBClick(){

    }

    public void onButtonUpClick(){

    }

    public void onButtonDownClick(){

    }

    public void onButtonLeftClick(){

    }

    public void onButtonRightClick(){

    }

    public void onCommandRun(String command) throws Exception {

    }

    public void onDraw(Canvas canvas) {

    }


    public interface Callback{
        void onRedraw();
        void showToast(String message);
    }


}
