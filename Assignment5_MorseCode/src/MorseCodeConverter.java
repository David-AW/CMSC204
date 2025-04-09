import java.io.File;
import java.io.FileNotFoundException;

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
		
		return null;
	}

	public static String printTree() {
		String output = "";
		for (String c : tree.toArrayList())
			output += c + " ";
		return output.strip();
	}

}
