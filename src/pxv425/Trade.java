package pxv425;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
/**
 * Class which contains all the informations for the trade
 * 
 * @author Panagiotis Vakalis
 * @version 24-07-2015
 *
 */

public class Trade {

	private String stockSymbol;
	private int portfolioNumber;
	private Timestamp date;
	
	/**
	 * Constructor of the class
	 * @param stockSymbol
	 * @param portfolioNumber
	 * @param date
	 * 
	 * @author Panagiotis Vakalis
	 * @version 24-07-2015
	 */
	public Trade(String stockSymbol, int portfolioNumber, Timestamp date){
		this.stockSymbol = stockSymbol;
		this.portfolioNumber = portfolioNumber;
		this.date = date;
	}

	/**
	 * Method to get the stock symbol outside the class
	 * @return stock symbol
	 * 
	 * @author Panagiotis Vakalis
	 * @version 24-07-2015
	 */
	public String getStockSymbol() {
		return stockSymbol;
	}

	/**
	 * Method to set the stock symbol outside the class
	 * @param stockSymbol
	 * 
	 * @author Panagiotis Vakalis
	 * @version 24-07-2015
	 */
	public void setStockSymbol(String stockSymbol) {
		this.stockSymbol = stockSymbol;
	}

	/**
	 * Method to get the portfolio number outside the class
	 * @return portfolio number
	 * 
	 * @author Panagiotis Vakalis
	 * @version 24-07-2015
	 */
	public int getPortfolioNumber() {
		return portfolioNumber;
	}

	/**
	 * Method to set the portfolio number outside the class
	 * @param portfolioNumber
	 * 
	 * @author Panagiotis Vakalis
	 * @version 24-07-2015
	 */
	public void setPortfolioNumber(int portfolioNumber) {
		this.portfolioNumber = portfolioNumber;
	}

	/**
	 * Method to get the date outside the class
	 * @return date
	 * 
	 * @author Panagiotis Vakalis
	 * @version 24-07-2015
	 */
	public Timestamp getDate() {
		return date;
	}

	/**
	 * Method to set the date outside the class
	 * @param date
	 * 
	 * @author Panagiotis Vakalis
	 * @version 24-07-2015
	 */
	public void setDate(Timestamp date) {
		this.date = date;
	}
	
	
}
