package ee.algo3.backPack;


import ee.algo3.Nodes.Node;
import ee.algo3.implementations.*;
import ee.algo3.items.Item;
import java.util.Collections;
import java.util.List;

/**
 * Created by Rauno-Sten Reile 104468IAPB & Henri Liiv 103995 on 11.11.2014.
 */

public class BackPack {

    private int maxWeight;      // maksimaalne koti kaal
    private List<Item> items;   // List asjadest, mis on kotis
    private Node bestNode;      //Node, mis on parim


    public int getMaxWeight() {
        return maxWeight;
    }

    public List<Item> getItems() {
        return items;
    }

    public int getItemCount() {
        return items.size();
    }

    /**
     * Arvutatakse välja parim variant sügavuti otsinguga
     * @param items - list itemitest
     * @param maxWeight - koti maksimaalkaal, mida ei tohi ületada
     * @return - tagastab parima lehe
     */
    public Node BackPackDepthFirst(List<Item> items, int maxWeight) {
        this.maxWeight = maxWeight;			// maksimaalne kaal inputist
        this.items = items;					// input itemid, koik, list
        MagazineArray mga = new MagazineArray(); // Uus Stack, kuhu talletatakse Node-d
        calculateDepth(mga);    //arvutatakse Node-d Stacki
        return bestNode;     // tagastatakse parim leht, parima valuega
    }

    private void calculateDepth(MagazineArray data) {

        Collections.sort(items); // sorteeritakse kaalu alusel item-d

        Node rootNode = new Node(this); // esimene node, mis pannakse stacki
        rootNode.calculateBound();      // arvutatakse esimese node väärtus
        data.push(rootNode);            // pannakse esimene node stacki

        Node childNode;                 //tekitatakse naaberleht
        bestNode = new Node(this);   //tekitatakse parima node leht
        while (!data.isEmpty()) { //tsükkel, mida käiakse, kuni stackis on itemeid, et leida parim

            rootNode = data.pop(); //võtame esimese node'i

            if (rootNode.getBound() > bestNode.getValue()) { //kui esimese node koguväärtus on suurem kui parima node koguväärtus

                childNode = new Node(this, rootNode, true); //tekitame naabernode, mis on rootnode naaber

                if (childNode.getWeight() <= maxWeight && childNode.getValue() > bestNode.getValue()) {
                    //kui naabernode kaal jääb maksimumile alla ja value on suurem, kui parima oma, siis valime selle parimaks
                    bestNode = childNode;
                }

                childNode.calculateBound();// arvutan childnode koguväärtuse

                if (childNode.getBound() > bestNode.getValue()) {
                    //lisan childnode stacki kui ta koguväärtus on parem kui parima node oma
                    data.push(childNode);
                }

                childNode = new Node(this, rootNode, false); // Lisan ka teise node, millel puudub eelneva välju ja weight

                childNode.calculateBound();
                if (childNode.getBound() > bestNode.getValue()) { //arvtuan koguväärtuse ja vajadusel lisan stacki
                    data.push(childNode);
                }
            }
        }
    }

    public Node BackPackBestFirst(List<Item> items, int maxWeight) { // best first algoritm, sisu sama, mis depth first
        this.maxWeight = maxWeight;
        this.items = items;
        calculateBest(new PriorityQueue(this));
        return bestNode;
    }

    private void calculateBest(PriorityQueue data) {
        // sordime kõik itemid kaalu järgi
        Collections.sort(items);

        Node rootNode = new Node(this); // esimese node tekitamine
        rootNode.calculateBound();  //arvutatakse esimese node väärtus
        data.enqueue(rootNode); //lisatakse see priorityqueuesse

        Node childNode;         // tekitame childnode
        bestNode = new Node(this);  //tekitame parima node
        while (!data.isEmpty()) {   //kuni on data-t queue-s

            rootNode = data.dequeue();  //võtame välja rootnode
            if (rootNode.getBound() > bestNode.getValue()) { //vaatame, kas rootnode koguväärtus on suurem, kui bestnode väärtus

                childNode = new Node(this, rootNode, true);     //lisame childnode, millel on järgmine item


                // juhul, kui childnodel on koguväärtus suurim, siis määrame ta parimaks
                //samas on sama voi vaiksem kui maxweigth
                if (childNode.getWeight() <= maxWeight && childNode.getValue() > bestNode.getValue()) {
                    bestNode = childNode;
                }

                childNode.calculateBound(); //arvutame childnode koguväärtuse
                if (childNode.getBound() > bestNode.getValue()) {
                    //kui childnode koguväärtus on parem kui parima node väärtus, siis lisame ta queuesse
                    data.enqueue(childNode);
                }

                // lisame childnode naabrile, millele pole järgmise väärtusi
                childNode = new Node(this, rootNode, false);

                childNode.calculateBound(); // arvuta childnode väärtus ning kui see on parima väärtusest suurem, lisa queuesse.
                if (childNode.getBound() > bestNode.getValue()) {
                    //System.out.println("Putting to queue because " + childNode.getBound() + ">" + optimalNode.getValue());
                    data.enqueue(childNode);
                }

            }
        }
    }
}
