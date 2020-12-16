package com.company;

public class Item{
    String name; // Название
    int weight; // Вес
    int volume; // Объем
    TypeItem type; // тип вещи

    public Item(String name, int weight, int volume, TypeItem type) {
        this.name = name;
        this.weight = weight;
        this.volume = volume;
        this.type = type;
    }
}
