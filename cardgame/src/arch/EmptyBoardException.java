package arch;
import arch.*;
public class EmptyBoardException extends Exception{
    public  EmptyBoardException(Card c) {
        super(c.toString());
    }
}
