public class Card implements Comparable<Card> {
    int rank;
    private String suit;

    Card(int rank, String suit) {
        this.rank = rank;
        this.suit  = suit;
    }

    int getRank() {
        return rank;
    }

    String getSuit() {
        return suit;
    }

    public String toString() {
        String stringValue;

        switch (rank) {
            case 1:
                stringValue = "Ace";
                break;
            case 11:
                stringValue = "Jack";
                break;
            case 12:
                stringValue = "Queen";
                break;
            case 13:
                stringValue = "King";
                break;
            case 21:
                stringValue = "Ace";
                break;
            default:
                stringValue = Integer.toString(rank);
                break;
        }

        return stringValue + " of " + suit;
    }

    @Override
    public int compareTo(Card other) {
        return Integer.compare(this.getRank(), other.getRank());
    }
}
