package arch;
//setting up individual nodes, which can point to another element
public class Node<T> {
    private T cardValue;
    private Node<T> next;

    public Node(T dataValue) {
        cardValue = dataValue;
        next = null;
    }

    public T getData() {
        return cardValue;
    }

    public void setData(T dataValue) {
        cardValue = dataValue;
    }

    public Node<T> getNext() {
        return next;
    }

    public void setNext(Node<T> nextNode) {
        next = nextNode;
    }

    public String toString(){
        if (getData() != null) {
            return "Data:\t" + getData()+ "\t->\t" + getNext();
        }else{
            return "Data:\t null";
        }
    }
}
