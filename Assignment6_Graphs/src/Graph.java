import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map.Entry;
import java.util.Set;

public class Graph implements GraphInterface<Town, Road>{

	private HashMap<Town, HashMap<Town, Road>> map;
	private HashMap<Town, Path> shortest_path;
	
	public Graph() {
		map = new HashMap<Town, HashMap<Town, Road>>();
		shortest_path = new HashMap<Town, Path>();
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
		if (!map.containsKey(v))
			return false;
		for (Town vertex : map.keySet()) {
			for (Town endpoint : map.get(vertex).keySet())
				if(endpoint.equals(v))
					map.get(vertex).remove(endpoint);
		}
		map.remove(v);
		return true;
	}

	@Override
	public Set<Town> vertexSet() {
		return map.keySet();
	}

	@Override
	public ArrayList<String> shortestPath(Town source, Town destination) {
		dijkstraShortestPath(source);
		LinkedList<Town> path = new Path(destination, shortest_path.get(destination)).add(destination).vertices;
		ArrayList<String> output = new ArrayList<String>();

		int size = path.size();
		Town prev = path.poll();
		for (int i = 1; i < size; i++) {
			Town curr = path.poll();
			Road road = map.get(prev).get(curr);
			output.add(prev.getName() + " via " + road.getName() + " to " + curr.getName() + " " + road.getWeight() + " mi");
			prev = curr;
		}
		return output;
	}
	
	private Path getShortestPathSoFar(Set<Entry<Town, Path>> paths) {
		Path shortest = null;
		int lowest_distance = Integer.MAX_VALUE;
		for (Entry<Town, Path> path : paths) {
			if (path.getValue().distance < lowest_distance) {
				shortest = path.getValue();
				lowest_distance = shortest.distance;
			}
		}
		return shortest;
	}
	
	@Override
	public void dijkstraShortestPath(Town source) {
		shortest_path = new HashMap<Town, Path>();
		HashMap<Town, Path> unresolved = new HashMap<Town, Path>();
		
		unresolved.put(source, new Path(source));
		unresolved.get(source).distance = 0;
		
	    while (!unresolved.isEmpty()) {
	        Path path = getShortestPathSoFar(unresolved.entrySet());
	        unresolved.remove(path.vertex);
	        
	        for (Entry<Town, Road> adjacent : map.get(path.vertex).entrySet()) {
	        	if (shortest_path.containsKey(adjacent.getKey()))
	        		continue;	        		
        		if (!unresolved.containsKey(adjacent.getKey()) || path.distance + adjacent.getValue().getWeight() < unresolved.get(adjacent.getKey()).distance) {
        			unresolved.put(adjacent.getKey(), new Path(adjacent.getKey(), path));
        			unresolved.get(adjacent.getKey()).add(path.vertex).distance = path.distance + adjacent.getValue().getWeight();
        		}
	        }
	        shortest_path.put(path.vertex, path);
	    }
		
	}

	private class Path{
		private Town vertex;
		private LinkedList<Town> vertices;
		private int distance = Integer.MAX_VALUE;
		
		public Path(Town vertex) {
			this.vertex = vertex;
			vertices = new LinkedList<Town>();
		}
		
		public Path(Town vertex, Path copy) {
			this(vertex);
			vertices = new LinkedList<Town>();
			for (Town t : copy.vertices)
				vertices.add(t);
			this.distance = copy.distance;
		}
		
		public Path add(Town vertex) {
			vertices.add(vertex);
			return this;
		}
		
		@Override
		public boolean equals(Object obj) {
			if (obj instanceof Path) {
				Path other = (Path) obj;
				return vertex.equals(other.vertex) && distance == other.distance;
			}
			return false;
		}
		
		@Override
		public int hashCode() {
			return vertex.hashCode() + distance;
		}
		
		@Override
		public String toString() {
			return vertex.toString() + " via " + vertices.toString() + " distance: " + distance;
		}
	}

}
