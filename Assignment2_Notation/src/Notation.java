
public class Notation {

	/**
	 * Convert the Postfix expression to the Infix expression
	 * @param postfix the postfix expression in string format
	 * @return the infix expression in string format
	 * @throws InvalidNotationFormatException if the postfix expression format is invalid
	 */
	public static String convertPostfixToInfix(String postfix) throws InvalidNotationFormatException{
		MyStack<String> stack = new MyStack<String>();
		for (char c : postfix.toCharArray()) {
			if (c == ' ')
				continue;
			
			if (isOperand(c))
				stack.push(c+"");
			
			if (isOperator(c)) {
				String first = stack.pop();
				String second = stack.pop();
				if (!isOperand(first) || !isOperand(second))
					throw new InvalidNotationFormatException();
				stack.push('(' + first + c + second + ')');
			}
		}
		String infix = stack.pop();
		if (!stack.isEmpty())
			throw new InvalidNotationFormatException();
		return infix;
	}
	
	/**
	 * Convert an infix expression into a postfix expression
	 * @param infix the infix expression in string format
	 * @return the postfix expression in string format
	 * @throws InvalidNotationFormatException if the infix expression format is invalid
	 */
	public static String convertInfixToPostfix(String infix) throws InvalidNotationFormatException {
		
		return null;
	}
	
	/**
	 * Evaluates a postfix expression from a string to a double
	 * @param postfix the postfix expression in String format
	 * @return the evaluation of the postfix expression as a double
	 * @throws InvalidNotationFormatException if the postfix expression format is invalid
	 */
	public static double evaluatePostfixExpression​(String postfix) throws InvalidNotationFormatException {
		
		return 0;
	}
	
	private static boolean isOperator(char c) {
		return c == '+' || c == '-' || c == '*' || c == '/';
	}
	
	private static boolean isOperand(char c) {
		return c >= '0' && c <= '9';
	}
	
	private static boolean isOperand(String s) {
		if (s.length() > 1)
			return false;
		char c = s.charAt(0);
		return c >= '0' && c <= '9';
	}
	
}
