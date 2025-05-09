import java.util.ArrayList;

public class TownGraphManager implements TownGraphManagerInterface{

	Graph graph;
	
	public TownGraphManager() {
		graph = new Graph();
	}
	
	@Override
	public boolean addRoad(String town1, String town2, int weight, String roadName) {
		try {
			graph.addEdge(new Town(town1), new Town(town2), weight, roadName);
			return true;
		}catch(IllegalArgumentException e) {
			return false;
		}
	}

	@Override
	public String getRoad(String town1, String town2) {
		Road road = graph.getEdge(new Town(town1), new Town(town2));
		if (road != null)
			return road.getName();
		return null;
	}

	@Override
	public boolean addTown(String v) {
		return graph.addVertex(new Town(v));
	}

	@Override
	public Town getTown(String name) {
		return new Town(name);
	}

	@Override
	public boolean containsTown(String v) {
		return graph.containsVertex(new Town(v));
	}

	@Override
	public boolean containsRoadConnection(String town1, String town2) {
		return graph.containsEdge(new Town(town1), new Town(town2));
	}

	@Override
	public ArrayList<String> allRoads() {
		ArrayList<String> output = new ArrayList<String>();
		for (Road road : graph.edgeSet()) {
			output.add(road.getName());
		}
		output.sort(null);
		return output;
	}

	@Override
	public boolean deleteRoadConnection(String town1, String town2, String road) {
		return graph.removeEdge(new Town(town1), new Town(town2), -1, road) != null;
	}

	@Override
	public boolean deleteTown(String v) {
		return graph.removeVertex(new Town(v));
	}

	@Override
	public ArrayList<String> allTowns() {
		ArrayList<String> output = new ArrayList<String>();
		for (Town town : graph.vertexSet()) {
			output.add(town.getName());
		}
		output.sort(null);
		return output;
	}

	@Override
	public ArrayList<String> getPath(String town1, String town2) {
		return graph.shortestPath(new Town(town1), new Town(town2));
	}

}
