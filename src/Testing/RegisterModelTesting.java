package Testing;

import static org.junit.Assert.*;

import org.junit.Test;

import pxv425.Client;
import pxv425.RegisterModel;

/**
 * JUnit tests for the LoginModel
 * @author Panagiotis Vakalis
 * @version 2015-08-17
 */
public class RegisterModelTesting {

	@Test
	/**
	 * Test the useRegisterUser method with no email
	 */
	public void useRegisterInvestorTest1(){
		Client client = new Client();
		RegisterModel registerModel = new RegisterModel(client);
		
		String email = "";
		String password = "electro_P89";
		String password2 = "electro_P89";
		String firstName = "Panagiotis";
		String lastName = "Vakalis";
		String securityQuestion = "How are you";
		String securityAnswer = "Fine";
		
		String expected = "Fill all the fields";
		String result = registerModel.useRegisterUser(email, password, password2, firstName, lastName, securityQuestion, securityAnswer);
		
		assertEquals(expected, result);
	}
	
	@Test
	/**
	 * Test the useRegisterUser method with no password
	 */
	public void useRegisterInvestorTest2(){
		Client client = new Client();
		RegisterModel registerModel = new RegisterModel(client);
		
		String email = "pv@gmail.com";
		String password = "";
		String password2 = "electro_P89";
		String firstName = "Panagiotis";
		String lastName = "Vakalis";
		String securityQuestion = "How are you";
		String securityAnswer = "Fine";
		
		String expected = "Fill all the fields";
		String result = registerModel.useRegisterUser(email, password, password2, firstName, lastName, securityQuestion, securityAnswer);
		
		assertEquals(expected, result);
	}
	
	@Test
	/**
	 * Test the useRegisterUser method with no password2
	 */
	public void useRegisterInvestorTest3(){
		Client client = new Client();
		RegisterModel registerModel = new RegisterModel(client);
		
		String email = "pv@gmail.com";
		String password = "electro_P89";
		String password2 = "";
		String firstName = "Panagiotis";
		String lastName = "Vakalis";
		String securityQuestion = "How are you";
		String securityAnswer = "Fine";
		
		String expected = "Fill all the fields";
		String result = registerModel.useRegisterUser(email, password, password2, firstName, lastName, securityQuestion, securityAnswer);
		
		assertEquals(expected, result);
	}
	
	@Test
	/**
	 * Test the useRegisterUser method with no first name
	 */
	public void useRegisterInvestorTest4(){
		Client client = new Client();
		RegisterModel registerModel = new RegisterModel(client);
		
		String email = "pv@gmail.com";
		String password = "electro_P89";
		String password2 = "electro_P89";
		String firstName = "";
		String lastName = "Vakalis";
		String securityQuestion = "How are you";
		String securityAnswer = "Fine";
		
		String expected = "Fill all the fields";
		String result = registerModel.useRegisterUser(email, password, password2, firstName, lastName, securityQuestion, securityAnswer);
		
		assertEquals(expected, result);
	}
	
	@Test
	/**
	 * Test the useRegisterUser method with no last name
	 */
	public void useRegisterInvestorTest5(){
		Client client = new Client();
		RegisterModel registerModel = new RegisterModel(client);
		
		String email = "pv@gmail.com";
		String password = "electro_P89";
		String password2 = "electro_P89";
		String firstName = "Panagiotis";
		String lastName = "";
		String securityQuestion = "How are you";
		String securityAnswer = "Fine";
		
		String expected = "Fill all the fields";
		String result = registerModel.useRegisterUser(email, password, password2, firstName, lastName, securityQuestion, securityAnswer);
		
		assertEquals(expected, result);
	}
	
	@Test
	/**
	 * Test the useRegisterUser method with no security question
	 */
	public void useRegisterInvestorTest6(){
		Client client = new Client();
		RegisterModel registerModel = new RegisterModel(client);
		
		String email = "pv@gmail.com";
		String password = "electro_P89";
		String password2 = "electro_P89";
		String firstName = "Panagiotis";
		String lastName = "Vakalis";
		String securityQuestion = "";
		String securityAnswer = "Fine";
		
		String expected = "Fill all the fields";
		String result = registerModel.useRegisterUser(email, password, password2, firstName, lastName, securityQuestion, securityAnswer);
		
		assertEquals(expected, result);
	}
	
	@Test
	/**
	 * Test the useRegisterUser method with no security answer
	 */
	public void useRegisterInvestorTest7(){
		Client client = new Client();
		RegisterModel registerModel = new RegisterModel(client);
		
		String email = "pv@gmail.com";
		String password = "electro_P89";
		String password2 = "electro_P89";
		String firstName = "Panagiotis";
		String lastName = "Vakalis";
		String securityQuestion = "How are you";
		String securityAnswer = "";
		
		String expected = "Fill all the fields";
		String result = registerModel.useRegisterUser(email, password, password2, firstName, lastName, securityQuestion, securityAnswer);
		
		assertEquals(expected, result);
	}
	
	@Test
	/**
	 * Test the useRegisterUser method with no same passwords
	 */
	public void useRegisterInvestorTest8(){
		Client client = new Client();
		RegisterModel registerModel = new RegisterModel(client);
		
		String email = "pv@gmail.com";
		String password = "electro_P897";
		String password2 = "electro_P89";
		String firstName = "Panagiotis";
		String lastName = "Vakalis";
		String securityQuestion = "How are you";
		String securityAnswer = "Fine";
		
		String expected = "Password does not match";
		String result = registerModel.useRegisterUser(email, password, password2, firstName, lastName, securityQuestion, securityAnswer);
		
		assertEquals(expected, result);
	}
	
	@Test
	/**
	 * Test the useRegisterUser method with no valid email
	 */
	public void useRegisterInvestorTest9(){
		Client client = new Client();
		RegisterModel registerModel = new RegisterModel(client);
		
		String email = "pv@gmail";
		String password = "electro_P89";
		String password2 = "electro_P89";
		String firstName = "Panagiotis";
		String lastName = "Vakalis";
		String securityQuestion = "How are you";
		String securityAnswer = "Fine";
		
		String expected = "Email is not valid";
		String result = registerModel.useRegisterUser(email, password, password2, firstName, lastName, securityQuestion, securityAnswer);
		
		assertEquals(expected, result);
	}
	
	@Test
	/**
	 * Test the useRegisterUser method with no valid password
	 */
	public void useRegisterInvestorTest10(){
		Client client = new Client();
		RegisterModel registerModel = new RegisterModel(client);
		
		String email = "pv@gmail.com";
		String password = "electro";
		String password2 = "electro";
		String firstName = "Panagiotis";
		String lastName = "Vakalis";
		String securityQuestion = "How are you";
		String securityAnswer = "Fine";
		
		String expected = "Password should meet the requirements";
		String result = registerModel.useRegisterUser(email, password, password2, firstName, lastName, securityQuestion, securityAnswer);
		
		assertEquals(expected, result);
	}
	
	@Test
	/**
	 * Test the useRegisterUser method with no entries
	 */
	public void useRegisterInvestorTest11(){
		Client client = new Client();
		RegisterModel registerModel = new RegisterModel(client);
		
		String email = "";
		String password = "";
		String password2 = "";
		String firstName = "";
		String lastName = "";
		String securityQuestion = "";
		String securityAnswer = "";
		
		String expected = "Fill all the fields";
		String result = registerModel.useRegisterUser(email, password, password2, firstName, lastName, securityQuestion, securityAnswer);
		
		assertEquals(expected, result);
	}
	
	@Test
	/**
	 * Test the useRegisterUser method with an existed email
	 */
	public void useRegisterInvestorTest12(){
		Client client = new Client();
		RegisterModel registerModel = new RegisterModel(client);
		
		String email = "pani.vak@gmail.com";
		String password = "electro_PA16989";
		String password2 = "electro_PA16989";
		String firstName = "Panagiotis";
		String lastName = "Vakalis";
		String securityQuestion = "How are you";
		String securityAnswer = "Fine";
		
		String expected = "Email already used";
		String result = registerModel.useRegisterUser(email, password, password2, firstName, lastName, securityQuestion, securityAnswer);
		
		assertEquals(expected, result);
	}
	
	@Test
	/**
	 * Test the useRegisterUser method with no password match and no security answer
	 */
	public void useRegisterInvestorTest13(){
		Client client = new Client();
		RegisterModel registerModel = new RegisterModel(client);
		
		String email = "pani@gmail.com";
		String password = "electro_PA16989";
		String password2 = "electro_PA1699";
		String firstName = "Panagiotis";
		String lastName = "Vakalis";
		String securityQuestion = "How are you";
		String securityAnswer = "";
		
		String expected = "Fill all the fields";
		String result = registerModel.useRegisterUser(email, password, password2, firstName, lastName, securityQuestion, securityAnswer);
		
		assertEquals(expected, result);
	}
	
	@Test
	/**
	 * Test the useRegisterUser method with no password match and no security answer
	 */
	public void useRegisterInvestorTest14(){
		Client client = new Client();
		RegisterModel registerModel = new RegisterModel(client);
		
		String email = "pani@gmail.com";
		String password = "electro_PA16999";
		String password2 = "electro_PA16999";
		String firstName = "Panagiotis";
		String lastName = "Vakalis";
		String securityQuestion = "How are you";
		String securityAnswer = "Fine";
		
		String expected = "Register succesfull";
		String result = registerModel.useRegisterUser(email, password, password2, firstName, lastName, securityQuestion, securityAnswer);
		
		assertEquals(expected, result);
	}
}
