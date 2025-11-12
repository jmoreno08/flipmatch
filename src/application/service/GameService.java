package application.service;

import application.model.Card;
import application.model.GameStats;

import java.util.ArrayList;
import java.util.List;

/**
 * Lógica central del juego: tablero, volteos y verificación de parejas.
 *
 * <p>Gestiona el flujo de turno simple (un jugador), bloqueo temporal en fallos,
 * y cálculo de puntuaciones/estadísticas.
 */
public class GameService {
    private final GameStats stats = new GameStats();
    private final List<Card> board = new ArrayList<>();

    private Card first = null;
    private Card second = null;
    private boolean locked = false;

    /** @return estadísticas de la partida. */
    public GameStats getStats() { return stats; }

    /** @return lista de cartas del tablero actual. */
    public List<Card> getBoard() { return board; }

    /** @return {@code true} si el tablero está bloqueado tras un fallo. */
    public boolean isLocked() { return locked; }

    /**
     * Crea un nuevo tablero con las cartas indicadas.
     *
     * @param cards cartas barajadas para tablero 4x4 (16)
     */
    public void newBoard(List<Card> cards) {
        board.clear();
        board.addAll(cards);
        stats.reset();
        first = null; second = null; locked = false;
    }

    /**
     * Intenta voltear una carta.
     *
     * @param c carta a voltear
     * @return {@code true} si la acción fue válida y la carta se volteó
     */
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

    /** Verifica si las dos cartas volteadas forman pareja y puntúa. */
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

    /** @return {@code true} si todas las cartas están emparejadas. */
    public boolean allMatched() {
        return board.stream().allMatch(Card::isMatched);
    }

    /** Vuelve a tapar las cartas no emparejadas tras el retraso visual. */
    public void hideUnmatched() {
        if (first != null && second != null && !first.isMatched()) {
            first.setFaceUp(false);
            second.setFaceUp(false);
        }
        first = null; second = null; locked = false;
    }
}

