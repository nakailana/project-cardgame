package model;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CardTest {

    Card c1, c2, c3;
    ArrayList<Card> d1, d2, testD1, testD2;
    List<ArrayList<Card>> decks, testDs;
    
    @BeforeEach
    void runBefore() {
        c1 = new Card("A", "");
        c2 = new Card("B", "ball");
        c2 = new Card("C", "");
        d1 = new ArrayList<Card>();
        d1.add(c1);
        d1.add(c2);
        d1.add(c3);
        d2 = new ArrayList<Card>();
        d2.add(c3);
        decks = new ArrayList<ArrayList<Card>>();
        decks.add(d1);
        decks.add(d2);

        testD1 = new ArrayList<Card>();
        testD2 = new ArrayList<Card>();
        testDs = new ArrayList<ArrayList<Card>>();
        testDs.add(testD1);
        testDs.add(testD2);
    }

    @Test
    void testConstructor() {
        assertEquals("B", c2.getActivity());
        assertEquals("ball", c2.getDescription());
    }

    @Test
    void testAddCardToDecksCardNotYetAdded(){
        testD1.add(c1);
        testD1.add(c2);
        c3.addToDecks(testDs);
        assertEquals(decks, testDs);
    }
    @Test
    void testAddCardToDecksCardAlreadyAdded(){
        testD1.add(c1);
        testD1.add(c2);
        testD1.add(c3);
        testD2.add(c3);
        testD2.add(c1);

        c1.addToDecks(decks);
        assertEquals(testDs, decks);
    }

    @Test
    void testRemoveAllInstancesMultipleInstances(){
        testD1.add(c1);
        testD1.add(c2);
        c3.removeAllInstances(decks);
        assertEquals(testDs, decks);
    }
    @Test
    void testRemoveAllInstancesNoInstances(){
        testD1.add(c1);
        testD1.add(c2);
        testD1.add(c3);
        testD2.add(c3);
        Card c4 = new Card("random", "");
        c4.removeAllInstances(decks);
        assertEquals(testDs, decks);
    }
}
