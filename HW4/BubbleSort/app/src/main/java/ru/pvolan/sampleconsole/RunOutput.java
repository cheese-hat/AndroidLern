package ru.pvolan.sampleconsole;

import android.graphics.Bitmap;

public class RunOutput {

    private String outputStr;
    private Bitmap outputBitmap;

    public RunOutput(String outputStr, Bitmap outputBitmap) {
        this.outputStr = outputStr;
        this.outputBitmap = outputBitmap;
    }

    public String getOutputStr() {
        return outputStr;
    }

    public Bitmap getOutputBitmap() {
        return outputBitmap;
    }
}
