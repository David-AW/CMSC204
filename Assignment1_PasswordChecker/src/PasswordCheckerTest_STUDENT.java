
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
	final int WEAK = 0, STRONG = 1, SHORT = 2, NO_NUMBER = 3, NO_UPPER = 4, NO_LOWER = 5, NO_SPECIAL = 6, INVALID_SEQ = 7;
	
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
			assertTrue(PasswordCheckerUtility.isValidLength​(passwords.get(WEAK)));
		} catch (LengthException e) {
			fail("Password length check failed the valid password test");
		}
		
		try {
			PasswordCheckerUtility.isValidLength​(passwords.get(SHORT)); // Should throw an exception
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
		try {
			assertTrue(PasswordCheckerUtility.hasUpperAlpha(passwords.get(WEAK)));
		} catch (NoUpperAlphaException e) {
			fail("Uppercase letter check failed the valid password test");
		}
		
		try {
			PasswordCheckerUtility.hasUpperAlpha(passwords.get(NO_UPPER)); // Should throw an exception
			fail("Uppercase letter check failed to throw an exception.");
		}catch(NoUpperAlphaException e) {
			assertTrue(true);
		}
	}

	/**
	 * Test if the password has at least one lowercase alpha character
	 * This test should throw a NoLowerAlphaException for second case
	 */
	@Test
	public void testIsValidPasswordNoLowerAlpha()
	{
		try {
			assertTrue(PasswordCheckerUtility.hasLowerAlpha(passwords.get(WEAK)));
		} catch (NoLowerAlphaException e) {
			fail("Lowercase letter check failed the valid password test");
		}
		
		try {
			PasswordCheckerUtility.hasLowerAlpha(passwords.get(NO_LOWER)); // Should throw an exception
			fail("Lowercase letter check failed to throw an exception.");
		}catch(NoLowerAlphaException e) {
			assertTrue(true);
		}
	}
	/**
	 * Test if the password has more than 2 of the same character in sequence
	 * This test should throw a InvalidSequenceException for second case
	 */
	@Test
	public void testIsWeakPassword()
	{
		try {
			PasswordCheckerUtility.isWeakPassword(passwords.get(WEAK));
			fail("Weak password failed to throw an exception");
		} catch (WeakPasswordException e) {
			assertTrue(true);
		}
		
		try {
			assertFalse(PasswordCheckerUtility.isWeakPassword(passwords.get(STRONG)));
		} catch (WeakPasswordException e) {
			fail("Strong valid password threw a weak password exception");
		}
	}

	/**
	 * Test if the password has more than 2 of the same character in sequence
	 * This test should throw a InvalidSequenceException for second case
	 */
	@Test
	public void testIsValidPasswordInvalidSequence()
	{
		try {
			assertFalse(PasswordCheckerUtility.NoSameCharInSequence(passwords.get(WEAK)));
		} catch (InvalidSequenceException e) {
			fail("Invalid sequence check failed the valid password test");
		}
		
		try {
			PasswordCheckerUtility.NoSameCharInSequence(passwords.get(INVALID_SEQ)); // Should throw an exception
			fail("Invalid sequence check failed to throw an exception.");
		}catch(InvalidSequenceException e) {
			assertTrue(true);
		}
	}

	/**
	 * Test if the password has at least one digit
	 * One test should throw a NoDigitException
	 */
	@Test
	public void testIsValidPasswordNoDigit()
	{
		try {
			assertTrue(PasswordCheckerUtility.hasDigit(passwords.get(WEAK)));
		} catch (NoDigitException e) {
			fail("Digit check failed the valid password test");
		}
		
		try {
			PasswordCheckerUtility.hasDigit(passwords.get(NO_NUMBER)); // Should throw an exception
			fail("Digit check failed to throw an exception.");
		}catch(NoDigitException e) {
			assertTrue(true);
		}
	}

	/**
	 * Test correct passwords
	 * This test should not throw an exception
	 */
	@Test
	public void testIsValidPasswordSuccessful()
	{
		try {
			assertTrue(PasswordCheckerUtility.isValidPassword(passwords.get(WEAK)));
		} catch (Exception e) {
			fail("Weak but valid password failed the valid password test");
		}
		
		try {
			assertTrue(PasswordCheckerUtility.isValidPassword(passwords.get(STRONG)));
		} catch (Exception e) {
			fail("Strong valid password failed the valid password test");
		}
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
