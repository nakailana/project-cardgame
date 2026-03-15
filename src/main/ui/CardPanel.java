package ui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import exceptions.EmptyListException;
import model.Card;
import model.Deck;

// References:
// https://github.students.cs.ubc.ca/CPSC210/B02-SpaceInvadersBase.git
// https://stackoverflow.com/questions/64750241/how-to-check-which-button-in-my-jframe-was-clicked-if-i-have-more-than-one-butto

// Represents the panel on which card related GUI will be placed
public class CardPanel extends JPanel {

    private DrawingSurface gui;
    private Deck currentDeck;

    private DefaultListModel<String> cardListModel;
    private JPanel buttonPanel;
    private JButton addCard;
    private JButton drawCard;
    private JButton drawFilteredCard;
    private JButton deleteCard;
    private JButton menu;

    // MODIFIES: this
	// EFFECTS:  initializes the panel where card info is displayed
    public CardPanel(DrawingSurface gui) {
        super(new BorderLayout());
        this.gui = gui;
        currentDeck = gui.getCurrentDeck();

        initScrollPane();
        initButtons();

        this.setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: initialize the scroll pane displaying the cards
    private void initScrollPane() {
        DefaultListModel<Card> cardListModel = new DefaultListModel<>();

        if (currentDeck != null) {
            for (Card c : currentDeck.getCards()) {
                cardListModel.addElement(c);
            }
        }
        JList<Card> cards = new JList<Card>(cardListModel);
        JScrollPane scrollPane = new JScrollPane(cards);
        this.add(scrollPane, BorderLayout.CENTER);
    }

    // MODIFIES: this
    // EFFECTS: initialize the buttons on the screen
    private void initButtons() {
        CardListener action = new CardListener();

        addCard = new JButton("add new card");
        addCard.addActionListener(action);
        drawCard = new JButton("draw random");
        drawCard.addActionListener(action);
        drawFilteredCard = new JButton("draw random (filtered)");
        drawFilteredCard.addActionListener(action);
        deleteCard = new JButton("delete");
        deleteCard.addActionListener(action);
        menu = new JButton("return to menu");
        menu.addActionListener(action);

        buttonPanel = new JPanel();
        buttonPanel.add(addCard);
        buttonPanel.add(drawCard);
        buttonPanel.add(drawFilteredCard);
        buttonPanel.add(deleteCard);
        buttonPanel.add(menu);
        this.add(buttonPanel, BorderLayout.SOUTH);
    }

    // MODIFIES: this
    // EFFECTS: update the scroll pane displaying the cards
    private void updateScrollPane(Card c) {
        cardListModel.addElement(c.getActivity());
    }

	// EFFECTS:  adds given card to current deck 
	public void addNewCard() {
	}

    // EFFECTS: draws a random card from the current deck
    public void drawCard() {
    }

    // EFFECTS: draws a random card from a filtered version of the current deck
    public void drawCardFiltered(Boolean outdoor) {
    }

    // MODIFIES: this
    // EFFECTS: displays the given card
    public void displayCard(Card c) {
    }

    // EFFECTS: deletes the card from the current deck
    public void deleteCard(Card c) {
    }

    //provides user interaction for the card panel class
    private class CardListener implements ActionListener {

        // EFFECTS: perform the action corresponding to each button on the screen
        public void actionPerformed(ActionEvent e) {
            Object source = e.getSource();

            if (source == addCard) {
                addNewCard();
            } else if (source == drawCard) {
                drawCard();
            } else if (source == drawFilteredCard) {
                drawCardFiltered(false);
            } else if (source == deleteCard) {
                //deleteCard(Card c)
            } else if (source == menu) {
                gui.showDeckPanel();
            }
        }
    }

    // provides user mouse related interaction for the card panel class
    private class CardMouseListener extends MouseAdapter {

        // EFFECTS: if delete card button was pressed and the card in the JList is double clicked,
        //          delete that card
        public void mousePressed(MouseEvent e) {
        }
    }

}
