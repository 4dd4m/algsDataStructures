package arch;

import java.util.Scanner;

public class Replay<T> extends Stack implements StackInterface {
    private ReplayItem firstItem, lastItem;
    private String data;

    public boolean push(ReplayItem pItem) throws LockedDeckException {              //add a new item to replay
        if (firstItem != null) {
            pItem.setNext(firstItem);
        }
        firstItem = pItem;

        size++;
        if (size == 1)                                  //if we have only one node, we record it as a last node
            lastItem = pItem;
        return true;
    }

    public ReplayItem[] toArray() throws EmptyDeckException {      //array representation of the replay
        ReplayItem[] itemArray = new ReplayItem[size];             //full size array
        ReplayItem[] emptyArray = new ReplayItem[0];               //empty array for return

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

    public int getSize() {                                   //get the size of the deck
        return size;
    }                     //get the size of the replay

    public String toString() throws NullPointerException {     //string representation

        ReplayItem currentItem = firstItem;
        String result = "";
        //result += firstItem.getData()+"\n";
        ReplayItem nextItem = (ReplayItem) currentItem.getNext();

        while (currentItem.getNext() != null) {                             //fill the array
            result = currentItem.getData();
            currentItem = (ReplayItem) currentItem.getNext();
            System.out.println(result);

        }
        System.out.println(lastItem.getData());
        result = lastItem.getData() + result + "\n";

        return "";
    }

    public ReplayItem getLastItem() throws NullPointerException {           //grab the last replay item
        if (firstItem == null)
            throw new NullPointerException("The Deck is Empty");
        return lastItem;
    }

    public String getLastItemAndDelete(){                                   //grab the last card and delete it
        if (size >0){
            ReplayItem item = getLastItem();

        }
        String tmp = "-1";
        ReplayItem currentItem = firstItem;
        for (int i = 0; i < size-1; i++) {
            currentItem = (ReplayItem) currentItem.getNext();
        }
        tmp =  currentItem.getData();
        currentItem.setNext(null);
        size--;
        return tmp;
    }

    public void printHistory(){                                               //print out in reverse
        Scanner scan = new Scanner(System.in);
        while(size != 0){
            System.out.println(getLastItemAndDelete());
            System.out.print(">>>");
            scan.nextLine();
        }
        System.out.println(">>>Bye...");
    }
}