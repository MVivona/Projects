import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class HandEvaluatorTest {
    private Hand hand;

    @Before
    public void setUp() {
        hand = new Hand();
    }

    @Test
    public void isRoyalsReturnsTrueIfHandContainsAllFaceCardsTenAndAce() {
        hand.add(new Card(13,"Hearts"));
        hand.add(new Card(11,"Hearts"));
        hand.add(new Card(10,"Hearts"));
        hand.add(new Card(1,"Hearts"));
        hand.add(new Card(12, "Hearts"));

        hand.sortHand();

        assertTrue(HandEvaluator.isRoyals(hand));
    }

    @Test
    public void isFlushReturnsTrueIfHandContainsAllCardsInSameSuit() {
        hand.add(new Card(2,"Hearts"));
        hand.add(new Card(4,"Hearts"));
        hand.add(new Card(6,"Hearts"));
        hand.add(new Card(8,"Hearts"));
        hand.add(new Card(10,"Hearts"));

        assertTrue(HandEvaluator.isFlush(hand));
    }

    @Test
    public void isStraightReturnsTrueIfHandContainsCardsWithEachRankInSequence() {
        hand.add(new Card(6,"Hearts"));
        hand.add(new Card(3,"Clubs"));
        hand.add(new Card(5,"Diamonds"));
        hand.add(new Card(2,"Spades"));
        hand.add(new Card(4,"Spades"));

        hand.sortHand();

        assertTrue(HandEvaluator.isStraight(hand));
    }

    @Test
    public void HandEvaluatesRoyalFlush() {
        hand.add(new Card(13,"Hearts"));
        hand.add(new Card(11,"Hearts"));
        hand.add(new Card(10,"Hearts"));
        hand.add(new Card(1,"Hearts"));
        hand.add(new Card(12, "Hearts"));

        assertEquals(HandEvaluator.ROYAL_FLUSH, hand.evaluate());
    }

    @Test
    public void HandEvaluatesStraightFlush() {
        hand.add(new Card(3,"Hearts"));
        hand.add(new Card(5,"Hearts"));
        hand.add(new Card(2,"Hearts"));
        hand.add(new Card(6,"Hearts"));
        hand.add(new Card(4,"Hearts"));

        assertEquals(HandEvaluator.STRAIGHT_FLUSH, hand.evaluate());
    }


    @Test
    public void HandEvaluatesAceLowStraightFlush() {
        hand.add(new Card(5,"Diamonds"));
        hand.add(new Card(2,"Diamonds"));
        hand.add(new Card(4,"Diamonds"));
        hand.add(new Card(1,"Diamonds"));
        hand.add(new Card(3,"Diamonds"));

        assertEquals(HandEvaluator.STRAIGHT_FLUSH, hand.evaluate());
    }

    @Test
    public void HandEvaluatesFourOfAKind() {
        hand.add(new Card(1,"Hearts"));
        hand.add(new Card(1,"Clubs"));
        hand.add(new Card(2,"Diamonds"));
        hand.add(new Card(1,"Spades"));
        hand.add(new Card(1,"Hearts"));

        assertEquals(HandEvaluator.FOUR_KIND, hand.evaluate());
    }

    @Test
    public void HandEvaluatesFullHouse() {
        hand.add(new Card(11,"Hearts"));
        hand.add(new Card(7,"Clubs"));
        hand.add(new Card(11,"Diamonds"));
        hand.add(new Card(7,"Hearts"));
        hand.add(new Card(11,"Diamonds"));

        assertEquals(HandEvaluator.FULL_HOUSE, hand.evaluate());
    }

    @Test
    public void HandEvaluatesFlush() {
        hand.add(new Card(2,"Hearts"));
        hand.add(new Card(4,"Hearts"));
        hand.add(new Card(6,"Hearts"));
        hand.add(new Card(8,"Hearts"));
        hand.add(new Card(10,"Hearts"));

        assertEquals(HandEvaluator.FLUSH, hand.evaluate());
    }

    @Test
    public void HandEvaluatesAceHighStraight() {
        hand.add(new Card(10,"Hearts"));
        hand.add(new Card(11,"Clubs"));
        hand.add(new Card(12,"Diamonds"));
        hand.add(new Card(13,"Spades"));
        hand.add(new Card(1,"Hearts"));

        assertEquals(HandEvaluator.STRAIGHT, hand.evaluate());
    }

    @Test
    public void HandEvaluatesAceLowStraight() {
        hand.add(new Card(5,"Hearts"));
        hand.add(new Card(2,"Hearts"));
        hand.add(new Card(4,"Clubs"));
        hand.add(new Card(1,"Diamonds"));
        hand.add(new Card(3,"Spades"));

        assertEquals(HandEvaluator.STRAIGHT, hand.evaluate());
    }

    @Test
    public void HandEvaluatesStraight() {
        hand.add(new Card(6,"Hearts"));
        hand.add(new Card(3,"Clubs"));
        hand.add(new Card(5,"Diamonds"));
        hand.add(new Card(2,"Spades"));
        hand.add(new Card(4,"Spades"));

        assertEquals(HandEvaluator.STRAIGHT, hand.evaluate());
    }

    @Test
    public void HandEvaluatesThreeOfAKind() {
        hand.add(new Card(1,"Hearts"));
        hand.add(new Card(7,"Diamonds"));
        hand.add(new Card(1,"Clubs"));
        hand.add(new Card(2,"Hearts"));
        hand.add(new Card(1,"Diamonds"));

        assertEquals(HandEvaluator.THREE_KIND, hand.evaluate());
    }

    @Test
    public void HandEvaluatesTwoPair() {
        hand.add(new Card(1,"Hearts"));
        hand.add(new Card(10,"Hearts"));
        hand.add(new Card(7,"Diamonds"));
        hand.add(new Card(1,"Clubs"));
        hand.add(new Card(7,"Spades"));

        assertEquals(HandEvaluator.TWO_PAIR, hand.evaluate());
    }

    @Test
    public void HandEvaluatesPair() {
        hand.add(new Card(1,"Hearts"));
        hand.add(new Card(7,"Diamonds"));
        hand.add(new Card(10,"Hearts"));
        hand.add(new Card(8,"Spades"));
        hand.add(new Card(1,"Diamonds"));

        assertEquals(HandEvaluator.PAIR, hand.evaluate());
    }

    @Test
    public void HandEvaluatesHighCard() {
        hand.add(new Card(8,"Clubs"));
        hand.add(new Card(21,"Hearts"));
        hand.add(new Card(5,"Diamonds"));
        hand.add(new Card(13,"Hearts"));
        hand.add(new Card(3,"Spades"));

        assertEquals(HandEvaluator.HIGH_CARD, hand.evaluate());
    }

}