package pxv425;

import java.math.BigDecimal;
import java.security.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.crypto.Data;
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
	private MainModel mainModel;
	private LotsModel lotsModel;

	/**
	 * Constructor of the class
	 * @param client
	 * @param portfolio
	 * @param lot
	 * 
	 * @author Panagiotis Vakalis
	 * @version 28-07-2015
	 */
	public SellModel(Client client, Portfolio portfolio, Lot lot){
		super(client);
		this.portfolio = portfolio;
		this.lot = lot;
	}
	
	/**
	 * Method to retrieve the stock symbol of a lot
	 * @return stock symbol
	 * 
	 * @author Panagiotis Vakalis
	 * @version 28-07-2015
	 */
	private String stockSymbol(){
		return lot.getStockSymbol();
	}
	
	/**
	 * Method to get the stock symbol of a lot outside
	 * the class
	 * @return stock symbol
	 * 
	 * @author Panagiotis Vakalis
	 * @version 28-07-2015
	 */
	public String getStockSymbol(){
		return stockSymbol();
	}
	
	/**
	 * Method which retrieves the stock name
	 * @return stock name
	 * 
	 * @author Panagiotis Vakalis
	 * @version 28-07-2015
	 */
	private String stockName(){
		return Database.useGetStockNameUsingSymbol(lot.getStockSymbol());
	}
	
	/**
	 * Method to use the stockName() outside the class
	 * @return stock name
	 * 
	 * @author Panagiotis Vakalis
	 * @version 28-07-2015
	 */
	public String getStockName(){
		return stockName();
	}
	
	/**
	 * Method which retrieves the stock price
	 * @return stock price
	 * 
	 * @author Panagiotis Vakalis
	 * @version 28-07-2015
	 */
	private double stockPrice(){
		return lot.getCurrentPrice();
	}
	
	/**
	 * Method to use the stockPrice() outside the class
	 * @return stock price
	 * 
	 * @author Panagiotis Vakalis
	 * @version 28-07-2015
	 */
	public double getStockPrice(){
		return stockPrice();
	}
	
//	/**
//	 * Method which updates the counter in the view part
//	 * 
//	 * @author Panagiotis Vakalis
//	 * @version 28-07-2015
//	 */
//	public void sharesCount(){
//		update(sharesCount);
//	}
	
	/**
	 * Method to get the shares count
	 * @return shares count
	 * 
	 * @author Panagiotis Vakalis
	 * @version 28-07-2015
	 */
	public String getSharesCount(){
		return String.valueOf(sharesCount);
	}
	
	/**
	 * Method to retrieve the invested money of the portfolio
	 * @return invested money
	 * 
	 * @author Panagiotis Vakalis
	 * @version 28-07-2015
	 */
	private BigDecimal investedMoney(){
//		return portfolio.getInvestedMoney();
		return Database.useGetInvestedMoney(portfolio.getNumber());
	}
	
	/**
	 * Method to use the investeMoney() outside the class
	 * @return invested money
	 * 
	 * @author Panagiotis Vakalis
	 * @version 28-07-2015
	 */
	public BigDecimal getInvestedMoney(){
		return investedMoney();
	}
	
	/**
	 * Method to retrieve the balance of the portfolio
	 * @return balance
	 * 
	 * @author Panagiotis Vakalis
	 * @version 28-07-2015
	 */
	private BigDecimal balance(){
//		return portfolio.getBalance();
		return Database.getTotalBalance(portfolio.getNumber());
	}
	
	/**
	 * Method to use the balance() outside the class
	 * @return balance
	 * 
	 * @author Panagiotis Vakalis
	 * @version 28-07-2015
	 */
	public BigDecimal getBalance(){
		return balance();
	}
	
//	/**
//	 * Method which increases the sharesCount variable and updates the view
//	 * 
//	 * @author Panagiotis Vakalis
//	 * @version 28-07-2015
//	 */
//	private void increaseSharesCount(){
//		sharesCount++;
//		update(sharesCount);
//	}
//	
//	/**
//	 * Method to use the increaseSharesCount() outside the class
//	 * 
//	 * @author Panagiotis Vakalis
//	 * @version 28-07-2015
//	 */
//	public void useIncreaseSharesCount(){
//		increaseSharesCount();
//	}
	
//	/**
//	 * Method which decreases the sharesCount variable and updates the view
//	 * 
//	 * @author Panagiotis Vakalis
//	 * @version 28-07-2015
//	 */
//	private void decreaseSharesCount(){
//		if(sharesCount > 0){
//			sharesCount--;
//			update(sharesCount);
//		}
//	}
//	
//	/**
//	 * Method to use the decreaseSharesCount() outside the class
//	 * 
//	 * @author Panagiotis Vakalis
//	 * @version 28-07-2015
//	 */
//	public void useDecreaseSharesCount(){
//		decreaseSharesCount();
//	}
	
	/**
	 * Method which is used when a stock is sold.
	 * It updates the database and returns a message
	 * @return "You have sold " + sharesCount + " of " + stock.getName() + " stock.", if sharesCount > 0
	 * @return "You have not selected the amount of shares", otherwise
	 * 
	 * @author Panagiotis Vakalis
	 * @version 28-07-2015
	 */
	private String sellStock(int shares){
		if(shares > 0){
			if(lot.getBoughtShares() >= shares){
//				Database.insertLot(portfolio.getNumber(), lot.getStockSymbol(), sharesCount);
				//convert date variable into string in order to parse it using the valueOf method of Timestamp (sql) class
				/*
				 * Format the date variable so it can be
				 * parsed in a Timestamp
				 */
				String dateString = String.valueOf(lot.getDate());
//				SimpleDateFormat dateFormated = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
				SimpleDateFormat dateFormated = new SimpleDateFormat("yyy-MM-dd HH:mm:ss.SSS");
				try {
					Date date = dateFormated.parse(dateString);
					java.sql.Timestamp dateTimestamp = new java.sql.Timestamp(date.getTime());
//					Database.deleteLot(portfolio.getNumber(), lot.getStockSymbol(), dateTimestamp);
					if(shares == lot.getBoughtShares()){
						Database.useDeleteLot(portfolio.getNumber(), lot.getStockSymbol(), dateTimestamp);
					}
					else{
						int sharesNew = lot.getBoughtShares() - shares;
						Database.useUpdateSharesBoughtInLot(portfolio.getNumber(), lot.getStockSymbol(), dateTimestamp, sharesNew);
					}
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
//				Database.deleteLot(portfolio.getNumber(), lot.getStockSymbol(), java.sql.Timestamp.valueOf(dateString));
//				Database.insertBuy(stock.getSymbol(), portfolio.getNumber(), sharesCount);
//				Database.insertSell(lot.getStockSymbol(), portfolio.getNumber(), sharesCount, lot.getCurrentPrice());
				Database.useInsertSell(lot.getStockSymbol(), portfolio.getNumber(), shares, lot.getCurrentPrice(), lot.getDate());
//				Database.updatePortfolioInvestedMoney(portfolio.getNumber(), stock.getPrice() * sharesCount);
				Database.updatePortfolioInvestedMoney(portfolio.getNumber(), (lot.getBoughtPrice() * shares) * (-1));
//				Database.updatePortfolioBalance(portfolio.getNumber(), (stock.getPrice() * sharesCount) * (-1));
//				Database.updatePortfolioBalance(portfolio.getNumber(), lot.getCurrentPrice() * sharesCount);
				Database.updatePortfolioBalance(portfolio.getNumber(), BigDecimal.valueOf(lot.getCurrentPrice() * shares));
				return "You have sold " + shares + " of " + stockName() + " stock.";
			}
			else{
				return "You are trying to sell amount of shares which you do not have.";
			}
		}
		else{
			return "You have not selected the amount of shares";
		}
	}
	
	/**
	 * Method to use the sellStock() outside the class
	 * @return "You have sold " + sharesCount + " of " + stock.getName() + " stock.", if sharesCount > 0
	 * @return "You have not selected the amount of shares", otherwise
	 * 
	 * @author Panagiotis Vakalis
	 * @version 28-07-2015
	 */
	public String useSellStock(int shares){
		return sellStock(shares);
	}
	
	/**
	 * Method to update the view
	 * @param arg
	 * 
	 * @author Panagiotis Vakalis
	 * @version 28-07-2015
	 */
	private void update(Object arg){
		setChanged();
		notifyObservers(arg);
	}
	
	private void updateInvestedMoney(double price, int shares){
		if(lot.getBoughtShares() >= shares){
//			newInvestedMoney = portfolio.getInvestedMoney().subtract(new BigDecimal(price * shares));
			newInvestedMoney = Database.useGetInvestedMoney(portfolio.getNumber()).subtract(new BigDecimal(price * shares));
			update(newInvestedMoney);
		}
	}
	
	public void useUpdateInvestedMoney(double price, int shares){
		updateInvestedMoney(price, shares);
	}
	
	private void updateBalance(double price, int shares){
		if(lot.getBoughtShares() >= shares){
//			newBalance = portfolio.getBalance().add(new BigDecimal(price * shares));
			newBalance = Database.getTotalBalance(portfolio.getNumber()).add(new BigDecimal(price * shares));
			update(newBalance);
		}
	}
	
	public void useUpdateBalance(double price, int shares){
		updateBalance(price, shares);
	}
	
	private String updateInvestedMoneyArea(){
		return String.valueOf(newInvestedMoney);
	}
	
	public String useUpdateInvesteMoneyArea(){
		return updateInvestedMoneyArea();
	}
	
	private String updateBalanceArea(){
		return String.valueOf(newBalance);
	}
	
	public String useUpdateBalanceArea(){
		return updateBalanceArea();
	}
	
	private void changeToLotsView(LotsView lotsView){
		lotsModel = new LotsModel(super.getClient(), portfolio);
		super.getClient().useChangePanel(lotsView);
	}
	
	public void useChangeToLotsView(LotsView lotsView){
		changeToLotsView(lotsView);
	}
	
	public Portfolio getPortfolio(){
		return portfolio;
	}
}
