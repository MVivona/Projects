import java.util.*;

class HandEvaluator {

    static final int HIGH_CARD      = 0;
    static final int PAIR           = 1;
    static final int TWO_PAIR       = 2;
    static final int THREE_KIND     = 3;
    static final int STRAIGHT       = 5;
    static final int FLUSH          = 8;
    static final int FULL_HOUSE     = 13;
    static final int FOUR_KIND      = 21;
    static final int STRAIGHT_FLUSH = 34;
    static final int ROYAL_FLUSH    = 55;

    static int evaluate(Hand hand) {
        Map<Integer, Integer> handMap = new HashMap<>();

        if (isFlush(hand)) {
            if (isRoyals(hand)) {
                return ROYAL_FLUSH;
            } else if (isStraight(hand)){
                return STRAIGHT_FLUSH;
            } else {
                return FLUSH;
            }
        }

        if (isStraight(hand)) {
            return STRAIGHT;
        }

        Iterator<Card> hi = hand.iterator();

        while(hi.hasNext()) {
            Card card = hi.next();

            if (handMap.containsKey(card.getRank())) {
                handMap.put(card.getRank(), handMap.get(card.getRank()) + 1);
            } else {
                handMap.put(card.getRank(), 1);
            }
        }

        int pair = 0;
        for (Integer rank : handMap.keySet()) {
            if (handMap.get(rank) == 4) {
                hand.setMatchCard(rank);
                return FOUR_KIND;
            }

            if (handMap.get(rank) == 3) {
                hand.setMatchCard(rank);
                if (handMap.containsValue(2)) {
                    return FULL_HOUSE;
                } else {
                    return THREE_KIND;
                }
            }

            if (handMap.get(rank) == 2) {
                pair++;
                if (rank > hand.getMatchCard()) {
                    hand.setMatchCard(rank);
                }
                hand.pairs.put(pair, rank);
            }
        }
        if (hand.pairs.size() == 2) {
            return TWO_PAIR;
        } else if (hand.pairs.size() == 1) {
            return PAIR;
        }

        return HIGH_CARD;
    }

    static Boolean isRoyals(Hand hand) {
        int royals = 0;

        Iterator<Card> hi = hand.iterator();
        while(hi.hasNext()) {
            Card card = hi.next();
            int rank = card.getRank();
            if (rank >= 10) {
                royals++;
            }
        }
        return royals == 5;
    }

    static Boolean isFlush(Hand hand) {
        int flush = 0;
        String suit;
        String lastSuit = hand.getCard(0).getSuit();

        Iterator<Card> hi = hand.iterator();
        while(hi.hasNext()) {
            Card card = hi.next();
            suit = card.getSuit();

            if (suit.equals(lastSuit)) {
                flush++;
                lastSuit = suit;
            } else {
                break;
            }
        }
        return flush == 5;
    }

    static Boolean isStraight(Hand hand) {
        int rank = hand.getCard(0).getRank();
        int connectors = 1;

        for (int i=1; i<hand.getHandSize(); i++) {
            Card card = hand.getCard(i);

            if (card.getRank() == rank + 1) connectors++;
            rank = card.getRank();
        }

        if (connectors == 4 && hand.getCard(4).getRank() == 21) {
            if (hand.getCard(0).getRank() == 2 || hand.getCard(3).getRank() == 13) {
                connectors++;
            }
        }

        return connectors == 5;
    }

}
