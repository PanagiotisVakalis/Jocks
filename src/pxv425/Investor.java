package pxv425;

import java.util.ArrayList;
import java.util.Date;

/**
 * This class contains the investor's details
 * 
 * @author Panagiotis Vakalis
 * @version 16-07-2015
 *
 */
public class Investor {

	private Integer id;
	private String email;
	private String password;
	private String firstName;
	private String lastName;
	private Date dateRegistered;
	private String securityQuestion;
	private String securityAnswer;
	private ArrayList<Portfolio> portfolios;

	/**
	 * Constructor of the class
	 * 
	 * @param id
	 * @param email
	 * @param password
	 * @param firstName
	 * @param lastName
	 * @param dateRegistered
	 * @param securityQuestion
	 * @param securityAnswer
	 * 
	 * @author Panagiotis Vakalis
	 * @version 16-07-2015
	 */
	public Investor(Integer id, String email, String password,
			String firstName, String lastName, Date dateRegistered,
			String securityQuestion, String securityAnswer) {
		this.id = id;
		this.email = email;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateRegistered = dateRegistered;
		this.securityQuestion = securityQuestion;
		this.securityAnswer = securityAnswer;
	}

	/**
	 * Method to get the id
	 * 
	 * @return id
	 * 
	 * @author Panagiotis Vakalis
	 * @version 16-07-2015
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * Method to set the id
	 * 
	 * @param id
	 * 
	 * @author Panagiotis Vakalis
	 * @version 16-07-2015
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * Method to get the email
	 * 
	 * @return email
	 * 
	 * @author Panagiotis Vakalis
	 * @version 16-07-2015
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Method to set the email
	 * 
	 * @param email
	 * 
	 * @author Panagiotis Vakalis
	 * @version 16-07-2015
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Method to get the password
	 * 
	 * @return password
	 * 
	 * @author Panagiotis Vakalis
	 * @version 16-07-2015
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Method to set the password
	 * 
	 * @param password
	 * 
	 * @author Panagiotis Vakalis
	 * @version 16-07-2015
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Method to get the first name
	 * 
	 * @return first name
	 * 
	 * @author Panagiotis Vakalis
	 * @version 16-07-2015
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Method to set the first name
	 * 
	 * @param firstName
	 * 
	 * @author Panagiotis Vakalis
	 * @version 16-07-2015
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Method to get the last name
	 * 
	 * @return last name
	 * 
	 * @author Panagiotis Vakalis
	 * @version 16-07-2015
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Method to set the last name
	 * 
	 * @param lastName
	 * 
	 * @author Panagiotis Vakalis
	 * @version 16-07-2015
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * Method to get the date registered
	 * 
	 * @return date registered
	 * 
	 * @author Panagiotis Vakalis
	 * @version 16-07-2015
	 */
	public Date getDateRegistered() {
		return dateRegistered;
	}

	/**
	 * Method to set the date registered
	 * 
	 * @param dateRegistered
	 * 
	 * @author Panagiotis Vakalis
	 * @version 16-07-2015
	 */
	public void setDateRegistered(Date dateRegistered) {
		this.dateRegistered = dateRegistered;
	}

	/**
	 * Method to get the security question
	 * 
	 * @return security question
	 * 
	 * @author Panagiotis Vakalis
	 * @version 16-07-2015
	 */
	public String getSecurityQuestion() {
		return securityQuestion;
	}

	/**
	 * Method to set the security question
	 * 
	 * @param securityQuestion
	 * 
	 * @author Panagiotis Vakalis
	 * @version 16-07-2015
	 */
	public void setSecurityQuestion(String securityQuestion) {
		this.securityQuestion = securityQuestion;
	}

	/**
	 * Method to get the security answer
	 * 
	 * @return security answer
	 * 
	 * @author Panagiotis Vakalis
	 * @version 16-07-2015
	 */
	public String getSecurityAnswer() {
		return securityAnswer;
	}

	/**
	 * Method to set the security answer
	 * 
	 * @param securityAnswer
	 * 
	 * @author Panagiotis Vakalis
	 * @version 16-07-2015
	 */
	public void setSecurityAnswer(String securityAnswer) {
		this.securityAnswer = securityAnswer;
	}

	/**
	 * Method to get the portfolios which
	 * the investor holds
	 * 
	 * @return list of portfolios
	 * 
	 * @author Panagiotis Vakalis
	 * @version 16-07-2015
	 */
	public ArrayList<Portfolio> getPortfolios() {
		return portfolios;
	}

	/**
	 * Method to set the portfolios which
	 * the investor holds
	 * 
	 * @param list of portfolios
	 * 
	 * @author Panagiotis Vakalis
	 * @version 16-07-2015
	 */
	public void setPortfolios(ArrayList<Portfolio> portfolios) {
		this.portfolios = portfolios;
	}

	@Override
	public String toString() {
		return "Investor [id=" + id + ", email=" + email + ", password="
				+ password + ", firstName=" + firstName + ", lastName="
				+ lastName + ", dateRegistered=" + dateRegistered
				+ ", securityQuestion=" + securityQuestion
				+ ", securityAnswer=" + securityAnswer + "]";
	}
}
