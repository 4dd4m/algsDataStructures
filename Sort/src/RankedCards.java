public class RankedCards {


    public static void main2(String[] args) {
        Card myCards[] = new Card[10];
        Card tempCard;
        int biggestValue = -1, biggestPos = -1;

        for (int i = 0; i < 10; i++) {
            myCards[i] = new Card();

            if (myCards[i].getRankValue() > biggestValue){
                biggestValue = myCards[i].getRankValue();
                biggestPos = i;
            }
        }

        System.out.println("my cards are");
        for (Card c : myCards) {
            System.out.println(c.toString());
        }
        System.out.println("Biggest  Rank Value " + biggestValue);
        System.out.println("Biggest  Position Value " + biggestPos);

        tempCard = myCards[9];
        myCards[9] =myCards[biggestPos];
        myCards[biggestPos] = tempCard;

        System.out.println("After Swapping");
        for (Card c : myCards) {
            System.out.println(c.toString());
        }
    }
}
