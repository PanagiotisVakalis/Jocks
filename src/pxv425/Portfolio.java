package pxv425;

import java.math.BigDecimal;

/**
 * Class which contains the Portfolio details
 * 
 * @author Panagiotis Vakalis
 * @version 18-07-2015
 *
 */
public class Portfolio {

	private Integer number;
	private String name;
	private BigDecimal investedMoney;
	private BigDecimal balance;
	private double profitLoss;

	/**
	 * Constructor of the class
	 * 
	 * @param number
	 * @param name
	 * @param investedMoney
	 * @param balance
	 * @param profitLoss
	 * 
	 * @author Panagiotis Vakalis
	 * @version 18-07-2015
	 */
	public Portfolio(Integer number, String name, BigDecimal investedMoney,
			BigDecimal balance, double profitLoss) {
		this.number = number;
		this.name = name;
		this.investedMoney = investedMoney;
		this.balance = balance;
		this.profitLoss = profitLoss;
	}

	/**
	 * Method to get the portfolio number
	 * 
	 * @return portfolio number
	 * 
	 * @author Panagiotis Vakalis
	 * @version 18-07-2015
	 */
	public Integer getNumber() {
		return number;
	}

	/**
	 * Method to set the portfolio number
	 * 
	 * @param number
	 * 
	 * @author Panagiotis Vakalis
	 * @version 18-07-2015
	 */
	public void setNumber(Integer number) {
		this.number = number;
	}

	/**
	 * Method to get the portfolio name
	 * 
	 * @return portfolio name
	 * 
	 * @author Panagiotis Vakalis
	 * @version 18-07-2015
	 */
	public String getName() {
		return name;
	}

	/**
	 * Method to set the portfolio name
	 * 
	 * @param name
	 * 
	 * @author Panagiotis Vakalis
	 * @version 18-07-2015
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Method to get the invested money
	 * 
	 * @return invested money
	 * 
	 * @author Panagiotis Vakalis
	 * @version 18-07-2015
	 */
	public BigDecimal getInvestedMoney() {
		return investedMoney;
	}

	/**
	 * Method to set the invested money
	 * 
	 * @param investedMoney
	 * 
	 * @author Panagiotis Vakalis
	 * @version 18-07-2015
	 */
	public void setInvestedMoney(BigDecimal investedMoney) {
		this.investedMoney = investedMoney;
	}

	/**
	 * Method to get the balance
	 * 
	 * @return balance
	 * 
	 * @author Panagiotis Vakalis
	 * @version 18-07-2015
	 */
	public BigDecimal getBalance() {
		return balance;
	}

	/**
	 * Method to set the balance
	 * 
	 * @param balance
	 * 
	 * @author Panagiotis Vakalis
	 * @version 18-07-2015
	 */
	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	/**
	 * Method to get the profit / loss
	 * 
	 * @return profit / loss
	 * 
	 * @author Panagiotis Vakalis
	 * @version 18-07-2015
	 */
	public double getProfitLoss() {
		return profitLoss;
	}

	/**
	 * Method to set the profit / loss
	 * 
	 * @param profitLoss
	 * 
	 * @author Panagiotis Vakalis
	 * @version 18-07-2015
	 */
	public void setProfitLoss(double profitLoss) {
		this.profitLoss = profitLoss;
	}
}
