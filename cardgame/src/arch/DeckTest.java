package arch;
import arch.*;
public class DeckTest {

    public static void state(Deck d){
        System.out.println("Current Deck Size: " + d.getSize());
        try {
        System.out.println("First Card: " + d.getFirstCard().toString());
        System.out.println("Last Card: " + d.getLastCard().toString());
        System.out.println("Returned ArrayLength: " + d.toArray().length);
        System.out.println("ToString: " + d.toString());
        System.out.println("\n");
        }catch(EmptyDeckException e){
            System.out.println("Empty Deck\n");
        }catch (NullPointerException e){
            System.out.println("This card is not in the deck");
        }
        //catch (LockedDeckException e){
        //    System.out.println("The Deck is Locked, not allowed to add more cards anymore");
        //}
    }

    public static void main(String[] args) throws LockedDeckException, CardNotFoundException {

        System.out.println("DECK TEST:\n-------------");
        Deck d= new Deck();

        state(d);

        System.out.println("REMOVE THE FIRST K CARD:\n-------------");
        d.remove();
        d.remove();
        d.remove();
        d.remove();
        state(d);


        Card c = new Card(7,"s");
        System.out.println("Removing " + c.toString() + ":\n-------------");
        d.remove(c);
        state(d);

        Card c2 = new Card(12,"c");
        System.out.println("Removing first card " + c2.toString() + ":\n-------------");
        d.remove(c2);
        state(d);

        Card c3 = new Card(1,"h");
        System.out.println("Removing last card " + c3.toString() + ":\n-------------");
        d.remove(c3);
        state(d);

        Card c4 = new Card(1,"d");
        System.out.println("Removing last card " + c4.toString() + ":\n-------------");
        d.remove(c4);
        state(d);

        System.out.println("Creation of an empty deck:\n-------------");
        Deck emptyDeck = new Deck(true);
        state(emptyDeck);

        System.out.println("Add just a couple of card to the deck:\n-------------");
        emptyDeck.addNewEntry(c);
        emptyDeck.addNewEntry(c2);
        emptyDeck.addNewEntry(c3);
        emptyDeck.addNewEntry(c4);
        state(emptyDeck);

        try {
            Card c5 = new Card(12, "h");
            System.out.println("Remove a non-existing card from the deck:" + c5.toString() + "\n-------------");
            emptyDeck.remove(c5);
            state(emptyDeck);
        }catch(CardNotFoundException e){
            System.out.println("Card not found: " + e);
        }
    }
}