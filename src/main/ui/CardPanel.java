package ui;

import javax.swing.JPanel;

import exceptions.EmptyListException;
import model.Card;

// References:
// https://github.students.cs.ubc.ca/CPSC210/B02-SpaceInvadersBase.git

// Represents the panel on which card related GUI will be placed
public class CardPanel extends JPanel {

    // MODIFIES: this
	// EFFECTS:  initializes the panel where card info is displayed
    public CardPanel() {

    }

	// EFFECTS:  adds given card to current deck 
	public void addToDeck(Card c) {
	}

    // EFFECTS: draws a random card from the current deck
    public void drawCard() {
    }

    // EFFECTS: draws a random card from a filtered version of the current deck
    public void drawCardFiltered() {
    }

    // MODIFIES: this
    // EFFECTS: displays the given card
    public void displayCard(Card card) {
    }

    // EFFECTS: deletes the card from the current deck
    public void deleteCard() {
    }

}
