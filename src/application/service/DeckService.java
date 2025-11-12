package application.service;

import application.model.Deck;

import java.util.Arrays;
import java.util.List;

/**
 * Servicio que provee barajas (decks) embebidas en código.
 *
 * <p>Permite obtener nombres de mazos disponibles y construir un {@link Deck}
 * por nombre.
 */
public class DeckService {

    /**
     * @return lista de nombres de mazos disponibles.
     */
    public List<String> getDeckNames() {
        return Arrays.asList("Animals", "Food", "Objects");
    }

    /**
     * Construye un mazo por nombre.
     *
     * @param name "Animals", "Food" o "Objects"
     * @return instancia de {@link Deck} con 8 pares
     */
    public Deck buildDeck(String name) {
        switch (name) {
            case "Animals": return animals();
            case "Food":    return food();
            case "Objects": return objects();
            default:        return animals();
        }
    }

    /** @return mazo "Animals" con 8 parejas. */
    private Deck animals() {
        Deck d = new Deck("Animals");
        d.addPair("dog", "Dog", "🐶");
        d.addPair("cat", "Cat", "🐱");
        d.addPair("lion", "Lion", "🦁");
        d.addPair("fish", "Fish", "🐟");
        d.addPair("bird", "Bird", "🐦");
        d.addPair("frog", "Frog", "🐸");
        d.addPair("cow", "Cow", "🐮");
        d.addPair("horse", "Horse", "🐴");
        return d;
    }

    /** @return mazo "Food" con 8 parejas. */
    private Deck food() {
        Deck d = new Deck("Food");
        d.addPair("apple", "Apple", "🍎");
        d.addPair("banana", "Banana", "🍌");
        d.addPair("bread", "Bread", "🍞");
        d.addPair("cheese", "Cheese", "🧀");
        d.addPair("pizza", "Pizza", "🍕");
        d.addPair("carrot", "Carrot", "🥕");
        d.addPair("rice", "Rice", "🍚");
        d.addPair("grapes", "Grapes", "🍇");
        return d;
    }

    /** @return mazo "Objects" con 8 parejas. */
    private Deck objects() {
        Deck d = new Deck("Objects");
        d.addPair("house", "House", "🏠");
        d.addPair("book", "Book", "📖");
        d.addPair("phone", "Phone", "📱");
        d.addPair("clock", "Clock", "🕒");
        d.addPair("key", "Key", "🔑");
        d.addPair("car", "Car", "🚗");
        d.addPair("tv", "TV", "📺");
        d.addPair("lamp", "Lamp", "💡");
        return d;
    }
}