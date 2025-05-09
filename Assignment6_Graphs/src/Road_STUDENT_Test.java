import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class Road_STUDENT_Test {

	private Town townA, townB, townC;
	private Road road_AB_same;
	private Road road_AB_diff_weight;
	private Road road_AC_same_weight;
	private Road road_AB_diff_name;
	private Road road_BA_same;

	@Before
	public void setUp() throws Exception {
		townA = new Town("TownA");
		townB = new Town("TownB");
		townC = new Town("TownC");
		road_AB_same = new Road(townA, townB, 5, "RoadAB");
		road_AB_diff_weight = new Road(townA, townB, 7, "RoadAB");
		road_AC_same_weight = new Road(townA, townC, 5, "RoadAC");
		road_AB_diff_name = new Road(townA, townB, 5, "RouteAB");
		road_BA_same = new Road(townB, townA, 5, "RoadAB");
	}

	@After
	public void tearDown() {
		townA = townB = townC = null;
		road_AB_diff_name = road_AB_diff_weight = road_AB_same = road_AC_same_weight = road_BA_same = null;
	}
	
	@Test
	public void testGetName() {
		assertEquals("RoadAB", road_AB_same.getName());
		assertEquals("RoadAC", road_AC_same_weight.getName());
		assertEquals("RouteAB", road_AB_diff_name.getName());
	}

	@Test
	public void testGetSource() {
		assertEquals(townA, road_AB_same.getSource());
		assertEquals(townB, road_BA_same.getSource());
		assertEquals(townA, road_AC_same_weight.getSource());
	}

	@Test
	public void testGetDestination() {
		assertEquals(townB, road_AB_same.getDestination());
		assertEquals(townA, road_BA_same.getDestination());
		assertEquals(townC, road_AC_same_weight.getDestination());
	}

	@Test
	public void testGetWeight() {
		assertEquals(5, road_AB_same.getWeight());
		assertEquals(7, road_AB_diff_weight.getWeight());
		assertEquals(5, road_AC_same_weight.getWeight());
	}

	@Test
	public void testContains() {
		assertTrue(road_AB_same.contains(townA));
		assertTrue(road_AB_same.contains(townB));
		assertFalse(road_AB_same.contains(townC));
	}

	@Test
	public void testEquals() {
		assertEquals(road_AB_same, road_BA_same);
		assertNotEquals(road_AB_same, road_AB_diff_weight);
		assertNotEquals(road_AB_same, road_AC_same_weight);
		assertNotEquals(road_AB_same, road_AB_diff_name);
		assertNotEquals(road_AB_same, null);
		assertNotEquals(road_AB_same, new Object());
	}

	@Test
	public void testHashCode() {
		assertEquals(road_AB_same.hashCode(), road_BA_same.hashCode());
		assertNotEquals(road_AB_same.hashCode(), road_AB_diff_weight.hashCode());
		assertNotEquals(road_AB_same.hashCode(), road_AC_same_weight.hashCode());
		assertNotEquals(road_AB_same.hashCode(), road_AB_diff_name.hashCode());
	}

	@Test
	public void testCompareTo() {
		assertTrue(road_AB_same.compareTo(road_AC_same_weight) < 0);
		assertTrue(road_AC_same_weight.compareTo(road_AB_same) > 0);
		assertEquals(0, road_AB_same.compareTo(road_BA_same));
	}
}