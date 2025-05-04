
public final class Town {

	private final String name;
	
	public Town(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Town) {
			Town other = (Town) obj;
			return this.name.equals(other.name);
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		return name.hashCode();
	}

}
