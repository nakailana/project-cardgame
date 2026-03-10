package ui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import exceptions.EmptyListException;
import model.Card;
import model.Deck;

// References:
// https://github.students.cs.ubc.ca/CPSC210/B02-SpaceInvadersBase.git

// Represents the panel on which card related GUI will be placed
public class CardPanel extends JPanel {

    private DrawingSurface gui;
    private Deck currentDeck;

    private JPanel buttonPanel;

    // MODIFIES: this
	// EFFECTS:  initializes the panel where card info is displayed
    public CardPanel(DrawingSurface gui) {
        this.gui = gui;
        currentDeck = gui.getCurrentDeck();

        this.setSize(600,400);
    }

	// EFFECTS:  adds given card to current deck 
	public void addToDeck(Card c) {
        currentDeck.addToDeck(c);
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

    private class CardListener implements ActionListener {

        // EFFECTS: show the deck menu when the back button is pressed
        public void actionPerformed(ActionEvent e) {
            gui.showDeckPanel();
        }
    }

}
