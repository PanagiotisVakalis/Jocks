package pxv425;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

/**
 * Class which contains the lot details
 * @author Panagiotis Vakalis
 * @version 19-07-2015
 *
 */
public class Lot {

	private int portfolioNumber;
	private String stockSymbol;
	private double boughtPrice;
	private int boughtShares;
	private double amount;
	private double currentAmount;
	private double currentProfitLoss;
	private Timestamp date;
	private double currentPrice;
	
	/**
	 * Constructor of the class
	 * 
	 * @param portfolio
	 * @param stockSymbol
	 * @param boughtPrice
	 * @param boughtShares
	 * @param amount
	 * @param currentPrice
	 * @param currentAmount
	 * @param currentProfitLoss
	 * @param date
	 * 
	 * @author Panagiotis Vakalis
	 * @version 19-07-2015
	 */
	public Lot(int portfolioNumber, String stockSymbol, double boughtPrice, int boughtShares, double amount, double currentPrice, double currentAmount, Timestamp date){
		this.portfolioNumber = portfolioNumber;
		this.stockSymbol = stockSymbol;
		this.boughtPrice = boughtPrice;
		this.boughtShares = boughtShares;
		this.amount = amount;
		this.currentPrice = currentPrice;
		this.currentAmount = currentAmount;
		this.currentProfitLoss = currentAmount - amount;
		this.date = date;
	}

	/**
	 * Method to use the current price outside the class
	 * @return current price
	 * 
	 * @author Panagiotis Vakalis
	 * @version 19-07-2015	
	 */
	public double getCurrentPrice() {
		return currentPrice;
	}

	/**
	 * Method to set the current price outside of the class
	 * @param currentPrice
	 * 
	 * @author Panagiotis Vakalis
	 * @version 19-07-2015	
	 */
	public void setCurrentPrice(double currentPrice) {
		this.currentPrice = currentPrice;
	}

	/**
	 * Method to get portfolio
	 * @return portfolio
	 * 
	 * @author Panagiotis Vakalis
	 * @version 19-07-2015
	 */
	public int getPortfolioNumber() {
		return portfolioNumber;
	}

	/**
	 * Method to set portfolio
	 * @param portfolio
	 * 
	 * @author Panagiotis Vakalis
	 * @version 19-07-2015
	 */
	public void setPortfolioNumber(int portfolioNumber) {
		this.portfolioNumber = portfolioNumber;
	}

	/**
	 * Method to get the stock symbol
	 * @return stock symbol
	 * 
	 * @author Panagiotis Vakalis
	 * @version 19-07-2015
	 */
	public String getStockSymbol() {
		return stockSymbol;
	}

	/**
	 * Method to set the stock symbol
	 * @param stockSymbol
	 * 
	 * @author Panagiotis Vakalis
	 * @version 19-07-2015
	 */
	public void setStockSymbol(String stockSymbol) {
		this.stockSymbol = stockSymbol;
	}

	/**
	 * Method to get the bought price
	 * @return bought price
	 * 
	 * @author Panagiotis Vakalis
	 * @version 19-07-2015
	 */
	public double getBoughtPrice() {
		return boughtPrice;
	}

	/**
	 * Method to set the bought price
	 * @param boughtPrice
	 * 
	 * @author Panagiotis Vakalis
	 * @version 19-07-2015
	 */
	public void setBoughtPrice(double boughtPrice) {
		this.boughtPrice = boughtPrice;
	}

	/**
	 * Method to get the bought shares
	 * @return bought shares
	 * 
	 * @author Panagiotis Vakalis
	 * @version 19-07-2015
	 */
	public int getBoughtShares() {
		return boughtShares;
	}

	/**
	 * Method to set the bought shares
	 * @param boughtShares
	 * 
	 * @author Panagiotis Vakalis
	 * @version 19-07-2015
	 */
	public void setBoughtShares(int boughtShares) {
		this.boughtShares = boughtShares;
	}

	/**
	 * Method to get the amount
	 * @return amount
	 * 
	 * @author Panagiotis Vakalis
	 * @version 19-07-2015
	 */
	public double getAmount() {
		return amount;
	}

	/**
	 * Method to set the amount
	 * @param amount
	 * 
	 * @author Panagiotis Vakalis
	 * @version 19-07-2015
	 */
	public void setAmount(double amount) {
		this.amount = amount;
	}

	/**
	 * Method to get the current amount
	 * @return current amount
	 * 
	 * @author Panagiotis Vakalis
	 * @version 19-07-2015
	 */
	public double getCurrentAmount() {
		return currentAmount;
	}

	/**
	 * Method to set the current amount
	 * @param currentAmount
	 * 
	 * @author Panagiotis Vakalis
	 * @version 19-07-2015
	 */
	public void setCurrentAmount(double currentAmount) {
		this.currentAmount = currentAmount;
	}

	/**
	 * Method to get the current profit / loss
	 * @return profit / loss
	 * 
	 * @author Panagiotis Vakalis
	 * @version 19-07-2015
	 */
	public double getCurrentProfitLoss() {
		return currentProfitLoss;
	}

	/**
	 * Method to set the current profit / loss
	 * @param currentProfitLoss
	 * 
	 * @author Panagiotis Vakalis
	 * @version 19-07-2015
	 */
	public void setCurrentProfitLoss(double currentProfitLoss) {
		this.currentProfitLoss = currentProfitLoss;
	}

	/**
	 * Method to get the date
	 * @return date
	 * 
	 * @author Panagiotis Vakalis
	 * @version 19-07-2015
	 */
	public Timestamp getDate() {
		return date;
	}

	/**
	 * Method to set the date
	 * @param date
	 * 
	 * @author Panagiotis Vakalis
	 * @version 19-07-2015
	 */
	public void setDate(Timestamp date) {
		this.date = date;
	}
	
	
}
