package arch;

import java.util.Scanner;

public class App {
    Scanner scan = new Scanner(System.in);
    private int gameState = 0; //0 running game | 1 win | -1 staleMate
    private Replay replay = new Replay();


    public App() throws LockedDeckException, EmptyDeckException {}

    public void selectMode() throws LockedDeckException, EmptyDeckException { //select and run the mode
        int user;

        System.out.println("CardGame\n-----------------");
        System.out.println("1. Play\n2. Demonstration Mode\n3. Rules\n4. Exit");
        Scanner scan = new Scanner(System.in);//this is for testing
        user = Integer.parseInt(scan.nextLine());

        while (user != 4) {                                                 //until we get a valid choice
            if (user == 1) {                                                //call the corresponding mode
                System.out.println("Play Mode\n");
                playMode(false);
            } else if (user == 2) {
                System.out.println("Demonstration Mode");
                playMode(true);
            } else if (user == 3) {
                System.out.println("The Rules of the Game\n");
                showRules();
            } else if (user == 4) {
                System.out.println("Bye...");
            }
        }
    }

    public void playMode(boolean boo) throws LockedDeckException, EmptyDeckException {  //main game
        System.out.println("Dealing Your cards......");
        Deck d = new Deck(true, true);                                      //get a new deck and board
        Board b = new Board();

        for (int i = 0; i < b.BOARDSIZE; i++) {                                     //deal the BOARDSIZE amount of cards
            b.addNewEntry(d.removeFirstElement());
        }
        System.out.println(b.representBoard());                                     //print out the board
        System.out.println("----------------------------------------");
        String initBoard = "Initial board: " + b.toString();                  //record the board initial state to replay
        ReplayItem r = new ReplayItem(initBoard);
        replay.push(r);

        while (gameState != -1 || (d.getSize() == 0 && b.getSize() == 0)) { //until no valid moves or (deck and board
            if (gameState == -1) {                                          //gets empty
                break;                                                      //break out
            }
            b.getValidMove().clear();                                       //clear the valid moves stack
            int cardRemoved = 0;
            boolean acceptableMove = false;

            Stack v = b.getValidMove();                                     //get all valid moves
            int[] choice = {0, 0, 0};                                       //init
            if (v.getSize() > 0) {                                          //if the valid stack has moves
                if (boo == true) {                                        //the machine will auto peek to learn a posish
                    choice = v.peek();
                } else {                                                  //USER MODE
                    boolean proceed = false;                              //user interface, hint, validate
                    while (proceed == false) {
                        System.out.print("[" + d.getSize() + "]]]]\tPlease Select Card: ");
                        String user = scan.nextLine();
                        if (user.length() > 0 && user.length() <= 3 && !user.equals("h")) {
                            for (int i = 0; i <= user.length() - 1; i++) {
                                choice[i] = user.charAt(i) - 48;
                                //System.out.println(user.charAt(i));
                            }
                            int[] tmpArr = choice;                         //order the users input
                            for (int i = 0; i <= tmpArr.length; i++) {
                                for (int j = i + 1; j < tmpArr.length; j++) {
                                    int tmp = 0;
                                    if (tmpArr[i] < tmpArr[j]) {
                                        tmp = tmpArr[i];
                                        tmpArr[i] = tmpArr[j];
                                        tmpArr[j] = tmp;
                                    }
                                }
                            }
                            if (!b.checkAnswer(choice[0], choice[1]) &&      //validate their selection
                                    b.checkAnswer(choice[0], choice[1], choice[2]) == false) {
                                System.out.println("This is not good selection");
                            } else {                                        //invalid input will stop and ask again
                                proceed = true;
                                break;
                            }
                        } else if (user.equals("h")) {                      //activate hint & auto remove
                            choice = v.peek();
                            String hint = "";
                            for (int i = 0; i < choice.length; i++) {
                                hint += Integer.toString(choice[i]) + "  ";
                            }
                            System.out.println("You can continue like this -> " + hint);
                            System.out.println("Let me help ya, I remove it for you...");
                            proceed = true;
                            break;
                        }
                    }
                }
                if (choice.length == 2 || choice[2] == 0) {                //extra security, validating the move on
                    cardRemoved = 2;                                       //the board as well | input nn0
                    acceptableMove = b.checkAnswer(choice[0], choice[1]);
                } else if (choice.length == 3) {                           //testing input nnn
                    cardRemoved = 3;
                    acceptableMove = b.checkAnswer(choice[0], choice[1], choice[2]);
                } else {
                    gameState = -1;
                    break;
                }

            } else {                                                      //no valid moves in the stack so stalemate
                gameState = -1;
                System.out.println("No valid Moves");
                break;
            }
            if (acceptableMove) {                                         //if the users move is acceptable
                String choosen = "";
                choosen += b.getNthCard(choice[0]).toString() + "  ";       //prepare the input for replay
                choosen += b.getNthCard(choice[1]).toString() + "  ";
                if (choice.length == 3 && choice[2] != 0) {
                    choosen += b.getNthCard(choice[2]).toString() + "  ";
                }
                System.out.println("Removed Cards:\t" + choosen);           //reprint the selection

                if (b.getSize() > 0) {                                      //solve the KJQ tab issue
                    if (choice.length == 3) {
                        choosen += "\tafter-->\t" + b.toString();
                    } else {
                        choosen += "\t\tafter-->\t" + b.toString();
                    }
                }

                ReplayItem rinit = new ReplayItem(choosen);                 //push the selection to the stack
                replay.push(rinit);

                if (b.getSize() == 2) {                                     //artificial board clear when two cards left
                    b.clear();                                              //anytime it happens, that is a won scen.
                } else {                                                    //to avoid mess up with the replay
                    for (int i : choice) {
                        b.removeNthCard(i);
                    }
                }
                if (d.getSize() > 0) {                                      //if deck has 2 card we can't remove 3
                    int toReplace = Math.min(d.getSize(), cardRemoved);

                    for (int i = 0; i < toReplace; i++) {                   //replace the cards
                        b.addNewEntry(d.removeFirstElement());
                    }
                }
                if (d.getSize() == 0 && b.getSize() == 0) {                 //no deck, no board means a win
                    gameState = 1;
                    break;
                }
                System.out.println("-----------------");
                System.out.println(b.representBoard());                     //display the empty board
            }
        }
        switch (gameState) {                                                //things relating to the game state
            case -1:                                                        //the game is over
                System.out.println("\n\"Game over man, GAME OVER!\" - Pvt. Hudson");
                System.out.println("Cards Left: " + d.getSize());           //deck size
                break;
            case 1:
                System.out.println("\nHold my beer...... YOU WON\n\n");     //win
                b = new Board();                                            //create a new board to proper cleanup
        }
        System.out.println("\nWould you like to see the replay? (y/n) - (q) quit"); //handling replay and quit
        String choice = scan.next();
        if (choice.equals("y") || choice.equals("Y")) {                             //render replay
            String finalBoard = "Final Board:\t" + b.toString();

            System.out.println("Action Replay!");
            System.out.println("Replay Size: " + replay.size);
            replay.push(new ReplayItem(finalBoard));
            replay.printHistory();
        } else if (choice.equals("q") || choice.equals("Q")                         //quit
                || choice.equals("n") || choice.equals("N")) {
            System.exit(0);
        }
    }

    public static void showRules() {                                                //nice touch!
        System.out.println("Elevens is extremely similar to Bowling Solitaire,\n" +
                "except that the layout is a little different and\n" +
                "the goal is to make matching pairs that add up to\n" +
                "11 rather than adding matching pairs up to 10.\n" +
                "\n" +
                "Empty spaces in the 9-card formation are automatically\n" +
                "filled by placing a card from the Deck in the free space.\n" +
                "Once you run out of cards in the Deck, do not fill the\n" +
                "empty spaces in the card formation with any other cards.\n" +
                "\n" +
                "To play this game, look at your 9-card formation and see\n" +
                "if any cards can be matched that add up to 11 in total.\n" +
                "If you have a matching pair that can create this sum, \n" +
                "then you may remove them from place. Once you’ve done so,\n" +
                "remember to fill in the gaps left by these two cards with\n" +
                "two cards from the Deck.\n" +
                "\n" +
                "Only cards in the 9-card formation are available to play\n" +
                "with, and you may not build any cards on top of each other\n" +
                "during the game. Cards cannot be removed from the Deck \n" +
                "unless they are being placed in the table layout, and you \n" +
                "should not look at the cards in the Deck before moving them\n" +
                "into play. They must remain unknown until they are flipped\n" +
                "over to be placed in the 9-card formation.\n" +
                "\n" +
                "The ranking of cards matches their face value i.e. the two of\n" +
                "clubs is equal to two. Aces hold a value of one and Jacks, \n" +
                "Queens, and Kings equal eleven only when they are removed \n" +
                "together. For example, if you have a Jack and King on your \n" +
                "board you can’t remove either until a Queen appears. Once all\n" +
                "three cards are present on the board they can be removed \n" +
                "together to make “11”. They are the only cards in the game \n" +
                "that are moved as a trio, rather than being matched as a pair.\n" +
                "\n" +
                "HOW TO WIN:\n" +
                "\n" +
                "To win at a round of Elevens, you must remove absolutely all\n" +
                "cards from play – including those from the Deck. Once you \n" +
                "have matched all cards in the Deck, then you have won the round.\n" +
                "\n" +
                "It is possible to play this game with more than one player. \n" +
                "To do so, you could create a scoring system by having each \n" +
                "player keep their matched pairs and making each set worth 1 point.\n" +
                "The player with the highest number of points would win the game.\n" +
                "Typically, this is a solo player game, but it’s extremely easy to\n" +
                "make into a family-friendly or party game.");
    }

    public static void main(String[] args) throws LockedDeckException, EmptyDeckException {
        App a = new App();
        try {
            a.selectMode();
        } catch (NumberFormatException e) {
            System.out.println("Invalid Output! Let's try that again");
            a.selectMode();
        } catch (LockedDeckException e) {
            e.printStackTrace();
        } catch (EmptyDeckException e) {
            e.printStackTrace();
        }
    }
}
