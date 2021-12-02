package arch;

public class Card extends Node{
    private String  suit, strSuit;
    private boolean isFace;
    private int     cardValue;

    //new Card(1,"h");
    public Card(int pValue, String pSuit) {
        super(pValue);
        cardValue = validateValue(pValue);
        setIsFaceCard();
        validateSuit(pSuit);
    }

    public Card() throws NullPointerException {
        throw new NullPointerException("Empty Card Cannot Be created");
    }

    //card point value restriction
    private int validateValue(int value) throws IllegalStateException{
        if (value >= 1 && value <= 13){
            return value;
        }else{
            throw new IllegalStateException("Invalid CardValue: " + value);
        }
    }

    //nice suit display
    private void validateSuit(String pSuit) throws IllegalStateException{
        String suitLower = pSuit.toLowerCase();
        String tmpsuit = "";
        if (suitLower.equals("s") || suitLower.equals("c") || suitLower.equals("h") || suitLower.equals("d")){
            switch (suitLower){
                case "s":
                    tmpsuit = "♠";
                    break;
                case "c":
                    tmpsuit = "♣";
                    break;
                case "h":
                    tmpsuit = "♥";
                    break;
                case "d":
                    tmpsuit = "♦";
                    break;
            }
            strSuit = suitLower;
            suit =  tmpsuit;
        }else{
            throw new IllegalStateException("Invalid Suit: " + pSuit);
        }
    }
    public String toString() throws IllegalStateException{                      //representation
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
            default:
                throw new IllegalStateException("Unknown cardValue: " + cardValue);
        }
        return tmpDenoted+this.suit;
    }
    public int getCardValue() {return cardValue;}                               //get point value e.g. J -> 11
    protected void setCardValue(int v) {cardValue = v;}                         //set a card value e.g. 11 -> J
    protected void setCardSuit(String s) {                                      //stringify a suit
        strSuit = s;
        validateSuit(s);
    }
    public String getSuit()   {return suit;}                                    //format
    public boolean isFace()   {return isFace;}                                  //is JQK?
    public String getStrSuit(){return strSuit;}                                 //get the suit in "h","s","p" format
    private void setIsFaceCard(){isFace = cardValue >= 11 && cardValue <= 13;}  //set faceCard
}
