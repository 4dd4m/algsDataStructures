package localhost.linkedlist;


public class Queue<T> implements QueInterface<T>{


    private MyNode<T> front;
    private MyNode<T> rear;
    private int numOfEntries;

    public Queue(){
        front = null;
        rear = null;
        numOfEntries = 0;
    }

    public void enque(T newEntry) {
        MyNode<T> newNode = new MyNode<T>(newEntry);


        if (numOfEntries == 0){
            front = newNode;
            rear = newNode;
            front.setNext(rear);
            numOfEntries++;
        }else{
            rear.setNext(newNode);
            rear = newNode;
            numOfEntries++;
        }
    }

    public T deque() {
        if (numOfEntries == 0) return null;

        MyNode<T> firstNode = front;
        MyNode<T> secondNode = front.getNext();

        front = secondNode;

        if(front == null){
            rear = null;
        }

        numOfEntries--;
        return (T) firstNode.getData();
    }

    public T getFront() {
        if (front == null) return null;
        else return front.getData();
    }

    public void clear() {
        front = null;
        rear = null;

    }

    public boolean isEmpty(){
        return numOfEntries == 0;
    }


    public static void main(String[] args) {
        Queue<String> queue = new Queue<String>();    


        queue.enque("egy");
        queue.enque("ketto");
        queue.enque("harom");

        for(int i=0;i < 4 ;i++){
            System.out.println("Get front: " + queue.getFront());
            System.out.println("Deque: " + queue.deque());
            System.out.println("Empty: " + queue.isEmpty());
        
        } 
            System.out.println("Empty: " + queue.isEmpty());
            
            String a = "A";
            String b = "B";
            System.out.println();

    }








}
