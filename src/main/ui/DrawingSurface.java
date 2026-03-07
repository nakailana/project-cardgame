package ui;

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.tools.Tool;

import model.Card;
import model.Deck;
import model.DecksController;

// References:
// Previous Java project: https://github.com/HHSAPCompSci2023/capstoneproject-capstone-vibhalanatalya-compcrew.git
// Course content: https://github.students.cs.ubc.ca/CPSC210/SimpleDrawingPlayer-Complete.git 

// Represents the surface where GUI is drawn
public class DrawingSurface extends JFrame{

    // constructs the drawing surface
    public DrawingSurface() {
    }

    // getters
	public Deck getCurrentDeck(){ return null; }

    // MODIFIES: this
	// EFFECTS:  initializes a DrawingMouseListener to be used in the JFrame
    private void initializeInteraction() {
    }

    // MODIFIES: this
    // EFFECTS:  initializes this DrawingEditor's deck controller field
    private void initializeDecks() {
    }

    // MODIFIES: this
    // EFFECTS:  draws the JFrame window where this DrawingSurface will operate
    private void initializeGraphics() {
    }

	// MODIFIES: this
    // EFFECTS:  sets deck controller and current deck to null
    //           this method is called by the DrawingSurface constructor
    private void initializeFields() {
    }

	// MODIFIES: this
	// EFFECTS:  declares and instantiates a Deck, and adds it to deck controller
	private void addNewDeck() {

	}

	// MODIFIES: this
	// EFFECTS:  adds given card to current deck 
	public void addToDeck(Card c) {
	}


    // EFFECTS: handles button click
	private void handleMouseClicked(MouseEvent e) {
	}


	public static void main(String args[]) {
	}

    private class DrawingMouseListener extends MouseAdapter {

		// EFFECTS:Forward mouse clicked event to the active tool
        public void mouseClicked(MouseEvent e) {
        }
    }
}
