import java.util.*;

public class Deck {
    private List<Card> deck;

    Deck() {
        deck = new ArrayList<>();
        String suit = "";

        for (int i=0; i<52; i++) {
            switch (i) {
                case 0:
                    suit = "Spades";
                    break;
                case 13:
                    suit = "Hearts";
                    break;
                case 26:
                    suit = "Clubs";
                    break;
                case 39:
                    suit = "Diamonds";
                    break;
                default:
                    break;
            }
            deck.add(new Card((i%13) + 1, suit));
        }
    }

    void shuffle() {
        Random random = new Random();

        for (int i=0; i < deck.size(); i++) {
            Card card = deck.get(i);
            int swap = i + random.nextInt(deck.size() - i);
            deck.set(i, deck.get(swap));
            deck.set(swap, card);
        }
    }

    void cut() {
        List<Card> cutDeck = new ArrayList<>();
        while (deck.size() > 26) {
            cutDeck.add(deck.remove(0));
        }
        deck.addAll(cutDeck);
    }

    public String toString() {
        StringBuilder deckString = new StringBuilder();

        for (Card card : deck) {
            deckString.append(card).append("\n");
        }

        return deckString.toString();
    }

    Card draw() {
        return deck.remove(deck.size()-1);
    }

    int getSize() {
        return deck.size();
    }

    boolean hasCard(int rank, String suit) {
        for (Card card : deck) {
            if (card.getRank() == rank && card.getSuit().equals(suit)) {
                return true;
            }
        }

        return false;
    }

    boolean equals(Deck otherDeck) {
        String thisCard;
        String otherCard;
        boolean equality = false;
        for (int i=0; i < deck.size(); i++) {
            thisCard  = deck.get(i).getRank() + " " + deck.get(i).getSuit();
            otherCard = otherDeck.deck.get(i).getRank() + " " + otherDeck.deck.get(i).getSuit();

            if (!thisCard.equals(otherCard)) {
                equality = false;
                break;
            } else {
                equality = true;
            }
        }

        return equality;
    }
}
