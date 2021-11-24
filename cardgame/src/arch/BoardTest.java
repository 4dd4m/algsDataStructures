package arch;
public class BoardTest {

    public static void boardState(Board b){
        System.out.println("-------------------------");
        System.out.println("\nCurrent Board Size: " + b.getSize());
            System.out.println("First Card: " + b.getFirstCard().toString());
            System.out.println("Last Card: " + b.getLastCard().toString());
            System.out.println("Number of J's: " + b.getcJ());
            System.out.println("Number of Q's: " + b.getcQ());
            System.out.println("Number of K's: " + b.getcK());
            try {
                System.out.println("Board Size: " + b.toArray().length);
                System.out.println("Board: " + b);
                System.out.println("\n");
            }catch (NullPointerException e){
                System.out.println("The board is empty");
            }
        System.out.println("-------------------------");
    }
    public static void state(Deck d){
        System.out.println("Current Deck Size: " + d.getSize());
        try {
            System.out.println("First Card: " + d.getFirstCard().toString());
            System.out.println("Last Card: " + d.getLastCard().toString());
            System.out.println("Deck Size: " + d.toArray().length);
            System.out.println("ToString: " + d);
            System.out.println("Shuffle: false");
            System.out.println("\n");
        }catch(EmptyDeckException e){
            System.out.println("Empty Deck\n");
        }catch (NullPointerException e){
            System.out.println("This card is not in the deck");
        }
    }
    public static void states(Deck d, Board b){
        state(d);
        boardState(b);
        state(d);
    }

    public static void main(String[] args) throws LockedDeckException, EmptyDeckException {
        Deck d = new Deck();
        Board b = new Board();
        System.out.println("Fill up the board with the first 9 cards\n");
        d.shuffle();
        for (int i = 0; i <= 8; i++) {
            b.addNewEntry(d.removeFirstCard());
        }
        states(d,b);


        System.out.println("Build a static board\n");

        Board ba = new Board();

        Card c1 = new Card(1,"h");
        Card c2 = new Card(2,"h");
        Card c3 = new Card(3,"h");
        Card c4 = new Card(4,"h");
        Card c5 = new Card(5,"h");
        Card c6 = new Card(6,"h");
        Card c7 = new Card(7,"h");
        Card c8 = new Card(8,"h");
        Card c9 = new Card(9,"h");
        ba.addNewEntry(c1);
        ba.addNewEntry(c2);
        ba.addNewEntry(c3);
        ba.addNewEntry(c4);
        ba.addNewEntry(c5);
        ba.addNewEntry(c6);
        ba.addNewEntry(c7);
        ba.addNewEntry(c8);
        ba.addNewEntry(c9);
        boardState(ba);
        try {
            System.out.println("Remove card: " + c3);
            ba.removeACard(c3);
            System.out.println("Get Card Value of the first element: " + ba.getNthCardValue(1));
            System.out.println("Get Card Value of the 2 element: " + ba.getNthCardValue(2));
            System.out.println("Get Card Value of the 3 element: " + ba.getNthCardValue(3));
            System.out.println("Get Card Value of the 4 element: " + ba.getNthCardValue(4));
            System.out.println("Get Card Value of the 6 element: " + ba.getNthCardValue(5));
            System.out.println("Get Card Value of the 7 element: " + ba.getNthCardValue(6));
            System.out.println("Get Card Value of the 8 element: " + ba.getNthCardValue(7));
            System.out.println("Get Card Value of the last element: " + ba.getNthCardValue(8));
        }catch(CardNotFoundException e){
            System.out.println("Card not in the board");
        }
        boardState(ba);

        try {
            System.out.println("Remove a non-existing Card: " + new Card(10,"s"));
            ba.removeACard(new Card(10,"s"));
        }catch(CardNotFoundException e){
            System.out.println("Card not in the board");
        }
        boardState(ba);


        try {
            System.out.println("Remove first Card: " + new Card(1,"h"));
            ba.removeACard(new Card(1,"h"));
        }catch(CardNotFoundException e){
            System.out.println("Card not in the board");
        }
        boardState(ba);

        try {
            System.out.println("Remove last Card: " + new Card(9,"h"));
            ba.removeACard(new Card(9,"h"));
        }catch(CardNotFoundException e){
            System.out.println("Card not in the board");
        }
        boardState(ba);

        try {
            System.out.println("Remove nth: 3 (4 of hearts)");
            ba.removeNthCard(3);
        }catch(NullPointerException e){
            System.out.println("Card not in the board");
        }
        boardState(ba);

        try {
            System.out.println("Remove the first element: 1");
            ba.removeNthCard(1);
        }catch(NullPointerException e){
            System.out.println("Card not in the board");
        }
        boardState(ba);

        try {
            System.out.println("Remove the last element: 4");
            ba.removeNthCard(4);
        }catch(NullPointerException e){
            System.out.println("Card not in the board");
        }
        boardState(ba);
        ba.clear();
        Card c10 = new Card(11,"h");
        Card c11 = new Card(12,"c");
        Card c12 = new Card(13,"s");
        ba.addNewEntry(c1);
        ba.addNewEntry(c2);
        ba.addNewEntry(c3);
        ba.addNewEntry(c4);
        ba.addNewEntry(c5);
        ba.addNewEntry(c6);
        ba.addNewEntry(c10);
        ba.addNewEntry(c11);
        ba.addNewEntry(c12);
        boardState(ba);
        try {
            System.out.println("Testing plain 11's (true)");
            System.out.println(ba.checkAnswer(2, 3, 0));
            System.out.println(ba.checkAnswer(1, 4, 0));
            System.out.println(ba.checkAnswer(1, 4, 0));
            System.out.println("Testing plain 11's (false)");
            System.out.println(ba.checkAnswer(7, 8, 0));
            System.out.println(ba.checkAnswer(7, 9, 0));
            System.out.println("Testing plain 11's (KJQ)");
            System.out.println(ba.checkAnswer(7, 8, 9));

            //System.out.println("Testing Fail Inputs"); //uncomment to test
            //System.out.println(ba.checkAnswer(0, 0, 0));
            //System.out.println(ba.checkAnswer(1, 0, 0));
            //System.out.println(ba.checkAnswer(0, 1, 0));
            //System.out.println(ba.checkAnswer(1, 1, 1));
            //System.out.println(ba.checkAnswer(1, 1, 1));
            //System.out.println(ba.checkAnswer(1, 1, 0));
            //System.out.println(ba.checkAnswer(0, 1, 1));
        }catch(NullPointerException e){
            System.out.println(e);
        }

        System.out.println("Testing possible valid moves");
        Deck db = new Deck();
        Board bb = new Board();
        System.out.println("Fill up the board with the first 9 cards\n");
        db.shuffle();
        for (int i = 0; i <= 8; i++) {
            bb.addNewEntry(d.removeFirstCard());
        }
        boardState(bb);



        Board bc = new Board();
        bc.addNewEntry(new Card(6,"c"));
        bc.addNewEntry(new Card(5,"d"));


        boardState(bc);
        bc.searchValidMoves();
        Stack st = bc.getValidMove();
        System.out.println("Valid Moves: " + st.getSize());
        bc.removeNthCard(1);
        bc.removeNthCard(1);
        bc.addNewEntry(new Card(11,"c"));
        bc.addNewEntry(new Card(12,"d"));
        bc.addNewEntry(new Card(13,"d"));
        boardState(bc);
        bc.searchValidMoves();
        st = bc.getValidMove();
        System.out.println("Valid Moves: " + st.getSize());


        //[A♦  A♠  5♣  6♠  7♦  9♥  9♦  K♥  K♣]
        Board be = new Board();
        be.addNewEntry(new Card(1,"s"));
        be.addNewEntry(new Card(1,"d"));
        be.addNewEntry(new Card(5,"c"));
        be.addNewEntry(new Card(6,"s"));
        be.addNewEntry(new Card(7,"d"));
        be.addNewEntry(new Card(9,"h"));
        be.addNewEntry(new Card(9,"d"));
        be.addNewEntry(new Card(13,"c"));
        be.addNewEntry(new Card(13,"h"));
       boardState(be);

       be.searchValidMoves();
       System.out.println(be.getValidMove().getSize());


    }
}