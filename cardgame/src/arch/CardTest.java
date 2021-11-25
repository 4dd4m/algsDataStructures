package arch;

public class CardTest {
    public static void state(replayItem c){
        System.out.println("Suit:\t\t\t" + c.getSuit());
        System.out.println("isFace:\t\t\t" + c.isFace());
        System.out.println("cardValue:\t\t" + c.getCardValue());
        System.out.println("callOut: \t\t" + c.toString() + "\n");
    }

    public static void main(String[] args) {
        // Creation of a linked List
        LinkedList<String> myList = new LinkedList<>();
        System.out.println("\n\nCreating a set of suit:");
        replayItem[] cards = new replayItem[13];
        cards[0] = new replayItem(13, "S");
        cards[1] = new replayItem(12, "S");
        cards[2] = new replayItem(11, "S");
        cards[3] = new replayItem(10, "S");
        cards[4] = new replayItem(9, "S");
        cards[5] = new replayItem(8, "S");
        cards[6] = new replayItem(7, "S");
        cards[7] = new replayItem(6, "S");
        cards[8] = new replayItem(5, "S");
        cards[9] = new replayItem(4, "S");
        cards[10] = new replayItem(3, "S");
        cards[11] = new replayItem(2, "S");
        cards[12] = new replayItem(1, "S");

        for (replayItem c :cards) {
            state(c);
        }
        System.out.println("\n\nCreating an invalid suit:");
        try{
            replayItem card1 = new replayItem(1,"x");
        }catch (IllegalStateException ex){
            System.out.println(ex.getMessage());
        }

        System.out.println("\n\nCreating w an invalid suit:");
        try{
        replayItem card1 = new replayItem(1,"x");
            replayItem card2 = new replayItem(15,"s");
        }catch (IllegalStateException ex){
            System.out.println(ex.getMessage());
        }

        System.out.println("\n\nCreating card w invalid value:");
        try{
        replayItem card2 = new replayItem(15,"s");
        }catch (IllegalStateException ex){
            System.out.println(ex.getMessage());
        }
    }
}