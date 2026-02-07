package model;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CardDeckTest {
    Card c1, c2, c3;
    ArrayList<Card> d1, d2, testD1, testD2;
    
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
        d2.add(c3);

        testD1 = new ArrayList<Card>();
        testD2 = new ArrayList<Card>();

    }

    @Test
    void sampleTest() {
        assertTrue(true);
    }
}
