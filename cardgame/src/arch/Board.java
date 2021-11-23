package arch;
import java.util.Objects;
public class Board extends LinkedList<Card>{
    private Card firstCard, lastCard;
    int cJ = 0, cQ = 0, cK = 0;

    public Board() { firstCard = null; lastCard = null;}                      //empty deck

    public Card removeCard() throws IllegalStateException{
        if (size == 0){throw new IllegalStateException("Cannot remove a Card from an empty deck");}
        if(firstCard != null){                                      //removing the very first card
            Card first = firstCard;                                 //tmp the first card
            firstCard =  (Card) firstCard.getNext();                //update the new first card
            size--;
            if (size == 1) setLastNode();                           //for integrity
            return first;                                           //if card removed return with it
        }else return null;                                          //return a BIG null
    }

    public Card removeACard(Card pc) throws CardNotFoundException{    //remove a specific card from the deck
        Card c = firstCard;
        Card tmpCard = c;
        boolean f = false;
        if (c.getCardValue() == pc.getCardValue() &&   //remove the first element
                Objects.equals(c.getStrSuit(), pc.getStrSuit())) {   //of the list
            firstCard = (Card) c.getNext();
            size--;
            f = true;
        }
        while(!f && c.getNext() != null){             //iterate throughout the list
            Card n = (Card) c.getNext();

            if (n.getCardValue() == pc.getCardValue() &&  //if both suit and value ar the same
                    n.getStrSuit().equals(pc.getStrSuit())) {  //we found the card
                size--;
                c.setNext(n.getNext());            //skipp the next card
                return n;
            }else{
                c = (Card) c.getNext();         //if no match, get the next card
            }
        }//endwhile
        if (!f)
            throw new CardNotFoundException(pc);
        return tmpCard;
    }

    public Card removeNthCard(int num) throws NullPointerException{    //remove a specific card number from the board
        if(num < 1 || num > size)
            throw new NullPointerException("The selection is out of range");
        Card currentCard = firstCard;
        Card tmpCard = currentCard;
        if(num == 1){                                             //retrieve the very first card
            firstCard = (Card) currentCard.getNext();
            size--;
            return tmpCard;
        }
        int counter = 0;
        while(counter <= num && currentCard.getNext() != null){             //iterate throughout the board
            Card nextCard = (Card) currentCard.getNext();

            if (num-2 == counter) {                                   //we found the card
                size--;
                currentCard.setNext(nextCard.getNext());              //skipp the current card
                tmpCard = nextCard;
                return nextCard;
            }else{
                currentCard = (Card) currentCard.getNext();         //if no match, get the next card
            }
            counter++;
        }//endwhile
        return tmpCard;
    }

    public int getNthCardValue(int num) throws NullPointerException{    //get the Nth card point value
        if (num < 1 || num > size)
            throw new NullPointerException("The selection is out of range");
        Card currentCard = firstCard;

        if ( num == 1) return firstCard.getCardValue();               //get the very first card
        int counter = 0;
        while(counter <= num && currentCard.getNext() != null){         //iterate throughout the list
            Card nextCard = (Card) currentCard.getNext();

            if (num-2 == counter) {                                     //we found the card, return its points
                return nextCard.getCardValue();
            }else{
                currentCard = (Card) currentCard.getNext();             //if no match, continue
            }
            counter++;
        }//endwhile
        return -1;
    }

    public void addNewEntry(Card newEntry){                          //add a new card to the board
        newEntry.setNext(firstCard);
        firstCard = newEntry;
        size++;

        if(getSize() == 1){                                   //if we have only one node, we record it as a lastCard
            lastCard = newEntry;
            lastCard.setNext(null);
        }
        switch (newEntry.getCardValue()) {                    //update KJQ counter
            case 11 -> cJ++;
            case 12 -> cQ++;
            case 13 -> cK++;
        }
            sort();
    }

    public void sort(){                                         //bubble sort the board
        if (size > 2) {
            for (int i = 0; i < size-1; i++ ) {
                Card currentCard = firstCard;
                Card nextCard = (Card) firstCard.getNext();
                for (int j = 1; j < size; j++) {
                    if (currentCard.getCardValue() > nextCard.getCardValue()) { //if the current card value is greater
                        String tmpSuit = currentCard.getStrSuit();
                        int tmpValue =   currentCard.getCardValue();
                        currentCard.setCardValue(nextCard.getCardValue());      //swap the cards
                        currentCard.setCardSuit(nextCard.getStrSuit());
                        nextCard.setCardValue(tmpValue);
                        nextCard.setCardSuit(tmpSuit);
                        //currentCard.setNext(nextCard.getNext());
                    }
                    currentCard = nextCard;                                    //slipping the current and nexCards
                    nextCard = (Card) nextCard.getNext();

                }
            }
        }
    }

    public Card[] toArray() throws NullPointerException{      //array representation of the board
        Card[] cardArray =  new Card[size];                   //full size array
        if (isEmpty())
            throw new NullPointerException("Board is empty");

        int counter = 0;                                      //build the result array
        Card currentCard = firstCard;
        while (currentCard.getNext() != null) {
            cardArray[counter] = currentCard;
            currentCard = (Card) currentCard.getNext();
            counter++;
        }
        //cardArray[size-1] = (Card) getLastCard();           //grab the last element
        return cardArray;
    }


    public String toString() throws NullPointerException{     //string representation of the array [Q♠][Q♦][Q♥]
        Card currentCard = firstCard;
        StringBuilder result = new StringBuilder("[");
        while (currentCard.getNext() != null) {               //fill the array
            result.append(currentCard).append(" ");
            currentCard = (Card) currentCard.getNext();
        }
        if (getLastCard() != null){
            result.append(currentCard);                 //grab the last card
        }else{
            throw new NullPointerException("The Deck is Empty");
        }
        //result += "["+currentCard.toString()+"]";
        result.append("]");
        return result.toString();
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

    public boolean checkAnswer(int pa, int pb) throws NullPointerException{         //check answer of 00 format
        if (pa == 0 || pb == 0 || pa == pb) {
            throw new NullPointerException("This selection is not allowed-> " + pa + "," + pb);
        }
        return checkAnswer(pa,pb,0);
    }

    public boolean checkAnswer(int pa, int pb, int pc) throws NullPointerException{ //overload for 000 format
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

    public int[] getJQKPos() throws NullPointerException{               //get the raw posish of the KJQ cards
        int[] posish = {0,0,0};
        if (cJ == 0 || cK == 0 || cQ == 0){                             //any of them 0 just return [0,0,0]
            return posish;
        }

        for (int i = 1; i < size+1; i++) {                              //iterate the board
            int value = getNthCardValue(i);                             //get the card point value on ith posish
            switch (value) {
                case 11 ->                                                 //J found
                        posish[0] = i;
                case 12 ->                                                 //Q found
                        posish[1] = i;
                case 13 ->                                                 //K found
                        posish[2] = i;
            }
        }
        return posish;                                                  //return the raw posish's e.g. [7,8,9]
    }

    //validate a board possible combinations
    public boolean isJQK(){return cJ != 0 && cQ !=0 && cK != 0;}        //is KJQ exists on the board
    public int getcJ() {return cJ;}                                     //get the cJ counter
    public int getcQ() {return cQ;}                                     //get the cQ counter
    public int getcK() {return cK;}                                     //get the cK counter
    public void clear() {firstCard.setNext(null);lastCard.setNext(null);size = 0;}      //clear a board (for tests)
    public int getSize(){return size;}                                  //get the size of the board
}