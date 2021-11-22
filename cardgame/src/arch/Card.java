package arch;

public class Card<T> extends Node{
    private String suit;
    private boolean isFace;
    private String denoted;
    private int cardValue;

    public Card(int pValue, String pSuit) {
        super(pValue);
        cardValue = validateValue(pValue);
        suit = validateSuit(pSuit);
        setIsFaceCard();
        denoted = setDenote();
    }

    private void setIsFaceCard(){
        isFace = cardValue >= 11 && cardValue <= 13;
    }

    private int validateValue(int value) throws IllegalStateException{
        if (value >= 1 && value <= 13){
            return value;
        }else{
            throw new IllegalStateException("Invalid CardValue: " + value);
        }
    }

    private String validateSuit(String suit) throws IllegalStateException{
        String suitLower = suit.toLowerCase();
        if (suitLower.equals("s") || suitLower.equals("c") || suitLower.equals("h") || suitLower.equals("d")){
            return suitLower;
        }else{
            throw new IllegalStateException("Invalid Suit: " + suit);
        }
    }

    private String setDenote(){
        String tmpDenoted = "";
        switch (cardValue) {
            case 1:  tmpDenoted = "A";
                break;
            case 2: case 3: case 4: case 5: case 6: case 7: case 8: case 9:
                tmpDenoted = Integer.toString(cardValue);
                break;
            case 10:  tmpDenoted = "T";
                break;
            case 11:  tmpDenoted = "J";
                break;
            case 12:  tmpDenoted = "Q";
                break;
            case 13:  tmpDenoted = "K";
                break;
            default: tmpDenoted = "-1";
                break;
        }
        return tmpDenoted;
    }

    public String callOut(){
        return cardValue + " of " + suit;
    }

    @Override
    public String toString() {
        //System.out.println(Integer.toString(cardValue));
        return "["+Integer.toString(cardValue)+"]";
    }
    public int getCardValue() {
        return cardValue;
    }

    public String getSuit() {
        return suit;
    }

    public boolean isFace() {
        return isFace;
    }

    public String getDenoted() {
        return denoted;
    }


}
