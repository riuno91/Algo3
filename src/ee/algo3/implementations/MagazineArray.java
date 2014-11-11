package teine.kodutöö.algoritmid.implementations;

/**
 * (stack - LIFO) tuleb realiseerida dünaamilise massiivi abil.
 * Created by Rauno-Sten Reile 104468IAPB on 14.10.2014.
 */
public class MagazineArray extends DynamicArray {

    /**
     * Loob uue tühja magasini O(1)
     */
    public MagazineArray() {

        super();
    }

    /**
     *Lisab täisarvu x magasini (listi algusesse) O(1)
     */
    public void push(int x){

        super.add(x);

    }

    /**
     * Eemaldab ja väljastab magasini viimasena sisestatud elemendi O(1)
     */
    public int pop (){
        int n = 0;

        n = super.rem();

        return n;
    }

    /**
     * Väjastab true, kui magasin on tühi O(1)
     */
    public boolean isEmpty(){

        if ((super.len()) <= 0){
            return true;
        }else return false;


    }
}
