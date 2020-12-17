package ru.pvolan.sampleconsole.program;

import java.util.ArrayList;

import ru.pvolan.sampleconsole.Drawer;
import ru.pvolan.sampleconsole.Input;
import ru.pvolan.sampleconsole.Output;

// ДЗ3
//5. Прямые (дежавю?)
// Выполняется на тренажере. *Любознательные могут вместо тренажера сделать полноценное Android-приложение.
// Пользователь вводит N прямых.
// Каждая линия задается формулой y=ax+b,
// пользователь вводит a и b для каждой прямой.
// Найти точки пересечения прямых друг с другом.
// Вывести координаты точек текстом.
// На рисунке нарисовать прямые и отметить точки пересечений.

public class Program {

    public void pseudoMain(Input in, Output out, Drawer drawer){

        int quantityLines = in.readInt(); //количество линий
        ArrayList<Line> lines = new ArrayList<Line>(); // массив линий с точками

        int[] intNumsUserAB = new int[quantityLines * 2];

        for (int i = 0; i < quantityLines * 2; i += 2) { // повторить для каждой строки с данными для линии

            String userAB = in.readLine(); // строка с A and B от пользователя
            String[] strNumsUserAB = userAB.split(" "); // массив цифр пользователя в строковом типе

            // превращение строки в число
            intNumsUserAB[i] = Integer.parseInt(strNumsUserAB[0]);
            int i2 = i+1;
            intNumsUserAB[i2] = Integer.parseInt(strNumsUserAB[1]);
        }

        int numA;
        int numB;

        for (int indAandB = 0; indAandB < intNumsUserAB.length; indAandB += 2) {

            numA = intNumsUserAB[indAandB];
            numB = intNumsUserAB[indAandB + 1];
            Line userLine = new Line(); // линия пользователя
            userLine.setAandB(numA, numB);
            lines.add(userLine);
        }


        for (int l = 0; l < lines.size() -1; l++) {

            for (int s = l + 1; s < lines.size(); s ++) {

                out.println("\nТочка пересечения двух прямых: ");
                out.println("------ прямая " + (l + 1) + " ------");
                out.println("x1 = " + Integer.toString(lines.get(l).start.x) +
                        " y1 = " + Integer.toString(lines.get(l).start.y) +
                        " x2 = " + Integer.toString(lines.get(l).end.x) +
                        " y2 = " + Integer.toString(lines.get(l).end.y) +
                        " a = " + Integer.toString(lines.get(l).getA()) +
                        " b = " + Integer.toString(lines.get(l).getB()));

                out.println("------ прямая " + (s + 1) + " ------");
                out.println("x1 = " + Integer.toString(lines.get(s).start.x) +
                        " y1 = " + Integer.toString(lines.get(s).start.y) +
                        " x2 = " + Integer.toString(lines.get(s).end.x) +
                        " y2 = " + Integer.toString(lines.get(s).end.y) +
                        " a = " + Integer.toString(lines.get(s).getA()) +
                        " b = " + Integer.toString(lines.get(s).getB()));

                getIntersectionPoint(out, drawer, lines.get(l), lines.get(s));


            }
        }

    }

    //y = a * x + b
    public Point getIntersectionPoint(Output out, Drawer drawer, Line... line) {

        int x, y;
        Point intersectionPoint = new Point();

        for (int i = 0; i < line.length; i++) {
            for (int j = i + 1; j < line.length; j++) {
                if (line[i].getA() == line[j].getA()){
                    out.println("Линии параллельны и не пересекаются.");
                    continue;
                } else {
                    x = ((line[j].getB() - line[i].getB()) / (line[i].getA() - line[j].getA()));
                    y = line[i].getA() * x + line[i].getB();
                    intersectionPoint = new Point(x,y);
                    out.println("точка пересечения --> x=" + intersectionPoint.x + " y=" + intersectionPoint.y + "\n");
                    drawer.drawPoint(intersectionPoint.x, intersectionPoint.y, 0xffff0000);
                    drawer.drawLine(line[i].start.x, line[i].start.y, line[i].end.x, line[i].end.y);
                    drawer.drawLine(line[j].start.x, line[j].start.y, line[j].end.x, line[j].end.y);
                }
            }
        }
        
        return intersectionPoint;

    }

    public boolean checkIntersectionOfTwoLineSegments(Line l1, Line l2){

        //сначала расставим точки по порядку, т.е. чтобы первая точка (начало первой линии) (x) <= (x) второй точки (конца линии)
        if (l1.end.x < l1.start.x) {
            Point tmp = l1.start;
            l1.start = l1.end;
            l1.end = tmp;
        }

        //и начало второй линии (x) <= (x) конец кторой линии
        if (l2.end.x < l2.start.x) {
            Point tmp = l2.start;
            l2.start = l2.end;
            l2.end = tmp;
        }

        //проверим существование потенциального интервала для точки пересечения отрезков
        if (l1.end.x < l2.start.x) {
            return false; //ибо у отрезков нету взаимной абсциссы
        }

        //если оба отрезка вертикальные
        if((l1.start.x - l1.end.x == 0) && (l2.start.x - l2.end.x == 0)) {

            //если они лежат на одном X
            if(l1.start.x == l2.start.x) {
                //проверим пересекаются ли они, т.е. есть ли у них общий Y
                //для этого возьмём отрицание от случая, когда они НЕ пересекаются
                if (!((Math.max(l1.start.y, l1.end.y) < Math.min(l2.start.y, l2.end.y)) ||
                        (Math.min(l1.start.y, l1.end.y) > Math.max(l2.start.y, l2.end.y)))) {
                    return true;
                }
            }
            return false;
        }

        //найдём коэффициенты уравнений, содержащих отрезки
        //f1(x) = a1 * x + b1 = y
        //f2(x) = a2 * x + b2 = y
        //если первый отрезок вертикальный
        if (l1.start.x - l1.end.x == 0) {

            //найдём Xa, Ya - точки пересечения двух прямых
            double Xa = l1.start.x;
            double A2 = (l2.start.y - l2.end.y) / (l2.start.x - l2.end.x);
            double b2 = l2.start.y - A2 * l2.start.x;
            double Ya = A2 * Xa + b2;

            if (l2.start.x <= Xa && l2.end.x >= Xa &&
                    Math.min(l1.start.y, l1.end.y) <= Ya &&
                    Math.max(l1.start.y, l1.end.y) >= Ya){
                return true;
            }
            return false;
        }

        //если второй отрезок вертикальный
        if (l2.start.x - l2.end.x == 0) {
            //найдём Xa, Ya - точки пересечения двух прямых
            double Xa = l2.start.x;
            double A1 = (l1.start.y - l1.end.y) / (l1.start.x - l1.end.x);
            double b1 = l1.start.y - A1 * l1.start.x;
            double Ya = A1 * Xa + b1;

            if (l1.start.x <= Xa && l1.end.x >= Xa && Math.min(l2.start.y, l2.end.y) <= Ya &&
                    Math.max(l2.start.y, l2.end.y) >= Ya) {
                return true;
            }

            return false;

        }
        //оба отрезка невертикальные
        double A1 = (l1.start.y - l1.end.y) / (l1.start.x - l1.end.x);
        double A2 = (l2.start.y - l2.end.y) / (l2.start.x - l2.end.x);
        double b1 = l1.start.y - A1 * l1.start.x;
        double b2 = l2.start.y - A2 * l2.start.x;

        if (A1 == A2) {
            return false; //отрезки параллельны
        }

        //Xa - абсцисса точки пересечения двух прямых
        double Xa = (b2 - b1) / (A1 - A2);

        if ((Xa < Math.max(l1.start.x, l2.start.x)) || (Xa > Math.min( l1.end.x, l2.end.x))) {
            return false; //точка Xa находится вне пересечения проекций отрезков на ось X
        } else {
            return true;
        }

    }

}

