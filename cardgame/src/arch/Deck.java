package arch;
import java.util.Random;
public class Deck<T> extends LinkedList{
    private replayItem next, firstCard, lastCard;
    private final int SHUFFLESIZE = 1000;
    private boolean lock = false;
    private static final String[] SUITS = {"h","d","s","c"};

    public Deck() throws LockedDeckException, NullPointerException{     //build a standard deck
        for (int r = 1; r <= 13 ; r++) {
            for (int s = 0; s <= 3 ; s++) {
                addNewEntry(new replayItem(r, SUITS[s]));
            }
        }
    }
    public Deck(boolean shuffle, boolean fill)throws LockedDeckException, NullPointerException, EmptyDeckException{
        if (fill) {
            for (int r = 1; r <= 13; r++) {
                for (int s = 0; s <= 3; s++) {
                    addNewEntry(new replayItem(r, SUITS[s]));
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
            replayItem currentCard = firstCard;
            while (j < randomElement && currentCard.getNext() != null) {    //iterate to the card
                currentCard = (replayItem) currentCard.getNext();
                j++;
            }
            replayItem first = (replayItem) currentCard;                                //swap the card
            replayItem SecondCard = (replayItem) currentCard.getNext();                 //insert it to the front
            replayItem thirdCard = (replayItem) currentCard.getNext().getNext();

            first.setNext(thirdCard);
            SecondCard.setNext(this.firstCard);
            this.firstCard = SecondCard;
            if (randomElement == size - 2){                       //if we pick the last element set size-2 card to last
                this.lastCard = currentCard;
            }
        }
        this.lock = true;                                         //lock the deck to avoid shuffle
    }
    public replayItem removeFirstCard() throws IllegalStateException{
        if (size == 0){throw new IllegalStateException("Cannot remove a Card from an empty deck");}
        if(firstCard != null){                                      //removing the very first card
            replayItem first = (replayItem) firstCard;                          //tmp the first card
            firstCard =  (replayItem) firstCard.getNext();                //update the new first card
            size--;
            if (size == 1) setLastNode();                           //for integrity
            return first;                                           //if card removed return with it
        }else return null;                                          //return a BIG null
    }
    public void removeFirstCard(replayItem aCard) throws CardNotFoundException{    //remove a specific card from the deck
        replayItem currentCard = (replayItem) firstCard;
        boolean found = false;
        if (currentCard.getCardValue() == aCard.getCardValue() &&   //remove the first element
                currentCard.getStrSuit() == aCard.getStrSuit()) {   //of the list
            firstCard = (replayItem) currentCard.getNext();
            size--;
            found = true;
        }
        while(!found && currentCard.getNext() != null){             //iterate throughout the list
            replayItem nextCard = (replayItem) currentCard.getNext();

            if (nextCard.getCardValue() == aCard.getCardValue() &&  //if both suit and value ar the same
                    nextCard.getStrSuit() == aCard.getStrSuit()) {  //we found the card
                found = true;
                size--;
                currentCard.setNext(nextCard.getNext());            //skipp the next card
            }else{
                currentCard = (replayItem) currentCard.getNext();         //if no match, get the next card
            }
        }//endwhile
        setlastcard();
        if (!found)
            throw new CardNotFoundException(aCard);
    }
    public void setlastcard(){
        replayItem card = firstCard;
        while(card.getNext() != null) {
            card = (replayItem) card.getNext();
        }
        lastCard = card;
    }
    public boolean addNewEntry(replayItem newEntry) throws LockedDeckException{//add a new card to the deck
        if (lock){                                 //when the deck is locked (after shuffle), disable the method
            throw new LockedDeckException("Deck is Locked, not modifiable");
        }
        replayItem newCard = newEntry;
        newCard.setNext(firstCard);
        firstCard = newCard;
        size++;
        if (getSize() == 1)                                 //if we have only one node, we record it as a last node
            lastCard = newCard;
        return true;
    }
    public replayItem[] toArray() throws EmptyDeckException{      //array representation of the deck
        replayItem[] cardArray =  new replayItem[size];                 //full size array
        replayItem[] emptyArray = new replayItem[0];                    //empty array for return

        if (isEmpty() == true)
            throw new EmptyDeckException("Deck is empty");

        int counter = 0;                                    //build the result array
        replayItem currentCard = firstCard;
        while (currentCard.getNext() != null) {
            cardArray[counter] = currentCard;
            currentCard = (replayItem) currentCard.getNext();
            counter++;
        }
        //cardArray[size-1] = (Card) getLastCard();           //grab the last element
        return cardArray;
    }
    public int getSize(){                                   //get the size of the deck
        return size;
    }
    public String toString() throws NullPointerException{     //string representation of the array [Q♠][Q♦][Q♥]
        replayItem currentCard = firstCard;
        String result = "[";
        while (currentCard.getNext() != null) {             //fill the array
            result += currentCard.toString()+" ";
            currentCard = (replayItem) currentCard.getNext();
        }
        if (getLastCard() != null){
            result += currentCard.toString();       //grab the last card
        }else{
            throw new NullPointerException("The Deck is Empty");
        }
        //result += "["+currentCard.toString()+"]";
        result += "]";
        return result;
    }
    public replayItem getFirstCard() throws NullPointerException {   //grab the first card
        if (firstCard== null)
            throw new NullPointerException("The Deck is Empty");
        return firstCard;
    }
    public replayItem getLastCard() throws NullPointerException  {   //grab the last card
        if (firstCard== null)
            throw new NullPointerException("The Deck is Empty");
        return lastCard;
    }
}