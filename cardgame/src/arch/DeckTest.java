package arch;

public class DeckTest {
    public static void state(Deck d) {
        System.out.println("Current Deck Size: " + d.getSize());
        try {
            System.out.println("First Card: " + d.getFirstCard().toString());
            System.out.println("Last Card: " + d.getLastCard().toString());
            System.out.println("Deck Size: " + d.toArray().length);
            System.out.println("ToString: " + d.toString());
            System.out.println("Shuffle: false");
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
            d.removeFirstCard();
            d.removeFirstCard();
            d.removeFirstCard();
            d.removeFirstCard();
            state(d);
            replayItem c = new replayItem(7, "s");
            System.out.println("Removing: " + c + "(connected two card [->][][<-] the first element doesnt change)\n-------------");
            d.removeFirstCard(c);
            state(d);
            replayItem c2 = new replayItem(12, "c");
            System.out.println("Removing first card: " + c2 + "\n-------------");
            d.removeFirstCard(c2);
            state(d);
            replayItem c3 = new replayItem(1, "h");
            System.out.println("Removing last card: " + c3 + "\n-------------");
            d.removeFirstCard(c3);
            state(d);
            replayItem c4 = new replayItem(1, "d");
            System.out.println("Removing last card: " + c4 + "\n-------------");
            d.removeFirstCard(c4);
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
            replayItem c5 = new replayItem(12, "h");
            System.out.println("Remove a non-existing card from the deck: " + c5 + "\n-------------");
            emptyDeck.removeFirstCard(c5);
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