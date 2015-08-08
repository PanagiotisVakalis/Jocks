package pxv425;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class which contains the model part of the 
 * reset password process
 * 
 * @author Panagiotis Vakalis
 * @version 17-07-2015
 *
 */
public class ChangePasswordModel extends Model {
	
	private LoginModel loginModel;
	private String securityQuestion;
	private String securityAnswer;
	/*
	 * This pattern has been found on:
	 * http://www.mkyong.com/regular-expressions/how-to-validate-email-address-with-regular-expression/
	 */
	private final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	private Pattern emailPattern;
	private Matcher emailMatcher;

	/**
	 * Constructor of the class
	 * @param client
	 * 
	 * @author Panagiotis Vakalis
	 * @version 17-07-2015
	 */
	public ChangePasswordModel(Client client) {
		super(client);
	}
	
	/**
	 * Method to change to login view
	 * @param loginView
	 * 
	 * @author Panagiotis Vakalis
	 * @version 17-07-2015
	 */
	private void changeToLoginView(LoginView loginView){
		loginModel = new LoginModel(super.getClient());
		super.getClient().useChangePanel(new LoginView(loginModel));
	}
	
	/**
	 * Method to use the changeToLoginView method outside the class
	 * @param loginView
	 * 
	 * @author Panagiotis Vakalis
	 * @version 17-07-2015
	 */
	public void useChangeToLoginView(LoginView loginView){
		changeToLoginView(loginView);
	}
	
//	/**
//	 * Method to retrieve the secutity question from the database
//	 * @param email
//	 * @return security question
//	 * 
//	 * @author Panagiotis Vakalis
//	 * @version 17-07-2015
//	 */
//	private String retrieveSecurityQuestion(String email){
//		return Database.useGetSecurityQuestion(email);
//	}
	
	/**
	 * Method to use the retrieveSecurityQuestion outside the class
	 * @param email
	 * 
	 * @author Panagiotis Vakalis
	 * @version 17-07-2015
	 */
	private void retrieveSecurityQuestion(String email){
		securityQuestion = Database.useGetSecurityQuestion(email) + "?";
		update(securityQuestion);
	}
	
	/**
	 * Method to access the securityQuestion private variable
	 * outside the class
	 * @return security question
	 * 
	 * @author Panagiotis Vakalis
	 * @version 17-07-2015
	 */
	public String getSecurityQuestion(){
		return securityQuestion;
	}
	
	/**
	 * Method to send the email to database
	 * @param email
	 * 
	 * @author Panagiotis Vakalis
	 * @version 17-07-2015
	 */
	private void submitEmail(String email){
		retrieveSecurityQuestion(email);
	}
	
	/**
	 * Method to use the submitEmail method outside the class
	 * @param email
	 * 
	 * @author Panagiotis Vakalis
	 * @version 17-07-2015
	 */
	public void useSubmitEmail(String email){
		submitEmail(email);
	}
	
	/**
	 * Method to get the security answer of the database
	 * @param email
	 * @return security answer
	 * 
	 * @author Panagiotis Vakalis
	 * @version 17-07-2015
	 */
	private String retrieveSecurityAnswer(String email){
		return Database.useGetSecurityAnswer(email);
	}
	
//	public void useRetrieveSecurityAnswer(String email){
//		securityAnswer = retrieveSecurityAnswer(email);
//	}
	
	/**
	 * Method which checks if the stored security answer matches
	 * the security answer which the investor has entered
	 * @param securityAnswer
	 * @param email
	 * @return true if they match
	 * @return false if they do not match 
	 * 
	 * @author Panagiotis Vakalis
	 * @version 17-07-2015
	 */
	private boolean checkSecurityAnswer(String securityAnswer, String email){
		this.securityAnswer = retrieveSecurityAnswer(email);
		return this.securityAnswer.equals(securityAnswer);
	}
	
	/**
	 * Method to change the password
	 * @param email
	 * @param securityAnswer
	 * @param newPassword
	 * @return Your password has been changed, if the password has been changed
	 * @return Wrong security answer, your password has not been changed, if the security answer and the answer which
	 * user has entered do not match
	 * 
	 * @author Panagiotis Vakalis
	 * @version 17-07-2015
	 */
	private String changePassword(String email, String securityAnswer, String newPassword, String reEnterNewPassword){
		//If email is not empty
		if(!email.isEmpty()){
			if(!securityAnswer.isEmpty()){
				if(checkSecurityAnswer(securityAnswer, email)){
//					if(checkPasswordCharacters(newPassword)){
//						Database.useResetInvestorPassword(email, newPassword);
//						return "Your password has been changed";
//					}
//					else{
//						return "Password should meet the requirements";
//					}
					if(newPassword.equals(reEnterNewPassword)){
						if(checkPasswordCharacters(newPassword)){
							Database.useChangeInvestorPassword(email, newPassword);
							return "Your password has been changed, press ok to continue to login screen.";
						}
						else{
							return "Password should meet the requirements";
						}
					}
					else{
						return "Passwords do not match. Press back button.";
					}
				}
				else{
					return "Wrong security answer, your password has not been changed. Press back button.";
				}
			}
			else{
				return "Go back and enter your security answer";
			}
		}
		else{
			return "Go back, enter your email and press submit";
		}
	}
	
	private boolean checkPasswordCharacters(String password){
		boolean passwordCorrect = false;
		if(password.length() >= 8 && password.length() <= 16){
			if(password.contains("0") || password.contains("1") || password.contains("2") || password.contains("3") || password.contains("4") || password.contains("5") || password.contains("6") || password.contains("7") || password.contains("8") || password.contains("9")){
				if(password.contains("!") || password.contains("@") || password.contains("#") || password.contains("$") || password.contains("%") || password.contains("^") || password.contains("&") || password.contains("*") || password.contains("-") || password.contains("_")){
					if(!password.contains("~") && !password.contains("`") && !password.contains("+") && !password.contains("=") && !password.contains("{") && !password.contains("[") && !password.contains("}") && !password.contains("]") && !password.contains("\"") && !password.contains("|") && !password.contains(":") && !password.contains(";") && !password.contains("'") && !password.contains("<") && !password.contains(",") && !password.contains(">") && !password.contains(".") && !password.contains("?") && !password.contains("/")){
						passwordCorrect = true;
					}
				}
			}
		}
		return passwordCorrect;
	}
	
	/**
	 * Method to use the changePassword method outside the class
	 * @param email
	 * @param securityAnswer
	 * @param newPassword
	 * @return Your password has been changed, if the password has been changed
	 * @return Wrong security answer, your password has not been changed, if the security answer and the answer which
	 * user has entered do not match
	 * 
	 * @author Panagiotis Vakalis
	 * @version 17-07-2015
	 */
	public String useChangePassword(String email, String securityAnswer, String newPassword, String reEnterNewPassword){
		return changePassword(email, securityAnswer, newPassword, reEnterNewPassword);
	}
	
	/**
	 * Method to update the view
	 * @param arg
	 * 
	 * @author Panagiotis Vakalis
	 * @version 17-07-2015
	 */
	private void update(Object arg){
		setChanged();
		notifyObservers();
	}
	/**
	 * Method to check if the email which is provided
	 * matches the email pattern
	 * @param email
	 * @return true, if it matches
	 * @return false, if it does not match
	 * 
	 * @author Panagiotis Vakalis
	 * @version 14-07-2015
	 */
	private boolean validEmail(String email){
		emailPattern = Pattern.compile(EMAIL_PATTERN);
		emailMatcher = emailPattern.matcher(email);
		return emailMatcher.matches();
	}
	
	public boolean useValidEmail(String email){
		return validEmail(email);
	}
	
	private boolean emailIsUsed(String email){
		return Database.useCheckEmail(email);
	}
	
	public boolean useEmailIsUsed(String email){
		return emailIsUsed(email);
	}
}
