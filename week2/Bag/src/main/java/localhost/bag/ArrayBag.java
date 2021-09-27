package localhost.bag;

public final class ArrayBag<T> implements BagInterface<T>{

    private T[] bag;
    private int numberOfEntries;
    private static final int DEFAULT_CAPACITY = 25;
    private static final int MAXIMUM_CAPACITY = 25;
    private boolean initialized = false;

    public ArrayBag(){
        this(DEFAULT_CAPACITY);
    }

    public void checkInitialization(){
        if (!initialized) {
            throw new SecurityException("0");
        }
    }

    private T removeElementAt(int index){
        T result = null;
        if (!isEmpty() && index >= 0 && index < numberOfEntries){
            result = bag[index];
            bag[index] = bag[numberOfEntries -1];
            bag[numberOfEntries - 1] = null;
            numberOfEntries--;
        }
        return result;
    }

    public ArrayBag(int capacity){
        if (capacity < MAXIMUM_CAPACITY) {
            T[] tempBag = (T[]) new Object[capacity];
            bag = tempBag;
            numberOfEntries = 0;
        }else{
            throw new SecurityException("0");
        }
        initialized = true;
    }

    public int getCurrentSize(){
        return numberOfEntries;
    }
    public boolean isEmpty(){
        return numberOfEntries == 0;
    }

    private boolean isArrayFull(){
        return (bag.length == numberOfEntries);
    }

    public boolean addNewEntry(T newEntry){
        if (isArrayFull()) return false;
        else{
            bag[numberOfEntries++] = newEntry;
        }
        return true;
    }

    public T remove(){
        T result = null;
        return removeElementAt(numberOfEntries - 1);
    }

    public boolean remove(T anEntry){
        boolean found = false;
        int index = 0;
        while(!found && index < numberOfEntries){
            if (bag[index] != null) {

                if (bag[index].equals(anEntry)) {
                    found = true;
                }else{
                    index++;
                }
            }
        }
        if (found) {
            removeElementAt(index);
        }
        return found;
    }

    public void clear(){
        while(!isEmpty()){
            remove();
        }
    }
    public int getFrequencyOf(T anEntry){
        int count = 0;
        for (int i = 0; i < numberOfEntries; i++) {
            if (bag[i] == anEntry) {
                count++;
            }
        }
        return count;
    }

    public boolean contains(T anEntry){
        boolean found = false;
        for (int i = 0; i < numberOfEntries; i++) {
            if (bag[i] != null) {
                if (bag[i].equals(anEntry)) {
                    found = true;
                }
            }
        }
        return found;
    }

    public T[] toArray(){
        T[] resultArray = (T[]) new Object[numberOfEntries];
        System.arraycopy(bag, 0, resultArray, 0, numberOfEntries);
        return resultArray;
    }

    public static void main(String[] args) {
        ArrayBag<String> bagOfNames = new ArrayBag<String>(5);
        System.out.println("Adrian" + bagOfNames.addNewEntry("Adrian"));
        System.out.println("Adrian" + bagOfNames.addNewEntry("Adrian"));
        System.out.println("Adrian" + bagOfNames.addNewEntry("Adrian"));
        System.out.println("Adrian" + bagOfNames.addNewEntry("Adrian"));
        System.out.println("Adrian" + bagOfNames.addNewEntry("Adrian"));
        System.out.println("Adrian" + bagOfNames.addNewEntry("Adrian"));
        Object[] arrayOfNames = bagOfNames.toArray();
        for (Object name : arrayOfNames) {
            System.out.println(name + "... ");
        }
    }
}
