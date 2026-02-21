package persistence;
// Referenced from the JsonSerialization Demo
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

import org.junit.jupiter.api.Test;

import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;
import model.Card;
import model.Deck;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExcludeFromJacocoGeneratedReport
class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            List<Deck> ds = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyCardDecks() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyDecks.json");
        try {
            List<Deck> ds = reader.read();
            assertEquals(0, ds.size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralCardDecks() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralDecks.json");
        try {
            List<Deck> ds = reader.read();
            assertEquals(2, ds.size());
            Deck deck = ds.get(0);
            assertEquals(0, deck.getCards().size());
            checkDeck("theme1",  new ArrayList<Card>(), ds.get(0));
            checkDeck("theme2", new ArrayList<Card>(), ds.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}