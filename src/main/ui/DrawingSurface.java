package ui;

import javax.swing.JFrame;
import javax.swing.JPanel;

import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.io.FileNotFoundException;
import java.io.IOException;

import model.Deck;
import model.DecksController;
import persistence.JsonReader;
import persistence.JsonWriter;

// References:
// Previous Java project: https://github.com/HHSAPCompSci2023/capstoneproject-capstone-vibhalanatalya-compcrew.git
// Course content: https://github.students.cs.ubc.ca/CPSC210/SimpleDrawingPlayer-Complete.git 
//                 https://github.students.cs.ubc.ca/CPSC210/B02-SpaceInvadersBase.git 

// Represents the main surface where GUI is drawn
@ExcludeFromJacocoGeneratedReport
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
        super("cards against boredom");
        initializeFields();
        initializeGraphics();
    }

    // MODIFIES: this
    // EFFECTS:  sets deck controller and current deck to null
    //           this method is called by the DrawingSurface constructor
    private void initializeFields() {
        dc = new DecksController("my game");
        currentDeck = new Deck("default");
        dc.addToController(currentDeck);

        screens = new CardLayout();
        container = new JPanel(screens);
        dp = new DeckPanel(this);
		cp = new CardPanel(this);

        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
    }

    // getters
	public Deck getCurrentDeck() { return currentDeck; }
    public DecksController getDeckController() { return dc; }

    // setters 
    public void setCurrentDeck(Deck d) { this.currentDeck = d; }

    // MODIFIES: this
    // EFFECTS:  draws the JFrame window where this DrawingSurface will operate
    private void initializeGraphics() {
        setLayout(new BorderLayout());
        setMinimumSize(new Dimension(WIDTH/2, HEIGHT/2));
        setSize(new Dimension(WIDTH, HEIGHT));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        container.add(dp, "deckPanel");
        container.add(cp, "cardPanel");

        add(container);

        screens.show(container, "deckPanel");

        setVisible(true);
    }

    // EFFECTS: saves the state of the card game to file
    public void saveGameState() throws FileNotFoundException, NullPointerException {
        jsonWriter.open();
        jsonWriter.write(dc);
        jsonWriter.close();
    }

    // MODIFIES: this
    // EFFECTS: loads the state of the card game from file
    public void loadGameState() throws IOException {
        dc = jsonReader.read();
    }

    // MODIFIES: this
    // EFFECTS: switches to the deck screen
    public void showDeckPanel() {
        screens.show(container, "deckPanel");
    }

    // MODIFIES: this
    // EFFECTS: switches to the card screen
    public void showCardPanel() {
        container.remove(cp);
        cp = new CardPanel(this);
        container.add(cp, "cardPanel");

        container.revalidate(); 
        container.repaint();

        screens.show(container, "cardPanel");
    }

    //EFFECTS: play the game
    public static void main(String args[]) {
        new DrawingSurface();
	}

}
