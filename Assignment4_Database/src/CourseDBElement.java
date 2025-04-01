
public final class CourseDBElement implements Comparable<CourseDBElement>{

	private final String course_id, room_num, instructor;
	private final int crn, credits;

	public CourseDBElement(String course_id, int crn, int credits, String room_num, String instructor) {
		this.course_id = course_id;
		this.crn = crn;
		this.credits = credits;
		this.room_num = room_num;
		this.instructor = instructor;
	}
	
	public String getID() {
		return course_id;
	}

	public String getRoomNum() {
		return room_num;
	}

	public String getInstructor() {
		return instructor;
	}

	public int getCRN() {
		return crn;
	}

	public int getCredits() {
		return credits;
	}

	@Override
	public int compareTo(CourseDBElement element) {
		
		return 0;
	}

}
