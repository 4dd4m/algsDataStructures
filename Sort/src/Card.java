import java.util.Random;

public class Card implements Comparable<Card>{
    private final int RANK, SUIT;
    private static final int[] RANKS = {1,2,3,4,5,6,7,8,9,10,11,12,13};
    private static final String[] SUITS = {"h","d","s","c"};
    private static final Random GENERATOR = new Random();


    public Card(){
    RANK = GENERATOR.nextInt(RANKS.length);
    SUIT = GENERATOR.nextInt(SUITS.length);
    }

    public Card(int r, int s){
    RANK = r;
    SUIT = s;
    }

    public int getRank(){
        return RANKS[RANK];
    }

    public int getRankValue(){
        return RANKS[RANK];
    }

    public String getSuit(){
        return SUITS[SUIT];
    }

    @Override
    public String toString(){
        return getRank() +  " of " + getSuit();
    }

    @Override
    public int compareTo(Card otherCard){
        if(this.getRankValue() > otherCard.getRankValue()) return 1;
        else if(this.getRankValue() < otherCard.getRankValue()) return -1;
        else return 0;
    }

}
