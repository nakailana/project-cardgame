package model;

import java.util.ArrayList;
import java.util.List;

// Represents a Card having an activity name, if it is an outdoor activity,
// and optionally a brief description
public class Card {

    /*
     * REQUIRES: activity and has a non-zero length; 
     * EFFECTS: the card's activity is set to activity;
     *          the card's location is set to indoor;
     *          its description is set to describe the recommended location, 
     *          followed by the given description.
     */
    public Card(String activity, Boolean outdoor, String description) {
        // stub
    }

    // setters
    public void updateActivity(String newActivity) {
        // stub
    }
    // MODIFIES: this
    // EFFECTS: updates the recommended location of this activity, and updates the 
    //          location listed in the description
    public void updateLocation(Boolean location) {
        //stub
    }
    // MODIFIES: this
    // EFFECTS: updates the description of the activity without changing the 
    //          location description
    public void updateDescription(String newDesc) {
        // stub
    }

    // getters
    public String getActivity() {
        return ""; // stub
    }
    public Boolean getAppropriateLocation() {
        return false; // stub
    }
    public String getDescription() {
        return ""; // stub
    }

}
