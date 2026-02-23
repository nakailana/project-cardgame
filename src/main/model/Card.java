package model;

import org.json.JSONArray;
import org.json.JSONObject;

import persistence.Writable;

// Represents a Card having an activity name, if it is an outdoor activity,
// and optionally a brief description
public class Card implements Writable{

    private String activity;
    private String description;
    private String locationDesc;
    private Boolean outdoor;

    /*
     * REQUIRES: activity has a non-zero length;
     * EFFECTS: the card's activity is set to activity; the card's location is set
     * to indoor;
     * its description states the recommended location followed by the given
     * description.
     */
    public Card(String activity, Boolean outdoor, String description) {
        this.activity = activity;
        this.outdoor = outdoor;

        if (this.outdoor) {
            this.locationDesc = "Outdoor activity. ";
        } else {
            this.locationDesc = "Indoor activity. ";
        }
        this.description = description;
    }

    // setters
    // MODIFIES: this
    // EFFECTS: updates the location of this, updates location returned in
    // description
    public void updateLocation(Boolean location) {
        outdoor = location;

        if (this.outdoor) {
            this.locationDesc = "Outdoor activity. ";
        } else {
            this.locationDesc = "Indoor activity. ";
        }
    }

    // MODIFIES: this
    // EFFECTS: updates the description of the activity without changing the
    // location description
    public void updateDescription(String newDesc) {
        this.description = newDesc;
    }

    // getters
    public String getActivity() {
        return activity;
    }

    public Boolean getLocation() {
        return outdoor;
    }

    public String getDescription() {
        return locationDesc + description;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("activity", activity);
        json.put("description", description);
        json.put("outdoor", outdoor);
        return json;
    }
}
