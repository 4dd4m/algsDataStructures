package arch;
import arch.*;

public class Deck<T> extends LinkedList{

    private Card firstNode;
    private Card lastNode;
    private boolean lock = false;

    private int MAXCARDS = 52;
    private int numOfCards = 0;

    private static final String[] SUITS = {"h","d","s","c"};

    public Deck(){
        for (int r = 1; r <= 13 ; r++) {
            for (int s = 0; s <= 3 ; s++) {
                addNewEntry(new Card(r, SUITS[s]));
                numOfCards++;
            }
        }
    }

    public boolean addNewEntry(Card newEntry) throws IllegalStateException {
        if (lock == true){ //when the deck is locked (after shuffle), disable the method
            throw new IllegalStateException("Deck is Locked, not modifiable");
        }

        Card newNode = newEntry;
        newNode.setNext(firstNode);
        firstNode = newNode;
        size++;
        if (getSize() == 1) //if we have only one node, we record it as a last node
            lastNode = newNode;
        return true;
    }
    
    public Card[] toArray(){
        Card[] resultArray =  new Card[size];

        if (isEmpty() == true)
            return resultArray;

        int counter = 0;
        Card currentNode = firstNode;

        while (currentNode.getNext() != null) {
            resultArray[counter] = currentNode;
            currentNode = (Card) currentNode.getNext();
            counter++;
        }
        return resultArray;
    }

    public String toString(){
        if (isEmpty() == true) {
            return "[]";
        }
        Card currentNode = firstNode;
        String result = "[";
        while (currentNode.getNext() != null) {
            result += "["+currentNode.getCardValue()+currentNode.getSuit()+"]";
            currentNode = (Card) currentNode.getNext();
        }
        //if (getLastNode() != null)
        //result += lastNode.getData();
        result += "]";
        return result;
    }

    public int getNumOfCards() {
        return numOfCards;
    }

    public Card getFirstCard() {
        return firstNode;
    }

    public Card getLastCard() {
        return lastNode;
    }
}
