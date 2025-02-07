
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * STUDENT tests for the methods of PasswordChecker
 * @author David Wery
 *
 */
public class PasswordCheckerTest_STUDENT {

	ArrayList<String> passwords;

	@Before
	public void setUp() throws Exception {
		passwords = new ArrayList<String>();
		passwords.add("A1b#234");		// 0 Valid But Weak
		passwords.add("A1b#234!@#a");	// 1 Valid and Strong
		passwords.add("12345");			// 2 Too short
		passwords.add("abcd!FGH");		// 3 No Number
		passwords.add("12345a!!");		// 4 No Uppercase Letter
		passwords.add("12345#AB");		// 5 No Lowercase Letter
		passwords.add("12345abcD"); 	// 6 No Special Character
		passwords.add("aaaB@123");		// 7 More than 2 of the same character in a row
	}

	@After
	public void tearDown() throws Exception {
		passwords = null;
	}

	/**
	 * Test if the password is less than 6 characters long.
	 * This test should throw a LengthException for second case.
	 */
	@Test
	public void testIsValidPasswordTooShort()
	{
		try {
			assertTrue(PasswordCheckerUtility.isValidLength​(passwords.get(0)));
			PasswordCheckerUtility.isValidLength​(passwords.get(2)); // Should throw an exception
			fail("Password length check failed to throw an exception.");
		}catch(LengthException e) {
			assertTrue(true);
		}
	}

	/**
	 * Test if the password has at least one uppercase alpha character
	 * This test should throw a NoUpperAlphaException for second case
	 */
	@Test
	public void testIsValidPasswordNoUpperAlpha()
	{
		fail("Not implemented by student yet");
	}

	/**
	 * Test if the password has at least one lowercase alpha character
	 * This test should throw a NoLowerAlphaException for second case
	 */
	@Test
	public void testIsValidPasswordNoLowerAlpha()
	{
		fail("Not implemented by student yet");
	}
	/**
	 * Test if the password has more than 2 of the same character in sequence
	 * This test should throw a InvalidSequenceException for second case
	 */
	@Test
	public void testIsWeakPassword()
	{
		fail("Not implemented by student yet");
	}

	/**
	 * Test if the password has more than 2 of the same character in sequence
	 * This test should throw a InvalidSequenceException for second case
	 */
	@Test
	public void testIsValidPasswordInvalidSequence()
	{
		fail("Not implemented by student yet");
	}

	/**
	 * Test if the password has at least one digit
	 * One test should throw a NoDigitException
	 */
	@Test
	public void testIsValidPasswordNoDigit()
	{
		fail("Not implemented by student yet");
	}

	/**
	 * Test correct passwords
	 * This test should not throw an exception
	 */
	@Test
	public void testIsValidPasswordSuccessful()
	{
		fail("Not implemented by student yet");
	}

	/**
	 * Test the invalidPasswords method
	 * Check the results of the ArrayList of Strings returned by the validPasswords method
	 */
	@Test
	public void testInvalidPasswords() {
		fail("Not implemented by student yet");
	}

}
