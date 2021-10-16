package localhost.linkedlist;




/**
 * QueInterface
 */
public interface QueInterface<T> {

    public void enque( T newEntry);

    public T deque();

    public T getFront();

    public void clear();
    
}
