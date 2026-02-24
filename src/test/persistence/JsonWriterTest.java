package persistence;
// Referenced from the JsonSerialization Demo
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

import org.junit.jupiter.api.Test;

import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;
import model.Deck;
import model.DecksController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExcludeFromJacocoGeneratedReport
class JsonWriterTest extends JsonTest {

    @Test
    void testWriterInvalidFile() {
        try {
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyCardDeck() {
        try {
            DecksController dc = new DecksController("1");
            JsonWriter writer = new JsonWriter("./data/testReaderEmptyDecks.json");
            writer.open();
            writer.write(dc);
            writer.close();

            JsonReader reader = new JsonReader("./data/testReaderEmptyDecks.json");
            dc = reader.read();
            assertEquals(0, dc.getDecks().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralCardDeck() {
        try {
            DecksController dc = new DecksController("1");
            dc.addToController(new Deck("theme1"));
            dc.addToController(new Deck("theme2"));
            JsonWriter writer = new JsonWriter("./data/testReaderGeneralDecks.json");
            writer.open();
            writer.write(dc);
            writer.close();

            JsonReader reader = new JsonReader("./data/testReaderGeneralDecks.json");
            dc = reader.read();
            List<Deck> decks = dc.getDecks();
            assertEquals(2, decks.size());
            checkDeck("theme1", 2, decks.get(0));
            checkDeck("theme2", 1, decks.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}