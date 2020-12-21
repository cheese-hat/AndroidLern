package com.company;

import java.util.ArrayList;

public class Backpack {
    ArrayList<Item> items = new ArrayList<>(); //предметы в рюкзаке

    int maxTotalVolume = 100; // Максимальная вместительность рюкзака по объему
    int maxTotalWeight = 50;// Максимальная вместительность рюкзака по весу
    int backpackVolume = maxTotalVolume;
    int backpackWeight = maxTotalWeight;

    int qPockets; // Количество карманов на данный момент


    public void addItem(Item item) {
        // Нельзя положить туда больше, чем позволяет объем/грузоподьемность, с учетом карманов.
        // Также, в рюкзак нельзя класть одоновременно более 2-х типов различных вещей
        TypeItem firstTypeAddItem;
        if (items.isEmpty() ) firstTypeAddItem = item.type;
        else firstTypeAddItem = items.get(0).type;


        TypeItem secondTypeAddItem = null;

        for (int m = 0; m < items.size(); m++){
            secondTypeAddItem = items.get(m).type;
            if (firstTypeAddItem != secondTypeAddItem) {
                secondTypeAddItem = items.get(m).type;
            }
        }

        int pass = 0;
        int countTypeItem;

        while (pass == 0){
            countTypeItem = 1;

            for(int i = 0; i < items.size(); i++) {

                for (int j = i + 1; j < items.size(); j++) {
                    if (items.get(i).type != items.get(j).type) {
                        countTypeItem += 1;
                    }
                }

                // первый и второй тип должны быть разными
                if (countTypeItem == 2 && (item.type != firstTypeAddItem && item.type != secondTypeAddItem)) {
                    System.out.println("В рюкзаке количество типов лежащих предметов не может быть больше 2!");
                    pass -= 1;
                    break;
                } else {
                    pass = 0;
                }
            }

            if (pass == 0) {

                if (item.volume > backpackVolume || item.weight > backpackWeight) {
                    System.out.println("Предмет не поместится в рюкзак!");
                    pass -= 1;
                } else {
                    items.add(item);
                    backpackWeight -= item.weight;
                    backpackVolume -= item.volume;
                    System.out.println(item.name + " был(а/о) добавлен(a/о) в рюкзак.");
                    pass += 1;
                }
            }
        }

        if (pass < 0) {
            System.out.println("Не получилось добавить предмет в рюкзак :С");
        } else {
            System.out.println("Предмет был успешно добавлен в рюкзак С:");
        }
    }

    public void delItem(Item item) {
        backpackWeight += item.weight;
        backpackVolume += item.volume;
        items.remove(item);
        System.out.println(item.name + " был(а) жестоко выкинут(а) из рюкзака!");
    }

    public void addPocket(int quantity){
        qPockets += quantity;
        backpackVolume += quantity * 5;
        maxTotalVolume += quantity * 5;
        backpackWeight += quantity;
        maxTotalWeight += quantity;
        System.out.println("Был повешен дополнительный карман!");
    }

    public void delPocket(int quantity){
//        нельзя снимать карманы, не вынув достаточное количество вещей.
        if (qPockets == 0) {
            System.out.println("У вас нет карманов, чтобы можно было их снях!");
        } else {
            int tmpBackpackWeight = backpackWeight - quantity;
            int tmpBackpackVolume = backpackVolume - quantity * 5;

            int itemsVolume = 0;

            for (int itemVol = 0; itemVol < items.size(); itemVol++) {
                itemsVolume += items.get(itemVol).volume;
            }

            int itemsWeight = 0;

            for (int itemW = 0; itemW < items.size(); itemW++) {
                itemsWeight += items.get(itemW).weight;
            }

            if (itemsWeight <= tmpBackpackWeight && itemsVolume <= tmpBackpackVolume) {
                qPockets -= quantity;
                backpackVolume -= quantity * 5;
                backpackWeight -= quantity;
                maxTotalVolume -= quantity * 5;
                maxTotalWeight -= quantity;
            } else {
                System.out.println("Выньте из рюкзака вещи, прежде чем снимать карман!");
            }

        }
    }

    public void backpackLoadingStatistics(){
        // если сначала положить один тип, потом второй, а затем опять ведь первого типа, то в выводе будет информация о первом типе, но не о втором

        int sizeOfItems;
        if (items.isEmpty() ) sizeOfItems = 0;
        else sizeOfItems = items.size();

        System.out.println("\n--------------------СТАТИСТИКА ЗАГРУЗКИ РЮКЗАКА---------------------");
        System.out.println("------------------------------------------------------------------------");
        System.out.println("| Количество вещей в рюкзаке: \t\t\t\t\t\t\t\t| \t" + sizeOfItems+"\t  |");


        int itemsVolume = 0;

        for (int itemVol = 0; itemVol < items.size(); itemVol++) {
            itemsVolume += items.get(itemVol).volume;
        }

        int freeVolume = maxTotalVolume - itemsVolume;

        System.out.println("------------------------------------------------------------------------");
        System.out.println("| Свободно ОБЪЕМА в рюкзаке: \t\t\t\t\t\t\t\t|\t" + freeVolume + "\t  |");


        int itemsWeight = 0;

        for (int itemW = 0; itemW < items.size(); itemW++) {
            itemsWeight += items.get(itemW).weight;
        }

        int freeWeight = maxTotalWeight - itemsWeight;

        System.out.println("------------------------------------------------------------------------");
        System.out.println("| Свободно ВЕСА в рюкзаке:  \t\t\t\t\t\t\t\t|\t" + freeWeight + "\t  |");

        System.out.println("------------------------------------------------------------------------");
        System.out.println("| Общий процент загрузки по ОБЪЕМУ:  \t\t\t\t\t\t|\t" + ((100 * itemsVolume) / maxTotalVolume) + "%\t  |");

        ArrayList<Item> itemsFirstType = new ArrayList<>();
        ArrayList<Item> itemsSecondType = new ArrayList<>();
        int volumeItemsFirstType = 0;
        int volumeItemsSecondType = 0;
        String answerVolumeByTypes;

        TypeItem firstTypeItem;
        TypeItem secondTypeItem = null;

        if (items.isEmpty()) {
            answerVolumeByTypes = "0%";
        } else {

            firstTypeItem = items.get(0).type;

            for (int m = 0; m < items.size(); m++){
                TypeItem tmpSecondTypeItem = items.get(m).type;
                if (firstTypeItem != tmpSecondTypeItem) {
                    secondTypeItem = items.get(m).type;
                } else {
                    continue;
                }
            }

            TypeItem tmpItemType;

            for (int volumeItemOnType = 0; volumeItemOnType < items.size(); volumeItemOnType++){
                tmpItemType = items.get(volumeItemOnType).type;
                if (tmpItemType == firstTypeItem) {
                    itemsFirstType.add(items.get(volumeItemOnType));
                } else if (tmpItemType == secondTypeItem) {
                    itemsSecondType.add(items.get(volumeItemOnType));
                }
            }


            if (firstTypeItem != secondTypeItem && secondTypeItem != null) {
                for (Item firstItem : itemsFirstType) {
                    volumeItemsFirstType += firstItem.volume;
                }

                for (Item secondItem : itemsSecondType) {
                    volumeItemsSecondType += secondItem.volume;
                }
                int procentVolumeByFirstTypeItem = ((100 * volumeItemsFirstType) / maxTotalVolume);
                int procentVolumeBySecondTypeItem = ((100 * volumeItemsSecondType) / maxTotalVolume);
                answerVolumeByTypes = procentVolumeByFirstTypeItem + "% занимает " + firstTypeItem + "; " +
                                      procentVolumeBySecondTypeItem +"% занимает " + secondTypeItem;
            } else {
                answerVolumeByTypes = ((100 * itemsVolume) / maxTotalVolume)+"% занимает " + firstTypeItem;
            }

        }

        System.out.println("------------------------------------------------------------------------");
        System.out.println("| Процент загрузки по ОБЪЕМУ c распределением по типам вещей: |\t\t" + answerVolumeByTypes + "\t|");

        System.out.println("------------------------------------------------------------------------");
        System.out.println("| Общий процент загрузки по МАССЕ:  \t\t\t\t\t\t|\t" + ((100 * itemsWeight) / maxTotalWeight) + "%\t  |");

        int weightItemsFirstType = 0;
        int weightItemsSecondType = 0;
        String answerWeightByTypes;

        if (items.isEmpty()) {
            answerWeightByTypes = "0%";
        } else {

            firstTypeItem = items.get(0).type;

            if (firstTypeItem != secondTypeItem && secondTypeItem != null) {

                for (Item firstItem : itemsFirstType) {
                    weightItemsFirstType += firstItem.weight;
                }

                for (Item secondItem : itemsSecondType) {
                    weightItemsSecondType += secondItem.weight;
                }

                int procentWeightByFirstTypeItem = ((100 * weightItemsFirstType) / maxTotalWeight);
                int procentWeightBySecondTypeItem = ((100 * weightItemsSecondType) / maxTotalWeight);
                answerWeightByTypes = procentWeightByFirstTypeItem + "% занимает " + firstTypeItem + "; " +
                                      procentWeightBySecondTypeItem +"% занимает " + secondTypeItem;
            } else {
                answerWeightByTypes = ((100 * itemsWeight) / maxTotalWeight)+"% занимает " + firstTypeItem;
            }

        }
        System.out.println("------------------------------------------------------------------------");
        System.out.println("| Процент загрузки по МАССЕ c распределением по типам вещей: |\t " + answerWeightByTypes + "\t  |\n");
    }

    public boolean itemsInBackpack() {
        if (items.isEmpty()) {
            System.out.println("\n-------------------");
            System.out.println("|   Рюкзак пуст!  |");
            System.out.println("-------------------\n");
            return false;
        } else {
            System.out.println("\n----------------СОДЕРЖИМОЕ РЮКЗАКА----------------");
            for (int bpItem = 0; bpItem < items.size(); bpItem++) {
                System.out.println(Integer.toString(bpItem) + ". " + items.get(bpItem).name);
            }
            System.out.println("---------------------------------------------------\n");
            return true;
        }
    }
}
