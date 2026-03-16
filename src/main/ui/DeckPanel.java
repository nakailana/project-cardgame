package ui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;
import model.Deck;
import model.DecksController;

// References:
// https://github.students.cs.ubc.ca/CPSC210/B02-SpaceInvadersBase.git
// https://stackoverflow.com/questions/64750241/how-to-check-which-button-in-my-jframe-was-clicked-if-i-have-more-than-one-butto
// https://stackoverflow.com/questions/20702013/how-to-add-double-click-capability-in-jscrollpane#:~:text=Comments,-Add%20a%20comment&text=0-,JList%20theList%20=%20(JList)%20mouseEvent.,work%20by%20mouse%20or%20keyboard. 

// Represents the panel on which deck related GUI will be placed
@ExcludeFromJacocoGeneratedReport
public class DeckPanel extends JPanel {

    private DrawingSurface gui;
    private DecksController dc;

    private DefaultListModel<String> deckListModel;
    private JPanel buttonPanel;
    private JButton addDeck;
    private JButton saveButton;
    private JButton loadButton;

    // MODIFIES: this
	// EFFECTS:  initializes the panel where deck and deck info is displayed
    public DeckPanel(DrawingSurface gui) {
        super(new BorderLayout());
        this.gui = gui;
        this.dc = gui.getDeckController();

        JLabel select = new JLabel("double click a deck to select it!", 
                                    SwingConstants.CENTER);
        this.add(select, BorderLayout.NORTH);

        initScrollPane();
        initButtons();
    }

    // MODIFIES: this
    // EFFECTS: initialize the scroll pane displaying the decks
    private void initScrollPane() {
        deckListModel = new DefaultListModel<>();
        
        for (Deck d : dc.getDecks()) {
            deckListModel.addElement("<html><body style='padding: 5px;'>" +
                                    "<b>" + d.getDeckName() + "</b></body></html>");
        }

        JList<String> decks = new JList<String>(deckListModel);
        JScrollPane scrollPane = new JScrollPane(decks);

        decks.addMouseListener(new DeckMouseListener()); 
        this.add(scrollPane, BorderLayout.CENTER);
    }

    // MODIFIES: this
    // EFFECTS: initialize the buttons on the screen
    private void initButtons() {
        DeckListener action = new DeckListener();

        addDeck = new JButton("add new deck");
        addDeck.addActionListener(action);
        saveButton = new JButton("save data");
        saveButton.addActionListener(action);
        loadButton = new JButton("load from data");
        loadButton.addActionListener(action);

        buttonPanel = new JPanel();

        buttonPanel.add(addDeck);
        buttonPanel.add(loadButton);
        buttonPanel.add(saveButton);

        this.add(buttonPanel, BorderLayout.SOUTH);
    }

    // MODIFIES: this
    // EFFECTS: update the scroll pane displaying the decks
    private void updateScrollPane(Deck d) {
        deckListModel.addElement("<html><body style='padding: 5px;'>" +
                                "<b>" + d.getDeckName() + "</b></body></html>");
    }

    // MODIFIES: this
	// EFFECTS:  declares and instantiates a Deck, and adds it to deck controller
    //           update scroll pane
	private void addNewDeck() {
        JFrame inBox = new JFrame();
        inBox.setLocationRelativeTo(null);
        inBox.setVisible(true);
        String input = JOptionPane.showInputDialog(inBox, "Please enter the deck's theme:");

        if (input != null && !input.isEmpty()) {
            JOptionPane.showMessageDialog(inBox, "new deck created!");
            Deck newDeck = new Deck(input);
            dc.addToController(newDeck);
            updateScrollPane(newDeck);
        } else {
            JOptionPane.showMessageDialog(inBox, "Invalid input.");
        }
        inBox.dispose();
    }

    // EFFECTS: saves the state of the card game to file
    private void saveGameState() {
        JFrame popUp = new JFrame();
        popUp.setLocationRelativeTo(null);
        popUp.setVisible(true);
        try {
            gui.saveGameState();
            JOptionPane.showMessageDialog(popUp, "Saved data to file!");
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(popUp, "Unable to save data to file.");
        } catch (NullPointerException e) {
            JOptionPane.showMessageDialog(popUp, "No data to save.");
        } finally {
            popUp.dispose();
        }
    }

    // EFFECTS: loads the state of the card game from file
    private void loadGameState() {
        JFrame popUp = new JFrame();
        popUp.setLocationRelativeTo(null);
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
        } finally {
            popUp.dispose();
        }
    }

    // provides user interaction for the deck panel class
    private class DeckListener implements ActionListener {

        // EFFECTS: perform the action corresponding to each button on the screen
        public void actionPerformed(ActionEvent e) {
            Object source = e.getSource();

            if (source == addDeck) {
                addNewDeck();
            } else if (source == saveButton) {
                saveGameState();
            } else if (source == loadButton) {
                loadGameState();
            }
        }
    }

    // provides user interaction for the deck panel class
    private class DeckMouseListener extends MouseAdapter {

        // EFFECTS: selects a deck when the deck in the scroll pane is double clicked
        public void mousePressed(MouseEvent e) {
            if (e.getClickCount() == 2) {
                JList<String> decks = (JList<String>) e.getSource();
                int index = decks.locationToIndex(e.getPoint());
                if (index >= 0) {
                    gui.setCurrentDeck(dc.getDecks().get(index));
                    gui.showCardPanel(); 
                }
            }
        }
    }

}
