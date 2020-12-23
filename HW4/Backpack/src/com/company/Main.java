package com.company;

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



//        BP.itemsInBackpack();
        itemsInBackpack(BP);
//        BP.backpackLoadingStatistics();
        backpackLoadingStatistics(BP);
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

            case 2:
                continueOrMenu(BP, 2, magazineItems);

            case 3:
                continueOrMenu(BP, 3, magazineItems);

            case 4:
                continueOrMenu(BP, 4, magazineItems);

            case 0:
                System.exit(0);

            default:
                System.out.println("Был введен неверный пункт меню!");
                menu(BP);

        }
    }

    public static void menuOrExit(Backpack BP){
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
                    //погрузить в рюкзак вещь из магазина

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
                    Item itemToAdd = magazineItems[indMagItem];

                    TypeItem firstTypeAddItem;

                    if (BP.items.isEmpty() ) firstTypeAddItem = itemToAdd.type;
                    else firstTypeAddItem = BP.items.get(0).type;

                    TypeItem secondTypeAddItem = null;

                    for (int m = 0; m < BP.items.size(); m++){
                        secondTypeAddItem = BP.items.get(m).type;
                        if (firstTypeAddItem != secondTypeAddItem) {
                            secondTypeAddItem = BP.items.get(m).type;
                        }
                    }

                    int pass = 0;
                    int countTypeItem;

                    while (pass == 0){
                        countTypeItem = 1;

                        for(int i = 0; i < BP.items.size(); i++) {

                            for (int j = i + 1; j < BP.items.size(); j++) {
                                if (BP.items.get(i).type != BP.items.get(j).type) {
                                    countTypeItem += 1;
                                }
                            }
                        }

                        // первый и второй тип должны быть разными
                        if (countTypeItem >= 2 && (itemToAdd.type != firstTypeAddItem && itemToAdd.type != secondTypeAddItem)) {
                            System.out.println("В рюкзаке количество типов лежащих предметов не может быть больше 2!");
                            pass -= 1;
                            break;
                        } else {
                            pass = 0;
                            if (BP.addItem(itemToAdd) && pass == 0) {

                                if (itemToAdd.volume > BP.backpackVolume || itemToAdd.weight > BP.backpackWeight) {
                                    System.out.println("Предмет не поместится в рюкзак!");
                                    pass -= 1;
                                } else {
                                    System.out.println(itemToAdd.name + " был(а/о) добавлен(a/о) в рюкзак.");
                                    pass += 1;
                                }
                            }
                        }
                    }

                    if (pass < 0) System.out.println("Не получилось добавить предмет в рюкзак :С");
                    else System.out.println("Предмет был успешно добавлен в рюкзак С:");

                    menuOrExit(BP);

                case 2:
                    //убрать вещь из рюкзака
//                    if (BP.itemsInBackpack()) {
                    if (itemsInBackpack(BP)) {
                        System.out.println("Выберите номер предмета, который хотите выкинуть из рюкзака: ");
                        int indItemToDel = in.nextInt();
                        System.out.println(BP.delItem(BP.items.get(indItemToDel)) + " был(а) жестоко выкинут(а) из рюкзака!");
                    } else {
                        System.out.println("Рюкзак пуст!");
                    }
                    menuOrExit(BP);

                case 3:
                    //навесить дополнительный карман
                    System.out.println("Был повешен дополнительный карман!");
                    System.out.println("Количетсво карманов на рюкзаке: " + BP.addPocket(1));
                    menuOrExit(BP);

                case 4:
                    //убрать дополнительный карман
                    if (BP.qPockets == 0) {
                        System.out.println("У вас нет карманов, чтобы можно было их снях!");
                    } else {
                        if (BP.delPocket(1)){
                            System.out.println("Был снят карман с рюкзака!");
                            System.out.println("Количетсво карманов на рюкзаке: " + BP.qPockets);
                        } else {
                            System.out.println("Выньте из рюкзака вещи, прежде чем снимать карман!");
                        }
                    }
                    menuOrExit(BP);

                default:
                    System.out.println("Вы ввели неверный пункт меню!");

            }

        } else {
            continueOrMenu(BP, choiceMenu, magazineItems);
        }
    }

    public static void backpackLoadingStatistics(Backpack BP) {
        System.out.println("| Количество вещей в рюкзаке:  \t\t\t\t\t\t\t\t|\t\t" + BP.quantityOfItems() +"\t\t  |");
        System.out.println("| Свободно ОБЪЕМА в рюкзаке:   \t\t\t\t\t\t\t\t|\t\t" + BP.freeVolume() + "\t\t  |");
        System.out.println("| Свободно ВЕСА в рюкзаке:     \t\t\t\t\t\t\t\t|\t\t" + BP.freeWeight() + "\t\t  |");
        System.out.println("| Общий процент загрузки по ОБЪЕМУ:  \t\t\t\t\t\t|\t\t" + BP.totalVolumePercentage() + "%\t\t  |");
        System.out.println("| Общий процент загрузки по МАССЕ:   \t\t\t\t\t\t|\t\t" + BP.totalWeightPercentage() + "%\t\t  |");
        System.out.println("| Процент загрузки по ОБЪЕМУ c распределением по типам вещей: |\t\t" + BP.percentageOfVolumeByType() + "\t\t  |");
        System.out.println("| Процент загрузки по МАССЕ c распределением по типам вещей:  |\t\t" + BP.percentageOfWeightByType() + "\t\t  |\n");
    }

    public static boolean itemsInBackpack(Backpack BP) {
        if (BP.items.isEmpty()) {
            System.out.println("\n-------------------");
            System.out.println("|   Рюкзак пуст!  |");
            System.out.println("-------------------\n");
            return false;
        } else {
            System.out.println("\n----------------СОДЕРЖИМОЕ РЮКЗАКА----------------");
            for (int bpItem = 0; bpItem < BP.items.size(); bpItem++) {
                System.out.println(Integer.toString(bpItem) + ". " + BP.items.get(bpItem).name);
            }
            System.out.println("---------------------------------------------------\n");
            return true;
        }
    }
}

enum TypeItem {
    Food, Paper, Clothes, Tool
}
