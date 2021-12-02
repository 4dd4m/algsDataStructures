package arch;

public class LinkedList<T> {
    protected Node<T> firstNode;
    protected Node<T> lastNode;
    protected Integer size;

    public LinkedList() {
        firstNode = null;
        lastNode = null;
        size = 0;
    }

    public Node<T> getFirstNode() {
        return firstNode;
    } //return first node

    public Node<T> getLastNode() {
        return lastNode;
    }//return last node

    public int getSize() {
        return size;
    } //get size

    public boolean isEmpty() {
        return size == 0;
    } //is empty

    protected void updateLastElement() {
        //this method updates the last node, which is required
        // if we remove exactly the last element from the list
        if (getSize() == 0) {
            lastNode = null;
        } else if (getSize() == 1) {
            lastNode = firstNode;
        } else {
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

    //add new element to the data structure
    public boolean addNewEntry(Object newEntry) {
        Node<T> newNode = new Node<T>((T) newEntry);
        newNode.setNext(firstNode);
        firstNode = newNode;
        size++;
        //if we have only one node, we record it as a last node
        if (getSize() == 1)
            lastNode = newNode;
        return true;
    }

    //remove the first element without getting the object
    public T removeFirstElement() throws NullPointerException{
        if(size==0){
            throw new NullPointerException("Empty list");
        }
        if (firstNode != null) {
            T result = firstNode.getData();
            firstNode = firstNode.getNext();
            size--;
            updateLastElement();
            return result;
        } else return null;
    }

    //remove first, return with first
    public boolean removeSpfecific(Object anEntry) {
        Node<T> nodeToRemove = findEntry((T) anEntry);
        if (nodeToRemove.getData() == null) {
            return false;
        }
        Node<T> node = firstNode;

        nodeToRemove.setData(firstNode.getData());
        firstNode = firstNode.getNext();
        size--;
        return true;
    }

    //clear everything
    protected void clear() {
        firstNode.setNext(null);
        lastNode.setNext(null);
        size = 0;
    }

    //find a given entry
    protected Node<T> findEntry(T nodeToFind) {
        Node<T> currentNode = firstNode;
        boolean found = false;

        while (!found && currentNode.getNext() != null) {
            if (currentNode.getData().equals(nodeToFind)) {
                found = true;
                return currentNode;
            } else {
                currentNode = currentNode.getNext();
            }
        }
        if (lastNode.getData() == nodeToFind) {
            return lastNode;
        }
        return null;
    }

    //representation in array
    public T[] toArray() throws EmptyDeckException {
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

    //string representation
    public String toString() {
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
