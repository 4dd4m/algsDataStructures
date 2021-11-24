package arch;

public class LinkedList<T>{
    protected Node<T> firstNode;
    protected Node<T> lastNode;
    protected Integer size;

    public LinkedList(){
        firstNode = null;
        lastNode = null;
        size = 0;
    }

    public Node<T> getFirstNode() {
        return firstNode;
    }

    public Node<T> getLastNode() {
        return lastNode;
    }

    public int getSize() {
        return size;
    }


    public boolean isEmpty() {
        return size == 0;
    }

    protected void setLastNode(){
        //this method updates the last node, which is required if we remove exactly the last element from the list
        if (getSize() == 0){
            lastNode = null;
        }
        else if (getSize() == 1){
            lastNode = firstNode;
        }
        else {
            Node<T> currentNode = firstNode;
            while (currentNode != null) {
                if (currentNode.getNext() != null) {
                    currentNode = currentNode.getNext();
                } else {
                    lastNode = currentNode;
                    break;
                }
            }
        }
    }

    public boolean addNewEntry(Object newEntry) {
        Node<T> newNode = new Node<T>((T) newEntry);
        newNode.setNext(firstNode);
        firstNode = newNode;
        size++;
        if (getSize() == 1) //if we have only one node, we record it as a last node
            lastNode = newNode;
        return true;
    }

    public T removeFirstCard() {
        if(firstNode != null){
            T result = firstNode.getData();
            firstNode = firstNode.getNext();
            size--;
            setLastNode();
            return result;
        }else return null;
    }

    public boolean removeFirstCard(Object anEntry) {
        Node<T> nodeToRemove = findEntry((T) anEntry);
        if(nodeToRemove.getData() == null){
            return false;
        }
        Node<T> node = firstNode;

        nodeToRemove.setData(firstNode.getData());
        firstNode = firstNode.getNext();
        size--;
        return true;
    }

    public void clear() {
        firstNode.setNext(null);
        lastNode.setNext(null);
        size = 0;
    }

    public int getFrequencyOf(Object anEntry) {
        int count = 0;
        Node<T> currentNode = firstNode;

        while (currentNode != null) {
            if (currentNode.getData().equals(anEntry)) {
                count++;
                //currentNode.setNext(currentNode.getNext());
                currentNode = currentNode.getNext();
            }
        }
        return count;
    }

    public boolean contains(Object anEntry) {
        Node<T> currentNode = firstNode;
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

    protected Node<T> findEntry(T nodeToFind){
        Node<T> currentNode = firstNode;
        boolean found = false;

        while(!found && currentNode.getNext() != null){
            if (currentNode.getData().equals(nodeToFind)) {
                found = true;
                return currentNode;
            } else{
                currentNode = currentNode.getNext();
            }
        }
        if (lastNode.getData() == nodeToFind){
            return lastNode;
        }
        return null;
    }

    public T[] toArray() throws EmptyDeckException{
        T[] resultArray = (T[]) new Object[size];

        if (isEmpty() == true)
            return resultArray;

        int counter = 0;
        Node<T> currentNode = firstNode;

        while (currentNode.getNext() != null) {
            resultArray[counter] = currentNode.getData();
            currentNode = currentNode.getNext();
            counter++;
        }
        return resultArray;
    }

    public String toString(){
        if (isEmpty() == true) {
            return "[]";
        }
        Node<T> currentNode = firstNode;
        String result = "[";
        while (currentNode.getNext() != null) {
            result += currentNode.getData();
            currentNode = currentNode.getNext();
        }
        if (getLastNode() != null)
            result += lastNode.getData();
        result += "]";
        return result;
    }
}
