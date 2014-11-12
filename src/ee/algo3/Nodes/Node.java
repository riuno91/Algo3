package ee.algo3.Nodes;

import ee.algo3.backPack.BackPack;
import ee.algo3.items.Item;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Henri on 11/12/2014.
 */
public class Node {

    private BackPack bPack;
    public final int level;
    private int bound = -1;

    private final double weight; // total weight of the branch that ends with this Node
    private double value; // total value of the branch that ends with this Node
    private List<Item> branch;

    public Node(BackPack bPack, Node parentNode, boolean includeNext) {
        this.bPack = bPack;
        level = parentNode.level + 1;
        branch = new ArrayList<Item>(parentNode.branch); // shallow copy is needed

        if (includeNext) {
            // if so required, include "this" level
            weight = parentNode.weight + bPack.getItems().get(level).getWeight();
            value = parentNode.value + bPack.getItems().get(level).getPrice();
            this.branch.add(bPack.getItems().get(level));
        } else {
            weight = parentNode.weight;
            value = parentNode.value;
        }
    }

    public int getLevel() {
        return level;
    }

    public double getProfit() {
        return value;
    }


    public double getWeight() {
        return weight;
    }


    public double getBound() {
        return bound;
    }


}
