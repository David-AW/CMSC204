import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class Town_STUDENT_Test {

	private Town townA, townB, townA_alias, townC;

	@Before
	public void setUp() throws Exception {
		townA = new Town("TownA");
		townB = new Town("TownB");
		townA_alias = new Town("TownA");
		townC = new Town("CityC");
	}

	@After
	public void tearDown() {
		townA = townB = townA_alias = townC = null;
	}
	
	@Test
	public void testGetName() {
		assertEquals("TownA", townA.getName());
		assertEquals("TownB", townB.getName());
		assertEquals("CityC", townC.getName());
	}

	@Test
	public void testEquals() {
		assertEquals(townA, townA_alias);
		assertEquals(townA_alias, townA);
		assertNotEquals(townA, townB);
		assertNotEquals(townB, townA);
		assertNotEquals(townA, null);
		assertNotEquals(townA, "TownA");
	}

	@Test
	public void testHashCode() {
		assertEquals(townA.hashCode(), townA_alias.hashCode());
		assertNotEquals(townA.hashCode(), townB.hashCode());
	}

	@Test
	public void testToString() {
		assertEquals("TownA", townA.toString());
		assertEquals("TownB", townB.toString());
		assertEquals("CityC", townC.toString());
	}

	@Test
	public void testCompareTo() {
		assertTrue(townA.compareTo(townB) < 0);
		assertTrue(townB.compareTo(townA) > 0);
		assertEquals(0, townA.compareTo(townA_alias));
		assertTrue(townC.compareTo(townA) < 0);
	}
}