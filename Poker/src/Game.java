public class Game {
    private static Deck deck;
    private static Hand playerOne;
    private static Hand playerTwo;

    public static void main(String[] args) {
        deck = new Deck();
        for (int i=0; i<3; i++) {
            deck.shuffle();
        }
        deck.cut();

        dealHands();
        switch (winner(playerOne, playerTwo)) {
            case 0:
                System.out.println("Tie!");
                break;
            case 1:
                System.out.println("Player one wins!");
                playerTwo.fold();
                break;
            case 2:
                System.out.println("Player two wins!");
                playerOne.fold();
                break;
            default:
                System.out.println("Error");
                break;
        }
    }

    private static void dealHands() {
        playerOne = new Hand();
        playerTwo = new Hand();

        for(int i=0; i<10; i++) {
            if (i % 2 == 0) {
                playerOne.add(deck.draw());
            } else {
                playerTwo.add(deck.draw());
            }
        }
    }

    static int winner(Hand h1, Hand h2) {
        int playerOneRanking = h1.evaluate();
        int playerTwoRanking = h2.evaluate();

        if (playerOneRanking > playerTwoRanking) {
            return 1;
        } else if (playerTwoRanking > playerOneRanking) {
            return 2;
        } else {
            return tieBreaker(h1, h2, playerOneRanking);
        }
    }

    private static int tieBreaker(Hand h1, Hand h2, int tiedRanking) {
        if (tiedRanking == HandEvaluator.FOUR_KIND || tiedRanking == HandEvaluator.THREE_KIND || tiedRanking == HandEvaluator.PAIR) {
            if (h1.matchCard > h2.matchCard) {
                return 1;
            } else if (h2.matchCard > h1.matchCard) {
                return 2;
            } else {
                for (int i=4; i>=0; i--) {
                    if (h1.getCard(i).getRank() != h1.matchCard) {
                        if (h1.getCard(i).getRank() > h2.getCard(i).getRank()) {
                            return 1;
                        } else if (h2.getCard(i).getRank() > h1.getCard(i).getRank()) {
                            return 2;
                        }
                    }
                }
            }
        }

        if (tiedRanking == HandEvaluator.FULL_HOUSE) {
            if (h1.matchCard > h2.matchCard) {
                return 1;
            } else if (h2.matchCard > h1.matchCard) {
                return 2;
            } else {
                if (h1.pairs.get(1) > h2.pairs.get(1)) {
                    return 1;
                } else if (h2.pairs.get(1) > h1.pairs.get(1)) {
                    return 2;
                }
            }
        }

        if (tiedRanking == HandEvaluator.TWO_PAIR) {
            if (h1.matchCard > h2.matchCard) {
                return 1;
            } else if (h2.matchCard > h1.matchCard) {
                return 2;
            } else {
                int h1PairTwo;
                int h2PairTwo;

                if (h1.pairs.get(1) == h1.matchCard) {
                    h1PairTwo = h1.pairs.get(2);
                } else {
                    h1PairTwo = h1.pairs.get(1);
                }

                if (h2.pairs.get(1) == h2.matchCard) {
                    h2PairTwo = h2.pairs.get(2);
                } else {
                    h2PairTwo = h2.pairs.get(1);
                }

                if (h1PairTwo > h2PairTwo) {
                    return 1;
                } else if (h2PairTwo > h1PairTwo) {
                    return 2;
                } else {
                    for (int i=4; i>=0; i--) {
                        if (h1.getCard(i).getRank() != h1.matchCard && h1.getCard(i).getRank() != h1PairTwo) {
                            if (h1.getCard(i).getRank() > h2.getCard(i).getRank()) {
                                return 1;
                            } else if (h2.getCard(i).getRank() > h1.getCard(i).getRank()) {
                                return 2;
                            }
                        }
                    }
                }
            }
        }

        if (tiedRanking == HandEvaluator.FLUSH || tiedRanking == HandEvaluator.HIGH_CARD) {
            for (int i=4; i>=0; i--) {
                if (h1.getCard(i).getRank() > h2.getCard(i).getRank()) {
                    return 1;
                } else if (h2.getCard(i).getRank() > h1.getCard(i).getRank()) {
                    return 2;
                }
            }
        }

        if (tiedRanking == HandEvaluator.STRAIGHT_FLUSH || tiedRanking == HandEvaluator.STRAIGHT) {
            if (h1.getCard(4).getRank() > h2.getCard(4).getRank()) {
                return 1;
            } else if (h2.getCard(4).getRank() > h1.getCard(4).getRank()) {
                return 2;
            }
        }

        return 0;
    }
}
