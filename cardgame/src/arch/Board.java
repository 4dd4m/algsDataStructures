package arch;

public class Board extends LinkedList<Card> implements CardInterFace {
    private Card firstCard, lastCard;
    private Stack validMove = new Stack();
    int cJ = 0, cQ = 0, cK = 0;
    public final int BOARDSIZE = 9;

    //just create an empty deck
    public Board(boolean b) throws LockedDeckException, NullPointerException {
        firstCard = null;
        lastCard = null;
    }

    public Board() {
        firstCard = null;
        lastCard = null;
    }

    //add a new card to the board
    public boolean addNewEntry(Card newEntry) {
        Card newCard = newEntry;
        newCard.setNext(firstCard);
        firstCard = newCard;
        size++;

        //if we have only one node, we record it as a lastCard
        if (getSize() == 1) {
            lastCard = newCard;
            lastCard.setNext(null);
        }
        //update KJQ counter
        switch (newEntry.getCardValue()) {
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

    public void sort() {
        if (size > 2) {
            //bubble sort the board
            for (int i = 0; i < size - 1; i++) {
                Card currentCard = firstCard;
                Card nextCard = (Card) firstCard.getNext();
                Card prevCard = null;
                for (int j = 1; j < size; j++) {
                    if (j == 1) {
                        prevCard = firstCard;
                    }
                    //if the current card value is greater
                    if (currentCard.getCardValue() > nextCard.getCardValue()) {
                        String tmpSuit = currentCard.getStrSuit();
                        int tmpValue = currentCard.getCardValue();
                        //swap the cards
                        currentCard.setCardValue(nextCard.getCardValue());
                        currentCard.setCardSuit(nextCard.getStrSuit());
                        nextCard.setCardValue(tmpValue);
                        nextCard.setCardSuit(tmpSuit);
                        //currentCard.setNext(nextCard.getNext());
                    }

                    //slipping the prevCard
                    //slipping the current and nexCards
                    prevCard = currentCard;
                    currentCard = nextCard;
                    nextCard = (Card) nextCard.getNext();

                }
            }
        }
    }

    //get an exact cards position
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
    //array representation of the board
    public Card[] toArray() throws NullPointerException {
        Card[] cardArray = new Card[size];
        Card[] emptyArray = new Card[0];

        if (isEmpty() == true)
            throw new NullPointerException("Board is empty");

        int counter = 0;
        Card currentCard = firstCard;
        while (currentCard.getNext() != null) {
            cardArray[counter] = currentCard;
            currentCard = (Card) currentCard.getNext();
            counter++;
        }
        //cardArray[size-1] = (Card) getLastCard();
        return cardArray;
    }

    //string representation of the array
    public String toString() throws NullPointerException {
        if (size == 0) {
            return "[]";
        }
        Card currentCard = firstCard;
        String result = "[";
        while (currentCard.getNext() != null) {
            result += currentCard + "  ";
            currentCard = (Card) currentCard.getNext();
        }
        if (getLastCard() != null) {
            result += currentCard.toString();
        } else {
            throw new NullPointerException("The Deck is Empty");
        }
        //result += "["+currentCard.toString()+"]";
        result += "]";
        return result;
    }

    //string representation of the array
    public String representBoard() throws NullPointerException {
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

    //grab the first card
    public Card getFirstCard() throws NullPointerException {
        if (firstCard == null)
            throw new NullPointerException("None shall pass.");
        return firstCard;
    }

    //grab the last card
    public Card getLastCard() throws NullPointerException {
        if (firstCard == null)
            throw new NullPointerException("The Deck is Empty");
        return lastCard;
    }
    //check answer of 00 format
    public boolean checkAnswer(int pa, int pb) {
        if (pa == pb) {
            return false;
        }
        return checkAnswer(pa, pb, 0);
    }

    //overload for 000 format
    public boolean checkAnswer(int pa, int pb, int pc) {
        if (pa == pb || pa == pc || pb == pc) {
            return false;
        }
        int a, b, c;
        a = getNthCardValue(pa);
        b = getNthCardValue(pb);
        if (pc == 0) {
            c = 0;
        } else {
            c = getNthCardValue(pc);
        }

        return ((a + b + c) == 36) || ((a + b) == 11);
    }

    //get the raw posish of the KJQ cards
    private void getJQKPos() throws NullPointerException {
        //return the raw posish's e.g. [7,8,9]
        int[] posish = {0, 0, 0};

        //iterate the board
        for (int i = 1; i < size + 1; i++) {
            int value = getNthCardValue(i);
            //get the card point value on ith posish
            switch (value) {
                case 11 ->
                        posish[0] = i;
                case 12 ->
                        posish[1] = i;
                case 13 ->
                        posish[2] = i;
            }
        }
        if (posish[0] > 0 && posish[1] > 0 && posish[2] > 0) {
            validMove.push(posish);
        }

    }
    //is KJQ exists on the board
    public boolean isJQK() {return cJ != 0 && cQ != 0 && cK != 0;}

    public int getcJ() {return cJ;}  //get the cJ counter

    public int getcQ() {return cQ;}  //get the cQ counter

    public int getcK() {return cK;}  //get the cK counter

    public void clear() {                                               //clear the board
        firstCard.setNext(null);
        lastCard.setNext(null);
        size = 0;
    }      //clear a board (for tests)

    public int getSize() {return size;}  //get the size of the board

    //check all valid moves except KJQ <- Board
    public void searchValidMoves() {
        //clear the stack to make sure
        validMove.clear();
        if (checkAnswer(1, 2) == true) {
            int[] a = {1, 2};
            validMove.push(a);
        }


        Card outerCard = firstCard;
        int outer = 1;
        //while while....
        while (outerCard.getNext() != null) {
            Card innerCard = (Card) firstCard.getNext();
            Card offsetCard = innerCard;
            int inner = getSize() - outer + 1;
            while (innerCard.getNext() != null) {

                innerCard = (Card) innerCard.getNext();
                //System.out.println("i: " +innerCard+" | o: " +outerCard + "offset: " + offsetCard);
                if ((outerCard.getCardValue() + innerCard.getCardValue() == 11)) {
                    int inner2 = size - outer;

                    int[] valid = {0, 0};
                    valid[0] = getCardPosition(innerCard);
                    valid[1] = getCardPosition(outerCard);
                    //push the found posish
                    validMove.push(valid);
                }

                inner++;
            }
            outerCard = (Card) outerCard.getNext();
            offsetCard = (Card) offsetCard.getNext();

            outer++;
        }
        getJQKPos(); //filled up KJQ posish to stack, providing
    }                //this element on the top of the stack to
                     //maximizing efficiency


    //update and return the valid move stack <-App.java
    public Stack getValidMove() {
        searchValidMoves();
        return validMove;
    }

    //remove a specific card from the deck
    public Card removeACard(Card aCard) throws CardNotFoundException {
        //used at testing
        Card c = (Card) firstCard;
        Card tmpCard = c;
        int i = 1;
        boolean found = false;
        //remove the first element
        //of the list
        if (c.getCardValue() == aCard.getCardValue() &&
                c.getStrSuit() == aCard.getStrSuit()) {
            firstCard = (Card) c.getNext();
            size--;
            found = true;
        }
        //iterate throughout the list
        while (!found && c.getNext() != null) {
            Card n = (Card) c.getNext();

            //if both suit and value ar the same
            //we found the card
            if (n.getCardValue() == aCard.getCardValue() &&
                    n.getStrSuit() == aCard.getStrSuit()) {
                found = true;
                size--;
                //skipp the next card
                c.setNext(n.getNext());
                tmpCard = n;
            } else {
                //if no match, get the next card
                c = (Card) c.getNext();
            }
            i++;
        }//endwhile
        setlastcard();
        if (!found)
            throw new CardNotFoundException(aCard);
        return tmpCard;
    }

    //used multiple times, so abstracted
    private void setlastcard() {
        //updates the card on the board
        Card card = firstCard;
        while (card.getNext() != null) {
            card = (Card) card.getNext();
        }
        lastCard = card;
    }

    //remove a specific card number from the board
    //used at testing
    public Card removeNthCard(int num){
        Card currentCard = (Card) firstCard;
        Card tmpCard = currentCard;
        //retrieve the very first card
        if (num == 1) {
            firstCard = (Card) currentCard.getNext();
            size--;
            return tmpCard;
        }
        int counter = 0;
        //iterate throughout the board
        while (counter <= num && currentCard.getNext() != null) {
            Card nextCard = (Card) currentCard.getNext();

            if (num - 2 == counter) {
                size--;
                currentCard.setNext(nextCard.getNext());
                tmpCard = nextCard;

            } else {
                currentCard = (Card) currentCard.getNext();
            }
            counter++;
        }//endwhile
        setlastcard();
        return tmpCard;
    }

    //get the Nth card point value <- Board.java
    public int getNthCardValue(int num) throws NullPointerException {
        if (num < 1 || num > size)
            throw new NullPointerException("The selection is out of range");
        Card currentCard = (Card) firstCard;
        if (num == 1) return firstCard.getCardValue();
        int counter = 0;
        while (counter <= num && currentCard.getNext() != null) {
            Card nextCard = (Card) currentCard.getNext();

            if (num - 2 == counter) {
                return nextCard.getCardValue();
            } else {
                currentCard = (Card) currentCard.getNext();
            }
            counter++;
        }//endwhile
        return -1;
    }
    //get the Nth card point value App uses this
    public Card getNthCard(int num) throws NullPointerException {
        if (num < 1 || num > size)
            throw new NullPointerException("The selection is out of range");
        Card currentCard = (Card) firstCard;
        if (num == 1) return firstCard;
        int counter = 0;
        while (counter <= num && currentCard.getNext() != null) {
            Card nextCard = (Card) currentCard.getNext();

            if (num - 2 == counter) {
                return nextCard;
            } else {
                currentCard = (Card) currentCard.getNext();
            }
            counter++;
        }//endwhile
        return new Card(0, "h");
    }
}