package ee.algo3.backPack;

import ee.algo3.items.Item;

import java.util.ArrayList;

/**
 * Created by Riuno on 11.11.2014.
 */
public class BackPack {
    private double weight;
    private ArrayList<Item> items;

    public BackPack() {
        items = new ArrayList<Item>();
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }
}
