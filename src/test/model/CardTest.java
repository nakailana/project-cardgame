package model;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CardTest {

    Card c1, c2, c3;
    
    @BeforeEach
    void runBefore() {
        c1 = new Card("A", false, "Indoor activity.");
        c2 = new Card("B", true, "Outdoor activity. ball");
        c3 = new Card("C", false, "Indoor activity.");
    }

    @Test
    void testConstructorOutdoor() {
        assertEquals("B", c2.getActivity());
        assertTrue(c2.getLocation());
        assertEquals("Outdoor activity. ball", c2.getDescription());
    }
    @Test
    void testConstructorIndoor() {
        assertEquals("A", c1.getActivity());
        assertFalse(c1.getLocation());
        assertEquals("Indoor activity", c1.getDescription());
    }

    @Test
    void updateLocationTestChangeDesc(){
        c1.updateLocation(true);
        assertTrue(c1.getLocation());
        assertEquals("Outdoor activity.", c1.getDescription());
    }
    @Test
    void updateLocationTestNoChangeDesc(){
        c1.updateLocation(false);
        assertFalse(c1.getLocation());
        assertEquals("Indoor activity.", c1.getDescription());
    }

    @Test
    void updateDescriptionNoChangeLocation(){
        c1.updateDescription("eat apples");
        assertEquals("Indoor activity. eat apples", c1.getDescription());
    }
}
