package application.service;

import application.model.Card;
import application.model.GameStats;
import java.util.ArrayList;
import java.util.List;

public class GameService {
    private final GameStats stats = new GameStats();
    private final List<Card> board = new ArrayList<>();

    private Card first = null;
    private Card second = null;
    private boolean locked = false;

    public GameStats getStats() { return stats; }
    public List<Card> getBoard() { return board; }
    public boolean isLocked() { return locked; }

    public void newBoard(List<Card> cards) {
        board.clear();
        board.addAll(cards);
        stats.reset();
        first = null; second = null; locked = false;
    }

    public boolean flip(Card c) {
        if (locked || c.isMatched() || c.isFaceUp()) return false;
        c.setFaceUp(true);
        if (first == null) {
            first = c;
        } else if (second == null) {
            second = c;
            stats.incTries();
            checkMatch();
        }
        return true;
    }

    private void checkMatch() {
        if (first.getId().equals(second.getId())) {
            first.setMatched(true);
            second.setMatched(true);
            stats.addScore(+100);
            first = null; second = null;
        } else {
            stats.addScore(-10);
            locked = true; // la UI tapará y desbloqueará
        }
    }

    public boolean allMatched() {
        return board.stream().allMatch(Card::isMatched);
    }

    public void hideUnmatched() {
        if (first != null && second != null && !first.isMatched()) {
            first.setFaceUp(false);
            second.setFaceUp(false);
        }
        first = null; second = null; locked = false;
    }
}
