package localhost.linkedlist;

public interface DequeInterFace<T> {



    public void addToFront(T newEnrty);
    public void addToBack(T newEnrty);
    public T removeFront();
    public T removeBack();
    public T getFront();
    public T getBack();
    public boolean isEmpty();
    public void clear();

    
}
