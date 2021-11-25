package arch;

import java.util.Scanner;

public class App {
    Scanner scan = new Scanner(System.in);
    private int gameState = 0; //0 running game | 1 win | -1 staleMate
    private Replay replay = new Replay();

    public static void selectMode(int pUser) {
        int user;
        System.out.println("CardGame\n-----------------");
        System.out.println("1. Play\n2. Demonstration Mode\n3. Rules\n4. Exit");
        if (pUser == 0) {
            Scanner scan = new Scanner(System.in);//this is for testing
            user = Integer.parseInt(scan.nextLine());
        } else {
            user = pUser;
        }
        if (user == 1) {
            System.out.println("Play Mode\n");
        } else if (user == 2) {
            System.out.println("Demonstration Mode");
        } else if (user == 3) {
            System.out.println("The Rules of the Game\n");
            showRules();
            selectMode(0);
        } else if (user == 4) {
            System.out.println("Bye...");
        }
    }
    private int selectMenu() {
        int choice = 0;
        return choice;
    }

    public void playMode(boolean boo) throws LockedDeckException, EmptyDeckException {

        Deck d = new Deck(true, true);
        Board b = new Board();
        System.out.println("Dealing Your cards......");

        for (int i = 0; i < b.BOARDSIZE; i++) {
            b.addNewEntry(d.removeFirstCard());
        } //DEALING THE FIRST NINE CARD

        System.out.println(b.representBoard());
        String initialBoard = b.toString();
        System.out.println("----------------------------------------");
        System.out.println("Your input, where: 12 <= input => 987");

        while (gameState != -1 || (d.getSize() == 0 && b.getSize() == 0)) {
            if (gameState == -1) {
                break;
            }
            b.getValidMove().clear();
            int cardRemoved = 0;
            boolean acceptableMove = false;

            Stack v = b.getValidMove();
            int[] choice = {0, 0, 0};
            if (v.getSize() > 0) {
                if (boo == true) {
                    choice = v.peek();
                } else {
                    boolean proceed = false;
                    while (proceed == false) {
                        System.out.print("Select cards: ");
                        String user = scan.nextLine();
                        if (user.length() > 0 && user.length() <= 3 && !user.equals("h")) {
                            for (int i = 0; i <= user.length() - 1; i++) {
                                choice[i] = user.charAt(i) - 48;
                                //System.out.println(user.charAt(i));
                            }


                            int[] tmpArr = choice;                         //order the user input
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

                            if (!b.checkAnswer(choice[0], choice[1]) &&
                                    b.checkAnswer(choice[0], choice[1], choice[2]) == false) {
                                System.out.println("This is not good selection");

                            } else {
                                proceed = true;
                                break;
                            }

                        } else if (user.equals("h")) {
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
                if (choice.length == 2 || choice[2] == 0) {
                    cardRemoved = 2;
                    acceptableMove = b.checkAnswer(choice[0], choice[1]);
                } else if (choice.length == 3) {
                    cardRemoved = 3;
                    acceptableMove = b.checkAnswer(choice[0], choice[1], choice[2]);

                } else {
                    gameState = -1;
                    break;
                }
                //System.out.println(b.getValidMove() + "-(" + b.getValidMove().getSize() + ")");
            } else {
                gameState = -1;
                System.out.println("No valid Moves");
                break;
            }

            if (acceptableMove) {
                String choosen = "";
                choosen += b.getNthCard(choice[0]).toString() + "  ";
                choosen += b.getNthCard(choice[1]).toString() + "  ";
                if (choice.length == 3) {
                    choosen += b.getNthCard(choice[2]).toString() + "  ";
                }
                if (b.getSize() > 0) {
                    if (choice.length == 3) {
                        choosen += "\tafter-->\t" + b.toString();
                    } else {
                        choosen += "\t\tafter-->\t" + b.toString();
                    }

                }
                //System.out.println(choosen);
                ReplayItem r = new ReplayItem(choosen);
                r.setData(choosen);
                replay.addNewReplay(r);

                if (b.getSize() == 2) {
                    b.clear();
                } else {
                    for (int i : choice) {
                        b.removeNthCard(i);
                    }
                }

                if (d.getSize() > 0) {
                    int toReplace = Math.min(d.getSize(), cardRemoved);

                    for (int i = 0; i < toReplace; i++) {
                        b.addNewEntry(d.removeFirstCard());
                    }
                }
                if (d.getSize() == 0 && b.getSize() == 0) {
                    gameState = 1;
                    break;
                }
                System.out.println("-----------------");
                System.out.println(b.representBoard());

            }

        }
        switch (gameState) {
            case -1:
                System.out.println("\n\"Game over man, GAME OVER!\" - Pvt. Hudson");
                System.out.println("Cards Left: " + d.getSize());

                break;
            case 1:
                System.out.println("\nHold my beer...... YOU WON\n\n");
                b = new Board();

        }
        //scan.nextLine();
        System.out.println("\nWould you like to see the replay? (y/n)");
        String choice = scan.next();
        if (choice.equals("y") || choice.equals("Y")) {
            String finalBoard = "Final Board:\t" + b.toString() + "\n";
            replay.addNewReplay(new ReplayItem(finalBoard));
            System.out.println("Action Replay!");
            System.out.println("Replay Size: " + replay.size);
            System.out.println(replay.toString());
            //System.out.println(replay.removeFirstCard());
        }
    }

    public static void showRules() {
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

    public static void main(String[] args) {
        App a = new App();

        try {
            a.selectMode(0);
        } catch (NumberFormatException e) {
            System.out.println("Invalid Output! Let's try that again");
            selectMode(0);
        }
    }
}
