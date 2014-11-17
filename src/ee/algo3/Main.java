package ee.algo3;

import ee.algo3.Nodes.Node;
import ee.algo3.backPack.BackPack;
import ee.algo3.items.Item;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        List<Item> inputItems = new ArrayList<Item>();


        Item i3 = new Item(15,27);
        inputItems.add(i3);
        Item i1 = new Item(10,18);
        inputItems.add(i1);
        Item i2 = new Item(20,10);
        inputItems.add(i2);
        Item i4 = new Item(4,3);
        inputItems.add(i4);
        Item i5 = new Item(5,3);
        inputItems.add(i5);

        BackPack BackPack = new BackPack();

        //Node resultNode = BackPack.BackPackDepthFirst(inputItems, 25);
        Node resultNode = BackPack.knapSackBestFirst(inputItems, 25);
        List<Item> resultItems = resultNode.getBranch();
        List<Item> resultItems2 = new ArrayList<Item>(1);
        resultItems2.add(new Item(resultNode.getWeight(), resultNode.getValue()));
        resultItems2.addAll(resultItems);

        System.out.println (resultItems2);
    }

}
