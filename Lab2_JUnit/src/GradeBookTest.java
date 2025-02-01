import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GradeBookTest {

	GradeBook test1, test2;
	
	@BeforeEach
	void setUp() throws Exception {
		test1 = new GradeBook(5);
		test2 = new GradeBook(5);
		
		test1.addScore(95.6);
		test1.addScore(54.4);
		
		test2.addScore(70.3);
		test2.addScore(69.9);
		test2.addScore(22.2);
		test2.addScore(78.9);
		test2.addScore(23.4);
	}

	@AfterEach
	void tearDown() throws Exception {
		test1 = test2 = null;
	}

	@Test
	void testAddScore() {
		assertTrue(test1.toString().equals("95.6 54.4"));
		assertTrue(test2.toString().equals("70.3 69.9 22.2 78.9 23.4"));
		assertEquals(2, test1.getScoreSize());
		assertEquals(5, test2.getScoreSize());
	}

	@Test
	void testSum() {
		assertEquals(150, test1.sum());
		assertEquals(264.7, test2.sum());
	}

	@Test
	void testMinimum() {
		assertEquals(54.4, test1.minimum());
		assertEquals(22.2, test2.minimum());
	}

	@Test
	void testFinalScore() {
		assertEquals(95.6, test1.finalScore());
		assertEquals(242.5, test2.finalScore());
	}

}
