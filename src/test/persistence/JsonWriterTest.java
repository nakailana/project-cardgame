package persistence;
// Referenced from the JsonSerialization Demo
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

import org.junit.jupiter.api.Test;

import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;
import model.Deck;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExcludeFromJacocoGeneratedReport
class JsonWriterTest extends JsonTest {
    //NOTE TO CPSC 210 STUDENTS: the strategy in designing tests for the JsonWriter is to
    //write data to a file and then use the reader to read it back in and check that we
    //read in a copy of what was written out.

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
    void testWriterEmptyWorkroom() {
        try {
            List<Deck> ds = new ArrayList<Deck>();
            JsonWriter writer = new JsonWriter("./data/testReaderEmptyDecks.json");
            writer.open();
            writer.write(ds);
            writer.close();

            JsonReader reader = new JsonReader("./data/testReaderEmptyDecks.json");
            ds = reader.read();
            assertEquals(0, ds.size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralWorkroom() {
        try {
            List<Deck> ds = new ArrayList<Deck>();
            ds.add(new Deck("theme1"));
            ds.add(new Deck("theme2"));
            JsonWriter writer = new JsonWriter("./data/testReaderGeneralDecks.json");
            writer.open();
            writer.write(ds);
            writer.close();

            JsonReader reader = new JsonReader("./data/testReaderGeneralDecks.json");
            ds = reader.read();
            List<Deck> decks = ds;
            assertEquals(2, decks.size());
            checkDeck("theme1", decks.get(0).getCards(), decks.get(0));
            checkDeck("theme2", decks.get(0).getCards(), decks.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}