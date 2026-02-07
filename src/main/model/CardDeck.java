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

    // EFFECTS: filters cards in the deck by whether they are indoor activities
    public List<Card> filterIndoor() {
        return null; // stub
    }
    // EFFECTS: filters cards in the deck by whether they are outdoor activities
    public List<Card> filterOutdoor() {
        return null; // stub
    }

    // MODIFIES: this
    // EFFECTS: flips to the next card in the deck;
    //          if the end of the deck is reached, return the first card in the deck
    public Card getNextCard() {
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
