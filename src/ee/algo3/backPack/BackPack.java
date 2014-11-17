package ee.algo3.backPack;


import ee.algo3.Nodes.Node;
import ee.algo3.implementations.MagazineArray;
import ee.algo3.items.Item;
import java.util.Collections;
import java.util.List;


public class BackPack {
    private int maxWeight;
    private List<Item> items;
    private Node optimalNode;

    public int getMaxWeight() {
        return maxWeight;
    }

    public List<Item> getItems() {
        return items;
    }

    public int getItemCount() {
        return items.size();
    }


    public Node BackPackDepthFirst(List<Item> items, int maxWeight) {
        this.maxWeight = maxWeight;			// maksimaalne kaal inputist
        this.items = items;					// input itemid, koik, list
        MagazineArray mga = new MagazineArray();
        calculate(mga);
        return optimalNode;
    }


    public Item[] getSetOfBestItems() {
        return optimalNode.getBranch().toArray(new Item[optimalNode.getBranch().size()]);
    }

    public List<Item> getListSetOfBestItems() {
        return optimalNode.getBranch();
    }


    // mida sa siia sisse votad ma ei saa aru, mis sul viga on?

    private void calculate(MagazineArray data) {
        //peame itemid ara sortima, et algo saaks oigesti teha
        // korgema kaaluga tulevad esimesena

        Collections.sort(items);

        // kas see on siis ylemine node???
        //weigth on 0,
        //price on ka 0
        //teeme boundi ka sellele libule

        Node rootNode = new Node(this); // the top of the decision tree (at first)
        rootNode.calculateBound();
        data.push(rootNode);

        Node childNode;
        optimalNode = new Node(this);
        while (!data.isEmpty()) {
            rootNode = data.pop();

            System.out.println ("Data size on " + data.len()+ "    " + optimalNode.getBound());
            if (rootNode.getBound() > optimalNode.getValue()) {
                // set childNode to the child that *does* include the next item
                childNode = new Node(this, rootNode, true);

                // if childNode's value is bigger than current largest value
                if (childNode.getWeight() <= maxWeight && childNode.getValue() > optimalNode.getValue()) {
                    optimalNode = childNode;
                }

                childNode.calculateBound();
                if (childNode.getBound() > optimalNode.getValue()) {
                    //System.out.println("Putting to queue because " + childNode.getBound() + ">" + optimalNode.getValue());

                    data.push(childNode);
                }

                // set childNode to the child that *does not* include the next item
                childNode = new Node(this, rootNode, false);

                childNode.calculateBound();
                if (childNode.getBound() > optimalNode.getValue()) {
                    //System.out.println("Putting to queue because " + childNode.getBound() + ">" + optimalNode.getValue());
                    data.push(childNode);
                }

            }
        }
    }

}
