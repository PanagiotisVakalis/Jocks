package pxv425;

import java.math.BigDecimal;
import java.util.ArrayList;
/**
 * Model part of the watchlist screen
 * 
 * @author Panagiotis Vakalis
 * @version 26-07-2015
 *
 */
public class WatchlistModel extends Model {
	
	private ArrayList<Stock> watches = new ArrayList<>();
	private String[][] watchesDetails;
	private Investor investor;
	private MainModel mainModel;
	private BuyModel buyModel;

	/**
	 * Constructor of the class
	 * @param client
	 * @param investor
	 * 
	 * @author Panagiotis Vakalis
	 * @version 26-07-2015
	 */
	public WatchlistModel(Client client, Investor investor) {
		super(client);
		this.investor = investor;
	}
	
	/**
	 * Method to get the investor out of the class
	 * @return investor
	 * 
	 * @author Panagiotis Vakalis
	 * @version 26-07-2015
	 */
	public Investor getInvestor(){
		return investor;
		
	}

	/**
	 * Method to retrieve the watchlist using the investor id
	 * @param investorId
	 * 
	 * @author Panagiotis Vakalis
	 * @version 26-07-2015
	 */
	private void retrieveWatchlist(){
		watches = Database.useRetrieveStocksWhichInvestorWatches(investor.getId());
	}
	
	/**
	 * Method to get the updated watchlist from the database
	 * 
	 * @author Panagiotis Vakalis
	 * @version 26-07-2015
	 */
	private void getUpdatedWatchlist(){
		watches = Database.useRetrieveStocksWhichInvestorWatches(investor.getId());
		update(watches);
	}
	
	/**
	 * Method to get the updated watchlist from the database
	 * 
	 * @author Panagiotis Vakalis
	 * @version 26-07-2015
	 */
	public void useGetUpdatedWatchlist(){
		getUpdatedWatchlist();
	}
	
	/**
	 * Method to use the retrieveWatchlist outside the class
	 * @param investorId
	 * @return list of the watches
	 * 
	 * @author Panagiotis Vakalis
	 * @version 26-07-2015
	 */
	public ArrayList<Stock> useRetrieveWatchlist(int investorId){
		retrieveWatchlist();
		return watches;
	}
	
	/**
	 * Method to retrieve the stocks' details which investor watches
	 * @param investorId
	 * @return two dimension array of strings conatining the details
	 * 
	 * @author Panagiotis Vakalis
	 * @version 26-07-2015
	 */
	private String[][] watchesDetails(){
		retrieveWatchlist();
		watchesDetails = new String[watches.size()][3];
		
		for (int i = 0; i < watches.size(); i++) {
			watchesDetails[i][0] = watches.get(i).getSymbol();
			watchesDetails[i][1] = watches.get(i).getName();
			watchesDetails[i][2] = View.currencyFormat(new BigDecimal(watches.get(i).getPrice()));
		}
		
		return watchesDetails;
	}
	
	/**
	 * Method to use the watchesDetails method outside the class
	 * @return two dimension array of strings containing the details
	 * 
	 * @author Panagiotis Vakalis
	 * @version 26-07-2015
	 */
	public String[][] useWatchesDetails(){
		return watchesDetails();
	}
	
	/**
	 * Method to change to main view
	 * 
	 * @param mainView
	 * @param portfolio
	 * 
	 * @author Panagiotis Vakalis
	 * @version 26-07-2015
	 */
	private void changeToMainView(MainView mainView, Portfolio portfolio) {
		mainModel = new MainModel(super.getClient(), portfolio);
		super.getClient().useChangePanel(mainView);
		System.out.println(mainModel.getPortfolio().getName());
	}

	/**
	 * Method to use the changeToMainView method outside the class
	 * 
	 * @param mainView
	 * @param portfolio
	 * 
	 * @author Panagiotis Vakalis
	 * @version 26-07-2015
	 */
	public void useChangeToMainView(MainView mainView, Portfolio portfolio) {
		changeToMainView(mainView, portfolio);
	}
	
	/**
	 * Method to change to buy view
	 * 
	 * @param buy view
	 * @param portfolio
	 * @param stock
	 * 
	 * @author Panagiotis Vakalis
	 * @version 26-07-2015
	 */
	private void changeToBuyView(BuyView buyView, Portfolio portfolio, Stock stock){
		buyModel = new BuyModel(super.getClient(), portfolio, stock);
		super.getClient().useChangePanel(buyView);
	}
	
	/**
	 * Method to change to buy view
	 * 
	 * @param buy view
	 * @param portfolio
	 * @param stock
	 * 
	 * @author Panagiotis Vakalis
	 * @version 26-07-2015
	 */
	public void useChangeToBuyView(BuyView buyView, Portfolio portfolio, Stock stock){
		changeToBuyView(buyView, portfolio, stock);
	}
	
	/**
	 * Method to select a stock from the list of stocks
	 * @param index
	 * @return stock
	 * 
	 * @author Panagiotis Vakalis
	 * @version 27-07-2015
	 */
	private Stock selectStock(int index){
		return watches.get(index);
	}
	
	/**
	 * Method to use the selectStock method outside the class
	 * @param index
	 * @return stock
	 * 
	 * @author Panagiotis Vakalis
	 * @version 27-07-2015
	 */
	public Stock useSelectStock(int index){
		return selectStock(index);
	}
	
	/**
	 * Method to delete a stock from the watchlist table
	 * @param stock symbol
	 * 
	 * @author Panagiotis Vakalis
	 * @version 26-07-2015
	 */
	private String deleteAWatch(String stockSymbol){
		Database.useDeleteWatch(investor.getId(), stockSymbol);
		return "You have deleted the " + stockSymbol + " from your watchlist";
	}
	
	/**
	 * Method to delete a stock from the watchlist table
	 * @param stock symbol
	 * 
	 * @author Panagiotis Vakalis
	 * @version 26-07-2015
	 */
	public String useDeleteAWatch(String stockSymbol){
		return deleteAWatch(stockSymbol);
	}
	
	/**
	 * Method to update the view
	 * @param arg
	 * 
	 * @author Panagiotis Vakalis
	 * @version 29-07-2015
	 */
	private void update(Object arg){
		setChanged();
		notifyObservers(arg);
	}
}
