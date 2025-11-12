package application.service;

import application.model.Deck;

public class DeckService {

    public Deck buildDeck(String name) {
        switch (name) {
            case "Animals": return animals();
            case "Food":    return food();
            case "Objects": return objects();
            default:        return animals();
        }
    }

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
