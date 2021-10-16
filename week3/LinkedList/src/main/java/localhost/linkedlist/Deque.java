package localhost.linkedlist;


public class Deque<T> implements DequeInterFace<T>{

    private MyNode<T> front;
    private MyNode<T> back;
    private int numOfEntries;

    public Deque(){

        front = null;
        back = null;
        numOfEntries = 0;
    }


	@Override
	public void addToFront(T newEnrty) {
		
	}

	@Override
	public void addToBack(T newEnrty) {
		
	}

	@Override
	public T removeFront() {
    MyNode<T> firstNode = front;

    if (front != null) {

        front = front.getNext();
        

        return (T) firstNode.getData();
    }

		return null;
	}

	@Override
	public T removeBack() {
    MyNode<T> lastNode = back;

    if (back != null) {
        

        return (T) back.getData();
    }
		return null;
	}

	@Override
	public T getFront() {
		if (front != null) {
		   return (T) front; 
		}
        return null;
	}

	@Override
	public T getBack() {
		if (back != null) {
		   return (T) back; 
		}
		return null;
	}

	@Override
	public boolean isEmpty() {
		return numOfEntries == 0;
	}

	@Override
	public void clear() {
        front = null;
        back = null;
	}
    public static void main(String args[]){
        
    }
}
