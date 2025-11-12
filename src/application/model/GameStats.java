package application.model;

public class GameStats {
    private int score = 0;
    private int tries = 0;
    private int seconds = 0;

    public int getScore() { return score; }
    public int getTries() { return tries; }
    public int getSeconds() { return seconds; }

    public void addScore(int delta) { score = Math.max(0, score + delta); }
    public void incTries() { tries++; }
    public void tick() { seconds++; }

    public void reset() { score = 0; tries = 0; seconds = 0; }
}
