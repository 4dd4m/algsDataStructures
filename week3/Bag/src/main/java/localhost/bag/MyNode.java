package localhost.bag;

public class MyNode<T>{

    private T data;
    private MyNode<T> next;


    public MyNode(T datavalue){
        data = datavalue;
        next = null;
    }

    public T getData(){
        return data;
    }

    public void setData(T datavalue){
        data = datavalue;
    }

    public MyNode<T> getNext(){
        return next;
    }

    public void setNext(MyNode<T> nextNode){
        next = nextNode;
    }

public static void main(String[] args) {
    MyNode<Integer> node1 = new MyNode<Integer>(1);
    MyNode<Integer> node2 = new MyNode<Integer>(2);
    MyNode<Integer> node3 = new MyNode<Integer>(3);

    node1.setNext(node2);
    node2.setNext(node3);

    System.out.println("Node 1 : " + node1.getData());
    System.out.println("Node 2 : " + node2.getData());
    System.out.println("Node 3 : " + node3.getData());
}
}
