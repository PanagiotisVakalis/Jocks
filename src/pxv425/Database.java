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
	 * Database url, database username and database password will be final
	 * static since they will not be changed and they will be part of the whole
	 * class
	 */
	private static final String DATABASEURL = "jdbc:postgresql://dbteach2.cs.bham.ac.uk:5432/jocks_db";
	private static final String USERNAME = "pxv425";
	private static final String PASSWORD = "electro_pa";
	// Instance variable to establish connection
	private static Connection connection;
	// Instance variable to store the result
	private static ResultSet resultSet;
	// Instance variable to keep track of database connection status
	private static boolean connectedToDatabase = false;
	// Instance variable which will be used for queries
	private static String query;
	/*
	 * PreparedStatement is used instead of Statement because using the former
	 * the same query can be executed repeatedly using different parameter
	 * values
	 */
	private static PreparedStatement preparedStatement;
	/*
	 * Variable which will store the result of the signIn method. This will be
	 * either an Investor or a String.
	 */
	private static Object investorResult;
	// Variable where the security question will be stored
	private static String securityQuestionResult;
	// Variable where the security answer will be stored
	private static String securityAnswerResult;
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

	private static Timestamp lotDate;

	// Buys
	private static Buy buy;
	private static ArrayList<Buy> buysList = new ArrayList<>();
	private static String buysStockSymbol;
	private static Timestamp buysDate;
	private static double buysPrice;
	private static int buysShares;
	private static double buysAmount;

	// Sells
	private static Sell sell;
	private static ArrayList<Sell> sellsList = new ArrayList<>();
	private static String sellsStockSymbol;
	private static Timestamp sellsDate;
	private static double sellsPrice;
	private static int sellsShares;
	private static double sellsAmount;
	private static double sellsProfitLoss;

	// Watchlist
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

	public static String getDatabaseurl() {
		return DATABASEURL;
	}

	public static String getUsername() {
		return USERNAME;
	}

	public static String getPassword() {
		return PASSWORD;
	}

	/**
	 * Method to use the connection instance variable outside of the class
	 * 
	 * @return connection
	 * 
	 * @author Panagiotis Vakalis
	 * @version 13-07-2015
	 */
	public static Connection getConnection() {
		return connection;
	}

	/**
	 * Method to connect to database
	 * 
	 * @throws SQLException
	 * 
	 * @author Panagiotis Vakalis
	 * @version 07-07-2015
	 */
	private static void connectToDatabase() throws SQLException {
		/*
		 * getConnection method is used to connect to database using the url,
		 * username and password
		 */
		connection = DriverManager.getConnection(DATABASEURL, USERNAME,
				PASSWORD);
		connectedToDatabase = true;
	}

	/**
	 * Method to connect to database
	 * 
	 * @throws SQLException
	 * 
	 * @author Panagiotis Vakalis
	 * @version 12-07-2015
	 */
	public static void useConnectToDatabase() throws SQLException {
		connectToDatabase();
	}

	private static void disconnectFromDatabase() throws SQLException {
		resultSet.close();
		preparedStatement.close();
		connection.close();
	}

	public static void useDisconnectFromDatabase() throws SQLException {
		disconnectFromDatabase();
	}

	/**
	 * Method to use the connectedToDatabase private field outside the class
	 * 
	 * @return true if it is connected
	 * @return false if it is not connected
	 * 
	 * @author Panagiotis Vakalis
	 * @version 12-07-2015
	 */
	public static boolean getConnectedToDatabase() {
		return connectedToDatabase;
	}

	/**
	 * Method to register a new investor by inserting this particular investor
	 * as a new entry into the investor table
	 * 
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
	private static void registerInvestor(String email, String password,
			String firstName, String lastName, String securityQuestion,
			String securityAnswer) {
		try {
			connectToDatabase();
			// Create sql query which will be used in order to insert a new
			// entry in the table
			query = "INSERT INTO investor (email, password, f_name, l_name, sec_question, sec_answer)"
					+ " VALUES (?, ?, ?, ?, ?, ?)";
			// Execute the prepared statement
			preparedStatement = connection.prepareStatement(query);

			// Set the parameter values
			preparedStatement.setString(1, email);
			preparedStatement.setString(2, password);
			preparedStatement.setString(3, firstName);
			preparedStatement.setString(4, lastName);
			preparedStatement.setString(5, securityQuestion);
			preparedStatement.setString(6, securityAnswer);

			// Execute SQL statement
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Investor has not inserted on the database");
		} finally {
			try {
				disconnectFromDatabase();
			} catch (SQLException e) {
				
			}
		}
	}

	/**
	 * Method to register a new investor by inserting this particular investor
	 * as a new entry into the investor table
	 * 
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
	public static void useRegisterInvestor(String email, String password,
			String firstName, String lastName, String securityQuestion,
			String securityAnswer) {
		registerInvestor(email, password, firstName, lastName,
				securityQuestion, securityAnswer);
	}

	/**
	 * Method to signIn an already registered investor
	 * 
	 * @param email
	 * @param password
	 * 
	 * @author Panagiotis Vakalis
	 * @version 15-07-2015
	 */
	private static void getInvestor(String email, String password) {
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
			// Call method in order to connect to database
			connectToDatabase();
			// Setup query
			query = "SELECT * FROM investor WHERE email = ?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, email);

			// Execute query
			resultSet = preparedStatement.executeQuery();

			// display each row
			while (resultSet.next()) {
				emailResult = resultSet.getString("email");
				passwordResult = resultSet.getString("password");
				idResult = resultSet.getInt("id");
				firstNameResult = resultSet.getString("f_name");
				lastNameResult = resultSet.getString("l_name");
				time = resultSet.getTimestamp("date_reg");
				dateRegisteredResult = new Date(time.getTime());
				securityQuestionResult = resultSet.getString("sec_question");
				securityAnswerResult = resultSet.getString("sec_answer");
			}

			if (checkEmail(email)) {
				/*
				 * If the email exists
				 */
				if (emailResult.equals(email)
						&& Password.USE_DECRYPT_PASSWORD(passwordResult)
								.equals(password)) {
					/*
					 * If email and the decrypted password which are stored in
					 * the database match the email and password which the user
					 * entered, then an investor object is created.
					 */
					investorResult = new Investor(idResult, emailResult,
							passwordResult, firstNameResult, lastNameResult,
							dateRegisteredResult, securityQuestionResult,
							securityAnswerResult);
				} else {
					/*
					 * Otherwise a String object is created
					 */
					investorResult = new String("unsuccesful");
				}
			}
		} catch (SQLException e) {
			System.out.println("Investor has not retrieved from the database");
		} finally {
			try {
				disconnectFromDatabase();
			} catch (SQLException e) {
			
			}
		}
	}

	/**
	 * Method to signIn an already registered investor
	 * 
	 * @param email
	 * @param password
	 * @return investor object if the sign in is succesfull
	 * @return string object if the sing in is unsuccessfull
	 * 
	 * @author Panagiotis Vakalis
	 * @version 15-07-2015
	 */
	public static Object useGetInvestor(String email, String password) {
		/*
		 * Call this method in order to change the signInResult value
		 */
		getInvestor(email, password);
		return investorResult;
	}

	/**
	 * Method which checks whether the email is used
	 * 
	 * @param email
	 * @return true if the email is used
	 * @return false, if the email is not used
	 * 
	 * @author Panagiotis Vakalis
	 * @version 07-07-2015
	 */
	private static boolean checkEmail(String email) {
		boolean found = false;
		String emailResult = null;

		try {
			connectToDatabase();
			query = "SELECT email, password FROM investor WHERE email = ?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, email);

			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				emailResult = resultSet.getString("email");
			}
			if (emailResult != null) {
				found = true;
			} else {
				found = false;
			}

		} catch (SQLException e) {
			System.out.println("Email has not been checked");
		} finally {
			try {
				disconnectFromDatabase();
			} catch (SQLException e) {
				
			}
		}
		return found;
	}

	/**
	 * Method which checks whether the email is used
	 * 
	 * @param email
	 * @return true if the email exists
	 * @return false if the email does not exist
	 * 
	 * @author Panagiotis Vakalis
	 * @version 07-07-2015
	 */
	public static boolean useCheckEmail(String email) {
		return checkEmail(email);
	}

	/**
	 * Method to check whether the portfolio name already exists
	 * 
	 * @param email
	 * @param portfolioName
	 * @return true if portfolio name already exists
	 * @return false if portfolio name does not exist
	 * 
	 * @author Panagiotis Vakalis
	 * @version 11-07-2015
	 */
	private static boolean portfolioNameExists(String email,
			String portfolioName) {
		String name = null;

		try {
			connectToDatabase();
			query = "SELECT name FROM portfolio WHERE inv_id = (SELECT id FROM investor WHERE email = ?)";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, email);

			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				name = resultSet.getString("name");
				if (name.equals(portfolioName)) {
					return true;
				} else {
					return false;
				}
			}
		} catch (SQLException e) {
			System.out.println("Portfolio name has not been checked");
		} finally {
			try {
				disconnectFromDatabase();
			} catch (SQLException e) {
				
			}
		}
		return false;
	}

	/**
	 * Method to check whether the portfolio name already exists
	 * 
	 * @param email
	 * @param portfolioName
	 * @return true if portfolio name already exists
	 * @return false if portfolio name does not exist
	 * 
	 * @author Panagiotis Vakalis
	 * @version 11-07-2015
	 */
	public static boolean usePortfolioNameExists(String email,
			String portfolioName) {
		return portfolioNameExists(email, portfolioName);
	}

	/**
	 * Method which resets the password
	 * 
	 * @param email
	 * @param newPassword
	 * @author Panagiotis Vakalis
	 * @version 08-07-2015
	 */
	private static void changeInvestorPassword(String email, String newPassword) {
		try {
			connectToDatabase();
			query = "UPDATE investor " + "SET password = ? WHERE email = ?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1,
					Password.USE_ENCRYPT_PASSWORD(newPassword));
			preparedStatement.setString(2, email);

			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Password has not been changed");
		} finally {
			try {
				disconnectFromDatabase();
			} catch (SQLException e) {
				
			}
		}
	}

	/**
	 * Method to use resetInvestorPassword method outside the class
	 * 
	 * @param email
	 * @param newPassword
	 * 
	 * @author Panagiotis Vakalis
	 * @version 08-07-2015
	 */
	public static void useChangeInvestorPassword(String email,
			String newPassword) {
		changeInvestorPassword(email, newPassword);
	}

	/**
	 * Method to get the security question
	 * 
	 * @param email
	 * 
	 * @author Panagiotis Vakalis
	 * @version 17-07-2015
	 */
	private static void getSecurityQuestion(String email) {
		try {
			connectToDatabase();
			query = "SELECT sec_question FROM investor WHERE email = ?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, email);

			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				securityQuestionResult = resultSet.getString("sec_question");
			}
		} catch (SQLException e) {
			System.out.println("Security question has not been retrieved");
		} finally {
			try {
				disconnectFromDatabase();
			} catch (SQLException e) {
				
			}
		}
	}

	/**
	 * Method to get the security question for a specific email address
	 * 
	 * @param email
	 * @return security question
	 * 
	 * @author Panagiotis Vakalis
	 * @version 17-07-2015
	 */
	public static String useGetSecurityQuestion(String email) {
		getSecurityQuestion(email);
		return securityQuestionResult;
	}

	/**
	 * Method to retrieve the security answer
	 * 
	 * @param email
	 * 
	 * @author Panagiotis Vakalis
	 * @version 17-07-2015
	 */
	private static void getSecurityAnswer(String email) {
		try {
			connectToDatabase();
			query = "SELECT sec_answer FROM investor WHERE email = ?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, email);

			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				securityAnswerResult = resultSet.getString("sec_answer");
			}
		} catch (SQLException e) {
			System.out.println("Security answer has not been retrieved");
		} finally {
			try {
				disconnectFromDatabase();
			} catch (SQLException e) {
				
			}
		}
	}

	/**
	 * Method to retrieve the security answer
	 * 
	 * @param email
	 * @return secutity answer
	 * 
	 * @author Panagiotis Vakalis
	 * @version 17-07-2015
	 */
	public static String useGetSecurityAnswer(String email) {
		getSecurityAnswer(email);
		return securityAnswerResult;
	}

	/**
	 * Method to create a new portfolio when a new user is registered
	 * 
	 * @param name
	 *            of the portfolio
	 * @param email
	 *            of the investor
	 * @param initial
	 *            balance
	 * 
	 * @author Panagiotis Vakalis
	 * @version 08-07-2015
	 */
	private static void createPortfolio(String name, String email,
			BigDecimal balance) {
		try {

			if (!portfolioNameExists(email, name)) {
				// Connect to database
				connectToDatabase();
				/*
				 * If portfolio name does not exist in the list of the
				 * portfolios which this particullar user owns
				 */
				// Query
				query = "INSERT INTO portfolio (name, inv_id, balance, initial_balance) "
						+ "VALUES (?, (SELECT id FROM investor WHERE email = ?), ?, ?)";
				preparedStatement = connection.prepareStatement(query);
				preparedStatement.setString(1, name);
				preparedStatement.setString(2, email);
				preparedStatement.setBigDecimal(3, balance);
				preparedStatement.setBigDecimal(4, balance);

				preparedStatement.executeUpdate();
			}
		} catch (SQLException e) {
			System.out.println("Portfolio has not been created");
		} finally {
			try {
				disconnectFromDatabase();
			} catch (SQLException e) {
				
			}
		}
	}

	/**
	 * Method to create a new portfolio when a new user is registered
	 * 
	 * @param name
	 * @param email
	 * @param balance
	 * 
	 * @author Panagiotis Vakalis
	 * @version 08-07-2015
	 */
	public static void useCreatePortfolio(String name, String email,
			BigDecimal balance) {
		createPortfolio(name, email, balance);
	}

	/**
	 * Method to create a new portfolio for the already registered users
	 * 
	 * @param email
	 * @param name
	 * @param balance
	 * 
	 * @author Panagiotis Vakalis
	 * @version 28-07-2015
	 */
	private static void createNewPortfolio(String email, String name,
			double balance) {
		/*
		 * This method is used to create a new portfolio for the already
		 * registered investors, using their id.
		 */
		if (!portfolioNameExists(email, name)) {
			try {
				connectToDatabase();

				query = "INSERT INTO portfolio (name, inv_id, balance, initial_balance) "
						+ "VALUES (?, (SELECT id FROM investor WHERE email = ?), ?, ?)";

				preparedStatement = connection.prepareStatement(query);
				preparedStatement.setString(1, name);
				preparedStatement.setString(2, email);
				preparedStatement.setDouble(3, balance);
				preparedStatement.setDouble(4, balance);

				preparedStatement.executeUpdate();
			} catch (SQLException e) {
				System.out.println("Portfolio has not been created");
			} finally {
				try {
					disconnectFromDatabase();
				} catch (SQLException e) {
					
				}
			}
		}
	}

	/**
	 * Method to create a new portfolio for the already registered users
	 * 
	 * @param email
	 * @param name
	 * @param balance
	 * 
	 * @author Panagiotis Vakalis
	 * @version 28-07-2015
	 */
	public static void useCreateNewPortfolio(String email, String name,
			double balance) {
		createNewPortfolio(email, name, balance);
	}

	/**
	 * Method to update the balance of portfolio
	 * 
	 * @param portfolioNumber
	 * @param amount
	 * 
	 * @author Panagiotis Vakalis
	 * @version 08-07-2015
	 */
	private static void updatePortfolioBalance(int portfolioNumber,
			BigDecimal amount) {
		try {
			// Connect to database
			connectToDatabase();
			// Query
			query = "UPDATE portfolio "
					+ "SET balance = (SELECT balance FROM portfolio WHERE number = ?) + ? WHERE number = ?";

			preparedStatement = connection.prepareStatement(query);

			preparedStatement.setInt(1, portfolioNumber);
			preparedStatement.setBigDecimal(2, amount);
			preparedStatement.setInt(3, portfolioNumber);

			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Portfolio's balance has not been updated");
		} finally {
			try {
				disconnectFromDatabase();
			} catch (SQLException e) {
				
			}
		}
	}

	public static void useUpdatePortfolioBalance(int portfolioNumber,
			BigDecimal amount) {
		updatePortfolioBalance(portfolioNumber, amount);
	}

	/**
	 * Method to update the invested money of a portfolio
	 * 
	 * @param portfolioNumber
	 * @param amount
	 * 
	 * @author Panagiotis Vakalis
	 * @version 08-07-2015
	 */
	private static void updatePortfolioInvestedMoney(int portfolioNumber,
			double amount) {
		try {
			connectToDatabase();
			query = "UPDATE portfolio "
					+ "SET inv_money = (SELECT inv_money FROM portfolio WHERE number = ?) + ? WHERE number = ?";

			preparedStatement = connection.prepareStatement(query);

			preparedStatement.setInt(1, portfolioNumber);
			preparedStatement.setDouble(2, amount);
			preparedStatement.setInt(3, portfolioNumber);

			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Portfolio's invested money has not been updated");
		} finally {
			try {
				disconnectFromDatabase();
			} catch (SQLException e) {
				
			}
		}
	}

	/**
	 * Method to update the invested money of a portfolio
	 * 
	 * @param portfolioNumber
	 * @param amount
	 * 
	 * @author Panagiotis Vakalis
	 * @version 08-07-2015
	 */
	public static void useUpdatePortfolioInvestedMoney(int portfolioNumber,
			double amount) {
		updatePortfolioInvestedMoney(portfolioNumber, amount);
	}

	/**
	 * Method to get the investor portfolios
	 * 
	 * @param id
	 * 
	 * @author Panagiotis Vakalis
	 * @version 18-07-2015
	 */
	private static void getInvestorPortfolio(Integer id) {
		portfoliosResult = new ArrayList<>();
		try {
			connectToDatabase();
			query = "SELECT * FROM portfolio WHERE inv_id = ?";

			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, id);

			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Integer portfolioNumber = resultSet.getInt("number");
				String portfolioName = resultSet.getString("name");
				BigDecimal investedMoney = resultSet.getBigDecimal("inv_money");
				BigDecimal balance = resultSet.getBigDecimal("balance");
				double profitLoss = resultSet.getDouble("profit_loss");

				Portfolio portfolio = new Portfolio(portfolioNumber,
						portfolioName, investedMoney, balance, profitLoss);
				portfoliosResult.add(portfolio);
			}
		} catch (SQLException e) {
			System.out.println("Portfolio has not been retrieved");
		} finally {
			try {
				disconnectFromDatabase();
			} catch (SQLException e) {
				
			}
		}
	}

	/**
	 * Method to get the investor portfolios
	 * 
	 * @param id
	 * @return portfolios
	 * 
	 * @author Panagiotis Vakalis
	 * @version 18-07-2015
	 */
	public static ArrayList<Portfolio> useGetInvestorPortfolioNumber(Integer id) {
		getInvestorPortfolio(id);
		return portfoliosResult;
	}

	/**
	 * Method which retrieves the portfolio details for a given investor
	 * 
	 * @param id
	 * 
	 * @author Panagiotis Vakalis
	 * @version 20-07-2015
	 */
	private static void getPortfoliosDetails(int id) {
		try {
			connectToDatabase();
			query = "SELECT COUNT(*), SUM(inv_money), SUM(balance), SUM(profit_loss) FROM portfolio WHERE inv_id = ?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, id);

			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				howManyPortfolios = resultSet.getInt(1);
				totalInvestedMoney = resultSet.getDouble(2);
				totalBalance = resultSet.getBigDecimal(3);
				totalProfitLoss = resultSet.getDouble(4);
			}
		} catch (SQLException e) {
			System.out.println("Portfolio's has not been retrieved");
		} finally {
			try {
				disconnectFromDatabase();
			} catch (SQLException e) {
				
			}
		}
	}

	/**
	 * Method which retrieves the portfolio details for a given investor
	 * 
	 * @param id
	 * 
	 * @author Panagiotis Vakalis
	 * @version 20-07-2015
	 */
	public static void useGetPortfoliosDetails(int id) {
		getPortfoliosDetails(id);
	}

	/**
	 * Method to get the howManyPortfolios instance variable outside the class
	 * 
	 * @return number of portfolios
	 * 
	 * @author Panagiotis Vakalis
	 * @version 20-07-2015
	 */
	public static int getHowManyPortfolios() {
		return howManyPortfolios;
	}

	/**
	 * Method which retrieves the portfolio balance for a given portfolio number
	 * 
	 * @param portfolioNumber
	 * 
	 * @author Panagiotis Vakalis
	 * @version 20-07-2015
	 */
	private static void retrievePortfolioBalance(int portfolioNumber) {

		try {
			connectToDatabase();
			query = "SELECT balance FROM portfolio WHERE number = ?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, portfolioNumber);

			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				portfolioBalance = resultSet.getBigDecimal("balance");
			}
		} catch (SQLException e) {
			System.out.println("Portfolio's balance has not been retrieved");
		} finally {
			try {
				disconnectFromDatabase();
			} catch (SQLException e) {
				
			}
		}
	}

	/**
	 * Method which retrieves the portfolio balance for a given portfolio number
	 * 
	 * @param portfolioNumber
	 * @return portfolioBalance
	 * 
	 * @author Panagiotis Vakalis
	 * @version 20-07-2015
	 */
	public static BigDecimal useRetrievePortfolioBalance(int portfolioNumber) {
		portfolioBalance = null;
		retrievePortfolioBalance(portfolioNumber);
		return portfolioBalance;
	}

	/**
	 * Method which retrieves the portfolio initial balance for a given
	 * portfolio number
	 * 
	 * @param portfolioNumber
	 * 
	 * @author Panagiotis Vakalis
	 * @version 20-07-2015
	 */
	private static void retrievePortfolioInitialBalance(int portfolioNumber) {

		try {
			connectToDatabase();
			query = "SELECT initial_balance FROM portfolio WHERE number = ?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, portfolioNumber);

			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				portfolioInitialBalance = resultSet
						.getBigDecimal("initial_balance");
			}
		} catch (SQLException e) {
			System.out.println("Portfolio's initial balance has not been retrieved");
		} finally {
			try {
				disconnectFromDatabase();
			} catch (SQLException e) {
				
			}
		}
	}

	/**
	 * Method which retrieves the portfolio initial balance for a given
	 * portfolio number
	 * 
	 * @param portfolioNumber
	 * @return portfolioNinitialBalance
	 * 
	 * @author Panagiotis Vakalis
	 * @version 20-07-2015
	 */
	public static BigDecimal useRetrievePortfolioInitialBalance(
			int portfolioNumber) {
		portfolioInitialBalance = null;
		retrievePortfolioInitialBalance(portfolioNumber);
		return portfolioInitialBalance;
	}

	/**
	 * Method which retrieves the portfolios total withdraws for a given
	 * portfolio number
	 * 
	 * @param portfolioNumber
	 * @return totalWithdraws
	 * 
	 * @author Panagiotis Vakalis
	 * @version 20-07-2015
	 */
	private static BigDecimal retrieveTotalWithdraws(int portfolioNumber) {
		BigDecimal totalWithdraws = null;

		try {
			connectToDatabase();
			query = "SELECT SUM(amount) AS total_amount FROM withdraw WHERE port_number = ?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, portfolioNumber);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				totalWithdraws = new BigDecimal(
						resultSet.getDouble("total_amount"));
			}
		} catch (SQLException e) {
			System.out.println("Portfolio's total withdraws has not been retrieved");
		} finally {
			try {
				disconnectFromDatabase();
			} catch (SQLException e) {
				
			}
		}

		return totalWithdraws;
	}

	/**
	 * Method which retrieves the portfolios total withdraws for a given
	 * portfolio number
	 * 
	 * @param portfolioNumber
	 * @return totalWithdraws
	 * 
	 * @author Panagiotis Vakalis
	 * @version 20-07-2015
	 */
	public static BigDecimal useRetrieveTotalWithdraws(int portfolioNumber) {
		return retrieveTotalWithdraws(portfolioNumber);
	}

	/**
	 * Method which retrieves the portfolios total deposits for a given
	 * portfolio number
	 * 
	 * @param portfolioNumber
	 * @return totalDeposits
	 * 
	 * @author Panagiotis Vakalis
	 * @version 20-07-2015
	 */
	private static BigDecimal retrieveTotalDeposits(int portfolioNumber) {
		BigDecimal totalDeposits = null;

		try {
			connectToDatabase();
			query = "SELECT SUM(amount) AS total_amount FROM deposit WHERE port_number = ?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, portfolioNumber);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				totalDeposits = new BigDecimal(
						resultSet.getDouble("total_amount"));
			}
		} catch (SQLException e) {
			System.out.println("Portfolio's total deposits has not been retrieved");
		} finally {
			try {
				disconnectFromDatabase();
			} catch (SQLException e) {
				
			}
		}

		return totalDeposits;
	}

	/**
	 * Method which retrieves the portfolios total withdraws for a given
	 * portfolio number
	 * 
	 * @param portfolioNumber
	 * @return totalDeposits
	 * 
	 * @author Panagiotis Vakalis
	 * @version 20-07-2015
	 */
	public static BigDecimal useRetrieveTotalDeposits(int portfolioNumber) {
		return retrieveTotalDeposits(portfolioNumber);
	}

	/**
	 * Method which retrieves the portfolios invested money for a given
	 * portfolio number
	 * 
	 * @param portfolioNumber
	 * @return investedMoney
	 * 
	 * @author Panagiotis Vakalis
	 * @version 20-07-2015
	 */
	private static BigDecimal retrieveInvestedMoney(int portfolioNumber) {
		totalInvestedMoney = 0;
		try {
			connectToDatabase();
			query = "SELECT inv_money FROM portfolio WHERE number = ?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, portfolioNumber);

			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				portfolioInvestedMoney = resultSet.getBigDecimal("inv_money");
			}
		} catch (SQLException e) {
			System.out.println("Portfolio's invested money has not been retrieved");
		} finally {
			try {
				disconnectFromDatabase();
			} catch (SQLException e) {
				
			}
		}

		return portfolioInvestedMoney;
	}

	/**
	 * Method which retrieves the portfolios invested money for a given
	 * portfolio number
	 * 
	 * @param portfolioNumber
	 * @return investedMoney
	 * 
	 * @author Panagiotis Vakalis
	 * @version 20-07-2015
	 */
	public static BigDecimal useRetrieveInvestedMoney(int portfolioNumber) {
		return retrieveInvestedMoney(portfolioNumber);
	}

	/**
	 * Method which retrieves the portfolios balance for a given portfolio
	 * number
	 * 
	 * @param portfolioNumber
	 * 
	 * @author Panagiotis Vakalis
	 * @version 20-07-2015
	 */
	private static void retrieveBalance(int portfolioNumber) {

		try {
			connectToDatabase();
			query = "SELECT balance FROM portfolio WHERE number = ?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, portfolioNumber);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				totalBalance = resultSet.getBigDecimal("balance");
			}
		} catch (SQLException e) {
			System.out.println("Portfolio's balance has not been retrieved");
		} finally {
			try {
				disconnectFromDatabase();
			} catch (SQLException e) {
				
			}
		}
	}

	/**
	 * Method which retrieves the portfolios invested money for a given
	 * portfolio number
	 * 
	 * @param portfolioNumber
	 * @return balance
	 * 
	 * @author Panagiotis Vakalis
	 * @version 20-07-2015
	 */
	public static BigDecimal getTotalBalance(int portfolioNumber) {
		retrieveBalance(portfolioNumber);
		return totalBalance.setScale(2, BigDecimal.ROUND_DOWN);
	}

	/**
	 * Method to get the total profit / loss outside the class
	 * 
	 * @return total profit / loss
	 * 
	 * @author Panagiotis Vakalis
	 * @version 20-07-2015
	 */
	public static double getTotalProfitLoss() {
		return totalProfitLoss;
	}

	/**
	 * Method to insert a stock in the database
	 * 
	 * @param symbol
	 * @param name
	 * @param industry
	 * @param tradePrice
	 * 
	 * @author Panagiotis Vakalis
	 * @version 08-07-2015
	 */
	private static void insertStock(String symbol, String name,
			String industry, double trade_price) {
		try {
			connectToDatabase();
			query = "INSERT INTO stock (symbol, name, industry, trade_price) "
					+ "VALUES (?, ?, ?, ?)";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, symbol);
			preparedStatement.setString(2, name);
			preparedStatement.setString(3, industry);
			preparedStatement.setDouble(4, trade_price);

			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Stock has not been inserted");
		} finally {
			try {
				disconnectFromDatabase();
			} catch (SQLException e) {
				
			}
		}
	}

	/**
	 * Method to insert a stock in the database
	 * 
	 * @param symbol
	 * @param name
	 * @param industry
	 * @param tradePrice
	 * 
	 * @author Panagiotis Vakalis
	 * @version 08-07-2015
	 */
	public static void useInsertStock(String symbol, String name,
			String industry, double trade_price) {
		insertStock(symbol, name, industry, trade_price);
	}

	/**
	 * Method to update the stock's price
	 * 
	 * @param symbol
	 * @param currentPrice
	 * 
	 * @author Panagiotis Vakalis
	 * @version 08-07-2015
	 */
	private static void updateStockPrice(String symbol, double trade_price) {
		try {
			connectToDatabase();
			/*
			 * The price which is inserted is first converted to pounds and is
			 * rounded using 4 decimals
			 */
			query = "UPDATE stock "
					+ "SET trade_price = ROUND (CAST(? AS numeric) / 100, 4) WHERE symbol = ?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setDouble(1, trade_price);
			preparedStatement.setString(2, symbol);

			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Stock's price has not been updated");
		} finally {
			try {
				disconnectFromDatabase();
			} catch (SQLException e) {
				
			}
		}
	}

	/**
	 * Method to update the stock's price
	 * 
	 * @param symbol
	 * @param currentPrice
	 * 
	 * @author Panagiotis Vakalis
	 * @version 08-07-2015
	 */
	public static void useUpdateStockPrice(String symbol, double trade_price) {
		updateStockPrice(symbol, trade_price);
	}

	/**
	 * Method to insert the stocks' prices into the stock_price table
	 * 
	 * @param stockSymbol
	 * @param openPrice
	 * @param closePrice
	 * @param date
	 * 
	 * @author Panagiotis Vakalis
	 * @version 14-07-2015
	 */
	private static void insertStockPrice(String stockSymbol, double openPrice,
			double closePrice, String date, double adjustedClosePrice) {
		try {
			connectToDatabase();
			query = "INSERT INTO stock_price (stock_symbol, open_price, close_price, profit_loss, date, adjusted_close_price) "
					+ "VALUES(?, ?, ?, ?, ?, ?)";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, stockSymbol);
			preparedStatement.setDouble(2, openPrice);
			preparedStatement.setDouble(3, closePrice);
			preparedStatement.setDouble(4, closePrice - openPrice);
			preparedStatement.setString(5, date);
			preparedStatement.setDouble(6, adjustedClosePrice);

			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Stock's price has not been inserted");
		} finally {
			try {
				disconnectFromDatabase();
			} catch (SQLException e) {
				
			}
		}
	}

	/**
	 * Method to insert the stocks' prices into the stock_price table
	 * 
	 * @param stockSymbol
	 * @param openPrice
	 * @param closePrice
	 * @param date
	 * 
	 * @author Panagiotis Vakalis
	 * @version 14-07-2015
	 */
	public static void useInsertStockPrice(String stockSymbol,
			double openPrice, double closePrice, String date,
			double adjustedClosePrice) {
		insertStockPrice(stockSymbol, openPrice, closePrice, date,
				adjustedClosePrice);
	}

	/**
	 * Method to get the last date for which price is stored in the database
	 * 
	 * @param stockSymbol
	 * 
	 * @author Panagiotis Vakalis
	 * @version 14-07-2015
	 */
	private static void getLastUpdatedDate(String stockSymbol) {

		try {
			connectToDatabase();
			query = "SELECT MAX(date) AS last_updated FROM stock_price WHERE stock_symbol = ?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, stockSymbol);

			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				lastUpdatedOn = resultSet.getTimestamp("last_updated");
			}
		} catch (SQLException e) {
			System.out.println("Last date from update has not been retrieved");
		} finally {
			try {
				disconnectFromDatabase();
			} catch (SQLException e) {
				
			}
		}
	}

	/**
	 * Method to get the last date for which price is stored in the database
	 * 
	 * @param stockSymbol
	 * @return last date
	 * 
	 * @author Panagiotis Vakalis
	 * @version 14-07-2015
	 */
	public static Timestamp useGetLastUpdatedDate(String stockSymbol) {
		getLastUpdatedDate(stockSymbol);
		return lastUpdatedOn;
	}

	/**
	 * Method to get all the stocks
	 * 
	 * @author Panagiotis Vakalis
	 * @version 20-07-2015
	 */
	private static void getAllStocks() {
		try {
			connectToDatabase();

			query = "SELECT * FROM stock ORDER BY symbol";
			preparedStatement = connection.prepareStatement(query);

			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				stockSymbol = resultSet.getString("symbol");
				stockName = resultSet.getString("name");
				stockIndustry = resultSet.getString("industry");
				stockPrice = resultSet.getDouble("trade_price");

				stock = new Stock(stockSymbol, stockName, stockIndustry,
						stockPrice);
				stocksList.add(stock);
			}
		} catch (SQLException e) {
			System.out.println("Stocks have not been retrieved");
		} finally {
			try {
				disconnectFromDatabase();
			} catch (SQLException e) {
				
			}
		}
	}

	/**
	 * Method to get the name of the stock
	 * 
	 * @param stockSymbol
	 * 
	 * @author Panagiotis Vakalis
	 * @version 14-07-2015
	 */
	private static void getStockNameUsingSymbol(String stockSymbol) {
		try {
			connectToDatabase();

			query = "SELECT name FROM stock WHERE symbol = ?";

			preparedStatement = connection.prepareStatement(query);

			preparedStatement.setString(1, stockSymbol);

			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				stockName = resultSet.getString("name");
			}
		} catch (SQLException e) {
			System.out.println("Stock's name has not been retrieved");
		} finally {
			try {
				disconnectFromDatabase();
			} catch (SQLException e) {
				
			}
		}
	}

	/**
	 * Method to get the name of the stock
	 * 
	 * @param stockSymbol
	 * @return name of the stock
	 * 
	 * @author Panagiotis Vakalis
	 * @version 14-07-2015
	 */
	public static String useGetStockNameUsingSymbol(String stockSymbol) {
		getStockNameUsingSymbol(stockSymbol);
		return stockName;
	}

	/**
	 * Method to get all the stocks outside the class
	 * 
	 * @return all the stocks
	 * 
	 * @author Panagiotis Vakalis
	 * @version 20-07-2015
	 */
	public static ArrayList<Stock> useGetAllStocks() {
		getAllStocks();
		return stocksList;
	}

	/**
	 * Method to insert a watch entry (watchlist)
	 * 
	 * @param email
	 * @param stockSymbol
	 * 
	 * @author Panagiotis Vakalis
	 * @version 08-07-2015
	 */
	private static void insertWatch(int investorId, String stockSymbol) {
		try {
			connectToDatabase();
			query = "INSERT INTO watches (inv_id, st_symbol) "
					+ "VALUES (?, ?)";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, investorId);
			preparedStatement.setString(2, stockSymbol);

			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Watch has not been inserted");
		} finally {
			try {
				disconnectFromDatabase();
			} catch (SQLException e) {
				
			}
		}
	}

	/**
	 * Method to insert a watch entry (watchlist)
	 * 
	 * @param email
	 * @param stockSymbol
	 * 
	 * @author Panagiotis Vakalis
	 * @version 08-07-2015
	 */
	public static void useInsertWatch(int investorId, String stockSymbol) {
		insertWatch(investorId, stockSymbol);
	}

	/**
	 * Method to delete a watch entry
	 * 
	 * @param email
	 * @param stockSymbol
	 * 
	 * @author Panagiotis Vakalis
	 * @version 08-07-2015
	 */
	private static void deleteWatch(int id, String stockSymbol) {
		try {
			connectToDatabase();
			query = "DELETE FROM watches WHERE inv_id = ? AND st_symbol = ?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, id);
			preparedStatement.setString(2, stockSymbol);

			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Watch has not been deleted");
		} finally {
			try {
				disconnectFromDatabase();
			} catch (SQLException e) {
				
			}
		}
	}

	/**
	 * Method to delete a watch entry
	 * 
	 * @param email
	 * @param stockSymbol
	 * 
	 * @author Panagiotis Vakalis
	 * @version 08-07-2015
	 */
	public static void useDeleteWatch(int id, String stockSymbol) {
		deleteWatch(id, stockSymbol);
	}

	/**
	 * Method to retrieve the watchlist which a user has
	 * 
	 * @param investorId
	 * 
	 * @author Panagiotis Vakalis
	 * @version 26-07-2015
	 */
	private static void retrieveWatches(int investorId) {
		watchlistStockSymbols = new ArrayList<>();
		try {
			connectToDatabase();

			query = "SELECT st_symbol FROM watches WHERE inv_id = ?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, investorId);

			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				watchlistStockSymbols.add(resultSet.getString("st_symbol"));
			}
		} catch (SQLException e) {
			System.out.println("Watchlist has not been retrieved");
		} finally {
			try {
				disconnectFromDatabase();
			} catch (SQLException e) {
				
			}
		}
	}

	/**
	 * Method to retrieve the informations for stocks which an investor is
	 * interested in
	 * 
	 * @param investorId
	 * 
	 * @author Panagiotis Vakalis
	 * @version 26-07-2015
	 */
	private static void retrieveStocksWhichInvestorWatches(int investorId) {
		try {

			retrieveWatches(investorId);
			connectToDatabase();
			for (int i = 0; i < watchlistStockSymbols.size(); i++) {
				query = "SELECT * FROM stock WHERE symbol = ?";

				preparedStatement = connection.prepareStatement(query);

				// Get all the stock symbols from the arraylist
				preparedStatement.setString(1, watchlistStockSymbols.get(i));

				resultSet = preparedStatement.executeQuery();

				while (resultSet.next()) {
					stockSymbol = resultSet.getString("symbol");
					stockName = resultSet.getString("name");
					stockIndustry = resultSet.getString("industry");
					stockPrice = resultSet.getDouble("trade_price");

					stock = new Stock(stockSymbol, stockName, stockIndustry,
							stockPrice);

					stocksWatchlist.add(stock);
				}
			}
		} catch (SQLException e) {
			System.out.println("Watchlist has not been retrieved");
		} finally {
			try {
				disconnectFromDatabase();
			} catch (SQLException e) {
				
			}
		}
	}

	/**
	 * Method to retrieve the informations for stocks which an investor is
	 * interested in
	 * 
	 * @param investorId
	 * @return watchlist of the investor
	 * 
	 * @author Panagiotis Vakalis
	 * @version 26-07-2015
	 */
	public static ArrayList<Stock> useRetrieveStocksWhichInvestorWatches(
			int investorId) {
		stocksWatchlist = new ArrayList<>();
		retrieveStocksWhichInvestorWatches(investorId);
		return stocksWatchlist;
	}

	/**
	 * Method to insert a lot in database
	 * 
	 * @param email
	 * @param stockSymbol
	 * @param shares
	 * 
	 * @author Panagiotis Vakalis
	 * @version 08-07-2015
	 */
	private static void insertLot(int portfolioNumber, String stockSymbol,
			int shares) {
		try {
			connectToDatabase();
			query = "INSERT INTO lot (port_number, st_symbol, b_price, b_shares, amount, cur_amount) "
					+ "VALUES (?, ?, (SELECT trade_price FROM stock WHERE symbol = ?), ?, ((SELECT trade_price FROM stock WHERE symbol = ?) * ?), ((SELECT trade_price FROM stock WHERE symbol = ?) * ?))";
			preparedStatement = connection.prepareStatement(query);

			preparedStatement.setInt(1, portfolioNumber);
			preparedStatement.setString(2, stockSymbol);
			preparedStatement.setString(3, stockSymbol);
			preparedStatement.setInt(4, shares);
			preparedStatement.setString(5, stockSymbol);
			preparedStatement.setInt(6, shares);
			preparedStatement.setString(7, stockSymbol);
			preparedStatement.setInt(8, shares);

			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Lot has not been inserted");
		} finally {
			try {
				disconnectFromDatabase();
			} catch (SQLException e) {

			}
		}
	}

	/**
	 * Method to insert a lot in database
	 * 
	 * @param email
	 * @param stockSymbol
	 * @param shares
	 * 
	 * @author Panagiotis Vakalis
	 * @version 08-07-2015
	 */
	public static void useInsertLot(int portfolioNumber, String stockSymbol,
			int shares) {
		insertLot(portfolioNumber, stockSymbol, shares);
	}

	/**
	 * Method to update all the lots from a portfolio with the current prices
	 * 
	 * @param portfolioNumber
	 * 
	 * @author Panagiotis Vakalis
	 * @version 08-07-2015
	 */
	private static void updateLotsWithCurrentPrice(int portfolioNumber) {
		// Retrieve stock symbols which this portfolio has
		String queryRetrieveStocks;
		Connection stocksConnection = null;
		PreparedStatement retrieveStocksStatement = null;
		ResultSet stocks = null;
		String stock;

		try {
			stocksConnection = DriverManager.getConnection(DATABASEURL,
					USERNAME, PASSWORD);
			queryRetrieveStocks = "SELECT st_symbol FROM lot WHERE port_number = ?";
			retrieveStocksStatement = stocksConnection
					.prepareStatement(queryRetrieveStocks);
			retrieveStocksStatement.setInt(1, portfolioNumber);

			stocks = retrieveStocksStatement.executeQuery();

			while (stocks.next()) {
				connectToDatabase();
				// Update prices into lots table
				stock = stocks.getString("st_symbol");
				query = "UPDATE lot SET cur_amount = ? * ? WHERE st_symbol = ? AND port_number = ?";
				preparedStatement = connection.prepareStatement(query);
				preparedStatement.setDouble(1, retrieveStockTradePrice(stock));
				preparedStatement.setInt(2,
						retrieveBoughtSharesForAStock(stock, portfolioNumber));
				preparedStatement.setString(3, stock);
				preparedStatement.setInt(4, portfolioNumber);

				preparedStatement.executeUpdate();

				query = new String();
			}
		} catch (SQLException e) {
			System.out.println("Lot has not been updated with the current price");
		} finally {
			try {
				stocks.close();
				retrieveStocksStatement.close();
				stocksConnection.close();
				disconnectFromDatabase();
			} catch (SQLException e) {
				
			}
		}
	}

	/**
	 * Method to update all the lots from a portfolio with the current prices
	 * 
	 * @param portfolioNumber
	 * 
	 * @author Panagiotis Vakalis
	 * @version 08-07-2015
	 */
	public static void useUpdateLotsWithCurrentPrice(int portfolioNumber) {
		updateLotsWithCurrentPrice(portfolioNumber);
	}

	/**
	 * Method to get the stock's trade price
	 * 
	 * @param stockSymbol
	 * @return trade price
	 * 
	 * @author Panagiotis Vakalis
	 * @version 08-07-2015
	 */
	private static double retrieveStockTradePrice(String stockSymbol) {
		String queryRetrieveTradePrice;
		Connection tradePriceConnection = null;
		PreparedStatement retrieveTradePriceStatement = null;
		ResultSet tradePrice = null;
		double price = 0;

		try {
			tradePriceConnection = DriverManager.getConnection(DATABASEURL,
					USERNAME, PASSWORD);
			queryRetrieveTradePrice = "SELECT trade_price FROM stock WHERE symbol = ?";
			retrieveTradePriceStatement = tradePriceConnection
					.prepareStatement(queryRetrieveTradePrice);
			retrieveTradePriceStatement.setString(1, stockSymbol);

			tradePrice = retrieveTradePriceStatement.executeQuery();

			while (tradePrice.next()) {
				price = tradePrice.getDouble("trade_price");
			}
		} catch (SQLException e) {
			System.out.println("Stock's trade price has not been retrieved");
		} finally {
			try {
				tradePrice.close();
				retrieveTradePriceStatement.close();
				tradePriceConnection.close();
			} catch (SQLException e) {
				
			}
		}

		return price;
	}

	/**
	 * Retrieve the amount of bought shares
	 * 
	 * @param stockSymbol
	 * @param portfolioNumber
	 * @return amount of bought shares
	 * 
	 * @author Panagiotis Vakalis
	 * @version 08-07-2015
	 */
	private static int retrieveBoughtSharesForAStock(String stockSymbol,
			int portfolioNumber) {
		String queryRetrieveShares;
		Connection sharesConnection = null;
		PreparedStatement retrieveSharesStatement = null;
		ResultSet sharesSet = null;
		int shares = 0;

		try {
			sharesConnection = DriverManager.getConnection(DATABASEURL,
					USERNAME, PASSWORD);
			queryRetrieveShares = "SELECT b_shares FROM lot WHERE st_symbol = ? AND port_number = ?";
			retrieveSharesStatement = sharesConnection
					.prepareStatement(queryRetrieveShares);
			retrieveSharesStatement.setString(1, stockSymbol);
			retrieveSharesStatement.setInt(2, portfolioNumber);

			sharesSet = retrieveSharesStatement.executeQuery();

			while (sharesSet.next()) {
				shares = sharesSet.getInt("b_shares");
			}
		} catch (SQLException e) {
			System.out.println("Bought shares have ot been retrived");
		} finally {
			try {
				sharesSet.close();
				retrieveSharesStatement.close();
				sharesConnection.close();
			} catch (SQLException e) {
				
			}
		}

		return shares;
	}

	/**
	 * Method to get the lots for a specific portfolio. The current price is
	 * retrieved as well
	 * 
	 * @param portfolioNumber
	 * 
	 * @author Panagiotis Vakalis
	 * @version 23-07-2015
	 */
	private static void getLotsForASpecificPortfolio(int portfolioNumber) {

		// local variables which will be used in order to get the current price
		// for each stock
		String queryFindCurrentPrice;
		Connection currentPriceConnection = null;
		PreparedStatement preparedStatementForCurrentPrice = null;
		ResultSet currentPriceResult = null;

		try {
			connectToDatabase();
			query = "SELECT * FROM lot WHERE port_number = ? ORDER BY st_symbol";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, portfolioNumber);

			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				lotStockSymbol = resultSet.getString("st_symbol");
				lotBoughtPrice = resultSet.getDouble("b_price");
				lotBoughtShares = resultSet.getInt("b_shares");
				lotAmount = resultSet.getDouble("amount");
				lotDate = resultSet.getTimestamp("date");
				/*
				 * Get the current price for this specific stock symbol
				 */
				currentPriceConnection = DriverManager.getConnection(
						DATABASEURL, USERNAME, PASSWORD);
				queryFindCurrentPrice = "SELECT trade_price FROM stock WHERE symbol = ?";
				preparedStatementForCurrentPrice = currentPriceConnection
						.prepareStatement(queryFindCurrentPrice);
				preparedStatementForCurrentPrice.setString(1, lotStockSymbol);

				currentPriceResult = preparedStatementForCurrentPrice
						.executeQuery();
				while (currentPriceResult.next()) {
					currentPrice = currentPriceResult.getDouble("trade_price");
				}

				/*
				 * Create a new lot using the previous variables
				 */
				lot = new Lot(portfolioNumber, lotStockSymbol, lotBoughtPrice,
						lotBoughtShares, lotAmount, currentPrice, currentPrice
								* lotBoughtShares, lotDate);

				lotsList.add(lot);

			}
		} catch (SQLException e) {
			System.out.println("Lots have not been retrieved");
		} finally {
			try {
				if (currentPriceResult != null
						&& preparedStatementForCurrentPrice != null
						&& currentPriceConnection != null) {
					currentPriceResult.close();
					preparedStatementForCurrentPrice.close();
					currentPriceConnection.close();
				}
				disconnectFromDatabase();
			} catch (SQLException e) {
				
			}
		}

	}

	/**
	 * Method to get the lots for a specific portfolio. The current price is
	 * retrieved as well
	 * 
	 * @param portfolioNumber
	 * @return arraylist of lots
	 * 
	 * @author Panagiotis Vakalis
	 * @version 23-07-2015
	 */
	public static ArrayList<Lot> useGetLotsForASpecificPortfolio(
			int portfolioNumber) {
		lotsList = new ArrayList<>();
		getLotsForASpecificPortfolio(portfolioNumber);
		return lotsList;
	}

	/**
	 * Method to delete a lot from database
	 * 
	 * @param email
	 * @param stockSymbol
	 * @param date
	 * 
	 * @author Panagiotis Vakalis
	 * @version 08-07-2015
	 */
	private static void deleteLot(int portfolioNumber, String stockSymbol,
			Timestamp date) {
		if (lotExists(portfolioNumber, stockSymbol)) {

			try {
				connectToDatabase();
				query = "DELETE FROM lot WHERE port_number = ? AND st_symbol = ? AND date = ?";
				preparedStatement = connection.prepareStatement(query);
				preparedStatement.setInt(1, portfolioNumber);
				preparedStatement.setString(2, stockSymbol);
				preparedStatement.setTimestamp(3, date);

				preparedStatement.executeUpdate();
			} catch (SQLException e) {
				System.out.println("Lot has not been deleted");
			} finally {
				try {
					disconnectFromDatabase();
				} catch (SQLException e) {
					
				}
			}

		}
	}

	/**
	 * Method to delete a lot from database
	 * 
	 * @param email
	 * @param stockSymbol
	 * @param date
	 * 
	 * @author Panagiotis Vakalis
	 * @version 08-07-2015
	 */
	public static void useDeleteLot(int portfolioNumber, String stockSymbol,
			Timestamp date) {
		deleteLot(portfolioNumber, stockSymbol, date);
	}

	/**
	 * Method to check whether or not a lot exists
	 * 
	 * @param portfolioNumber
	 * @param stockSymbol
	 * @return true if it exists
	 * @return false if it does not exist
	 * 
	 * @author Panagiotis Vakalis
	 * @version 08-07-2015
	 */
	private static boolean lotExists(int portfolioNumber, String stockSymbol) {
		boolean exists = false;
		try {
			connectToDatabase();
			query = "SELECT st_symbol FROM lot WHERE port_number = ?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, portfolioNumber);

			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				if (stockSymbol.equals(resultSet.getString("st_symbol"))) {
					exists = true;
				}
			}
		} catch (SQLException e) {
			System.out.println("Lots have not been retrieved");
		} finally {
			try {
				disconnectFromDatabase();
			} catch (SQLException e) {
				
			}
		}
		return exists;
	}

	/**
	 * Method to update the shares of a lot
	 * 
	 * @param portfolioNumber
	 * @param stockSymbol
	 * @param date
	 * @param shares
	 * 
	 * @author Panagiotis Vakalis
	 * @version 08-07-2015
	 */
	private static void updateSharesBoughtInLot(int portfolioNumber,
			String stockSymbol, Timestamp date, int shares) {

		try {
			connectToDatabase();
			// Update the lot using the current price in current amount
			query = "UPDATE lot SET b_shares = ?, amount = ?, cur_amount = ? "
					+ "WHERE port_number = ? AND st_symbol = ? AND date = ?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, shares);
			preparedStatement.setDouble(
					2,
					shares
							* getBoughtPriceOfALot(stockSymbol,
									portfolioNumber, date));
			preparedStatement.setDouble(3, shares
					* getCurrentPriceOfAStock(stockSymbol));
			preparedStatement.setInt(4, portfolioNumber);
			preparedStatement.setString(5, stockSymbol);
			preparedStatement.setTimestamp(6, date);

			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Shares have not been updated");
		} finally {
			try {
				disconnectFromDatabase();
			} catch (SQLException e) {
				
			}
		}
	}

	/**
	 * Method to update the shares of a lot
	 * 
	 * @param portfolioNumber
	 * @param stockSymbol
	 * @param date
	 * @param shares
	 * 
	 * @author Panagiotis Vakalis
	 * @version 08-07-2015
	 */
	public static void useUpdateSharesBoughtInLot(int portfolioNumber,
			String stockSymbol, Timestamp date, int shares) {
		updateSharesBoughtInLot(portfolioNumber, stockSymbol, date, shares);
	}

	/**
	 * Method to get the current price of a stock
	 * 
	 * @param stockSymbol
	 * @return current price
	 * 
	 * @author Panagiotis Vakalis
	 * @version 08-07-2015
	 */
	private static double getCurrentPriceOfAStock(String stockSymbol) {
		String queryCurrentPrice;
		Connection currentPriceConnection = null;
		PreparedStatement currentPriceStatement = null;
		ResultSet currentPriceResultSet = null;
		double currentPrice = 0;

		// Get the current price for this specific stock

		try {
			currentPriceConnection = DriverManager.getConnection(DATABASEURL,
					USERNAME, PASSWORD);
			queryCurrentPrice = "SELECT trade_price FROM stock WHERE symbol = ?";
			currentPriceStatement = currentPriceConnection
					.prepareStatement(queryCurrentPrice);
			currentPriceStatement.setString(1, stockSymbol);
			currentPriceResultSet = currentPriceStatement.executeQuery();

			while (currentPriceResultSet.next()) {
				currentPrice = currentPriceResultSet.getDouble("trade_price");
			}
		} catch (SQLException e) {
			System.out.println("Current price has not been retrieved");
		} finally {
			try {
				currentPriceResultSet.close();
				currentPriceStatement.close();
				currentPriceConnection.close();
			} catch (SQLException e) {
				
			}
		}
		return currentPrice;
	}

	/**
	 * Method to get the bought price of a lot
	 * 
	 * @param stockSymbol
	 * @param portfolioNumber
	 * @return date
	 * @return current price
	 * 
	 * @author Panagiotis Vakalis
	 * @version 08-07-2015
	 */
	private static double getBoughtPriceOfALot(String stockSymbol,
			int portfolioNumber, Timestamp date) {
		String queryBoughtPrice;
		Connection boughtPriceConnection = null;
		PreparedStatement boughtPriceStatement = null;
		ResultSet boughtPriceSet = null;
		double boughtPrice = 0;

		try {
			boughtPriceConnection = DriverManager.getConnection(DATABASEURL,
					USERNAME, PASSWORD);
			queryBoughtPrice = "SELECT b_price FROM lot WHERE port_number = ? AND st_symbol = ? AND date = ?";
			boughtPriceStatement = boughtPriceConnection
					.prepareStatement(queryBoughtPrice);
			boughtPriceStatement.setInt(1, portfolioNumber);
			boughtPriceStatement.setString(2, stockSymbol);
			boughtPriceStatement.setTimestamp(3, date);

			boughtPriceSet = boughtPriceStatement.executeQuery();

			while (boughtPriceSet.next()) {
				boughtPrice = boughtPriceSet.getDouble("b_price");
			}

		} catch (SQLException e) {
			System.out.println("Bought price has not been retrieved");
		} finally {
			try {
				boughtPriceSet.close();
				boughtPriceStatement.close();
				boughtPriceConnection.close();
			} catch (SQLException e) {
				
			}
		}
		return boughtPrice;
	}

	/**
	 * Method to insert a buy
	 * 
	 * @param stockSymbol
	 * @param email
	 * @param portfolioName
	 * @param shares
	 * 
	 * @author Panagiotis Vakalis
	 * @version 08-07-2015
	 */
	private static void insertBuy(String stockSymbol, int portfolioNumber,
			int shares) {
		Connection maxDateConnection = null;
		PreparedStatement statementMaxDate = null;
		ResultSet resultMaxDate = null;
		Connection boughtPriceConnection = null;
		PreparedStatement statementBoughtPrice = null;
		ResultSet resultBoughtPrice = null;
		try {

			// Get most recent day
			String selectMaxDate = "SELECT MAX(date) AS date FROM lot WHERE port_number = ? AND st_symbol = ?";
			maxDateConnection = DriverManager.getConnection(DATABASEURL,
					USERNAME, PASSWORD);
			statementMaxDate = maxDateConnection
					.prepareStatement(selectMaxDate);
			statementMaxDate.setInt(1, portfolioNumber);
			statementMaxDate.setString(2, stockSymbol);
			resultMaxDate = statementMaxDate.executeQuery();
			Timestamp maxDate = null;

			while (resultMaxDate.next()) {
				maxDate = resultMaxDate.getTimestamp("date");
			}

			// Get bought price
			boughtPriceConnection = DriverManager.getConnection(DATABASEURL,
					USERNAME, PASSWORD);
			String selectBoughtPrice = "SELECT b_price FROM lot WHERE port_number = ? AND st_symbol = ? AND date = ?";
			statementBoughtPrice = boughtPriceConnection
					.prepareStatement(selectBoughtPrice);
			statementBoughtPrice.setInt(1, portfolioNumber);
			statementBoughtPrice.setString(2, stockSymbol);
			statementBoughtPrice.setTimestamp(3, maxDate);
			resultBoughtPrice = statementBoughtPrice.executeQuery();
			double boughtPrice = 0;

			while (resultBoughtPrice.next()) {
				boughtPrice = resultBoughtPrice.getDouble("b_price");
			}

			// Insert a new buy
			connectToDatabase();
			query = "INSERT INTO buy (st_symbol, port_number, bought_price, bought_shares, bought_amount, date) "
					+ "VALUES (?, ?, ?, ?, ?, ?)";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, stockSymbol);
			preparedStatement.setInt(2, portfolioNumber);
			preparedStatement.setDouble(3, boughtPrice);
			preparedStatement.setInt(4, shares);
			preparedStatement.setDouble(5, boughtPrice * shares);
			preparedStatement.setTimestamp(6, maxDate);

			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Buy has not been inserted");
		} finally {
			try {
				resultBoughtPrice.close();
				statementBoughtPrice.close();
				boughtPriceConnection.close();
				resultMaxDate.close();
				statementMaxDate.close();
				maxDateConnection.close();
				disconnectFromDatabase();
			} catch (SQLException e) {
				
			}
		}
	}

	/**
	 * Method to insert a buy
	 * 
	 * @param stockSymbol
	 * @param email
	 * @param portfolioName
	 * @param shares
	 * 
	 * @author Panagiotis Vakalis
	 * @version 08-07-2015
	 */
	public static void useInsertABuy(String stockSymbol, int portfolioNumber,
			int shares) {
		insertBuy(stockSymbol, portfolioNumber, shares);
	}

	/**
	 * Method to insert a new sell
	 * 
	 * @param stockSymbol
	 * @param email
	 * @param portfolioName
	 * @param shares
	 * 
	 * @author Panagiotis Vakalis
	 * @version 08-07-2015
	 */
	private static void insertSell(String stockSymbol, int portfolioNumber,
			int shares, double currentPrice, Timestamp date) {
		try {
			connectToDatabase();
			query = "INSERT INTO sell (st_symbol, port_number, sold_price, sold_shares, sold_amount, profit_loss) "
					+ "VALUES(?, ?, ?, ?, ?, ?)";
			preparedStatement = connection.prepareStatement(query);

			preparedStatement.setString(1, stockSymbol);
			preparedStatement.setInt(2, portfolioNumber);
			preparedStatement.setDouble(3, currentPrice);
			preparedStatement.setInt(4, shares);
			preparedStatement.setDouble(5, currentPrice * shares);
			preparedStatement.setDouble(
					6,
					(currentPrice * shares)
							- (getBoughtPriceOfALot(stockSymbol,
									portfolioNumber, date) * shares));

			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Sell has not been inserted");
		} finally {
			try {
				disconnectFromDatabase();
			} catch (SQLException e) {
				
			}
		}
	}

	/**
	 * Method to insert a new sell
	 * 
	 * @param stockSymbol
	 * @param email
	 * @param portfolioName
	 * @param shares
	 * 
	 * @author Panagiotis Vakalis
	 * @version 08-07-2015
	 */
	public static void useInsertSell(String stockSymbol, int portfolioNumber,
			int shares, double currentPrice, Timestamp date) {
		insertSell(stockSymbol, portfolioNumber, shares, currentPrice, date);
	}

	/**
	 * Method to retrieve the buys
	 * 
	 * @param email
	 * @param portfolioName
	 * 
	 * @author Panagiotis Vakalis
	 * @version 08-07-2015
	 */
	private static void retrieveBuys(int portfolioNumber) {
		try {
			connectToDatabase();
			query = "SELECT st_symbol, date, bought_price, bought_shares, bought_amount FROM buy WHERE port_number = ?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, portfolioNumber);

			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				buysStockSymbol = resultSet.getString("st_symbol");
				buysDate = resultSet.getTimestamp("date");
				buysPrice = resultSet.getDouble("bought_price");
				buysShares = resultSet.getInt("bought_shares");
				buysAmount = resultSet.getDouble("bought_amount");

				buy = new Buy(buysStockSymbol, portfolioNumber, buysDate,
						buysPrice, buysShares, buysAmount);

				buysList.add(buy);
			}
		} catch (SQLException e) {
			System.out.println("Buys have not been retrieved");
		} finally {
			try {
				disconnectFromDatabase();
			} catch (SQLException e) {
				
			}
		}
	}

	/**
	 * Method to retrieve the buys
	 * 
	 * @param portfolioNumber
	 * @return buys list
	 * 
	 * @author Panagiotis Vakalis
	 * @version 24-07-2015
	 */
	public static ArrayList<Buy> useRetrieveBuys(int portfolioNumber) {
		buysList = new ArrayList<>();
		retrieveBuys(portfolioNumber);
		return buysList;
	}

	/**
	 * Method to retrieve sells
	 * 
	 * @param email
	 * @param portfolioName
	 * 
	 * @author Panagiotis Vakalis
	 * @version 08-07-2015
	 */
	private static void retrieveSells(int portfolioNumber) {
		try {
			connectToDatabase();
			query = "SELECT st_symbol, date, sold_price, sold_shares, sold_amount, profit_loss FROM sell WHERE port_number = ?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, portfolioNumber);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				sellsStockSymbol = resultSet.getString("st_symbol");
				sellsDate = resultSet.getTimestamp("date");
				sellsPrice = resultSet.getDouble("sold_price");
				sellsShares = resultSet.getInt("sold_shares");
				sellsAmount = resultSet.getDouble("sold_amount");
				sellsProfitLoss = resultSet.getDouble("profit_loss");

				sell = new Sell(sellsStockSymbol, portfolioNumber, sellsDate,
						sellsPrice, sellsShares, sellsAmount, sellsProfitLoss);

				sellsList.add(sell);
			}
		} catch (SQLException e) {
			System.out.println("Sells have not been retrieved");
		} finally {
			try {
				disconnectFromDatabase();
			} catch (SQLException e) {
				
			}
		}
	}

	/**
	 * Method to retrieve the sells
	 * 
	 * @param portfolioNumber
	 * @return sellsList
	 * 
	 * @author Panagiotis Vakalis
	 * @version 24-07-2015
	 */
	public static ArrayList<Sell> useRetrieveSells(int portfolioNumber) {
		sellsList = new ArrayList<>();
		retrieveSells(portfolioNumber);
		return sellsList;
	}

	/**
	 * Method which retrieves the historical prices for a stock
	 * 
	 * @param stockSymbol
	 * 
	 * @author Panagiotis Vakalis
	 * @version 29-07-2015
	 */
	private static void getHistoricalPrices(String stockSymbol) {
		// Reinitialize the variable
		historicalPrices = new ArrayList<>();

		try {
			connectToDatabase();
			query = "SELECT close_price, date FROM stock_price WHERE stock_symbol = ?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, stockSymbol);

			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				historicalPrices.add(resultSet.getDouble("close_price"));
				dates.add(resultSet.getTimestamp("date"));
			}

		} catch (SQLException e) {
			System.out.println("Historical prices have not been retrieved");
		} finally {
			try {
				disconnectFromDatabase();
			} catch (SQLException e) {
				
			}
		}
	}

	/**
	 * Method which retrieves the historical prices for a stock
	 * 
	 * @param stockSymbol
	 * @return stock prices
	 * 
	 * @author Panagiotis Vakalis
	 * @version 29-07-2015
	 */
	public static ArrayList<Double> useGetHistoricalPrices(String stockSymbol) {
		getHistoricalPrices(stockSymbol);
		return historicalPrices;
	}

	/**
	 * Method which retrieves the dates fot the historical prices for a stock
	 * 
	 * @param stockSymbol
	 * @return dates
	 * 
	 * @author Panagiotis Vakalis
	 * @version 29-07-2015
	 */
	public static ArrayList<Timestamp> getDatesForHistoricalPrices(
			String stockSymbol) {
		return dates;
	}

	/**
	 * Method to get all the stock symbols
	 * 
	 * @return stock symbols
	 * 
	 * @author Panagiotis Vakalis
	 * @version 29-07-2015
	 */
	private static ArrayList<String> getStockSymbols() {
		stockSymbols = new ArrayList<String>();
		try {
			connectToDatabase();
			query = "SELECT symbol FROM stock";
			preparedStatement = connection.prepareStatement(query);

			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				stockSymbols.add(resultSet.getString("symbol"));
			}
		} catch (SQLException e) {
			System.out.println("Stock symbols have not been retrieved");
		} finally {
			try {
				disconnectFromDatabase();
			} catch (SQLException e) {
				
			}
		}

		return stockSymbols;
	}

	/**
	 * Method to get all the stock symbols
	 * 
	 * @return stock symbols
	 * 
	 * @author Panagiotis Vakalis
	 * @version 29-07-2015
	 */
	public static ArrayList<String> useGetStockSymbols() {
		return getStockSymbols();
	}

	/**
	 * Method to get balance for a portfolio
	 * 
	 * @param portfolio
	 *            number
	 * @return balance
	 * 
	 * @author Panagiotis Vakalis
	 * @version 29-07-2015
	 */
	private static BigDecimal getBalance(int portfolioNumber) {
		BigDecimal balance = null;

		try {
			connectToDatabase();
			query = "SELECT balance FROM portfolio WHERE number = ?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, portfolioNumber);

			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				balance = new BigDecimal(resultSet.getDouble("balance"));
			}
		} catch (SQLException e) {
			System.out.println("Balance has not been retrieved");
		} finally {
			try {
				disconnectFromDatabase();
			} catch (SQLException e) {
				
			}
		}
		return balance;
	}

	/**
	 * Method to get balance for a portfolio
	 * 
	 * @param portfolio
	 *            number
	 * @return balance
	 * 
	 * @author Panagiotis Vakalis
	 * @version 29-07-2015
	 */
	public static BigDecimal useGetBalance(int portfolioNumber) {
		return getBalance(portfolioNumber);
	}

	/**
	 * Method to get the invested money from a portfolio
	 * 
	 * @param portfolioNumber
	 * @return invested money
	 * 
	 * @author Panagiotis Vakalis
	 * @version 29-07-2015
	 */
	private static BigDecimal getInvestedMoney(int portfolioNumber) {
		BigDecimal investedMoney = null;

		try {
			connectToDatabase();
			query = "SELECT inv_money FROM portfolio WHERE number = ?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, portfolioNumber);

			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				investedMoney = new BigDecimal(resultSet.getDouble("inv_money"));
			}
		} catch (SQLException e) {
			System.out.println("Invested money have not been retrieved");
		} finally {
			try {
				disconnectFromDatabase();
			} catch (SQLException e) {
				
			}
		}
		return investedMoney.setScale(2, BigDecimal.ROUND_DOWN);
	}

	/**
	 * Method to get the invested money from a portfolio
	 * 
	 * @param portfolioNumber
	 * @return invested money
	 * 
	 * @author Panagiotis Vakalis
	 * @version 29-07-2015
	 */
	public static BigDecimal useGetInvestedMoney(int portfolioNumber) {
		return getInvestedMoney(portfolioNumber);
	}

	/**
	 * Method to insert a deposit for a portfolio
	 * 
	 * @param portfolioNumber
	 * @param amount
	 * 
	 * @author Panagiotis Vakalis
	 * @version 29-07-2015
	 */
	private static void insertDeposit(int portfolioNumber, BigDecimal amount) {

		try {
			connectToDatabase();
			query = "INSERT INTO deposit(port_number, amount) "
					+ "VALUES (?, ?)";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, portfolioNumber);
			preparedStatement.setBigDecimal(2, amount);

			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Deposit has not been inserted");
		} finally {
			try {
				disconnectFromDatabase();
			} catch (SQLException e) {
				
			}
		}
	}

	/**
	 * Method to insert a deposit for a portfolio
	 * 
	 * @param portfolioNumber
	 * @param amount
	 * 
	 * @author Panagiotis Vakalis
	 * @version 29-07-2015
	 */
	public static void useInsertDeposit(int portfolioNumber, BigDecimal amount) {
		insertDeposit(portfolioNumber, amount);
	}

	/**
	 * Method to insert a withdraw for a portfolio
	 * 
	 * @param portfolioNumber
	 * @param amount
	 * 
	 * @author Panagiotis Vakalis
	 * @version 29-07-2015
	 */
	private static void insertWithdraw(int portfolioNumber, BigDecimal amount) {

		try {
			connectToDatabase();
			query = "INSERT INTO withdraw(port_number, amount) "
					+ "VALUES (?, ?)";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, portfolioNumber);
			preparedStatement.setBigDecimal(2, amount);

			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Withdraw has not been inserted");
		} finally {
			try {
				disconnectFromDatabase();
			} catch (SQLException e) {
				
			}
		}
	}

	/**
	 * Method to insert a withdraw for a portfolio
	 * 
	 * @param portfolioNumber
	 * @param amount
	 * 
	 * @author Panagiotis Vakalis
	 * @version 29-07-2015
	 */
	public static void useInsertWithdraw(int portfolioNumber, BigDecimal amount) {
		insertWithdraw(portfolioNumber, amount);
	}
}