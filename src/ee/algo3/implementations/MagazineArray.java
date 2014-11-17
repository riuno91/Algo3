package ee.algo3.implementations;

import ee.algo3.Nodes.Node;
import ee.algo3.backPack.BackPack;

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
    public void push(Node x){

        super.add(x);

    }

    /**
     * Eemaldab ja väljastab magasini viimasena sisestatud elemendi O(1)
     */
    public Node pop (){

        return super.rem();
    }

    /**
     * Väjastab true, kui magasin on tühi O(1)
     */
    public boolean isEmpty(){

        if (super.get(0) == null){
            return true;
        }else return false;

    }
}
