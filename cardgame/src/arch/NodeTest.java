package arch;
import arch.*;

public class NodeTest {


    public static void main(String[] args) {
        Node<Integer> node1 = new Node<Integer>(1);
        Node<Integer> node2 = new Node<Integer>(2);
        Node<Integer> node3 = new Node<Integer>(3);
        Node<Integer> node4 = new Node<Integer>(4);
        Node<Integer> node5 = new Node<Integer>(5);

        node1.setNext(node2);
        node2.setNext(node3);
        node3.setNext(node4);
        node4.setNext(node5);
        node5.setNext(null);
        System.out.println("Testing The nodes pointing to each other");
        System.out.println(node1.toString());

        System.out.println("\n\nSetting All to null");
        node1.setNext(null);
        node2.setNext(null);
        node3.setNext(null);
        node4.setNext(null);
        node5.setNext(null);
        System.out.println(node1.toString());
        System.out.println(node2.toString());
        System.out.println(node3.toString());
        System.out.println(node4.toString());
        System.out.println(node5.toString());
    }



}
