package model;

import java.util.ArrayList;

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
    public Card pullRandomCard(){
        return null; //stub
    }

    // MODIFIES: this
    // EFFECTS: adds given card to the deck
    public void addToDeck(Card card) {
        // stub
    }

    // MODIFIES: this
    // EFFECTS: removes given card from the deck
    public void removeFromDeck(Card card) {
        // stub
    }

    // MODIFIES: this
    // EFFECTS: updates this deck's name; changes deckName for all cards in this deck
    public void updateName(String newActivity) {
        // stub
    }

    // getters
    public String getDeckName() {
        return ""; // stub
    }

    public ArrayList<Card> getCards() {
        return null; // stub
    }

}
