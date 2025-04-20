import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class MorseCodeConverter {

	private static MorseCodeTree tree = new MorseCodeTree();
	
	/**
	 * Converts a morse code sentence where letters are separated by the "space" character and words are separated by a slash "/".
	 * @param sentence Sentence to convert
	 * @return English conversion of the morse code
	 */
	public static String convertToEnglish(String sentence) {
		String decoded = "";
		for (String word : sentence.split("/")) {
			for (String letter : word.split(" ")) {
				decoded += tree.fetch(letter);
			}
			decoded += " ";
		}
		return decoded.strip();
	}

	/**
	 * Converts a morse code sentence where letters are separated by the "space" character and words are separated by a slash "/".
	 * @param file File containing morse code to convert
	 * @return English conversion of the morse code
	 * @throws FileNotFoundException If the file specified doesn't exist
	 */
	public static String convertToEnglish(File file) throws FileNotFoundException{
		BufferedReader reader = new BufferedReader(new FileReader(file));
		String morse = "";
		try {
			while (reader.ready()) {
				morse += reader.readLine().strip() + " ";
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return convertToEnglish(morse.strip());
	}

	/**
	 * Prints the tree in LNR (post)order separated by spaces
	 * @return the postorder representation of the tree
	 */
	public static String printTree() {
		String output = "";
		for (String c : tree.toArrayList())
			output += c + " ";
		return output.strip();
	}

}
