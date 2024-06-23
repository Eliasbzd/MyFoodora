package fr.cs.group12.myFoodora.test;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import fr.cs.group12.myFoodora.spaceTimeCoordinates.Position;
import fr.cs.group12.myFoodora.spaceTimeCoordinates.Time;
/**
 * This class tests the functionality of the Position and Time classes in the MyFoodora system.
 */
public class SpaceTimeCoordinatesTest {

    private Position position1;
    private Position position2;
    private Time time1;
    private Time time2;

    @Before
    public void setUp() {
        // Create mock data for testing
        position1 = new Position(0, 0);
        position2 = new Position(3, 4);
        time1 = new Time(1, 1, 2020, 0, 0, 0);
        time2 = new Time(1, 2, 2020, 0, 0, 0);
    }

    @Test
    public void testPositionDistanceTo() {
        double distance = position1.distanceTo(position2);
        assertEquals(5.0, distance, 0.001);
    }

    @Test
    public void testTimeIsBefore() {
        assertTrue(time1.isBefore(time2));
        assertFalse(time2.isBefore(time1));
    }

    @Test
    public void testTimeIsAfter() {
        assertTrue(time2.isAfter(time1));
        assertFalse(time1.isAfter(time2));
    }

    @Test
    public void testTimeToString() {
        assertEquals("01/01/2020 00:00:00", time1.toString());
        assertEquals("01/02/2020 00:00:00", time2.toString());
    }
}