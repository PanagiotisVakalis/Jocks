package pxv425;

import java.math.BigDecimal;
import java.util.ArrayList;
/**
 * Model part of the trades screen
 * @author Panagiotis Vakalis
 * @version 24-07-2015
 *
 */
public class TradesModel extends Model {

	private ArrayList<Buy> buys;
	private String[][] buysDetails;
	private Portfolio portfolio;
	private ArrayList<Sell> sells;
	private String[][] sellsDetails;
	private MainModel mainModel;
	
	/**
	 * Constructor of the class
	 * @param client
	 * @param portfolio
	 * 
	 * @author Panagiotis Vakalis
	 * @version 24-07-2015
	 */
	public TradesModel(Client client, Portfolio portfolio){
		super(client);
		this.portfolio = portfolio;
	}
	
	/**
	 * Method to get the buys outside the class
	 * @return buys
	 * 
	 * @author Panagiotis Vakalis
	 * @version 24-07-2015
	 */
	public ArrayList<Buy> getBuys() {
		return buys;
	}

	/**
	 * Method to set the buys outside the class
	 * @param buys
	 * 
	 * @author Panagiotis Vakalis
	 * @version 24-07-2015
	 */
	public void setBuys(ArrayList<Buy> buys) {
		this.buys = buys;
	}

	/**
	 * Method to get the buys details outside the class
	 * @return buys details
	 * 
	 * @author Panagiotis Vakalis
	 * @version 24-07-2015
	 */
	public String[][] getBuysDetails() {
		return buysDetails;
	}

	/**
	 * Method to set the buys details outside the class
	 * @param buysDetails
	 * 
	 * @author Panagiotis Vakalis
	 * @version 24-07-2015
	 */
	public void setBuysDetails(String[][] buysDetails) {
		this.buysDetails = buysDetails;
	}

	/**
	 * Method to get the portfolio outside the class
	 * @return portfolio
	 * 
	 * @author Panagiotis Vakalis
	 * @version 24-07-2015
	 */
	public Portfolio getPortfolio() {
		return portfolio;
	}

	/**
	 * Method to set the portfolio outside the class
	 * @param portfolio
	 * 
	 * @author Panagiotis Vakalis
	 * @version 24-07-2015
	 */
	public void setPortfolio(Portfolio portfolio) {
		this.portfolio = portfolio;
	}

	/**
	 * Method to get the sells outside the class
	 * @return sells
	 * 
	 * @author Panagiotis Vakalis
	 * @version 24-07-2015
	 */
	public ArrayList<Sell> getSells() {
		return sells;
	}

	/**
	 * Method to set the sells outside the class
	 * @param sells
	 * 
	 * @author Panagiotis Vakalis
	 * @version 24-07-2015
	 */
	public void setSells(ArrayList<Sell> sells) {
		this.sells = sells;
	}

	/**
	 * Method to get the sells details outside the class
	 * @return sells details
	 * 
	 * @author Panagiotis Vakalis
	 * @version 24-07-2015
	 */
	public String[][] getSellsDetails() {
		return sellsDetails;
	}

	/**
	 * Method to set the sells details outside the class
	 * @param sellsDetails
	 * 
	 * @author Panagiotis Vakalis
	 * @version 24-07-2015
	 */
	public void setSellsDetails(String[][] sellsDetails) {
		this.sellsDetails = sellsDetails;
	}

	/**
	 * Method to get the main model outside the class
	 * @return main model
	 * 
	 * @author Panagiotis Vakalis
	 * @version 24-07-2015
	 */
	public MainModel getMainModel() {
		return mainModel;
	}

	/**
	 * Method to set the main model outside the class
	 * @param mainModel
	 * 
	 * @author Panagiotis Vakalis
	 * @version 24-07-2015
	 */
	public void setMainModel(MainModel mainModel) {
		this.mainModel = mainModel;
	}

	/**
	 * Method to get all the buys for a specific portfolio number from the database
	 * 
	 * @author Panagiotis Vakalis
	 * @version 24-07-2015
	 */
	private void getAllBuys(){
		buys = Database.useRetrieveBuys(portfolio.getNumber());
	}
	
	/**
	 * Method to use the getAllBuys() outside the class
	 * 
	 * @author Panagiotis Vakalis
	 * @version 24-07-2015
	 */
	public void useGetAllBuys(){
		getAllBuys();
	}
	
	/**
	 * Method which stores the buys details into a two dimension array of strings
	 * @return buys details
	 * 
	 * @author Panagiotis Vakalis
	 * @version 24-07-2015
	 */
	private String[][] buysDetails(){
		buysDetails = new String[buys.size()][5];
		
		for (int i = 0; i < buys.size(); i++) {
			System.out.println(String.valueOf(buys.get(i).getDate().getTime()));
			buysDetails[i][0] = buys.get(i).getStockSymbol();
			buysDetails[i][1] = View.currencyFormat(new BigDecimal(buys.get(i).getBoughtPrice()));
			buysDetails[i][2] = String.valueOf(buys.get(i).getBoughtShares());
			buysDetails[i][3] = View.currencyFormat(new BigDecimal(buys.get(i).getBoughtAmount()));
			buysDetails[i][4] = String.valueOf(buys.get(i).getDate());
		}
		
		return buysDetails;
	}
	
	/**
	 * Method to use the buysDetails() outside the class
	 * @return buys details
	 * 
	 * @author Panagiotis Vakalis
	 * @version 24-07-2015
	 */
	public String[][] useBuysDetails(){
		return buysDetails();
	}
	
	/**
	 * Method to get all the sells for a specific portfolio number from the database
	 * 
	 * @author Panagiotis Vakalis
	 * @version 24-07-2015
	 */
	private void getAllSells(){
		sells = Database.useRetrieveSells(portfolio.getNumber());
	}
	
	/**
	 * Method to use the getAllSells() outside the class
	 * 
	 * @author Panagiotis Vakalis
	 * @version 24-07-2015
	 */
	public void useGetAllSells(){
		getAllSells();
	}
	
	/**
	 * Method which stores the sells details into a two dimension array of strings
	 * @return buys details
	 * 
	 * @author Panagiotis Vakalis
	 * @version 24-07-2015
	 */
	private String[][] sellsDetails(){
		sellsDetails = new String[sells.size()][6];
		
		for (int i = 0; i < sells.size(); i++) {
			sellsDetails[i][0] = sells.get(i).getStockSymbol();
			sellsDetails[i][1] = View.currencyFormat(new BigDecimal(sells.get(i).getSoldPrice()));
			sellsDetails[i][2] = String.valueOf(sells.get(i).getSoldShares());
			sellsDetails[i][3] = View.currencyFormat(new BigDecimal(sells.get(i).getSoldAmount()));
			sellsDetails[i][4] = View.currencyFormat(new BigDecimal(sells.get(i).getProfitLoss()));
			sellsDetails[i][5] = String.valueOf(sells.get(i).getDate());
		}
		
		return sellsDetails;
	}
	
	/**
	 * Method to use the buysDetails() outside the class
	 * @return buys details
	 * 
	 * @author Panagiotis Vakalis
	 * @version 24-07-2015
	 */
	public String[][] useSellsDetails(){
		return sellsDetails();
	}
	
	/**
	 * Method to change to main view
	 * @param mainView
	 * @param portfolio
	 * 
	 * @author Panagiotis Vakalis
	 * @version 26-07-2015	
	 */
	private void changeToMainView(MainView mainView, Portfolio portfolio){
		mainModel = new MainModel(super.getClient(), portfolio);
		super.getClient().useChangePanel(mainView);
	}
	
	/**
	 * Method to use the changeToMainView method outside the class
	 * @param mainView
	 * @param portfolio
	 * 
	 * @author Panagiotis Vakalis
	 * @version 26-07-2015	
	 */
	public void useChangeToMainView(MainView mainView, Portfolio portfolio){
		changeToMainView(mainView, portfolio);
	}
	
}
