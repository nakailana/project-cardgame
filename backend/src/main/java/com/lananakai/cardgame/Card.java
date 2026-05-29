package com.lananakai.cardgame;

import java.util.UUID;

// Represents a Card having an activity name, if it is an outdoor activity,
// and optionally a brief description
public class Card {
    
    private String id;
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
        this.id = UUID.randomUUID().toString();
        
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
    public String getId() {
        return id;
    }
    
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
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((activity == null) ? 0 : activity.hashCode());
        result = prime * result + ((outdoor == null) ? 0 : outdoor.hashCode());
        return result;
    }

    @Override
    @SuppressWarnings("methodlength")
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Card other = (Card) obj;
        if (activity == null) {
            if (other.activity != null) {
                return false;
            }
        } else if (!activity.equals(other.activity)) {
            return false;
        }
        if (outdoor == null) {
            if (other.outdoor != null) {
                return false;
            }
        } else if (!outdoor.equals(other.outdoor)) {
            return false;
        }
        return true;
    }
}
