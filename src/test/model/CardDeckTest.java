package model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CardDeckTest {

    Deck deck;
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

        deck = new Deck("cool");
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
        assertTrue((pullC1 > 8) && (pullC1 < 40));
        assertTrue((pullC2 > 8) && (pullC2 < 40));
    }

    @Test
    void testPullRandomCardFilterIndoor() {
        deck.addToDeck(c1);
        deck.addToDeck(c3);
        int pullC1 = 0;
        int pullC3 = 0;

        for (int i = 0; i < 50; i++) {
            if (c1.equals(deck.pullRandomCard(false))) {
                pullC1++;
            } else {
                pullC3++;
            }
        }
        assertTrue((pullC1 > 15) && (pullC1 < 35));
        assertTrue((pullC3 > 15) && (pullC3 < 35));
    }
    @Test
    void testPullRandomCardFilterOutdoor() {
        Card c4 = new Card("swim", true, "");
        deck.addToDeck(c2);
        deck.addToDeck(c4);
        int pullC2 = 0;
        int pullC4 = 0;

        for (int i = 0; i < 50; i++) {
            if (c2.equals(deck.pullRandomCard(true))) {
                pullC2++;
            } else {
                pullC4++;
            }
        }
        assertTrue((pullC2 > 10) && (pullC2 < 40));
        assertTrue((pullC4 > 10) && (pullC4 < 40));
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
