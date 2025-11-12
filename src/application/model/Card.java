package application.model;

/**
 * Representa una carta del memorama.
 *
 * <p>Dos cartas forman pareja si comparten el mismo {@code id}. El atributo
 * {@code face} es el texto que se muestra (palabra o emoji).
 */
public class Card {
    private final String id;
    private final String face;
    private boolean matched = false;
    private boolean faceUp = false;

    /**
     * Crea una carta.
     *
     * @param id   identificador de pareja (común a ambas cartas)
     * @param face texto mostrado en la cara (e.g., "Apple" o "🍎")
     */
    public Card(String id, String face) {
        this.id = id;
        this.face = face;
    }

    /** @return identificador de pareja. */
    public String getId() { return id; }

    /** @return contenido visible de la carta. */
    public String getFace() { return face; }

    /** @return {@code true} si ya fue emparejada. */
    public boolean isMatched() { return matched; }

    /** @param matched nuevo estado de emparejada. */
    public void setMatched(boolean matched) { this.matched = matched; }

    /** @return {@code true} si está boca arriba. */
    public boolean isFaceUp() { return faceUp; }

    /** @param faceUp nuevo estado de orientación. */
    public void setFaceUp(boolean faceUp) { this.faceUp = faceUp; }
}

