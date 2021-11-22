public class CardTest {
    public static void main(String args[]) {
    Card card1 = new Card();
    Card card2 = new Card();
    System.out.println("Card is " + card1.toString());
    System.out.println("Card is " + card2.toString());

    if(card1.compareTo(card2) > 0 ){
        System.out.println("Card1 is bigger");
    }else if(card1.compareTo(card2) < 0 ) {
        System.out.println("Card2 is bigger");
    }else{
        System.out.println("They are equal");
    }
    }
}
