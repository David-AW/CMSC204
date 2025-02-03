import java.util.regex.Pattern;

public class PasswordCheckerUtility {
	
	private static final Pattern alphanumeric = Pattern.compile("[a-zA-Z0-9]*");
	
	public static boolean isValidPassword(String password) throws LengthException, NoUpperAlphaException, NoLowerAlphaException, NoDigitException, NoSpecialCharacterException, InvalidSequenceException {
		isValidLength​(password);
		hasUpperAlpha(password);
		hasLowerAlpha(password);
		hasDigit(password);
		hasSpecialChar(password);
		NoSameCharInSequence(password);
		return true;
	}
	
	public static boolean isValidLength​(String password) throws LengthException {
		if (password.length() >= 6)
			return true;
		throw new LengthException();
	}
	
	public static boolean hasUpperAlpha(String password) throws NoUpperAlphaException {
		for (char c : password.toCharArray())
			if (c >= 'A' && c <= 'Z')
				return true;
		throw new NoUpperAlphaException();
	}
	
	public static boolean hasLowerAlpha(String password) throws NoLowerAlphaException {
		for (char c : password.toCharArray())
			if (c >= 'a' && c <= 'z')
				return true;
		throw new NoLowerAlphaException();
	}
	
	public static boolean hasDigit(String password) throws NoDigitException {
		for (char c : password.toCharArray())
			if (c >= '0' && c <= '9')
				return true;
		throw new NoDigitException();
	}
	
	public static boolean hasSpecialChar(String password) throws NoSpecialCharacterException {
		if (!alphanumeric.matcher(password).matches())
			return true;
		throw new NoSpecialCharacterException();
	}
	
	public static boolean NoSameCharInSequence(String password) throws InvalidSequenceException {
		char a = 0, b = 0;
		for (char c : password.toCharArray()) {
			if (a == b && b == c)
				throw new InvalidSequenceException();
			a = b;
			b = c;
		}
		return false;
	}
	
}
