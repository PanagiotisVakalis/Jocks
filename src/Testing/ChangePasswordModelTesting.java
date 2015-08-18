package Testing;

import static org.junit.Assert.*;

import org.junit.Test;

import pxv425.ChangePasswordModel;
import pxv425.Client;

/**
 * JUnit tests for the LoginModel
 * 
 * @author Panagiotis Vakalis
 * @version 2015-08-17
 */
public class ChangePasswordModelTesting {

	@Test
	/**
	 * Test the useChangePassword method with an empty email
	 */
	public void useChangePasswordTest1() {
		Client client = new Client();
		ChangePasswordModel changePasswordModel = new ChangePasswordModel(
				client);

		String email = "";
		String securityAnswer = "Pani";
		String newPassword = "electro_P89";
		String reEnterNewPassword = "electro_P89";

		String expected = "Go back, enter your email and press submit";
		String result = changePasswordModel.useChangePassword(email,
				securityAnswer, newPassword, reEnterNewPassword);

		assertEquals(expected, result);
	}

	@Test
	/**
	 * Test the useChangePassword method with an empty security answer
	 */
	public void useChangePasswordTest2() {
		Client client = new Client();
		ChangePasswordModel changePasswordModel = new ChangePasswordModel(
				client);

		String email = "pani.vak@gmail.com";
		String securityAnswer = "";
		String newPassword = "electro_P89";
		String reEnterNewPassword = "electro_P89";

		String expected = "Go back and enter your security answer";
		String result = changePasswordModel.useChangePassword(email,
				securityAnswer, newPassword, reEnterNewPassword);

		assertEquals(expected, result);
	}

	@Test
	/**
	 * Test the useChangePassword method with unmacthed passwords
	 */
	public void useChangePasswordTest3() {
		Client client = new Client();
		ChangePasswordModel changePasswordModel = new ChangePasswordModel(
				client);

		String email = "pani.vak@gmail.com";
		String securityAnswer = "Pani";
		String newPassword = "electro_P89";
		String reEnterNewPassword = "electro_P897";

		String expected = "Passwords do not match. Press back button.";
		String result = changePasswordModel.useChangePassword(email,
				securityAnswer, newPassword, reEnterNewPassword);

		assertEquals(expected, result);
	}

	@Test
	/**
	 * Test the useChangePassword method with no valid password
	 */
	public void useChangePasswordTest4() {
		Client client = new Client();
		ChangePasswordModel changePasswordModel = new ChangePasswordModel(
				client);

		String email = "pani.vak@gmail.com";
		String securityAnswer = "Pani";
		String newPassword = "electro";
		String reEnterNewPassword = "electro";

		String expected = "Password should meet the requirements";
		String result = changePasswordModel.useChangePassword(email,
				securityAnswer, newPassword, reEnterNewPassword);

		assertEquals(expected, result);
	}

	@Test
	/**
	 * Test the useChangePassword method with wrong security answer
	 */
	public void useChangePasswordTest5() {
		Client client = new Client();
		ChangePasswordModel changePasswordModel = new ChangePasswordModel(
				client);

		String email = "pani.vak@gmail.com";
		String securityAnswer = "Hi";
		String newPassword = "electro_PA16989";
		String reEnterNewPassword = "electro_PA16989";

		String expected = "Wrong security answer, your password has not been changed. Press back button.";
		String result = changePasswordModel.useChangePassword(email,
				securityAnswer, newPassword, reEnterNewPassword);

		assertEquals(expected, result);
	}

	@Test
	/**
	 * Test the useChangePassword method with correct entries
	 */
	public void useChangePasswordTest6() {
		Client client = new Client();
		ChangePasswordModel changePasswordModel = new ChangePasswordModel(
				client);

		String email = "pani.vak@gmail.com";
		String securityAnswer = "Pani";
		String newPassword = "electro_PA16989";
		String reEnterNewPassword = "electro_PA16989";

		String expected = "Your password has been changed, press ok to continue to login screen.";
		String result = changePasswordModel.useChangePassword(email,
				securityAnswer, newPassword, reEnterNewPassword);

		assertEquals(expected, result);
	}

	@Test
	/**
	 * Test the useValidEmail method with an empty email
	 */
	public void useValidEmailTest1() {
		Client client = new Client();
		ChangePasswordModel changePasswordModel = new ChangePasswordModel(
				client);

		String email = "";

		assertFalse(changePasswordModel.useValidEmail(email));
	}

	@Test
	/**
	 * Test the useValidEmail method with an invalid email
	 */
	public void useValidEmailTest2() {
		Client client = new Client();
		ChangePasswordModel changePasswordModel = new ChangePasswordModel(
				client);

		String email = "pani@com";

		assertFalse(changePasswordModel.useValidEmail(email));
	}

	@Test
	/**
	 * Test the useValidEmail method with an invalid email
	 */
	public void useValidEmailTest3() {
		Client client = new Client();
		ChangePasswordModel changePasswordModel = new ChangePasswordModel(
				client);

		String email = "pani@gmail.com";

		assertTrue(changePasswordModel.useValidEmail(email));
	}

	@Test
	/**
	 * Test the useEmailIsUsed method with an invalid email
	 */
	public void useEmailIsUsedTest1() {
		Client client = new Client();
		ChangePasswordModel changePasswordModel = new ChangePasswordModel(
				client);

		String email = "pani@gmail";

		assertFalse(changePasswordModel.useEmailIsUsed(email));
	}

	@Test
	/**
	 * Test the useEmailIsUsed method with an used email
	 */
	public void useEmailIsUsedTest2() {
		Client client = new Client();
		ChangePasswordModel changePasswordModel = new ChangePasswordModel(
				client);

		String email = "pani.vak@gmail.com";

		assertTrue(changePasswordModel.useEmailIsUsed(email));
	}

	@Test
	/**
	 * Test the useEmailIsUsed method with an unused email
	 */
	public void useEmailIsUsedTest3() {
		Client client = new Client();
		ChangePasswordModel changePasswordModel = new ChangePasswordModel(
				client);

		String email = "abs@gmail.com";

		assertFalse(changePasswordModel.useEmailIsUsed(email));
	}
}
