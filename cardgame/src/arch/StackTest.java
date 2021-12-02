package arch;

public class StackTest {


    public static void main(String[] args) {
        Stack s = new Stack();
        int[] p1 = {3, 6, 9};
        int[] p2 = {0, 1, 1};
        int[] p3 = {2, 9, 0};
        int[] p4 = {1, 0, 1};

        System.out.println("Pushing {3, 6, 9}  - order is always " +
                "descending in a stack element");
        s.push(p1);
        System.out.println(s);
        System.out.println("Size of the stack is: " + s.getSize());

        System.out.println("Pushing {0,1,1}");
        s.push(p2);
        System.out.println(s);
        System.out.println("Size of the stack is: " + s.getSize());
        System.out.println("Pushing {2, 9, 0}");
        s.push(p3);
        System.out.println(s);
        System.out.println("Size of the stack is: " + s.getSize());
        System.out.println("Pushing {1, 0, 1}");
        s.push(p4);
        System.out.println(s);
        System.out.println("Size of the stack is: " + s.getSize());
        System.out.println("Content of the Stack");
        System.out.println(s);
        System.out.println("Popping one item");
        s.pop();
        System.out.println(s);
        System.out.println("Testing Peak");
        int[] peek = s.peek();
        for (int i : peek){
            System.out.println(i);
        }
        System.out.println(s);
        System.out.println("Popping the three remaining element");
        s.pop();
        s.pop();
        s.pop();
        System.out.println(s);
        System.out.println("Try to pop with no elements at all (null return)");
        System.out.println(s.pop());
    }
}
