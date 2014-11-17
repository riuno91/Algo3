package ee.algo3;

import ee.algo3.Nodes.Node;
import ee.algo3.backPack.BackPack;
import ee.algo3.items.Item;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        List<Item> inputItems = new ArrayList<Item>();

        Item i1 = new Item(18,10);
        inputItems.add(i1);
        Item i2 = new Item(10,20);
        inputItems.add(i2);
        Item i3 = new Item(27,15);
        inputItems.add(i3);
        Item i4 = new Item(3,4);
        inputItems.add(i4);
        Item i5 = new Item(3,5);
        inputItems.add(i5);

        BackPack BackPack = new BackPack();

        Node resultNode = BackPack.BackPackDepthFirst(inputItems, 25);
        List<Item> resultItems = resultNode.getBranch();
        List<Item> resultItems2 = new ArrayList<Item>(1);
        resultItems2.add(new Item(resultNode.getWeight(), resultNode.getValue()));
        resultItems2.addAll(resultItems);

        System.out.println (resultItems2);
    }

}
