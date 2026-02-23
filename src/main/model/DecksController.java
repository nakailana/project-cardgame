package model;

import java.util.ArrayList;
import java.util.List;

// Represents all the decks of cards in the program
public class DecksController {

    private List<Deck> decks;

    /*
     * EFFECTS: create a new instance of the game with an empty
     * ArrayList of decks
     */
    public DecksController() {
        decks = new ArrayList<Deck>();
    }

    // MODIFIES: this
    // EFFECTS: adds deck to the deck controller if not already added
    public void addToController(Deck deck) {
        if (!decks.contains(deck)) {
            decks.add(deck);
        }
    }

    // getters
    public List<Deck> getDecks() {
        return decks;
    }

}
