package pxv425;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
/**
 * Class which contains the sell details
 * 
 * @author Panagiotis Vakalis
 * @version 24-07-2015
 *
 */
public class Sell extends Trade {

	private double soldPrice;
	private int soldShares;
	private double soldAmount;
	private double profitLoss;
	
	/**
	 * Constructor of the class
	 * @param stockSymbol
	 * @param portfolioNumber
	 * @param sellsDate
	 * @param soldPrice
	 * @param soldShares
	 * @param soldAmount
	 * @param profitLoss
	 * 
	 * @author Panagiotis Vakalis
	 * @version 24-07-2015
	 */
	public Sell(String stockSymbol, int portfolioNumber, Timestamp sellsDate, double soldPrice, int soldShares, double soldAmount, double profitLoss){
		super(stockSymbol, portfolioNumber, sellsDate);
		this.soldPrice = soldPrice;
		this.soldShares = soldShares;
		this.soldAmount = soldAmount;
		this.profitLoss = profitLoss;
	}

	/**
	 * Method to get the sold price outside the class
	 * @return sold price
	 * 
	 * @author Panagiotis Vakalis
	 * @version 24-07-2015
	 */
	public double getSoldPrice() {
		return soldPrice;
	}

	/**
	 * Method to set the sold price outside the class
	 * @param soldPrice
	 * 
	 * @author Panagiotis Vakalis
	 * @version 24-07-2015
	 */
	public void setSoldPrice(double soldPrice) {
		this.soldPrice = soldPrice;
	}

	/**
	 * Method to get the sold shares outside the class
	 * @return sold shares
	 * 
	 * @author Panagiotis Vakalis
	 * @version 24-07-2015
	 */
	public int getSoldShares() {
		return soldShares;
	}

	/**
	 * Method to set the sold shares outside the class
	 * @param soldShares
	 * 
	 * @author Panagiotis Vakalis
	 * @version 24-07-2015
	 */
	public void setSoldShares(int soldShares) {
		this.soldShares = soldShares;
	}

	/**
	 * Method to get the sold amount outside the class
	 * @return sold amount
	 * 
	 * @author Panagiotis Vakalis
	 * @version 24-07-2015
	 */
	public double getSoldAmount() {
		return soldAmount;
	}

	/**
	 * Method to set the sold amount outside the class
	 * @param soldAmount
	 * 
	 * @author Panagiotis Vakalis
	 * @version 24-07-2015
	 */
	public void setSoldAmount(double soldAmount) {
		this.soldAmount = soldAmount;
	}

	/**
	 * Method to get the profit/loss outside the class
	 * @return profit/loss
	 * 
	 * @author Panagiotis Vakalis
	 * @version 24-07-2015
	 */
	public double getProfitLoss() {
		return profitLoss;
	}

	/**
	 * Method to set the profit/loss outside the class
	 * @param profitLoss
	 * 
	 * @author Panagiotis Vakalis
	 * @version 24-07-2015
	 */
	public void setProfitLoss(double profitLoss) {
		this.profitLoss = profitLoss;
	}
}
