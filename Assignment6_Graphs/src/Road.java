
public final class Road implements Comparable<Road>{
	
	private final String name;
	private final Town a, b;
	private final int weight;
	
	public Road(Town a, Town b, int weight, String name) {
		this.a = a;
		this.b = b;
		this.weight = weight;
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public Town getSource() {
		return a;
	}

	public Town getDestination() {
		return b;
	}

	public int getWeight() {
		return weight;
	}
	
	public boolean contains(Town town) {
		return this.a.equals(town) || this.b.equals(town);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Road) {
			Road other = (Road) obj;
			return contains(other.a) && contains(other.b) && this.name.equals(other.name) && this.weight == other.weight;
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		return (a.hashCode() + b.hashCode()) / weight + name.hashCode();
	}

	@Override
	public int compareTo(Road o) {
		return this.getName().compareTo(o.getName());
	}
	
}
