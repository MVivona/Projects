import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

public class DeckTest {
    private Deck deck;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void setUp() {
        deck = new Deck();
    }

    @Test
    public void DeckHasFiftyTwoCards() {
        assertEquals(52, deck.getSize());
    }

    @Test
    public void DeckHasFiftyTwoDistinctCards() {
        Card card;
        int distinctCards = 0;
        while(deck.getSize() > 0) {
            card = deck.draw();

            if (!deck.hasCard(card.getRank(), card.getSuit())) {
                distinctCards++;
            }
        }
        assertEquals(52, distinctCards);
    }

    @Test
    public void ThisDeckEqualsThatDeck() {
        Deck thisDeck = new Deck();

        assertTrue(deck.equals(thisDeck));
    }

    @Test
    public void DeckIsShuffled() {
        Deck thisDeck = new Deck();

        deck.shuffle();
        assertFalse(deck.equals(thisDeck));
    }

    @Test
    public void DeckIsCut() {
        Deck thisDeck = new Deck();

        deck.cut();
        assertFalse(deck.equals(thisDeck));
    }

    @Test
    public void DeckSizeSameAfterShuffle() {
        deck.shuffle();
        assertEquals(52, deck.getSize());
    }

    @Test
    public void DeckSizeSameAfterCut() {
        deck.cut();
        assertEquals(52, deck.getSize());
    }

    @Test
    public void HasCardReturnsTrueIfDeckHasCheckedCard() {
        assertTrue(deck.hasCard(1,"Spades"));
    }

    @Test
    public void HasCardReturnsFalseIfDeckDoesNotHaveCheckedCard() {
        assertFalse(deck.hasCard(14, "Spades"));
    }

    @Test
    public void DrawnCardNoLongerInDeck(){
        Card card = deck.draw();

        assertFalse(deck.hasCard(card.getRank(), card.getSuit()));
    }
}