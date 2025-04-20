import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MorseCodeTree_STUDENT_Test {

	MorseCodeTree tree;
	
	@BeforeEach
	void setUp() throws Exception {
		tree = new MorseCodeTree();
		tree.insert(".----", "1");
		tree.insert("..---", "2");
		tree.insert("...--", "3");
		tree.insert("....-", "4");
		tree.insert(".....", "5");
		tree.insert("-....", "6");
		tree.insert("--...", "7");
		tree.insert("---..", "8");
		tree.insert("----.", "9");
		tree.insert("-----", "0");
	}

	@AfterEach
	void tearDown() throws Exception {
		tree = null;
	}

	@Test
	void testInsert() {
		assertEquals("0", tree.fetch("-----"));
		assertEquals("1", tree.fetch(".----"));
		assertEquals("2", tree.fetch("..---"));
		assertEquals("3", tree.fetch("...--"));
		assertEquals("4", tree.fetch("....-"));
		assertEquals("5", tree.fetch("....."));
		assertEquals("6", tree.fetch("-...."));
		assertEquals("7", tree.fetch("--..."));
		assertEquals("8", tree.fetch("---.."));
		assertEquals("9", tree.fetch("----."));
	}

	@Test
	void testFetch() {
		String coded_message = "-.-. .- .-.. .-.. / .--- --. / .-- . -. - .-- --- .-. - .... / ---.. --... --... / -.-. .- ... .... / -. --- .--";
		String decoded_message = "";
		
		for (String word : coded_message.split("/")) {
			for (String letter : word.strip().split(" "))
				decoded_message += tree.fetch(letter);
			decoded_message += " ";
		}
		
		assertEquals("call jg wentworth 877 cash now", decoded_message.strip());
	}
	
	@Test
	void testLNRTraversal() {
		String result = "";
		for (String s : tree.toArrayList()) {
			if (s.isEmpty())
				result += ".";
			result += s;
		}
		assertEquals("5h4sv3ifu.2elrapwj1.6bdxnckyt7zgqm8.o9.0", result);
	}
	
}
