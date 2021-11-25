package arch;
//setting up individual nodes, which can point to another element
public class Node<T> {
    private T cardValue;
    private Node<T> next;

    public Node(T dataValue) {
        cardValue = dataValue;
        next = null;
    }

    public T getData() {                                //get the value of the node
        return cardValue;
    }

    public void setData(T dataValue) {
        cardValue = dataValue;
    }       //set the node data

    public Node<T> getNext() {
        return next;
    }                         //get the next node

    public void setNext(Node<T> nextNode) {
        next = nextNode;
    }        //set the next node

    public String toString(){                                         //string repr
        if (getData() != null) {
            return "Data:\t" + getData()+ "\t->\t" + getNext();
        }else{
            return "Data:\t null";
        }
    }
}
