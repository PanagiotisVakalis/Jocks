package pxv425;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.Scanner;

/*
 * http://opencsv.sourceforge.net
 * opencsv is a library which is imported inside the project
 * and has been developed by Glen Smith
 */
import com.opencsv.CSVReader;

/**
 * Class which will be used in order to manage the database
 *
 * @author Panagiotis Vakalis
 * @version 13-07-2015
 *
 */
public class DatabaseManagement {

	private static BufferedReader csvReader;
	private static String csvLine;
	private static Scanner csvScanner;
	private static StringBuilder yahooUri;
	private static String query;
	private static PreparedStatement preparedStatement;
	private static Connection connection;
	private static String symbolResult;
	private static Statement statement;
	private static URL downloadCsv;
	private static HttpURLConnection urlConnection;
	private static String url;

	/**
	 * Method to populate the stock table from database
	 * 
	 * @param csvFileDirectory
	 * 
	 * @author Panagiotis Vakalis
	 * @version 13-07-2015
	 */
	private static void populateStockTable(String csvFileDirectory) {

		try {
			// Create a file input stream
			csvReader = new BufferedReader(new FileReader(csvFileDirectory));

			// Omit the first line which contains the titles
			csvReader.readLine();

			// Read file and insert into database table
			while ((csvLine = csvReader.readLine()) != null) {
				csvScanner = new Scanner(csvLine);
				// Enter the delimiter
				csvScanner.useDelimiter(",");
				while (csvScanner.hasNext()) {
					Database.useInsertStock(csvScanner.next(),
							csvScanner.next(), csvScanner.next(),
							csvScanner.nextDouble());
				}
			}
		} catch (IOException e) {
			System.out.println("No file");
		}
		System.out.println("Done");
	}

	/**
	 * Method to convert the pence which have been
	 * stored in the database to pounds
	 * 
	 * @author Panagiotis Vakalis
	 * @version 13-07-2015
	 */
	private static void convertPenceToPounds() {
		String queryGetSymbols;
		PreparedStatement statementGetSymbols;
		ResultSet resultSymbols;
		String stockSymbol;

		try {
			connection = Database.getConnection();
			// Get the symbols from the table
			queryGetSymbols = "SELECT symbol FROM stock";
			statementGetSymbols = connection.prepareStatement(queryGetSymbols);
			resultSymbols = statementGetSymbols.executeQuery();

			while (resultSymbols.next()) {
				stockSymbol = resultSymbols.getString("symbol");
				/*
				 * Convert the trade price from the stock which has this stock
				 * symbol.
				 */
				query = "UPDATE stock SET trade_price = round(((SELECT trade_price FROM stock WHERE symbol = ?) / 100), 4) WHERE symbol = ?";
				preparedStatement = connection.prepareStatement(query);
				preparedStatement.setString(1, stockSymbol);
				preparedStatement.setString(2, stockSymbol);

				preparedStatement.executeUpdate();
				System.out.println(stockSymbol + " converted");
			}
		} catch (SQLException e) {
			System.out.println("Price has not been converted");
		}
	}

	/**
	 * Method to convert the pence which have been
	 * stored in the database to pounds
	 * 
	 * @author Panagiotis Vakalis
	 * @version 13-07-2015
	 */
	public static void useConvertPenceToPounds() {
		convertPenceToPounds();
	}

	/**
	 * Method which allows other classes to use the populateStockTable method
	 * 
	 * @param csvFileDirectory
	 * 
	 * @author Panagiotis Vakalis
	 * @version 13-07-2015
	 */
	public static void usePopulateStockTable(String csvFileDirectory) {
		populateStockTable(csvFileDirectory);
	}

	/**
	 * Method to update the stock prices
	 * 
	 * @author Panagiotis Vakalis
	 * @version 13-07-2015
	 */
	private static void updateStockPrices() {
		Connection connectionSelectSymbols = null;
		String selectSymbolsQuery;
		PreparedStatement selectSymbolsStatement = null;
		ResultSet symbolsResultSet = null;
		try {
			// Use the connection of the database
			connectionSelectSymbols = DriverManager.getConnection(
					Database.getDatabaseurl(), Database.getUsername(),
					Database.getPassword());
			// Write query to get the symbols
			selectSymbolsQuery = "SELECT symbol FROM stock";
			// Create the statement
			selectSymbolsStatement = connectionSelectSymbols
					.prepareStatement(selectSymbolsQuery);
			// Execute query
			symbolsResultSet = statement.executeQuery(query);

			while (symbolsResultSet.next()) {
				// Get the result
				symbolResult = symbolsResultSet.getString("symbol");
				/*
				 * Call the buildYahooUri using the symbol which has been
				 * retrieved from the database in order to build the url which
				 * will download the price for this particular stock symbol
				 */
				url = buildYahooUrlForCurrentPrice(symbolResult);

				downloadCsv = new URL(url);

				urlConnection = (HttpURLConnection) downloadCsv
						.openConnection();

				/*
				 * This part is from http://opencsv.sourceforge.net opencsv is a
				 * library which is imported inside the project and has been
				 * developed by Glen Smith
				 */
				CSVReader reader = new CSVReader(new InputStreamReader(
						urlConnection.getInputStream()));
				String[] nextLine;
				while ((nextLine = reader.readNext()) != null) {
					System.out.println(nextLine[0].toString() + " "
							+ nextLine[3].toString());
					if (!nextLine[3].equals("N/A")) {
						Database.useUpdateStockPrice(nextLine[0].toString(),
								Double.parseDouble(nextLine[3]));
					}
				}
			}
		} catch (SQLException | IOException e) {
			System.out.println(symbolResult + " has not been updated");
		} finally {
			try {
				symbolsResultSet.close();
				selectSymbolsStatement.close();
				connectionSelectSymbols.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	/**
	 * Method to use updateStockPrices method outside of the class
	 * 
	 * @author Panagiotis Vakalis
	 * @version 13-07-2015
	 */
	public static void useUpdateStockPrices() {
		updateStockPrices();
	}

	/**
	 * Method to get the stock prices
	 * 
	 * @author Panagiotis Vakalis
	 * @version 14-07-2015
	 */
	private static void getStockPrices() {
		Connection connectionSymbol = null;
		PreparedStatement preparedStatmentSymbol = null;
		String querySymbol;
		ResultSet resultSetSymbol = null;
		Calendar start = null;
		Calendar end = null;
		try {

			connectionSymbol = DriverManager.getConnection(
					Database.getDatabaseurl(), Database.getUsername(),
					Database.getPassword());
			// Query to get the symbol from the stock table
			querySymbol = "SELECT symbol FROM stock";
			preparedStatmentSymbol = connectionSymbol
					.prepareStatement(querySymbol);
			resultSetSymbol = preparedStatmentSymbol.executeQuery();

			while (resultSetSymbol.next()) {
				symbolResult = resultSetSymbol.getString("symbol");

				start = Calendar.getInstance();
				
				start.setTimeInMillis(Database.useGetLastUpdatedDate(
						symbolResult).getTime());
				end = Calendar.getInstance();

				url = buildYahooUrlForPrices(symbolResult, start, end);

				downloadCsv = new URL(url);

				urlConnection = (HttpURLConnection) downloadCsv
						.openConnection();

				/*
				 * This part is from http://opencsv.sourceforge.net opencsv is a
				 * library which is imported inside the project and has been
				 * developed by Glen Smith
				 */
				CSVReader reader = new CSVReader(new InputStreamReader(
						urlConnection.getInputStream()));
				String[] nextLine;
				nextLine = reader.readNext();
				while ((nextLine = reader.readNext()) != null) {

					/*
					 * index 0 returns the date index 1 returns the open price
					 * index 2 returns the high price index 3 returns the low
					 * price index 4 returns the close price index 5 returns the
					 * volume index 6 returns the adjusted close price
					 */
					Database.useInsertStockPrice(symbolResult,
							Double.parseDouble(nextLine[1]),
							Double.parseDouble(nextLine[4]), nextLine[0],
							Double.parseDouble(nextLine[6]));
				}
			}
		} catch (SQLException | IOException e) {
			System.out.println(symbolResult + " has not been updated");
		} finally {
			try {
				resultSetSymbol.close();
				preparedStatmentSymbol.close();
				connectionSymbol.close();
			} catch (SQLException e) {
				/*
				 * No body
				 */
			}
		}
	}

	/**
	 * Method to get the stock prices
	 * 
	 * @author Panagiotis Vakalis
	 * @version 14-07-2015
	 */
	public static void useGetStockPrices() {
		getStockPrices();
	}

	/**
	 * Method to build the url in order to retrieve the current price
	 * 
	 * @param stockSymbol
	 * @return url
	 * 
	 * @author Panagiotis Vakalis
	 * @version 13-07-2015
	 */
	private static String buildYahooUrlForCurrentPrice(String stockSymbol) {
		yahooUri = new StringBuilder("http://finance.yahoo.com/d/quotes.csv");
		yahooUri.append("?s=");
		yahooUri.append(stockSymbol);
		yahooUri.append("&f=");
		yahooUri.append("snd1l1yr");

		return yahooUri.toString();
	}

	/**
	 * Method to build the url in order to retrieve the current price
	 * 
	 * @param stockSymbol
	 * @return url
	 * 
	 * @author Panagiotis Vakalis
	 * @version 13-07-2015
	 */
	public static String useBuildYahooUrlForCurrentPrice(String stockSymbol) {
		return buildYahooUrlForCurrentPrice(stockSymbol);
	}

	/**
	 * Method in order to build the yahoo url which will return the prices for
	 * each stock
	 * 
	 * @param stockSymbol
	 * @param start
	 * @param end
	 * @return url
	 * 
	 * @author Panagiotis Vakalis
	 * @version 14-07-2015
	 */
	private static String buildYahooUrlForPrices(String stockSymbol,
			Calendar start, Calendar end) {
		yahooUri = new StringBuilder(
				"http://ichart.finance.yahoo.com/table.csv");
		yahooUri.append("?s=");
		yahooUri.append(stockSymbol);
		yahooUri.append("&a=");
		yahooUri.append(start.get(Calendar.MONTH));
		yahooUri.append("&b=");
		yahooUri.append(start.get(Calendar.DAY_OF_MONTH));
		yahooUri.append("&c=");
		yahooUri.append(start.get(Calendar.YEAR));
		yahooUri.append("&d=");
		yahooUri.append(end.get(Calendar.MONTH));
		yahooUri.append("&e=");
		yahooUri.append(end.get(Calendar.DAY_OF_MONTH));
		yahooUri.append("&f=");
		yahooUri.append(end.get(Calendar.YEAR));
		yahooUri.append("&g=d");

		return yahooUri.toString();
	}

	/**
	 * Method in order to build the yahoo url which will return the prices for
	 * each stock
	 * 
	 * @param stockSymbol
	 * @param start
	 * @param end
	 * @return url
	 * 
	 * @author Panagiotis Vakalis
	 * @version 14-07-2015
	 */
	public static String useBuildYahooUrlForPrices(String stockSymbol,
			Calendar start, Calendar end) {
		return buildYahooUrlForPrices(stockSymbol, start, end);
	}

}
