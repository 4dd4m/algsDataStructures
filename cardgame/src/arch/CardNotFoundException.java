package arch;

public class CardNotFoundException extends Exception{
    public  CardNotFoundException(replayItem c) {
        super(c.toString());
    }
}
