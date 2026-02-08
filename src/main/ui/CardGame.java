package ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.apple.laf.resources.aqua;

import model.Card;
import model.Deck;

public class CardGame {

    private List<Deck> decks;
    private Deck currentDeck;

    private Scanner scanner;
    private boolean isProgramRunning;

    // EFFECTS: creates an instance of the CardGame console ui application
    public CardGame() {
        init();

        printDivider();
        System.out.println("Welcome to Cards Against Boredom!");
        printDivider();

        while (this.isProgramRunning) {
            handleMenu();
        }
    }

    // MODIFIES: this
    // EFFECTS: initializes the application with the starting values
    public void init() {
        decks = new ArrayList<Deck>();
        currentDeck = null;
        this.scanner = new Scanner(System.in);addNewCard();
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
        System.out.println("Please select an option:\n");
        System.out.println("a: Add a new Deck");
        System.out.println("v: View all Decks");
        System.out.println("q: Exit the application");
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
            case "q":
                quitGame();
                break;
            default:
                System.out.println("Invalid option inputted. Please try again.");
        }
        printDivider();
    }

    // MODIFIES: this
    // EFFECTS: adds a Deck to the program
    public void addNewDeck() {
        System.out.println("\nPlease enter the deck's theme:");
        String theme = this.scanner.nextLine();

        Deck d = new Deck(theme);

        this.decks.add(d);
        System.out.println("\nNew deck created!");
    }

    // MODIFIES: this
    // EFFECTS: displays the list of all decks, handles inputs to select a deck
    public void viewDecks() {
        if (decks.isEmpty()) {
            System.out.println("Error: No decks to view. Please create a deck first!");
            return;
        }
        displayDeckMenu();
        String input = "";
        while (!input.equals("q")) {
            input = this.scanner.nextLine();
            try { 
                int i = Integer.parseInt(input) - 1;
                currentDeck = decks.get(i);
                System.out.println(currentDeck.getDeckName() + " deck selected!");
            } catch (Exception e) {
                System.out.println("Invalid option inputted. Please try again.");
            }
        }
        handleViewMenuDeck(input, currentDeck);
    }

    // EFFECTS: displays a list of decks/commands that can be used in the card deck menu
    public void displayDeckMenu() {
        System.out.println("Please select a deck by entering its number:\n");
        for (int i = 0; i < decks.size(); i++) {
            System.out.println((i + 1) + ": " + decks.get(i).getDeckName());
        }
        System.out.println("Enter 'q' to return to the menu.");
    }

    // EFFECTS: displays a list of commands that can be used within the deck
    public void displayCardMenu() {
        System.out.println("Please select an option:\n");
        System.out.println("a: Add a new Card");
        System.out.println("r: Draw a random Card");
        System.out.println("f: Draw a random Card (filtered)");
        System.out.println("v: View all Cards");
        System.out.println("q: Exit the application");
        printDivider();
    }
    // MODIFIES: this
    // EFFECTS: processes user's input within the deck viewing menu
    public void handleViewMenuDeck(String input, Deck deck) {
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
            case "q":
                System.out.println("Returning to the menu...");
                break;
            default:
                System.out.println("Invalid option inputted. Please try again.");
        }
    }

    // MODIFIES: this
    // EFFECTS: displays the list of cards in this deck and handles inputs within the menu
    public void displayAllCards(List<Card> deck) {
        //stub
    }

    // MODIFIES:
    // EFFECTS: adds a Card to the current Deck
    public void addNewCard() {
        //stub
    }

    // MODIFIES: this
    // EFFECTS: draws a random card from the current deck
    public void drawCard() {
       //stub
    }
    // MODIFIES: this
    // EFFECTS: draws a random card from a filtered version of the current deck
    public void drawCardFiltered() {
       //stub
    }

    // EFFECTS: displays the given card
    public void displayCard(Card card) {
        //stub
    }

    // MODIFIES: Card
    // EFFECTS: change the given card's recommended location
    public void toggleCardLocation(Boolean input, Card card) {
        //stub
    }
    // MODIFIES: Card
    // EFFECTS: change the given card's description based on user input
    public void changeDescription(String input, Card card) {
        //stub
    }

    // MODIFIES: this
    // EFFECTS: if there is another card in the deck, increments the current card index
    public void getNextCard(List<Card> cards) {
        //stub
    }

    // MODIFIES: this
    // EFFECTS: if there is a previous card in the deck, decrements the current card index
    public void getPreviousCard() {
        //stub
    }

    // MODIFIES: cards
    // EFFECTS: deletes the card from the deck 
    public void deleteCard(List<Card> cards) {
       //stub
    }

    // MODIFIES: this
    // EFFECTS: prints a closing message and marks the program as not running
    public void quitGame() {
        System.out.println("Hope we added a little fun to your day :)");
        System.out.println("See you soon!");
        this.isProgramRunning = false;
    }

    // EFFECTS: prints out a line of dashes to act as a divider
    private void printDivider() {
        //stub
    }
}
