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

public class UpdateHistoricalPrices implements Runnable {

	private String stockSymbol;
	private String url;
	private URL downloadCsv;
	private HttpURLConnection urlConnection;
	private ArrayList<String> stockSymbols;
	private Connection connection;
	private PreparedStatement preparedStatement;
	private String query;
	private ResultSet resultSet;

	public UpdateHistoricalPrices(String stockSymbol) {
		this.stockSymbol = stockSymbol;
	}

	private synchronized void getStockPrices() {

		Calendar start = null;
		Calendar end = null;
		try {
			start = Calendar.getInstance();
			// start.set(2004, 7, 31);
			// start.setTime(Database.useGetLastUpdatedDate(symbolResult));
			/*
			 * Get the last updated date and add 86400000 milliseconds (which
			 * are equal to 24 hours) to set the start from the next day
			 */
			start.setTimeInMillis(Database.useGetLastUpdatedDate(stockSymbol)
					.getTime() + 86400000);
			// System.out.println(start.getTime().toString());
			// System.out.println(start.getTime().toString());
			end = Calendar.getInstance();

			url = DatabaseManagement.useBuildYahooUrlForPrices(stockSymbol,
					start, end);

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
			nextLine = reader.readNext();
			// System.out.println(nextLine[0] + nextLine[1] + nextLine[2] +
			// nextLine[3] + nextLine[4] + nextLine[5] + nextLine[6]);
			while ((nextLine = reader.readNext()) != null) {
				// System.out.println(symbolResult.toString() +
				// nextLine[1].toString() + nextLine[4].toString() +
				// nextLine[0].toString());
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

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
//		getStockPrices();
		while(true){
			getStockPrices();
			try {
			Thread.sleep(86400000);
			System.out.println("Historical prices thread is sleeping");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
	}

}
