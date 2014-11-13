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
    private double bound = -1;

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

    public Node(BackPack bPack) {
        this.bPack = bPack;
        this.weight = 0;
        this.value = 0;
        this.level = -1;
        branch = new ArrayList<Item>();
        // don't add item to branch
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

    public void calculateBound() {
        bound = 0;
        int childLevel;
        double totalWeight;

        if (weight < bPack.getMaxWeight()) {
            bound = value;
            childLevel = level + 1;
            totalWeight = weight;

            Item childItem;
            while (childLevel < bPack.getItemCount() &&
                    totalWeight + (childItem = bPack.getItems().get(childLevel)).getWeight() <= bPack.getMaxWeight()) {
                totalWeight += childItem.getWeight();
                bound += childItem.getPrice();
                childLevel++;

            }

            // if the above loop terminated (weight over limit) before we managed to fully pass the tree down
            // then we attempt to estimate
            if (childLevel < bPack.getItemCount()) {
                // TODO: understand this
                bound += (bPack.getMaxWeight() - totalWeight) * bPack.getItems().get(childLevel).getRate();
            }

        }
    }

}
