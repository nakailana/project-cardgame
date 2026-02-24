package model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import exceptions.EmptyListException;

import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;

@ExcludeFromJacocoGeneratedReport
public class DecksControllerTest {

    Card c1, c2, c3;
    Deck d1, d2;
    DecksController dc;
    
    @BeforeEach
    void runBefore() {
        c1 = new Card("A", false, "");
        c2 = new Card("B", true, "ball");
        c3 = new Card("C", false, "");
        d1 = new Deck("d1");
        d1.addToDeck(c1);
        d1.addToDeck(c2);
        d1.addToDeck(c3);
        d2 = new Deck("d2");

        dc = new DecksController("1");
    }

    @Test
    void testConstructor() {
        assertEquals(0, dc.getDecks().size());
        assertEquals("1", dc.getName());
    }

    @Test 
    void testAddToDeckNotYetAdded() {
        dc.addToController(d1);
        assertEquals(1, dc.getDecks().size());
        assertEquals(d1, dc.getDecks().get(0));
        dc.addToController(d2);
        assertEquals(2, dc.getDecks().size());
        assertEquals(d2, dc.getDecks().get(1));
    }
    @Test 
    void testAddToDeckAddMultipleTimes() {
        dc.addToController(d1);
        dc.addToController(d1);
        assertEquals(1, dc.getDecks().size());
    }

}
