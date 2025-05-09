import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

public class Graph_STUDENT_Test {

	private GraphInterface<Town, Road> graph;
	private Town[] city;

	@BeforeEach
	public void setUp() throws Exception {
		graph = new Graph();
		city = new Town[7];

		for (int i = 0; i < 7; i++) {
			city[i] = new Town("City_" + (char) ('A' + i));
			graph.addVertex(city[i]);
		}

		graph.addEdge(city[0], city[1], 5, "AB");
		graph.addEdge(city[0], city[2], 3, "AC");
		graph.addEdge(city[1], city[3], 2, "BD");
		graph.addEdge(city[2], city[3], 4, "CD");
		graph.addEdge(city[2], city[4], 6, "CE");
		graph.addEdge(city[3], city[5], 1, "DF");
		graph.addEdge(city[4], city[6], 3, "EG");
		graph.addEdge(city[3], city[6], 5, "DG");
	}

	@AfterEach
	public void tearDown() throws Exception {
		graph = null;
	}

	@Test
	public void testGetEdge() {
		assertEquals(new Road(city[0], city[1], 5, "AB"), graph.getEdge(city[0], city[1]));
		assertEquals(new Road(city[2], city[4], 6, "CE"), graph.getEdge(city[2], city[4]));
		assertNull(graph.getEdge(city[0], city[3]));
	}

	@Test
	public void testAddEdge() {
		assertFalse(graph.containsEdge(city[1], city[4]));
		graph.addEdge(city[1], city[4], 7, "BE");
		assertTrue(graph.containsEdge(city[1], city[4]));
		assertEquals(new Road(city[1], city[4], 7, "BE"), graph.getEdge(city[1], city[4]));
	}

	@Test
	public void testAddVertex() {
		Town newCity = new Town("City_H");
		assertFalse(graph.containsVertex(newCity));
		graph.addVertex(newCity);
		assertTrue(graph.containsVertex(newCity));
	}

	@Test
	public void testContainsEdge() {
		assertTrue(graph.containsEdge(city[0], city[2]));
		assertFalse(graph.containsEdge(city[1], city[5]));
	}

	@Test
	public void testContainsVertex() {
		assertTrue(graph.containsVertex(new Town("City_D")));
		assertFalse(graph.containsVertex(new Town("City_Z")));
	}

	@Test
	public void testEdgeSet() {
		Set<Road> roads = graph.edgeSet();
		assertEquals(8, roads.size());
		ArrayList<String> roadNames = new ArrayList<>();
		for (Road road : roads) {
			roadNames.add(road.getName());
		}
		Collections.sort(roadNames);
		assertEquals("AB", roadNames.get(0));
		assertEquals("AC", roadNames.get(1));
		assertEquals("BD", roadNames.get(2));
		assertEquals("CD", roadNames.get(3));
		assertEquals("CE", roadNames.get(4));
		assertEquals("DF", roadNames.get(5));
		assertEquals("DG", roadNames.get(6));
		assertEquals("EG", roadNames.get(7));
	}

	@Test
	public void testEdgesOf() {
		Set<Road> roadsFromC = graph.edgesOf(city[2]);
		assertEquals(3, roadsFromC.size());
		Set<String> roadNamesFromC = new HashSet<>();
		for (Road road : roadsFromC) {
			roadNamesFromC.add(road.getName());
		}
		assertTrue(roadNamesFromC.contains("AC"));
		assertTrue(roadNamesFromC.contains("CD"));
		assertTrue(roadNamesFromC.contains("CE"));
	}

	@Test
	public void testRemoveEdge() {
		assertTrue(graph.containsEdge(city[1], city[3]));
		graph.removeEdge(city[1], city[3], 2, "BD");
		assertFalse(graph.containsEdge(city[1], city[3]));
	}

	@Test
	public void testRemoveVertex() {
		assertTrue(graph.containsVertex(city[3]));
		graph.removeVertex(city[3]);
		assertFalse(graph.containsVertex(city[3]));

		assertNull(graph.getEdge(city[1], city[3]));
		assertNull(graph.getEdge(city[2], city[3]));
		assertNull(graph.getEdge(city[3], city[5]));
		assertNull(graph.getEdge(city[3], city[6]));
	}

	@Test
	public void testVertexSet() {
		Set<Town> vertices = graph.vertexSet();
		assertEquals(7, vertices.size());
		assertTrue(vertices.contains(city[0]));
		assertTrue(vertices.contains(city[5]));
		assertFalse(vertices.contains(new Town("City_Z")));
	}

	@Test
	public void testShortestPath() {
		ArrayList<String> path = graph.shortestPath(city[0], city[5]);
		assertNotNull(path);
		assertEquals(3, path.size());
		assertEquals("City_A via AC to City_C 3 mi", path.get(0).trim());
		assertEquals("City_C via CD to City_D 4 mi", path.get(1).trim());
		assertEquals("City_D via DF to City_F 1 mi", path.get(2).trim());
	}

	@Test
	public void testShortestPathNoPath() {
		Town newCity = new Town("City_H");
		graph.addVertex(newCity);
		ArrayList<String> path = graph.shortestPath(city[0], newCity);
		assertNotNull(path);
		assertEquals(0, path.size());
	}
}