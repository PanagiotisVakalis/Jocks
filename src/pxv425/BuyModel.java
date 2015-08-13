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
//	private int sharesCount = 0;
	private BigDecimal newInvestedMoney;
	private BigDecimal newBalance;
	private BigDecimal balance;
	private MainModel mainModel;

	/**
	 * Constructor of the class
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
	 * @return stock symbol
	 * 
	 * @author Panagiotis Vakalis
	 * @version 27-07-2015
	 */
	private String stockSymbol(){
		return stock.getSymbol();
	}
	
	/**
	 * Method to use the stockSymbol() outside the class
	 * @return stock symbol
	 */
	public String getStockSymbol(){
		return stockSymbol();
	}
	
	/**
	 * Method which retrieves the stock name
	 * @return stock name
	 * 
	 * @author Panagiotis Vakalis
	 * @version 27-07-2015
	 */
	private String stockName(){
		return stock.getName();
	}
	
	/**
	 * Method to use the stockName() outside the class
	 * @return stock name
	 * 
	 * @author Panagiotis Vakalis
	 * @version 27-07-2015
	 */
	public String getStockName(){
		return stockName();
	}
	
	/**
	 * Method which retrieves the stock price
	 * @return stock price
	 * 
	 * @author Panagiotis Vakalis
	 * @version 27-07-2015
	 */
	private double stockPrice(){
		return stock.getPrice();
	}
	
	/**
	 * Method to use the stockPrice() outside the class
	 * @return stock price
	 * 
	 * @author Panagiotis Vakalis
	 * @version 27-07-2015
	 */
	public double getStockPrice(){
		return stockPrice();
	}
	
//	/**
//	 * Method which updates the counter in the view part
//	 * 
//	 * @author Panagiotis Vakalis
//	 * @version 27-07-2015
//	 */
//	public void sharesCount(){
//		update(sharesCount);
//	}
	
//	/**
//	 * Method to get the shares count
//	 * @return shares count
//	 * 
//	 * @author Panagiotis Vakalis
//	 * @version 27-07-2015
//	 */
//	public String getSharesCount(){
//		return String.valueOf(sharesCount);
//	}
	
	/**
	 * Method to retrieve the invested money of the portfolio
	 * @return invested money
	 * 
	 * @author Panagiotis Vakalis
	 * @version 27-07-2015
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
	 * @version 27-07-2015
	 */
	public BigDecimal getInvestedMoney(){
		return investedMoney();
	}
	
	/**
	 * Method to retrieve the balance of the portfolio
	 * @return balance
	 * 
	 * @author Panagiotis Vakalis
	 * @version 27-07-2015
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
	 * @version 27-07-2015
	 */
	public BigDecimal getBalance(){
		return balance();
	}
	
//	/**
//	 * Method which increases the sharesCount variable and updates the view
//	 * 
//	 * @author Panagiotis Vakalis
//	 * @version 27-07-2015
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
//	 * @version 27-07-2015
//	 */
//	public void useIncreaseSharesCount(){
//		increaseSharesCount();
//	}
	
//	/**
//	 * Method which decreases the sharesCount variable and updates the view
//	 * 
//	 * @author Panagiotis Vakalis
//	 * @version 27-07-2015
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
//	 * @version 27-07-2015
//	 */
//	public void useDecreaseSharesCount(){
//		decreaseSharesCount();
//	}
	
	
	private void updateInvestedMoney(double price, BigInteger shares){
//		newInvestedMoney = portfolio.getInvestedMoney() - (price * shares);
		newInvestedMoney = investedMoney().add(new BigDecimal(price).multiply(new BigDecimal(shares)));
//		update(newInvestedMoney);
	}
	
	public void useUpdateInvestedMoney(double price, BigInteger shares){
		updateInvestedMoney(price, shares);
	}
	
	private String updateInvestedMoneyArea(){
//		return String.valueOf((portfolio.getInvestedMoney()).add(new BigDecimal(price * shares)));
		return String.valueOf(newInvestedMoney.setScale(2, BigDecimal.ROUND_DOWN));
	}
	
	public String useUpdateInvesteMoneyArea(){
		return updateInvestedMoneyArea();
	}
	
	private String updateBalanceArea(){
		return String.valueOf(newBalance.setScale(2, BigDecimal.ROUND_DOWN));
	}
	
	public String useUpdateBalanceArea(){
		return updateBalanceArea();
	}
	
	/**
	 * Method which is used when a stock is bought.
	 * It updates the database and returns a message
	 * @return "You have bought " + sharesCount + " of " + stock.getName() + " stock.", if sharesCount > 0
	 * @return "You have not selected the amount of shares", otherwise
	 * 
	 * @author Panagiotis Vakalis
	 * @version 27-07-2015
	 */
	private String buyStock(int shares){
		if(shares > 0){
//			if(balance.compareTo(new BigDecimal((stock.getPrice() * shares)).compareTo(new BigDecimal(0))){
//			if(balance.compareTo(new BigDecimal(stock.getPrice() * shares)) == new BigDecimal(1))
			int compare = portfolio.getBalance().compareTo(new BigDecimal(stock.getPrice() * shares));
			if(compare == 0 || compare == 1){
				Database.insertLot(portfolio.getNumber(), stock.getSymbol(), shares);
				Database.insertBuy(stock.getSymbol(), portfolio.getNumber(), shares);
				Database.updatePortfolioInvestedMoney(portfolio.getNumber(), stock.getPrice() * shares);
//				Database.updatePortfolioBalance(portfolio.getNumber(), (stock.getPrice() * sharesCount) * (-1));
				Database.updatePortfolioBalance(portfolio.getNumber(), BigDecimal.valueOf((stock.getPrice() * shares) * (-1)));
				return "You have bought " + shares + " of " + stock.getName() + " stock.";
			}
			else{
				return "You do not have enough balance";
			}
		}
		else{
			return "You have not selected the amount of shares";
		}
	}
	
	/**
	 * Method to use the buyStock() outside the class
	 * @return "You have bought " + sharesCount + " of " + stock.getName() + " stock.", if sharesCount > 0
	 * @return "You have not selected the amount of shares", otherwise
	 * 
	 * @author Panagiotis Vakalis
	 * @version 27-07-2015
	 */
	public String useBuyStock(int shares){
		return buyStock(shares);
	}
	
	/**
	 * Method to update the view
	 * @param arg
	 * 
	 * @author Panagiotis Vakalis
	 * @version 27-07-2015
	 */
	private void update(Object arg){
		setChanged();
		notifyObservers(arg);
	}
	
	private void changeToMainView(MainView mainView){
		mainModel = new MainModel(super.getClient(), portfolio);
		super.getClient().useChangePanel(mainView);
	}
	
	public void useChangeToMainView(MainView mainView){
		changeToMainView(mainView);
	}
	
	public Portfolio getPortfolio(){
		return portfolio;
	}
	
	private void updateBalance(double price, BigInteger shares){
		newBalance = balance().subtract(new BigDecimal(price).setScale(2, BigDecimal.ROUND_DOWN).multiply(new BigDecimal(shares)));
//		newBalance = balance().subtract(new BigDecimal(price).multiply(new BigDecimal(shares)));
//		update(newBalance);
	}
	
	public void useUpdateBalance(double price, BigInteger shares){
		updateBalance(price, shares);
	}
	
	private void updateNewBalanceAndNewInvestedMoney(double price, BigInteger shares){
		updateBalance(price, shares);
		updateInvestedMoney(price, shares);
		update(balance);
		update(newInvestedMoney);
	}
	
	public void useUpdateNewBalanceAndNewInvestedMoney(double price, BigInteger shares){
		updateNewBalanceAndNewInvestedMoney(price, shares);
	}
}
