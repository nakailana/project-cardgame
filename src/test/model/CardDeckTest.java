package model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CardDeckTest {

    CardDeck deck;
    Card c1, c2, c3;
    ArrayList<Card> d1, d2;
    
    @BeforeEach
    void runBefore() {
        c1 = new Card("A", false, "");
        c2 = new Card("B", true, "ball");
        c3 = new Card("C", false, "");
        d1 = new ArrayList<Card>();
        d1.add(c1);
        d1.add(c2);
        d1.add(c3);
        d2 = new ArrayList<Card>();

        deck = new CardDeck("cool");
    }

    @Test
    void testConstructor() {
        assertEquals("cool", deck.getDeckName());
    }

    @Test 
    void testAddToDeckNotYetAdded() {
        deck.addToDeck(c1);
        d2.add(c1);
        assertEquals(d2, deck.getCards());
    }
    @Test 
    void testAddToDeckAddMultipleTimes() {
        deck.addToDeck(c1);
        deck.addToDeck(c1);
        d2.add(c1);
        assertEquals(d2, deck.getCards());
    }

    @Test
    void testPullRandomCardNoFilter() {
        deck.addToDeck(c1);
        deck.addToDeck(c2);
        deck.addToDeck(c3);

        int pullC1 = 0;
        int pullC2 = 0;
        for (int i = 0; i < 60; i++) {
            if (c1.equals(deck.pullRandomCard())) {
                pullC1++;
            } else if (c2.equals(deck.pullRandomCard())) {
                pullC2++;
            }
        }
        assertTrue((pullC1 > 5) && (pullC1 < 45));
        assertTrue((pullC2 > 5) && (pullC2 < 45));
    }

    @Test
    void testPullRandomCardFilterIndoor() {
    }
    @Test
    void testPullRandomCardFilterOutdoor() {
        Card c4 = new Card("swim", true, "");
    }

    @Test 
    void testFilterCardsIndoor() {
        deck.addToDeck(c1);
        deck.addToDeck(c2);
        deck.addToDeck(c3);
        d2.add(c1);
        d2.add(c3);
        assertEquals(d2, deck.filterCards(false));
    }
    @Test 
    void testFilterCardsOutdoor() {
        deck.addToDeck(c1);
        deck.addToDeck(c2);
        deck.addToDeck(c3);
        d2.add(c2);
        assertEquals(d2, deck.filterCards(true));
    }
}
