package ru.pvolan.sampleconsole.program;

import java.util.ArrayList;
import ru.pvolan.sampleconsole.Drawer;
import ru.pvolan.sampleconsole.Input;
import ru.pvolan.sampleconsole.Output;

/*2. Сортировка пузырьком.
Выполняется на тренажере. Ввести N точек. Отсортировать точки по удалению от точки 0,0,
вывести текстом отсортированный список. Алгоритм сортировки создать самостоятельно,
запрещается использовать “стандартные” библиотеки. Использовать сортировку пузырьком,
принцип сортировка спросить или подсмотреть в гугле. На холсте отрисовать точки,
пометить каждую точку номером в соответствии с сортировкой.
*/

public class Program {

    public void pseudoMain(Input in, Output out, Drawer drawer){

        int quantityPoints = in.readInt(); //количество линий
        ArrayList<Point> points = new ArrayList<Point>(); // массив точек

        for (int i = 0; i < quantityPoints; i ++) { // повторить для каждой строки с данными для точки

            String coordinatesOnePoint = in.readLine(); // строка с координатами точки от пользователя
            String[] strCoordinates = coordinatesOnePoint.split(" "); // массив цифр пользователя в строковом типе

            // превращение строки в число
            Point tmpPoint = new Point(Integer.parseInt(strCoordinates[0]), Integer.parseInt(strCoordinates[1]));
            drawer.drawPoint(Integer.parseInt(strCoordinates[0]), Integer.parseInt(strCoordinates[1]));

            points.add(tmpPoint);
        }


        for (int k = 0; k < quantityPoints; k++) {
            for (int j = 0; j < quantityPoints - 1; j++){

                Point point1 = points.get(j);
                Point point2 = points.get(j + 1);

                double distPoint1 = distancePoints(point1);
                double distPoint2 = distancePoints(point2);

                if (distPoint1 > distPoint2 ) {
                    points.set(j, point2);
                    points.set(j + 1, point1);
                } else {
                    continue;
                }
            }
        }


        for (int p = 0; p < points.size(); p++) {
            String[] alphabet = {"A", "B", "C", "D", "E", "F", "G", "H", "I",
                                "J", "K", "L", "M", "N", "O", "P", "Q", "R",
                                "S", "T", "U", "V", "W", "X", "Y", "Z"};
            Point sortPoint = points.get(p);
            out.println(alphabet[p] + " " + sortPoint.x + " " + sortPoint.y);
            drawer.drawText(alphabet[p], points.get(p).x, points.get(p).y);
        }

    }

    public static double distancePoints(Point userPoint){
        Point startPoint = new Point(0,0);

        double dXsquare = (double) Math.pow((userPoint.x - startPoint.x), 2);
        double dYsquare = (double) Math.pow((userPoint.y - startPoint.y), 2);
        double distance = (double) Math.sqrt(dYsquare + dXsquare);
        return distance;
    }
}
