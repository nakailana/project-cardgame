package ui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

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
// https://stackoverflow.com/questions/64750241/how-to-check-which-button-in-my-jframe-was-clicked-if-i-have-more-than-one-butto

// Represents the panel on which deck related GUI will be placed
public class DeckPanel extends JPanel {

    private DrawingSurface gui;
    private DecksController dc;
    private Deck currentDeck;

    private DefaultListModel<String> deckListModel;
    private JPanel buttonPanel;
    private JButton addDeck;
    private JButton openDeck;
    private JButton saveButton;
    private JButton loadButton;

    // MODIFIES: this
	// EFFECTS:  initializes the panel where deck and deck info is displayed
    public DeckPanel(DrawingSurface gui) {
        this.gui = gui;
        this.dc = gui.getDeckController();
        currentDeck = gui.getCurrentDeck();

        this.setLayout(new BorderLayout());

        initScrollPane();
        initButtons();
    }

    // MODIFIES: this
    // EFFECTS: initialize the scroll pane displaying the decks
    private void initScrollPane() {
        deckListModel = new DefaultListModel<>();
        
        for (Deck d : dc.getDecks()) {
            deckListModel.addElement(d.getDeckName());
        }

        JList<String> decks = new JList<String>(deckListModel);
        JScrollPane scrollPane = new JScrollPane(decks);

        this.add(scrollPane, BorderLayout.CENTER);
    }

    // MODIFIES: this
    // EFFECTS: initialize the buttons on the screen
    private void initButtons() {
        DeckListener action = new DeckListener();

        addDeck = new JButton("add new deck");
        addDeck.addActionListener(action);
        openDeck = new JButton("select deck");
        openDeck.addActionListener(action);
        saveButton = new JButton("save");
        saveButton.addActionListener(action);
        loadButton = new JButton("load from data");
        loadButton.addActionListener(action);

        buttonPanel = new JPanel();

        buttonPanel.add(addDeck);
        buttonPanel.add(openDeck);
        buttonPanel.add(saveButton);
        buttonPanel.add(loadButton);

        this.add(buttonPanel, BorderLayout.SOUTH);
    }

    // MODIFIES: this
    // EFFECTS: update the scroll pane displaying the decks
    private void updateScrollPane(Deck d) {
        deckListModel.addElement(d.getDeckName());
    }

    // MODIFIES: this
	// EFFECTS:  declares and instantiates a Deck, and adds it to deck controller
    //           update scroll pane
	private void addNewDeck() {
        JFrame inBox = new JFrame();
        inBox.setVisible(true);
        String input = JOptionPane.showInputDialog(inBox, "Please enter the deck's theme:");

        if (input != null && !input.isEmpty()) {
            JOptionPane.showMessageDialog(inBox, "new deck created!");
        } else {
            JOptionPane.showMessageDialog(inBox, "Invalid input. Please try again.");
        }
        inBox.dispose();

        Deck newDeck = new Deck(input);
        dc.addToController(newDeck);
        updateScrollPane(newDeck);
    }

    // EFFECTS: saves the state of the card game to file
    private void saveGameState() {
        JFrame popUp = new JFrame();
        popUp.setVisible(true);
        try {
            gui.saveGameState();
            JOptionPane.showMessageDialog(popUp, "Saved data to file!");
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(popUp, "Unable to save data to file.");
        } catch (NullPointerException e) {
            JOptionPane.showMessageDialog(popUp, "No data to save.");
        }
    }

    // EFFECTS: loads the state of the card game from file
    private void loadGameState() {
        JFrame popUp = new JFrame();
        popUp.setVisible(true);
        try {
            gui.loadGameState();
            JOptionPane.showMessageDialog(popUp, "Loaded data from file!");
            
            deckListModel.clear(); 
            dc = gui.getDeckController();
            for (Deck d: dc.getDecks()) {
                updateScrollPane(d);
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(popUp, "Unable to read data from file.");
        }
    }

    // provides user interaction for the deck panel class
    private class DeckListener implements ActionListener {

        // EFFECTS: perform the action corresponding to each button on the screen
        public void actionPerformed(ActionEvent e) {
            Object source = e.getSource();

            if (source == addDeck) {
                addNewDeck();
            } else if (source == openDeck) {
                gui.showCardPanel();
            } else if (source == saveButton) {
                saveGameState();
            } else if (source == loadButton) {
                loadGameState();
            }
        }
    }

}
