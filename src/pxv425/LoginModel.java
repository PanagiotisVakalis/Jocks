package pxv425;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class contains the model part of the LoginProcess
 * 
 * @author Panagiotis Vakalis
 * @version 16-07-2015
 *
 */
public class LoginModel extends Model {
	private Object signInResult;
	private ChangePasswordModel changePasswordModel;
	private PortfolioModel portfolioModel;
	private ArrayList<Portfolio> portfolios;
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
	 * @version 16-07-2015
	 */
	public LoginModel(Client client) {
		super(client);
	}
	
	/**
	 * Method to use the signInResult variable outside the class
	 * @return singInResult
	 * 
	 * @author Panagiotis Vakalis
	 * @version 16-07-2015
	 */
	public Object getSignInResult(){
		return signInResult;
	}

	/**
	 * Method to terminate the programme
	 * 
	 * @author Panagiotis Vakalis
	 * @version 16-07-2015
	 */
	private void exitProgramme(){
		System.exit(1);
	}
	
	/**
	 * Method to use the exitProgramme() outside the class
	 * 
	 * @author Panagiotis Vakalis
	 * @version 16-07-2015
	 */
	public void useExitProgramme(){
		exitProgramme();
	}
	
	/**
	 * Method to login a user
	 * @param email
	 * @param password
	 * @return Investor if login is successful
	 * @return String if login is unsuccessful
	 * 
	 * @author Panagiotis Vakalis
	 * @version 16-07-2015
	 */
	private String loginUser(String email, String password){
		signInResult = Database.useGetInvestor(email, password);
		
		if(signInResult instanceof Investor){
			/*
			 * If database has returned an investor, set the investor
			 * in the client class, retrieve the portfolios which investor
			 * holds and return ok.
			 * Otherwise return no
			 */
			super.getClient().setInvestor((Investor)signInResult);
			portfolios = Database.useGetInvestorPortfolioNumber(super.getClient().getInvestor().getId());
			super.getClient().getInvestor().setPortfolios(portfolios);
			return "ok";
		}
		else{
			return "no";
		}
	}
	
	/**
	 * Method to use the loginUser method outside the class
	 * @param email
	 * @param password
	 * @return Investor if login is successful
	 * @return String if login is unsuccessful
	 * 
	 * @author Panagiotis Vakalis
	 * @version 16-07-2015
	 */
	public String useLoginUser(String email, String password){
		return loginUser(email, password);
	}
	
	/**
	 * Method to change panel to resetPasswordView
	 * @param resetPasswordView
	 * 
	 * @author Panagiotis Vakalis
	 * @version 17-07-2015
	 */
	private void changeToChangePasswordView(ChangePasswordView changePasswordView){
		changePasswordModel = new ChangePasswordModel(super.getClient());
		super.getClient().useChangePanel(changePasswordView);
	}
	
	/**
	 * Method to use the changeToResetPasswordView outside the class
	 * @param resetPasswordView
	 * 
	 * @author Panagiotis Vakalis
	 * @version 17-07-2015
	 */
	public void useChangeToChangePasswordView(ChangePasswordView resetPasswordView){
		changeToChangePasswordView(resetPasswordView);
	}
	
	/**
	 * Method to change panel to portfolioView
	 * @param portfolioView
	 * 
	 * @author Panagiotis Vakalis
	 * @version 18-07-2015
	 */
	private void changeToPortfolioView(PortfolioView portfolioView){
		//Get the portfolios which the investor holds
		portfolioModel = new PortfolioModel(super.getClient());
		super.getClient().useChangePanel(new PortfolioView(portfolioModel));
	}
	
	/**
	 * Method to use changeToPortfolioView outside the class
	 * @param portfolioView
	 * 
	 * @author Panagiotis Vakalis
	 * @version 18-07-2015
	 */
	public void useChangeToPortfolioView(PortfolioView portfolioView){
		changeToPortfolioView(portfolioView);
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
	public boolean useValidEmail(String email){
		return validEmail(email);
	}
	
	/**
	 * Method to change to login view
	 * @param loginView
	 * 
	 * @author Panagiotis Vakalis
	 * @version 18-07-2015
	 */
	private void changeToLoginView(LoginView loginView){
		super.getClient().useChangePanel(loginView);
	}
	
	/**
	 * Method to change to login view
	 * @param loginView
	 * 
	 * @author Panagiotis Vakalis
	 * @version 18-07-2015
	 */
	public void useChangeToLoginView(LoginView loginView){
		changeToLoginView(loginView);
	}
}
