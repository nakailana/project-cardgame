package com.lananakai.cardgame;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

// Represents a Card Deck with a name (theme) and list of cards.
public class Deck {
    
    private String id;
    private String deckName;
    private List<Card> cards;

    /*
     * REQUIRES: deckName has a non-zero length
     * EFFECTS: the deck's name is set to deckName;
     * the deck is set to an empty ArrayList containing no Cards
     */
    public Deck(String deckName) {
        this.id = UUID.randomUUID().toString();
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
    public Card pullRandomCard(Boolean outdoor) {
        List<Card> filtered = filterCards(outdoor);
        if (filtered.size() == 0) {
            return null;
        }
        int cardNum = (int) (Math.random() * filtered.size());

        return filtered.get(cardNum);
    }

    // MODIFIES: this
    // EFFECTS: adds given card to the deck if it is not yet in the deck
    public void addCard(Card card) {
        if (!cards.contains(card)) {
            cards.add(card);
        }
    }

    // MODIFIES: this
    // EFFECTS: removes given card from the deck
    public void removeCard(String cardId) {
        cards.removeIf(c -> c.getId().equals(cardId));
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
    public String getName() {
        return deckName;
    }

    public List<Card> getCards() {
        return cards;
    }

    public String getId() {
        return id;
    }
}
