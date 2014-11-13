package ee.algo3.items;


/**
 * Created by Riuno on 11.11.2014.
 */
public class Item implements Comparable<Item>{
    private int weight;
    private double price;

    public Item(int itemWeight, int itemValue) {
        this.setPrice(itemValue);
        this.setWeight(itemWeight);
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getRate() {
        if (weight == 0) {
            return 0;
        } else {
            return price / weight;
        }
    }

    @Override
    public String toString() {
        return "Item(w: " + weight + ", v: " + price + ")";
    }

    @Override
    public int compareTo(Item o) {
        return getWeight() - o.getWeight();
    }
}
