package arch;

public interface CardInterFace {
    public Card removeFirstElement() throws IllegalStateException;
    public boolean isEmpty();
    public boolean addNewEntry(Object newEntry) throws LockedDeckException;
    public Card[] toArray() throws EmptyDeckException;
    public boolean addNewEntry(Card newEntry) throws LockedDeckException;
    public Card getFirstCard() throws NullPointerException;
    public Card getLastCard() throws NullPointerException;

}
