package ee.algo3;

import ee.algo3.Nodes.Node;
import ee.algo3.backPack.BackPack;
import ee.algo3.items.Item;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        List<Item> inputItems = null;



        int maxWeight = inputItems.get(0).getWeight();
        inputItems = inputItems.subList(1, inputItems.size());

        BackPack BackPack = new BackPack();

        Node resultNode = BackPack.BackPackDepthFirst(inputItems, maxWeight);
        List<Item> resultItems = resultNode.getBranch();
        List<Item> resultItems2 = new ArrayList<Item>(1);
        resultItems2.add(new Item(resultNode.getWeight(), resultNode.getValue()));
        resultItems2.addAll(resultItems);


    }

}
