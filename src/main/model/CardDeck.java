package model;

import java.util.ArrayList;
import java.util.List;

// Represents a Card Deck with a name (theme) and list of cards.
public class CardDeck {

     /*
     * REQUIRES: deckName has a non-zero length
     * EFFECTS: the deck's name is set to deckName;
     *          the deck is set to an empty ArrayList containing no Cards
     */
    public CardDeck(String deckName) {
        // stub
    }

    // REQUIRES: this is not an empty ArrayList
    // EFFECTS: draws a random card from the deck
    public Card pullRandomCard() {
        return null; //stub
    }
    // REQUIRES: this is not an empty ArrayList
    // EFFECTS: draws a random card of specified location from the deck
    public Card pullRandomCard(Boolean outdoor) {
        return null; //stub
    }

    // MODIFIES: this
    // EFFECTS: adds given card to the deck if it is not yet in the deck
    public void addToDeck(Card card) {
        // stub
    }

    // EFFECTS: filters cards in the deck by whether they are indoor activities
    public List<Card> filterCards(Boolean outdoor) {
        return null; // stub
    }

    // getters
    public String getDeckName() {
        return ""; // stub
    }

    public ArrayList<Card> getCards() {
        return null; // stub
    }

}
