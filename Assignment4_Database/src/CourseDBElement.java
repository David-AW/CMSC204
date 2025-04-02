
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
	public int compareTo(CourseDBElement other) {
		if (this.course_id == other.course_id)
			return this.crn - other.crn;
		return this.course_id.compareTo(other.course_id);
	}
	
	@Override
	public int hashCode() {
		return Integer.toString(crn).hashCode();
	}
	
	@Override
	public String toString() {
		return "\nCourse:" + course_id + " CRN:" + crn + " Credits:" + credits + " Instructor:" + instructor + " Room:" + room_num;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof CourseDBElement) {
			CourseDBElement other = (CourseDBElement) obj;
			return this.course_id.equals(other.course_id) && this.room_num.equals(other.room_num) 
					&& this.instructor.equals(other.instructor) && this.crn == other.crn && this.credits == other.credits;
		}
		return false;
	}

}
