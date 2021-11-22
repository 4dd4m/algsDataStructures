package arch;
//setting up individual nodes, which can point to another element
public class Node<T> {
    private T data;
    private Node<T> next;

    public Node(T dataValue) {
        data = dataValue;
        next = null;
    }

    public T getData() {
        return data;
    }

    public void setData(T dataValue) {
        data = dataValue;
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
