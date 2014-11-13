package ee.algo3.backPack;

import ee.algo3.Nodes.Node;
import ee.algo3.implementations.MagazineArray;
import ee.algo3.items.Item;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Riuno on 11.11.2014.
 */
public class BackPack {
    private int weight;
    private int profit;
    private int maxWeight;
    private ArrayList<Item> items;
    private Node optimalNode;

    public BackPack() {
        items = new ArrayList<Item>();
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }


    public double getProfit() {
        return profit;
    }

    public void setProfit(int   profit) {
        this.profit = profit;
    }

    public int getMaxWeight() {
        return maxWeight;
    }

    public void setMaxWeight(int maxWeight) {
        this.maxWeight = maxWeight;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }



    public Node knapSackDepthFirst(ArrayList<Item> items, int maxWeight) {
        this.maxWeight = maxWeight;
        this.items = items;
        calculate(new MagazineArray());
        return optimalNode;
    }

    public double knapSackDepthFirst(int[] itemWeights, int[] itemValues, int maxWeight) {

        this.maxWeight = maxWeight;
        items = new ArrayList<Item>(itemValues.length);
        for (int i = 0; i < itemValues.length; i++) {
            items.add(new Item(itemWeights[i], itemValues[i]));
        }
        calculate(new MagazineArray());

        return optimalNode.getProfit();
    }

    private void calculate(MagazineArray data) {
        Collections.sort(items);

        Node rootNode = new Node(this); // the top of the decision tree (at first)
        rootNode.calculateBound();
        data.push(rootNode);

        Node childNode;
        optimalNode = new Node(this);

        while (!data.isEmpty()) {
            rootNode = data.pop(this);

            if (rootNode.getBound() > optimalNode.getProfit()) {
                // set childNode to the child that *does* include the next item
                childNode = new Node(this, rootNode, true);

                // if childNode's value is bigger than current largest value
                if (childNode.getWeight() <= maxWeight && childNode.getProfit() > optimalNode.getProfit()) {
                    optimalNode = childNode;
                }

                childNode.calculateBound();
                if (childNode.getBound() > optimalNode.getProfit()) {
                    //System.out.println("Putting to queue because " + childNode.getBound() + ">" + optimalNode.getValue());

                    data.push(childNode);
                }

                // set childNode to the child that *does not* include the next item
                childNode = new Node(this, rootNode, false);

                childNode.calculateBound();
                if (childNode.getBound() > optimalNode.getProfit()) {
                    //System.out.println("Putting to queue because " + childNode.getBound() + ">" + optimalNode.getValue());
                    data.push(childNode);
                }

            }
        }
    }

    public int getItemCount() {
        return items.size();
    }
}
