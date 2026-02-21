package persistence;
// Referenced from the JsonSerialization Demo
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;
import model.Card;
import model.Deck;

@ExcludeFromJacocoGeneratedReport
public class JsonTest {
    protected void checkDeck(String deckName, List<Card> cards, Deck deck) {
        assertEquals(deckName, deck.getDeckName());
        assertEquals(cards.size(), deck.getCards().size());
    }
}
