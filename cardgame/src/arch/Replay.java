package arch;
public class Replay<T> extends LinkedList{
    private ReplayItem firstItem, lastItem;
    private String data;


    public ReplayItem removeFirstCard() throws IllegalStateException{
        if (size == 0){throw new IllegalStateException("Cannot remove a Card from an empty deck");}
        if(firstItem != null){                                      //removing the very first card
            ReplayItem first = (ReplayItem) firstItem;                          //tmp the first card
            firstItem =  (ReplayItem) firstItem.getNext();                //update the new first card
            size--;
            if (size == 1) setLastNode();                           //for integrity
            return first;                                           //if card removed return with it
        }else return null;                                          //return a BIG null
    }


    public boolean addNewReplay(ReplayItem pitem) throws LockedDeckException{//add a new card to the deck
        if (firstItem != null){
            pitem.setNext(firstItem);
            firstItem = pitem;
        }else{
            firstItem = pitem;
        }

        size++;
        if (size == 1)                                 //if we have only one node, we record it as a last node
            lastItem = pitem;
        return true;
    }

    public ReplayItem[] toArray() throws EmptyDeckException{      //array representation of the deck
        ReplayItem[] itemArray =  new ReplayItem[size];                 //full size array
        ReplayItem[] emptyArray = new ReplayItem[0];                    //empty array for return

        if (isEmpty() == true)
            throw new EmptyDeckException("Deck is empty");

        int counter = 0;                                    //build the result array
        ReplayItem currentItem = firstItem;
        while (currentItem.getNext() != null) {
            itemArray[counter] = currentItem;
            currentItem = (ReplayItem) currentItem.getNext();
            counter++;
        }
        //cardArray[size-1] = (Card) getLastCard();           //grab the last element
        return itemArray;
    }
    public int getSize(){                                   //get the size of the deck
        return size;
    }
    public String toString() throws NullPointerException{     //string representation of the array [Q♠][Q♦][Q♥]
        int c = size;
        ReplayItem currentItem = firstItem;
        String result = "";
        //result += firstItem.getData()+"\n";
        while (currentItem.getNext() != null) {             //fill the array
            result += currentItem.getData()+"\n";
            currentItem = (ReplayItem) currentItem.getNext();
            c--;
        }
        result += lastItem.getData();
        return result;

    }
    public ReplayItem getFirstItem() throws NullPointerException {   //grab the first card
        if (firstItem == null)
            throw new NullPointerException("The Deck is Empty");
        return firstItem;
    }
    public ReplayItem getLastItem() throws NullPointerException  {   //grab the last card
        if (firstItem == null)
            throw new NullPointerException("The Deck is Empty");
        return lastItem;
    }
}