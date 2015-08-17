package Testing;

import static org.junit.Assert.*;

import org.junit.Test;

import pxv425.Client;
import pxv425.LoginModel;

/**
 * JUnit tests for the LoginModel
 * @author Panagiotis Vakalis
 * @version 2015-08-17
 */
public class LoginModelTesting {
	
	@Test
	/**
	 * Test the useLoginUser method with existed email and password
	 */
	public void useLoginUserTest1(){
		Client client = new Client();
		LoginModel loginModel = new LoginModel(client);
		String email = "pani.vak@gmail.com";
		String password = "electro_PA16989";
		
		String expected = "ok";
		String result = loginModel.useLoginUser(email, password);
		
		assertEquals(expected, result);
	}
	
	@Test
	/**
	 * Test the useLoginUser method with wrong email and correct password
	 */
	public void useLoginUserTest2(){
		Client client = new Client();
		LoginModel loginModel = new LoginModel(client);
		String email = "pani@gmail.com";
		String password = "electro_PA16989";
		
		String expected = "no";
		String result = loginModel.useLoginUser(email, password);
		
		assertEquals(expected, result);
	}
	
	@Test
	/**
	 * Test the useLoginUser method with existed email and wrong password
	 */
	public void useLoginUserTest3(){
		Client client = new Client();
		LoginModel loginModel = new LoginModel(client);
		String email = "pani.vak@gmail.com";
		String password = "electro_PA";
		
		String expected = "no";
		String result = loginModel.useLoginUser(email, password);
		
		assertEquals(expected, result);
	}
	
	@Test
	/**
	 * Test the useLoginUser method with both email and password wrong
	 */
	public void useLoginUserTest4(){
		Client client = new Client();
		LoginModel loginModel = new LoginModel(client);
		String email = "pani@gmail.com";
		String password = "electro_PA";
		
		String expected = "no";
		String result = loginModel.useLoginUser(email, password);
		
		assertEquals(expected, result);
	}
	
	@Test
	/**
	 * Test the useLoginUser method without
	 */
	public void useLoginUserTest5(){
		Client client = new Client();
		LoginModel loginModel = new LoginModel(client);
		String email = null;
		String password = "electro_PA16989";
		
		String expected = "no";
		String result = loginModel.useLoginUser(email, password);
		
		assertEquals(expected, result);
	}
	
	@Test
	/**
	 * Test the useLoginUser method without password
	 */
	public void useLoginUserTest6(){
		Client client = new Client();
		LoginModel loginModel = new LoginModel(client);
		String email = "pani.vak@gmail.com";
		String password = null;
		
		String expected = "no";
		String result = loginModel.useLoginUser(email, password);
		
		assertEquals(expected, result);
	}
	
	@Test
	/**
	 * Test the useLoginUser method without entries
	 */
	public void useLoginUserTest7(){
		Client client = new Client();
		LoginModel loginModel = new LoginModel(client);
		String email = null;
		String password = null;
		
		String expected = "no";
		String result = loginModel.useLoginUser(email, password);
		
		assertEquals(expected, result);
	}
}
