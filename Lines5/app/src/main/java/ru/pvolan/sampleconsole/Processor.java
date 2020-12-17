package ru.pvolan.sampleconsole;

import android.graphics.Bitmap;

import java.io.PrintWriter;
import java.io.StringWriter;

import ru.pvolan.sampleconsole.program.Program;

public class Processor {

    public RunOutput doTheMagic(String text, int bitmapW, int bitmapH) {
        Program program = new Program();
        StringInput input = new StringInput(text);
        StringOutput output = new StringOutput();

        Bitmap bitmap = Bitmap.createBitmap(bitmapW, bitmapH, Bitmap.Config.ARGB_8888);
        BitmapDrawer drawer = new BitmapDrawer(bitmap);

        try {
            program.pseudoMain(input, output, drawer);
        } catch (Exception e) {
            output.print("ERROR\n");
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            pw.flush();
            output.print(sw.toString());
            output.print("\n");
        }

        return new RunOutput(output.getOutput(), bitmap);
    }

}
