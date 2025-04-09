import java.io.File;
import java.io.FileNotFoundException;

public class MorseCodeConverter {

	private static MorseCodeTree tree = new MorseCodeTree();
	
	public static String convertToEnglish(String string) {
		
		return null;
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
