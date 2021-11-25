package arch;

public class EmptyBoardException extends Exception{
    public  EmptyBoardException(replayItem c) {
        super(c.toString());
    }
}
