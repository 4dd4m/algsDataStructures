package arch;
import java.util.Random;
public class Deck<T> extends LinkedList implements CardInterFace {
    private Card next, firstCard, lastCard;
    private final int SHUFFLESIZE = 1000;
    private boolean lock = false;
    private static final String[] SUITS = {"h","d","s","c"};

    public Deck() throws LockedDeckException, NullPointerException{     //build a standard deck
        for (int r = 1; r <= 13 ; r++) {
            for (int s = 0; s <= 3 ; s++) {
                addNewEntry(new Card(r, SUITS[s]));
            }
        }
    }
    public Deck(boolean shuffle, boolean fill)throws LockedDeckException, NullPointerException, EmptyDeckException{
        if (fill) {                                             //optional shuffle and fill
            for (int r = 1; r <= 13; r++) {
                for (int s = 0; s <= 3; s++) {
                    addNewEntry(new Card(r, SUITS[s]));
                }
            }
        }
        if (shuffle){
            shuffle();
            lock =true;
        }

    }
    public void shuffle() throws LockedDeckException, EmptyDeckException {
        if (lock == true)                                         //deck is locked after shuffle, disabling more shuffle
            throw new LockedDeckException("Deck is locked");
        if (size == 0)                                            //empty deck cannot be shuffled
            throw new EmptyDeckException("The Deck is Empty");
        Random random = new Random();                             //random generator

        for (int i = 0; i < size*SHUFFLESIZE; i++) {              //shuffle N times
            int randomElement = random.nextInt(size - 1);
            int j = 0;
            Card currentCard = firstCard;
            while (j < randomElement && currentCard.getNext() != null) {    //iterate to the card
                currentCard = (Card) currentCard.getNext();
                j++;
            }
            Card first = (Card) currentCard;                                //swap the card
            Card SecondCard = (Card) currentCard.getNext();                 //insert it to the front
            Card thirdCard = (Card) currentCard.getNext().getNext();

            first.setNext(thirdCard);
            SecondCard.setNext(this.firstCard);
            this.firstCard = SecondCard;
            if (randomElement == size - 2){                       //if we pick the last element set size-2 card to last
                this.lastCard = currentCard;
            }
        }
        this.lock = true;                                         //lock the deck to avoid shuffle
    }
    public Card removeFirstElement() throws IllegalStateException{
        if (size == 0){throw new IllegalStateException("Cannot remove a Card from an empty deck");}
        if(firstCard != null){                                      //removing the very first card
            Card first = (Card) firstCard;                          //tmp the first card
            firstCard =  (Card) firstCard.getNext();                //update the new first card
            size--;
            if (size == 1) updateLastElement();                     //for integrity
            return first;                                           //if card removed return with it
        }else return null;                                          //return a BIG null
    }
    public void removeFirstElement(Card aCard) throws CardNotFoundException{    //remove a specific card from the deck
        Card currentCard = (Card) firstCard;
        boolean found = false;
        if (currentCard.getCardValue() == aCard.getCardValue() &&   //remove the first element
                currentCard.getStrSuit() == aCard.getStrSuit()) {   //of the list
            firstCard = (Card) currentCard.getNext();
            size--;
            found = true;
        }
        while(!found && currentCard.getNext() != null){             //iterate throughout the list
            Card nextCard = (Card) currentCard.getNext();

            if (nextCard.getCardValue() == aCard.getCardValue() &&  //if both suit and value ar the same
                    nextCard.getStrSuit() == aCard.getStrSuit()) {  //we found the card
                found = true;
                size--;
                currentCard.setNext(nextCard.getNext());            //skipp the next card
            }else{
                currentCard = (Card) currentCard.getNext();         //if no match, get the next card
            }
        }//endwhile
        setlastcard();
        if (!found)
            throw new CardNotFoundException(aCard);
    }
    public void setlastcard(){                                      //update the last card safety
        Card card = firstCard;
        while(card.getNext() != null) {
            card = (Card) card.getNext();
        }
        lastCard = card;
    }
    public boolean addNewEntry(Card newEntry) throws LockedDeckException{//add a new card to the deck
        if (lock){                                 //when the deck is locked (after shuffle), disable the method
            throw new LockedDeckException("Deck is Locked, not modifiable");
        }
        Card newCard = newEntry;
        newCard.setNext(firstCard);
        firstCard = newCard;
        size++;
        if (getSize() == 1)                                 //if we have only one node, we record it as a last node
            lastCard = newCard;
        return true;
    }
    public Card[] toArray() throws EmptyDeckException{      //array representation of the deck
        Card[] cardArray =  new Card[size];                 //full size array
        Card[] emptyArray = new Card[0];                    //empty array for return

        if (isEmpty() == true)
            throw new EmptyDeckException("Deck is empty");

        int counter = 0;                                    //build the result array
        Card currentCard = firstCard;
        while (currentCard.getNext() != null) {
            cardArray[counter] = currentCard;
            currentCard = (Card) currentCard.getNext();
            counter++;
        }
        return cardArray;
    }
    public int getSize(){                                   //get the size of the deck
        return size;
    }                     //return the deck size
    public String toString() throws NullPointerException{     //string representation of the array [Q♠][Q♦][Q♥]
        Card currentCard = firstCard;
        String result = "[";
        while (currentCard.getNext() != null) {               //fill the array
            result += currentCard.toString()+" ";
            currentCard = (Card) currentCard.getNext();
        }
        if (getLastCard() != null){
            result += currentCard.toString();                 //grab the last card
        }else{
            throw new NullPointerException("The Deck is Empty");
        }
        //result += "["+currentCard.toString()+"]";
        result += "]";
        return result;
    }
    public Card getFirstCard() throws NullPointerException {   //grab the first card
        if (firstCard== null)
            throw new NullPointerException("The Deck is Empty");
        return firstCard;
    }
    public Card getLastCard() throws NullPointerException  {   //grab the last card
        if (firstCard== null)
            throw new NullPointerException("The Deck is Empty");
        return lastCard;
    }
}