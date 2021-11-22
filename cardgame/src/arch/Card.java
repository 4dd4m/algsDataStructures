package arch;

public class Card<T> extends Node{
    private String  suit, strSuit;
    private boolean isFace;
    private int     cardValue;

    public Card(int pValue, String pSuit) {
        super(pValue);
        cardValue = validateValue(pValue);
        setIsFaceCard();
        validateSuit(pSuit);
    }

    private int validateValue(int value) throws IllegalStateException{
        if (value >= 1 && value <= 13){
            return value;
        }else{
            throw new IllegalStateException("Invalid CardValue: " + value);
        }
    }

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

    public String toString() throws IllegalStateException{
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

    public int getCardValue() {return cardValue;}
    public String getSuit()   {return suit;}
    public boolean isFace()   {return isFace;}
    public String getStrSuit(){return strSuit;}
    private void setIsFaceCard(){isFace = cardValue >= 11 && cardValue <= 13;}
}
