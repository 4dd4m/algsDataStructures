package localhost.bag;
import localhost.bag.BagInterface;

public class LinkedList<T> implements BagInterface{

    private MyNode<T> firstnode;
    private Integer numOfEntries;

    public LinkedList(){
        firstnode = null;
        numOfEntries = 0;
    }

    @Override
    public int getCurrentSize() {
        return numOfEntries;
    }

    @Override
    public boolean isEmpty() {
        return numOfEntries > 0;
    }

    @Override
    public boolean addNewEntry(Object newEntry) {
        MyNode<T> newNode = new MyNode<T>((T) newEntry);
        newNode.setNext(firstnode);
        firstnode = newNode;
        numOfEntries++;
        return true;
    }

    @Override
    public T remove() {
        if(firstnode != null){
            T result = firstnode.getData();
            firstnode = firstnode.getNext();
            numOfEntries--;
            return result;
        }else return null;
    }

    @Override
    public boolean remove(Object anEntry) {
        MyNode<T> nodeToRemove = findEntry((T) anEntry);
        if(nodeToRemove == null){
            return false;
        }
        nodeToRemove.setData(firstnode.getData());
        firstnode = firstnode.getNext();
        numOfEntries--;
        return true;
    }

    @Override
    public void clear() {
        firstnode.setNext(null);
        numOfEntries = 0;
    }

    @Override
    public int getFrequencyOf(Object anEntry) {
        int count = 0;
        MyNode<T> currentNode = firstnode;

        while (currentNode != null) {
            if (currentNode.getData().equals(anEntry)) {
                count++; 
                //currentNode.setNext(currentNode.getNext());
                currentNode = currentNode.getNext();
            } 
        }

        return count;
    }

    @Override
    public boolean contains(Object anEntry) {
        MyNode<T> currentNode = firstnode;
        boolean found = false;

        while (!found && currentNode != null) {
            if (currentNode.getData().equals(anEntry)) {
                currentNode = currentNode.getNext();
                found = true;
                break;
            } 
        }
        return found;
    }

    private MyNode<T> findEntry(T nodeToFind){
        MyNode<T> currentNode = firstnode;
        boolean found = false;

        while(!found && currentNode.getNext() != null){
            if (currentNode.getData().equals(nodeToFind)) {
                found = true;
            } else{
                currentNode = currentNode.getNext();
            }
            if (found) {
                return currentNode; 
            }  
        }
        return null;
    }

    public T[] toArray(){

        T[] resultArray = (T[]) new Object[numOfEntries];
        int counter = 0;
        MyNode<T> currentNode = firstnode;

        while (currentNode.getNext() != null) {
            resultArray[counter] = currentNode.getData();
            currentNode = currentNode.getNext();
            counter++;
        }

        return resultArray;
    }

    public String toString(){

        MyNode<T> currentNode = firstnode;
        String result = "[ ";
        while (currentNode.getNext() != null) {
            result += currentNode.getData();
            currentNode = currentNode.getNext();
        }
        result += " ]";
        return result;

    }

    public static void main (String args[]){

    }



}
