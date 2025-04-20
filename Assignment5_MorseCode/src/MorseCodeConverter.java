import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class MorseCodeConverter {

	private static MorseCodeTree tree = new MorseCodeTree();
	
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

	public static String printTree() {
		String output = "";
		for (String c : tree.toArrayList())
			output += c + " ";
		return output.strip();
	}

}
