package model;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CardTest {

    Card c1;
    
    @BeforeEach
    void runBefore() {
        c1 = new Card("A1", "A", "");
    }

    @Test
    void testConstructor() {
        assertEquals("A1", c1.getActivity());
        assertEquals("A", c1.getDeckName());
        assertEquals("", c1.getDescription());
    }
    @Test
    void testAddCardToDecks(){
        
    }
}
