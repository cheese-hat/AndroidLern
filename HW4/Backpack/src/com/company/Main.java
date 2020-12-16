package com.company;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        Backpack backpackOfUser = new Backpack();

        menu(backpackOfUser);

    }

    public static void menu (Backpack BP) {
        Scanner in = new Scanner(System.in);

        Item apple = new Item("яблоко", 3, 5, TypeItem.Food);
        Item banana = new Item("банан", 3, 7, TypeItem.Food);
        Item tea = new Item("чай", 6, 7, TypeItem.Food);

        Item hammer = new Item("молоток", 15, 6, TypeItem.Tool);
        Item knife = new Item("нож", 3, 5, TypeItem.Tool);
        Item pen = new Item("шариковая ручка", 1, 2, TypeItem.Tool);

        Item helmet = new Item("шлем", 12, 15,TypeItem.Clothes);
        Item armor = new Item("доспехи", 40, 40, TypeItem.Clothes);
        Item sweater = new Item("свитер", 8, 30, TypeItem.Clothes);

        Item resurrectionScroll = new Item("свиток воскрешения", 2, 7, TypeItem.Paper);
        Item map = new Item("карта", 1, 2, TypeItem.Paper);
        Item notebook = new Item("тетрадка", 3, 10, TypeItem.Paper);

        Item[] magazineItems = new Item[] {
                apple, hammer, helmet, resurrectionScroll, banana, knife, armor, map, tea, pen, sweater, notebook
        };



        BP.itemsInBackpack();
        BP.backpackLoadingStatistics();
        System.out.println("--------------------МЕНЮ---------------\n" +
                "1. погрузить в рюкзак вещь из магазина\n" +
                "2. убрать вещь из рюкзака\n" +
                "3. навесить дополнительный карман\n" +
                "4. убрать дополнительный карман\n" +
                "0. выход из программы\n");
        System.out.println("Введите пункт, который хотите выборать: ");

        int choiceMenu = in.nextInt();

        switch (choiceMenu) {
            case 1:
                continueOrMenu(BP, 1, magazineItems);
//                System.out.println("----МАГАЗИН----");
//                for (int magItem = 0; magItem < magazineItems.length; magItem++) {
//                    System.out.println(Integer.toString(magItem) + ". " + magazineItems[magItem].name +
//                            " вес=[" + magazineItems[magItem].weight +
//                            "] объем=[" + magazineItems[magItem].volume +
//                            "] тип=" + magazineItems[magItem].type);
//                }
//                System.out.println("Для того, чтобы вернуться на страницу меню нажмите 9, продолжить 5: ");
//                int menuOrContinue = in.nextInt();
//                if (menuOrContinue == 9) {
//                    menu(BP);
//                } else if (menuOrContinue == 5) {
//
//                    System.out.println("---------------");
//                    System.out.println("Выберите номер предмета: ");
//                    int indMagItem = in.nextInt();
//                    BP.addItem(magazineItems[indMagItem]);
//                    continueOrExit(BP);
//                } else {
//                    break;
//                }


            case 2:
                continueOrMenu(BP, 2, magazineItems);
//                System.out.println("Для того, чтобы вернуться на страницу меню нажмите 9, продолжить 5: ");
//                menuOrContinue = in.nextInt();
//                if (menuOrContinue == 9) {
//                    menu(BP);
//                } else if (menuOrContinue == 5) {
//                    if (BP.itemsInBackpack()) {
//                        System.out.println("Выберите номер предмета, который хотите выкинуть из рюкзака: ");
//                        int indItemToDel = in.nextInt();
//                        BP.delItem(BP.items.get(indItemToDel));
//                    } else {
//                        System.out.println("Рюкзак пуст!");
//                    }
//                    continueOrExit(BP);
//                }
            case 3:
                continueOrMenu(BP, 3, magazineItems);
//                System.out.println("Для того, чтобы вернуться на страницу меню нажмите 9, продолжить 5: ");
//                menuOrContinue = in.nextInt();
//                if (menuOrContinue == 9) {
//                    menu(BP);
//                } else if (menuOrContinue == 5) {
//                    BP.addPocket(1);
//                    continueOrExit(BP);
//                }
            case 4:
                continueOrMenu(BP, 4, magazineItems);
//                System.out.println("Для того, чтобы вернуться на страницу меню нажмите 9, продолжить 5: ");
//                menuOrContinue = in.nextInt();
//                if (menuOrContinue == 9) {
//                    menu(BP);
//                } else if (menuOrContinue == 5) {
//                    BP.delPocket(1);
//                    continueOrExit(BP);
//                }
            case 0:
                //continueOrMenu(BP, 0, magazineItems);
                break;
            default:
                //continueOrMenu(BP, , magazineItems);
                break;
        }
    }

    public static void continueOrExit(Backpack BP){
        Scanner in = new Scanner(System.in);
        System.out.println("Для выхода из программы введите 0, для выхода в меню 9: ");
        int choiceExit = in.nextInt();
        if (choiceExit == 0) {
            System.exit(0);
        } else if (choiceExit == 9) {
            menu(BP);
        }
    }

    public static void continueOrMenu(Backpack BP, int choiceMenu, Item[] magazineItems){
        Scanner in = new Scanner(System.in);
        System.out.println("Вы действительно хотите продолжить?");
        System.out.println("Для того, чтобы вернуться на страницу меню нажмите 9, продолжить 5: ");
        int menuOrContinue = in.nextInt();
        if (menuOrContinue == 9) {
            menu(BP);
        } else if (menuOrContinue == 5) {
            switch (choiceMenu) {
                case 1:
                    System.out.println("----МАГАЗИН----");
                    for (int magItem = 0; magItem < magazineItems.length; magItem++) {
                        System.out.println(Integer.toString(magItem) + ". " + magazineItems[magItem].name +
                                " вес=[" + magazineItems[magItem].weight +
                                "] объем=[" + magazineItems[magItem].volume +
                                "] тип=" + magazineItems[magItem].type);
                    }
                    System.out.println("---------------");
                    System.out.println("Выберите номер предмета: ");
                    int indMagItem = in.nextInt();
                    BP.addItem(magazineItems[indMagItem]);
                    continueOrExit(BP);

                case 2:
                    if (BP.itemsInBackpack()) {
                        System.out.println("Выберите номер предмета, который хотите выкинуть из рюкзака: ");
                        int indItemToDel = in.nextInt();
                        BP.delItem(BP.items.get(indItemToDel));
                    } else {
                        System.out.println("Рюкзак пуст!");
                    }
                    continueOrExit(BP);

                case 3:
                    BP.addPocket(1);
                    continueOrExit(BP);

                case 4:
                    BP.delPocket(1);
                    continueOrExit(BP);

//                case 0:
//                    break;
//
//                default:
//                    break;
            }

        } else {
            continueOrMenu(BP, choiceMenu, magazineItems);
        }
    }



}

enum TypeItem {
    Food, Paper, Clothes, Tool
}
