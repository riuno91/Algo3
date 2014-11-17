package ee.algo3.implementations;

import ee.algo3.Nodes.Node;
import ee.algo3.backPack.BackPack;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Henri Liiv on 10/14/2014.
 *
 * Dünaamiline massiiv hoiab andmeid massiivis, mille mahtu suurendatakse kaks
 * korda massiivi täitumisel ja vähendatakse kaks korda, kui täituvus langeb alla 1/4
 *
 */
public class DynamicArray {
    private ArrayList<Node> array;
    public int size;

    /**
     * konstruktor
     * create()
     */
     public DynamicArray(){
        array = new ArrayList<Node>();
        size = -1;
    }
    /**
     * lisame elemendi dünaamilisse massiivi,
     * kui oletatav massiivi pikkus on suurem, kui massiivi tegelik pikkus,
     * siis kasvatame massiivi.
     * @param node
     */
    public void add(Node node){

        size++;
     /*   if (size >= array.size()) {
            increaseListSize();
        }*/
        array.set(size,node);
    }

    /**
     * eemaldame massiivist viimase elemendi
     * alatäitumisel vähendame massiivi.
     * @return
     */
    public Node rem(){
        BackPack tempBpack = new BackPack();
        Node lastElement = new Node(tempBpack);
        if (size > -1) {
             lastElement = array.get(size);
            array.set(size,null);
/*
            if (array.size() / (size + 1) < 0.25) {
                decreaseListSize();
            }
*/
            size--;
            return lastElement;
        } else {
            return null;
        }

    }

    /**
     * Väljastame n-inda elemendi massiivist
     * @param n
     * @return
     */
    public Node get(int n){
        if(n <= size){
            return array.get(n);
        }
        else return null;
    }

    /**
     * vahetame elemendi väärtuse tingimusel,
     * et see on olemas.
     * @param x
     * @param i
     */
    public void put(Node x, int i){
        if(i <= size) {
            array.set(i,x);
        }
    }

    /**
     * väljastame massiivi pikkuse
     * @return
     */
    public int len(){
        return size + 1;
    }


    /**
     * teeme array kaks kord suuremaks,
     * et saaks sisestada uusi täisarve array'isse
     */
  /*  private void increaseListSize(){

        if (array.size() == 0){
            array = ArrayList<Node>.
            array = Arrays.copyOf(array, 1);
        }else{
            array = Arrays.copyOf(array, array.length * 2);
        }

    }
*/
    /**
     * teeme array kokkuhoiu nimel kaks korda väiksemaks,
     * juhul kui eemaldame sellest elemente
     *
     */
    /*
    private void decreaseListSize(){
        array = Arrays.copyOf(array, array.length / 2);
    }
*/
}
