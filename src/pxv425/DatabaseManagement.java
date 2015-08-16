package pxv425;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.opencsv.CSVReader;

/**
 * Class which will be used in order to manage the database
 *
 * @author Panagiotis Vakalis
 * @version 13-07-2015
 *
 */
public class DatabaseManagement{
	
	private static BufferedReader csvReader;
	private static String csvLine;
	private static Scanner csvScanner;
	private static StringBuilder yahooUri;
	private static String query;
	private static ResultSet resultSet;
	private static PreparedStatement preparedStatement;
	private static Connection connection;
	private static String symbolResult;
	private static Statement statement;
	private static URL downloadCsv;
	private static HttpURLConnection urlConnection;
	private static String url;
	private static Date dateResult;
	

	/**
	 * Method to populate the stock table from database
	 * @param csvFileDirectory
	 * 
	 * @author Panagiotis Vakalis
	 * @version 13-07-2015
	 */
	private static void populateStockTable(String csvFileDirectory){
		
		try {
			//Create a file input stream
			csvReader = new BufferedReader(new FileReader(csvFileDirectory));
			
			//Omit the first line which contains the titles
			csvReader.readLine();
			
			//Read file and insert into database table
			while((csvLine = csvReader.readLine()) != null){
				csvScanner = new Scanner(csvLine);
				//Enter the delimiter
				csvScanner.useDelimiter(",");
				while(csvScanner.hasNext()){
					Database.insertStock(csvScanner.next(), csvScanner.next(), csvScanner.next(), csvScanner.nextDouble());
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Done");
	}
	
	private static void convertPenceToPounds(){
		String queryGetSymbols;
		PreparedStatement statementGetSymbols;
		ResultSet resultSymbols;
		String stockSymbol;
		
		try {
			connection = Database.getConnection();
			//Get the symbols from the table
			queryGetSymbols = "SELECT symbol FROM stock";
			statementGetSymbols = connection.prepareStatement(queryGetSymbols);
			resultSymbols = statementGetSymbols.executeQuery();
			
			while(resultSymbols.next()){
				stockSymbol = resultSymbols.getString("symbol");
				/*
				 * Convert the trade price from the stock
				 * which has this stock symbol.
				 */
				query = "UPDATE stock SET trade_price = round(((SELECT trade_price FROM stock WHERE symbol = ?) / 100), 4) WHERE symbol = ?";
				preparedStatement = connection.prepareStatement(query);
				preparedStatement.setString(1, stockSymbol);
				preparedStatement.setString(2, stockSymbol);
				
				preparedStatement.executeUpdate();
				System.out.println(stockSymbol + " converted");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void useConvertPenceToPounds(){
		convertPenceToPounds();
	}
	
	/**
	 * Method which allows other classes to use the populateStockTable method
	 * @param csvFileDirectory
	 * 
	 * @author Panagiotis Vakalis
	 * @version 13-07-2015
	 */
	public static void usePopulateStockTable(String csvFileDirectory){
		populateStockTable(csvFileDirectory);
	}
	
	/**
	 * Method to update the stock prices
	 * 
	 * @author Panagiotis Vakalis
	 * @version 13-07-2015
	 */
	private static void updateStockPrices(){
		try {
			//Use the connection of the database
			connection = Database.getConnection();
			//Write query to get the symbols
			query = "SELECT symbol FROM stock";
			//Create the statement
			statement = connection.createStatement();
			//Execute query
			resultSet = statement.executeQuery(query);
			
			
			while(resultSet.next()){
				//Get the result
				symbolResult = resultSet.getString("symbol");
				/*
				 * Call the buildYahooUri using the symbol
				 * which has been retrieved from the database
				 * in order to build the url which will download the
				 * price for this particular stock symbol
				 */
				url = buildYahooUrlForCurrentPrice(symbolResult);
				
				downloadCsv = new URL(url);

				urlConnection = (HttpURLConnection)downloadCsv.openConnection();
//				csvReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
				
				/*
				 * This part is from http://opencsv.sourceforge.net
				 * opencsv is a library which is imported inside the project
				 * and has been developed by Glen Smith
				 */
				CSVReader reader = new CSVReader(new InputStreamReader(urlConnection.getInputStream()));
				String[] nextLine;
				while((nextLine = reader.readNext()) != null){
					System.out.println(nextLine[0].toString() + " " + nextLine[3].toString());
					if(!nextLine[3].equals("N/A")){
						Database.useUpdateStockPrice(nextLine[0].toString(), Double.parseDouble(nextLine[3]));
					}
				}
				
				
//				while((csvLine = csvReader.readLine()) != null){
////					System.out.println(csvLine);
//					
////					csvScanner = new Scanner(csvLine);
////					//Enter the delimiter
////					csvScanner.useDelimiter(",");
////					while(csvScanner.hasNext()){
////						Database.insertStock(csvScanner.next(), csvScanner.next(), csvScanner.next(), csvScanner.nextDouble());
////					}
//					
//					//Pattern in order to detect commas inside data
//					Pattern p = Pattern.compile("\"[- ' & a-zA-Z0-9. , / (.?) .]+[,][ a-zA-Z0-9. , / (.?) .]+\"");
//					Matcher matcher = p.matcher(csvLine);
//					if(matcher.find()){
////						System.out.println("Found");
//						String replacement = matcher.group().replaceAll(",", "a");
//						csvLine = csvLine.replaceAll(matcher.group(), replacement);
//					}
//					
//					System.out.println(csvLine);
//					
//					csvScanner = new Scanner(csvLine);
//					csvScanner.useDelimiter(",");
//					count++;
//					while(csvScanner.hasNext()){
////						String symbol = csvScanner.next();
////						String name = csvScanner.next();
////						String date = csvScanner.next();
////						String price = csvScanner.next();
////						String unknown = csvScanner.next();
////						String pE = csvScanner.next();
////						System.out.println("Symbol " + symbol + " name " + name + " date " + date + " price " + price + " unknown " + unknown + " p/e " + pE);
////						Database.useUpdateStockPrice(symbol, Double.parseDouble(unknown));
////						System.out.println(csvScanner.next());
////						String symbol = csvScanner.next();
////						csvScanner.next();
////						csvScanner.next();
//////						csvScanner.next();
////						String price = csvScanner.next();
////						csvScanner.next();
////						csvScanner.next();
////						System.out.println(symbol + " : " + price);
////						System.out.println(csvScanner.next());
//						String symbol = csvScanner.next();
//						csvScanner.next();
//						csvScanner.next();
//						String price = csvScanner.next();
//						csvScanner.next();
//						csvScanner.next();
//						if(!price.equals("N/A")){
//							Database.useUpdateStockPrice(symbol, Double.parseDouble(price));
//						}
//					}
//					System.out.println(count);
//				}
			}
		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Method to use updateStockPrices method outside of the class
	 * 
	 * @author Panagiotis Vakalis
	 * @version 13-07-2015
	 */
	public static void useUpdateStockPrices(){
		updateStockPrices();
	}
	
	/**
	 * Method to get the stock prices
	 * 
	 * @author Panagiotis Vakalis
	 * @version 14-07-2015
	 */
	private static void getStockPrices(){
		String query2;
		ResultSet resultSet2;
		Calendar start = null;
		Calendar end = null;
		try {
			
			connection = Database.getConnection();
			//Query to get the symbol from the stock table
			query = "SELECT symbol FROM stock";
			statement = connection.createStatement();
			resultSet = statement.executeQuery(query);
			
			while(resultSet.next()){
				symbolResult = resultSet.getString("symbol");
				
//				//Query to get the date which is stored into the stock_price table for each stock symbol
//				query2 = "SELECT date FROM stock_price WHERE stock_symbol = ?";
//				preparedStatement = connection.prepareStatement(query2);
//				preparedStatement.setString(1, symbolResult);
//				resultSet2 = preparedStatement.executeQuery();
//				
//				while(resultSet2.next()){
//					dateResult = resultSet2.getDate("date");
//					if(dateResult == null){
//						start = Calendar.getInstance();
//						start.set(2005, 7, 14);
//						end = Calendar.getInstance();
//					}
//					else{
//						start = (Calendar) dateResult;
//						end = Calendar.getInstance();
//					}
//				}
				
				start = Calendar.getInstance();
//				start.set(2004, 7, 31);
//				start.setTime(Database.useGetLastUpdatedDate(symbolResult));
				/*
				 * Get the last updated date and add 86400000 milliseconds
				 * (which are equal to 24 hours) to set the start from the 
				 * next day
				 */
				start.setTimeInMillis(Database.useGetLastUpdatedDate(symbolResult).getTime());
//				System.out.println(start.getTime().toString());
//				System.out.println(start.getTime().toString());
				end = Calendar.getInstance();
				
				url = buildYahooUrlForPrices(symbolResult, start , end);
				
				downloadCsv = new URL(url);
				
				urlConnection = (HttpURLConnection) downloadCsv.openConnection();
				
				/*
				 * This part is from http://opencsv.sourceforge.net
				 * opencsv is a library which is imported inside the project
				 * and has been developed by Glen Smith
				 */
				CSVReader reader = new CSVReader(new InputStreamReader(urlConnection.getInputStream()));
				String[] nextLine;
				nextLine = reader.readNext();
//				System.out.println(nextLine[0] + nextLine[1] + nextLine[2] + nextLine[3] + nextLine[4] + nextLine[5] + nextLine[6]);
				while((nextLine = reader.readNext()) != null){
//					System.out.println(symbolResult.toString() + nextLine[1].toString() + nextLine[4].toString() + nextLine[0].toString());
					/*
					 * index 0 returns the date
					 * index 1 returns the open price
					 * index 2 returns the high price
					 * index 3 returns the low price
					 * index 4 returns the close price
					 * index 5 returns the volume
					 * index 6 returns the adjusted close price
					 */
					Database.useInsertStockPrice(symbolResult, Double.parseDouble(nextLine[1]), Double.parseDouble(nextLine[4]), nextLine[0], Double.parseDouble(nextLine[6]));
				}
			}
		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void useGetStockPrices(){
		getStockPrices();
	}
	
	/**
	 * Method to build the url in order to retrieve the current price
	 * @param stockSymbol
	 * @return url
	 * 
	 * @author Panagiotis Vakalis
	 * @version 13-07-2015
	 */
	private static String buildYahooUrlForCurrentPrice(String stockSymbol){
		yahooUri = new StringBuilder("http://finance.yahoo.com/d/quotes.csv");
		yahooUri.append("?s=");
		yahooUri.append(stockSymbol);
		yahooUri.append("&f=");
		yahooUri.append("snd1l1yr");
		
		return yahooUri.toString();
	}
	
	public static String useBuildYahooUrlForCurrentPrice(String stockSymbol){
		return buildYahooUrlForCurrentPrice(stockSymbol);
	}
	
	/**
	 * Method in order to build the yahoo url which will return the prices for each stock
	 * @param stockSymbol
	 * @param start
	 * @param end
	 * @return url
	 * 
	 * @author Panagiotis Vakalis
	 * @version 14-07-2015
	 */
	private static String buildYahooUrlForPrices(String stockSymbol, Calendar start, Calendar end){
		yahooUri = new StringBuilder("http://ichart.finance.yahoo.com/table.csv");
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
	
	public static String useBuildYahooUrlForPrices(String stockSymbol, Calendar start, Calendar end){
		return buildYahooUrlForPrices(stockSymbol, start, end);
	}

}
