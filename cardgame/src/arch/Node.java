package arch;
//setting up individual nodes, which can point to another element
public class Node<T> {
    private T cardValue;
    private Node<T> next;

    public Node() throws NullPointerException{
        throw new NullPointerException("No data in node!");
    }

    public Node(T dataValue) {
        cardValue = dataValue;
        next = null;
    }

    //get the value of the node
    public T getData() {
        return cardValue;
    }

    //set the node data
    public void setData(T dataValue) {
        cardValue = dataValue;
    }

    //get the next node
    public Node<T> getNext() {
        return next;
    }

    //set the next node
    public void setNext(Node<T> nextNode) {
        next = nextNode;
    }

    //string repr
    public String toString(){
        if (getData() != null) {
            return "Data:\t" + getData()+ "\t->\t" + getNext();
        }else{
            return "Data:\t null";
        }
    }
}
