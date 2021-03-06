package arch;

public class CardTest {
    public static void state(Card c) {
        System.out.println("Suit:\t\t\t" + c.getSuit());
        System.out.println("isFace:\t\t\t" + c.isFace());
        System.out.println("cardValue:\t\t" + c.getCardValue());
        System.out.println("callOut: \t\t" + c.toString() + "\n");
    }

    public static void main(String[] args) {

        //creation of an empty card
        System.out.println("\nCard empty = new Card();");
        try {
            Card empty = new Card();
        }catch(NullPointerException e){
            System.out.println(e);
        }


        // Creation of a card array
        LinkedList<String> myList = new LinkedList<>();
        System.out.println("\n\nCreating a set of suit:");
        Card[] cards = new Card[13];
        cards[0] = new Card(13, "S");
        cards[1] = new Card(12, "S");
        cards[2] = new Card(11, "S");
        cards[3] = new Card(10, "S");
        cards[4] = new Card(9, "S");
        cards[5] = new Card(8, "S");
        cards[6] = new Card(7, "S");
        cards[7] = new Card(6, "S");
        cards[8] = new Card(5, "S");
        cards[9] = new Card(4, "S");
        cards[10] = new Card(3, "S");
        cards[11] = new Card(2, "S");
        cards[12] = new Card(1, "S");

        for (Card c : cards) {
            state(c);
        }
        System.out.println("\n\nCreating an invalid suit:");
        try {
            Card card1 = new Card(1, "x");
        } catch (IllegalStateException ex) {
            System.out.println(ex.getMessage());
        }

        System.out.println("\n\nCreating w an invalid suit:");
        try {
            Card card1 = new Card(1, "x");
            Card card2 = new Card(15, "s");
        } catch (IllegalStateException ex) {
            System.out.println(ex.getMessage());
        }

        System.out.println("\n\nCreating card w invalid value:");
        try {
            Card card2 = new Card(15, "s");
        } catch (IllegalStateException ex) {
            System.out.println(ex.getMessage());
        }
    }
}