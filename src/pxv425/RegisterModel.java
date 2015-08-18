package pxv425;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class which contains the methods for the register process
 * 
 * @author Panagiotis Vakalis
 * @version 14-07-2015
 *
 */
public class RegisterModel extends Model {

	private LoginModel loginModel;
	private IntroModel introModel;
	private Pattern emailPattern;
	private Matcher emailMatcher;
	private String firstName;
	private String lastName;
	private String email;
	/*
	 * This pattern has been found on:
	 * http://www.mkyong.com/regular-expressions/
	 * how-to-validate-email-address-with-regular-expression/
	 */
	private final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

	/**
	 * Constructor of the class
	 * 
	 * @param client
	 * 
	 * @author Panagiotis Vakalis
	 * @version 14-07-2015
	 */
	public RegisterModel(Client client) {
		super(client);
	}

	/**
	 * Method to change panel to intro
	 * 
	 * @param introView
	 * 
	 * @author Panagiotis Vakalis
	 * @version 14-07-2015
	 */
	private void changeToIntroView(IntroView introView) {
		introModel = new IntroModel(super.getClient());
		super.getClient().useChangePanel(introView);

	}

	/**
	 * Method to use the changeToIntroView method outside the class
	 * 
	 * @param introView
	 * 
	 * @author Panagiotis Vakalis
	 * @version 14-07-2015
	 */
	public void useChangeToIntroView(IntroView introView) {
		changeToIntroView(introView);
	}

	/**
	 * Method to clear the fields of the register form by changing panel to
	 * registerView
	 * 
	 * @param registerView
	 * 
	 * @author Panagiotis Vakalis
	 * @version 14-07-2015
	 */
	private void clearFields(View registerView) {
		super.getClient().useChangePanel(new RegisterView(this));
	}

	/**
	 * Method to use the clearFields method outside the class
	 * 
	 * @param registerView
	 * 
	 * @author Panagiotis Vakalis
	 * @version 14-07-2015
	 */
	public void useClearFields(View registerView) {
		clearFields(registerView);
	}

	/**
	 * Method to register user
	 * 
	 * @param email
	 * @param password
	 * @param password2
	 * @param firstName
	 * @param lastName
	 * @param securityQuestion
	 * @param securityAnswer
	 * @return Register succesfull, your portfolio is created, if register is
	 *         succesfull
	 * @return Password does not match, if the passwords do not match
	 * @return Email already used, if the email exists
	 * @return Email is not valid, if the email does not match the email pattern
	 * @return Fill all the fields, if there are blank fields
	 * 
	 * @author Panagiotis Vakalis
	 * @version 14-07-2015
	 */
	private String registerUser(String email, String password,
			String password2, String firstName, String lastName,
			String securityQuestion, String securityAnswer) {
		/*
		 * Since method getText() returns an empty String if the JTextField is
		 * null instead of null value this if statement checks if the String is
		 * empty
		 */
		if (!email.equals("") && !password.equals("") && !password2.equals("")
				&& !firstName.equals("") && !lastName.equals("")
				&& !securityQuestion.equals("") && !securityAnswer.equals("")) {
			if (validEmail(email)) {
				if (!Database.useCheckEmail(email)) {
					// If email is not used
					if (checkPasswordCharacters(password)) {
						if (password.equals(password2)) {
							this.firstName = firstName;
							this.lastName = lastName;
							this.email = email;
							Database.useRegisterInvestor(email,
									Password.USE_ENCRYPT_PASSWORD(password),
									firstName, lastName, securityQuestion,
									securityAnswer);
							/*
							 * Due to the fact that a portfolio is created
							 * automatically every time that a new investor is
							 * registered the following method will be used in
							 * order to create a new portfolio having some
							 * default values which they can be changed after.
							 */
							return "Register succesfull";
						} else {
							return "Password does not match";
						}
					} else {
						return "Password should meet the requirements";
					}
				} else {
					return "Email already used";
				}
			}

			else {
				return "Email is not valid";
			}
		} else {
			return "Fill all the fields";
		}
	}

	private boolean checkPasswordCharacters(String password) {
		boolean passwordCorrect = false;
		if (password.length() >= 8 && password.length() <= 16) {
			if (password.contains("0") || password.contains("1")
					|| password.contains("2") || password.contains("3")
					|| password.contains("4") || password.contains("5")
					|| password.contains("6") || password.contains("7")
					|| password.contains("8") || password.contains("9")) {
				if (password.contains("!") || password.contains("@")
						|| password.contains("#") || password.contains("$")
						|| password.contains("%") || password.contains("^")
						|| password.contains("&") || password.contains("*")
						|| password.contains("-") || password.contains("_")) {
					if (!password.contains("~") && !password.contains("`")
							&& !password.contains("+")
							&& !password.contains("=")
							&& !password.contains("{")
							&& !password.contains("[")
							&& !password.contains("}")
							&& !password.contains("]")
							&& !password.contains("\"")
							&& !password.contains("|")
							&& !password.contains(":")
							&& !password.contains(";")
							&& !password.contains("'")
							&& !password.contains("<")
							&& !password.contains(",")
							&& !password.contains(">")
							&& !password.contains(".")
							&& !password.contains("?")
							&& !password.contains("/")) {
						passwordCorrect = true;
					}
				}
			}
		}
		return passwordCorrect;
	}

	/**
	 * Method to use the registerUser method outside the class
	 * 
	 * @param email
	 * @param password
	 * @param password2
	 * @param firstName
	 * @param lastName
	 * @param securityQuestion
	 * @param securityAnswer
	 * @return Register succesfull, if register is succesfull
	 * @return Password does not match, if the passwords do not match
	 * @return Email already used, if the email exists
	 * @return Email is not valid, if the email does not match the email pattern
	 * @return Fill all the fields, if there are blank fields
	 * 
	 * @author Panagiotis Vakalis
	 * @version 14-07-2015
	 */
	public String useRegisterUser(String email, String password,
			String password2, String firstName, String lastName,
			String securityQuestion, String securityAnswer) {
		return registerUser(email, password, password2, firstName, lastName,
				securityQuestion, securityAnswer);
	}

	/**
	 * Method to check if the email which is provided matches the email pattern
	 * 
	 * @param email
	 * @return true, if it matches
	 * @return false, if it does not match
	 * 
	 * @author Panagiotis Vakalis
	 * @version 14-07-2015
	 */
	private boolean validEmail(String email) {
		emailPattern = Pattern.compile(EMAIL_PATTERN);
		emailMatcher = emailPattern.matcher(email);
		return emailMatcher.matches();
	}

	/**
	 * Method to create the first portfolio of an investor when he/she registers
	 * 
	 * @param name
	 * @param email
	 * @param balance
	 * @return Your portfolio has been created
	 * 
	 * @author Panagiotis Vakalis
	 * @version 14-07-2015
	 */
	private String createFirstPortfolio(String name, String email,
			BigDecimal balance) {
		Database.useCreatePortfolio(name, email, balance);
		return "Your portolio has been created.";
	}

	/**
	 * Method to use the createFirstPortfolio method outside class
	 * 
	 * @param name
	 * @param email
	 * @param balance
	 * @return Your portfolio has been created
	 * 
	 * @author Panagiotis Vakalis
	 * @version 14-07-2015
	 */
	public String useCreateFirstPortfolio(BigDecimal balance) {
		return createFirstPortfolio(firstName + " " + lastName, email, balance);
	}

	private void changeToLoginView(LoginView loginView) {
		loginModel = new LoginModel(super.getClient());
		super.getClient().useChangePanel(loginView);
	}

	public void useChangeToLoginView(LoginView loginView) {
		changeToLoginView(loginView);
	}
}
