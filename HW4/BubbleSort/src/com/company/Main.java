package com.company;

import java.util.Arrays;
import java.util.Scanner;
// Сортировка пузырьком.
// Выполняется на тренажере.
// Ввести N точек.
// Отсортировать точки по удалению от точки 0,0,
// вывести текстом отсортированный список.
// Алгоритм сортировки создать самостоятельно, запрещается использовать “стандартные” библиотеки.
// Использовать сортировку пузырьком, принцип сортировка спросить или подсмотреть в гугле.
// На холсте отрисовать точки, пометить каждую точку номером в соответствии с сортировкой.
public class Main {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        String strUserNums = in.nextLine();
        String[] strMasUserNums = strUserNums.split(" ");
        int[] intMasUserNums = new int[strMasUserNums.length];
        for (int i = 0; i < strMasUserNums.length; i++) {
            intMasUserNums[i] = Integer.parseInt(strMasUserNums[i]);
        }

        boolean isSorted = false;
        int buf;
        while(!isSorted) {
            isSorted = true;
            for (int i = 0; i < intMasUserNums.length-1; i++) {
                if(intMasUserNums[i] > intMasUserNums[i+1]){
                    isSorted = false;

                    buf = intMasUserNums[i];
                    intMasUserNums[i] = intMasUserNums[i+1];
                    intMasUserNums[i+1] = buf;
                }
            }
        }
        System.out.println(Arrays.toString(intMasUserNums));
    }
}
