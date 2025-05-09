import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TownGraphManager_STUDENT_Test {

	private TownGraphManagerInterface graph;
	private String[] town;

	@Before
	public void setUp() throws Exception {
		graph = new TownGraphManager();
		town = new String[7];

		for (int i = 0; i < 7; i++) {
			town[i] = "City_" + (char)('A' + i);
			graph.addTown(town[i]);
		}

		graph.addRoad(town[0], town[1], 5, "AB");
		graph.addRoad(town[0], town[2], 3, "AC");
		graph.addRoad(town[1], town[3], 2, "BD");
		graph.addRoad(town[2], town[3], 4, "CD");
		graph.addRoad(town[2], town[4], 6, "CE");
		graph.addRoad(town[3], town[5], 1, "DF");
		graph.addRoad(town[4], town[6], 3, "EG");
		graph.addRoad(town[3], town[6], 5, "DG");
	}

	@After
	public void tearDown() throws Exception {
		graph = null;
	}

	@Test
	public void testAddRoad() {
		ArrayList<String> roads = graph.allRoads();
		assertEquals(8, roads.size());
		assertTrue(roads.contains("AB"));
		assertTrue(roads.contains("AC"));
		graph.addRoad(town[5], town[6], 2, "FG");
		roads = graph.allRoads();
		assertEquals(9, roads.size());
		assertTrue(roads.contains("FG"));
	}

	@Test
	public void testGetRoad() {
		assertEquals("AB", graph.getRoad(town[0], town[1]));
		assertEquals("AC", graph.getRoad(town[0], town[2]));
		assertNull(graph.getRoad(town[0], town[3]));
	}

	@Test
	public void testAddTown() {
		assertFalse(graph.containsTown("City_H"));
		graph.addTown("City_H");
		assertTrue(graph.containsTown("City_H"));
	}

	@Test
	public void testContainsTown() {
		assertTrue(graph.containsTown("City_A"));
		assertFalse(graph.containsTown("City_Z"));
	}

	@Test
	public void testContainsRoadConnection() {
		assertTrue(graph.containsRoadConnection(town[0], town[1]));
		assertFalse(graph.containsRoadConnection(town[0], town[3]));
	}

	@Test
	public void testAllRoads() {
		ArrayList<String> roads = graph.allRoads();
		assertEquals(8, roads.size());
		Collections.sort(roads);
		assertEquals("AB", roads.get(0));
		assertEquals("AC", roads.get(1));
		assertEquals("BD", roads.get(2));
		assertEquals("CD", roads.get(3));
		assertEquals("CE", roads.get(4));
		assertEquals("DF", roads.get(5));
		assertEquals("DG", roads.get(6));
		assertEquals("EG", roads.get(7));
	}

	@Test
	public void testDeleteRoadConnection() {
		assertTrue(graph.containsRoadConnection(town[0], town[1]));
		graph.deleteRoadConnection(town[0], town[1], "AB");
		assertFalse(graph.containsRoadConnection(town[0], town[1]));
	}

	@Test
	public void testDeleteTown() {
		assertTrue(graph.containsTown("City_A"));
		graph.deleteTown("City_A");
		assertFalse(graph.containsTown("City_A"));
		assertFalse(graph.containsRoadConnection(town[0], town[1]));
		assertFalse(graph.containsRoadConnection(town[0], town[2]));
	}

	@Test
	public void testAllTowns() {
		ArrayList<String> towns = graph.allTowns();
		assertEquals(7, towns.size());
		Collections.sort(towns);
		assertEquals("City_A", towns.get(0));
		assertEquals("City_B", towns.get(1));
		assertEquals("City_C", towns.get(2));
		assertEquals("City_D", towns.get(3));
		assertEquals("City_E", towns.get(4));
		assertEquals("City_F", towns.get(5));
		assertEquals("City_G", towns.get(6));
	}

	@Test
	public void testGetPath() {
		ArrayList<String> path = graph.getPath(town[0], town[5]);
		assertNotNull(path);
		assertEquals(3, path.size());
		assertEquals("City_A via AC to City_C 3 mi", path.get(0).trim());
		assertEquals("City_C via CD to City_D 4 mi", path.get(1).trim());
		assertEquals("City_D via DF to City_F 1 mi", path.get(2).trim());
	}

	@Test
	public void testGetPathWithNoConnection() {
		graph.deleteRoadConnection(town[0], town[1], "AB");
		graph.deleteRoadConnection(town[0], town[2], "AC");
		ArrayList<String> path = graph.getPath(town[0], town[5]);
		assertNotNull(path);
		assertEquals(0, path.size());
	}

	@Test
	public void testGetPathWithMultiplePaths() {
		ArrayList<String> path = graph.getPath(town[1], town[6]);
		assertNotNull(path);
		assertTrue(path.size() > 0);

		assertTrue(path.get(path.size() - 1).contains("City_G"));
	}
}