package arch;
import java.util.Random;
public class Deck extends LinkedList{
    private Card firstCard, lastCard;
    private final int SHUFFLESIZE = 500;
    private boolean lock = false;
    private static final String[] SUITS = {"h","d","s","c"};


    public Deck() throws LockedDeckException, NullPointerException,EmptyDeckException{     //build a standard deck
        for (int r = 1; r <= 13 ; r++) {
            for (int s = 0; s <= 3 ; s++) {
                addNewEntry(new Card(r, SUITS[s]));
            }
        }
        //shuffle();
    }

    public void shuffle() throws LockedDeckException, EmptyDeckException {
        if (lock)                                         //deck is locked after shuffle, disabling more shuffle
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
            Card first = currentCard;                                //swap the card
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


    public Deck(boolean b) throws  NullPointerException{}    //just create an empty deck

    public Card removeFirstCard() throws IllegalStateException{
        if (size == 0){throw new IllegalStateException("Cannot remove a Card from an empty deck");}
        if(firstCard != null){                                      //removing the very first card
            Card first = firstCard;                          //tmp the first card
            firstCard =  (Card) firstCard.getNext();                //update the new first card
            size--;
            if (size == 1) setLastNode();                           //for integrity
            return first;                                           //if card removed return with it
        }else return null;                                          //return a BIG null
    }

    public void removeFirstCard(Card aCard) throws CardNotFoundException{    //remove a specific card from the deck
        Card currentCard = firstCard;
        boolean found = false;
        if (currentCard.getCardValue() == aCard.getCardValue() &&   //remove the first element
                currentCard.getStrSuit().equals(aCard.getStrSuit())) {   //of the list
            firstCard = (Card) currentCard.getNext();
            size--;
            found = true;
        }
        while(!found && currentCard.getNext() != null){             //iterate throughout the list
            Card nextCard = (Card) currentCard.getNext();

            if (nextCard.getCardValue() == aCard.getCardValue() &&  //if both suit and value ar the same
                    nextCard.getStrSuit().equals(aCard.getStrSuit())) {  //we found the card
                found = true;
                size--;
                currentCard.setNext(nextCard.getNext());            //skipp the next card
                break;
            }else{
                currentCard = (Card) currentCard.getNext();         //if no match, get the next card
            }
        }//endwhile
        if (!found)
            throw new CardNotFoundException(aCard);
    }

    public boolean addNewEntry(Card newEntry) throws LockedDeckException{//add a new card to the deck
        if (lock){                                 //when the deck is locked (after shuffle), disable the method
            throw new LockedDeckException("Deck is Locked, not modifiable");
        }
        newEntry.setNext(firstCard);
        firstCard = newEntry;
        size++;
        if (getSize() == 1)                                 //if we have only one node, we record it as a last node
            lastCard = newEntry;
        return true;
    }

    public Card[] toArray() throws EmptyDeckException{      //array representation of the deck
        Card[] cardArray =  new Card[size];                 //full size array

        if (isEmpty())
            throw new EmptyDeckException("Deck is empty");

        int counter = 0;                                    //build the result array
        Card currentCard = firstCard;
        while (currentCard.getNext() != null) {
            cardArray[counter] = currentCard;
            currentCard = (Card) currentCard.getNext();
            counter++;
        }
        //cardArray[size-1] = (Card) getLastCard();           //grab the last element
        return cardArray;
    }

    public int getSize(){                                   //get the size of the deck
        return size;
    }
    public String toString() throws NullPointerException{     //string representation of the array [Q♠][Q♦][Q♥]
        Card currentCard = firstCard;
        StringBuilder result = new StringBuilder("[");
        while (currentCard.getNext() != null) {             //fill the array
            result.append(currentCard).append(" ");
            currentCard = (Card) currentCard.getNext();
        }
        if (getLastCard() != null){
            result.append(currentCard.toString());       //grab the last card
        }else{
            throw new NullPointerException("The Deck is Empty");
        }
        //result += "["+currentCard.toString()+"]";
        result.append("]");
        return result.toString();
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