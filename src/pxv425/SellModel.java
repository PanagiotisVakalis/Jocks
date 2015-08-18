package pxv425;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Class which contains the model part of the sell screen
 * 
 * 
 * @author Panagiotis Vakalis
 * @version 28-07-2015
 *
 */
public class SellModel extends Model {

	private Portfolio portfolio;
	private Lot lot;
	private int sharesCount = 0;
	private BigDecimal newInvestedMoney;
	private BigDecimal newBalance;
	private LotsModel lotsModel;

	/**
	 * Constructor of the class
	 * 
	 * @param client
	 * @param portfolio
	 * @param lot
	 * 
	 * @author Panagiotis Vakalis
	 * @version 28-07-2015
	 */
	public SellModel(Client client, Portfolio portfolio, Lot lot) {
		super(client);
		this.portfolio = portfolio;
		this.lot = lot;
	}

	/**
	 * Method to retrieve the stock symbol of a lot
	 * 
	 * @return stock symbol
	 * 
	 * @author Panagiotis Vakalis
	 * @version 28-07-2015
	 */
	private String stockSymbol() {
		return lot.getStockSymbol();
	}

	/**
	 * Method to get the stock symbol of a lot outside the class
	 * 
	 * @return stock symbol
	 * 
	 * @author Panagiotis Vakalis
	 * @version 28-07-2015
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
	 * @version 28-07-2015
	 */
	private String stockName() {
		return Database.useGetStockNameUsingSymbol(lot.getStockSymbol());
	}

	/**
	 * Method to use the stockName() outside the class
	 * 
	 * @return stock name
	 * 
	 * @author Panagiotis Vakalis
	 * @version 28-07-2015
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
	 * @version 28-07-2015
	 */
	private double stockPrice() {
		return lot.getCurrentPrice();
	}

	/**
	 * Method which retrieves the stock price
	 * 
	 * @return stock price
	 * 
	 * @author Panagiotis Vakalis
	 * @version 28-07-2015
	 */
	public double getStockPrice() {
		return stockPrice();
	}

	/**
	 * Method which retrieves the shares from the lot
	 * 
	 * @return shares
	 * 
	 * @author Panagiotis Vakalis
	 * @version 28-07-2015
	 */
	private int boughtShares() {
		return lot.getBoughtShares();
	}

	/**
	 * Method which retrieves the shares from the lot
	 * 
	 * @return shares
	 * 
	 * @author Panagiotis Vakalis
	 * @version 28-07-2015
	 */
	public int getBoughtShares() {
		return boughtShares();
	}

	/**
	 * Method to get the shares count
	 * 
	 * @return shares count
	 * 
	 * @author Panagiotis Vakalis
	 * @version 28-07-2015
	 */
	public String getSharesCount() {
		return String.valueOf(sharesCount);
	}

	/**
	 * Method to retrieve the invested money of the portfolio
	 * 
	 * @return invested money
	 * 
	 * @author Panagiotis Vakalis
	 * @version 28-07-2015
	 */
	private BigDecimal investedMoney() {
		return Database.useGetInvestedMoney(portfolio.getNumber());
	}

	/**
	 * Method to use the investeMoney() outside the class
	 * 
	 * @return invested money
	 * 
	 * @author Panagiotis Vakalis
	 * @version 28-07-2015
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
	 * @version 28-07-2015
	 */
	private BigDecimal balance() {
		return Database.getTotalBalance(portfolio.getNumber());
	}

	/**
	 * Method to use the balance() outside the class
	 * 
	 * @return balance
	 * 
	 * @author Panagiotis Vakalis
	 * @version 28-07-2015
	 */
	public BigDecimal getBalance() {
		return balance();
	}

	/**
	 * Method which is used when a stock is sold. It updates the database and
	 * returns a message
	 * 
	 * @return "You have sold " + sharesCount + " of " + stock.getName() +
	 *         " stock.", if sharesCount > 0
	 * @return "You have not selected the amount of shares", otherwise
	 * @return "You are trying to sell amount of shares which you do not have.",
	 *         if the amount of shares which user tries t sell is higher than
	 *         the amount which holds
	 * 
	 * @author Panagiotis Vakalis
	 * @version 28-07-2015
	 */
	private String sellStock(int shares) {
		if (shares > 0) {
			if (lot.getBoughtShares() >= shares) {
				// convert date variable into string in order to parse it using
				// the valueOf method of Timestamp (sql) class
				/*
				 * Format the date variable so it can be parsed in a Timestamp
				 */
				String dateString = String.valueOf(lot.getDate());
				SimpleDateFormat dateFormated = new SimpleDateFormat(
						"yyy-MM-dd HH:mm:ss.SSS");
				try {
					Date date = dateFormated.parse(dateString);
					java.sql.Timestamp dateTimestamp = new java.sql.Timestamp(
							date.getTime());
					if (shares == lot.getBoughtShares()) {
						Database.useDeleteLot(portfolio.getNumber(),
								lot.getStockSymbol(), dateTimestamp);
					} else {
						int sharesNew = lot.getBoughtShares() - shares;
						Database.useUpdateSharesBoughtInLot(
								portfolio.getNumber(), lot.getStockSymbol(),
								dateTimestamp, sharesNew);
					}
				} catch (ParseException e) {
					e.printStackTrace();
				}
				Database.useInsertSell(lot.getStockSymbol(),
						portfolio.getNumber(), shares, lot.getCurrentPrice(),
						lot.getDate());
				Database.useUpdatePortfolioInvestedMoney(portfolio.getNumber(),
						(lot.getBoughtPrice() * shares) * (-1));
				Database.useUpdatePortfolioBalance(portfolio.getNumber(),
						BigDecimal.valueOf(lot.getCurrentPrice() * shares));
				return "You have sold " + shares + " of " + stockName()
						+ " stock.";
			} else {
				return "You are trying to sell amount of shares which you do not have.";
			}
		} else {
			return "You have not selected the amount of shares";
		}
	}

	/**
	 * Method which is used when a stock is sold. It updates the database and
	 * returns a message
	 * 
	 * @return "You have sold " + sharesCount + " of " + stock.getName() +
	 *         " stock.", if sharesCount > 0
	 * @return "You have not selected the amount of shares", otherwise
	 * @return "You are trying to sell amount of shares which you do not have.",
	 *         if the amount of shares which user tries t sell is higher than
	 *         the amount which holds
	 * 
	 * @author Panagiotis Vakalis
	 * @version 28-07-2015
	 */
	public String useSellStock(int shares) {
		return sellStock(shares);
	}

	/**
	 * Method to update the view
	 * 
	 * @param arg
	 * 
	 * @author Panagiotis Vakalis
	 * @version 28-07-2015
	 */
	private void update(Object arg) {
		setChanged();
		notifyObservers(arg);
	}

	/**
	 * Method which updates the invested money for the updated area
	 * 
	 * @param price
	 * @param shares
	 * 
	 * @author Panagiotis Vakalis
	 * @version 28-07-2015
	 */
	private void updateInvestedMoney(double price, int shares) {
		if (lot.getBoughtShares() >= shares) {
			newInvestedMoney = Database.useGetInvestedMoney(
					portfolio.getNumber()).subtract(
					new BigDecimal(price * shares));
			update(newInvestedMoney);
		}
	}

	/**
	 * Method which updates the invested money for the updated area
	 * 
	 * @param price
	 * @param shares
	 * 
	 * @author Panagiotis Vakalis
	 * @version 28-07-2015
	 */
	public void useUpdateInvestedMoney(double price, int shares) {
		updateInvestedMoney(price, shares);
	}

	/**
	 * Method which updates the balance for the updated area
	 * 
	 * @param price
	 * @param shares
	 * 
	 * @author Panagiotis Vakalis
	 * @version 28-07-2015
	 */
	private void updateBalance(double price, int shares) {
		if (lot.getBoughtShares() >= shares) {
			newBalance = Database.getTotalBalance(portfolio.getNumber()).add(
					new BigDecimal(price * shares));
			update(newBalance);
		}
	}

	/**
	 * Method which updates the balance for the updated area
	 * 
	 * @param price
	 * @param shares
	 * 
	 * @author Panagiotis Vakalis
	 * @version 28-07-2015
	 */
	public void useUpdateBalance(double price, int shares) {
		updateBalance(price, shares);
	}

	/**
	 * Method which updates the invested money area in the view part
	 * 
	 * @return future invested money
	 * 
	 * @author Panagiotis Vakalis
	 * @version 28-07-2015
	 */
	private String updateInvestedMoneyArea() {
		return View.currencyFormat(newInvestedMoney.setScale(2,
				BigDecimal.ROUND_DOWN));
	}

	/**
	 * Method which updates the invested money area in the view part
	 * 
	 * @return future invested money
	 * 
	 * @author Panagiotis Vakalis
	 * @version 28-07-2015
	 */
	public String useUpdateInvesteMoneyArea() {
		return updateInvestedMoneyArea();
	}

	/**
	 * Method which updates the balance area in the view part
	 * 
	 * @return future balance
	 * 
	 * @author Panagiotis Vakalis
	 * @version 28-07-2015
	 */
	private String updateBalanceArea() {
		return View.currencyFormat(newBalance
				.setScale(2, BigDecimal.ROUND_DOWN));
	}

	/**
	 * Method which updates the balance area in the view part
	 * 
	 * @return future balance
	 * 
	 * @author Panagiotis Vakalis
	 * @version 28-07-2015
	 */
	public String useUpdateBalanceArea() {
		return updateBalanceArea();
	}

	/**
	 * Method to change to lots view
	 * 
	 * @param lots
	 *            view
	 * 
	 * @author Panagiotis Vakalis
	 * @version 28-07-2015
	 */
	private void changeToLotsView(LotsView lotsView) {
		lotsModel = new LotsModel(super.getClient(), portfolio);
		super.getClient().useChangePanel(lotsView);
	}

	/**
	 * Method to change to lots view
	 * 
	 * @param lots
	 *            view
	 * 
	 * @author Panagiotis Vakalis
	 * @version 28-07-2015
	 */
	public void useChangeToLotsView(LotsView lotsView) {
		changeToLotsView(lotsView);
	}

	/**
	 * Method to get the portfolio
	 * 
	 * @return portfolio
	 * 
	 * @author Panagiotis Vakalis
	 * @version 28-07-2015
	 */
	public Portfolio getPortfolio() {
		return portfolio;
	}

	/**
	 * Method to update the balance when check is pressed
	 * 
	 * @param price
	 * @param shares
	 * 
	 * @author Panagiotis Vakalis
	 * @version 28-07-2015
	 */
	private void updateBalanceInCheck(double price, int shares) {
		newBalance = balance().add(
				new BigDecimal(price).setScale(2, BigDecimal.ROUND_DOWN)
						.multiply(new BigDecimal(shares)));
	}

	/**
	 * Method to update the invested money when check is pressed
	 * 
	 * @param price
	 * @param shares
	 * 
	 * @author Panagiotis Vakalis
	 * @version 28-07-2015
	 */
	private void updateInvestedMoneyInCheck(double price, int shares) {
		newInvestedMoney = investedMoney().subtract(
				new BigDecimal(lot.getBoughtPrice()).multiply(new BigDecimal(
						shares)));
	}

	/**
	 * Method to update the balance and the invested money when check is pressed
	 * 
	 * @param price
	 * @param shares
	 * 
	 * @author Panagiotis Vakalis
	 * @version 28-07-2015
	 */
	private void updateNewBalanceAndNewInvestedMoney(double price, int shares) {
		updateBalanceInCheck(price, shares);
		updateInvestedMoneyInCheck(price, shares);
		update(newBalance);
		update(newInvestedMoney);
	}

	/**
	 * Method to update the balance and the invested money when check is pressed
	 * 
	 * @param price
	 * @param shares
	 * 
	 * @author Panagiotis Vakalis
	 * @version 28-07-2015
	 */
	public void useUpdateNewBalanceAndNewInvestedMoney(double price, int shares) {
		updateNewBalanceAndNewInvestedMoney(price, shares);
	}
}
