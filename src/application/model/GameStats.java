package application.model;

/**
 * Estadísticas de una partida: puntaje, intentos y tiempo transcurrido.
 */
public class GameStats {
    private int score = 0;
    private int tries = 0;
    private int seconds = 0;

    /** @return puntaje actual. */
    public int getScore() { return score; }

    /** @return número de intentos realizados. */
    public int getTries() { return tries; }

    /** @return segundos transcurridos. */
    public int getSeconds() { return seconds; }

    /**
     * Modifica el puntaje sumando {@code delta}. Nunca baja de cero.
     * @param delta incremento/decremento de puntaje
     */
    public void addScore(int delta) { score = Math.max(0, score + delta); }

    /** Incrementa en 1 el contador de intentos. */
    public void incTries() { tries++; }

    /** Incrementa en 1 el contador de segundos. */
    public void tick() { seconds++; }

    /** Reinicia las estadísticas a cero. */
    public void reset() { score = 0; tries = 0; seconds = 0; }
}

