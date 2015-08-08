package pxv425;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

/**
 * Class which contains the details for a buy
 * @author Panagiotis Vakalis
 * @version 24-07-2015
 *
 */
public class Buy extends Trade {

	private double boughtPrice;
	private int boughtShares;
	private double boughtAmount;
	
	/**
	 * Constructor of the class
	 * @param stockSymbol
	 * @param portfolioNumber
	 * @param date
	 * @param boughtPrice
	 * @param boughtShares
	 * @param boughtAmount
	 * 
	 * @author Panagiotis Vakalis
	 * @version 24-07-2015
	 */
	public Buy(String stockSymbol, int portfolioNumber, Timestamp date, double boughtPrice, int boughtShares, double boughtAmount){
		super(stockSymbol, portfolioNumber, date);
		this.boughtPrice = boughtPrice;
		this.boughtShares = boughtShares;
		this.boughtAmount = boughtAmount;
	}

	/**
	 * Method to get the bought price outside the class
	 * @return bought price
	 * 
	 * @author Panagiotis Vakalis
	 * @version 24-07-2015
	 */
	public double getBoughtPrice() {
		return boughtPrice;
	}

	/**
	 * Method to set the bought price outside the class
	 * @param boughtPrice
	 * 
	 * @author Panagiotis Vakalis
	 * @version 24-07-2015
	 */
	public void setBoughtPrice(double boughtPrice) {
		this.boughtPrice = boughtPrice;
	}

	/**
	 * Method to get the bought shares outside the class
	 * @return bought shares
	 * 
	 * @author Panagiotis Vakalis
	 * @version 24-07-2015
	 */
	public int getBoughtShares() {
		return boughtShares;
	}

	/**
	 * Method to set the bought shares outside the class
	 * @param boughtShares
	 * 
	 * @author Panagiotis Vakalis
	 * @version 24-07-2015
	 */
	public void setBoughtShares(int boughtShares) {
		this.boughtShares = boughtShares;
	}

	/**
	 * Method to get the bought amount outside the class
	 * @return bought amount
	 * 
	 * @author Panagiotis Vakalis
	 * @version 24-07-2015
	 */
	public double getBoughtAmount() {
		return boughtAmount;
	}

	/**
	 * Method to set the bought amount outside the class
	 * @param boughtAmount
	 * 
	 * @author Panagiotis Vakalis
	 * @version 24-07-2015
	 */
	public void setBoughtAmount(double boughtAmount) {
		this.boughtAmount = boughtAmount;
	}
}
