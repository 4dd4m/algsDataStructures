package localhost.linkedlist;
import java.util.EmptyStackException;

public class Stack<T> implements StackInterFace<T>{

    MyNode<T> topNode;
    int numOfEntries;


    public  Stack() {
       topNode = null; 
       numOfEntries = 0;
    }

	@Override
	public void push(T newEntry) {
        MyNode<T> newNode = new MyNode<T>(newEntry);
        newNode.setNext(topNode);
        topNode = newNode;
		
	}

	@Override
	public T pop() {
        T dataToReturn = peek();
        topNode = topNode.getNext();
		return dataToReturn;
	}

	@Override
	public T peek() {
        if (topNode == null) throw new EmptyStackException();
		return topNode.getData();
	}

	@Override
	public boolean isEmpty() {
		return topNode == null;
	}

	@Override
	public void clear() {
		topNode = null;
	}

    public String toString(){
    MyNode<T> currentNode = topNode;
        System.out.println("-------------------------");
    while(currentNode != null){
        System.out.println(currentNode.getData());
        currentNode = currentNode.getNext();
    }
        System.out.println("-------------------------");
	return null;

    }

    public static void main(String[] args) {
        Stack<Integer> stack1 = new Stack();
        stack1.push(2);
        stack1.push(4);
        stack1.push(6);

        for(int i=0;i < 4 ;i++){
           try {
              System.out.println("Peek: " + stack1.peek()); 
              System.out.println("Pop: " + stack1.pop()); 
           } catch (Exception e) {
               System.out.println("Exception");
           } 
        }
        System.out.println("Stack is empty: " + stack1.isEmpty());
        
        stack1.push(10);
        stack1.push(20);
        System.out.println("Stack is empty: " + stack1.isEmpty());
        stack1.push(30);

        System.out.println("Stack is empty: " + stack1.isEmpty());

    }




}
