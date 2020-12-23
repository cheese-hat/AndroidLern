package com.company;

import java.util.ArrayList;

public class Backpack {
    ArrayList<Item> items = new ArrayList<>(); //предметы в рюкзаке
    int maxTotalVolume = 100; // Максимальная вместительность рюкзака по объему
    int maxTotalWeight = 50;// Максимальная вместительность рюкзака по весу
    int backpackVolume = maxTotalVolume;
    int backpackWeight = maxTotalWeight;
    int qPockets; // Количество карманов на данный момент

    public boolean addItem(Item item) {
        // Нельзя положить туда больше, чем позволяет объем/грузоподьемность, с учетом карманов.
        // Также, в рюкзак нельзя класть одоновременно более 2-х типов различных вещей

        if (item.volume > backpackVolume || item.weight > backpackWeight) {
            return false;
        } else {
            items.add(item);
            backpackWeight -= item.weight;
            backpackVolume -= item.volume;
            return true;
        }
    }

    public String delItem(Item item) {
        backpackWeight += item.weight;
        backpackVolume += item.volume;
        items.remove(item);
        return item.name;
    }

    public int addPocket(int quantity){
        qPockets += quantity;
        backpackVolume += quantity * 5;
        maxTotalVolume += quantity * 5;
        backpackWeight += quantity;
        maxTotalWeight += quantity;
        return qPockets;
    }

    public boolean delPocket(int quantity){
        // нельзя снимать карманы, не вынув достаточное количество вещей.

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
            return true;
        } else {
            return false;
        }

    }

    public int quantityOfItems(){
        int sizeOfItems;
        if (items.isEmpty() ) sizeOfItems = 0;
        else sizeOfItems = items.size();
        return sizeOfItems;
    }

    public int volumeAllItems(){
        int itemsVolume = 0;

        for (int itemVol = 0; itemVol < items.size(); itemVol++) {
            itemsVolume += items.get(itemVol).volume;
        }

        return itemsVolume;
    }

    public int weightAllItems(){
        int itemsWeight = 0;

        for (int itemW = 0; itemW < items.size(); itemW++) {
            itemsWeight += items.get(itemW).weight;
        }

        return itemsWeight;
    }

    public int freeVolume(){
        int freeVolume = maxTotalVolume - volumeAllItems();
        return  freeVolume;
    }

    public int freeWeight(){
        int freeWeight = maxTotalWeight - weightAllItems();
        return freeWeight;
    }

    public int totalVolumePercentage(){
        return ((100 * volumeAllItems()) / maxTotalVolume);
    }

    public int totalWeightPercentage(){
        return ((100 * weightAllItems()) / maxTotalWeight);
    }

    public String percentageOfVolumeByType(){
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

            for (int ind = 0; ind < items.size(); ind++){
                tmpItemType = items.get(ind).type;
                if (tmpItemType == firstTypeItem) {
                    itemsFirstType.add(items.get(ind));
                } else if (tmpItemType == secondTypeItem) {
                    itemsSecondType.add(items.get(ind));
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
                answerVolumeByTypes = ((100 * volumeAllItems()) / maxTotalVolume)+"% занимает " + firstTypeItem;
            }
        }
        return answerVolumeByTypes;
    }

    public String percentageOfWeightByType(){
        ArrayList<Item> itemsFirstType = new ArrayList<>();
        ArrayList<Item> itemsSecondType = new ArrayList<>();
        int weightItemsFirstType = 0;
        int weightItemsSecondType = 0;
        String answerWeightByTypes;
        TypeItem firstTypeItem;
        TypeItem secondTypeItem = null;

        if (items.isEmpty()) {
            answerWeightByTypes = "0%";
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

            for (int ind = 0; ind < items.size(); ind++){
                tmpItemType = items.get(ind).type;
                if (tmpItemType == firstTypeItem) {
                    itemsFirstType.add(items.get(ind));
                } else if (tmpItemType == secondTypeItem) {
                    itemsSecondType.add(items.get(ind));
                }
            }

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
                answerWeightByTypes = ((100 * weightAllItems()) / maxTotalWeight)+"% занимает " + firstTypeItem;
            }
        }
        return answerWeightByTypes;
    }

}
