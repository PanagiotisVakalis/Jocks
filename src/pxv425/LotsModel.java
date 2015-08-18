package pxv425;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Class which contains the model part of the lots screen
 * 
 * @author Panagiotis Vakalis
 * @version 23-07-2015
 *
 */
public class LotsModel extends Model {

	private Portfolio portfolio;
	private ArrayList<Lot> lots;
	private String stockSymbol;
	private double boughtPrice;
	private int boughtShares;
	private double amount;
	private double currentPrice;
	private double currentAmount;
	private double currentProfitLoss;
	private Calendar date;
	private String[][] lotsDetails;
	private MainModel mainModel;
	private SellModel sellModel;
	private SellView sellView;

	/**
	 * Method to get the portfolio outside of the class
	 * 
	 * @return portfolio
	 * 
	 * @author Panagiotis Vakalis
	 * @version 23-07-2015
	 */
	public Portfolio getPortfolio() {
		return portfolio;
	}

	/**
	 * Method to set the portfolio outside of the class
	 * 
	 * @param portfolio
	 * 
	 * @author Panagiotis Vakalis
	 * @version 23-07-2015
	 */
	public void setPortfolio(Portfolio portfolio) {
		this.portfolio = portfolio;
	}

	/**
	 * Method to get the lots outside of the class
	 * 
	 * @return lots
	 * 
	 * @author Panagiotis Vakalis
	 * @version 23-07-2015
	 */
	public ArrayList<Lot> getLots() {
		return lots;
	}

	/**
	 * Method to set the lots outside of the class
	 * 
	 * @param lots
	 * 
	 * @author Panagiotis Vakalis
	 * @version 23-07-2015
	 */
	public void setLots(ArrayList<Lot> lots) {
		this.lots = lots;
	}

	/**
	 * Method to get the stock symbol outside of the class
	 * 
	 * @return stock symbol
	 * 
	 * @author Panagiotis Vakalis
	 * @version 23-07-2015
	 */
	public String getStockSymbol() {
		return stockSymbol;
	}

	/**
	 * Method to set the stock symbol outside of the class
	 * 
	 * @param stock
	 *            symbol
	 * 
	 * @author Panagiotis Vakalis
	 * @version 23-07-2015
	 */
	public void setStockSymbol(String stockSymbol) {
		this.stockSymbol = stockSymbol;
	}

	/**
	 * Method to get the bought price outside of the class
	 * 
	 * @return bought price
	 * 
	 * @author Panagiotis Vakalis
	 * @version 23-07-2015
	 */
	public double getBoughtPrice() {
		return boughtPrice;
	}

	/**
	 * Method to set the bought price outside of the class
	 * 
	 * @param bought
	 *            price
	 * 
	 * @author Panagiotis Vakalis
	 * @version 23-07-2015
	 */
	public void setBoughtPrice(double boughtPrice) {
		this.boughtPrice = boughtPrice;
	}

	/**
	 * Method to get the shares outside of the class
	 * 
	 * @return shares
	 * 
	 * @author Panagiotis Vakalis
	 * @version 23-07-2015
	 */
	public int getBoughtShares() {
		return boughtShares;
	}

	/**
	 * Method to set the shares outside of the class
	 * 
	 * @param shares
	 * 
	 * @author Panagiotis Vakalis
	 * @version 23-07-2015
	 */
	public void setBoughtShares(int boughtShares) {
		this.boughtShares = boughtShares;
	}

	/**
	 * Method to get the amount outside of the class
	 * 
	 * @return amount
	 * 
	 * @author Panagiotis Vakalis
	 * @version 23-07-2015
	 */
	public double getAmount() {
		return amount;
	}

	/**
	 * Method to set the amount outside of the class
	 * 
	 * @param amount
	 * 
	 * @author Panagiotis Vakalis
	 * @version 23-07-2015
	 */
	public void setAmount(double amount) {
		this.amount = amount;
	}

	/**
	 * Method to get the current price outside of the class
	 * 
	 * @return current price
	 * 
	 * @author Panagiotis Vakalis
	 * @version 23-07-2015
	 */
	public double getCurrentPrice() {
		return currentPrice;
	}

	/**
	 * Method to set the current price outside of the class
	 * 
	 * @param current
	 *            price
	 * 
	 * @author Panagiotis Vakalis
	 * @version 23-07-2015
	 */
	public void setCurrentPrice(double currentPrice) {
		this.currentPrice = currentPrice;
	}

	/**
	 * Method to get the current amount outside of the class
	 * 
	 * @return current amount
	 * 
	 * @author Panagiotis Vakalis
	 * @version 23-07-2015
	 */
	public double getCurrentAmount() {
		return currentAmount;
	}

	/**
	 * Method to set the current amount outside of the class
	 * 
	 * @param current
	 *            amount
	 * 
	 * @author Panagiotis Vakalis
	 * @version 23-07-2015
	 */
	public void setCurrentAmount(double currentAmount) {
		this.currentAmount = currentAmount;
	}

	/**
	 * Method to get the current profit or loss outside of the class
	 * 
	 * @return current profit or loss
	 * 
	 * @author Panagiotis Vakalis
	 * @version 23-07-2015
	 */
	public double getCurrentProfitLoss() {
		return currentProfitLoss;
	}

	/**
	 * Method to set the current profit or loss outside of the class
	 * 
	 * @param current
	 *            profit or loss
	 * 
	 * @author Panagiotis Vakalis
	 * @version 23-07-2015
	 */
	public void setCurrentProfitLoss(double currentProfitLoss) {
		this.currentProfitLoss = currentProfitLoss;
	}

	/**
	 * Method to get the date outside of the class
	 * 
	 * @return date
	 * 
	 * @author Panagiotis Vakalis
	 * @version 23-07-2015
	 */
	public Calendar getDate() {
		return date;
	}

	/**
	 * Method to set the date outside of the class
	 * 
	 * @param date
	 * 
	 * @author Panagiotis Vakalis
	 * @version 23-07-2015
	 */
	public void setDate(Calendar date) {
		this.date = date;
	}

	/**
	 * Method to get the lots details outside of the class
	 * 
	 * @return lots details
	 * 
	 * @author Panagiotis Vakalis
	 * @version 23-07-2015
	 */
	public String[][] getLotsDetails() {
		/*
		 * This method is used for the table in lots view
		 */
		return lotsDetails;
	}

	/**
	 * Method to set the lots details outside of the class
	 * 
	 * @param lots
	 *            details
	 * 
	 * @author Panagiotis Vakalis
	 * @version 23-07-2015
	 */
	public void setLotsDetails(String[][] lotsDetails) {
		this.lotsDetails = lotsDetails;
	}

	/**
	 * Method to get the main model outside of the class
	 * 
	 * @return main model
	 * 
	 * @author Panagiotis Vakalis
	 * @version 23-07-2015
	 */
	public MainModel getMainModel() {
		return mainModel;
	}

	/**
	 * Method to set the main model outside of the class
	 * 
	 * @param main
	 *            model
	 * 
	 * @author Panagiotis Vakalis
	 * @version 23-07-2015
	 */
	public void setMainModel(MainModel mainModel) {
		this.mainModel = mainModel;
	}

	/**
	 * Constructor of the class
	 * 
	 * @param client
	 * @param portfolio
	 * 
	 * @author Panagiotis Vakalis
	 * @version 23-07-2015
	 */
	public LotsModel(Client client, Portfolio portfolio) {
		super(client);
		this.portfolio = portfolio;
	}

	/**
	 * Method to get the name of the portfolio
	 * 
	 * @return portfolio name
	 * 
	 * @author Panagiotis Vakalis
	 * @version 20-07-2015
	 */
	private String getPortfolioName() {
		return portfolio.getName();
	}

	/**
	 * Method to use the getPortfolioName() outside the class
	 * 
	 * @return portfolio name
	 * 
	 * @author Panagiotis Vakalis
	 * @version 23-07-2015
	 */
	public String useGetPortfolioName() {
		return getPortfolioName();
	}

	/**
	 * Method to get all the lots the portfolio has
	 * 
	 * @author Panagiotis Vakalis
	 * @version 23-07-2015
	 */
	private void getAllLots() {
		lots = Database.useGetLotsForASpecificPortfolio(portfolio.getNumber());
	}

	/**
	 * Method to use the getAllLots() outside the class
	 * 
	 * @author Panagiotis Vakalis
	 * @version 23-07-2015
	 */
	public void useGetAllLots() {
		getAllLots();
	}

	/**
	 * Method to get all the lots details
	 * 
	 * @return lots details
	 * 
	 * @author Panagiotis Vakalis
	 * @version 23-07-2015
	 */
	private String[][] lotsDetails() {
		/*
		 * This method assigns the corresponding details into an array of
		 * strings
		 */
		lotsDetails = new String[lots.size()][8];

		for (int i = 0; i < lots.size(); i++) {
			lotsDetails[i][0] = lots.get(i).getStockSymbol();
			lotsDetails[i][1] = View.currencyFormat(new BigDecimal(lots.get(i)
					.getBoughtPrice()));
			lotsDetails[i][2] = String.valueOf(lots.get(i).getBoughtShares());
			lotsDetails[i][3] = View.currencyFormat(new BigDecimal(lots.get(i)
					.getAmount()));
			lotsDetails[i][4] = View.currencyFormat(new BigDecimal(lots.get(i)
					.getCurrentPrice()));
			lotsDetails[i][5] = View.currencyFormat(new BigDecimal(lots.get(i)
					.getCurrentAmount()));
			lotsDetails[i][6] = View.currencyFormat(new BigDecimal(lots.get(i)
					.getCurrentProfitLoss()));
			lotsDetails[i][7] = String.valueOf(lots.get(i).getDate());
		}
		return lotsDetails;
	}

	/**
	 * Method to use the lotsDetails() outside the class
	 * 
	 * @return lots details
	 * 
	 * @author Panagiotis Vakalis
	 * @version 23-07-2015
	 */
	public String[][] useLotsDetails() {
		return lotsDetails();
	}

	/**
	 * Method to change to main view
	 * 
	 * @param mainView
	 * @param portfolio
	 * 
	 * @author Panagiotis Vakalis
	 * @version 23-07-2015
	 */
	private void changeToMainView(MainView mainView, Portfolio portfolio) {
		mainModel = new MainModel(super.getClient(), portfolio);
		super.getClient().useChangePanel(mainView);
	}

	/**
	 * Method to use the changeToMainView method outside the class
	 * 
	 * @param mainView
	 * @param portfolio
	 * 
	 * @author Panagiotis Vakalis
	 * @version 23-07-2015
	 */
	public void useChangeToMainView(MainView mainView, Portfolio portfolio) {
		changeToMainView(mainView, portfolio);
	}

	/**
	 * Method to change to sell view
	 * 
	 * @param sellview
	 * @param portfolio
	 * @param lot
	 * 
	 * @author Panagiotis Vakalis
	 * @version 23-07-2015
	 */
	private void changeToSellView(SellView sellView, Portfolio portfolio,
			Lot lot) {
		sellModel = new SellModel(super.getClient(), portfolio, lot);
		super.getClient().useChangePanel(sellView);
	}

	/**
	 * Method to change to sell view
	 * 
	 * @param sellview
	 * @param portfolio
	 * @param lot
	 * 
	 * @author Panagiotis Vakalis
	 * @version 23-07-2015
	 */
	public void useChangeToSellView(SellView sellView, Portfolio portfolio,
			Lot lot) {
		changeToSellView(sellView, portfolio, lot);
	}

	/**
	 * Method to select a stock from the list of stocks
	 * 
	 * @param index
	 * @return stock
	 * 
	 * @author Panagiotis Vakalis
	 * @version 27-07-2015
	 */
	private Lot selectLot(int index) {
		return lots.get(index);
	}

	/**
	 * Method to use the selectStock method outside the class
	 * 
	 * @param index
	 * @return stock
	 * 
	 * @author Panagiotis Vakalis
	 * @version 27-07-2015
	 */
	public Lot useSelectLot(int index) {
		return selectLot(index);
	}

	/**
	 * Method to initialize the sell model
	 * 
	 * @param lot
	 * 
	 * @author Panagiotis Vakalis
	 * @version 23-07-2015
	 */
	private void initializeSellModel(Lot lot) {
		/*
		 * This method is used when sell button is pressed in order to construct
		 * the sell model object
		 */
		sellModel = new SellModel(this.getClient(), portfolio, lot);
	}

	/**
	 * Method to get the sell model
	 * 
	 * @return sellModel
	 * 
	 * @author Panagiotis Vakalis
	 * @version 23-07-2015
	 */
	public SellModel getSellModel() {
		return sellModel;
	}

	/**
	 * Method to initialize the sell view
	 * 
	 * @param lot
	 * 
	 * @author Panagiotis Vakalis
	 * @version 23-07-2015
	 */
	private void initializeSellView(Lot lot) {
		/*
		 * This method is used when sell button is pressed in order to construct
		 * the sell view object
		 */
		initializeSellModel(lot);
		sellView = new SellView(sellModel);
	}

	/**
	 * Method to initialize the sell view outside the class
	 * 
	 * @param lot
	 * 
	 * @author Panagiotis Vakalis
	 * @version 23-07-2015
	 */
	public void useInitializeSellView(Lot lot) {
		initializeSellView(lot);
	}

	/**
	 * Method to get the sell view
	 * 
	 * @return sell view
	 * 
	 * @author Panagiotis Vakalis
	 * @version 23-07-2015
	 */
	public SellView getSellView() {
		return sellView;
	}
}
