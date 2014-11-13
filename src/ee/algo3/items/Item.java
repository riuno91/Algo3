package ee.algo3.items;


/**
 * Created by Riuno on 11.11.2014.
 */
public class Item implements Comparable<Item> {
    private int weight;
    private int value;

    public Item(int weight, int value) {
        this.weight = weight;
        this.value = value;
    }

    public double getRatio() {
        if (weight == 0) {
            return 0;
        } else {
            return value / weight;
        }
    }

    public int getWeight() {
        return weight;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "Item(w: " + weight + ", v: " + value + ")";
    }

    @Override
    public int compareTo(Item o) {
        return getWeight() - o.getWeight();
    }


}