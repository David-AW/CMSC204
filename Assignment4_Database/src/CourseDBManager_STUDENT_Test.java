import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CourseDBManager_STUDENT_Test {

	CourseDBManager cdm;
	
	@BeforeEach
	void setUp() throws Exception {
		cdm = new CourseDBManager();
		try {
			cdm.readFile(new File("courses.txt"));
		} catch (FileNotFoundException e) {
			assertTrue(false, "Failed to read file");
		}
	}

	@AfterEach
	void tearDown() throws Exception {
		cdm = null;
	}

	@Test
	void testAdd() {
		cdm.add("CMSC100", 10023, 3, "RM203", "David A. Wery");
		cdm.add("CMSC204", 34534, 3, "RM204", "David Kuijt");
		
		assertNotNull(cdm.get(10023));
		assertNotNull(cdm.get(34534));
	}

	@Test
	void testGet() {
		CourseDBElement element = cdm.get(21561);
		assertNotNull(element);
		assertEquals(21561, element.getCRN());
		assertEquals("Rabiha J. Kayed", element.getInstructor());
	}
	
	@Test
	void testShowAll() {
		
		ArrayList<String> lines = cdm.showAll();
			
		for (String line : lines) {
			line = line.substring(line.indexOf("CRN:") + 4);
			int crn = Integer.parseInt(line.substring(0, line.indexOf(' ')));
			assertNotNull(cdm.get(crn));
		}
		
	}
	
}
