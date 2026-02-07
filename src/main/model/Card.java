package model;

import java.util.ArrayList;
import java.util.List;

// Represents a Card having an activity name, and optionally a brief description
public class Card {

    /*
     * REQUIRES: activity and has a non-zero length; 
     * EFFECTS: the card's activity is set to activity;
     *          its description is set to the given description.
     */
    public Card(String activity, String description) {
        // stub
    }

    // REQUIRES: decks is an ArrayList of decks, which are ArrayLists of Cards;
    // MODIFIES: decks
    // EFFECTS: adds this card to all given decks that don't already have this card
    public void addToDecks(List<ArrayList<Card>> decks) {
        // stub
    }

    // MODIFIES: decks
    // EFFECTS: removes this from list of given decks
    public void removeAllInstances(List<ArrayList<Card>> decks) {
        // stub
    }

    // setters
    public void updateActivity(String newActivity) {
        // stub
    }
    public void updateDescription(String newDesc) {
        // stub
    }

    // getters
    public String getActivity() {
        return ""; // stub
    }

    public String getDescription() {
        return ""; // stub
    }

}
