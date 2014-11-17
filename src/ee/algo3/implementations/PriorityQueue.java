package ee.algo3.implementations;


import ee.algo3.Nodes.Node;
import ee.algo3.backPack.BackPack;

/**
 * Created by Rauno-Sten Reile 104468IAPB on 14.10.2014.
 */
public class PriorityQueue extends  DynamicArray{
    DynamicArray minBinHeap;
    int nextpos;

    /**
     *Loome minHeapi,
     *lisame esimese elemendi massiivi (suvaline), et alates positsioonist 1 hakataks t2itma
     * määrame järgmise positsiooniks 1
     */
    public PriorityQueue(BackPack bPack) {

        minBinHeap = new DynamicArray();
        Node fnode = new Node (bPack);
        minBinHeap.add(fnode);
        nextpos = 1;
    }

    /**
     * lisame elemendi järjekorda
     * @param n - number, mis lisatakse minimaalsesse kuhja
     */
    public void enqueue (Node n){

        int positsion = nextpos; // int x-i positsioon, kuhu lisatakse
        minBinHeap.add(n); // lisain xi alguses arraysse

        while(true){

            int upParentPos;
            Node parent;  // int-d ülemelementi jaoks

            //kontrollin ega element ei ole esimene
            if (positsion <= 1){
                break;
            }

            upParentPos = positsion/2;   // v6tan ylemelemendi positsiooni (bin kuhjal alati ylemine postisoon /2 v2iksem)
            parent = minBinHeap.get(upParentPos); //v6tan elemendi arvulise v22rtuse

            if(parent.getValue() < n.getValue()){//kui ylemin parent on v2iksem x-st(min kuhja reegel), siis l6petan
                break;
            }else{ // vastasel korral vahetan positsioonid parrentiga
                minBinHeap.put(n, upParentPos);
                minBinHeap.put(parent, positsion);
            }

            positsion = upParentPos; // m22ran elemendi positsiooniks ylemelemendi positsiooni
        }

        nextpos++; // suurendan järmiseks elemendiks kohta
    }

    /**
     * Antakse välja kuhja tipus olev element
     * @return - tagastan tipus oleva, minimaalse elemendi
     */
    public Node dequeue(){
        Node n, last; //int-d, yks viimane teine esimene element

        n = minBinHeap.get(1); // v6tan esimese m2llu

        last = minBinHeap.get(nextpos-1); // võtan viimase LISATUD elemendi mällu

        minBinHeap.put(last,1); // panen esimeseks elemendiks viimase lisatud elemendi
        minBinHeap.rem();// eemaldan kuhjast viimase elemend (kuna ta nüüd on tipus)

        nextpos--; //vähendan järgmise elemendi kohta (eelmine viimane element läks tippu)
        organizeHeap(); //korrastan kuhja

        return n; //tagastan vana tipus olnud elemendi
    }

    /**
     * Meetod, mis kontrollib, kas kuhi on tühi või mitte, seejuures ei arvestata massiivis element kohal 0 ehk siis
     * kuhi ei ole tühi, kui seal on rohkem kui 1 element
     * @return tagastab tõeväärtuse, kas kuhi on tühi või mitte
     */
    public boolean isEmpty(){
        if (nextpos == 1){
            return true;
        }else return false;

    }

    /**
     * Meetod, mis korrastab peale kuhjast elemendi väljastamist kuhja vastavalt minimaalse kuhja reglemendile.
     *
     */
    private void organizeHeap(){
        int curPos = 1; //esimeseks kohaks valitakse 1 (tipp, kus element väljastati)


        //tsükkel, mis korrastab kuhja
       /* while(true){
            int leftParent = 2*curPos,  rightParent = 2*curPos + 1, smallestPos = curPos;

            //võrreldakse, kas vasak parrent on väiksem, kui hetkel võrreldav element, kui jah, siis
            //märgitakse väikseimaks vasak parrent
            if (leftParent <(nextpos-1) && minBinHeap.get(leftParent) < minBinHeap.get(smallestPos)) {
                smallestPos = leftParent;
            }

            //võrreldakse, kas parem parrent on väiksem, kui hetkel võrreldav element, kui jah, siis
            //märgitakse väikseimaks parem parrent
            if (rightParent < (nextpos-1) && minBinHeap.get(rightParent) < minBinHeap.get(smallestPos)) {
                smallestPos = rightParent;
            }

            // kui left või right parrent on väiksem, kui hetkel võrreldav element, siis tehakse asendus

            if (curPos != smallestPos){
                int smaller = minBinHeap.get(smallestPos);
                //asendan kaks elementi
                minBinHeap.put(minBinHeap.get(curPos),smallestPos);
                minBinHeap.put(smaller,curPos);
                // märgin hetkepositsiooniks uue väikseima elemendi positsiooni
                curPos = smallestPos;

            }else break;

        }*/

    }

   /* public void kontroll(){
        for (int i = 1; i < minBinHeap.len()-1;i++){
            System.out.print(minBinHeap.get(i) + " ");
        }

        System.out.println();
    }*/

}
