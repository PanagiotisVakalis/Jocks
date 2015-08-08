package pxv425;

import java.sql.Timestamp;
import java.util.ArrayList;

import javax.xml.crypto.Data;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

/**
 * Class which contains the model part of the main screen
 * @author Panagiotis Vakalis
 * @version 20-07-2015	
 *
 */
public class MainModel extends Model {

	private ArrayList<Stock> stocks;
	private int count = 0;
	private String symbol;
	private String name;
	private double price;
	private String[][] stocksDetails;
	private Portfolio portfolio;
	private LotsModel lotsModel;
	private TradesModel tradesModel;
	private Investor investor;
	private WatchlistModel watchlistModel;
	private BuyModel buyModel;
	private IntroModel introModel;
	private MainModel mainModel;
	private JFreeChart chartPanel;
	private PortfolioModel portfolioModel;
	private ArrayList<Lot> allLots;
	private ArrayList<Stock> watches;
	
	/**
	 * Constructor of the class
	 * @param client
	 */
	public MainModel(Client client, Portfolio portfolio) {
		super(client);
		this.portfolio = portfolio;
		this.investor = super.getClient().getInvestor();
	}
	
	
	public Investor getInvestor() {
		return investor;
	}


	public void setInvestor(Investor investor) {
		this.investor = investor;
	}


	/**
	 * Method to use the portfolio outside the class
	 * @return portfolio
	 * 
	 * @author Panagiotis Vakalis
	 * @version 20-07-2015
	 */
	public Portfolio getPortfolio(){
		return portfolio;
	}

	/**
	 * Method to get all the stocks of the database
	 */
	private void getAllStocks(){
		stocks = Database.useGetAllStocks();
//		for(Stock stock: stocks){
//			System.out.println(stock.getSymbol());
//		}
	}
	
	/**
	 * Method to use getAllStocks() outside the class
	 */
	public void useGetAllStocks(){
		getAllStocks();
	}
	
//	private Stock nextStock(){
//		return stocks.get(++count);
//	}
	
	/**
	 * Method to get the stock details
	 * @return stock details
	 * 
	 * @author Panagiotis Vakalis
	 * @version 20-07-2015	
	 */
	private String[][] stocksDetails(){
		stocksDetails = new String[stocks.size()][3];
//		if(count < stocks.size()){
//			symbol = stocks.get(count).getSymbol();
//			name = stocks.get(count).getName();
//			price = stocks.get(count).getPrice();
			
			//change stock
//			count++;
//			stockDetails[0] = symbol;
//			stockDetails[1] = name;
//			stockDetails[2] = String.valueOf(price);
			
			for (int i = 0; i < stocks.size(); i++) {
//				stocksDetails[i][0] = stocks.get(i).getSymbol();
//				stocksDetails[i][1] = stocks.get(i).getName();
//				stocksDetails[i][2] = String.valueOf(stocks.get(i).getPrice());
//				for (int j = 0; j < 3; j++) {
					stocksDetails[i][0] = stocks.get(i).getSymbol();
					stocksDetails[i][1] = stocks.get(i).getName();
					stocksDetails[i][2] = String.valueOf(stocks.get(i).getPrice());
//				}
			}
			
//			for (int i = 0; i < stocksDetails.length; i++) {
//				for (int j = 0; j < 3; j++) {
//					System.out.println(stocksDetails[i][j]);
//				}
//			}
//		}
			return stocksDetails;
	}
	
	/**
	 * Method to use the stockDetails() oustide the class
	 * @return stockDeatails
	 * 
	 * @author Panagiotis Vakalis
	 * @version 20-07-2015	
	 */
	public String[][] useStocksDetails(){
		return stocksDetails();
	}

	/**
	 * Method to get the stocks
	 * @return stocks
	 * 
	 * @author Panagiotis Vakalis
	 * @version 20-07-2015	
	 */
	public ArrayList<Stock> getStocks() {
		return stocks;
	}

	/**
	 * Method to get the symbol
	 * @return symbol
	 * 
	 * @author Panagiotis Vakalis
	 * @version 20-07-2015	
	 */
	public String getSymbol() {
		return symbol;
	}

	/**
	 * Method to get the name
	 * @return name
	 * 
	 * @author Panagiotis Vakalis
	 * @version 20-07-2015	
	 */
	public String getName() {
		return name;
	}

	/**
	 * Method to get the price
	 * @return price
	 * 
	 * @author Panagiotis Vakalis
	 * @version 20-07-2015	
	 */
	public double getPrice() {
		return price;
	}
	
	/**
	 * Method to get the amount of portfolios
	 * @return amount of portfolios
	 * 
	 * @author Panagiotis Vakalis
	 * @version 20-07-2015	
	 */
	private String portfoliosAmount(){
		return String.valueOf(Database.getHowManyPortfolios());
	}
	
	/**
	 * Method to use the portfolioAmount() outside the class
	 * @return amount of portfolios
	 * 
	 * @author Panagiotis Vakalis
	 * @version 20-07-2015	
	 */
	public String usePortfoliosAmount(){
		return portfoliosAmount();
	}
	
	/**
	 * Method to get the total balance of the portfolios
	 * @return total balance
	 * 
	 * @author Panagiotis Vakalis
	 * @version 20-07-2015	
	 */
	private String totalBalance(){
		return String.valueOf(Database.getTotalBalance());
	}
	
	/**
	 * Method to use the totalBalance() outside the class
	 * @return total balance
	 * 
	 * @author Panagiotis Vakalis
	 * @version 20-07-2015	
	 */
	public String useTotalBalance(){
		return totalBalance();
	}
	
	/**
	 * Method to get the total invested money of the portfolios
	 * @return total invested money
	 * 
	 * @author Panagiotis Vakalis
	 * @version 20-07-2015	
	 */
	private String investedMoney(){
		return String.valueOf(Database.getTotalInvestedMoney());
	}
	
	/**
	 * Method to get the total profit / loss of the portfolios
	 * @return total profit / loss
	 * 
	 * @author Panagiotis Vakalis
	 * @version 20-07-2015	
	 */
	private String profitLoss(){
		return String.valueOf(Database.getTotalProfitLoss());
	}
	
	/**
	 * Method to use the totalProfitLoss() outside the class
	 * @return total profit / loss
	 * 
	 * @author Panagiotis Vakalis
	 * @version 20-07-2015	
	 */
	public String useProfitLoss(){
		return profitLoss();
	}
	
	/**
	 * Method to use the totalinvestedMoney() outside the class
	 * @return total invested money
	 * 
	 * @author Panagiotis Vakalis
	 * @version 20-07-2015	
	 */
	public String useInvestedMoney(){
		return investedMoney();
	}
	
	/**
	 * Method to get the portfolios details for an investor
	 * @param id
	 * 
	 * @author Panagiotis Vakalis
	 * @version 20-07-2015
	 */
	private void portfoliosDetails(int id){
		Database.useGetPortfoliosDetails(id);
	}
	
	/**
	 * Method to use the portfolioDetails method outside the class
	 * 
	 * @author Panagiotis Vakalis
	 * @version 20-07-2015	
	 */
	public void usePortfoliosDetails(){
		portfoliosDetails(super.getClient().getInvestor().getId());
	}
	
	/**
	 * Method which is called when the investor clicks the buy button
	 * in order to buy a stock
	 * @param index
	 * 
	 * @author Panagiotis Vakalis
	 * @version 20-07-2015	
	 */
	private void buyStock(int index){
		System.out.println("you have bought " + stocks.get(index).getSymbol());
	}
	
	/**
	 * Method to use the buyStock method outside the class
	 * @param index
	 * 
	 * @author Panagiotis Vakalis
	 * @version 20-07-2015	
	 */
	public void useBuyStock(int index){
		buyStock(index);
	}
	
	/**
	 * Method to change to lots view
	 * @param lotsView
	 * @param portfolio
	 * 
	 * @author Panagiotis Vakalis
	 * @version 23-07-2015	
	 */
	private void changeToLotsView(LotsView lotsView, Portfolio portfolio){
		lotsModel = new LotsModel(super.getClient(), portfolio);
		super.getClient().useChangePanel(lotsView);
	}
	
	/**
	 * Method to use the changeToLotsView outside the class
	 * @param lotsView
	 * @param portfolio
	 * 
	 * @author Panagiotis Vakalis
	 * @version 23-07-2015
	 */
	public void useChangeToLotsView(LotsView lotsView, Portfolio portfolio){
		changeToLotsView(lotsView, portfolio);
	}
	
	/**
	 * Method to change to trades view
	 * @param tradesView
	 * @param portfolio
	 * 
	 * @author Panagiotis Vakalis
	 * @version 26-07-2015	
	 */
	private void changeToTradesView(TradesView tradesView, Portfolio portfolio){
		tradesModel = new TradesModel(super.getClient(), portfolio);
		super.getClient().useChangePanel(tradesView);
	}
	
	/**
	 * Method to use the changeToTradesView outside the class
	 * @param tradesView
	 * @param portfolio
	 * 
	 * @author Panagiotis Vakalis
	 * @version 26-07-2015
	 */
	public void useChangeToTradesView(TradesView tradesView, Portfolio portfolio){
		changeToTradesView(tradesView, portfolio);
	}
	
	/**
	 * Method to change to watchlist view
	 * @param watchlistView
	 * @param investor
	 * 
	 * @author Panagiotis Vakalis
	 * @version 26-07-2015	
	 */
	private void changeToWatchilistView(WatchlistView watchlistView, Investor investor){
		watchlistModel = new WatchlistModel(super.getClient(), investor);
		super.getClient().useChangePanel(watchlistView);
	}
	
	/**
	 * Method to use the changeToWatchlistView outside the class
	 * @param watchlistView
	 * @param investor
	 * 
	 * @author Panagiotis Vakalis
	 * @version 26-07-2015
	 */
	public void useChangeToWatchilistView(WatchlistView watchlistView, Investor investor){
		changeToWatchilistView(watchlistView, investor);
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
		return stocks.get(index);
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
	 * Method to change to buy view
	 * @param buyView
	 * @param portfolio
	 * @param stock
	 * 
	 * @author Panagiotis Vakalis
	 * @version 27-07-2015
	 */
	private void changeToBuyView(BuyView buyView, Portfolio portfolio, Stock stock){
		buyModel = new BuyModel(super.getClient(), portfolio, stock);
		super.getClient().useChangePanel(buyView);
	}
	
	/**
	 * Method to use the changeToBuyView method outside the class
	 * @param buyView
	 * @param portfolio
	 * @param stock
	 * 
	 * @author Panagiotis Vakalis
	 * @version 27-07-2015
	 */
	public void useChangeToBuyView(BuyView buyView, Portfolio portfolio, Stock stock){
		changeToBuyView(buyView, portfolio, stock);
	}
	
	private void changeToPortfolioView(PortfolioView portfolioView){
		portfolioModel = new PortfolioModel(super.getClient());
		super.getClient().useChangePanel(portfolioView);
	}
	
	public void useChangeToPortfolioView(PortfolioView portfolioView){
		changeToPortfolioView(portfolioView);
	}
	
//	private void changeToIntroView(IntroView introView){
//		introModel = new IntroModel(super.getClient());
//		super.getClient().useChangePanel(introView);
//	}
//	
//	public void useChangeToIntroView(IntroView introView){
//		changeToIntroView(introView);
//	}
	
	/**
	 * Method to delete the frame and create a new Client
	 * 
	 * @author Panagiotis Vakalis
	 * @version 28-07-2015
	 */
	private void logout(){
//		super.getClient().useRemoveFrame();
//		this.setClient(new Client());
//		System.exit(0);
		this.getClient().dispose();
		super.setClient(new Client());
	}
	
	/**
	 * Method to use the logout()
	 * 
	 * @author Panagiotis Vakalis
	 * @version 28-07-2015
	 */
	public void useLogout(){
		logout();
	}
	
	/**
	 * Method which creates a new portfolio by inserting
	 * a new entry into the database
	 * @param name
	 * @param balance
	 * 
	 * @author Panagiotis Vakalis
	 * @version 28-07-2015
	 */
	private void createPortfolio(String name, double balance){
		Database.useCreateNewPortfolio(investor.getId(), name, balance);
	}
	
	/**
	 * Method to use the createPortfolio method outside the class
	 * @param name
	 * @param balance
	 * 
	 * @author Panagiotis Vakalis
	 * @version 28-07-2015
	 */
	public void useCreatePortfolio(String name, double balance){
		createPortfolio(name, balance);
	}
	
	/**
	 * Method which draws a line chart for the historical prices
	 * @param index
	 * @return line chart of historical prices
	 * 
	 * @author Panagiotis Vakalis
	 * @version 29-07-2015
	 */
	private JFreeChart drawChart(int index){
		
		ArrayList<Double> prices = new ArrayList<>();
		
		prices = Database.useGetHistoricalPrices(stocks.get(index).getSymbol());
		
		XYSeries series = new XYSeries("XYGraph");
		
		for(int i =1; i < prices.size(); i++){
			series.add(i, prices.get(i-1));
		}
		
		XYSeriesCollection dataCollection = new XYSeriesCollection(series);
		
		JFreeChart chart = ChartFactory.createXYLineChart("Price chart for the " + stocks.get(index).getSymbol(), null, "Prices", dataCollection, PlotOrientation.VERTICAL, false, true, false);
		
		update(chart);
		
		return chart;
	}
	
	/**
	 * Method to use the drawChart method outside the class
	 * @param index
	 * @return line chart of historical prices
	 * 
	 * @author Panagiotis Vakalis
	 * @version 29-07-2015
	 */
	public JFreeChart useDrawChart(int index){
		return drawChart(index);
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
		notifyObservers();
	}
	
	/**
	 * Method to send an email to the developer
	 * @param message
	 * 
	 * @author Panagiotis Vakalis
	 * @version 29-07-2015
	 */
	private void requestHelp(String message){
		Email email = new Email(investor.getFirstName(), investor.getLastName(), investor.getEmail());
		email.useSendEmail(message);
	}
	
	/**
	 * Method to use the requestHelp method outside the class
	 * @param message
	 * @return a string
	 * 
	 * @author Panagiotis Vakalis
	 * @version 29-07-2015
	 */
	public String useRequestHelp(String message){
		requestHelp(message);
		return "Your message has been sent";
	}
	
	/**
	 * Method to calculate the average prices for the given days
	 * @param prices
	 * @param days
	 * @return an arraylist containing the average prices
	 * 
	 * @author Panagiotis Vakalis
	 * @version 30-07-2015
	 */
	private ArrayList<Double> calculateAveragePrices(ArrayList<Double> prices, int days){
		
		//Create an arraylist
		ArrayList<Double> averagePrices = new ArrayList<>();
		
		/*
		 * for i equals 0 and while the result of the addition between
		 * the i and the days subtracted by 1 is smaller than the size
		 * of the prices list.
		 * 
		 * I have used the i+days-1<prices.size() because I do not want 
		 * to exit the limit of the prices list
		 */
		for(int i = 0; i + days-1 < prices.size(); i++){
			int day = 0;
			double value = 0.0;
			
			while(day < days){
				value += prices.get(i + day);
				day++;
			}
			averagePrices.add(value / days);
		}
		
		return averagePrices;
	}
	
	/**
	 * Method to draw the cross of the simple moving average of stock prices
	 * using two different time periods
	 * @param index
	 * @param daysShort
	 * @param daysLong
	 * @return chart of the two SMA
	 * 
	 * @author Panagiotis Vakalis
	 * @version 30-07-2015
	 */
	private JFreeChart drawCrossSMA(int index, int daysShort, int daysLong){
		ArrayList<Double> averagePricesForShort = new ArrayList<>();
		ArrayList<Double> averagePricesForLong = new ArrayList<>();
		ArrayList<Double> prices = new ArrayList<>();
		ArrayList<Timestamp> dates = new ArrayList<>();
		
		prices = Database.useGetHistoricalPrices(stocks.get(index).getSymbol());
		dates = Database.getDatesForHistoricalPrices(stocks.get(index).getSymbol());
		
		averagePricesForShort = calculateAveragePrices(prices, daysShort);
		averagePricesForLong = calculateAveragePrices(prices, daysLong);
		prices = Database.useGetHistoricalPrices(stocks.get(index).getSymbol());
		
		XYSeries seriesShort = new XYSeries("Short");
		XYSeries seriesLong = new XYSeries("Long");
		XYSeries price = new XYSeries("Price");
		
		for(int i = 1; i < averagePricesForShort.size(); i++){
			seriesShort.add(i, averagePricesForShort.get(i-1));
		}
		
		for(int i = 1; i < averagePricesForLong.size(); i++){
			seriesLong.add(i, averagePricesForLong.get(i-1));
		}
		
		for(int i = 1; i < prices.size(); i++){
			price.add(i, prices.get(i-1));
		}
		
		XYSeriesCollection dataCollection = new XYSeriesCollection();
		dataCollection.addSeries(seriesShort);
		dataCollection.addSeries(seriesLong);
		dataCollection.addSeries(price);
		
		JFreeChart chart = ChartFactory.createXYLineChart("Cross SMA for the " + stocks.get(index).getSymbol(), null, "Prices", dataCollection, PlotOrientation.VERTICAL, true, true, false);
		
		return chart;
	}
	
	/**
	 * Method to use the drawCrossSMA method outside the class
	 * @param index
	 * @param daysShort
	 * @param daysLong
	 * @return chart containing the two SMA
	 * 
	 * @author Panagiotis Vakalis
	 * @version 30-07-2015
	 */
	public JFreeChart useDrawCrossSMA(int index, int daysShort, int daysLong){
		return drawCrossSMA(index, daysShort, daysLong);
	}
	
	private String addToWatchlist(String stockSymbol){
		boolean found = false;;
		watches = Database.useRetrieveStocksWhichInvestorWatches(investor.getId());
		for(Stock stock : watches){
			if (stock.getSymbol().equals(stockSymbol)) {
				found = true;
				break;
			}
		}
//		Database.useInsertWatch(investor.getId(), stockSymbol);
		if(found == true){
			return "You have already added this stock into your watchlist";
		}
		else{
			Database.useInsertWatch(investor.getId(), stockSymbol);
			return "You have inserted " + stockSymbol + " into your watchlist";
		}
	}
	
	public String useAddToWatchlist(Stock stock){
		return addToWatchlist(stock.getSymbol());
	}
	
	private double getAllLotsProfitLoss(Portfolio portfolio){
		double profitLossSum = 0;
		//Retrieve the lots
		allLots = Database.useGetLotsForASpecificPortfolio(portfolio.getNumber());
		
		for(Lot lot: allLots){
			profitLossSum += lot.getCurrentProfitLoss();
		}
		
		return profitLossSum;
	}
	
	public double useGetAllLotsProfitLoss(Portfolio portfolio){
		return getAllLotsProfitLoss(portfolio);
	}
}
