package ui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;
import exceptions.EmptyListException;
import model.Card;
import model.Deck;

// References:
// https://github.students.cs.ubc.ca/CPSC210/B02-SpaceInvadersBase.git
// https://stackoverflow.com/questions/64750241/how-to-check-which-button-in-my-jframe-was-clicked-if-i-have-more-than-one-butto
// https://stackoverflow.com/questions/54800528/joptionpane-with-multiple-inputs 
// https://stackoverflow.com/questions/1790500/render-html-in-swing-application

// Represents the panel on which card related GUI will be placed
@ExcludeFromJacocoGeneratedReport
public class CardPanel extends JPanel {

    private DrawingSurface gui;
    private Deck currentDeck;

    private DefaultListModel<String> cardListModel;
    private JPanel buttonPanel;
    private JButton addCard;
    private JButton drawCard;
    private JButton drawFilteredCard;
    private JButton menu;

    // MODIFIES: this
    // EFFECTS: initializes the panel where card info is displayed
    public CardPanel(DrawingSurface gui) {
        super(new BorderLayout());
        this.gui = gui;
        currentDeck = gui.getCurrentDeck();

        JLabel showCurr = new JLabel("deck: " + currentDeck.getDeckName(), SwingConstants.CENTER);
        this.add(showCurr, BorderLayout.NORTH);

        initScrollPane();
        initButtons();
    }

    // MODIFIES: this
    // EFFECTS: initialize the scroll pane displaying the cards
    private void initScrollPane() {
        cardListModel = new DefaultListModel<>();

        for (Card c : currentDeck.getCards()) {
            cardListModel.addElement("<html><body style='padding: 5px;'>" +
                    "<b>activity: </b>" + c.getActivity() +
                    "<br>" + c.getDescription() +
                    "</body></html>");
        }
        JList<String> cards = new JList<String>(cardListModel);
        JScrollPane scrollPane = new JScrollPane(cards);

        cards.addMouseListener(new CardMouseListener());
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
        menu = new JButton("return to menu");
        menu.addActionListener(action);
        JLabel deleteInstructions = new JLabel("<html><body style='padding-left: 10px;'>" + 
                                                "<small>right click to" +
                                                "<br>delete a card</small></html>");

        buttonPanel = new JPanel();
        buttonPanel.add(addCard);
        buttonPanel.add(drawCard);
        buttonPanel.add(drawFilteredCard);
        buttonPanel.add(menu);
        buttonPanel.add(deleteInstructions);
        this.add(buttonPanel, BorderLayout.SOUTH);
    }

    // MODIFIES: this
    // EFFECTS: update the scroll pane displaying the cards
    private void updateScrollPane(Card c) {
        cardListModel.addElement("<html><body style='padding: 5px;'>" +
                "<b>activity: </b>" + c.getActivity() +
                "<br>" + c.getDescription() +
                "</body></html>");
    }

    // EFFECTS: adds given card to current deck
    public void addNewCard() {
        JFrame inBox = new JFrame();
        inBox.setLocationRelativeTo(null);
        inBox.setVisible(true);

        Card newCard = createCardInputWindow(inBox);

        if (newCard != null && !currentDeck.getCards().contains(newCard)) {
            currentDeck.addToDeck(newCard);
            updateScrollPane(newCard);

            JOptionPane.showMessageDialog(inBox, "new card created!");
        } else if (currentDeck.getCards().contains(newCard)) {
            JOptionPane.showMessageDialog(inBox, "this card already exists in your deck! card not created.");
        } else {
            JOptionPane.showMessageDialog(inBox, "card not created.");
        }
        inBox.dispose();
    }

    // MODIFIES: this
    // EFFECTS: creates a user input window for a user to make a card and returns
    // the new card
    public Card createCardInputWindow(JFrame inBox) {
        JTextField activityIn = new JTextField();
        JCheckBox outdoorIn = new JCheckBox("outdoor activity?");
        JTextField descIn = new JTextField();

        Object[] message = { "activity name:", activityIn, outdoorIn, "description:", descIn };

        int result = JOptionPane.showConfirmDialog(inBox, message, "enter:", JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            return new Card(activityIn.getText(), outdoorIn.isSelected(), descIn.getText());
        }
        return null;
    }

    // EFFECTS: draws a random card from the current deck if it is not empty, otherwise show error msg
    public void drawCard() {
        if (currentDeck.getCards().size() <= 0) {
            JFrame popUp = new JFrame();
            JOptionPane.showMessageDialog(popUp, "<html>Sorry, there are no cards of this type to draw from."
                        + "<br> Please create some more cards first!</html>");
        } else {
            displayCard(currentDeck.pullRandomCard());
        }
    }

    // EFFECTS: draws a random card from a filtered version of the current deck if cards of those
    //          type exist in the deck, otherwise show error msg
    public void drawCardFiltered(Boolean outdoor) {
        JFrame inBox = new JFrame();
        JCheckBox outdoorIn = new JCheckBox("<html>filtered draw:" +
                "<br><b>check this box to filter for outdoor cards</b></html>");
        Object[] message = { outdoorIn };
        int result = JOptionPane.showConfirmDialog(inBox, message, "enter:", JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            try {
                displayCard(currentDeck.pullRandomCard(outdoorIn.isSelected()));
            } catch (EmptyListException e) {
                JOptionPane.showMessageDialog(inBox, "<html>Sorry, there are no cards of this type to draw from."
                        + "<br> Please create some more cards first!</html>");
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: displays the given card
    public void displayCard(Card c) {
        JFrame popUp = new JFrame();
        popUp.setLocationRelativeTo(null);
        popUp.setVisible(true);
        JOptionPane.showMessageDialog(popUp, "<html><body style='padding: 5px;'>" +
                "card generated!" +
                "<br><b>activity: </b>" + c.getActivity() +
                "<br>" + c.getDescription() +
                "</body></html>");
        popUp.dispose();
    }

    // EFFECTS: deletes the card at the given index from the current deck
    public void deleteCard(Card c, int index) {
        JFrame inBox = new JFrame();
        String message = "<html><b>are you sure you want to delete this card?</b>" +
                "<br>(this can not be undone)</html>";

        int result = JOptionPane.showConfirmDialog(inBox, message, "enter:", JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            currentDeck.removeFromDeck(c);
        }
        cardListModel.remove(index);
        inBox.dispose();
    }

    // provides user interaction for the card panel class
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
            } else if (source == menu) {
                gui.showDeckPanel();
            }
        }
    }

    // provides user mouse related interaction for the card panel class
    private class CardMouseListener extends MouseAdapter {

        // EFFECTS: if user right clicks, confirm if the user wants to delete the
        // clicked card and delete that card if confirmed yes
        public void mousePressed(MouseEvent e) {
            if (e.isPopupTrigger()) {
                JList<String> cardList = (JList<String>) e.getSource();
                int index = cardList.locationToIndex(e.getPoint());
                if (index >= 0) {
                    deleteCard(currentDeck.getCards().get(index), index);
                }
            }
        }
    }

}
