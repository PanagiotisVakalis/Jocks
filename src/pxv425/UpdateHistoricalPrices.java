package pxv425;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;

import com.opencsv.CSVReader;

/**
 * Class which contains the method for updating the historical prices of a
 * stock
 * 
 * @author Panagiotis Vakalis
 * @version 07-08-2015
 *
 */
public class UpdateHistoricalPrices {

	private String stockSymbol;
	private String url;
	private URL downloadCsv;
	private HttpURLConnection urlConnection;
	private ArrayList<String> stockSymbols;
	private Connection connection;
	private PreparedStatement preparedStatement;
	private String query;
	private ResultSet resultSet;

	/**
	 * Constructor of the class
	 * 
	 * @param stock
	 *            symbol
	 * 
	 * @author Panagiotis Vakalis
	 * @version 07-08-2015
	 *
	 */
	public UpdateHistoricalPrices(String stockSymbol) {
		this.stockSymbol = stockSymbol;
	}

	/**
	 * Method which gets the stock prices from yahoo finance
	 * 
	 * @author Panagiotis Vakalis
	 * @version 07-08-2015
	 *
	 */
	private void getStockPrices() {

		Calendar start = null;
		Calendar end = null;
		try {
			start = Calendar.getInstance();
			/*
			 * Get the last updated date and add 86400000 milliseconds (which
			 * are equal to 24 hours) to set the start from the next day
			 */
			start.setTimeInMillis(Database.useGetLastUpdatedDate(stockSymbol)
					.getTime() + 86400000);

			end = Calendar.getInstance();

			url = DatabaseManagement.useBuildYahooUrlForPrices(stockSymbol,
					start, end);

			downloadCsv = new URL(url);

			/*
			 * Open connection
			 */
			urlConnection = (HttpURLConnection) downloadCsv.openConnection();

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
				 * index 0 returns the date index 1 returns the open price index
				 * 2 returns the high price index 3 returns the low price index
				 * 4 returns the close price index 5 returns the volume index 6
				 * returns the adjusted close price
				 */
				Database.useInsertStockPrice(stockSymbol,
						Double.parseDouble(nextLine[1]),
						Double.parseDouble(nextLine[4]), nextLine[0],
						Double.parseDouble(nextLine[6]));
			}

			System.out.println(stockSymbol
					+ " historical prices has been updated");
		} catch (IOException e) {
			System.out.println("No new entry for the " + stockSymbol
					+ " stock.");
		}
	}

	/**
	 * Method which gets the stock prices from yahoo finance
	 * 
	 * @author Panagiotis Vakalis
	 * @version 07-08-2015
	 *
	 */
	public void useGetStockPrices() {
		getStockPrices();
	}

}
