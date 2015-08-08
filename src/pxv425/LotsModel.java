package pxv425;

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
	
	public Portfolio getPortfolio() {
		return portfolio;
	}

	public void setPortfolio(Portfolio portfolio) {
		this.portfolio = portfolio;
	}

	public ArrayList<Lot> getLots() {
		return lots;
	}

	public void setLots(ArrayList<Lot> lots) {
		this.lots = lots;
	}

	public String getStockSymbol() {
		return stockSymbol;
	}

	public void setStockSymbol(String stockSymbol) {
		this.stockSymbol = stockSymbol;
	}

	public double getBoughtPrice() {
		return boughtPrice;
	}

	public void setBoughtPrice(double boughtPrice) {
		this.boughtPrice = boughtPrice;
	}

	public int getBoughtShares() {
		return boughtShares;
	}

	public void setBoughtShares(int boughtShares) {
		this.boughtShares = boughtShares;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public double getCurrentPrice() {
		return currentPrice;
	}

	public void setCurrentPrice(double currentPrice) {
		this.currentPrice = currentPrice;
	}

	public double getCurrentAmount() {
		return currentAmount;
	}

	public void setCurrentAmount(double currentAmount) {
		this.currentAmount = currentAmount;
	}

	public double getCurrentProfitLoss() {
		return currentProfitLoss;
	}

	public void setCurrentProfitLoss(double currentProfitLoss) {
		this.currentProfitLoss = currentProfitLoss;
	}

	public Calendar getDate() {
		return date;
	}

	public void setDate(Calendar date) {
		this.date = date;
	}

	public String[][] getLotsDetails() {
		return lotsDetails;
	}

	public void setLotsDetails(String[][] lotsDetails) {
		this.lotsDetails = lotsDetails;
	}

	public MainModel getMainModel() {
		return mainModel;
	}

	public void setMainModel(MainModel mainModel) {
		this.mainModel = mainModel;
	}

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
		lotsDetails = new String[lots.size()][8];

		for (int i = 0; i < lots.size(); i++) {
			lotsDetails[i][0] = lots.get(i).getStockSymbol();
			lotsDetails[i][1] = String.valueOf(lots.get(i).getBoughtPrice());
			lotsDetails[i][2] = String.valueOf(lots.get(i).getBoughtShares());
			lotsDetails[i][3] = String.valueOf(lots.get(i).getAmount());
			lotsDetails[i][4] = String.valueOf(lots.get(i).getCurrentPrice());
			lotsDetails[i][5] = String.valueOf(lots.get(i).getCurrentAmount());
			lotsDetails[i][6] = String.valueOf(lots.get(i)
					.getCurrentProfitLoss());
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
	
	private void changeToSellView(SellView sellView, Portfolio portfolio, Lot lot){
		sellModel = new SellModel(super.getClient(), portfolio, lot);
		super.getClient().useChangePanel(sellView);
	}
	
	public void useChangeToSellView(SellView sellView, Portfolio portfolio, Lot lot){
		changeToSellView(sellView, portfolio, lot);
	}
	
	/**
	 * Method to select a stock from the list of stocks
	 * @param index
	 * @return stock
	 * 
	 * @author Panagiotis Vakalis
	 * @version 27-07-2015
	 */
	private Lot selectLot(int index){
		return lots.get(index);
	}
	
	/**
	 * Method to use the selectStock method outside the class
	 * @param index
	 * @return stock
	 * 
	 * @author Panagiotis Vakalis
	 * @version 27-07-2015
	 */
	public Lot useSelectLot(int index){
		return selectLot(index);
	}
}
