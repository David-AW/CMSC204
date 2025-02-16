
public class Notation {

	/**
	 * Convert the Postfix expression to the Infix expression
	 * @param postfix the postfix expression in string format
	 * @return the infix expression in string format
	 * @throws InvalidNotationFormatException if the postfix expression format is invalid
	 */
	public static String convertPostfixToInfix(String postfix) throws InvalidNotationFormatException{
		MyStack<String> stack = new MyStack<String>(postfix.length());
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
		MyStack<Character> stack = new MyStack<Character>(infix.length());
		MyQueue<String> queue = new MyQueue<String>(infix.length());
		
		for (char c : infix.toCharArray()) {
			if (c == ' ')
				continue;
			
			if (isOperand(c))
				queue.enqueue(c+"");
			
			if (c == '(')
				stack.push(c);
			
			if (isOperator(c)) {
				
			}
		}
		return null;
	}
	
	/**
	 * Evaluates a postfix expression from a string to a double
	 * @param postfix the postfix expression in String format
	 * @return the evaluation of the postfix expression as a double
	 * @throws InvalidNotationFormatException if the postfix expression format is invalid
	 */
	public static double evaluatePostfixExpression(String postfix) throws InvalidNotationFormatException {
		
		return 0;
	}
	
	/**
	 * Gets rank of an operator (higher value = more priority) based on rules of PEMDAS
	 * @param operator character to evaluate
	 * @return rank of character in terms of operator priority (non-operators return -1)
	 */
	private static int getOperatorRank(char operator) {
		if (!isOperator(operator))
			return -1;
		return (operator == '*' || operator == '/') ? 1 : 0;
	}
	
	/**
	 * Evaluates a character to be an operator character
	 * @param c character to evaluate
	 * @return true if the character is a plus, minus, multiply, or division character; false if not
	 */
	private static boolean isOperator(char c) {
		return c == '+' || c == '-' || c == '*' || c == '/';
	}
	
	/**
	 * Evaluates a character to be a numeric digit
	 * @param c character to evaluate
	 * @return true if character is a numeric digit; false if not
	 */
	private static boolean isOperand(char c) {
		return c >= '0' && c <= '9';
	}
	
	/**
	 * Evaluates a string representation of a character to be a numeric digit
	 * @param s string representation of a character
	 * @return true if string is a single character of a numeric digit; false if string is not 1 character long, null, or not numeric
	 */
	private static boolean isOperand(String s) {
		if (s == null || s.length() != 1)
			return false;
		char c = s.charAt(0);
		return c >= '0' && c <= '9';
	}
	
}
