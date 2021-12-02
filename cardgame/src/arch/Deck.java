package arch;
import java.util.Random;
public class Deck<T> extends LinkedList implements CardInterFace {
    private Card next, firstCard, lastCard;
    private final int SHUFFLESIZE = 1000;
    private boolean lock = false;
    private static final String[] SUITS = {"h","d","s","c"};

    //build a standard deck
    public Deck() throws LockedDeckException, NullPointerException{
        for (int r = 1; r <= 13 ; r++) {
            for (int s = 0; s <= 3 ; s++) {
                addNewEntry(new Card(r, SUITS[s]));
            }
        }
    }

    //for testing
    public Deck(boolean shuffle, boolean fill)throws LockedDeckException,
            NullPointerException, EmptyDeckException{
        //optional shuffle and fill
        if (fill) {
            for (int r = 1; r <= 13; r++) {
                for (int s = 0; s <= 3; s++) {
                    addNewEntry(new Card(r, SUITS[s]));
                }
            }
        }
        if (shuffle){
            shuffle();
            lock=true;
        }

    }

    protected void shuffle() throws LockedDeckException, EmptyDeckException {
        //deck is locked after shuffle, disabling more shuffle
        if (lock == true)
            throw new LockedDeckException("Deck is locked");
        //empty deck cannot be shuffled
        if (size == 0)
            throw new EmptyDeckException("The Deck is Empty");
        Random random = new Random();
        //shuffle N times
        for (int i = 0; i < size*SHUFFLESIZE; i++) {
            int randomElement = random.nextInt(size - 1);
            int j = 0;
            Card currentCard = firstCard;
            //iterate to the card
            while (j < randomElement && currentCard.getNext() != null) {
                currentCard = (Card) currentCard.getNext();
                j++;
            }
            //insert it to the front
            Card first = (Card) currentCard;
            Card SecondCard = (Card) currentCard.getNext();
            Card thirdCard = (Card) currentCard.getNext().getNext();

            first.setNext(thirdCard);
            SecondCard.setNext(this.firstCard);
            this.firstCard = SecondCard;
            //if we pick the last element set size-2 card to last
            if (randomElement == size - 2){
                this.lastCard = currentCard;
            }
        }
        //lock the deck to avoid shuffle
        lock = true;
    }

    public Card removeFirstElement() throws IllegalStateException{
        if (size == 0){throw new IllegalStateException("Cannot " +
                "remove a Card from an empty deck");}
        //removing the very first card
        if(firstCard != null){
            Card first = (Card) firstCard;
            firstCard =  (Card) firstCard.getNext();
            size--;
            //for integrity
            if (size == 1) updateLastElement();
            //if card removed return with it
            return first;
            //return a BIG null
        }else return null;
    }

    //remove a specific card from the deck
    public void removeFirstElement(Card aCard) throws CardNotFoundException{
        Card currentCard = (Card) firstCard;
        boolean found = false;
        //remove the first element
        if (currentCard.getCardValue() == aCard.getCardValue() &&
                currentCard.getStrSuit() == aCard.getStrSuit()) {
            firstCard = (Card) currentCard.getNext();
            size--;
            found = true;
        }
        while(!found && currentCard.getNext() != null){
            Card nextCard = (Card) currentCard.getNext();

            //if both suit and value are the same //we found the card
            if (nextCard.getCardValue() == aCard.getCardValue() &&
                    nextCard.getStrSuit() == aCard.getStrSuit()) {
                found = true;
                size--;
                //skipp the next card
                currentCard.setNext(nextCard.getNext());
            }else{
                //if no match, get the next card
                currentCard = (Card) currentCard.getNext();
            }
        }//endwhile
        setlastcard();
        if (!found)
            throw new CardNotFoundException(aCard);
    }

    //update the last card safety
    public void setlastcard(){
        Card card = firstCard;
        while(card.getNext() != null) {
            card = (Card) card.getNext();
        }
        lastCard = card;
    }

    //add a new card to the deck
    public boolean addNewEntry(Card newEntry) throws LockedDeckException{
        //when the deck is locked (after shuffle), disable the method
        if (lock){
            throw new LockedDeckException("Deck is Locked, not modifiable");
        }
        Card newCard = newEntry;
        newCard.setNext(firstCard);
        firstCard = newCard;
        size++;
        //if we have only one node, we record it as a last node
        if (getSize() == 1)
            lastCard = newCard;
        return true;
    }

    //array representation of the deck
    public Card[] toArray() throws EmptyDeckException{
        Card[] cardArray =  new Card[size];
        Card[] emptyArray = new Card[0];

        if (isEmpty() == true)
            throw new EmptyDeckException("Deck is empty");

        int counter = 0;
        Card currentCard = firstCard;
        while (currentCard.getNext() != null) {
            cardArray[counter] = currentCard;
            currentCard = (Card) currentCard.getNext();
            counter++;
        }
        return cardArray;
    }

    //return the deck size
    public int getSize(){                                   //get the size of the deck
        return size;
    }

    //string representation of the array
    public String toString() throws NullPointerException{
        Card currentCard = firstCard;
        String result = "[";
        //fill the array
        while (currentCard.getNext() != null) {
            result += currentCard.toString()+" ";
            currentCard = (Card) currentCard.getNext();
        }
        if (getLastCard() != null){
            result += currentCard.toString();
        }else{
            throw new NullPointerException("The Deck is Empty");
        }
        //result += "["+currentCard.toString()+"]";
        result += "]";
        return result;
    }

    //grab the first card
    public Card getFirstCard() throws NullPointerException {
        if (firstCard== null)
            throw new NullPointerException("The Deck is Empty");
        return firstCard;
    }

    //grab the last card
    public Card getLastCard() throws NullPointerException  {
        if (firstCard== null)
            throw new NullPointerException("The Deck is Empty");
        return lastCard;
    }

    public boolean isLocked() {
        return lock;
    }
}