package ee.algo3.Nodes;

import ee.algo3.backPack.BackPack;
import ee.algo3.items.Item;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Henri on 11/12/2014.
 */
public class Node {

    private final BackPack bPack; //Igale lehel-le vastab backpack
    public final int level;     // lehe kõrgus puus
    private int bound = -1;     //lehe koguväärtus
    private final int weight; // lehe koguraskus
    private int value; // lehe väärtus
    private List<Item> branch; // Itemite list, mis on lehes

    /**
     * Node konstruktor, kus lisatakse node algväärtused
     * @param bPack - viide seljakotile, kus on esemed
     * @param parentNode - viide naabernode-le
     * @param includeNext   - kas lisatakse järgmine item või mitte
     */
    public Node(BackPack bPack, Node parentNode, boolean includeNext) {
        this.bPack = bPack;     // lisa koti
        level = parentNode.level + 1;   // järgmise lehe väärtus on 1 võrra kõrgem, kui eelmise oma
        branch = new ArrayList<Item>(parentNode.branch);    //lisan uue item-ite listi

        if (includeNext) {
            weight = parentNode.weight + bPack.getItems().get(level).getWeight();
            value = parentNode.value + bPack.getItems().get(level).getValue();
            this.branch.add(bPack.getItems().get(level));
        } else {
            weight = parentNode.weight;
            value = parentNode.value;
        }
    }

    /**
     * Konstruktor esimese node tegemiseks
     * @param bPack - node kott, kus ta asub
     */
    public Node(BackPack bPack) {
        this.bPack = bPack;
        this.weight = 0;
        this.value = 0;
        this.level = -1;
        branch = new ArrayList<Item>();
    }

    public int getValue() {
        return value;
    }


    public int getWeight() {
        return weight;
    }


    public double getBound() {
        return bound;
    }

    public List<Item> getBranch() {
        return branch;
    }

    /**
     * Koguväärtuse arvutamise kalkulaator
     */
    public void calculateBound() {
        bound = 0;
        int childLevel;
        int totalWeight;

        // Kui raskust ei ületa
        if (weight < bPack.getMaxWeight()) {
            bound = value; //algväärtustame koguväärtuse samaseks väärtusega
            childLevel = level + 1; // määrame childleveli
            totalWeight = weight;   // määrame totalweightiks = lehe raskusega

            Item childItem; //teeme childItemi

            //kuni totalweight ei ületa bpacki max raskust ning itemite count ei ületa kogu seljakoti itemite arvu
            while (childLevel < bPack.getItemCount() &&
                    totalWeight + (childItem = bPack.getItems().get(childLevel)).getWeight() <= bPack.getMaxWeight()) {
            // suurendame koguraskus childitemi võrra ning lisame koguväärtusele childItemi väärtused
                totalWeight += childItem.getWeight();
                bound += childItem.getValue();
                childLevel++;
            }

            // kui while lõppes enne, kui jõudsime puu lõppu, siis suurendame boundi järelejäänud kaalu ja eelmise elemendi kaalu/väärtuse
            //suhte korrutise võrra
            if (childLevel < bPack.getItemCount()) {
                bound += (bPack.getMaxWeight() - totalWeight) * bPack.getItems().get(childLevel).getRatio();
            }

        }
    }

}
