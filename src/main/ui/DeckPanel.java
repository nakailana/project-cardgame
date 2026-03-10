package ui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import model.Deck;
import model.DecksController;

// References:
// https://github.students.cs.ubc.ca/CPSC210/B02-SpaceInvadersBase.git

// Represents the panel on which deck related GUI will be placed
public class DeckPanel extends JPanel {

    private DrawingSurface gui;
    private DecksController dc;
    private Deck currentDeck;

    JPanel buttonPanel;

    // MODIFIES: this
	// EFFECTS:  initializes the panel where deck and deck info is displayed
    public DeckPanel(DrawingSurface gui, DecksController dc) {
        this.gui = gui;
        this.dc = dc;
        currentDeck = gui.getCurrentDeck();

        this.setLayout(new BorderLayout());
    }

    // MODIFIES: this
	// EFFECTS:  declares and instantiates a Deck, and adds it to deck controller
	private void addNewDeck() {
        
    }

    private class DeckListener implements ActionListener {

        // EFFECTS: open the card menu panel when a deck is selected
        public void actionPerformed(ActionEvent e) {
            gui.showCardPanel();
        }
    }

}
