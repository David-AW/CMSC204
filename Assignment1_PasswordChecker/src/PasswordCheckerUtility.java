
public class PasswordCheckerUtility {

	public static void main(String[] args) {
		boolean test = false;
		try {
			test = isValidPassword("abc");
		}catch(NullPointerException e) {
			
		}
		System.out.println(test);
	}
	
	public static boolean isValidPassword(String password) throws NullPointerException {
		hasUpperAlpha(password);
		return true;
	}
	
	public static boolean hasUpperAlpha(String password) {
		for (char c : password.toCharArray())
			if (c >= 'A' && c <= 'Z')
				return true;
		throw new NullPointerException();
	}
	
}
