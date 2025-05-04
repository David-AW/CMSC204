import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;
import java.util.Set;

public class Graph implements GraphInterface<Town, Road>{

	private HashMap<Town, HashMap<Town, Road>> map;
	
	public Graph() {
		map = new HashMap<Town, HashMap<Town, Road>>();
	}
	
	@Override
	public Road getEdge(Town source, Town destination) {
		if (!map.containsKey(source))
			return null;
		if (!map.get(source).containsKey(destination))
			return null;
		return map.get(source).get(destination);
	}

	@Override
	public Road addEdge(Town source, Town destination, int weight, String description) {
		addVertex(source);
		addVertex(destination);
		Road edge =  new Road(source, destination, weight, description);
		map.get(source).put(destination, edge);
		return edge;
	}

	@Override
	public boolean addVertex(Town v) {
		if (!map.containsKey(v)) {
			map.put(v, new HashMap<Town, Road>());
			return true;
		}
		return false;
	}

	@Override
	public boolean containsEdge(Town source, Town destination) {
		return getEdge(source, destination) != null;
	}

	@Override
	public boolean containsVertex(Town v) {
		return map.containsKey(v);
	}

	@Override
	public Set<Road> edgeSet() {
		Set<Road> roads = new HashSet<Road>();
		for (Town vertex : map.keySet()) {
			for (Entry<Town, Road> entry : map.get(vertex).entrySet()) {
				roads.add(entry.getValue());
			}
		}
		return roads;
	}

	@Override
	public Set<Road> edgesOf(Town vertex) {
		Set<Road> roads = new HashSet<Road>();
		for (Entry<Town, Road> entry : map.get(vertex).entrySet()) {
			roads.add(entry.getValue());
		}
		return roads;
	}

	@Override
	public Road removeEdge(Town source, Town destination, int weight, String description) {
		Road edge = getEdge(source, destination);
		
		boolean weight_matches = weight < 0;
		boolean desc_matches = (description == null);
		
		if (edge == null)
			return null;
		
		if (!weight_matches)
			weight_matches = edge.getWeight() == weight;
		if (!desc_matches)
			desc_matches = edge.getName().equals(description);
		
		if (!weight_matches || !desc_matches)
			return null;
		
		map.get(source).remove(destination);
		
		return edge;
	}

	@Override
	public boolean removeVertex(Town v) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Set<Town> vertexSet() {
		return map.keySet();
	}

	@Override
	public ArrayList<String> shortestPath(Town source, Town destination) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void dijkstraShortestPath(Town source) {
		// TODO Auto-generated method stub
		
	}



}
