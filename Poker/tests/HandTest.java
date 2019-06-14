import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

public class HandTest {
    private Hand hand;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void setUp() {
        hand = new Hand();
    }

    @Test
    public void FoldHandRemovesAllCardsInHand() {
        hand.add(new Card(13,"Hearts"));
        hand.add(new Card(11,"Hearts"));
        hand.add(new Card(10,"Hearts"));
        hand.add(new Card(1,"Hearts"));
        hand.add(new Card(12, "Hearts"));

        hand.fold();

        assertEquals(0, hand.getHandSize());
    }

    @Test
    public void AddMoreThanFiveCardsThrowsException() {
        thrown.expect(UnsupportedOperationException.class);
        thrown.expectMessage("Hand cannot have more than five cards");

        hand.add(new Card(1,"Hearts"));
        hand.add(new Card(2,"Hearts"));
        hand.add(new Card(10,"Hearts"));
        hand.add(new Card(11,"Hearts"));
        hand.add(new Card(12,"Hearts"));
        hand.add(new Card(13,"Hearts"));
    }

    @Test
    public void EvaluateFewerThanFiveCardsErrorsOut() {
        thrown.expect(UnsupportedOperationException.class);
        thrown.expectMessage("Hand must contain five cards");

        hand.evaluate();
    }
}