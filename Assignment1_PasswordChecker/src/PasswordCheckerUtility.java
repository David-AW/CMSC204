import java.util.regex.Pattern;

public class PasswordCheckerUtility {
	
	private static final Pattern alphanumeric = Pattern.compile("[a-zA-Z0-9]*");
	
	/**
	 * Returns true if the password meets the following criteria:
	 * 1. At least 6 characters long
	 * 2. At least 1 numeric character
	 * 3. At least 1 uppercase alphabetic character
	 * 4. At least 1 lowercase alphabetic character
	 * 5. At least 1 special character
	 * 6. No more than 2 of the same character in a sequence
	 * @param password password to check
	 * @return true if password is valid
	 * @throws LengthException thrown if length is less than 6 characters
	 * @throws NoUpperAlphaException thrown if no uppercase alphabetic
	 * @throws NoLowerAlphaException thrown if no lowercase alphabetic
	 * @throws NoDigitException thrown if no digit
	 * @throws NoSpecialCharacterException thrown if does not meet SpecialCharacter requirement
	 * @throws InvalidSequenceException thrown if more than 2 of same character
	 */
	public static boolean isValidPassword(String password) throws LengthException, NoUpperAlphaException, NoLowerAlphaException, NoDigitException, NoSpecialCharacterException, InvalidSequenceException {
		isValidLength​(password);
		hasUpperAlpha(password);
		hasLowerAlpha(password);
		hasDigit(password);
		hasSpecialChar(password);
		NoSameCharInSequence(password);
		return true;
	}
	
	/**
	 * Checks if password is VALID and the length is NOT between 6-9 characters
	 * @param password password to check
	 * @return false if password is valid and strong
	 * @throws WeakPasswordException thrown if password is invalid and/or weak
	 */
	public static boolean isWeakPassword(String password) throws WeakPasswordException {
		try {
			isValidPassword(password);
			if (!hasBetweenSixAndNineChars(password))
				return false;
		} catch (Exception e) {}

		throw new WeakPasswordException();
	}
	
	/**
	 * Checks if the password is at least 6 characters long
	 * @param password password to check
	 * @return true if valid length
	 * @throws LengthException thrown if password is less than 6 characters in length
	 */
	public static boolean isValidLength​(String password) throws LengthException {
		if (password.length() >= 6)
			return true;
		throw new LengthException();
	}
	
	/**
	 * Checks if the password is between 6 and 9 characters (inclusive)
	 * @param password password to check
	 * @return true if password length is between 6 and 9 characters
	 */
	public static boolean hasBetweenSixAndNineChars(String password) {
		return password.length() >= 6 && password.length() <= 9;
	}
	
	/**
	 * Checks if the password has at least 1 uppercase letter
	 * @param password password to check
	 * @return true if password contains at least 1 uppercase letter
	 * @throws NoUpperAlphaException thrown if there is no uppercase letter present in the password
	 */
	public static boolean hasUpperAlpha(String password) throws NoUpperAlphaException {
		for (char c : password.toCharArray())
			if (c >= 'A' && c <= 'Z')
				return true;
		throw new NoUpperAlphaException();
	}
	
	/**
	 * Checks if the password has at least 1 lowercase letter
	 * @param password password to check
	 * @return true if password contains at least 1 lowercase letter
	 * @throws NoLowerAlphaException thrown if there is no lowercase letter present in the password
	 */
	public static boolean hasLowerAlpha(String password) throws NoLowerAlphaException {
		for (char c : password.toCharArray())
			if (c >= 'a' && c <= 'z')
				return true;
		throw new NoLowerAlphaException();
	}
	
	/**
	 * Checks if the password has at least 1 number
	 * @param password password to check
	 * @return true if password contains at least 1 number
	 * @throws NoDigitException thrown if there is no number present in the password
	 */
	public static boolean hasDigit(String password) throws NoDigitException {
		for (char c : password.toCharArray())
			if (c >= '0' && c <= '9')
				return true;
		throw new NoDigitException();
	}
	
	/**
	 * Checks if the password has at least 1 special character
	 * @param password password to check
	 * @return true if password contains at least 1 special character
	 * @throws NoSpecialCharacterException thrown if there is no special character present in the password
	 */
	public static boolean hasSpecialChar(String password) throws NoSpecialCharacterException {
		if (!alphanumeric.matcher(password).matches())
			return true;
		throw new NoSpecialCharacterException();
	}
	
	/**
	 * Checks the password Sequence requirement - Password should not contain more than 2 of the same character in sequence
	 * @param password password string to be checked for Sequence requirement
	 * @return false if does NOT meet Sequence requirement
	 * @throws InvalidSequenceException thrown if meets Sequence requirement
	 */
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
