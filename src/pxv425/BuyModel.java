package pxv425;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * Model part of the buy screen
 * 
 * @author Panagiotis Vakalis
 * @version 27-07-2015
 *
 */
public class BuyModel extends Model {

	private Portfolio portfolio;
	private Stock stock;
	private BigDecimal newInvestedMoney;
	private BigDecimal newBalance;
	private BigDecimal balance;
	private MainModel mainModel;

	/**
	 * Constructor of the class
	 * 
	 * @param client
	 * @param portfolio
	 * @param stock
	 * 
	 * @author Panagiotis Vakalis
	 * @version 27-07-2015
	 */
	public BuyModel(Client client, Portfolio portfolio, Stock stock) {
		super(client);
		this.portfolio = portfolio;
		this.stock = stock;
	}

	/**
	 * Method which retrieves the stock symbol
	 * 
	 * @return stock symbol
	 * 
	 * @author Panagiotis Vakalis
	 * @version 27-07-2015
	 */
	private String stockSymbol() {
		return stock.getSymbol();
	}

	/**
	 * Method which retrieves the stock symbol
	 * 
	 * @return stock symbol
	 * 
	 * @author Panagiotis Vakalis
	 * @version 27-07-2015
	 */
	public String getStockSymbol() {
		return stockSymbol();
	}

	/**
	 * Method which retrieves the stock name
	 * 
	 * @return stock name
	 * 
	 * @author Panagiotis Vakalis
	 * @version 27-07-2015
	 */
	private String stockName() {
		return stock.getName();
	}

	/**
	 * Method which retrieves the stock name
	 * 
	 * @return stock name
	 * 
	 * @author Panagiotis Vakalis
	 * @version 27-07-2015
	 */
	public String getStockName() {
		return stockName();
	}

	/**
	 * Method which retrieves the stock price
	 * 
	 * @return stock price
	 * 
	 * @author Panagiotis Vakalis
	 * @version 27-07-2015
	 */
	private double stockPrice() {
		return stock.getPrice();
	}

	/**
	 * Method which retrieves the stock price
	 * 
	 * @return stock price
	 * 
	 * @author Panagiotis Vakalis
	 * @version 27-07-2015
	 */
	public double getStockPrice() {
		return stockPrice();
	}

	/**
	 * Method to retrieve the invested money of the portfolio
	 * 
	 * @return invested money
	 * 
	 * @author Panagiotis Vakalis
	 * @version 27-07-2015
	 */
	private BigDecimal investedMoney() {
		/*
		 * Call the method from the database
		 */
		return Database.useGetInvestedMoney(portfolio.getNumber());
	}

	/**
	 * Method to retrieve the invested money of the portfolio
	 * 
	 * @return invested money
	 * 
	 * @author Panagiotis Vakalis
	 * @version 27-07-2015
	 */
	public BigDecimal getInvestedMoney() {
		return investedMoney();
	}

	/**
	 * Method to retrieve the balance of the portfolio
	 * 
	 * @return balance
	 * 
	 * @author Panagiotis Vakalis
	 * @version 27-07-2015
	 */
	private BigDecimal balance() {
		return Database.getTotalBalance(portfolio.getNumber());
	}

	/**
	 * Method to retrieve the balance of the portfolio
	 * 
	 * @return balance
	 * 
	 * @author Panagiotis Vakalis
	 * @version 27-07-2015
	 */
	public BigDecimal getBalance() {
		return balance();
	}

	/**
	 * Method to get the updated invested money of the portfolio
	 * 
	 * @author Panagiotis Vakalis
	 * @version 27-07-2015
	 */
	private void updateInvestedMoney(double price, BigInteger shares) {
		newInvestedMoney = investedMoney().add(
				new BigDecimal(price).multiply(new BigDecimal(shares)));
	}

	/**
	 * Method to get the updated invested money of the portfolio
	 * 
	 * @author Panagiotis Vakalis
	 * @version 27-07-2015
	 */
	public void useUpdateInvestedMoney(double price, BigInteger shares) {
		updateInvestedMoney(price, shares);
	}

	/**
	 * Method to update the invested money area on the view
	 * 
	 * @return new invested money
	 * 
	 * @author Panagiotis Vakalis
	 * @version 27-07-2015
	 */
	private BigDecimal updateInvestedMoneyArea() {
		/*
		 * Uses only two decimals
		 */
		return newInvestedMoney.setScale(2, BigDecimal.ROUND_DOWN);
	}

	/**
	 * Method to update the invested money area on the view
	 * 
	 * @return new invested money
	 * 
	 * @author Panagiotis Vakalis
	 * @version 27-07-2015
	 */
	public BigDecimal useUpdateInvesteMoneyArea() {
		return updateInvestedMoneyArea();
	}

	/**
	 * Method to update the balance area on the view
	 * 
	 * @return new invested money
	 * 
	 * @author Panagiotis Vakalis
	 * @version 27-07-2015
	 */
	private BigDecimal updateBalanceArea() {
		return newBalance.setScale(2, BigDecimal.ROUND_DOWN);
	}

	/**
	 * Method to update the balance area on the view
	 * 
	 * @return new invested money
	 * 
	 * @author Panagiotis Vakalis
	 * @version 27-07-2015
	 */
	public BigDecimal useUpdateBalanceArea() {
		return updateBalanceArea();
	}

	/**
	 * Method which is used when a stock is bought. It updates the database and
	 * returns a message
	 * 
	 * @return "You have bought " + sharesCount + " of " + stock.getName() +
	 *         " stock.", if sharesCount > 0
	 * @return "You have not selected the amount of shares", otherwise
	 * @return "You do not have enough balance", if the stock price multiplied
	 *         by shares is higher than the balance
	 * 
	 * @author Panagiotis Vakalis
	 * @version 27-07-2015
	 */
	private String buyStock(int shares) {
		if (shares > 0) {
			int compare = portfolio.getBalance().compareTo(
					new BigDecimal(stock.getPrice() * shares));
			if (compare == 0 || compare == 1) {
				Database.useInsertLot(portfolio.getNumber(), stock.getSymbol(),
						shares);
				Database.useInsertABuy(stock.getSymbol(),
						portfolio.getNumber(), shares);
				Database.useUpdatePortfolioInvestedMoney(portfolio.getNumber(),
						stock.getPrice() * shares);
				Database.useUpdatePortfolioBalance(portfolio.getNumber(),
						BigDecimal.valueOf((stock.getPrice() * shares) * (-1)));
				return "You have bought " + shares + " of " + stock.getName()
						+ " stock.";
			} else {
				return "You do not have enough balance";
			}
		} else {
			return "You have not selected the amount of shares";
		}
	}

	/**
	 * Method which is used when a stock is bought. It updates the database and
	 * returns a message
	 * 
	 * @return "You have bought " + sharesCount + " of " + stock.getName() +
	 *         " stock.", if sharesCount > 0
	 * @return "You have not selected the amount of shares", otherwise
	 * @return "You do not have enough balance", if the stock price multiplied
	 *         by shares is higher than the balance
	 * 
	 * @author Panagiotis Vakalis
	 * @version 27-07-2015
	 */
	public String useBuyStock(int shares) {
		return buyStock(shares);
	}

	/**
	 * Method to update the view
	 * 
	 * @param arg
	 * 
	 * @author Panagiotis Vakalis
	 * @version 27-07-2015
	 */
	private void update(Object arg) {
		setChanged();
		notifyObservers(arg);
	}

	/**
	 * Method to change to main view
	 * 
	 * @param mainView
	 * 
	 * @author Panagiotis Vakalis
	 * @version 27-07-2015
	 */
	private void changeToMainView(MainView mainView) {
		mainModel = new MainModel(super.getClient(), portfolio);
		super.getClient().useChangePanel(mainView);
	}

	/**
	 * Method to change to main view
	 * 
	 * @param mainView
	 * 
	 * @author Panagiotis Vakalis
	 * @version 27-07-2015
	 */
	public void useChangeToMainView(MainView mainView) {
		changeToMainView(mainView);
	}

	/**
	 * Method to get the portfolio outside of the class
	 * 
	 * @return portfolio
	 * 
	 * @author Panagiotis Vakalis
	 * @version 27-07-2015
	 */
	public Portfolio getPortfolio() {
		return portfolio;
	}

	/**
	 * Method which update the balance
	 * 
	 * @param price
	 * @param shares
	 * 
	 * @author Panagiotis Vakalis
	 * @version 27-07-2015
	 */
	private void updateBalance(double price, BigInteger shares) {
		newBalance = balance().subtract(
				new BigDecimal(price).setScale(2, BigDecimal.ROUND_DOWN)
						.multiply(new BigDecimal(shares)));
	}

	/**
	 * Method which update the balance
	 * 
	 * @param price
	 * @param shares
	 * 
	 * @author Panagiotis Vakalis
	 * @version 27-07-2015
	 */
	public void useUpdateBalance(double price, BigInteger shares) {
		updateBalance(price, shares);
	}

	/**
	 * Method which updates the balance and the invested money when check button
	 * is pressed
	 * 
	 * @param price
	 * @param shares
	 * 
	 * @author Panagiotis Vakalis
	 * @version 27-07-2015
	 */
	private void updateNewBalanceAndNewInvestedMoney(double price,
			BigInteger shares) {
		updateBalance(price, shares);
		updateInvestedMoney(price, shares);
		update(balance);
		update(newInvestedMoney);
	}

	/**
	 * Method which updates the balance and the invested money when check button
	 * is pressed
	 * 
	 * @param price
	 * @param shares
	 * 
	 * @author Panagiotis Vakalis
	 * @version 27-07-2015
	 */
	public void useUpdateNewBalanceAndNewInvestedMoney(double price,
			BigInteger shares) {
		updateNewBalanceAndNewInvestedMoney(price, shares);
	}
}
