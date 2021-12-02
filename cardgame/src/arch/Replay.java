package arch;

import java.util.Scanner;

public class Replay<T> extends Stack implements StackInterface {
    private ReplayItem firstItem, lastItem;

    //add a new item to replay
    public boolean push(ReplayItem pItem) throws LockedDeckException {
        if (firstItem != null) {
            pItem.setNext(firstItem);
        }
        firstItem = pItem;

        size++;
        //if we have only one node, we record it as a last node
        if (size == 1)
            lastItem = pItem;
        return true;
    }

    //array representation of the replay
    public ReplayItem[] toArray() throws EmptyDeckException {
        ReplayItem[] itemArray = new ReplayItem[size];
        ReplayItem[] emptyArray = new ReplayItem[0];

        if (isEmpty() == true)
            throw new EmptyDeckException("Deck is empty");

        //build the result array
        int counter = 0;
        ReplayItem currentItem = firstItem;
        while (currentItem.getNext() != null) {
            itemArray[counter] = currentItem;
            currentItem = (ReplayItem) currentItem.getNext();
            counter++;
        }
        return itemArray;
    }

    //get the size of the replay
    public int getSize() {                                   //get the size of the deck
        return size;
    }

    //string representation
    public String toString() throws NullPointerException {

        ReplayItem currentItem = firstItem;
        String result = "";
        //result += firstItem.getData()+"\n";
        ReplayItem nextItem = (ReplayItem) currentItem.getNext();

        //fill the array
        while (currentItem.getNext() != null) {
            result = currentItem.getData();
            currentItem = (ReplayItem) currentItem.getNext();
            System.out.println(result);

        }
        System.out.println(lastItem.getData());
        result = lastItem.getData() + result + "\n";

        return "";
    }

    //grab the last replay item
    public ReplayItem getLastItem() throws NullPointerException {
        if (firstItem == null)
            throw new NullPointerException("The Deck is Empty");
        return lastItem;
    }

    //grab the last card and delete it
    public String getLastItemAndDelete(){
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
    //print out in reverse
    public void printHistory(){
        Scanner scan = new Scanner(System.in);
        while(size != 0){
            System.out.println(getLastItemAndDelete());
            System.out.print(">>>");
            scan.nextLine();
        }
        System.out.println(">>>Bye...");
    }
}