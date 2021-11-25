package arch;

public class DemoTest {

    public static void testTemodnstrationMode() throws LockedDeckException, EmptyDeckException {
        App app = new App();
        Deck d = new Deck(true,true);
        Board b = new Board();
        app.playMode(true);
    }

    public static void main(String[] args) throws LockedDeckException, EmptyDeckException {
        testTemodnstrationMode();

    }
}
