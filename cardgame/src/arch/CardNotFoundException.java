package arch;

public class CardNotFoundException extends Exception{
    public  CardNotFoundException(Card c) {
        super(c.toString());
    }
}
