package pxv425;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;

import org.jfree.chart.ChartFactory;

import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.PlotOrientation;

import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

/**
 * Class which contains the model part of the main screen
 * 
 * @author Panagiotis Vakalis
 * @version 20-07-2015
 *
 */
public class MainModel extends Model {

	private ArrayList<Stock> stocks;
	private String symbol;
	private String name;
	private double price;
	private String[][] stocksDetails;
	private Portfolio portfolio;
	private LotsModel lotsModel;
	private LotsView lotsView;
	private TradesModel tradesModel;
	private Investor investor;
	private WatchlistModel watchlistModel;
	private WatchlistView watchlistView;
	private BuyModel buyModel;
	private BuyView buyView;
	private IntroModel introModel;
	private MainModel mainModel;
	private PortfolioModel portfolioModel;
	private ArrayList<Lot> allLots;
	private ArrayList<Stock> watches;
	private double profitLossSum;
	private BigDecimal totalInvestedMoney;
	private BigDecimal availableBalance;

	/**
	 * Constructor of the class
	 * 
	 * @param client
	 * 
	 * @author Panagiotis Vakalis
	 * @version 20-07-2015
	 */
	public MainModel(Client client, Portfolio portfolio) {
		super(client);
		this.portfolio = portfolio;
		this.investor = super.getClient().getInvestor();
	}

	/**
	 * Method to get the investor outside the class
	 * 
	 * @return investor
	 * 
	 * @author Panagiotis Vakalis
	 * @version 20-07-2015
	 */
	public Investor getInvestor() {
		return investor;
	}

	/**
	 * Method to set the investor outside the class
	 * 
	 * @param investor
	 * 
	 * @author Panagiotis Vakalis
	 * @version 20-07-2015
	 */
	public void setInvestor(Investor investor) {
		this.investor = investor;
	}

	/**
	 * Method to use the portfolio outside the class
	 * 
	 * @return portfolio
	 * 
	 * @author Panagiotis Vakalis
	 * @version 20-07-2015
	 */
	public Portfolio getPortfolio() {
		return portfolio;
	}

	/**
	 * Method to get all the stocks of the database
	 * 
	 * @author Panagiotis Vakalis
	 * @version 20-07-2015
	 */
	private void getAllStocks() {
		stocks = Database.useGetAllStocks();
	}

	/**
	 * Method to get all the stocks of the database
	 * 
	 * @author Panagiotis Vakalis
	 * @version 20-07-2015
	 */
	public void useGetAllStocks() {
		getAllStocks();
	}

	/**
	 * Method to get the stock details
	 * 
	 * @return stock details
	 * 
	 * @author Panagiotis Vakalis
	 * @version 20-07-2015
	 */
	private String[][] stocksDetails() {
		stocksDetails = new String[stocks.size()][3];
		for (int i = 0; i < stocks.size(); i++) {
			stocksDetails[i][0] = stocks.get(i).getSymbol();
			stocksDetails[i][1] = stocks.get(i).getName();
			stocksDetails[i][2] = "£"
					+ String.valueOf(stocks.get(i).getPrice());

		}
		return stocksDetails;
	}

	/**
	 * Method to use the stockDetails() oustide the class
	 * 
	 * @return stockDeatails
	 * 
	 * @author Panagiotis Vakalis
	 * @version 20-07-2015
	 */
	public String[][] useStocksDetails() {
		return stocksDetails();
	}

	/**
	 * Method to get the stocks
	 * 
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
	 * 
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
	 * 
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
	 * 
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
	 * 
	 * @return amount of portfolios
	 * 
	 * @author Panagiotis Vakalis
	 * @version 20-07-2015
	 */
	private String portfoliosAmount() {
		return String.valueOf(Database.getHowManyPortfolios());
	}

	/**
	 * Method to use the portfolioAmount() outside the class
	 * 
	 * @return amount of portfolios
	 * 
	 * @author Panagiotis Vakalis
	 * @version 20-07-2015
	 */
	public String usePortfoliosAmount() {
		return portfoliosAmount();
	}

	/**
	 * Method to get the total balance of the portfolios
	 * 
	 * @return total balance
	 * 
	 * @author Panagiotis Vakalis
	 * @version 20-07-2015
	 */
	private String totalBalance() {
		return String.valueOf(View.currencyFormat(Database
				.getTotalBalance(portfolio.getNumber())));
	}

	/**
	 * Method to use the totalBalance() outside the class
	 * 
	 * @return total balance
	 * 
	 * @author Panagiotis Vakalis
	 * @version 20-07-2015
	 */
	public String useTotalBalance() {
		return totalBalance();
	}

	/**
	 * Method to get the total invested money of the portfolios
	 * 
	 * @return total invested money
	 * 
	 * @author Panagiotis Vakalis
	 * @version 20-07-2015
	 */
	private String investedMoney() {
		return String.valueOf(View.currencyFormat(Database
				.useRetrieveInvestedMoney(portfolio.getNumber())));
	}

	/**
	 * Method to update the total invested money
	 * 
	 * @author Panagiotis Vakalis
	 * @version 20-07-2015
	 */
	private void updateTotalInvestedMoney() {
		totalInvestedMoney = Database.useRetrieveInvestedMoney(portfolio
				.getNumber());
		update(totalInvestedMoney);
	}

	/**
	 * Method to update the total invested money
	 * 
	 * @author Panagiotis Vakalis
	 * @version 20-07-2015
	 */
	public void useUpdateTotalInvestedMoney() {
		updateTotalInvestedMoney();
	}

	/**
	 * Method to update the total invested money area on view
	 * 
	 * @author Panagiotis Vakalis
	 * @version 20-07-2015
	 */
	public String getUpdatedTotalInvestedMoney() {
		return View.currencyFormat(totalInvestedMoney);
	}

	/**
	 * Method to update the available balance
	 * 
	 * @author Panagiotis Vakalis
	 * @version 20-07-2015
	 */
	private void updateAvailableBalance() {
		availableBalance = Database.getTotalBalance(portfolio.getNumber());
		update(availableBalance);
	}

	/**
	 * Method to update the available balance
	 * 
	 * @author Panagiotis Vakalis
	 * @version 20-07-2015
	 */
	public void useUpdateAvailableBalance() {
		updateAvailableBalance();
	}

	/**
	 * Method to update the available balance area on view
	 * 
	 * @author Panagiotis Vakalis
	 * @version 20-07-2015
	 */
	public String getUpdatedAvailableBalance() {
		return View.currencyFormat(availableBalance);
	}

	/**
	 * Method to get the total profit / loss of the portfolios
	 * 
	 * @return total profit / loss
	 * 
	 * @author Panagiotis Vakalis
	 * @version 20-07-2015
	 */
	private String profitLoss() {
		return String.valueOf(Database.getTotalProfitLoss());
	}

	/**
	 * Method to use the totalProfitLoss() outside the class
	 * 
	 * @return total profit / loss
	 * 
	 * @author Panagiotis Vakalis
	 * @version 20-07-2015
	 */
	public String useProfitLoss() {
		return profitLoss();
	}

	/**
	 * Method to use the totalinvestedMoney() outside the class
	 * 
	 * @return total invested money
	 * 
	 * @author Panagiotis Vakalis
	 * @version 20-07-2015
	 */
	public String useInvestedMoney() {
		return investedMoney();
	}

	/**
	 * Method to get the portfolios details for an investor
	 * 
	 * @param id
	 * 
	 * @author Panagiotis Vakalis
	 * @version 20-07-2015
	 */
	private void portfoliosDetails(int id) {
		Database.useGetPortfoliosDetails(id);
	}

	/**
	 * Method to use the portfolioDetails method outside the class
	 * 
	 * @author Panagiotis Vakalis
	 * @version 20-07-2015
	 */
	public void usePortfoliosDetails() {
		portfoliosDetails(super.getClient().getInvestor().getId());
	}

	/**
	 * Method which is called when the investor clicks the buy button in order
	 * to buy a stock
	 * 
	 * @param index
	 * 
	 * @author Panagiotis Vakalis
	 * @version 20-07-2015
	 */
	private void buyStock(int index) {
		System.out.println("you have bought " + stocks.get(index).getSymbol());
	}

	/**
	 * Method to use the buyStock method outside the class
	 * 
	 * @param index
	 * 
	 * @author Panagiotis Vakalis
	 * @version 20-07-2015
	 */
	public void useBuyStock(int index) {
		buyStock(index);
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
	private Stock selectStock(int index) {
		return stocks.get(index);
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
	public Stock useSelectStock(int index) {
		return selectStock(index);
	}

	/**
	 * Method to change to portfolio view
	 * 
	 * @param portfolioView
	 * 
	 * @author Panagiotis Vakalis
	 * @version 26-07-2015
	 */
	private void changeToPortfolioView(PortfolioView portfolioView) {
		portfolioModel = new PortfolioModel(super.getClient());
		super.getClient().useChangePanel(portfolioView);
	}

	/**
	 * Method to change to portfolio view
	 * 
	 * @param portfolioView
	 * 
	 * @author Panagiotis Vakalis
	 * @version 26-07-2015
	 */
	public void useChangeToPortfolioView(PortfolioView portfolioView) {
		changeToPortfolioView(portfolioView);
	}

	/**
	 * Method for logout
	 * 
	 * @author Panagiotis Vakalis
	 * @version 28-07-2015
	 */
	private void logout() {
		/*
		 * Delete the frame and create a new client
		 */
		this.getClient().dispose();
		super.setClient(new Client());
	}

	/**
	 * Method for logout
	 * 
	 * @author Panagiotis Vakalis
	 * @version 28-07-2015
	 */
	public void useLogout() {
		logout();
	}

	/**
	 * Method which creates a new portfolio by inserting a new entry into the
	 * database
	 * 
	 * @param name
	 * @param balance
	 * 
	 * @author Panagiotis Vakalis
	 * @version 28-07-2015
	 */
	private void createPortfolio(String name, double balance) {
		Database.useCreateNewPortfolio(investor.getEmail(), name, balance);
	}

	/**
	 * Method which if the portfolio name exists
	 * 
	 * @param name
	 * 
	 * @author Panagiotis Vakalis
	 * @version 28-07-2015
	 */
	private boolean nameExists(String name) {
		return Database.usePortfolioNameExists(investor.getEmail(), name);
	}

	/**
	 * Method which if the portfolio name exists
	 * 
	 * @param name
	 * 
	 * @author Panagiotis Vakalis
	 * @version 28-07-2015
	 */
	public boolean useNameExists(String name) {
		return nameExists(name);
	}

	/**
	 * Method which creates a new portfolio by inserting a new entry into the
	 * database
	 * 
	 * @param name
	 * @param balance
	 * 
	 * @author Panagiotis Vakalis
	 * @version 28-07-2015
	 */
	public void useCreatePortfolio(String name, double balance) {
		createPortfolio(name, balance);
	}

	/**
	 * Method which draws a line chart for the historical prices
	 * 
	 * @param index
	 * @return line chart of historical prices
	 * 
	 * @author Panagiotis Vakalis
	 * @version 29-07-2015
	 */
	private JFreeChart drawChart(int index) {

		ArrayList<Double> prices = new ArrayList<>();

		prices = Database.useGetHistoricalPrices(stocks.get(index).getSymbol());

		XYSeries series = new XYSeries("XYGraph");

		for (int i = 1; i < prices.size(); i++) {
			series.add(i, prices.get(i - 1));
		}

		XYSeriesCollection dataCollection = new XYSeriesCollection(series);

		JFreeChart chart = ChartFactory.createXYLineChart(
				"Price chart for the " + stocks.get(index).getSymbol(), null,
				"Prices", dataCollection, PlotOrientation.VERTICAL, false,
				true, false);
		// Delete the x axis
		ValueAxis xAxis = chart.getXYPlot().getDomainAxis();
		xAxis.setVisible(false);
		// Delete the y axis
		ValueAxis yAxis = chart.getXYPlot().getRangeAxis();
		yAxis.setVisible(false);
		update(chart);

		return chart;
	}

	/**
	 * Method which draws a line chart for the historical prices
	 * 
	 * @param index
	 * @return line chart of historical prices
	 * 
	 * @author Panagiotis Vakalis
	 * @version 29-07-2015
	 */
	public JFreeChart useDrawChart(int index) {
		return drawChart(index);
	}

	/**
	 * Method to update the view
	 * 
	 * @param arg
	 * 
	 * @author Panagiotis Vakalis
	 * @version 29-07-2015
	 */
	private void update(Object arg) {
		setChanged();
		notifyObservers(arg);
	}

	/**
	 * Method to send an email to the developer
	 * 
	 * @param message
	 * 
	 * @author Panagiotis Vakalis
	 * @version 29-07-2015
	 */
	private void requestHelp(String message) {
		Email email = new Email(investor.getFirstName(),
				investor.getLastName(), investor.getEmail());
		email.useSendEmail(message);
	}

	/**
	 * Method to send an email to the developer
	 * 
	 * @param message
	 * 
	 * @author Panagiotis Vakalis
	 * @version 29-07-2015
	 */
	public String useRequestHelp(String message) {
		requestHelp(message);
		return "Your message has been sent";
	}

	/**
	 * Method to calculate the average prices for the given days
	 * 
	 * @param prices
	 * @param days
	 * @return an arraylist containing the average prices
	 * 
	 * @author Panagiotis Vakalis
	 * @version 30-07-2015
	 */
	private ArrayList<Double> calculateAveragePrices(ArrayList<Double> prices,
			int days) {

		// Create an arraylist
		ArrayList<Double> averagePrices = new ArrayList<>();

		/*
		 * for i equals 0 and while the result of the addition between the i and
		 * the days subtracted by 1 is smaller than the size of the prices list.
		 * 
		 * I have used the i+days-1<prices.size() because I do not want to exit
		 * the limit of the prices list
		 */
		for (int i = 0; i + days - 1 < prices.size(); i++) {
			int day = 0;
			double value = 0.0;

			while (day < days) {
				value += prices.get(i + day);
				day++;
			}
			averagePrices.add(value / days);
		}

		return averagePrices;
	}

	public ArrayList<Double> useCalculateAveragePrices(
			ArrayList<Double> prices, int days) {
		return calculateAveragePrices(prices, days);
	}

	/**
	 * Method to draw the cross of the simple moving average of stock prices
	 * using two different time periods
	 * 
	 * @param index
	 * @param daysShort
	 * @param daysLong
	 * @return chart of the two SMA
	 * 
	 * @author Panagiotis Vakalis
	 * @version 30-07-2015
	 */
	private JFreeChart drawCrossSMA(int index, int daysShort, int daysLong) {
		ArrayList<Double> averagePricesForShort = new ArrayList<>();
		ArrayList<Double> averagePricesForLong = new ArrayList<>();
		ArrayList<Double> prices = new ArrayList<>();

		prices = Database.useGetHistoricalPrices(stocks.get(index).getSymbol());

		averagePricesForShort = calculateAveragePrices(prices, daysShort);
		averagePricesForLong = calculateAveragePrices(prices, daysLong);
		prices = Database.useGetHistoricalPrices(stocks.get(index).getSymbol());

		XYSeries seriesShort = new XYSeries("Short");
		XYSeries seriesLong = new XYSeries("Long");
		XYSeries price = new XYSeries("Price");

		for (int i = 1; i < averagePricesForShort.size(); i++) {
			seriesShort.add(i, averagePricesForShort.get(i - 1));
		}

		for (int i = 1; i < averagePricesForLong.size(); i++) {
			seriesLong.add(i, averagePricesForLong.get(i - 1));
		}

		for (int i = 1; i < prices.size(); i++) {
			price.add(i, prices.get(i - 1));
		}

		XYSeriesCollection dataCollection = new XYSeriesCollection();
		dataCollection.addSeries(seriesShort);
		dataCollection.addSeries(seriesLong);
		dataCollection.addSeries(price);

		JFreeChart chart = ChartFactory.createXYLineChart("Cross SMA for the "
				+ stocks.get(index).getSymbol(), null, "Prices",
				dataCollection, PlotOrientation.VERTICAL, true, true, false);

		ValueAxis xAxis = chart.getXYPlot().getDomainAxis();
		xAxis.setVisible(false);
		ValueAxis yAxis = chart.getXYPlot().getRangeAxis();
		yAxis.setVisible(false);

		return chart;
	}

	/**
	 * Method to draw the cross of the simple moving average of stock prices
	 * using two different time periods
	 * 
	 * @param index
	 * @param daysShort
	 * @param daysLong
	 * @return chart of the two SMA
	 * 
	 * @author Panagiotis Vakalis
	 * @version 30-07-2015
	 */
	public JFreeChart useDrawCrossSMA(int index, int daysShort, int daysLong) {
		return drawCrossSMA(index, daysShort, daysLong);
	}

	/**
	 * Method to add a stock to watchlist
	 * 
	 * @param stock
	 *            symbol
	 * @return You have already added this stock into your watchlist, if the
	 *         stock symbol already exists
	 * @return "You have inserted " + stockSymbol + " into your watchlist", if
	 *         the stock symbol does not already exist
	 * 
	 * @author Panagiotis Vakalis
	 * @version 30-07-2015
	 */
	private String addToWatchlist(String stockSymbol) {
		boolean found = false;
		;
		watches = Database.useRetrieveStocksWhichInvestorWatches(investor
				.getId());
		for (Stock stock : watches) {
			if (stock.getSymbol().equals(stockSymbol)) {
				found = true;
				break;
			}
		}
		if (found == true) {
			return "You have already added this stock into your watchlist";
		} else {
			Database.useInsertWatch(investor.getId(), stockSymbol);
			return "You have inserted " + stockSymbol + " into your watchlist";
		}
	}

	/**
	 * Method to add a stock to watchlist
	 * 
	 * @param stock
	 *            symbol
	 * @return You have already added this stock into your watchlist, if the
	 *         stock symbol already exists
	 * @return "You have inserted " + stockSymbol + " into your watchlist", if
	 *         the stock symbol does not already exist
	 * 
	 * @author Panagiotis Vakalis
	 * @version 30-07-2015
	 */
	public String useAddToWatchlist(Stock stock) {
		return addToWatchlist(stock.getSymbol());
	}

	/**
	 * Method to calculate the profit or loss of all the lots
	 * 
	 * @param portfolio
	 * 
	 * @author Panagiotis Vakalis
	 * @version 30-07-2015
	 */
	private void getAllLotsProfitLoss(Portfolio portfolio) {
		profitLossSum = 0;
		// Retrieve the lots
		allLots = Database.useGetLotsForASpecificPortfolio(portfolio
				.getNumber());

		for (Lot lot : allLots) {
			profitLossSum += lot.getCurrentProfitLoss();
		}
	}

	/**
	 * Method to update the profit or loss of all the lots
	 * 
	 * @param portfolio
	 * 
	 * @author Panagiotis Vakalis
	 * @version 30-07-2015
	 */
	private void updateProfitLossSum(Portfolio portfolio) {
		getAllLotsProfitLoss(portfolio);
		update(profitLossSum);
	}

	/**
	 * Method to update the profit or loss of all the lots
	 * 
	 * @param portfolio
	 * 
	 * @author Panagiotis Vakalis
	 * @version 30-07-2015
	 */
	public void useUpdateProfitLossSum(Portfolio portfolio) {
		updateProfitLossSum(portfolio);
	}

	/**
	 * Method to update the profit or loss of all the lots area in view
	 * 
	 * @param portfolio
	 * 
	 * @author Panagiotis Vakalis
	 * @version 30-07-2015
	 */
	public String getUpdatedProfitLossSum(Portfolio portfolio) {
		return View.currencyFormat(new BigDecimal(profitLossSum));
	}

	/**
	 * Method to calculate the profit or loss of all the lots
	 * 
	 * @param portfolio
	 * 
	 * @author Panagiotis Vakalis
	 * @version 30-07-2015
	 */
	public double useGetAllLotsProfitLoss(Portfolio portfolio) {
		getAllLotsProfitLoss(portfolio);
		return Math.round(profitLossSum);
	}

	/**
	 * Method to initialize the buy model
	 * 
	 * @param stock
	 * 
	 * @author Panagiotis Vakalis
	 * @version 30-07-2015
	 */
	private void initializeBuyModel(Stock stock) {
		buyModel = new BuyModel(this.getClient(), portfolio, stock);
	}

	/**
	 * Method to initialize the buy model
	 * 
	 * @param stock
	 * 
	 * @author Panagiotis Vakalis
	 * @version 30-07-2015
	 */
	public void useInitializeBuyModel(Stock stock) {
		initializeBuyModel(stock);
	}

	/**
	 * Method to get the buy model outside the class
	 * 
	 * @return stock
	 * 
	 * @author Panagiotis Vakalis
	 * @version 30-07-2015
	 */
	public BuyModel getBuyModel() {
		return buyModel;
	}

	/**
	 * Method to initialize the buy view
	 * 
	 * @param stock
	 * 
	 * @author Panagiotis Vakalis
	 * @version 30-07-2015
	 */
	private void initializeBuyView(Stock stock) {
		initializeBuyModel(stock);
		buyView = new BuyView(buyModel);
	}

	/**
	 * Method to initialize the buy view
	 * 
	 * @param stock
	 * 
	 * @author Panagiotis Vakalis
	 * @version 30-07-2015
	 */
	public void useInitializeBuyView(Stock stock) {
		initializeBuyView(stock);
	}

	/**
	 * Method to get the buy view outside the class
	 * 
	 * @return stock
	 * 
	 * @author Panagiotis Vakalis
	 * @version 30-07-2015
	 */
	public BuyView getBuyView() {
		return buyView;
	}

	/**
	 * Method to initialize the lots model
	 * 
	 * @author Panagiotis Vakalis
	 * @version 30-07-2015
	 */
	private void initializeLotsModel() {
		lotsModel = new LotsModel(this.getClient(), portfolio);
	}

	/**
	 * Method to initialize the lots view
	 * 
	 * @author Panagiotis Vakalis
	 * @version 30-07-2015
	 */
	private void initializeLotsView() {
		initializeLotsModel();
		lotsView = new LotsView(lotsModel);
	}

	/**
	 * Method to initialize the lots view
	 * 
	 * @author Panagiotis Vakalis
	 * @version 30-07-2015
	 */
	public void useInitializeLotsView() {
		initializeLotsView();
	}

	/**
	 * Method to get the lots model outside the class
	 * 
	 * @return lotsModel
	 * 
	 * @author Panagiotis Vakalis
	 * @version 30-07-2015
	 */
	public LotsModel getLotsModel() {
		return lotsModel;
	}

	/**
	 * Method to get the lots view outside the class
	 * 
	 * @return lotsView
	 * 
	 * @author Panagiotis Vakalis
	 * @version 30-07-2015
	 */
	public LotsView getLotsView() {
		return lotsView;
	}

	/**
	 * Method to initialize the watchlist model
	 * 
	 * @author Panagiotis Vakalis
	 * @version 30-07-2015
	 */
	private void initializeWatchlistModel() {
		watchlistModel = new WatchlistModel(getClient(), getClient()
				.getInvestor());
	}

	/**
	 * Method to initialize the watchlist view
	 * 
	 * @author Panagiotis Vakalis
	 * @version 30-07-2015
	 */
	private void initializeWatchlistView() {
		initializeWatchlistModel();
		watchlistView = new WatchlistView(watchlistModel);
	}

	/**
	 * Method to initialize the watchlist view
	 * 
	 * @author Panagiotis Vakalis
	 * @version 30-07-2015
	 */
	public void useInitializeWatchlistView() {
		initializeWatchlistView();
	}

	/**
	 * Method to initialize the watchlist model
	 * 
	 * @author Panagiotis Vakalis
	 * @version 30-07-2015
	 */
	public WatchlistModel getWatchlistModel() {
		return watchlistModel;
	}

	/**
	 * Method to get the watchlist view outside the class
	 * 
	 * @author Panagiotis Vakalis
	 * @version 30-07-2015
	 */
	public WatchlistView getWatchlistView() {
		return watchlistView;
	}
}
