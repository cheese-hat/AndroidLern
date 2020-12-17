package ru.pvolan.sampleconsole.program;

import ru.pvolan.sampleconsole.Drawer;

public class Line {
    public int a;
    public int b;

    public Point start = new Point();
    public Point end = new Point();

    public void setAandB(int a, int b) {
        this.a = a;
        this.b = b;

        this.start.x = 0;
        this.start.y = start.x * a + b;


        this.end.x = 800;
        this.end.y = end.x * a + b;

    }

    public int getA() {
        return a;
    }

    public int getB() {
        return b;
    }


}
