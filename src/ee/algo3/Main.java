package ee.algo3;

import ee.algo3.Nodes.Node;
import ee.algo3.backPack.BackPack;
import ee.algo3.items.Item;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {


        Scanner s = new Scanner(new File(args[0] + ".in"));
        List<Item> inputItems = new ArrayList<Item>();

        while (s.hasNextLine()){
            String[] splitline = s.nextLine().split(" ");
            if(splitline.length == 2) {
                inputItems.add(new Item(Integer.parseInt(splitline[1]), Integer.parseInt(splitline[0])));
            }else{
                inputItems.add(new Item(Integer.parseInt(splitline[0]),0));
            }
        }
        s.close();

        /*
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
        */
        BackPack BackPack = new BackPack();

        int maxWeight = inputItems.get(0).getWeight();
        inputItems = inputItems.subList(1, inputItems.size());

        //Node resultNode = BackPack.BackPackDepthFirst(inputItems, maxWeight);
        Node resultNode = BackPack.BackPackBestFirst(inputItems, maxWeight);
        List<Item> resultItems = resultNode.getBranch();
        List<Item> resultItems2 = new ArrayList<Item>(1);
        resultItems2.add(new Item(resultNode.getWeight(), resultNode.getValue()));
        resultItems2.addAll(resultItems);

        System.out.println (resultItems2);


        FileWriter writer = new FileWriter(args[0] + ".out");
        for(Item item: resultItems2) {
            writer.write(item.getValue() + " " + item.getWeight()+ "\n");
        }
        writer.close();
    }

}
