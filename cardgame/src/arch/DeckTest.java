package arch;
public class DeckTest {
    public static void state(Deck d){
        System.out.println("Current Deck Size: " + d.getSize());
        try {
        System.out.println("First Card: " + d.getFirstCard().toString());
        System.out.println("Last Card: " + d.getLastCard().toString());
        System.out.println("Returned ArrayLength: " + d.toArray().length);
        System.out.println("ToString: " + d.toString());
        System.out.println("Shuffle: false");
        System.out.println("\n");
        }catch(EmptyDeckException e){
            System.out.println("Empty Deck\n");
        }catch (NullPointerException e){
            System.out.println("This card is not in the deck");
        }
    }

    public static void shuffledState(Deck d){
        System.out.println("Current Deck Size: " + d.getSize());
        try {
            System.out.println("Shuffle: true");
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
    }

    public static void main(String[] args) throws LockedDeckException, CardNotFoundException {
        try {
        System.out.println("DECK TEST:\n-------------");
        Deck d= new Deck();

        state(d);

        System.out.println("REMOVE THE FIRST 4 K CARD:\n-------------");
        d.removeFirstCard();
        d.removeFirstCard();
        d.removeFirstCard();
        d.removeFirstCard();
        state(d);

        Card c = new Card(7,"s");
        System.out.println("Removing " + c.toString() + ":\n-------------");
        d.removeFirstCard(c);
        state(d);

        Card c2 = new Card(12,"c");
        System.out.println("Removing first card " + c2.toString() + ":\n-------------");
        d.removeFirstCard(c2);
        state(d);

        Card c3 = new Card(1,"h");
        System.out.println("Removing last card " + c3.toString() + ":\n-------------");
        d.removeFirstCard(c3);
        state(d);

        Card c4 = new Card(1,"d");
        System.out.println("Removing last card " + c4.toString() + ":\n-------------");
        d.removeFirstCard(c4);
        state(d);

        System.out.println("Creation of an empty deck:\n-------------");
        Deck emptyDeck = new Deck(true);
        state(emptyDeck);
            System.out.println("\n");
        System.out.println("Add just a couple of card to the deck:\n-------------");
        emptyDeck.addNewEntry(c);
        emptyDeck.addNewEntry(c2);
        emptyDeck.addNewEntry(c3);
        emptyDeck.addNewEntry(c4);
        state(emptyDeck);
        Card c5 = new Card(12, "h");
        System.out.println("Remove a non-existing card from the deck:" + c5.toString() + "\n-------------");
        emptyDeck.removeFirstCard(c5);
        state(emptyDeck);
        }catch(CardNotFoundException e){
            System.out.println("Card not found: " + e + "\n");
        }catch(EmptyDeckException e){
            System.out.println("Deck is Empty");
        }

        try {
            Deck sd = new Deck();
            sd.shuffle();
            shuffledState(sd);

        }catch(EmptyDeckException e){

        }
    }
}