package persistence;
// Referenced from the JsonSerialization Demo
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

import org.json.*;

import model.Deck;

// Represents a reader that reads CardGame Decks from JSON data stored in file
public class JsonReader {
    
    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
    }

    // EFFECTS: reads Decks from file and returns it;
    // throws IOException if an error occurs reading data from file
    public List<Deck> read() throws IOException {
        return null;
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        return "";
    }

    // EFFECTS: parses workroom from JSON object and returns it
    private List<Deck> parseWorkRoom(JSONObject jsonObject) {
        return null;
    }

    // MODIFIES: decks
    // EFFECTS: parses decks from JSON object and adds them to CardGame Decks
    private void addDecks(List<Deck> decks, JSONObject jsonObject) {
    }

    // MODIFIES: decks
    // EFFECTS: parses deck from JSON object and adds it to CardGame Decks
    private void addDeck(List<Deck> decks, JSONObject jsonObject) {
    }
}
