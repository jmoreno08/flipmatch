package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import application.model.Card;
import application.service.GameService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

class GameServiceTest {

    private GameService game;
    private Card cardA1, cardA2, cardB1, cardB2;

    @BeforeEach
    void setUp() {
        game = new GameService();
        cardA1 = new Card("A", "🍎");
        cardA2 = new Card("A", "🍎");
        cardB1 = new Card("B", "🍌");
        cardB2 = new Card("B", "🍌");
        List<Card> deck = List.of(cardA1, cardA2, cardB1, cardB2);
        game.newBoard(deck);
    }

    @Test
    void testNewBoardResetsState() {
        assertEquals(4, game.getBoard().size());
        assertFalse(game.isLocked());
        assertEquals(0, game.getStats().getScore());
        assertEquals(0, game.getStats().getTries());
    }

    @Test
    void testFlipValidCard() {
        boolean flipped = game.flip(cardA1);
        assertTrue(flipped);
        assertTrue(cardA1.isFaceUp());
    }

    @Test
    void testFlipSameCardTwiceFails() {
        game.flip(cardA1);
        boolean secondFlip = game.flip(cardA1);
        assertFalse(secondFlip);
    }

    @Test
    void testFlipMatchedCardFails() {
        cardA1.setMatched(true);
        boolean flipped = game.flip(cardA1);
        assertFalse(flipped);
    }

    @Test
    void testMatchingPairIncreasesScore() {
        game.flip(cardA1);
        game.flip(cardA2);
        assertTrue(cardA1.isMatched());
        assertTrue(cardA2.isMatched());
        assertEquals(100, game.getStats().getScore());
        assertFalse(game.isLocked());
    }

    @Test
    void testNonMatchingPairDecreasesScoreAndLocks() {
        game.flip(cardA1);
        game.flip(cardB1);
        assertFalse(cardA1.isMatched());
        assertFalse(cardB1.isMatched());
        assertEquals(0, game.getStats().getScore());
        assertTrue(game.isLocked());
    }

    @Test
    void testHideUnmatchedResetsCardsAndUnlocks() {
        game.flip(cardA1);
        game.flip(cardB1);
        game.hideUnmatched();
        assertFalse(cardA1.isFaceUp());
        assertFalse(cardB1.isFaceUp());
        assertFalse(game.isLocked());
    }

    @Test
    void testAllMatchedReturnsTrueWhenAllCardsMatched() {
        cardA1.setMatched(true);
        cardA2.setMatched(true);
        cardB1.setMatched(true);
        cardB2.setMatched(true);
        assertTrue(game.allMatched());
    }

    @Test
    void testAllMatchedReturnsFalseWhenSomeCardsUnmatched() {
        cardA1.setMatched(true);
        cardA2.setMatched(true);
        assertFalse(game.allMatched());
    }
}
