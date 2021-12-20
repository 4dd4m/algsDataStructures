package arch;

public class DeckTest {
    public static void state(Deck d) {
        System.out.println("Current Deck Size: " + d.getSize());
        try {
            System.out.println("First Card: " + d.getFirstCard().toString());
            System.out.println("Last Card: " + d.getLastCard().toString());
            System.out.println("Deck Size: " + d.toArray().length);
            System.out.println("ToString: " + d.toString());
            System.out.println("Shuffle: " + d.isLocked());
            System.out.println("\n");
        } catch (EmptyDeckException e) {
            System.out.println("Empty Deck\n");
        } catch (NullPointerException e) {
            System.out.println("This card is not in the deck");
        }
    }

    public static void shuffledState(Deck d) {
        System.out.println("Current Deck Size: " + d.getSize());
        try {
            System.out.println("Shuffle: true");
            System.out.println("First Card: " + d.getFirstCard().toString());
            System.out.println("Last Card: " + d.getLastCard().toString());
            System.out.println("Deck Size: " + d.toArray().length);
            System.out.println("ToString: " + d.toString());
            System.out.println("\n");
        } catch (EmptyDeckException e) {
            System.out.println("Empty Deck\n");
        } catch (NullPointerException e) {
            System.out.println("This card is not in the deck");
        }
    }

    public static void main(String[] args) throws LockedDeckException, CardNotFoundException, EmptyDeckException {
        try {
            System.out.println("Crating and Empty Deck");
            Deck dc = new Deck(false, false);
            state(dc);
            System.out.println("\nCreating A full but not shuffled deck");
            Deck dd = new Deck(false, true);
            state(dd);
            System.out.println("Creating A full and shuffled deck");
            Deck de = new Deck(true, true);
            state(de);


            System.out.println("DECK TEST:\n-------------");
            Deck d = new Deck();
            state(d);
            System.out.println("REMOVE THE FIRST 4 K CARD:\n-------------");
            d.removeFirstElement();
            d.removeFirstElement();
            d.removeFirstElement();
            d.removeFirstElement();
            state(d);
            Card c = new Card(7, "s");
            System.out.println("Removing: " + c + "(connected two card [->][][<-] the first element doesnt change)\n-------------");
            d.removeFirstElement(c);
            state(d);
            Card c2 = new Card(12, "c");
            System.out.println("Removing first card: " + c2 + "\n-------------");
            d.removeFirstElement(c2);
            state(d);
            Card c3 = new Card(1, "h");
            System.out.println("Removing last card: " + c3 + "\n-------------");
            d.removeFirstElement(c3);
            state(d);
            Card c4 = new Card(1, "d");
            System.out.println("Removing last card: " + c4 + "\n-------------");
            d.removeFirstElement(c4);
            state(d);
            System.out.println("Creation of an empty deck:\n-------------");
            Deck emptyDeck = new Deck(false, false);
            state(emptyDeck);
            System.out.println("\nAdd just a couple of card to the deck:\n-------------");
            emptyDeck.addNewEntry(c);
            emptyDeck.addNewEntry(c2);
            emptyDeck.addNewEntry(c3);
            emptyDeck.addNewEntry(c4);
            state(emptyDeck);
            Card c5 = new Card(12, "h");
            System.out.println("Remove a non-existing card from the deck: " + c5 + "\n-------------");
            emptyDeck.removeFirstElement(c5);
            state(emptyDeck);
        } catch (CardNotFoundException e) {
            System.out.println("Card not found: " + e + "\n");
        }

        System.out.println("Testing shuffle twice\n-----------------");
        try {
            Deck sd = new Deck(true, true);
            sd.shuffle();
            shuffledState(sd);

        } catch (LockedDeckException e) {
            System.out.println(e);
        }
        
    }
}