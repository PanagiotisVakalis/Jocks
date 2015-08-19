package pxv425;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.opencsv.CSVReader;

/**
 * Class which contains the method for updating the stock prices of a stock
 * using the current price which yahoo finance provides
 * 
 * @author Panagiotis Vakalis
 * @version 07-08-2015
 *
 */
public class UpdateStock {

	private String stockSymbol;
	private String url;
	private URL downloadCsv;
	private HttpURLConnection urlConnection;
	private ArrayList<String> stockSymbols;
	private Connection connection;
	private PreparedStatement preparedStatement;
	private String query;
	private ResultSet resultSet;
	private StringBuilder yahooUri;

	/**
	 * Constructor of the class
	 * 
	 * @param stock
	 *            symbol
	 * 
	 * @author Panagiotis Vakalis
	 * @version 07-08-2015
	 */
	public UpdateStock(String stockSymbol) {
		this.stockSymbol = stockSymbol;
	}

	/**
	 * Method to update the stock prices
	 * 
	 * @author Panagiotis Vakalis
	 * @version 13-07-2015
	 */
	private void updateStockPrices() {
		try {
			/*
			 * Call the buildYahooUri using the symbol which has been retrieved
			 * from the database in order to build the url which will download
			 * the price for this particular stock symbol
			 */
			url = buildYahooUrlForCurrentPrice(stockSymbol);

			downloadCsv = new URL(url);

			urlConnection = (HttpURLConnection) downloadCsv.openConnection();

			/*
			 * This part is from http://opencsv.sourceforge.net opencsv is a
			 * library which is imported inside the project and has been
			 * developed by Glen Smith
			 */
			CSVReader reader = new CSVReader(new InputStreamReader(
					urlConnection.getInputStream()));
			String[] nextLine;
			while ((nextLine = reader.readNext()) != null) {
				if (!nextLine[3].equals("N/A")) {
					Database.useUpdateStockPrice(nextLine[0].toString(),
							Double.parseDouble(nextLine[3]));
				}
			}
			System.out.println(stockSymbol + " current price has been updated");
		} catch (IOException e) {
			System.out.println("Stock price has not been updated");
		}

	}

	/**
	 * Method to update the stock prices
	 * 
	 * @author Panagiotis Vakalis
	 * @version 13-07-2015
	 */
	public void useUpdateStockPrices() {
		updateStockPrices();
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
	private String buildYahooUrlForCurrentPrice(String stockSymbol) {
		yahooUri = new StringBuilder("http://finance.yahoo.com/d/quotes.csv");
		yahooUri.append("?s=");
		yahooUri.append(stockSymbol);
		yahooUri.append("&f=");
		yahooUri.append("snd1l1yr");

		return yahooUri.toString();
	}

}
