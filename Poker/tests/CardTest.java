import org.junit.Test;

import static org.junit.Assert.*;

public class CardTest {

    private Card thisCard;
    private Card otherCard;

    @Test
    public void CardCompareToCardOfGreaterRankReturnsNegativeOne() {
        thisCard  = new Card(5, "Hearts");
        otherCard = new Card(10, "Hearts");

        assertEquals(-1, thisCard.compareTo(otherCard));
    }

    @Test
    public void CardCompareToCardOfEqualRankReturnsZero() {
        thisCard  = new Card(10, "Hearts");
        otherCard = new Card(10, "Hearts");

        assertEquals(0, thisCard.compareTo(otherCard));
    }

    @Test
    public void CardCompareToCardOfLesserRankReturnsOne() {
        thisCard  = new Card(10, "Hearts");
        otherCard = new Card(5, "Hearts");

        assertEquals(1, thisCard.compareTo(otherCard));
    }

    @Test
    public void ToStringCardRankOneReturnsAceString() {
        thisCard  = new Card(1, "Hearts");

        assertEquals("Ace of Hearts", thisCard.toString());
    }

    @Test
    public void ToStringCardRankTwentyOneReturnsAceString() {
        thisCard  = new Card(21, "Hearts");

        assertEquals("Ace of Hearts", thisCard.toString());
    }

    @Test
    public void ToStringCardRankElevenReturnsJackString() {
        thisCard  = new Card(11, "Hearts");

        assertEquals("Jack of Hearts", thisCard.toString());
    }

    @Test
    public void ToStringCardRankTwelveReturnsQueenString() {
        thisCard  = new Card(12, "Hearts");

        assertEquals("Queen of Hearts", thisCard.toString());
    }

    @Test
    public void ToStringCardRankThirteenReturnsKingString() {
        thisCard  = new Card(13, "Hearts");

        assertEquals("King of Hearts", thisCard.toString());
    }

}