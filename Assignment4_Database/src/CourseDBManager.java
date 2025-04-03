import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Stack;

public class CourseDBManager implements CourseDBManagerInterface {

	private CourseDBStructure cds;
	
	public CourseDBManager() {
		cds = new CourseDBStructure(10);
	}
	
	public CourseDBManager(int size) {
		cds = new CourseDBStructure(size);
	}
	
	@Override
	public void add(String id, int crn, int credits, String roomNum, String instructor) {
		cds.add(new CourseDBElement(id, crn, credits, roomNum, instructor));
	}

	@Override
	public CourseDBElement get(int crn) {
		try {
			return cds.get(crn);
		} catch (IOException e) {
			return null;
		}
	}

	@Override
	public void readFile(File input) throws FileNotFoundException {
		BufferedReader reader = new BufferedReader(new FileReader(input));
		ArrayList<String> lines = new ArrayList<String>();
		
		try {
			String current_line = reader.readLine();
			while(current_line != null) {
				lines.add(current_line);
				current_line = reader.readLine();
			}
			reader.close();
		} catch (IOException e) {
			System.err.println("Error reading file...");
			return;
		}
		
		cds = new CourseDBStructure(lines.size());
		
		for (String line : lines) {
			try {
				cds.add(parseLine(line));
			}catch(IndexOutOfBoundsException e) {
				System.err.println("Error parsing line: " + line);
				return;
			}
		}
	}

	/**
	 * Parses a line of text in the format [Course ID] [CRN] [Credits] [Room Number] [Instructors Name] into a CourseDBElement
	 * @param line text to parse
	 * @return CourseDBElement object containing information from the line of text
	 * @throws IndexOutOfBoundsException if the file does not exist
	 */
	private CourseDBElement parseLine(String line) throws IndexOutOfBoundsException{
		String[] tokens = line.trim().split(" ");
		int i = tokens.length - 1;
		
		String name = tokens[i--]; // Gets the instructors last name.
		
		if (tokens[i].contains(".")) // Has a middle name if abbreviation is found.
			name = tokens[i--] + " " + name;
		
		name = tokens[i--] + " " + name; // Gets instructors first name.
		
		String room_number = "";
		while (tokens[i].length() > 1) // Credits are never double digits
			room_number = tokens[i--] + room_number;
		
		int credits = Integer.parseInt(tokens[i--]);
		
		int crn = Integer.parseInt(tokens[i--]);
		
		String id = tokens[0];
		
		return new CourseDBElement(id, crn, credits, room_number, name);
	}
	
	@Override
	public ArrayList<String> showAll() {
		return cds.showAll();
	}

}
