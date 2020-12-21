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
                if (points.get(j).x > points.get(j+1).x || points.get(j).y > points.get(j + 1).y) {
                    Point tmpPointToMove = new Point(points.get(j).x, points.get(j).y);
                    points.set(j, points.get(j + 1));
                    points.set(j + 1, tmpPointToMove);
                }
            }
        }


        for (int p = 0; p < points.size(); p++) {
            String[] alphabet = {"A", "B", "C", "D", "E", "F", "G", "H", "I",
                                "J", "K", "L", "M", "N", "O", "P", "Q", "R",
                                "S", "T", "U", "V", "W", "X", "Y", "Z"};
            out.println(alphabet[p] + " " + Integer.toString(points.get(p).x) + " " + Integer.toString(points.get(p).y));
            drawer.drawText(alphabet[p], points.get(p).x, points.get(p).y);
        }

    }
}
