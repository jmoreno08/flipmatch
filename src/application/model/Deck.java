package application.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
	
    private final String name;
    private final List<Card> cards = new ArrayList<>();

    public Deck(String name) { this.name = name; }
    public String getName() { return name; }
    public List<Card> getCards() { return cards; }

    public void addPair(String id, String a, String b) {
    	
        cards.add(new Card(id, a));
        cards.add(new Card(id, b));
        
    }

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
