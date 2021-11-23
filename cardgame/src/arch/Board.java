package arch;
import java.util.Random;
public class Board<T> extends LinkedList{
    private Card next, firstCard, lastCard;

    int cJ = 0, cQ = 0, cK = 0;
    private final int BOARDSIZE = 9;

    public Board(boolean b) throws LockedDeckException, NullPointerException{
        firstCard = null;
        lastCard = null;
    }    //just create an empty deck

    public Board() {
        firstCard = null;
        lastCard = null;
    }

    public Card removeCard() throws IllegalStateException{
        if (size == 0){throw new IllegalStateException("Cannot remove a Card from an empty deck");}
        if(firstCard != null){                                      //removing the very first card
            Card first = (Card) firstCard;                          //tmp the first card
            firstCard =  (Card) firstCard.getNext();                //update the new first card
            size--;
            if (size == 1) setLastNode();                           //for integrity
            return first;                                           //if card removed return with it
        }else return null;                                          //return a BIG null
    }

    public Card removeACard(Card aCard) throws CardNotFoundException{    //remove a specific card from the deck
        Card currentCard = (Card) firstCard;
        Card tmpCard = currentCard;
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
                tmpCard = nextCard;
                break;
            }else{
                currentCard = (Card) currentCard.getNext();         //if no match, get the next card
            }
        }//endwhile
        if (!found)
            throw new CardNotFoundException(aCard);
        return tmpCard;
    }

    public Card removeNthCard(int num) throws NullPointerException{    //remove a specific card from the deck
        if (num < 1 || num > size)
            throw new NullPointerException("The selection is out of range");
        Card currentCard = (Card) firstCard;
        Card tmpCard = currentCard;
        if ( num == 1) {                                             //remove the very first card
            firstCard = (Card) currentCard.getNext();
            size--;
            return tmpCard;
        }
        int counter = 0;
        while(counter <= num && currentCard.getNext() != null){             //iterate throughout the list
            Card nextCard = (Card) currentCard.getNext();

            if (num-2 == counter) {                                   //we found the card
                size--;
                currentCard.setNext(nextCard.getNext());
                tmpCard = nextCard;                                 //skipp the next card
                break;
            }else{
                currentCard = (Card) currentCard.getNext();         //if no match, get the next card
            }
            counter++;
        }//endwhile
    return tmpCard;
    }

    public int getNthCardValue(int num) throws NullPointerException{    //remove a specific card from the deck
        if (num < 1 || num > size)
            throw new NullPointerException("The selection is out of range");
        Card currentCard = (Card) firstCard;

        if ( num == 1) {                                             //remove the very first card
            return firstCard.getCardValue();

        }
        int counter = 0;
        while(counter <= num && currentCard.getNext() != null){             //iterate throughout the list
            Card nextCard = (Card) currentCard.getNext();

            if (num-2 == counter) {                                   //we found the card
                return nextCard.getCardValue();
            }else{
                currentCard = (Card) currentCard.getNext();         //if no match, get the next card
            }
            counter++;
        }//endwhile
        return -1;
    }

    public void clear() {
        firstCard.setNext(null);
        lastCard.setNext(null);
        size = 0;
    }

    public boolean addNewEntry(Card newEntry){//add a new card to the deck
        Card newCard = newEntry;
        newCard.setNext(firstCard);
        firstCard = newCard;
        size++;

        if (getSize() == 1)                                 //if we have only one node, we record it as a last node
            lastCard = newCard;
            lastCard.setNext(null);

            switch(newEntry.getCardValue()){
                case 11:
                    cJ++;
                    break;
                case 12:
                    cQ++;
                    break;
                case 13:
                    cK++;
                    break;
            }

            sort();
        return true;
    }

    public void sort(){
        if (size > 2) {
            for (int i = 0; i < size-1; i++ ) {
                Card currentCard = firstCard;
                Card nextCard = (Card) firstCard.getNext();
                Card prevCard = null;
                for (int j = 1; j < size; j++) {
                    if (j == 1){
                        prevCard = firstCard;
                    }
                    if (currentCard.getCardValue() > nextCard.getCardValue()) {
                        String tmpSuit = currentCard.getStrSuit();
                        int tmpValue =   currentCard.getCardValue();

                        currentCard.setCardValue(nextCard.getCardValue());
                        currentCard.setCardSuit(nextCard.getStrSuit());
                        nextCard.setCardValue(tmpValue);
                        nextCard.setCardSuit(tmpSuit);

                        //currentCard.setNext(nextCard.getNext());
                    }

                    if(j >= 1){
                        prevCard = currentCard;
                    }
                    currentCard = nextCard;
                    nextCard = (Card) nextCard.getNext();

                }
            }
        }
    }

    public Card[] toArray() throws NullPointerException{      //array representation of the deck
        Card[] cardArray =  new Card[size];                 //full size array
        Card[] emptyArray = new Card[0];                    //empty array for return

        if (isEmpty() == true)
            throw new NullPointerException("Board is empty");

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
        String result = "[";
        while (currentCard.getNext() != null) {             //fill the array
            result += currentCard.toString()+" ";
            currentCard = (Card) currentCard.getNext();
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
    public Card getFirstCard() throws NullPointerException {   //grab the first card
        if (firstCard== null)
            throw new NullPointerException("None shall pass.");
        return firstCard;
    }

    public Card getLastCard() throws NullPointerException  {   //grab the last card
        if (firstCard== null)
            throw new NullPointerException("The Deck is Empty");
        return lastCard;
    }
    public int getcJ() {
        return cJ;
    }

    public int getcQ() {
        return cQ;
    }

    public int getcK() {
        return cK;
    }

    public boolean isJQK(){
        return cJ != 0 && cQ !=0 && cK != 0;
    }

    public boolean checkAnswer(int pa, int pb) throws NullPointerException{
        if (pa == 0 || pb == 0 || pa == pb) {
            throw new NullPointerException("This selection is not allowed-> " + pa + "," + pb);
        }
        return checkAnswer(pa,pb,0);
    }

    public boolean checkAnswer(int pa, int pb, int pc) throws NullPointerException{
        if (pa == 0 || pb == 0 || pa == pc) {
            throw new NullPointerException("This selection is not allowed -> " + pa + "," + pb+","+pc);
        }
        int a,b,c;
        a =  getNthCardValue(pa);
        b =  getNthCardValue(pb);
        if (pc != 0)
            c =  getNthCardValue(pc);
        else
            c = 0;
        System.out.println("The values are: " + (a + b + c) + "\nSelected cards values were: ["+a+","+b+","+c+"]");
        return  ((a + b + c) == 36) || ((a + b) == 11);
    }

    public int[] getJQKPos() throws NullPointerException{
        int[] posish = {0,0,0};
        if (cJ == 0 || cK == 0 || cQ == 0){
            return posish;
        }


        for (int i = 1; i < size+1; i++) {
            int value = getNthCardValue(i);
           switch(value){
               case 11:
                   posish[0] = i;
                   break;
               case 12:
                   posish[1] = i;
                   break;
               case 13:
                   posish[2] = i;
                   break;
           }
        }
        return posish;
    }
}