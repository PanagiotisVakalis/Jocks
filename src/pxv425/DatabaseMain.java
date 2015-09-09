package pxv425;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class DatabaseMain {

	public static void main(String[] args) {

		/*
		 * Get all the symbols which are stored on the database. Create an
		 * arraylist for the updateStock objects. Create an arraylist for the
		 * updateHistoricalPrices objects.
		 */
		ArrayList<String> symbols = Database.useGetStockSymbols();
		ArrayList<UpdateStock> stockPrice = new ArrayList<>();
		ArrayList<UpdateHistoricalPrices> historicalPrices = new ArrayList<>();

		for (int i = 0; i < symbols.size(); i++) {
			/*
			 * Add both updateStock and UpdateHistoricalPrices objects in the
			 * corresponding arraylist. In construction of each object the
			 * current symbol from arraylist is used.
			 */
			stockPrice.add(new UpdateStock(symbols.get(i)));
			historicalPrices.add(new UpdateHistoricalPrices(symbols.get(i)));
		}

		while (true) {
			if (isValidDayAndTime()) {
				/*
				 * If the stock market is open
				 */
				int i;
				for (i = 0; i < stockPrice.size()
						&& i < historicalPrices.size(); i++) {
					/*
					 * Call the methods When the loop finishes reinitialize the
					 * i with 0 in order to start the loop again
					 */
					stockPrice.get(i).useUpdateStockPrices();
					historicalPrices.get(i).useGetStockPrices();
				}
				i = 0;
			}
			else{
				System.out.println("Stock market is closed.");
			}
		}

	}

	/**
	 * Method to check whether the stock market is open or closed
	 * 
	 * @return true, if the time and the day are ok return false, if the time
	 *         and the day are not ok
	 * 
	 * @author Panagiotis Vakalis
	 * @version 14-07-2015
	 */
	private static boolean isValidDayAndTime() {
		Calendar calendar = Calendar.getInstance();
		int today = calendar.get(Calendar.DAY_OF_WEEK);
		SimpleDateFormat time = new SimpleDateFormat("HH:mm:ss");
		if (today == Calendar.SATURDAY || today == Calendar.SUNDAY) {
			return false;
		} else {
			if (time.format(calendar.getTime()).compareTo("16:29:59") == 1
					|| time.format(calendar.getTime()).compareTo("08:59:59") == -1) {
				return false;
			} else {
				return true;
			}
		}
	}
}
