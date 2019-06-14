import java.util.*;

public class Hand {
    Map<Integer, Integer> pairs;
    private List<Card> hand;
    int matchCard;

    Hand() {
        hand  = new ArrayList<>();
        pairs = new HashMap<>();
    }

    void add(Card c) {
        if (hand.size() == 5) {
            throw new UnsupportedOperationException("Hand cannot have more than five cards");
        } else {
            hand.add(c);
        }
    }

    void fold() {
        hand.clear();
    }

    int evaluate() {
        if (5 > hand.size()) {
            throw new UnsupportedOperationException("Hand must contain five cards");
        }

        sortHand();

        int ranking = HandEvaluator.evaluate(this);

        switch (ranking) {
            case HandEvaluator.ROYAL_FLUSH:
                System.out.println("Royal Flush!");
                break;
            case HandEvaluator.STRAIGHT_FLUSH:
                System.out.println("Straight Flush!");
                break;
            case HandEvaluator.FOUR_KIND:
                System.out.println("Four of a Kind!");
                break;
            case HandEvaluator.FULL_HOUSE:
                System.out.println("Full House!");
                break;
            case HandEvaluator.FLUSH:
                System.out.println("Flush!");
                break;
            case HandEvaluator.STRAIGHT:
                System.out.println("Straight!");
                break;
            case HandEvaluator.THREE_KIND:
                System.out.println("Three of a Kind!");
                break;
            case HandEvaluator.TWO_PAIR:
                System.out.println("Two Pair!");
                break;
            case HandEvaluator.PAIR:
                System.out.println("Pair!");
                break;
            default:
                System.out.println("High Card!");
                break;
        }

        return ranking;
    }

    Card getCard(int cardPos) {
        return hand.get(cardPos);
    }

    int getHandSize() {
        return hand.size();
    }

    int getMatchCard() {
        return matchCard;
    }

    void setMatchCard(int kind) {
        matchCard = kind;
    }

    Iterator<Card> iterator() {
        return hand.iterator();
    }

    void sortHand() {
        Collections.sort(hand);

        if (hand.get(0).getRank() == 1) {
            if (hand.get(4).getRank() != 5) {
                for (Card card : hand) {
                    if (card.getRank() == 1) {
                        card.rank = 21;
                    }
                }
                Collections.sort(hand);
            }
        }
        System.out.println(hand);
    }

    public String toString() {
        StringBuilder handString = new StringBuilder();

        for (Card card : hand) {
            handString.append(card).append("\n");
        }

        return handString.toString();
    }
}
