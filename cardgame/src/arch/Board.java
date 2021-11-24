package arch;
public class Board<Node> extends LinkedList<Card> {
    private Card firstCard, lastCard;
    private Stack validMove = new Stack();
    int cJ = 0, cQ = 0, cK = 0;
    public final int BOARDSIZE = 9;

    public Board(boolean b) throws LockedDeckException, NullPointerException { //just create an empty deck
        firstCard = null;
        lastCard = null;
    }
    public Board() {
        firstCard = null;
        lastCard = null;
    }
    public boolean addNewEntry(Card newEntry) {                 //add a new card to the board
        Card newCard = newEntry;
        newCard.setNext(firstCard);
        firstCard = newCard;
        size++;

        if (getSize() == 1) {                                   //if we have only one node, we record it as a lastCard
            lastCard = newCard;
            lastCard.setNext(null);
        }
        switch (newEntry.getCardValue()) {                      //update KJQ counter
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
    public void sort() {                                         //bubble sort the board
        if (size > 2) {
            for (int i = 0; i < size - 1; i++) {
                Card currentCard = firstCard;
                Card nextCard = (Card) firstCard.getNext();
                Card prevCard = null;
                for (int j = 1; j < size; j++) {
                    if (j == 1) {
                        prevCard = firstCard;
                    }
                    if (currentCard.getCardValue() > nextCard.getCardValue()) { //if the current card value is greater
                        String tmpSuit = currentCard.getStrSuit();
                        int tmpValue = currentCard.getCardValue();
                        currentCard.setCardValue(nextCard.getCardValue());      //swap the cards
                        currentCard.setCardSuit(nextCard.getStrSuit());
                        nextCard.setCardValue(tmpValue);
                        nextCard.setCardSuit(tmpSuit);
                        //currentCard.setNext(nextCard.getNext());
                    }

                    prevCard = currentCard;                                    //slipping the prevCard
                    currentCard = nextCard;                                    //slipping the current and nexCards
                    nextCard = (Card) nextCard.getNext();

                }
            }
        }
    }
    public int getCardPosition(Card c) {
        int i = 1;
        Card currentCard = firstCard;
        while (currentCard.getNext() != null) {

            if (currentCard.getCardValue() == c.getCardValue() &&
                    currentCard.getStrSuit() == c.getStrSuit()) {
                return i;
            }

            if (currentCard.getCardValue() == c.getCardValue() &&
                    "*" == c.getStrSuit()) {
                return i;
            }

            currentCard = (Card) currentCard.getNext();
            i++;
        }
        if (lastCard.getCardValue() == c.getCardValue() &&
                lastCard.getStrSuit() == c.getStrSuit()) {
            return i++;
        }
        return -1;
    }
    public Card[] toArray() throws NullPointerException {      //array representation of the board
        Card[] cardArray = new Card[size];                   //full size array
        Card[] emptyArray = new Card[0];                      //empty array for return

        if (isEmpty() == true)
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
    public String toString() throws NullPointerException {     //string representation of the array [Q♠][Q♦][Q♥]
        Card currentCard = firstCard;
        String result = "[";
        while (currentCard.getNext() != null) {               //fill the array
            result += currentCard + "  ";
            currentCard = (Card) currentCard.getNext();
        }
        if (getLastCard() != null) {
            result += currentCard.toString();                 //grab the last card
        } else {
            throw new NullPointerException("The Deck is Empty");
        }
        //result += "["+currentCard.toString()+"]";
        result += "]";
        return result;
    }
    public String representBoard() throws NullPointerException {     //string representation of the array [Q♠][Q♦][Q♥]
        String s = toString();
        s += "\n";
        String choice = "";
        for (int i = 1; i < getSize() + 1; i++) {
            if (i == 1) {
                choice += " " + i;
            } else {
                choice += "   " + i;
            }
        }
        return s + choice;
    }
    public Card getFirstCard() throws NullPointerException {   //grab the first card
        if (firstCard == null)
            throw new NullPointerException("None shall pass.");
        return firstCard;
    }
    public Card getLastCard() throws NullPointerException {   //grab the last card
        if (firstCard == null)
            throw new NullPointerException("The Deck is Empty");
        return lastCard;
    }
    public boolean checkAnswer(int pa, int pb){         //check answer of 00 format
        if (pa ==pb){
            return false;
        }
        return checkAnswer(pa, pb,0);
    }
    public boolean checkAnswer(int pa, int pb, int pc){ //overload for 000 format
        if (pa == pb || pa == pc || pb == pc){
            return false;
        }
        int a, b, c;
        a = getNthCardValue(pa);
        b = getNthCardValue(pb);
        if (pc == 0){
            c = 0;
        }else{
            c = getNthCardValue(pc);
        }


        //System.out.println("Selected cards values were: [" + a + "," + b + "," + c + "]");
        return ((a + b + c) == 36) || ((a + b) == 11);
    }
    public void getJQKPos() throws NullPointerException {               //get the raw posish of the KJQ cards
        int[] posish = {0, 0, 0};

        for (int i = 1; i < size + 1; i++) {                              //iterate the board
            int value = getNthCardValue(i);
                                             //get the card point value on ith posish
            switch (value) {
                case 11 ->                                                 //J found
                        posish[0] = i;
                case 12 ->                                                 //Q found
                        posish[1] = i;
                case 13 ->                                                 //K found
                        posish[2] = i;
            }
        }
        if (posish[0] > 0 && posish[1] > 0 && posish[2] > 0){
            validMove.push(posish);
        }
                                                       //return the raw posish's e.g. [7,8,9]
    }
    public boolean isJQK() {return cJ != 0 && cQ != 0 && cK != 0;}        //is KJQ exists on the board
    public int getcJ() {return cJ;}                                     //get the cJ counter
    public int getcQ() {return cQ;}                                     //get the cQ counter
    public int getcK() {return cK;}                                     //get the cK counter
    public void clear() {
        firstCard.setNext(null);
        lastCard.setNext(null);
        size = 0;
    }      //clear a board (for tests)
    public int getSize() {return size;}                                  //get the size of the board
    public void searchValidMoves() {
        validMove.clear();
            if (checkAnswer(1,2) == true) {
                int[] a = {1,2};
                validMove.push(a);
            }


        Card outerCard = firstCard;
        int outer = 1;
        while (outerCard.getNext() != null) {
            Card innerCard = (Card) firstCard.getNext();
            Card offsetCard = innerCard;
            int inner = getSize() - outer + 1;
            while (innerCard.getNext() != null) {

                innerCard = (Card) innerCard.getNext();
                //System.out.println("i: " +innerCard+" | o: " +outerCard + "offset: " + offsetCard);
                if ((outerCard.getCardValue() + innerCard.getCardValue() == 11)) {
                    int inner2 = size - outer;

                    int[] valid = {0,0};
                    valid[0] = getCardPosition(innerCard);
                    valid[1] = getCardPosition(outerCard);
                    validMove.push(valid);
                }

                inner++;
            }
            outerCard = (Card) outerCard.getNext();
            offsetCard = (Card) offsetCard.getNext();

            outer++;
        }

//
//        int iValue = 0;
//        int jValue = 0;
//        for (int i = 1; i <= size-1; i++) {
//            iValue = getNthCardValue(i);
//
//            for (int j = i; j <= size; j++) {
//                jValue = getNthCardValue(j);
//                //System.out.println(iValue + " & "+ jValue);
//                if(iValue + jValue == 11){
//                    int[] valid = {-1,-1};
//                    valid[0] = iValue;
//                    valid[1] = jValue;
//                    System.out.print(" this is 11\n");
//                    validMove.push(valid);
//                    break;
//                }
//            }
//        }
        getJQKPos();
    }
    public Stack getValidMove() {
        searchValidMoves();
        return validMove;
    }
    public Card removeCard() throws IllegalStateException {
        if (size == 0) {throw new IllegalStateException("Cannot remove a Card from an empty deck");}
        if (firstCard != null) {                                      //removing the very first card
            Card first = (Card) firstCard;                          //tmp the first card
            firstCard = (Card) firstCard.getNext();                //update the new first card
            size--;
            setlastcard();                                          //for integrity
            return first;                                           //if card removed return with it
        } else return null;                                          //return a BIG null
    }
    public Card removeACard(Card aCard) throws CardNotFoundException {    //remove a specific card from the deck
        Card c = (Card) firstCard;
        Card tmpCard = c;
        int i = 1;
        boolean found = false;
        if (c.getCardValue() == aCard.getCardValue() &&   //remove the first element
                c.getStrSuit() == aCard.getStrSuit()) {   //of the list
            firstCard = (Card) c.getNext();
            size--;
            found = true;
        }
        while (!found && c.getNext() != null) {             //iterate throughout the list
            Card n = (Card) c.getNext();

            if (n.getCardValue() == aCard.getCardValue() &&  //if both suit and value ar the same
                    n.getStrSuit() == aCard.getStrSuit()) {  //we found the card
                found = true;
                size--;
                c.setNext(n.getNext());            //skipp the next card
                tmpCard = n;
            } else {
                c = (Card) c.getNext();         //if no match, get the next card
            }
            i++;
        }//endwhile
        setlastcard();
        if (!found)
            throw new CardNotFoundException(aCard);
        return tmpCard;
    }
    public void setlastcard() {
        Card card = firstCard;
        while (card.getNext() != null) {
            card = (Card) card.getNext();
        }
        lastCard = card;
    }
    public Card removeNthCard(int num) throws NullPointerException {    //remove a specific card number from the board
        //if (num < 1 || num > size)
        //throw new NullPointerException("The selection is out of range");
        Card currentCard = (Card) firstCard;
        Card tmpCard = currentCard;
        if (num == 1) {                                             //retrieve the very first card
            firstCard = (Card) currentCard.getNext();
            size--;
            return tmpCard;
        }
        int counter = 0;
        while (counter <= num && currentCard.getNext() != null) {             //iterate throughout the board
            Card nextCard = (Card) currentCard.getNext();

            if (num - 2 == counter) {                                   //we found the card
                size--;
                currentCard.setNext(nextCard.getNext());              //skipp the current card
                tmpCard = nextCard;

            } else {
                currentCard = (Card) currentCard.getNext();         //if no match, get the next card
            }
            counter++;
        }//endwhile
        setlastcard();
        return tmpCard;
    }
    public int getNthCardValue(int num) throws NullPointerException {    //get the Nth card point value
        if (num < 1 || num > size)
            throw new NullPointerException("The selection is out of range");
        Card currentCard = (Card) firstCard;
        if (num == 1) return firstCard.getCardValue();               //get the very first card
        int counter = 0;
        while (counter <= num && currentCard.getNext() != null) {         //iterate throughout the list
            Card nextCard = (Card) currentCard.getNext();

            if (num - 2 == counter) {                                     //we found the card, return its points
                return nextCard.getCardValue();
            } else {
                currentCard = (Card) currentCard.getNext();             //if no match, continue
            }
            counter++;
        }//endwhile
        return -1;
    }
}