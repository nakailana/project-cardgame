package model;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import persistence.Writable;

// Represents all the decks of cards in the program
public class DecksController implements Writable {

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

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        return json;
    }

    // EFFECTS: returns things in this workroom as a JSON array
    private JSONArray decksToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Deck d : decks) {
            jsonArray.put(d.toJson());
        }

        return jsonArray;
    }

}
