package application.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Baraja de cartas (mazo) con un nombre y un conjunto de pares.
 *
 * <p>Permite añadir parejas y obtener una copia barajada limitada a 16 cartas
 * (8 parejas) para crear el tablero 4x4.
 */
public class Deck {
    private final String name;
    private final List<Card> cards = new ArrayList<>();

    /**
     * @param name nombre del mazo (e.g., "Animals").
     */
    public Deck(String name) { this.name = name; }

    /** @return nombre del mazo. */
    public String getName() { return name; }

    /**
     * Agrega una pareja al mazo.
     *
     * @param id identificador compartido por ambas cartas de la pareja
     * @param a  cara A (texto)
     * @param b  cara B (texto o emoji)
     */
    public void addPair(String id, String a, String b) {
        cards.add(new Card(id, a));
        cards.add(new Card(id, b));
    }

    /**
     * Devuelve una copia barajada con máximo 16 cartas (8 parejas).
     *
     * @return lista de cartas clonadas y barajadas listas para tablero 4x4.
     */
    public List<Card> shuffledCopy16() {
        List<Card> copy = new ArrayList<>(cards);
        Collections.shuffle(copy);
        List<Card> sixteen = copy.subList(0, Math.min(16, copy.size()));
        List<Card> cloned = new ArrayList<>();
        for (Card c : sixteen) cloned.add(new Card(c.getId(), c.getFace()));
        Collections.shuffle(cloned);
        return cloned;
    }
}

