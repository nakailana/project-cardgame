package model;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import exceptions.EmptyListException;
import persistence.Writable;

// Represents a Card Deck with a name (theme) and list of cards.
public class Deck implements Writable {

    String deckName;
    List<Card> cards;

    /*
     * REQUIRES: deckName has a non-zero length
     * EFFECTS: the deck's name is set to deckName;
     * the deck is set to an empty ArrayList containing no Cards
     */
    public Deck(String deckName) {
        this.deckName = deckName;
        cards = new ArrayList<Card>();
    }

    // REQUIRES: this is not an empty ArrayList
    // EFFECTS: draws a random card from the deck
    public Card pullRandomCard() {
        int cardNum = (int) (Math.random() * cards.size());
        return cards.get(cardNum);
    }

    // EFFECTS: draws a random card of specified location from the deck
    public Card pullRandomCard(Boolean outdoor) throws EmptyListException {
        List<Card> filtered = filterCards(outdoor);
        if (filtered.size() == 0) {
            throw new EmptyListException();
        }
        int cardNum = (int) (Math.random() * filtered.size());

        return filtered.get(cardNum);
    }

    // MODIFIES: this
    // EFFECTS: adds given card to the deck if it is not yet in the deck
    public void addToDeck(Card card) {
        if (!cards.contains(card)) {
            cards.add(card);
        }
    }

    // MODIFIES: this
    // EFFECTS: removes given card from the deck
    public void removeFromDeck(Card card) {
        cards.remove(card);
    }

    // EFFECTS: filters cards in the deck by their activity location
    public List<Card> filterCards(Boolean outdoor) {
        List<Card> filtered = new ArrayList<Card>();

        for (Card c : cards) {
            if (c.getLocation() == outdoor) {
                filtered.add(c);
            }
        }
        return filtered;
    }

    // getters
    public String getDeckName() {
        return deckName;
    }

    public List<Card> getCards() {
        return cards;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("theme", deckName);
        json.put("cards", cardsToJson());
        return json;
    }

    // EFFECTS: returns things in this workroom as a JSON array
    private JSONArray cardsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Card c : cards) {
            jsonArray.put(c.toJson());
        }

        return jsonArray;
    }
}
