package pxv425;

import java.math.BigDecimal;
import java.security.cert.CertPathValidatorException.Reason;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;

/**
 * JDBC class to connect to database as well manage it
 * 
 * @author Panagiotis Vakalis
 * @version 07-07-2015
 */
public class Database {

	/*
	 * Database url, database username and database password
	 * will be final static since they will not be changed and
	 * they will be part of the whole class
	 */
	//Url for home
	//Previous database
//	private static final String DATABASEURL = "jdbc:postgresql://10.44.130.49:5432/final_project_db";
	//new database
//	private static final String DATABASEURL = "jdbc:postgresql://10.44.130.49:5432/jocks_db";
	//Url for uni
	//Previous database
//	private static final String DATABASEURL = "jdbc:postgresql://10.8.180.117:5432/final_project_db";
	private static final String DATABASEURL = "jdbc:postgresql://10.44.130.49:5432/jocks_db";
	private static final String USERNAME = "postgres";
	private static final String PASSWORD = "electro_PA16989";
	//Instance variable to establish connection
	private static Connection connection;
	//Instance variable to store statement
	private Statement statement;
	//Instance variable to store the result
	private static ResultSet resultSet;
	//Instance variable to store the metadata
	private ResultSetMetaData metaData;
	//Instance variable to keep track of database connection status
	private static boolean connectedToDatabase = false;
	//Instance variable which will be used for queries
	private static String query;
	/*
	 * PreparedStatement is used instead of Statement because using the former
	 * the same query can be executed repeatedly using different parameter values
	 */
	private static PreparedStatement preparedStatement;
	/*
	 * Variable which will store the result of the signIn method.
	 * This will be either an Investor or a String.
	 */
	private static Object investorResult;
	//Variable where the security question will be stored
	private static String securityQuestionResult;
	//Variable where the security answer will be stored
	private static String securityAnswerResult;
//	private static int[] portfoliosNumberResult;
	private static ArrayList<Portfolio> portfoliosResult = new ArrayList<>();
	private static String stockSymbol;
	private static String stockName;
	private static String stockIndustry;
	private static double stockPrice;
	private static Stock stock;

	private static ArrayList<Stock> stocksList = new ArrayList<>();
	private static int howManyPortfolios;
	private static double totalInvestedMoney;
	private static BigDecimal totalBalance;
	private static double totalProfitLoss;
	
	private static Lot lot;
	private static double currentPrice;
	private static ArrayList<Lot> lotsList = new ArrayList<>();
	private static String lotStockSymbol;
	private static double lotBoughtPrice;
	private static int lotBoughtShares;
	private static double lotAmount;
	private static double lotCurrentAmount;
	private static double lotCurrentProfitLoss;
	private static Timestamp lotDate;
	
	//Buys
	private static Buy buy;
	private static ArrayList<Buy> buysList = new ArrayList<>();
	private static String buysStockSymbol;
	private static Timestamp buysDate;
	private static double buysPrice;
	private static int buysShares;
	private static double buysAmount;
	
	//Sells
	private static Sell sell;
	private static ArrayList<Sell> sellsList = new ArrayList<>();
	private static String sellsStockSymbol;
	private static Timestamp sellsDate;
	private static double sellsPrice;
	private static int sellsShares;
	private static double sellsAmount;
	private static double sellsProfitLoss;
	
	//Watchlist
	private static ArrayList<Stock> stocksWatchlist = new ArrayList<>();
	private static ArrayList<String> watchlistStockSymbols = new ArrayList<>();
	
	private static ArrayList<Double> historicalPrices = new ArrayList<>();
	private static ArrayList<Timestamp> dates = new ArrayList<>();
	
	private static Timestamp lastUpdatedOn;
	
	private static BigDecimal portfolioBalance;
	
	private static ArrayList<String> stockSymbols;
	
	private static ArrayList<Integer> portfolioNumbers;
	
	private static BigDecimal portfolioInitialBalance;
	private static BigDecimal portfolioInvestedMoney;
	
	/**
	 * Method to use the connection instance variable
	 * outside of the class
	 * @return connection
	 * 
	 * @author Panagiotis Vakalis
	 * @version 13-07-2015
	 */
	public static Connection getConnection(){
		return connection;
	}
	
	/**
	 * Method to connect to database
	 * @throws SQLException
	 * 
	 * @author Panagiotis Vakalis
	 * @version 07-07-2015
	 */
	
	private static void connectToDatabase() throws SQLException{
		/*
		 * getConnection method is used to connect to database
		 * using the url, username and password
		 */
		connection = DriverManager.getConnection(DATABASEURL, USERNAME, PASSWORD);
		connectedToDatabase = true;
	}
	
	/**
	 * Method to use connect to database method outside of the class
	 * @throws SQLException
	 * 
	 * @author Panagiotis Vakalis
	 * @version 12-07-2015
	 */
	public static void useConnectToDatabase() throws SQLException{
		connectToDatabase();
	}
	
	/**
	 * Method to register a new investor by inserting this particular
	 * investor as a new entry into the investor table
	 * @param email
	 * @param password
	 * @param firstName
	 * @param lastName
	 * @param securityQuestion
	 * @param securityAnswer
	 * 
	 * @author Panagiotis Vakalis
	 * @version 07-07-2015
	 */
	private static void registerInvestor(String email, String password, String firstName, String lastName, String securityQuestion, String securityAnswer){
		try {
//			if(checkEmail(email) == false){
//				connectToDatabase();
				//Create sql query which will be used in order to insert a new entry in the table
				query = "INSERT INTO investor (email, password, f_name, l_name, sec_question, sec_answer)" + " VALUES (?, ?, ?, ?, ?, ?)";
				//Execute the prepared statement
				preparedStatement = connection.prepareStatement(query);
				
				//Set the parameter values
				preparedStatement.setString(1, email);
				preparedStatement.setString(2, password);
				preparedStatement.setString(3, firstName);
				preparedStatement.setString(4, lastName);
				preparedStatement.setString(5, securityQuestion);
				preparedStatement.setString(6, securityAnswer);
				
				//Execute SQL statement
				preparedStatement.executeUpdate();
//				System.out.println("User has been registered");
//			}
//			else{
//				System.out.println("Email already used");
//			}
		} catch (SQLException e) {
//			System.err.println("Connection failed");
			e.printStackTrace();
		}
		finally{
			try {
				preparedStatement.close();
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Method to use the register investor outside of the class
	 * @param email
	 * @param password
	 * @param firstName
	 * @param lastName
	 * @param securityQuestion
	 * @param securityAnswer
	 * 
	 * @author Panagiotis Vakalis
	 * @version 15-07-2015
	 */
	public static void useRegisterInvestor(String email, String password, String firstName, String lastName, String securityQuestion, String securityAnswer){
		registerInvestor(email, password, firstName, lastName, securityQuestion, securityAnswer);
	}
	
	/**
	 * Method to signIn an already registered investor
	 * @param email
	 * @param password
	 * 
	 * @author Panagiotis Vakalis
	 * @version 15-07-2015
	 */
	private static void getInvestor(String email, String password){
		String emailResult = null;
		String passwordResult = null;
		int idResult = 0;
		String firstNameResult = null;
		String lastNameResult = null;
		Date dateRegisteredResult = null;
		String securityQuestionResult = null;
		String securityAnswerResult = null;
		Timestamp time = null;
		
		try {
			//Call method in order to connect to database
			connectToDatabase();
			//Setup query
//			query = "SELECT email, password FROM investor WHERE email = ?";
			query = "SELECT * FROM investor WHERE email = ?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, email);
			
			//Execute query
			resultSet = preparedStatement.executeQuery();
			
			//display each row
			while(resultSet.next()){
				emailResult = resultSet.getString("email");
				passwordResult = resultSet.getString("password");
				idResult = resultSet.getInt("id");
				firstNameResult = resultSet.getString("f_name");
				lastNameResult = resultSet.getString("l_name");
				time = resultSet.getTimestamp("date_reg");
				dateRegisteredResult = new Date(time.getTime());
				securityQuestionResult = resultSet.getString("sec_question");
				securityAnswerResult = resultSet.getString("sec_answer");
//				System.out.println(emailResult + " " + passwordResult);
			}
			
			if(checkEmail(email)){
//				if(emailResult.equals(email) && passwordResult.equals(password)){
				if(emailResult.equals(email) && Password.USE_DECRYPT_PASSWORD(passwordResult).equals(password)){
					investorResult = new Investor(idResult, emailResult, passwordResult, firstNameResult, lastNameResult, dateRegisteredResult, securityQuestionResult, securityAnswerResult);
//					System.out.println("singed in");
				}
				else{
					investorResult = new String("unsuccesful");
//					System.out.println("not signed in");
				}
			}
			else{
//				System.out.println("no investor with this email");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Method to use getInvestor method outside class
	 * @param email
	 * @param password
	 * @return 
	 * 
	 * @author Panagiotis Vakalis
	 * @version 15-07-2015
	 */
	public static Object useGetInvestor(String email, String password){
		/*
		 * Call this method in order to change the
		 * signInResult value
		 */
		getInvestor(email, password);
		
//		if(signInResult instanceof Investor){
//			return signInResult;
//		}
//		else{
//			return "Unsuccesful sign in";
//		}
		return investorResult;
	}
	
	/**
	 * Method which checks whether the email is used
	 * @param email
	 * @return true if the email is used
	 * @return false, if the email is not used
	 * 
	 * @author Panagiotis Vakalis
	 * @version 07-07-2015
	 */
	private static boolean checkEmail(String email){
		boolean found = false;
		String emailResult = null;
		
		try {
			connectToDatabase();
			query = "SELECT email, password FROM investor WHERE email = ?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, email);
			
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()){
				emailResult = resultSet.getString("email");
			}
			if(emailResult != null){
				found = true;
			}
			else{
				found = false;
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return found;
	}
	
	/**
	 * Method to use the checkEmail() outside of the class
	 * @param email
	 * @return true if the email exists
	 * @return false if the email does not exist
	 * 
	 * @author Panagiotis Vakalis
	 * @version 07-07-2015
	 */
	public static boolean useCheckEmail(String email){
		return checkEmail(email);
	}
	
	/**
	 * Method to check whether the portfolio name already exists
	 * @param email
	 * @param portfolioName
	 * @return true if portfolio name already exists
	 * @return false if portfolio name does not exist
	 * 
	 * @author Panagiotis Vakalis
	 * @version 11-07-2015
	 */
	private static boolean portfolioNameExists(String email, String portfolioName){
		String name = null;
		
		try {
			connectToDatabase();
			query = "SELECT name FROM portfolio WHERE inv_id = (SELECT id FROM investor WHERE email = ?)";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, email);
			
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()){
				name = resultSet.getString("name");
				if(name.equals(portfolioName)){
					return true;
				}
				else{
					return false;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public static boolean usePortfolioNameExists(String email, String portfolioName){
		return portfolioNameExists(email, portfolioName);
	}
	
	/**
	 * Method which resets the password
	 * @param email
	 * @param newPassword
	 * @author Panagiotis Vakalis
	 * @version 08-07-2015
	 */
	private static void changeInvestorPassword(String email, String newPassword){
		if(checkEmail(email)){
//			//Variables where the security question and answer will be stored
//			String securityQuestion = null;
//			String securityAnswer = null;
			try {
				//If email exists retrieve the security question and answer
//				query = "SELECT sec_question, sec_answer FROM investor WHERE email = ?";
//				preparedStatement = connection.prepareStatement(query);
//				preparedStatement.setString(1, email);
				
//				resultSet = preparedStatement.executeQuery();
				
//				//Iterate the result
//				while(resultSet.next()){
//					securityQuestion = resultSet.getString("sec_question");
//					securityAnswer = resultSet.getString("sec_answer");
//				}
				
				//Ask investor
//				System.out.println(securityQuestion + "?");
//				Scanner input = new Scanner(System.in);
				//Investor answers
//				String answer = input.next();
				
//				if(securityAnswer.equals(answer)){
					query = "UPDATE investor " + "SET password = ? WHERE email = ?";
					preparedStatement = connection.prepareStatement(query);
					preparedStatement.setString(1, Password.USE_ENCRYPT_PASSWORD(newPassword));
					preparedStatement.setString(2, email);
					
					preparedStatement.executeUpdate();
//					System.out.println("Password has been changed");
//				}
//				else{
//					System.out.println("Wrong answer");
//				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else{
//			System.out.println("No such email");
		}
	}
	
	/**
	 * Method to use resetInvestorPassword method outside the class
	 * @param email
	 * @param newPassword
	 * 
	 * @author Panagiotis Vakalis
	 * @version 08-07-2015
	 */
	public static void useChangeInvestorPassword(String email, String newPassword){
		try {
			connectToDatabase();
			changeInvestorPassword(email, newPassword);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Method to get the security question
	 * @param email
	 * 
	 * @author Panagiotis Vakalis
	 * @version 17-07-2015
	 */
	private static void getSecurityQuestion(String email){
		try {
			connectToDatabase();
			query = "SELECT sec_question FROM investor WHERE email = ?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, email);
			
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()){
				securityQuestionResult = resultSet.getString("sec_question");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Method to use the getSecurityQuestion outside the class
	 * @param email
	 * @return security question
	 * 
	 * @author Panagiotis Vakalis
	 * @version 17-07-2015
	 */
	public static String useGetSecurityQuestion(String email){
		getSecurityQuestion(email);
		return securityQuestionResult;
	}
	
	/**
	 * Method to retrieve the security answer
	 * @param email
	 * 
	 * @author Panagiotis Vakalis
	 * @version 17-07-2015
	 */
	private static void getSecurityAnswer(String email){
		try {
			connectToDatabase();
			query = "SELECT sec_answer FROM investor WHERE email = ?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, email);
			
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()){
				securityAnswerResult = resultSet.getString("sec_answer");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Method to use the getSecutityAnswer method outside the class
	 * @param email
	 * @return secutity answer
	 * 
	 * @author Panagiotis Vakalis
	 * @version 17-07-2015
	 */
	public static String useGetSecurityAnswer(String email){
		getSecurityAnswer(email);
		return securityAnswerResult;
	}
	
	/**
	 * Method to create a new portfolio
	 * 
	 * @param name of the portfolio
	 * @param email of the investor
	 * @param initial balance
	 * 
	 * @author Panagiotis Vakalis
	 * @version 08-07-2015
	 */
	private static void createPortfolio(String name, String email, BigDecimal balance){
		try {
			//Connect to database
			connectToDatabase();
			if(!portfolioNameExists(email, name)){
				//Query
				query = "INSERT INTO portfolio (name, inv_id, balance, initial_balance) " + "VALUES (?, (SELECT id FROM investor WHERE email = ?), ?, ?)";
				preparedStatement = connection.prepareStatement(query);
				preparedStatement.setString(1, name);
				preparedStatement.setString(2, email);
				preparedStatement.setBigDecimal(3, balance);
				preparedStatement.setBigDecimal(4, balance);
				
				preparedStatement.executeUpdate();
//				System.out.println("Portfolio has been created");
			}
			else{
//				System.out.println("Portfolio name already exists");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Method to use createPortolio method outside of the class
	 * @param name
	 * @param email
	 * @param balance
	 * 
	 * @author Panagiotis Vakalis
	 * @version 08-07-2015
	 */
	public static void useCreatePortfolio(String name, String email, BigDecimal balance){
		createPortfolio(name, email, balance);
	}
	
	
	/**
	 * Method to insert a new portfolio into the table portfolio
	 * @param id
	 * @param name
	 * @param balance
	 * 
	 * @author Panagiotis Vakalis
	 * @version 28-07-2015
	 */
	private static void createNewPortfolio(int id, String name, double balance){
		/*
		 * This method is used to create a new portfolio for the already
		 * registered investors, using their id.
		 */
		try {
			connectToDatabase();
			
			query = "INSERT INTO portfolio (name, inv_id, balance) " + "VALUES (?, ?, ?)";
			
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, name);
			preparedStatement.setInt(2, id);
			preparedStatement.setDouble(3, balance);
			
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Method to use the createNewPortfolio method outside the class
	 * @param id
	 * @param name
	 * @param balance
	 * 
	 * @author Panagiotis Vakalis
	 * @version 28-07-2015
	 */
	public static void useCreateNewPortfolio(int id, String name, double balance){
		createNewPortfolio(id, name, balance);
	}
	
	/**
	 * Method to update the balance of portfolio
	 * @param name
	 * @param email
	 * @param difference in amount
	 * 
	 * @author Panagiotis Vakalis
	 * @version 08-07-2015
	 */
	public static void updatePortfolioBalance(int portfolioNumber, BigDecimal amount){
		try {
			//Connect to database
			connectToDatabase();
			//Query
//			query = "UPDATE portfolio " + "SET balance = (SELECT balance FROM portfolio WHERE name = ? AND inv_id = (SELECT id FROM investor WHERE email = ?)) + ? WHERE name = ? AND inv_id = (SELECT id FROM investor WHERE email = ?)";
			
			query = "UPDATE portfolio " + "SET balance = (SELECT balance FROM portfolio WHERE number = ?) + ? WHERE number = ?";
			
			preparedStatement = connection.prepareStatement(query);
//			preparedStatement.setString(1, name);
//			preparedStatement.setString(2, email);
//			preparedStatement.setDouble(3, amount);
//			preparedStatement.setString(4, name);
//			preparedStatement.setString(5, email);
			
			preparedStatement.setInt(1, portfolioNumber);
			preparedStatement.setBigDecimal(2, amount);
			preparedStatement.setInt(3, portfolioNumber);
			
			preparedStatement.executeUpdate();
//			System.out.println("Portfolio's balance has been updated");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Method to update the name of the portfolio
	 * @param name
	 * @param email
	 * @param newName
	 * 
	 * @author Panagiotis Vakalis
	 * @version 08-07-2015
	 */
	private static void updatePortfolioName(int portfolioNumber, String email, String newName){
		try {
//			if(!portfolioNameExists(email, newName)){
				connectToDatabase();
				query = "UPDATE portfolio " + "SET name = ? WHERE number = ?";
				preparedStatement = connection.prepareStatement(query);
				preparedStatement.setString(1, newName);
				preparedStatement.setInt(2, portfolioNumber);
				
				preparedStatement.executeUpdate();
//				System.out.println("Portfolio's name has been updated");
//			}
//			else{
////				System.out.println("Portfolio name already exists");
//			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void useUpdatePortfolioName(int portfolioNumber, String email, String newName){
		updatePortfolioName(portfolioNumber, email, newName);
	}
	
	/**
	 * Method to update the invested money of a portfolio
	 * @param name
	 * @param email
	 * @param amount
	 * 
	 * @author Panagiotis Vakalis
	 * @version 08-07-2015
	 */
	public static void updatePortfolioInvestedMoney(int porfolioNumber, double amount){
		try {
			connectToDatabase();
//			query = "UPDATE portfolio " + "SET inv_money = (SELECT inv_money FROM portfolio WHERE name = ? AND inv_id = (SELECT id FROM investor WHERE email = ?)) + ? WHERE name = ? AND inv_id = (SELECT id FROM investor WHERE email = ?)";
//			query = "UPDATE portfolio " + "SET inv_money = (SELECT inv_money FROM portfolio WHERE name = ? AND inv_id = (SELECT id FROM investor WHERE email = ?)) + ? WHERE name = ? AND inv_id = (SELECT id FROM investor WHERE email = ?)";

			query = "UPDATE portfolio " + "SET inv_money = (SELECT inv_money FROM portfolio WHERE number = ?) + ? WHERE number = ?";
			
			preparedStatement = connection.prepareStatement(query);
//			preparedStatement.setString(1, name);
//			preparedStatement.setString(2, email);
//			preparedStatement.setDouble(3, amount);
//			preparedStatement.setString(4, name);
//			preparedStatement.setString(5, email);
			
			preparedStatement.setInt(1, porfolioNumber);
			preparedStatement.setDouble(2, amount);
			preparedStatement.setInt(3, porfolioNumber);
			
			preparedStatement.executeUpdate();
//			System.out.println("Portfolio's invested money has been updated");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Method to update the profit / loss of a portfolio
	 * @param name
	 * @param email
	 * @param profitLoss
	 * 
	 * @author Panagiotis Vakalis
	 * @version 08-07-2015
	 */
	public static void updatePortfolioProfitLoss(String name, String email, double profitLoss){
		try {
			connectToDatabase();
			query = "UPDATE portfolio " + "SET profit_loss = (SELECT profit_loss FROM portfolio WHERE name = ? AND inv_id = (SELECT id FROM investor WHERE email = ?)) + ? WHERE name = ? AND inv_id = (SELECT id FROM investor WHERE email = ?)";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, name);
			preparedStatement.setString(2, email);
			preparedStatement.setDouble(3, profitLoss);
			preparedStatement.setString(4, name);
			preparedStatement.setString(5, email);
			
			preparedStatement.executeUpdate();
//			System.out.println("Portfolio's profit/loss has been updated");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Method to get the investor portfolio
	 * @param id
	 * 
	 * @author Panagiotis Vakalis
	 * @version 18-07-2015
	 */
	private static void getInvestorPortfolio(Integer id){
		try {
			connectToDatabase();
			query = "SELECT * FROM portfolio WHERE inv_id = ?";
			
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, id);
			
			resultSet = preparedStatement.executeQuery();
			
//			int count = 0;
			while(resultSet.next()){
//				portfoliosNumberResult[count] = resultSet.getInt("number");
				Integer portfolioNumber = resultSet.getInt("number");
				String portfolioName = resultSet.getString("name");
//				Integer investorId = resultSet.getInt("inv_id");
				BigDecimal investedMoney = resultSet.getBigDecimal("inv_money");
				BigDecimal balance = resultSet.getBigDecimal("balance");
				double profitLoss = resultSet.getDouble("profit_loss");
				
				Portfolio portfolio = new Portfolio(portfolioNumber, portfolioName, investedMoney, balance, profitLoss);
				portfoliosResult.add(portfolio);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
//	private static Investor getInvestorUsingId(Integer investorId){
//		String investorEmail;
//		String investorPassword;
//		String investorFirstName;
//		String investorLastName;
//		String investorDateRegistered;
//		
//	}
	
	/**
	 * Method to use the getInvestorPortfolio outside the class
	 * @param id
	 * @return portfolios
	 * 
	 * @author Panagiotis Vakalis
	 * @version 18-07-2015
	 */
	public static ArrayList<Portfolio> useGetInvestorPortfolioNumber(Integer id){
		getInvestorPortfolio(id);
		return portfoliosResult;
	}
	
//	private static void getPortfolio(int[] portfolioNumbers){
//		query = "SELECT "
//	}
	
	/**
	 * Method which retrieves the portfolio details for a give investor
	 * @param id
	 * 
	 * @author Panagiotis Vakalis
	 * @version 20-07-2015
	 */
	private static void getPortfoliosDetails(int id){
		try {
			connectToDatabase();
			query = "SELECT COUNT(*), SUM(inv_money), SUM(balance), SUM(profit_loss) FROM portfolio WHERE inv_id = ?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, id);
			
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()){
				howManyPortfolios = resultSet.getInt(1);
				totalInvestedMoney = resultSet.getDouble(2);
				totalBalance = resultSet.getBigDecimal(3);
				totalProfitLoss = resultSet.getDouble(4);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Method to use the getPortfoliosDetails outside the class
	 * @param id
	 * 
	 * @author Panagiotis Vakalis
	 * @version 20-07-2015
	 */
	public static void useGetPortfoliosDetails(int id){
		getPortfoliosDetails(id);
	}
	
	public static int getHowManyPortfolios(){
		return howManyPortfolios;
	}
	
	private static void retrievePortfolioBalance(int portfolioNumber){
		
		try {
			query = "SELECT balance FROM portfolio WHERE number = ?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, portfolioNumber);
			
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()){
				portfolioBalance = resultSet.getBigDecimal("balance");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static BigDecimal useRetrievePortfolioBalance(int portfolioNumber){
		portfolioBalance = null;
		retrievePortfolioBalance(portfolioNumber);
		return portfolioBalance;
	}
	
	private static void retrievePortfolioInitialBalance(int portfolioNumber){
		
		try {
			query = "SELECT initial_balance FROM portfolio WHERE number = ?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, portfolioNumber);
			
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()){
				portfolioInitialBalance = resultSet.getBigDecimal("initial_balance");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static BigDecimal useRetrievePortfolioInitialBalance(int portfolioNumber){
		portfolioInitialBalance = null;
		retrievePortfolioInitialBalance(portfolioNumber);
		return portfolioInitialBalance;
	}
	
	private static BigDecimal retrieveTotalWithdraws(int portfolioNumber){
		BigDecimal totalWithdraws = null;
		
		try {
			query = "SELECT SUM(amount) AS total_amount FROM withdraw WHERE port_number = ?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, portfolioNumber);
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()){
//				totalWithdraws.add(resultSet.getBigDecimal("total_amount"));
				totalWithdraws = new BigDecimal(resultSet.getDouble("total_amount"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return totalWithdraws;
	}
	
	public static BigDecimal useRetrieveTotalWithdraws(int portfolioNumber){
		return retrieveTotalWithdraws(portfolioNumber);
	}
	
	private static BigDecimal retrieveTotalDeposits(int portfolioNumber){
		BigDecimal totalDeposits = null;
		
		try {
			query = "SELECT SUM(amount) AS total_amount FROM deposit WHERE port_number = ?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, portfolioNumber);
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()){
//				totalDeposits.add(resultSet.getBigDecimal("total_amount"));
				totalDeposits = new BigDecimal(resultSet.getDouble("total_amount"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return totalDeposits;
	}
	
	public static BigDecimal useRetrieveTotalDeposits(int portfolioNumber){
		return retrieveTotalDeposits(portfolioNumber);
	}
	
//	/**
//	 * Method to get the total invested money outside the class
//	 * @return total invested money
//	 * 
//	 * @author Panagiotis Vakalis
//	 * @version 20-07-2015
//	 */
//	public static double getTotalInvestedMoney(){
//		return totalInvestedMoney;
//	}
	
	private static BigDecimal retrieveInvestedMoney(int portfolioNumber){
		totalInvestedMoney = 0;
		
		
		try {
			query = "SELECT inv_money FROM portfolio WHERE number = ?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, portfolioNumber);
			
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()){
				portfolioInvestedMoney = resultSet.getBigDecimal("inv_money");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return portfolioInvestedMoney;
	}
	
	public static BigDecimal useRetrieveInvestedMoney(int portfolioNumber){
		return retrieveInvestedMoney(portfolioNumber);
	}
	
	private static void retrieveBalance(int portfolioNumber){
		
		try {
			query = "SELECT balance FROM portfolio WHERE number = ?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, portfolioNumber);
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()){
				totalBalance = resultSet.getBigDecimal("balance");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Method to get the total balance outside the class
	 * @return total balance
	 * 
	 * @author Panagiotis Vakalis
	 * @version 20-07-2015
	 */
	public static BigDecimal getTotalBalance(int portfolioNumber){
		retrieveBalance(portfolioNumber);
		return totalBalance.setScale(2, BigDecimal.ROUND_DOWN);
	}
	
	/**
	 * Method to get the total profit / loss outside the class
	 * @return total profit / loss
	 * 
	 * @author Panagiotis Vakalis
	 * @version 20-07-2015
	 */
	public static double getTotalProfitLoss(){
		return totalProfitLoss;
	}
	
	/**
	 * Method to insert a stock in the database
	 * @param symbol
	 * @param name
	 * @param industry
	 * @param currentPrice
	 * 
	 * @author Panagiotis Vakalis
	 * @version 08-07-2015
	 */
	public static void insertStock(String symbol, String name, String industry, double trade_price){
		try {
//			connectToDatabase();
			query = "INSERT INTO stock (symbol, name, industry, trade_price) " + "VALUES (?, ?, ?, ?)";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, symbol);
			preparedStatement.setString(2, name);
			preparedStatement.setString(3, industry);
			preparedStatement.setDouble(4, trade_price);
			
			preparedStatement.executeUpdate();
//			System.out.println("Stock has been inserted");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Method to update the stock's price
	 * @param symbol
	 * @param currentPrice
	 * 
	 * @author Panagiotis Vakalis
	 * @version 08-07-2015
	 */
	private static void updateStockPrice(String symbol, double trade_price){
		try {
//			connectToDatabase();
			query = "UPDATE stock " + "SET trade_price = ? WHERE symbol = ?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setDouble(1, trade_price);
//			preparedStatement.setString(2, symbol.substring(1, symbol.length()-1));
			preparedStatement.setString(2, symbol);
			
			preparedStatement.executeUpdate();
//			System.out.println("Stock's price has been updated");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void deleteStock(String symbol){
		
		try {
			query = "DELETE FROM stock WHERE symbol = ?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, symbol);
			
			preparedStatement.executeUpdate();
//			System.out.println(symbol + " has been deleted");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Method to use updateStockPrice method outside the class
	 * @param symbol
	 * @param trade_price
	 * 
	 * @author Panagiotis Vakalis
	 * @version 13-07-2015
	 */
	public static void useUpdateStockPrice(String symbol, double trade_price){
		updateStockPrice(symbol, trade_price);
	}
	
	/**
	 * Method to insert the stocks' prices into the stock_price table
	 * @param stockSymbol
	 * @param openPrice
	 * @param closePrice
	 * @param date
	 * 
	 * @author Panagiotis Vakalis
	 * @version 14-07-2015
	 */
	private static void insertStockPrice(String stockSymbol, double openPrice, double closePrice, String date, double adjustedClosePrice){
		try {
			query = "INSERT INTO stock_price (stock_symbol, open_price, close_price, profit_loss, date, adjusted_close_price) " + "VALUES(?, ?, ?, ?, ?, ?)";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, stockSymbol);
			preparedStatement.setDouble(2, openPrice);
			preparedStatement.setDouble(3, closePrice);
			preparedStatement.setDouble(4, closePrice - openPrice);
			preparedStatement.setString(5, date);
			preparedStatement.setDouble(6, adjustedClosePrice);
			
			preparedStatement.executeUpdate();
//			System.out.println("Stock price has been inserted");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Method to use insertStockPrice method outside of the class
	 * @param stockSymbol
	 * @param openPrice
	 * @param closePrice
	 * @param date
	 * 
	 * @author Panagiotis Vakalis
	 * @version 14-07-2015
	 */
	public static void useInsertStockPrice(String stockSymbol, double openPrice, double closePrice, String date, double adjustedClosePrice){
		insertStockPrice(stockSymbol, openPrice, closePrice, date, adjustedClosePrice);
	}
	
	private static void getLastUpdatedDate(String stockSymbol){
		
		try {
			query = "SELECT MAX(date) AS last_updated FROM stock_price WHERE stock_symbol = ?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, stockSymbol);
			
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()){
				lastUpdatedOn = resultSet.getTimestamp("last_updated");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static Timestamp useGetLastUpdatedDate(String stockSymbol){
		getLastUpdatedDate(stockSymbol);
		return lastUpdatedOn;
	}
	
	/**
	 * Method to get all the stocks
	 * 
	 * @author Panagiotis Vakalis
	 * @version 20-07-2015
	 */
	private static void getAllStocks(){
		try {
			connectToDatabase();
			
			query = "SELECT * FROM stock ORDER BY symbol";
			preparedStatement = connection.prepareStatement(query);
			
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()){
				stockSymbol = resultSet.getString("symbol");
				stockName = resultSet.getString("name");
				stockIndustry = resultSet.getString("industry");
				stockPrice = resultSet.getDouble("trade_price");
				
				stock = new Stock(stockSymbol, stockName, stockIndustry, stockPrice);
				stocksList.add(stock);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static void getStockNameUsingSymbol(String stockSymbol){
		try {
			connectToDatabase();
			
			query = "SELECT name FROM stock WHERE symbol = ?";
			
			preparedStatement = connection.prepareStatement(query);
			
			preparedStatement.setString(1, stockSymbol);
			
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()){
				stockName = resultSet.getString("name");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static String useGetStockNameUsingSymbol(String stockSymbol){
		getStockNameUsingSymbol(stockSymbol);
		return stockName;
	}
	
	/**
	 * Method to get all the stocks outside the class
	 * @return all the stocks
	 * 
	 * @author Panagiotis Vakalis
	 * @version 20-07-2015
	 */
	public static ArrayList<Stock> useGetAllStocks(){
		getAllStocks();
		return stocksList;
	}
	
	/**
	 * Method to insert a watch entry (watchlist)
	 * @param email
	 * @param stockSymbol
	 * 
	 * @author Panagiotis Vakalis
	 * @version 08-07-2015
	 */
	private static void insertWatch(int investorId, String stockSymbol){
		try {
			connectToDatabase();
			query = "INSERT INTO watches (inv_id, st_symbol) " + "VALUES (?, ?)";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, investorId);
			preparedStatement.setString(2, stockSymbol);
			
			preparedStatement.executeUpdate();
//			System.out.println("Watch created");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void useInsertWatch(int investorId, String stockSymbol){
		insertWatch(investorId, stockSymbol);
	}
	
	/**
	 * Method to delete a watch entry
	 * @param email
	 * @param stockSymbol
	 * 
	 * @author Panagiotis Vakalis
	 * @version 08-07-2015
	 */
	private static void deleteWatch(int id, String stockSymbol){
		try {
			connectToDatabase();
			query = "DELETE FROM watches WHERE inv_id = ? AND st_symbol = ?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, id);
			preparedStatement.setString(2, stockSymbol);
			
			preparedStatement.executeUpdate();
//			System.out.println("Watch has been deleted");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void useDeleteWatch(int id, String stockSymbol){
		deleteWatch(id, stockSymbol);
	}
	
	/**
	 * Method to retrieve the watchlist which a user has
	 * @param investorId
	 * 
	 * @author Panagiotis Vakalis
	 * @version 26-07-2015
	 */
	private static void retrieveWatches(int investorId){
		watchlistStockSymbols = new ArrayList<>();
		try {
			connectToDatabase();
			
			query = "SELECT st_symbol FROM watches WHERE inv_id = ?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, investorId);
			
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()){
				watchlistStockSymbols.add(resultSet.getString("st_symbol"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
//	public static ArrayList<String> useRetrieveWatches(int investorId){
//		retrieveWatches(investorId);
//		return watchlistStockSymbols;
//	}
	
	/**
	 * Method to retrieve the informations for stocks which an investor is interested in
	 * @param investorId
	 * 
	 * @author Panagiotis Vakalis
	 * @version 26-07-2015
	 */
	private static void retrieveStocksWhichInvestorWatches(int investorId){
		try {
			connectToDatabase();
			retrieveWatches(investorId);
			
			for(int i = 0; i < watchlistStockSymbols.size(); i++){
				query = "SELECT * FROM stock WHERE symbol = ?";
				
				preparedStatement = connection.prepareStatement(query);
				
				//Get all the stock symbols from the arraylist
				preparedStatement.setString(1, watchlistStockSymbols.get(i));
				
				resultSet = preparedStatement.executeQuery();
				
				while(resultSet.next()){
					stockSymbol = resultSet.getString("symbol");
					stockName = resultSet.getString("name");
					stockIndustry = resultSet.getString("industry");
					stockPrice = resultSet.getDouble("trade_price");
					
					stock = new Stock(stockSymbol, stockName, stockIndustry, stockPrice);
					
					stocksWatchlist.add(stock);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Method to use the retrieveStocksWhichInvestorWatches method outside the class
	 * @param investorId
	 * @return a list of stocks which the investor watches
	 * 
	 * @author Panagiotis Vakalis
	 * @version 26-07-2015
	 */
	public static ArrayList<Stock> useRetrieveStocksWhichInvestorWatches(int investorId){
		stocksWatchlist = new ArrayList<>();
		retrieveStocksWhichInvestorWatches(investorId);
		return stocksWatchlist;
	}
	
	/**
	 * Method to insert a lot in database
	 * @param email
	 * @param stockSymbol
	 * @param shares
	 * 
	 * @author Panagiotis Vakalis
	 * @version 08-07-2015
	 */
//	public static void insertLot(String email, String portfolioName, String stockSymbol, int shares){
	public static void insertLot(int portfolioNumber, String stockSymbol, int shares){
		try {
			connectToDatabase();
			query = "INSERT INTO lot (port_number, st_symbol, b_price, b_shares, amount, cur_amount) " + "VALUES (?, ?, (SELECT trade_price FROM stock WHERE symbol = ?), ?, ((SELECT trade_price FROM stock WHERE symbol = ?) * ?), ((SELECT trade_price FROM stock WHERE symbol = ?) * ?))";
			preparedStatement = connection.prepareStatement(query);
//			preparedStatement.setString(1, email);
//			preparedStatement.setString(2, portfolioName);
//			preparedStatement.setString(3, stockSymbol);
//			preparedStatement.setString(4, stockSymbol);
//			preparedStatement.setInt(5, shares);
//			preparedStatement.setString(6, stockSymbol);
//			preparedStatement.setInt(7, shares);
//			preparedStatement.setString(8, stockSymbol);
//			preparedStatement.setInt(9, shares);
			
			preparedStatement.setInt(1, portfolioNumber);
			preparedStatement.setString(2, stockSymbol);
			preparedStatement.setString(3, stockSymbol);
			preparedStatement.setInt(4, shares);
			preparedStatement.setString(5, stockSymbol);
			preparedStatement.setInt(6, shares);
			preparedStatement.setString(7, stockSymbol);
			preparedStatement.setInt(8, shares);
			
			preparedStatement.executeUpdate();
//			System.out.println("Lot has been recorded");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static void updateLotsWithCurrentPrice(int portfolioNumber){
		//Retrieve stock symbols which this portfolio has
		String queryRetrieveStocks;
		PreparedStatement retrieveStocksStatement;
		ResultSet stocks;
		String stock;
		
		
		try {
			queryRetrieveStocks = "SELECT st_symbol FROM lot WHERE port_number = ?";
			retrieveStocksStatement = connection.prepareStatement(queryRetrieveStocks);
			retrieveStocksStatement.setInt(1, portfolioNumber);
			
			stocks = retrieveStocksStatement.executeQuery();
			
//			while(stocks.next()){
//				//Update the prices into lots table
//				stock = stocks.getString("st_symbol");
//				query = "UPDATE lot SET cur_amount = ((SELECT trade_price FROM stock WHERE symbol = ?) * (SELECT b_shares FROM lot WHERE st_symbol = ?))";
//				preparedStatement = connection.prepareStatement(query);
//				preparedStatement.setString(1, stock);
//				preparedStatement.setString(2, stock);
//				
//				preparedStatement.executeUpdate();
//			}
			
			while(stocks.next()){
				
				//Update prices into lots table
				stock = stocks.getString("st_symbol");
				query = "UPDATE lot SET cur_amount = ? * ? WHERE st_symbol = ? AND port_number = ?";
				preparedStatement = connection.prepareStatement(query);
				preparedStatement.setDouble(1, retrieveStockTradePrice(stock));
				preparedStatement.setInt(2, retrieveBoughtSharesForAStock(stock, portfolioNumber));
				preparedStatement.setString(3, stock);
				preparedStatement.setInt(4, portfolioNumber);
				
				preparedStatement.executeUpdate();
				
				query = new String();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static double retrieveStockTradePrice(String stockSymbol){
		String queryRetrieveTradePrice;
		PreparedStatement retrieveTradePriceStatement;
		ResultSet tradePrice;
		double price = 0;
		
		try {
			queryRetrieveTradePrice = "SELECT trade_price FROM stock WHERE symbol = ?";
			retrieveTradePriceStatement = connection.prepareStatement(queryRetrieveTradePrice);
			retrieveTradePriceStatement.setString(1, stockSymbol);
			
			tradePrice = retrieveTradePriceStatement.executeQuery();
			
//			price = tradePrice.getDouble("trade_price");
			while(tradePrice.next()){
				price = tradePrice.getDouble("trade_price");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return price;
	}
	
	private static int retrieveBoughtSharesForAStock(String stockSymbol, int portfolioNumber){
		String queryRetrieveShares;
		PreparedStatement retrieveSharesStatement;
		ResultSet sharesSet;
		int shares = 0;
		
		try {
			queryRetrieveShares = "SELECT b_shares FROM lot WHERE st_symbol = ? AND port_number = ?";
			retrieveSharesStatement = connection.prepareStatement(queryRetrieveShares);
			retrieveSharesStatement.setString(1, stockSymbol);
			retrieveSharesStatement.setInt(2, portfolioNumber);
			
			sharesSet = retrieveSharesStatement.executeQuery();
//			shares = sharesSet.getInt("b_shares");
			
			while(sharesSet.next()){
				shares = sharesSet.getInt("b_shares");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return shares;
	}
	
	public static void useUpdateLotsWithCurrentPrice(int portfolioNumber){
		updateLotsWithCurrentPrice(portfolioNumber);
	}
	
	/**
	 * Method to get the lots for a specific portfolio.
	 * The current price is retrieved as well
	 * @param portfolioNumber
	 * 
	 * @author Panagiotis Vakalis
	 * @version 23-07-2015	
	 */
	private static void getLotsForASpecificPortfolio(int portfolioNumber){
		
		//local variables which will be used in order to get the current price for each stock
		String queryFindCurrentPrice;
		PreparedStatement preparedStatementForCurrentPrice;
		ResultSet currentPriceResult ;
		
		try {
			query = "SELECT * FROM lot WHERE port_number = ? ORDER BY st_symbol";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, portfolioNumber);
			
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()){
				lotStockSymbol = resultSet.getString("st_symbol");
				lotBoughtPrice = resultSet.getDouble("b_price");
				lotBoughtShares = resultSet.getInt("b_shares");
				lotAmount = resultSet.getDouble("amount");
				//I retrieve the current amount by calculating it durng the construction
//				lotCurrentAmount = resultSet.getDouble("cur_amount");
//				lotCurrentProfitLoss = resultSet.getDouble("cur_profit_loss");
//				lotDate.setTimeInMillis(resultSet.getTimestamp("date").getTime());
				lotDate = resultSet.getTimestamp("date");
				/*
				 * Get the current price for this specific stock symbol
				 */
				queryFindCurrentPrice = "SELECT trade_price FROM stock WHERE symbol = ?";
				preparedStatementForCurrentPrice = connection.prepareStatement(queryFindCurrentPrice);
				preparedStatementForCurrentPrice.setString(1, lotStockSymbol);
				
				currentPriceResult = preparedStatementForCurrentPrice.executeQuery();
				while(currentPriceResult.next()){
					currentPrice = currentPriceResult.getDouble("trade_price");
				}
				
				/*
				 * Create a new lot using the previous variables
				 */
				lot = new Lot(portfolioNumber, lotStockSymbol, lotBoughtPrice, lotBoughtShares, lotAmount, currentPrice, currentPrice * lotBoughtShares, lotDate);
				
				lotsList.add(lot);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Method to use the getLotsForASpecificPortfolio method outside the class
	 * @param portfolioNumber
	 * @return arraylist of lots
	 * 
	 * @author Panagiotis Vakalis
	 * @version 23-07-2015	
	 */
	public static ArrayList<Lot> useGetLotsForASpecificPortfolio(int portfolioNumber){
		lotsList = new ArrayList<>();
		getLotsForASpecificPortfolio(portfolioNumber);
		return lotsList;
	}
	
	/**
	 * Method to delete a lot from database
	 * @param email
	 * @param stockSymbol
	 * @param date
	 * 
	 * @author Panagiotis Vakalis
	 * @version 08-07-2015
	 */
	private static void deleteLot(int portfolioNumber, String stockSymbol, Timestamp date){
		if(lotExists(portfolioNumber, stockSymbol)){
			
			try {
				query = "DELETE FROM lot WHERE port_number = ? AND st_symbol = ? AND date = ?";
				preparedStatement = connection.prepareStatement(query);
				preparedStatement.setInt(1, portfolioNumber);
				preparedStatement.setString(2, stockSymbol);
				preparedStatement.setTimestamp(3, date);
				
				preparedStatement.executeUpdate();
//				System.out.println("Lot has been deleted");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		else{
//			System.out.println("No such lot");
		}
	}
	
	public static void useDeleteLot(int portfolioNumber, String stockSymbol, Timestamp date){
		deleteLot(portfolioNumber, stockSymbol, date);
	}
	
	/**
	 * Method to check whether or not a lot exists
	 * @param email
	 * @param stockSymbol
	 * @return true if it exists
	 * @return false if it does not exist
	 * 
	 * @author Panagiotis Vakalis
	 * @version 08-07-2015
	 */
	private static boolean lotExists(int portfolioNumber, String stockSymbol){
		boolean exists = false;
		try {
			connectToDatabase();
			query = "SELECT st_symbol FROM lot WHERE port_number = ?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, portfolioNumber);
			
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()){
				if(stockSymbol.equals(resultSet.getString("st_symbol"))){
					exists = true;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return exists;
	}
	
	private static void updateSharesBoughtInLot(int portfolioNumber, String stockSymbol, Timestamp date, int shares){
		
		try {
			//Update the lot using the current price in current amount
			query = "UPDATE lot SET b_shares = ?, amount = ?, cur_amount = ? " + "WHERE port_number = ? AND st_symbol = ? AND date = ?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, shares);
			preparedStatement.setDouble(2, shares * getBoughtPriceOfALot(stockSymbol, portfolioNumber, date));
			preparedStatement.setDouble(3, shares * getCurrentPriceOfAStock(stockSymbol));
			preparedStatement.setInt(4, portfolioNumber);
			preparedStatement.setString(5, stockSymbol);
			preparedStatement.setTimestamp(6, date);
			
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void useUpdateSharesBoughtInLot(int portfolioNumber, String stockSymbol, Timestamp date, int shares){
		updateSharesBoughtInLot(portfolioNumber, stockSymbol, date, shares);
	}
	
	private static double getCurrentPriceOfAStock(String stockSymbol){
		String queryCurrentPrice;
		PreparedStatement currentPriceStatement;
		ResultSet currentPriceResultSet;
		double currentPrice = 0;
		
		//Get the current price for this specific stock
		
		try {
			queryCurrentPrice = "SELECT trade_price FROM stock WHERE symbol = ?";
			currentPriceStatement = connection.prepareStatement(queryCurrentPrice);
			currentPriceStatement.setString(1, stockSymbol);
			currentPriceResultSet = currentPriceStatement.executeQuery();
			
			while(currentPriceResultSet.next()){
				currentPrice = currentPriceResultSet.getDouble("trade_price");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return currentPrice;
	}
	
	private static double getBoughtPriceOfALot(String stockSymbol, int portfolioNumber, Timestamp date){
		String queryBoughtPrice;
		PreparedStatement boughtPriceStatement;
		ResultSet boughtPriceSet;
		double boughtPrice = 0;
		
		try {
			queryBoughtPrice = "SELECT b_price FROM lot WHERE port_number = ? AND st_symbol = ? AND date = ?";
			boughtPriceStatement = connection.prepareStatement(queryBoughtPrice);
			boughtPriceStatement.setInt(1, portfolioNumber);
			boughtPriceStatement.setString(2, stockSymbol);
			boughtPriceStatement.setTimestamp(3, date);
			
			boughtPriceSet = boughtPriceStatement.executeQuery();
			
			while(boughtPriceSet.next()){
				boughtPrice = boughtPriceSet.getDouble("b_price");
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return boughtPrice;
	}
	
	/**
	 * Method to insert a buy
	 * @param stockSymbol
	 * @param email
	 * @param portfolioName
	 * @param shares
	 * 
	 * @author Panagiotis Vakalis
	 * @version 08-07-2015
	 */
	public static void insertBuy(String stockSymbol, int portfolioNumber, int shares){
		try {
			connectToDatabase();
			
//			//Get portfolio number
//			String selectPortfolioNumber = "SELECT number FROM portfolio WHERE inv_id = (SELECT id FROM investor WHERE email = ?) AND name = ?";
//			PreparedStatement statementPortfolioNumber = connection.prepareStatement(selectPortfolioNumber);
//			statementPortfolioNumber.setString(1, email);
//			statementPortfolioNumber.setString(2, portfolioName);
//			ResultSet resultPortfolioNumber = statementPortfolioNumber.executeQuery();
//			int portfolioNumber = 0;
//			
//			while(resultPortfolioNumber.next()){
//				portfolioNumber = resultPortfolioNumber.getInt("number");
//				System.out.println(portfolioNumber);
//			}
			
			//Get most recent day
			String selectMaxDate = "SELECT MAX(date) AS date FROM lot WHERE port_number = ? AND st_symbol = ?";
			PreparedStatement statementMaxDate = connection.prepareStatement(selectMaxDate);
			statementMaxDate.setInt(1, portfolioNumber);
			statementMaxDate.setString(2, stockSymbol);
			ResultSet resultMaxDate = statementMaxDate.executeQuery();
			Timestamp maxDate = null;
			
			while(resultMaxDate.next()){
				maxDate = resultMaxDate.getTimestamp("date");
//				System.out.println(maxDate);
			}
			
			//Get bought price
			String selectBoughtPrice = "SELECT b_price FROM lot WHERE port_number = ? AND st_symbol = ? AND date = ?";
			PreparedStatement statementBoughtPrice = connection.prepareStatement(selectBoughtPrice);
			statementBoughtPrice.setInt(1, portfolioNumber);
			statementBoughtPrice.setString(2, stockSymbol);
			statementBoughtPrice.setTimestamp(3, maxDate);
			ResultSet resultBoughtPrice = statementBoughtPrice.executeQuery();
			double boughtPrice = 0;
			
			while(resultBoughtPrice.next()){
				boughtPrice = resultBoughtPrice.getDouble("b_price");
//				System.out.println(boughtPrice);
			}
			
			//Insert a new buy
			query = "INSERT INTO buy (st_symbol, port_number, bought_price, bought_shares, bought_amount, date) " + "VALUES (?, ?, ?, ?, ?, ?)";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, stockSymbol);
			preparedStatement.setInt(2, portfolioNumber);
			preparedStatement.setDouble(3, boughtPrice);
			preparedStatement.setInt(4, shares);
			preparedStatement.setDouble(5, boughtPrice * shares);
			preparedStatement.setTimestamp(6, maxDate);
			
			preparedStatement.executeUpdate();
//			System.out.println("A buy has been inserted");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Method to insert a new sell
	 * @param stockSymbol
	 * @param email
	 * @param portfolioName
	 * @param shares
	 * 
	 * @author Panagiotis Vakalis
	 * @version 08-07-2015
	 */
	private static void insertSell(String stockSymbol, int portfolioNumber, int shares, double currentPrice, Timestamp date){
		try {
			connectToDatabase();
			
//			//Retrieve the bought price
//			String selectBoughtPrice = "SELECT b_price FROM lot WHERE port_number = ? AND st_symbol = ?";
//			PreparedStatement statementBoughtPrice = connection.prepareStatement(selectBoughtPrice);
//			statementBoughtPrice.setInt(1, porftolioNumber);
////			statement.setString(2, portfolioName);
//			statementBoughtPrice.setString(2, stockSymbol);
//			
//			ResultSet resultBoughtPrice = statementBoughtPrice.executeQuery();
//			
//			double boughtPrice = 0;
//			
//			while(resultBoughtPrice.next()){
//				boughtPrice = resultBoughtPrice.getDouble("b_price");
//			}
			
//			query = "INSERT INTO sell (st_symbol, port_number, sold_price, sold_shares, sold_amount, profit_loss) " + "VALUES (?, ?, (SELECT trade_price FROM stock WHERE symbol = ?), ?, ((SELECT trade_price FROM stock WHERE symbol = ?) * ?), ((SELECT trade_price FROM stock WHERE symbol = ?) * ?) - (? * ?))";
			query = "INSERT INTO sell (st_symbol, port_number, sold_price, sold_shares, sold_amount, profit_loss) " + "VALUES(?, ?, ?, ?, ?, ?)";
			preparedStatement = connection.prepareStatement(query);
//			preparedStatement.setString(1, stockSymbol);
//			preparedStatement.setInt(2, portfolioNumber);
////			preparedStatement.setString(3, portfolioName);
//			preparedStatement.setString(3, stockSymbol);
//			preparedStatement.setInt(4, shares);
//			preparedStatement.setString(5, stockSymbol);
//			preparedStatement.setInt(6, shares);
//			preparedStatement.setString(7, stockSymbol);
//			preparedStatement.setInt(8, shares);
//			preparedStatement.setDouble(9, getBoughtPriceOfALot(stockSymbol, portfolioNumber, date));
//			preparedStatement.setInt(10, shares);
			
			preparedStatement.setString(1, stockSymbol);
			preparedStatement.setInt(2, portfolioNumber);
			preparedStatement.setDouble(3, currentPrice);
			preparedStatement.setInt(4, shares);
			preparedStatement.setDouble(5, currentPrice * shares);
			preparedStatement.setDouble(6, (currentPrice * shares) - (getBoughtPriceOfALot(stockSymbol, portfolioNumber, date) * shares));
			
			preparedStatement.executeUpdate();
//			System.out.println("Sell has been inserted");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void useInsertSell(String stockSymbol, int portfolioNumber, int shares, double currentPrice, Timestamp date){
		insertSell(stockSymbol, portfolioNumber, shares, currentPrice, date);
	}
	
	/**
	 * Method to retrieve the buys
	 * @param email
	 * @param portfolioName
	 * 
	 * @author Panagiotis Vakalis
	 * @version 08-07-2015
	 */
	private static void retrieveBuys(int portfolioNumber){
		try {
			connectToDatabase();
			query = "SELECT st_symbol, date, bought_price, bought_shares, bought_amount FROM buy WHERE port_number = ?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, portfolioNumber);
			
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()){
				buysStockSymbol = resultSet.getString("st_symbol");
//				lotDate.setTimeInMillis(resultSet.getTimestamp("date").getTime());
//				buysDate.setTimeInMillis(resultSet.getTimestamp("date").getTime());
//				Timestamp timestamp = new Timestamp(resultSet.getTimestamp("date").getTime());
//				buysDate = new Date(timestamp.getTime());
				buysDate = resultSet.getTimestamp("date");
				buysPrice = resultSet.getDouble("bought_price");
				buysShares = resultSet.getInt("bought_shares");
				buysAmount = resultSet.getDouble("bought_amount");
				
				buy = new Buy(buysStockSymbol, portfolioNumber, buysDate, buysPrice, buysShares, buysAmount);
				
				buysList.add(buy);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Method to use the retrieveBuys method outside the class
	 * @param portfolioNumber
	 * @return buys list
	 * 
	 * @author Panagiotis Vakalis
	 * @version 24-07-2015
	 */
	public static ArrayList<Buy> useRetrieveBuys(int portfolioNumber){
		buysList = new ArrayList<>();
		retrieveBuys(portfolioNumber);
		return buysList;
	}
	
	/**
	 * Method to retrieve sells
	 * @param email
	 * @param portfolioName
	 * 
	 * @author Panagiotis Vakalis
	 * @version 08-07-2015
	 */
	private static void retrieveSells(int portfolioNumber){
		try {
			connectToDatabase();
//			query = "SELECT * FROM sell WHERE port_number = (SELECT number FROM portfolio WHERE inv_id = (SELECT id FROM investor WHERE email = ?) AND name = ?)";
			query = "SELECT st_symbol, date, sold_price, sold_shares, sold_amount, profit_loss FROM sell WHERE port_number = ?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, portfolioNumber);
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()){
//				System.out.println("Stock symbol: " + resultSet.getString("st_symbol") + " Sold price: " + resultSet.getDouble("sold_price") + " Sold shares: " + resultSet.getInt("sold_shares") + " Amount: " + resultSet.getDouble("sold_amount") + " Profit/Loss : " + resultSet.getDouble("profit_loss") + " Date: " + resultSet.getTimestamp("date"));
				sellsStockSymbol = resultSet.getString("st_symbol");
//				sellsDate.setTimeInMillis(resultSet.getTimestamp("date").getTime());
//				Timestamp timestamp = new Timestamp(resultSet.getTimestamp("date").getTime());
//				sellsDate = new Date(timestamp.getTime());
				sellsDate = resultSet.getTimestamp("date");
				sellsPrice = resultSet.getDouble("sold_price");
				sellsShares = resultSet.getInt("sold_shares");
				sellsAmount = resultSet.getDouble("sold_amount");
				sellsProfitLoss = resultSet.getDouble("profit_loss");
				
				sell = new Sell(sellsStockSymbol, portfolioNumber, sellsDate, sellsPrice, sellsShares, sellsAmount, sellsProfitLoss);
				
				sellsList.add(sell);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Method to use the retrieveSells method outside the class
	 * @param portfolioNumber
	 * @return sellsList
	 * 
	 * @author Panagiotis Vakalis
	 * @version 24-07-2015
	 */
	public static ArrayList<Sell> useRetrieveSells(int portfolioNumber){
		sellsList = new ArrayList<>();
		retrieveSells(portfolioNumber);
		return sellsList;
	}
	
	/**
	 * Method to retrieve portfolio
	 * @param email
	 * @param portfolioName
	 * 
	 * @author Panagiotis Vakalis
	 * @version 08-07-2015
	 */
	public static void retrievePortfolio(String email, String portfolioName){
		try {
			connectToDatabase();
			query = "SELECT name, inv_money, balance, profit_loss FROM portfolio WHERE inv_id = (SELECT id FROM investor WHERE email = ?) AND name = ?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, email);
			preparedStatement.setString(2, portfolioName);
			
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()){
//				System.out.println("Portfolio name: " + resultSet.getString("name") + " Invested money: " + resultSet.getDouble("inv_money") + " Balance: " + resultSet.getDouble("balance") + " Profit/Loss: " + resultSet.getDouble("profit_loss"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Method which retrieves the historical prices for a stock
	 * @param stockSymbol
	 * 
	 * @author Panagiotis Vakalis
	 * @version 29-07-2015
	 */
	private static void getHistoricalPrices(String stockSymbol){
		//Reinitialize the variable
		historicalPrices = new ArrayList<>();
		
		try {
			query = "SELECT close_price, date FROM stock_price WHERE stock_symbol = ?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, stockSymbol);
			
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()){
				historicalPrices.add(resultSet.getDouble("close_price"));
				dates.add(resultSet.getTimestamp("date"));
			}
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	
	/**
	 * Method to use the getHistoricalPrices method outside the class
	 * @param stockSymbol
	 * @return stock prices
	 * 
	 * @author Panagiotis Vakalis
	 * @version 29-07-2015
	 */
	public static ArrayList<Double> useGetHistoricalPrices(String stockSymbol){
		getHistoricalPrices(stockSymbol);
		return historicalPrices;
	}
	
	public static ArrayList<Timestamp> getDatesForHistoricalPrices(String stockSymbol){
		return dates;
	}
	
	private static ArrayList<String> getStockSymbols(){
		stockSymbols = new ArrayList<String>();
		try {
			connection = Database.getConnection();
			query = "SELECT symbol FROM stock";
			preparedStatement = connection.prepareStatement(query);
			
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()){
				stockSymbols.add(resultSet.getString("symbol"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return stockSymbols;
	}
	
	public static ArrayList<String> useGetStockSymbols(){
		return getStockSymbols();
	}
	
	private static void insertIntoPortfolioBalance(int portfolioNumber, BigDecimal totalBalance){
		
		try {
			query = "INSERT INTO portfolio_balance (port_number, total_balance) " + "VALUES (?, ?)";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, portfolioNumber);
			preparedStatement.setBigDecimal(2, totalBalance);
			
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void useInsertIntoPortfolioBalance(int portfolioNumber, BigDecimal totalBalance){
		insertIntoPortfolioBalance(portfolioNumber, totalBalance);
	}
	
	private static ArrayList<Integer> getPortfolioNumbers(){
		portfolioNumbers = new ArrayList<>();
		try {
			query = "SELECT number FROM portfolio";
			preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()){
				portfolioNumbers.add(resultSet.getInt("number"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return portfolioNumbers;
	}
	
	public static ArrayList<Integer> useGetPortfolioNumbers(){
		return getPortfolioNumbers();
	}
	
	private static BigDecimal getBalance(int portfolioNumber){
		BigDecimal balance = null;
		
		
		try {
			query = "SELECT balance FROM portfolio WHERE number = ?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, portfolioNumber);
			
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()){
				balance = new BigDecimal(resultSet.getDouble("balance"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return balance;
	}
	
	public static BigDecimal useGetBalance(int portfolioNumber){
		return getBalance(portfolioNumber);
	}
	
	private static BigDecimal getInvestedMoney(int portfolioNumber){
		BigDecimal investedMoney = null;
		
		
		try {
			query = "SELECT inv_money FROM portfolio WHERE number = ?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, portfolioNumber);
			
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()){
				investedMoney = new BigDecimal(resultSet.getDouble("inv_money"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return investedMoney.setScale(2, BigDecimal.ROUND_DOWN);
	}
	
	public static BigDecimal useGetInvestedMoney(int portfolioNumber){
		return getInvestedMoney(portfolioNumber);
	}
	
	private static Timestamp getTheFirstDateFromPortfolioBalance(int portfolioNumber){
		Timestamp firstDate = null;
		String firstDateQuery;
		PreparedStatement firstDateStatement;
		ResultSet firstDateResult;
		
		try {
			firstDateQuery = "SELECT MIN(date) AS start FROM portfolio_balance WHERE port_number = ?";
			firstDateStatement = connection.prepareStatement(firstDateQuery);
			firstDateStatement.setInt(1, portfolioNumber);
			firstDateResult = firstDateStatement.executeQuery();
			
			while(firstDateResult.next()){
				firstDate = firstDateResult.getTimestamp("start");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return firstDate;
	}
	
	public static Timestamp useGetTheFirstDateFromPortfolioBalance(int portfolioNumber){
		return getTheFirstDateFromPortfolioBalance(portfolioNumber);
	}
	
	private static Timestamp getTheLastDateFromPortfolioBalance(int portfolioNumber){
		Timestamp lastDate = null;
		String lastDateQuery;
		PreparedStatement lastDateStatement;
		ResultSet lastDateResult;
		
		try {
			lastDateQuery = "SELECT MAX(date) AS end FROM portfolio_balance WHERE port_number = ?";
			lastDateStatement = connection.prepareStatement(lastDateQuery);
			lastDateStatement.setInt(1, portfolioNumber);
			lastDateResult = lastDateStatement.executeQuery();
			
			while(lastDateResult.next()){
				lastDate = lastDateResult.getTimestamp("end");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lastDate;
	}
	
	public static Timestamp useGetTheLastDateFromPortfolioBalance(int portfolioNumber){
		return getTheLastDateFromPortfolioBalance(portfolioNumber);
	}
	
//	private static BigDecimal getTheTotalBalanceOnTheFirstDate(int portfolioNumber){
//		BigDecimal totalBalanceOnTheFirstDate = null;
//		
//		
//		try {
//			query = "SELECT total_balance FROM portfolio_balance WHERE port_number = ? AND date = ?";
//			preparedStatement = connection.prepareStatement(query);
//			preparedStatement.setInt(1, portfolioNumber);
//			preparedStatement.setTimestamp(2, getTheFirstDateFromPortfolioBalance(portfolioNumber));
//			
//			resultSet = preparedStatement.executeQuery();
//			
//			while(resultSet.next()){
//				totalBalanceOnTheFirstDate = new BigDecimal(resultSet.getDouble("total_balance"));
//			}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		return totalBalanceOnTheFirstDate;
//	}
//	
//	public static BigDecimal useGetTheTotalBalanceOnTheFirstDate(int portfolioNumber){
//		return getTheTotalBalanceOnTheFirstDate(portfolioNumber);
//	}
	
//	private static BigDecimal getTheTotalBalanceOnTheLastDate(int portfolioNumber){
//		BigDecimal totalBalanceOnTheLastDate = null;
//		
//		
//		try {
//			query = "SELECT total_balance FROM portfolio_balance WHERE port_number = ? AND date = ?";
//			preparedStatement = connection.prepareStatement(query);
//			preparedStatement.setInt(1, portfolioNumber);
//			preparedStatement.setTimestamp(2, getTheLastDateFromPortfolioBalance(portfolioNumber));
//			
//			resultSet = preparedStatement.executeQuery();
//			
//			while(resultSet.next()){
//				totalBalanceOnTheLastDate = new BigDecimal(resultSet.getDouble("total_balance"));
//			}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		return totalBalanceOnTheLastDate;
//	}
//	
//	public static BigDecimal useGetTheTotalBalanceOnTheLastDate(int portfolioNumber){
//		return getTheTotalBalanceOnTheLastDate(portfolioNumber);
//	}
	
	private static void insertDeposit(int portfolioNumber, BigDecimal amount){
		
		try {
			query = "INSERT INTO deposit(port_number, amount) " + "VALUES (?, ?)";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, portfolioNumber);
			preparedStatement.setBigDecimal(2, amount);
			
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void useInsertDeposit(int portfolioNumber, BigDecimal amount){
		insertDeposit(portfolioNumber, amount);
	}
	
	private static void insertWithdraw(int portfolioNumber, BigDecimal amount){
		
		try {
			query = "INSERT INTO withdraw(port_number, amount) " + "VALUES (?, ?)";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, portfolioNumber);
			preparedStatement.setBigDecimal(2, amount);
			
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void useInsertWithdraw(int portfolioNumber, BigDecimal amount){
		insertWithdraw(portfolioNumber, amount);
	}
}
