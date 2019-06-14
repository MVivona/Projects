import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class GameTest {
    private Hand playerOne;
    private Hand playerTwo;

    @Before
    public void setUp() {
        playerOne = new Hand();
        playerTwo = new Hand();
    }

    @Test
    public void RoyalFlushBeatsStandardStraightFlush() {
        playerOne.add(new Card(13,"Hearts"));
        playerOne.add(new Card(11,"Hearts"));
        playerOne.add(new Card(10,"Hearts"));
        playerOne.add(new Card(1,"Hearts"));
        playerOne.add(new Card(12, "Hearts"));

        playerTwo.add(new Card(10,"Diamonds"));
        playerTwo.add(new Card(9,"Diamonds"));
        playerTwo.add(new Card(8,"Diamonds"));
        playerTwo.add(new Card(7,"Diamonds"));
        playerTwo.add(new Card(6, "Diamonds"));

        assertEquals(1, Game.winner(playerOne, playerTwo));
    }

    @Test
    public void StraightFlushBeatsFourOfAKind() {
        playerOne.add(new Card(12, "Hearts"));
        playerOne.add(new Card(12, "Spades"));
        playerOne.add(new Card(12, "Diamonds"));
        playerOne.add(new Card(12, "Clubs"));
        playerOne.add(new Card(13, "Hearts"));

        playerTwo.add(new Card(10,"Diamonds"));
        playerTwo.add(new Card(9,"Diamonds"));
        playerTwo.add(new Card(8,"Diamonds"));
        playerTwo.add(new Card(7,"Diamonds"));
        playerTwo.add(new Card(6, "Diamonds"));

        assertEquals(2, Game.winner(playerOne, playerTwo));
    }

    @Test
    public void FourOfAKindBeatsFullHouse() {
        playerOne.add(new Card(12, "Hearts"));
        playerOne.add(new Card(12, "Spades"));
        playerOne.add(new Card(12, "Diamonds"));
        playerOne.add(new Card(12, "Clubs"));
        playerOne.add(new Card(13, "Hearts"));

        playerTwo.add(new Card(10, "Hearts"));
        playerTwo.add(new Card(10, "Spades"));
        playerTwo.add(new Card(10, "Diamonds"));
        playerTwo.add(new Card(11, "Clubs"));
        playerTwo.add(new Card(11, "Spades"));

        assertEquals(1, Game.winner(playerOne, playerTwo));
    }

    @Test
    public void FullHouseBeatsFlush() {
        playerOne.add(new Card(8,"Hearts"));
        playerOne.add(new Card(4,"Hearts"));
        playerOne.add(new Card(10,"Hearts"));
        playerOne.add(new Card(3,"Hearts"));
        playerOne.add(new Card(12, "Hearts"));

        playerTwo.add(new Card(10, "Hearts"));
        playerTwo.add(new Card(10, "Spades"));
        playerTwo.add(new Card(10, "Diamonds"));
        playerTwo.add(new Card(11, "Clubs"));
        playerTwo.add(new Card(11, "Spades"));

        assertEquals(2, Game.winner(playerOne, playerTwo));
    }

    @Test
    public void FlushBeatsStraight() {
        playerOne.add(new Card(8,"Hearts"));
        playerOne.add(new Card(4,"Hearts"));
        playerOne.add(new Card(10,"Hearts"));
        playerOne.add(new Card(3,"Hearts"));
        playerOne.add(new Card(12, "Hearts"));

        playerTwo.add(new Card(2, "Hearts"));
        playerTwo.add(new Card(3, "Spades"));
        playerTwo.add(new Card(4, "Diamonds"));
        playerTwo.add(new Card(5, "Clubs"));
        playerTwo.add(new Card(6, "Spades"));

        assertEquals(1, Game.winner(playerOne, playerTwo));
    }

    @Test
    public void StraightBeatsThreeOfAKind() {
        playerOne.add(new Card(12, "Hearts"));
        playerOne.add(new Card(12, "Spades"));
        playerOne.add(new Card(12, "Diamonds"));
        playerOne.add(new Card(10, "Clubs"));
        playerOne.add(new Card(13, "Hearts"));

        playerTwo.add(new Card(2, "Hearts"));
        playerTwo.add(new Card(3, "Spades"));
        playerTwo.add(new Card(4, "Diamonds"));
        playerTwo.add(new Card(5, "Clubs"));
        playerTwo.add(new Card(6, "Spades"));

        assertEquals(2, Game.winner(playerOne, playerTwo));
    }

    @Test
    public void ThreeOfAKindBeatsTwoPair() {
        playerOne.add(new Card(12, "Hearts"));
        playerOne.add(new Card(12, "Spades"));
        playerOne.add(new Card(12, "Diamonds"));
        playerOne.add(new Card(10, "Clubs"));
        playerOne.add(new Card(13, "Hearts"));

        playerTwo.add(new Card(2, "Hearts"));
        playerTwo.add(new Card(2, "Spades"));
        playerTwo.add(new Card(4, "Diamonds"));
        playerTwo.add(new Card(4, "Clubs"));
        playerTwo.add(new Card(6, "Spades"));

        assertEquals(1, Game.winner(playerOne, playerTwo));
    }

    @Test
    public void TwoPairBeatsPair() {
        playerOne.add(new Card(12, "Hearts"));
        playerOne.add(new Card(12, "Spades"));
        playerOne.add(new Card(11, "Diamonds"));
        playerOne.add(new Card(10, "Clubs"));
        playerOne.add(new Card(13, "Hearts"));

        playerTwo.add(new Card(2, "Hearts"));
        playerTwo.add(new Card(2, "Spades"));
        playerTwo.add(new Card(4, "Diamonds"));
        playerTwo.add(new Card(4, "Clubs"));
        playerTwo.add(new Card(6, "Spades"));

        assertEquals(2, Game.winner(playerOne, playerTwo));
    }

    @Test
    public void PairBeatsHighCard() {
        playerOne.add(new Card(2, "Hearts"));
        playerOne.add(new Card(2, "Spades"));
        playerOne.add(new Card(4, "Diamonds"));
        playerOne.add(new Card(7, "Clubs"));
        playerOne.add(new Card(3, "Hearts"));

        playerTwo.add(new Card(1, "Hearts"));
        playerTwo.add(new Card(2, "Spades"));
        playerTwo.add(new Card(4, "Diamonds"));
        playerTwo.add(new Card(5, "Clubs"));
        playerTwo.add(new Card(6, "Spades"));

        assertEquals(1, Game.winner(playerOne, playerTwo));
    }


    @Test
    public void RoyalFlushTiesRoyalFlush() {
        playerOne.add(new Card(13,"Hearts"));
        playerOne.add(new Card(11,"Hearts"));
        playerOne.add(new Card(10,"Hearts"));
        playerOne.add(new Card(1,"Hearts"));
        playerOne.add(new Card(12, "Hearts"));

        playerTwo.add(new Card(13,"Diamonds"));
        playerTwo.add(new Card(11,"Diamonds"));
        playerTwo.add(new Card(10,"Diamonds"));
        playerTwo.add(new Card(1,"Diamonds"));
        playerTwo.add(new Card(12, "Diamonds"));

        assertEquals(0, Game.winner(playerOne, playerTwo));
    }

    @Test
    public void TopCardBreaksStraightFlushTie() {
        playerOne.add(new Card(12, "Hearts"));
        playerOne.add(new Card(11, "Hearts"));
        playerOne.add(new Card(10, "Hearts"));
        playerOne.add(new Card(9, "Hearts"));
        playerOne.add(new Card(8, "Hearts"));

        playerTwo.add(new Card(7, "Hearts"));
        playerTwo.add(new Card(6, "Hearts"));
        playerTwo.add(new Card(5, "Hearts"));
        playerTwo.add(new Card(4, "Hearts"));
        playerTwo.add(new Card(3, "Hearts"));

        assertEquals(1, Game.winner(playerOne, playerTwo));
    }

    @Test
    public void SameTopCardIsTiedStraightFlush() {
        playerOne.add(new Card(12, "Hearts"));
        playerOne.add(new Card(11, "Hearts"));
        playerOne.add(new Card(10, "Hearts"));
        playerOne.add(new Card(9, "Hearts"));
        playerOne.add(new Card(8, "Hearts"));

        playerTwo.add(new Card(12, "Spades"));
        playerTwo.add(new Card(11, "Spades"));
        playerTwo.add(new Card(10, "Spades"));
        playerTwo.add(new Card(9, "Spades"));
        playerTwo.add(new Card(8, "Spades"));

        assertEquals(0, Game.winner(playerOne, playerTwo));
    }

    @Test
    public void HighMatchingCardBreaksFourOfAKindTie() {
        playerOne.add(new Card(12, "Hearts"));
        playerOne.add(new Card(12, "Spades"));
        playerOne.add(new Card(12, "Diamonds"));
        playerOne.add(new Card(12, "Clubs"));
        playerOne.add(new Card(13, "Hearts"));

        playerTwo.add(new Card(10, "Hearts"));
        playerTwo.add(new Card(10, "Spades"));
        playerTwo.add(new Card(10, "Diamonds"));
        playerTwo.add(new Card(10, "Clubs"));
        playerTwo.add(new Card(8, "Spades"));

        assertEquals(1, Game.winner(playerOne, playerTwo));
    }

    @Test
    public void KickerCardBreaksFourOfAKindTie() {
        playerOne.add(new Card(12, "Hearts"));
        playerOne.add(new Card(12, "Spades"));
        playerOne.add(new Card(12, "Diamonds"));
        playerOne.add(new Card(12, "Clubs"));
        playerOne.add(new Card(13, "Hearts"));

        playerTwo.add(new Card(12, "Hearts"));
        playerTwo.add(new Card(12, "Spades"));
        playerTwo.add(new Card(12, "Diamonds"));
        playerTwo.add(new Card(12, "Clubs"));
        playerTwo.add(new Card(10, "Spades"));

        assertEquals(1, Game.winner(playerOne, playerTwo));
    }

    @Test
    public void HighThreeOfAKindBreaksFullHouseTie() {
        playerOne.add(new Card(12, "Hearts"));
        playerOne.add(new Card(12, "Spades"));
        playerOne.add(new Card(12, "Diamonds"));
        playerOne.add(new Card(7, "Clubs"));
        playerOne.add(new Card(7, "Hearts"));

        playerTwo.add(new Card(10, "Hearts"));
        playerTwo.add(new Card(10, "Spades"));
        playerTwo.add(new Card(10, "Diamonds"));
        playerTwo.add(new Card(11, "Clubs"));
        playerTwo.add(new Card(11, "Spades"));

        assertEquals(1, Game.winner(playerOne, playerTwo));
    }

    @Test
    public void HigherPairBreaksFullHouseTie() {
        playerOne.add(new Card(12, "Hearts"));
        playerOne.add(new Card(12, "Spades"));
        playerOne.add(new Card(12, "Diamonds"));
        playerOne.add(new Card(7, "Clubs"));
        playerOne.add(new Card(7, "Hearts"));

        playerTwo.add(new Card(12, "Clubs"));
        playerTwo.add(new Card(12, "Spades"));
        playerTwo.add(new Card(12, "Diamonds"));
        playerTwo.add(new Card(11, "Clubs"));
        playerTwo.add(new Card(11, "Spades"));

        assertEquals(2, Game.winner(playerOne, playerTwo));
    }

    @Test
    public void HighCardBreaksFlushTie() {
        playerOne.add(new Card(12, "Hearts"));
        playerOne.add(new Card(5, "Hearts"));
        playerOne.add(new Card(3, "Hearts"));
        playerOne.add(new Card(10, "Hearts"));
        playerOne.add(new Card(7, "Hearts"));

        playerTwo.add(new Card(2, "Spades"));
        playerTwo.add(new Card(4, "Spades"));
        playerTwo.add(new Card(6, "Spades"));
        playerTwo.add(new Card(8, "Spades"));
        playerTwo.add(new Card(10, "Spades"));

        assertEquals(1, Game.winner(playerOne, playerTwo));
    }

    @Test
    public void SecondHighCardBreaksFlushTie() {
        playerOne.add(new Card(12, "Hearts"));
        playerOne.add(new Card(5, "Hearts"));
        playerOne.add(new Card(3, "Hearts"));
        playerOne.add(new Card(10, "Hearts"));
        playerOne.add(new Card(7, "Hearts"));

        playerTwo.add(new Card(12, "Spades"));
        playerTwo.add(new Card(4, "Spades"));
        playerTwo.add(new Card(6, "Spades"));
        playerTwo.add(new Card(8, "Spades"));
        playerTwo.add(new Card(11, "Spades"));

        assertEquals(2, Game.winner(playerOne, playerTwo));
    }

    @Test
    public void ThirdHighCardBreaksFlushTie() {
        playerOne.add(new Card(12, "Hearts"));
        playerOne.add(new Card(5, "Hearts"));
        playerOne.add(new Card(3, "Hearts"));
        playerOne.add(new Card(10, "Hearts"));
        playerOne.add(new Card(11, "Hearts"));

        playerTwo.add(new Card(12, "Spades"));
        playerTwo.add(new Card(4, "Spades"));
        playerTwo.add(new Card(6, "Spades"));
        playerTwo.add(new Card(8, "Spades"));
        playerTwo.add(new Card(11, "Spades"));

        assertEquals(1, Game.winner(playerOne, playerTwo));
    }

    @Test
    public void FourthHighCardBreaksFlushTie() {
        playerOne.add(new Card(12, "Hearts"));
        playerOne.add(new Card(5, "Hearts"));
        playerOne.add(new Card(3, "Hearts"));
        playerOne.add(new Card(10, "Hearts"));
        playerOne.add(new Card(11, "Hearts"));

        playerTwo.add(new Card(12, "Spades"));
        playerTwo.add(new Card(10, "Spades"));
        playerTwo.add(new Card(6, "Spades"));
        playerTwo.add(new Card(8, "Spades"));
        playerTwo.add(new Card(11, "Spades"));

        assertEquals(2, Game.winner(playerOne, playerTwo));
    }

    @Test
    public void FifthHighCardBreaksFlushTie() {
        playerOne.add(new Card(12, "Hearts"));
        playerOne.add(new Card(9, "Hearts"));
        playerOne.add(new Card(6, "Hearts"));
        playerOne.add(new Card(10, "Hearts"));
        playerOne.add(new Card(11, "Hearts"));

        playerTwo.add(new Card(12, "Spades"));
        playerTwo.add(new Card(10, "Spades"));
        playerTwo.add(new Card(3, "Spades"));
        playerTwo.add(new Card(9, "Spades"));
        playerTwo.add(new Card(11, "Spades"));

        assertEquals(1, Game.winner(playerOne, playerTwo));
    }

    @Test
    public void HighCardBreaksStraightTie() {
        playerOne.add(new Card(5, "Hearts"));
        playerOne.add(new Card(1, "Spades"));
        playerOne.add(new Card(3, "Clubs"));
        playerOne.add(new Card(4, "Diamonds"));
        playerOne.add(new Card(2, "Spades"));

        playerTwo.add(new Card(2, "Hearts"));
        playerTwo.add(new Card(5, "Spades"));
        playerTwo.add(new Card(3, "Diamonds"));
        playerTwo.add(new Card(6, "Hearts"));
        playerTwo.add(new Card(4, "Clubs"));

        assertEquals(2, Game.winner(playerOne, playerTwo));
    }

    @Test
    public void SameHighCardIsTiedStraight() {
        playerOne.add(new Card(10, "Hearts"));
        playerOne.add(new Card(6, "Spades"));
        playerOne.add(new Card(8, "Clubs"));
        playerOne.add(new Card(9, "Diamonds"));
        playerOne.add(new Card(7, "Spades"));

        playerTwo.add(new Card(9, "Hearts"));
        playerTwo.add(new Card(6, "Spades"));
        playerTwo.add(new Card(10, "Diamonds"));
        playerTwo.add(new Card(8, "Hearts"));
        playerTwo.add(new Card(7, "Clubs"));

        assertEquals(0, Game.winner(playerOne, playerTwo));
    }

    @Test
    public void HighCardBreaksThreeOfAKindTie() {
        playerOne.add(new Card(10, "Hearts"));
        playerOne.add(new Card(10, "Clubs"));
        playerOne.add(new Card(10, "Spades"));
        playerOne.add(new Card(8, "Spades"));
        playerOne.add(new Card(3, "Diamonds"));

        playerTwo.add(new Card(11, "Hearts"));
        playerTwo.add(new Card(11, "Clubs"));
        playerTwo.add(new Card(11, "Spades"));
        playerTwo.add(new Card(4, "Spades"));
        playerTwo.add(new Card(7, "Diamonds"));

        assertEquals(2, Game.winner(playerOne, playerTwo));
    }

    @Test
    public void FirstKickerBreaksThreeOfAKindTie() {
        playerOne.add(new Card(10, "Hearts"));
        playerOne.add(new Card(10, "Clubs"));
        playerOne.add(new Card(10, "Spades"));
        playerOne.add(new Card(8, "Spades"));
        playerOne.add(new Card(3, "Diamonds"));

        playerTwo.add(new Card(10, "Hearts"));
        playerTwo.add(new Card(10, "Clubs"));
        playerTwo.add(new Card(10, "Diamonds"));
        playerTwo.add(new Card(4, "Spades"));
        playerTwo.add(new Card(7, "Diamonds"));

        assertEquals(1, Game.winner(playerOne, playerTwo));
    }

    @Test
    public void SecondKickerBreaksThreeOfAKindTie() {
        playerOne.add(new Card(10, "Hearts"));
        playerOne.add(new Card(10, "Clubs"));
        playerOne.add(new Card(10, "Spades"));
        playerOne.add(new Card(8, "Spades"));
        playerOne.add(new Card(3, "Diamonds"));

        playerTwo.add(new Card(10, "Hearts"));
        playerTwo.add(new Card(10, "Clubs"));
        playerTwo.add(new Card(10, "Diamonds"));
        playerTwo.add(new Card(4, "Spades"));
        playerTwo.add(new Card(8, "Diamonds"));

        assertEquals(2, Game.winner(playerOne, playerTwo));
    }

    @Test
    public void HighestPairBreaksTwoPairTie() {
        playerOne.add(new Card(10, "Diamonds"));
        playerOne.add(new Card(10, "Clubs"));
        playerOne.add(new Card(2, "Clubs"));
        playerOne.add(new Card(2, "Hearts"));
        playerOne.add(new Card(8, "Spades"));

        playerTwo.add(new Card(9,"Spades"));
        playerTwo.add(new Card(9,"Hearts"));
        playerTwo.add(new Card(4,"Hearts"));
        playerTwo.add(new Card(4,"Clubs"));
        playerTwo.add(new Card(11,"Diamonds"));

        assertEquals(1, Game.winner(playerOne, playerTwo));
    }

    @Test
    public void SecondHighestPairBreaksTwoPairTie() {
        playerOne.add(new Card(10, "Diamonds"));
        playerOne.add(new Card(10, "Clubs"));
        playerOne.add(new Card(2, "Clubs"));
        playerOne.add(new Card(2, "Hearts"));
        playerOne.add(new Card(4, "Spades"));

        playerTwo.add(new Card(10,"Spades"));
        playerTwo.add(new Card(10,"Hearts"));
        playerTwo.add(new Card(4,"Hearts"));
        playerTwo.add(new Card(4,"Clubs"));
        playerTwo.add(new Card(2,"Diamonds"));

        assertEquals(2, Game.winner(playerOne, playerTwo));
    }

    @Test
    public void KickerBreaksTwoPairTie() {
        playerOne.add(new Card(10, "Diamonds"));
        playerOne.add(new Card(10, "Clubs"));
        playerOne.add(new Card(5, "Clubs"));
        playerOne.add(new Card(5, "Hearts"));
        playerOne.add(new Card(4, "Spades"));

        playerTwo.add(new Card(10,"Spades"));
        playerTwo.add(new Card(10,"Hearts"));
        playerTwo.add(new Card(5,"Hearts"));
        playerTwo.add(new Card(5,"Clubs"));
        playerTwo.add(new Card(2,"Diamonds"));

        assertEquals(1, Game.winner(playerOne, playerTwo));
    }

    @Test
    public void HigherPairBreaksPairTie() {
        playerOne.add(new Card(3, "Spades"));
        playerOne.add(new Card(5, "Clubs"));
        playerOne.add(new Card(2, "Hearts"));
        playerOne.add(new Card(8, "Spades"));
        playerOne.add(new Card(2, "Diamonds"));

        playerTwo.add(new Card(2, "Spades"));
        playerTwo.add(new Card(6, "Clubs"));
        playerTwo.add(new Card(1, "Hearts"));
        playerTwo.add(new Card(7, "Spades"));
        playerTwo.add(new Card(1, "Diamonds"));

        assertEquals(2, Game.winner(playerOne, playerTwo));
    }

    @Test
    public void KickerBreaksPairTie() {
        playerOne.add(new Card(3, "Spades"));
        playerOne.add(new Card(5, "Clubs"));
        playerOne.add(new Card(1, "Hearts"));
        playerOne.add(new Card(8, "Spades"));
        playerOne.add(new Card(1, "Diamonds"));

        playerTwo.add(new Card(2, "Spades"));
        playerTwo.add(new Card(6, "Hearts"));
        playerTwo.add(new Card(1, "Clubs"));
        playerTwo.add(new Card(7, "Clubs"));
        playerTwo.add(new Card(1, "Spades"));

        assertEquals(1, Game.winner(playerOne, playerTwo));
    }

    @Test
    public void SecondKickerBreaksPairTie() {
        playerOne.add(new Card(3, "Spades"));
        playerOne.add(new Card(5, "Clubs"));
        playerOne.add(new Card(1, "Hearts"));
        playerOne.add(new Card(8, "Spades"));
        playerOne.add(new Card(1, "Diamonds"));

        playerTwo.add(new Card(2, "Spades"));
        playerTwo.add(new Card(6, "Hearts"));
        playerTwo.add(new Card(1, "Clubs"));
        playerTwo.add(new Card(8, "Clubs"));
        playerTwo.add(new Card(1, "Spades"));

        assertEquals(2, Game.winner(playerOne, playerTwo));
    }

    @Test
    public void ThirdKickerBreaksPairTie() {
        playerOne.add(new Card(3, "Spades"));
        playerOne.add(new Card(6, "Clubs"));
        playerOne.add(new Card(1, "Hearts"));
        playerOne.add(new Card(8, "Spades"));
        playerOne.add(new Card(1, "Diamonds"));

        playerTwo.add(new Card(2, "Spades"));
        playerTwo.add(new Card(6, "Hearts"));
        playerTwo.add(new Card(1, "Clubs"));
        playerTwo.add(new Card(8, "Clubs"));
        playerTwo.add(new Card(1, "Spades"));

        assertEquals(1, Game.winner(playerOne, playerTwo));
    }

    @Test
    public void TopCardBreaksHighCardTie() {
        playerOne.add(new Card(2, "Spades"));
        playerOne.add(new Card(6, "Clubs"));
        playerOne.add(new Card(13, "Hearts"));
        playerOne.add(new Card(4, "Spades"));
        playerOne.add(new Card(8, "Diamonds"));

        playerTwo.add(new Card(7, "Spades"));
        playerTwo.add(new Card(3, "Hearts"));
        playerTwo.add(new Card(5, "Clubs"));
        playerTwo.add(new Card(12, "Clubs"));
        playerTwo.add(new Card(9, "Spades"));

        assertEquals(1,  Game.winner(playerOne, playerTwo));
    }

    @Test
    public void KickerBreaksHighCardTie() {
        playerOne.add(new Card(2, "Spades"));
        playerOne.add(new Card(6, "Clubs"));
        playerOne.add(new Card(12, "Hearts"));
        playerOne.add(new Card(4, "Spades"));
        playerOne.add(new Card(8, "Diamonds"));

        playerTwo.add(new Card(7, "Spades"));
        playerTwo.add(new Card(3, "Hearts"));
        playerTwo.add(new Card(5, "Clubs"));
        playerTwo.add(new Card(12, "Clubs"));
        playerTwo.add(new Card(9, "Spades"));

        assertEquals(2,  Game.winner(playerOne, playerTwo));
    }

    @Test
    public void SecondKickerBreaksHighCardTie() {
        playerOne.add(new Card(2, "Spades"));
        playerOne.add(new Card(9, "Clubs"));
        playerOne.add(new Card(12, "Hearts"));
        playerOne.add(new Card(4, "Spades"));
        playerOne.add(new Card(8, "Diamonds"));

        playerTwo.add(new Card(7, "Spades"));
        playerTwo.add(new Card(3, "Hearts"));
        playerTwo.add(new Card(5, "Clubs"));
        playerTwo.add(new Card(12, "Clubs"));
        playerTwo.add(new Card(9, "Spades"));

        assertEquals(1,  Game.winner(playerOne, playerTwo));
    }

    @Test
    public void ThirdKickerBreaksHighCardTie() {
        playerOne.add(new Card(2, "Spades"));
        playerOne.add(new Card(9, "Clubs"));
        playerOne.add(new Card(12, "Hearts"));
        playerOne.add(new Card(4, "Spades"));
        playerOne.add(new Card(8, "Diamonds"));

        playerTwo.add(new Card(8, "Spades"));
        playerTwo.add(new Card(3, "Hearts"));
        playerTwo.add(new Card(5, "Clubs"));
        playerTwo.add(new Card(12, "Clubs"));
        playerTwo.add(new Card(9, "Spades"));

        assertEquals(2,  Game.winner(playerOne, playerTwo));
    }

    @Test
    public void FourthKickerBreaksHighCardTie() {
        playerOne.add(new Card(4, "Spades"));
        playerOne.add(new Card(9, "Clubs"));
        playerOne.add(new Card(12, "Hearts"));
        playerOne.add(new Card(5, "Spades"));
        playerOne.add(new Card(8, "Diamonds"));

        playerTwo.add(new Card(8, "Spades"));
        playerTwo.add(new Card(3, "Hearts"));
        playerTwo.add(new Card(5, "Clubs"));
        playerTwo.add(new Card(12, "Clubs"));
        playerTwo.add(new Card(9, "Spades"));

        assertEquals(1,  Game.winner(playerOne, playerTwo));
    }
}