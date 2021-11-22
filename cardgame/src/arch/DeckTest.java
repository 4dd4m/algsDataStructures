package arch;
import arch.*;

public class DeckTest {

    public static void state(Deck d){
        System.out.println("Deck Size: " + d.getNumOfCards());
        System.out.println("First Card: " + d.getFirstCard());
        System.out.println("Last Card: " + d.getLastCard());
        System.out.println("ToArray: " + d.toArray());
        System.out.println("ToString: " + d.toString());
    }

    public static void main(String[] args) {
        Deck deck = new Deck();
        state(deck);
    }


}
