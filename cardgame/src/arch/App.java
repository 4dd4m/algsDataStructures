package arch;
import arch.*;
import java.util.Scanner;

public class App {
    public static void selectMode(int pUser) {
        int user;
        System.out.println("CardGame\n-----------------");
        System.out.println("1. Play\n2. Demonstration Mode\n3. Rules\n4. Exit");
        if (pUser == 0) {
            Scanner scan = new Scanner(System.in);//this is for testing
            user = Integer.parseInt(scan.nextLine());
        }else{
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


    private int selectMenu(){
            int choice = 0;
            Scanner scan = new Scanner(System.in);
            return choice;
    }

    public void demonstrationMode(){}
    public void playMode(){}
    public static void showRules(){
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
    try{
        a.selectMode(0);
        }catch(NumberFormatException e){
        System.out.println("Invalid Output! Let's try that again");
            selectMode(0);
        }
    }
}
