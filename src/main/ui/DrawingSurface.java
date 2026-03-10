package ui;

import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import model.Card;
import model.Deck;
import model.DecksController;
import persistence.JsonReader;
import persistence.JsonWriter;

// References:
// Previous Java project: https://github.com/HHSAPCompSci2023/capstoneproject-capstone-vibhalanatalya-compcrew.git
// Course content: https://github.students.cs.ubc.ca/CPSC210/SimpleDrawingPlayer-Complete.git 
//                 https://github.students.cs.ubc.ca/CPSC210/B02-SpaceInvadersBase.git 

// Represents the main surface where GUI is drawn
public class DrawingSurface extends JFrame{

    public static final int WIDTH = 1000;
	public static final int HEIGHT = 700;

    private static final String JSON_STORE = "./data/cardGame.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    private CardLayout screens;
    private JPanel container;
    private JPanel dp;
    private JPanel cp;

    private DecksController dc;
    private Deck currentDeck;

    // constructs the drawing surface
    public DrawingSurface() {
        super("Cards Against Boredom");
        initializeFields();
        initializeGraphics();
        initializeInteraction();
    }

    // MODIFIES: this
    // EFFECTS:  sets deck controller and current deck to null
    //           this method is called by the DrawingSurface constructor
    private void initializeFields() {
        dc = new DecksController("my game");
        currentDeck = null;

        screens = new CardLayout();
        container = new JPanel(screens);
        dp = new DeckPanel(this, dc);
		cp = new CardPanel(this);

        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
    }

    // getters
	public Deck getCurrentDeck() { return currentDeck; }

    // MODIFIES: this
	// EFFECTS:  initializes a DrawingMouseListener to be used in the JFrame
    private void initializeInteraction() {
        DrawingMouseListener dml = new DrawingMouseListener();
        addMouseListener(dml);
        addMouseMotionListener(dml);
    }

    // MODIFIES: this
    // EFFECTS:  draws the JFrame window where this DrawingSurface will operate
    private void initializeGraphics() {
        setLayout(new BorderLayout());
        setMinimumSize(new Dimension(WIDTH, HEIGHT));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        container.add(dp, "deckPanel");
        container.add(cp, "cardPanel");

        screens.show(container, "deckPanel");

        setVisible(true);
    }

    public void showDeckPanel() {
        screens.show(container, "deckPanel");
    }

    public void showCardPanel() {
        screens.show(container, "cardPanel");
    }

	public static void main(String args[]) {
        new DrawingSurface();
	}

    private class DrawingMouseListener extends MouseAdapter {

		// EFFECTS: Forward mouse clicked event
        public void mouseClicked(MouseEvent e) {
        }
    }
}
