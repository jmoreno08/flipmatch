package application.model;

public class Card {
    private final String id;     // mismo id para las dos cartas de la pareja
    private final String face;   // texto mostrado ("Apple" o "🍎")
    private boolean matched = false;
    private boolean faceUp = false;

    public Card(String id, String face) {
        this.id = id;
        this.face = face;
    }

    public String getId() { return id; }
    public String getFace() { return face; }

    public boolean isMatched() { return matched; }
    public void setMatched(boolean matched) { this.matched = matched; }

    public boolean isFaceUp() { return faceUp; }
    public void setFaceUp(boolean faceUp) { this.faceUp = faceUp; }
}
