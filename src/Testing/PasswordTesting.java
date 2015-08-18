package Testing;

import static org.junit.Assert.*;

import org.junit.Test;

import pxv425.Password;

/**
 * JUnit tests for the Password The passwords which will be tested should meet
 * the requirements
 * 
 * @author Panagiotis Vakalis
 * @version 2015-08-17
 */
public class PasswordTesting {

	@Test
	/**
	 * Test the USE_ENCRYPT_PASSWORD for the: electro_PA16989
	 */
	public void useEncryptPasswordTest1() {
		String password = "electro_PA16989";

		String expected = "tzroEBx~79~WG6!#!!";
		String result = Password.USE_ENCRYPT_PASSWORD(password);

		assertEquals(expected, result);
	}

	@Test
	/**
	 * Test the USE_ENCRYPT_PASSWORD for the: electro_P89
	 */
	public void useEncryptPasswordTest2() {
		String password = "electro_P89";

		String expected = "pvnkAxt~75~S!!";
		String result = Password.USE_ENCRYPT_PASSWORD(password);

		assertEquals(expected, result);
	}

	@Test
	/**
	 * Test the USE_ENCRYPT_PASSWORD for the: 98712365
	 */
	public void useEncryptPasswordTest3() {
		String password = "98712365";

		String expected = "*^$66686";
		String result = Password.USE_ENCRYPT_PASSWORD(password);

		assertEquals(expected, result);
	}

	@Test
	/**
	 * Test the USE_DECRYPT_PASSWORD for the: tzroEBx~79~WG6!#!!
	 */
	public void useDecryptPasswordTest1() {
		String encryptedPassword = "tzroEBx~79~WG6!#!!";

		String expected = "electro_PA16989";
		String result = Password.USE_DECRYPT_PASSWORD(encryptedPassword);

		assertEquals(expected, result);
	}

	@Test
	/**
	 * Test the USE_DECRYPT_PASSWORD for the: pvnkAxt~75~S!!
	 */
	public void useDecryptPasswordTest2() {
		String encryptedPassword = "pvnkAxt~75~S!!";

		String expected = "electro_P89";
		String result = Password.USE_DECRYPT_PASSWORD(encryptedPassword);

		assertEquals(expected, result);
	}

	@Test
	/**
	 * Test the USE_DECRYPT_PASSWORD for the: *^$66686
	 */
	public void useDecryptPasswordTest3() {
		String encryptedPassword = "*^$66686";

		String expected = "98712365";
		String result = Password.USE_DECRYPT_PASSWORD(encryptedPassword);

		assertEquals(expected, result);
	}
}
