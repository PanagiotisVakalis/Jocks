package pxv425;

import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * Class which contains the model part for the portfolio
 * 
 * @author Panagiotis Vakalis
 * @version 18-07-2015
 *
 */
public class PortfolioModel extends Model {

	private ArrayList<Portfolio> portfolios;
	private Portfolio[] portfoliosArray;
	private String[] portfolioNames;
	private MainModel mainModel;
	private LotsModel lotsModel;
	private Investor investor;
	private ArrayList<Lot> allLots;
	private BigDecimal totalReturn;
	private BigDecimal balanceNow;
	private BigDecimal initialBalance;
	private BigDecimal totalWithdraws;
	private BigDecimal totalDeposits;
	private String[][] lotsDetails;
	private BigDecimal investedMoney;
	private double lotsProfitLoss;
	private BigDecimal totalBalance;

	/*
	 * I have used big decimal because double can reach the limit
	 */
	private BigDecimal portfolioBalance;

	/**
	 * Constructor of the class
	 * 
	 * @param client
	 * 
	 * @author Panagiotis Vakalis
	 * @version 18-07-2015
	 */
	public PortfolioModel(Client client) {
		super(client);
		this.investor = super.getClient().getInvestor();
		/*
		 * Initialize the portfolios, the array of portfolios and get the
		 * portfolios names
		 */
		portfolios = Database.useGetInvestorPortfolioNumber(super.getClient()
				.getInvestor().getId());
		portfoliosArray = new Portfolio[portfolios.size()];
		portfolioNames = new String[portfolios.size()];
	}

	/**
	 * Method to get the portfolios
	 * 
	 * @return array of portfolios
	 * 
	 * @author Panagiotis Vakalis
	 * @version 18-07-2015
	 */
	public Portfolio[] getPortfolios() {
		for (int i = 0; i < portfolios.size(); i++) {
			portfoliosArray[i] = portfolios.get(i);
		}
		return portfoliosArray;
	}

	/**
	 * Method to set portfolios
	 * 
	 * @param portfolios
	 * 
	 * @author Panagiotis Vakalis
	 * @version 18-07-2015
	 */
	public void setPortfolio(ArrayList<Portfolio> portfolios) {
		this.portfolios = portfolios;
	}

	/**
	 * Method to get the portfolio names
	 * 
	 * @return array which contains the portfolio names
	 * 
	 * @author Panagiotis Vakalis
	 * @version 18-07-2015
	 */
	public String[] getPortfolioNames() {
		getPortfolios();
		for (int i = 0; i < portfolios.size(); i++) {
			portfolioNames[i] = portfolios.get(i).getName();
		}
		return portfolioNames;
	}

	/**
	 * Method to get the portfolio balance
	 * 
	 * @param index
	 *            of the array
	 * @return portfolio balance
	 * 
	 * @author Panagiotis Vakalis
	 * @version 18-07-2015
	 */
	private BigDecimal getPortfolioBalance(int index) {
		return portfoliosArray[index].getBalance();
	}

	/**
	 * Method to use the getPortfolioBalance method outside the class
	 * 
	 * @param index
	 *            of the array
	 * @return portfolio balance
	 * 
	 * @author Panagiotis Vakalis
	 * @version 18-07-2015
	 */
	public String useGetPortfolioBalance(int index) {
		return String.valueOf(getPortfolioBalance(index));
	}

	/**
	 * Method to get the invested money
	 * 
	 * @param index
	 *            of the array
	 * @return invested money
	 * 
	 * @author Panagiotis Vakalis
	 * @version 18-07-2015
	 */
	private BigDecimal getPortfolioInvestedMoney(int portfolioNumber) {
		return Database.useGetInvestedMoney(portfolioNumber);
	}

	public BigDecimal useGetPortfolioInvestedMoney(int portfolioNumber) {
		return getPortfolioInvestedMoney(portfolioNumber);
	}

	/**
	 * Method to get the profit / loss
	 * 
	 * @param index
	 *            of the array
	 * @return profit / loss
	 * 
	 * @author Panagiotis Vakalis
	 * @version 18-07-2015
	 */
	private double getPortfolioProfitLoss(int index) {
		return portfoliosArray[index].getProfitLoss();
	}

	/**
	 * Method to get the portfolio
	 * 
	 * @param index
	 * @return portolio
	 * 
	 * @author Panagiotis Vakalis
	 * @version 18-07-2015
	 */
	private Portfolio getPortfolio(int index) {
		return portfoliosArray[index];
	}

	/**
	 * Method to use the getPortfolio method outside the class
	 * 
	 * @param index
	 * @return portfolio
	 * 
	 * @author Panagiotis Vakalis
	 * @version 18-07-2015
	 */
	public Portfolio useGetPortfolio(int index) {
		return getPortfolio(index);
	}

	/**
	 * Method to use the getPortfolioProfitLoss method outside the class
	 * 
	 * @param index
	 *            of the array
	 * @return portfolio's profit / loss
	 * 
	 * @author Panagiotis Vakalis
	 * @version 18-07-2015
	 */
	public String useGetPortfolioProfitLoss(int index) {
		return String.valueOf(getPortfolioProfitLoss(index));
	}

	/**
	 * Method to change to main view
	 * 
	 * @param mainView
	 * @param portfolio
	 * 
	 * @author Panagiotis Vakalis
	 * @version 20-07-2015
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
	 * @version 20-07-2015
	 */
	public void useChangeToMainView(MainView mainView, Portfolio portfolio) {
		changeToMainView(mainView, portfolio);
	}

	/**
	 * Method to change to lots view
	 * 
	 * @param lotsView
	 * @param portfolio
	 * 
	 * @author Panagiotis Vakalis
	 * @version 23-07-2015
	 */
	private void changeToLotsView(LotsView lotsView, Portfolio portfolio) {
		lotsModel = new LotsModel(super.getClient(), portfolio);
		super.getClient().useChangePanel(lotsView);
	}

	/**
	 * Method to use the changeToLotsView outside the class
	 * 
	 * @param lotsView
	 * @param portfolio
	 * 
	 * @author Panagiotis Vakalis
	 * @version 23-07-2015
	 */
	public void useChangeToLotsView(LotsView lotsView, Portfolio portfolio) {
		changeToLotsView(lotsView, portfolio);
	}

	/**
	 * Method to add money into portfolio
	 * 
	 * @param portfolio
	 * @param amount
	 * 
	 * @author Panagiotis Vakalis
	 * @version 28-07-2015
	 */
	private String deposit(Portfolio portfolio, BigDecimal amount) {
		Database.useUpdatePortfolioBalance(portfolio.getNumber(), amount);
		// Update the database and the text area
		updateDeposit(portfolio.getNumber(), amount);
		updatePortfolioBalance(portfolio.getNumber());
		return "You have deposited " + String.valueOf(amount)
				+ " into your portfolio.";
	}

	/**
	 * Method to update the deposit table
	 * 
	 * @param portfolioNumber
	 * @param amount
	 * 
	 * @author Panagiotis Vakalis
	 * @version 28-07-2015
	 */
	private void updateDeposit(int portfolioNumber, BigDecimal amount) {
		Database.useInsertDeposit(portfolioNumber, amount);
		totalDeposits = getTotalDeposits(portfolioNumber);
		update(totalDeposits);
	}

	/**
	 * Method to use the deposit method outside the class
	 * 
	 * @param portfolio
	 * @param amount
	 * 
	 * @author Panagiotis Vakalis
	 * @version 28-07-2015
	 */
	public String useDeposit(Portfolio portfolio, BigDecimal amount) {
		return deposit(portfolio, amount);
	}

	/**
	 * Method to remove money from the portfolio
	 * 
	 * @param portfolio
	 * @param amount
	 * 
	 * @author Panagiotis Vakalis
	 * @version 28-07-2015
	 */
	private String withdraw(Portfolio portfolio, BigDecimal amount) {
		// The amount is multiplied by -1 in order to decrease the amount
		/*
		 * Using the getBalance() it was not updated instantly, so I used the
		 * portfolioBalance variable which is updated using the
		 * updatePortfolioBalance method
		 */
		// Update portfolio balance first
		updatePortfolioBalance(portfolio.getNumber());
		if (portfolioBalance.compareTo(amount) >= 0) {
			Database.useUpdatePortfolioBalance(portfolio.getNumber(),
					amount.multiply(new BigDecimal(-1)));
			// Method to update the database and the text area
			updateWithdraw(portfolio.getNumber(), amount);
			updatePortfolioBalance(portfolio.getNumber());
			return "You have withrawn " + String.valueOf(amount)
					+ " from your portfolio.";
		} else {
			return "The available balance is less than the amount you want to withdraw";
		}
	}

	/**
	 * Method to update the withdraw table
	 * 
	 * @param portfolioNumber
	 * @param amount
	 * 
	 * @author Panagiotis Vakalis
	 * @version 28-07-2015
	 */
	private void updateWithdraw(int portfolioNumber, BigDecimal amount) {
		Database.useInsertWithdraw(portfolioNumber, amount);
		totalWithdraws = getTotalWithdraws(portfolioNumber);
		update(totalWithdraws);
	}

	/**
	 * Method to use the withdraw method outside the class
	 * 
	 * @param portfolio
	 * @param amount
	 * 
	 * @author Panagiotis Vakalis
	 * @version 28-07-2015
	 */
	public String useWithdraw(Portfolio portfolio, BigDecimal amount) {
		return withdraw(portfolio, amount);
	}

	/**
	 * Method to update the portfolio balance
	 * 
	 * @param portfolioNumber
	 * 
	 * @author Panagiotis Vakalis
	 * @version 28-07-2015
	 */
	private void updatePortfolioBalance(int portfolioNumber) {
		portfolioBalance = Database
				.useRetrievePortfolioBalance(portfolioNumber);
		update(portfolioBalance);
	}

	/**
	 * Method to update the portfolio balance
	 * 
	 * @param portfolioNumber
	 * 
	 * @author Panagiotis Vakalis
	 * @version 28-07-2015
	 */
	public void useUpdatePortfolioBalance(int index) {
		updatePortfolioBalance(index);
	}

	/**
	 * Method to update the portfolio balance area in view
	 * 
	 * @param portfolioNumber
	 * 
	 * @author Panagiotis Vakalis
	 * @version 28-07-2015
	 */
	private String updatePortfolioBalanceArea(int index) {
		return View
				.currencyFormat(Database.useRetrievePortfolioBalance(portfolios
						.get(index).getNumber()));
	}

	/**
	 * Method to update the portfolio balance area in view
	 * 
	 * @param portfolioNumber
	 * 
	 * @author Panagiotis Vakalis
	 * @version 28-07-2015
	 */
	public String useUpdatePortfolioBalanceArea(int index) {
		return updatePortfolioBalanceArea(index);
	}

	/**
	 * Method to calculate the total balance of a portfolio
	 * 
	 * @param index
	 *            of the portfolios array
	 * 
	 * @author Panagiotis Vakalis
	 * @version 28-07-2015
	 */
	private BigDecimal calculateTotalBalance(int index) {
		return getPortfolio(index).getBalance().add(
				getPortfolio(index).getInvestedMoney());
	}

	/**
	 * Method to get the total balance of a portfolio
	 * 
	 * @param index
	 *            of the portfolios array
	 * 
	 * @author Panagiotis Vakalis
	 * @version 28-07-2015
	 */
	public BigDecimal getTotalBalance(int index) {
		return calculateTotalBalance(index);
	}

	/**
	 * Method to update the total withdraws area in view
	 * 
	 * @param portfolioNumber
	 * 
	 * @author Panagiotis Vakalis
	 * @version 28-07-2015
	 */
	private String updateTotalWithdrawsArea(int index) {
		return View.currencyFormat(totalWithdraws);
	}

	/**
	 * Method to update the total withdraws area in view
	 * 
	 * @param portfolioNumber
	 * 
	 * @author Panagiotis Vakalis
	 * @version 28-07-2015
	 */
	public String useUpdateTotalWithdrawsArea(int index) {
		return updateTotalWithdrawsArea(index);
	}

	/**
	 * Method to update the total deposits area in view
	 * 
	 * @param portfolioNumber
	 * 
	 * @author Panagiotis Vakalis
	 * @version 28-07-2015
	 */
	private String updateTotalDepositsArea(int index) {
		return View.currencyFormat(totalDeposits);
	}

	/**
	 * Method to update the total deposits area in view
	 * 
	 * @param portfolioNumber
	 * 
	 * @author Panagiotis Vakalis
	 * @version 28-07-2015
	 */
	public String useUpdateTotalDepositsArea(int index) {
		return updateTotalDepositsArea(index);
	}

	/**
	 * Method to update the portfolio initial balance area in view
	 * 
	 * @param portfolioNumber
	 * 
	 * @author Panagiotis Vakalis
	 * @version 28-07-2015
	 */
	private String updatePortfolioInitialBalanceArea(int index) {
		return View.currencyFormat(getInitialBalance(portfolios.get(index)
				.getNumber()));
	}

	/**
	 * Method to update the portfolio initial balance area in view
	 * 
	 * @param portfolioNumber
	 * 
	 * @author Panagiotis Vakalis
	 * @version 28-07-2015
	 */
	public String useUpdatePortfolioInitialBalanceArea(int index) {
		return updatePortfolioInitialBalanceArea(index);
	}

	/**
	 * Method to update the portfolio invested money area in view
	 * 
	 * @param portfolioNumber
	 * 
	 * @author Panagiotis Vakalis
	 * @version 28-07-2015
	 */
	private String updatePortfolioInvestedMoney(int index) {
		return View.currencyFormat(getPortfolioInvestedMoney(portfolios.get(
				index).getNumber()));
	}

	/**
	 * Method to update the portfolio invested money area in view
	 * 
	 * @param portfolioNumber
	 * 
	 * @author Panagiotis Vakalis
	 * @version 28-07-2015
	 */
	public String useUpdatePortfolioInvestedMoney(int index) {
		return updatePortfolioInvestedMoney(index);
	}

	/**
	 * Method to update the portfolio profit loss area in view
	 * 
	 * @param portfolioNumber
	 * 
	 * @author Panagiotis Vakalis
	 * @version 28-07-2015
	 */
	private String updatePortfolioProfitLoss(int index) {
		return View
				.currencyFormat(new BigDecimal(Math
						.round(getAllLotsProfitLoss(portfolios.get(index)
								.getNumber()))));
	}

	/**
	 * Method to update the portfolio profit loss area in view
	 * 
	 * @param portfolioNumber
	 * 
	 * @author Panagiotis Vakalis
	 * @version 28-07-2015
	 */
	public String useUpdatePortfolioProfitLoss(int index) {
		return updatePortfolioProfitLoss(index);
	}

	/**
	 * Method to update the portfolio total return area in view
	 * 
	 * @param portfolioNumber
	 * 
	 * @author Panagiotis Vakalis
	 * @version 28-07-2015
	 */
	private String updatePortfolioTotalReturn(int index) {
		return retrieveTotalReturn(portfolios.get(index).getNumber());
	}

	/**
	 * Method to update the portfolio total return area in view
	 * 
	 * @param portfolioNumber
	 * 
	 * @author Panagiotis Vakalis
	 * @version 28-07-2015
	 */
	public String useUpdatePortfolioTotalReturn(int index) {
		return updatePortfolioTotalReturn(index);
	}

	/**
	 * Method to update the view
	 * 
	 * @param arg
	 * 
	 * @author Panagiotis Vakalis
	 * @version 17-07-2015
	 */
	private void update(Object arg) {
		setChanged();
		notifyObservers(arg);
	}

	/**
	 * Method to get all the profit or loss of all the lots
	 * 
	 * @param portfolioNumber
	 * 
	 * @author Panagiotis Vakalis
	 * @version 28-07-2015
	 */
	private double getAllLotsProfitLoss(int portfolioNumber) {
		double profitLossSum = 0;
		// Retrieve the lots
		allLots = Database.useGetLotsForASpecificPortfolio(portfolioNumber);

		for (Lot lot : allLots) {
			/*
			 * For each lot add the current profit of loss
			 */
			profitLossSum += lot.getCurrentProfitLoss();
		}

		return profitLossSum;
	}

	/**
	 * Method to get all the profit or loss of all the lots
	 * 
	 * @param portfolioNumber
	 * 
	 * @author Panagiotis Vakalis
	 * @version 28-07-2015
	 */
	public double useGetAllLotsProfitLoss(int portfolioNumber) {
		return getAllLotsProfitLoss(portfolioNumber);
	}

	/**
	 * Method to calculate the total balance
	 * 
	 * @param portfolio
	 * 
	 * @author Panagiotis Vakalis
	 * @version 28-07-2015
	 */
	private BigDecimal totalBalance(Portfolio portfolio) {
		/*
		 * Total balance = balanceNow + investedMoney + lotsProfitLoss
		 */
		return portfolio
				.getBalance()
				.add(portfolio.getInvestedMoney())
				.add(new BigDecimal(getAllLotsProfitLoss(portfolio.getNumber())));
	}

	/**
	 * Method to get the total balance
	 * 
	 * @param portfolio
	 * 
	 * @author Panagiotis Vakalis
	 * @version 28-07-2015
	 */
	public BigDecimal getTotalBalance(Portfolio portfolio) {
		return totalBalance(portfolio);
	}

	/**
	 * Method to get the initial balance from the database
	 * 
	 * @param portfolioNumber
	 * 
	 * @author Panagiotis Vakalis
	 * @version 28-07-2015
	 */
	private BigDecimal getInitialBalance(int portfolioNumber) {
		return Database.useRetrievePortfolioInitialBalance(portfolioNumber);
	}

	/**
	 * Method to get the initial balance from the database
	 * 
	 * @param portfolioNumber
	 * 
	 * @author Panagiotis Vakalis
	 * @version 28-07-2015
	 */
	public BigDecimal useGetInitialBalance(int portfolioNumber) {
		return getInitialBalance(portfolioNumber);
	}

	/**
	 * Method to get the balance now from the database
	 * 
	 * @param portfolioNumber
	 * 
	 * @author Panagiotis Vakalis
	 * @version 28-07-2015
	 */
	private BigDecimal getBalanceNow(int portfolioNumber) {
		return Database.useRetrievePortfolioBalance(portfolioNumber);
	}

	/**
	 * Method to get the balance now from the database
	 * 
	 * @param portfolioNumber
	 * 
	 * @author Panagiotis Vakalis
	 * @version 28-07-2015
	 */
	public BigDecimal useGetBalanceNow(int portfolioNumber) {
		return getBalanceNow(portfolioNumber);
	}

	/**
	 * Method to get the total withdraws from the database
	 * 
	 * @param portfolioNumber
	 * 
	 * @author Panagiotis Vakalis
	 * @version 28-07-2015
	 */
	private BigDecimal getTotalWithdraws(int portfolioNumber) {
		return Database.useRetrieveTotalWithdraws(portfolioNumber);
	}

	/**
	 * Method to get the total withdraws from the database
	 * 
	 * @param portfolioNumber
	 * 
	 * @author Panagiotis Vakalis
	 * @version 28-07-2015
	 */
	public BigDecimal useGetTotalWithdraws(int portfolioNumber) {
		return getTotalWithdraws(portfolioNumber);
	}

	/**
	 * Method to get the total deposits from the database
	 * 
	 * @param portfolioNumber
	 * 
	 * @author Panagiotis Vakalis
	 * @version 28-07-2015
	 */
	private BigDecimal getTotalDeposits(int portfolioNumber) {
		return Database.useRetrieveTotalDeposits(portfolioNumber);
	}

	/**
	 * Method to get the total deposits from the database
	 * 
	 * @param portfolioNumber
	 * 
	 * @author Panagiotis Vakalis
	 * @version 28-07-2015
	 */
	public BigDecimal useGetTotalDeposits(int portfolioNumber) {
		return getTotalDeposits(portfolioNumber);
	}

	/**
	 * Method to calculate the total return of a portfolio
	 * 
	 * @param portfolioNumber
	 * 
	 * @author Panagiotis Vakalis
	 * @version 28-07-2015
	 */
	private BigDecimal calculateTotalReturn(int portfolioNumber) {

		/*
		 * The total return can be calculated using the following formula:
		 * totalReturn = balanceNow - initialBalance + sum(withdraws) -
		 * sum(deposits) + investedMoney
		 */
		balanceNow = getBalanceNow(portfolioNumber);
		initialBalance = getInitialBalance(portfolioNumber);
		totalWithdraws = getTotalWithdraws(portfolioNumber);
		totalDeposits = getTotalDeposits(portfolioNumber);
		investedMoney = getPortfolioInvestedMoney(portfolioNumber);

		totalReturn = balanceNow.subtract(initialBalance).add(totalWithdraws)
				.subtract(totalDeposits).add(investedMoney);
		return totalReturn;
	}

	/**
	 * Method to get the total return of a portfolio
	 * 
	 * @param portfolioNumber
	 * 
	 * @author Panagiotis Vakalis
	 * @version 28-07-2015
	 */
	public String retrieveTotalReturn(int portfolioNumber) {
		return View.currencyFormat(calculateTotalReturn(portfolioNumber));
	}

	/**
	 * Method to get all the lots the portfolio has
	 * 
	 * @author Panagiotis Vakalis
	 * @version 23-07-2015
	 */
	private void getAllLots(Portfolio portfolio) {
		allLots = Database.useGetLotsForASpecificPortfolio(portfolio
				.getNumber());
	}

	/**
	 * Method to use the getAllLots() outside the class
	 * 
	 * @author Panagiotis Vakalis
	 * @version 23-07-2015
	 */
	public void useGetAllLots(Portfolio portfolio) {
		getAllLots(portfolio);
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
		lotsDetails = new String[allLots.size()][8];

		for (int i = 0; i < allLots.size(); i++) {
			lotsDetails[i][0] = allLots.get(i).getStockSymbol();
			lotsDetails[i][1] = View.currencyFormat(new BigDecimal(allLots.get(
					i).getBoughtPrice()));
			lotsDetails[i][2] = String
					.valueOf(allLots.get(i).getBoughtShares());
			lotsDetails[i][3] = View.currencyFormat(new BigDecimal(allLots.get(
					i).getAmount()));
			lotsDetails[i][4] = View.currencyFormat(new BigDecimal(allLots.get(
					i).getCurrentPrice()));
			lotsDetails[i][5] = View.currencyFormat(new BigDecimal(allLots.get(
					i).getCurrentAmount()));
			lotsDetails[i][6] = View.currencyFormat(new BigDecimal(allLots.get(
					i).getCurrentProfitLoss()));
			lotsDetails[i][7] = String.valueOf(allLots.get(i).getDate());
		}
		return lotsDetails;
	}

	/**
	 * Method to get all the lots details
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
	 * Method which is used in order to update the view when an other portfolio
	 * is selected
	 * 
	 * @return index in portfolio array
	 * 
	 * @author Panagiotis Vakalis
	 * @version 23-07-2015
	 */
	private void portfolioChanged(int index) {
		initialBalance = getInitialBalance(portfolios.get(index).getNumber());
		balanceNow = getBalanceNow(portfolios.get(index).getNumber());
		investedMoney = getPortfolioInvestedMoney(portfolios.get(index)
				.getNumber());
		lotsProfitLoss = getAllLotsProfitLoss(portfolios.get(index).getNumber());
		totalWithdraws = getTotalWithdraws(portfolios.get(index).getNumber());
		totalDeposits = getTotalDeposits(portfolios.get(index).getNumber());
		totalReturn = calculateTotalReturn(portfolios.get(index).getNumber());
		getAllLots(portfolios.get(index));
		totalBalance = calculateTotalBalance(index);

		update(initialBalance);
		update(balanceNow);
		update(investedMoney);
		update(lotsProfitLoss);
		update(totalWithdraws);
		update(totalDeposits);
		update(totalReturn);
		update(allLots);
		update(totalBalance);
	}

	/**
	 * Method which is used in order to update the view when an other portfolio
	 * is selected
	 * 
	 * @return index in portfolio array
	 * 
	 * @author Panagiotis Vakalis
	 * @version 23-07-2015
	 */
	public void usePortfolioChanged(int index) {
		portfolioChanged(index);
	}
}
