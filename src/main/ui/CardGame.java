package ui;

import java.io.FileNotFoundException;
import java.io.IOException;
// Referenced from Lab 4 - Flashcard Reviewer
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import exceptions.EmptyListException;
import model.Card;
import model.Deck;
import model.DecksController;
import persistence.JsonReader;
import persistence.JsonWriter;
import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;

//Represents the card application
@ExcludeFromJacocoGeneratedReport
public class CardGame {

    private DecksController dc;
    private Deck currentDeck;

    private Scanner scanner;
    private boolean isProgramRunning;

    private static final String JSON_STORE = "./data/cardGame.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // EFFECTS: creates an instance of the CardGame console ui application
    public CardGame() {
        init();

        printDivider();
        System.out.println("Welcome to Cards Against Boredom!");

        while (this.isProgramRunning) {
            handleMenu();
        }
    }

    // MODIFIES: this
    // EFFECTS: initializes the application with the starting values
    public void init() {
        dc = new DecksController("Decks");
        currentDeck = null;
        this.scanner = new Scanner(System.in);
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        isProgramRunning = true;
    }

    // EFFECTS: displays and processes inputs for the main menu
    public void handleMenu() {
        displayMenu();
        String input = this.scanner.nextLine();
        processMenuCommands(input);
    }

    // EFFECTS: displays a list of commands that can be used in the main menu
    public void displayMenu() {
        printDivider();
        System.out.println("Please select an option:\n");
        System.out.println("a: Add a new deck");
        System.out.println("v: View all decks and select");
        System.out.println("s: save decks to file");
        System.out.println("l: load decks from file");
        System.out.println("q: Quit game");
        printDivider();
    }

    // EFFECTS: processes the user's input in the main menu
    public void processMenuCommands(String input) {
        printDivider();
        switch (input) {
            case "a":
                addNewDeck();
                break;
            case "v":
                viewDecks();
                break;
            case "s":
                saveGameState();
                break;
            case "l":
                loadGameState();
                break;
            case "q":
                quitGame();
                break;
            default:
                System.out.println("Invalid option inputted. Please try again.");
        }
        printDivider();
    }

    // EFFECTS: saves the state of the card game to file
    private void saveGameState() {
        try {
            jsonWriter.open();
            jsonWriter.write(dc);
            jsonWriter.close();
            System.out.println("Saved decks to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        } catch (NullPointerException e) {
            System.out.println("No data to save.");
        }
    }

    // MODIFIES: this
    // EFFECTS: loads the state of the card game from file
    private void loadGameState() {
        try {
            dc = jsonReader.read();
            System.out.println("Loaded decks from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: adds a Deck to the program
    public void addNewDeck() {
        System.out.println("\nPlease enter the deck's theme:");
        String theme = this.scanner.nextLine();

        Deck d = new Deck(theme);

        dc.addToController(d);
        System.out.println("\nNew deck created!");
    }

    // MODIFIES: this
    // EFFECTS: displays the list of all decks, handles inputs to select a deck
    public void viewDecks() {
        if (dc.getDecks().isEmpty()) {
            System.out.println("Error: No decks to view. Please create a deck first!");
            return;
        }
        displayDeckMenu();
        String input = "";

        input = this.scanner.nextLine();
        try {
            int i = Integer.parseInt(input) - 1;
            currentDeck = dc.getDecks().get(i);
            System.out.println(currentDeck.getDeckName() + " deck selected!");
            printDivider();
            handleMenuDeck();
        } catch (Exception e) {
            System.out.println("Invalid option inputted. Please try again.");
        }
    }

    // EFFECTS: displays a list of decks in the card deck view
    public void displayDeckMenu() {
        System.out.println("\nPlease select a deck by entering its number:\n");
        for (int i = 0; i < dc.getDecks().size(); i++) {
            System.out.println((i + 1) + ": " + dc.getDecks().get(i).getDeckName());
        }
    }

    // EFFECTS: displays and processes inputs for the deck menu
    public void handleMenuDeck() {
        displayCardMenu();
        String input = this.scanner.nextLine();
        handleViewMenuDeck(input);
    }

    // EFFECTS: displays a list of commands that can be used within the deck
    public void displayCardMenu() {
        printDivider();
        System.out.println("Please select an option:\n");
        System.out.println("a: Add a new card");
        System.out.println("r: Draw a random card");
        System.out.println("f: Draw a random card (filtered)");
        System.out.println("v: View all cards");
        System.out.println("x: Delete a card");
        System.out.println("q: Return to menu");
        printDivider();
    }

    // MODIFIES: this
    // EFFECTS: processes user's input within the deck viewing menu
    @SuppressWarnings("methodlength")
    public void handleViewMenuDeck(String input) {
        System.out.print("\n");

        switch (input) {
            case "a":
                addNewCard();
                break;
            case "r":
                drawCard();
                break;
            case "f":
                drawCardFiltered();
                break;
            case "v":
                displayAllCards();
                break;
            case "x":
                deleteCard();
                break;
            case "q":
                break;
            default:
                System.out.println("Invalid option inputted. Please try again.");
                handleMenuDeck();
        }
        if (!input.equals("q")) {
            handleMenuDeck();
        } else {
            System.out.println("Returning to the menu...");
            handleMenu();
        }
    }

    // MODIFIES: this
    // EFFECTS: displays the list of cards in this deck and handles inputs within
    // the menu
    public void displayAllCards() {
        List<Card> deck = currentDeck.getCards();
        if (deck.isEmpty()) {
            System.out.println("Error: No cards to view. Please create a card first!");
            return;
        } else {
            for (int i = 0; i < deck.size(); i++) {
                System.out.println((i + 1) + ": " + deck.get(i).getActivity());
                System.out.println("   " + deck.get(i).getDescription());
            }
        }
        printDivider();
    }

    // MODIFIES:
    // EFFECTS: adds a Card to the current Deck
    public void addNewCard() {
        System.out.println("Please enter the card's activity name:");
        String name = this.scanner.nextLine();

        System.out.println("\nIs the activity for the outdoors?");
        boolean loc = toggleCardLocation();

        System.out.println("Finally, please enter a brief description:");
        String desc = this.scanner.nextLine();

        Card c = new Card(name, loc, desc);

        this.currentDeck.addToDeck(c);
        System.out.println("\nNew activity card successfully created!");
        System.out.println("\nActivity: " + c.getActivity());
        System.out.println("Description: " + c.getDescription());
    }

    // MODIFIES: this
    // EFFECTS: draws a random card from the current deck
    public void drawCard() {
        if (currentDeck.getCards().isEmpty()) {
            System.out.println("Error: No cards to draw from. Please create some cards first!");
            return;
        }
        displayCard(currentDeck.pullRandomCard());
    }

    // MODIFIES: this
    // EFFECTS: draws a random card from a filtered version of the current deck
    public void drawCardFiltered() {
        if (currentDeck.getCards().isEmpty()) {
            System.out.println("Error: No cards to draw from. Please create some cards first!");
            return;
        }
        System.out.println("\nWould you like to filter for outdoor, or indoor activities?");

        boolean loc = toggleCardLocation();

        try {
            displayCard(currentDeck.pullRandomCard(loc));
        } catch (EmptyListException e) {
            System.out.println("\nThere are no activities of this type in your deck!");
        }
    }

    // EFFECTS: displays the given card
    public void displayCard(Card card) {
        System.out.println("\nHere is your random card!");
        System.out.println("\nActivity: " + card.getActivity());
        System.out.println("Description: " + card.getDescription());
    }

    // EFFECTS: deletes the card from the current deck
    public void deleteCard() {
        System.out.println("\nPlease select a card by entering its number:\n");
        displayAllCards();

        String input = this.scanner.nextLine();
        Card card = null;

        try {
            int i = Integer.parseInt(input) - 1;
            card = currentDeck.getCards().get(i);
            currentDeck.removeFromDeck(card);
            System.out.println(card.getActivity() + " card has been deleted.");
            printDivider();
        } catch (Exception e) {
            System.out.println("Invalid option inputted. Please try again.");
        }
    }

    // EFFECTS: handle user input to change the a card's location, and return the
    // location value
    public boolean toggleCardLocation() {
        boolean validInput = false;
        boolean loc = false;
        while (!validInput) {
            System.out.println("(enter t for outdoor, f for indoor)");

            String input = this.scanner.nextLine();

            if (input.equals("t")) {
                loc = true;
                validInput = true;
            } else if (input.equals("f")) {
                loc = false;
                validInput = true;
            } else {
                System.out.println("Invalid option inputted. Please try again.");
            }
        }
        return loc;
    }

    // MODIFIES: this
    // EFFECTS: prints a closing message and marks the program as not running
    public void quitGame() {
        System.out.println("Hope we added a little fun to your day :)");
        System.out.println("See you soon!");
        printDivider();
        this.isProgramRunning = false;
    }

    // EFFECTS: prints out a line of dashes to act as a divider
    private void printDivider() {
        System.out.println("------------------------------------");
    }
}
