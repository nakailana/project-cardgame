package ui;

import java.util.List;
import java.util.Scanner;

import model.Card;
import model.Deck;

public class CardGame {

    // EFFECTS: creates an instance of the CardGame console ui application
    public CardGame() {
        //stub
    }

    // MODIFIES: this
    // EFFECTS: initializes the application with the starting values
    public void init() {
        //stub
    }

    // EFFECTS: displays and processes inputs for the main menu
    public void handleMenu() {
        //stub
    }

    // EFFECTS: displays a list of commands that can be used in the main menu
    public void displayMenu() {
        //stub
    }

    // EFFECTS: processes the user's input in the main menu
    public void processMenuCommands(String input) {
        //stub
    }

    // MODIFIES: this
    // EFFECTS: adds a Deck to the program
    public void addNewDeck() {
        //stub
    }
    // MODIFIES:
    // EFFECTS: adds a Card to the current Deck
    public void addNewCard() {
        //stub
    }

    // MODIFIES: this
    // EFFECTS: displays all Decks one at a time
    public void viewDecks() {
        //stub
    }

    // MODIFIES: this
    // EFFECTS: draws a random card from the current deck
    public void drawCard() {
       //stub
    }

    // EFFECTS: displays a list of commands that can be used in the card deck menu
    public void displayViewMenu() {
        //stub
    }

    // MODIFIES: this
    // EFFECTS: displays the list of cards in this deck and handles inputs related to viewing the cards
    public void displayAllCards(List<Card> deck) {
        //stub
    }

    // EFFECTS: displays the given card
    public void displayCard(Card card) {
        //stub
    }

    // MODIFIES: this
    // EFFECTS: processes user's input for the card deck viewing menu
    public void handleViewMenu(String input, List<Deck> decks) {
        //stub
    }
    // MODIFIES: this
    // EFFECTS: processes user's input within the deck viewing menu
    public void handleViewDeckMenu(String input, List<Card> deck) {
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
    public void closeApplication() {
       //stub
    }

    // EFFECTS: prints out a line of dashes to act as a divider
    private void printDivider() {
        //stub
    }
}
