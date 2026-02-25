package persistence;

// Referenced from the JsonSerialization Demo
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.json.*;

import model.Card;
import model.Deck;
import model.DecksController;

// Represents a reader that reads CardGame Decks from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads Decks from file and returns it;
    // throws IOException if an error occurs reading data from file
    public DecksController read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseDecks(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses decksController from JSON object
    private DecksController parseDecks(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        DecksController decks = new DecksController(name);
        addDecks(decks, jsonObject);
        return decks;
    }

    // MODIFIES: decks
    // EFFECTS: parses decks from JSON object and adds them to the controller
    private void addDecks(DecksController dc, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("decks");
        for (Object json : jsonArray) {
            JSONObject nextDeck = (JSONObject) json;
            addDeck(dc, nextDeck);
        }
    }

    // MODIFIES: decks
    // EFFECTS: parses deck from JSON object and adds it to CardGame Decks
    private void addDeck(DecksController dc, JSONObject jsonObject) {
        String name = jsonObject.getString("theme");
        Deck deck = new Deck(name);
        addCards(deck, jsonObject);
        dc.addToController(deck);
    }

    // MODIFIES: decks
    // EFFECTS: parses cards from JSON object and adds them to deck
    private void addCards(Deck deck, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("cards");
        for (Object json : jsonArray) {
            JSONObject nextCard = (JSONObject) json;
            addCard(deck, nextCard);
        }
    }

    // MODIFIES: decks
    // EFFECTS: parses card from JSON object and adds it deck
    private void addCard(Deck deck, JSONObject jsonObject) {
        String activity = jsonObject.getString("activity");
        String desc = jsonObject.getString("description");
        boolean outdoor = jsonObject.getBoolean("outdoor");
        Card card = new Card(activity, outdoor, desc);
        deck.addToDeck(card);
    }
}
