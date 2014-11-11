package teine.kodutöö.algoritmid.implementations;

import java.util.Arrays;

/**
 * Created by Henri Liiv on 10/14/2014.
 *
 * Dünaamiline massiiv hoiab andmeid massiivis, mille mahtu suurendatakse kaks
 * korda massiivi täitumisel ja vähendatakse kaks korda, kui täituvus langeb alla 1/4
 *
 */
public class DynamicArray {
    private int[] array;
    public int size;

    /**
     * konstruktor
     * create()
     */
     public DynamicArray(){
        array = new int[0];
        size = -1;
    }
    /**
     * lisame elemendi dünaamilisse massiivi,
     * kui oletatav massiivi pikkus on suurem, kui massiivi tegelik pikkus,
     * siis kasvatame massiivi.
     * @param x
     */
    public void add(int x){

        size++;
        if (size >= array.length) {
            increaseListSize();
        }
        array[size]= x;
    }

    /**
     * eemaldame massiivist viimase elemendi
     * alatäitumisel vähendame massiivi.
     * @return
     */
    public int rem(){

        if (size > -1) {
            int lastElement = array[size];
            array[size] = 0;

            if (array.length / (size + 1) < 0.25) {
                decreaseListSize();
            }

            size--;
            return lastElement;
        } else {
            return 0;
        }

    }

    /**
     * Väljastame n-inda elemendi massiivist
     * @param n
     * @return
     */
    public int get(int n){
        if(n <= size){
            return array[n];
        }
        else return 0;
    }

    /**
     * vahetame elemendi väärtuse tingimusel,
     * et see on olemas.
     * @param x
     * @param i
     */
    public void put(int x, int i){
        if(i<=size){
            array[i]=x;
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
    private void increaseListSize(){

        if (array.length == 0){
            array = Arrays.copyOf(array, 1);
        }else{
            array = Arrays.copyOf(array, array.length * 2);
        }

    }

    /**
     * teeme array kokkuhoiu nimel kaks korda väiksemaks,
     * juhul kui eemaldame sellest elemente
     *
     */
    private void decreaseListSize(){
        array = Arrays.copyOf(array, array.length / 2);
    }

}
