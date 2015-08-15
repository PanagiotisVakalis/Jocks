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
	
//	/*
//	 * The following variables will be used when
//	 * the portfolio is changed.
//	 */
//	private BigDecimal initialBalanceFromAnotherPortfolio;
//	private BigDecimal balanceNowFromAnotherPortfolio;
//	private BigDecimal investedMoneyFromAnotherPortfolio;
//	private BigDecimal lotsProfitLossFromAnotherPortfolio;
//	private BigDecimal totalWithdrawsFromAnotherPortfolio;
//	private BigDecimal totalDepositsFromAnotherPortfolio;
	
	
	
	/*
	 * I have used big decimal
	 * because double can reach the limit
	 */
	private BigDecimal portfolioBalance;
	
	/**
	 * Constructor of the class
	 * @param client
	 * 
	 * @author Panagiotis Vakalis
	 * @version 18-07-2015
	 */
	public PortfolioModel(Client client) {
		super(client);
		this.investor = super.getClient().getInvestor();
		
//		portfolios = super.getClient().getInvestor().getPortfolios();
		portfolios = Database.useGetInvestorPortfolioNumber(super.getClient().getInvestor().getId());
		portfoliosArray = new Portfolio[portfolios.size()];
		portfolioNames = new String[portfolios.size()];
	}

	/**
	 * Methot to get the portfolios
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
	 * @return array which contains the portfolio names
	 * 
	 * @author Panagiotis Vakalis
	 * @version 18-07-2015
	 */
	public String[] getPortfolioNames(){
		getPortfolios();
		for (int i = 0; i < portfolios.size(); i++) {
			portfolioNames[i] = portfolios.get(i).getName();
		}
		return portfolioNames;
	}
	
	/**
	 * Method to get the portfolio balance
	 * @param index of the array
	 * @return portfolio balance
	 * 
	 * @author Panagiotis Vakalis
	 * @version 18-07-2015
	 */
	private BigDecimal getPortfolioBalance(int index){
//		return portfoliosArray[index].getBalance();
//		return new BigDecimal(portfoliosArray[index].getBalance());
		return portfoliosArray[index].getBalance();
	}
	
	/**
	 * Method to use the getPortfolioBalance method outside the class
	 * @param index of the array
	 * @return portfolio balance
	 * 
	 * @author Panagiotis Vakalis
	 * @version 18-07-2015
	 */
	public String useGetPortfolioBalance(int index){
		return String.valueOf(getPortfolioBalance(index));
	}
	
	/**
	 * Method to get the invested money
	 * @param index of the array
	 * @return invested money
	 * 
	 * @author Panagiotis Vakalis
	 * @version 18-07-2015
	 */
	public BigDecimal getPortfolioInvestedMoney(int portfolioNumber){
//		return portfoliosArray[index].getInvestedMoney();
		return Database.useGetInvestedMoney(portfolioNumber);
	}
	
	/**
//	 * Method to use the getPortfolioInvestedMoney method outside the class
//	 * @param index of the array
//	 * @return portfolio invested money
//	 * 
//	 * @author Panagiotis Vakalis
//	 * @version 18-07-2015
//	 */
//	public String useGetPortfolioInvestedMoney(int index){
//		return String.valueOf(getPortfolioInvestedMoney(index));
//	}
	
	/**
	 * Method to get the profit / loss
	 * @param index of the array
	 * @return profit / loss
	 * 
	 * @author Panagiotis Vakalis
	 * @version 18-07-2015
	 */
	private double getPortfolioProfitLoss(int index){
		return portfoliosArray[index].getProfitLoss();
	}
	
	/**
	 * Method to get the portfolio
	 * @param index
	 * @return portolio
	 * 
	 * @author Panagiotis Vakalis
	 * @version 18-07-2015
	 */
	private Portfolio getPortfolio(int index){
		return portfoliosArray[index];
	}
	
	/**
	 * Method to use the getPortfolio method outside the class
	 * @param index
	 * @return portfolio
	 * 
	 * @author Panagiotis Vakalis
	 * @version 18-07-2015
	 */
	public Portfolio useGetPortfolio(int index){
		return getPortfolio(index);
	}
	
	/**
	 * Method to use the getPortfolioProfitLoss method outside the class
	 * @param index of the array
	 * @return portfolio's profit / loss
	 * 
	 * @author Panagiotis Vakalis
	 * @version 18-07-2015
	 */
	public String useGetPortfolioProfitLoss(int index){
		return String.valueOf(getPortfolioProfitLoss(index));
	}
	
	/**
	 * Method to change to main view
	 * @param mainView
	 * @param portfolio
	 * 
	 * @author Panagiotis Vakalis
	 * @version 20-07-2015	
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
	 * @version 20-07-2015	
	 */
	public void useChangeToMainView(MainView mainView, Portfolio portfolio){
		changeToMainView(mainView, portfolio);
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
	 * Method to add money into portfolio
	 * @param portfolio
	 * @param amount
	 * 
	 * @author Panagiotis Vakalis
	 * @version 28-07-2015
	 */
	private String deposit(Portfolio portfolio, BigDecimal amount){
		Database.updatePortfolioBalance(portfolio.getNumber(), amount);
//		Database.useInsertDeposit(portfolio.getNumber(), amount);
		//Update the database and the text area
		updateDeposit(portfolio.getNumber(), amount);
		updatePortfolioBalance(portfolio.getNumber());
		return "You have deposited " + String.valueOf(amount) + " into your portfolio.";
	}
	
	private void updateDeposit(int portfolioNumber, BigDecimal amount){
		Database.useInsertDeposit(portfolioNumber, amount);
		totalDeposits = getTotalDeposits(portfolioNumber);
		update(totalDeposits);
	}
	
	/**
	 * Method to use the deposit method outside the class
	 * @param portfolio
	 * @param amount
	 * 
	 * @author Panagiotis Vakalis
	 * @version 28-07-2015
	 */
	public String useDeposit(Portfolio portfolio, BigDecimal amount){
		return deposit(portfolio, amount);
	}
	
	/**
	 * Method to remove money from the portfolio
	 * @param portfolio
	 * @param amount
	 * 
	 * @author Panagiotis Vakalis
	 * @version 28-07-2015
	 */
	private String withdraw(Portfolio portfolio, BigDecimal amount){
		//The amount is multiplied by -1 in order to decrease the amount
//		Database.updatePortfolioBalance(portfolio.getNumber(), (-1) * amount);
		/*
		 * Using the getBalance() it was not updated instantly,
		 * so I used the portfolioBalance variable
		 * which is updated using the updatePortfolioBalance method
		 */
		//Update portfolio balance first
		updatePortfolioBalance(portfolio.getNumber());
		if(portfolioBalance.compareTo(amount) >= 0){
			Database.updatePortfolioBalance(portfolio.getNumber(), amount.multiply(new BigDecimal(-1)));
//			Database.useInsertWithdraw(portfolio.getNumber(), amount);
			//Method to update the database and the text area
			updateWithdraw(portfolio.getNumber(), amount);
			updatePortfolioBalance(portfolio.getNumber());
			return "You have withrawn " + String.valueOf(amount) + " from your portfolio.";
		}
		else{
			return "The available balance is less than the amount you want to withdraw";
		}
	}
	
	private void updateWithdraw(int portfolioNumber, BigDecimal amount){
		Database.useInsertWithdraw(portfolioNumber, amount);
		totalWithdraws = getTotalWithdraws(portfolioNumber);
		update(totalWithdraws);
	}
	
	/**
	 * Method to use the withdraw method outside the class
	 * @param portfolio
	 * @param amount
	 * 
	 * @author Panagiotis Vakalis
	 * @version 28-07-2015
	 */
	public String useWithdraw(Portfolio portfolio, BigDecimal amount){
		return withdraw(portfolio, amount);
	}
	
	private void updatePortfolioBalance(int portfolioNumber){
//		portfolioBalance = Database.useRetrievePortfolioBalance(portfolioNumber);
//		portfolioBalance = BigDecimal.valueOf(Database.useRetrievePortfolioBalance(portfolioNumber));
		portfolioBalance = Database.useRetrievePortfolioBalance(portfolioNumber);
		update(portfolioBalance);
	}
	
	public void useUpdatePortfolioBalance(int index){
		updatePortfolioBalance(index);
	}
	
	private String updatePortfolioBalanceArea(int index){
//		updatePortfolioBalanceArea(index);
		return View.currencyFormat(Database.useRetrievePortfolioBalance(portfolios.get(index).getNumber()));
	}
	
	public String useUpdatePortfolioBalanceArea(int index){
		return updatePortfolioBalanceArea(index);
	}
	
	private String updateTotalWithdrawsArea(int index){
		return View.currencyFormat(totalWithdraws);
	}
	
	public String useUpdateTotalWithdrawsArea(int index){
		return updateTotalWithdrawsArea(index);
	}
	
	private String updateTotalDepositsArea(int index){
		return View.currencyFormat(totalDeposits);
	}
	
	public String useUpdateTotalDepositsArea(int index){
		return updateTotalDepositsArea(index);
	}
	
	private String updatePortfolioInitialBalanceArea(int index){
		return View.currencyFormat(getInitialBalance(portfolios.get(index).getNumber()));
	}
	
	public String useUpdatePortfolioInitialBalanceArea(int index){
		return updatePortfolioInitialBalanceArea(index);
	}
	
	private String updatePortfolioInvestedMoney(int index){
		return View.currencyFormat(getPortfolioInvestedMoney(portfolios.get(index).getNumber()));
	}
	
	public String useUpdatePortfolioInvestedMoney(int index){
		return updatePortfolioInvestedMoney(index);
	}
	
	private String updatePortfolioProfitLoss(int index){
		return View.currencyFormat(new BigDecimal(Math.round(getAllLotsProfitLoss(portfolios.get(index).getNumber()))));
	}
	
	public String useUpdatePortfolioProfitLoss(int index){
		return updatePortfolioProfitLoss(index);
	}
	
	private String updatePortfolioTotalReturn(int index){
		return retrieveTotalReturn(portfolios.get(index).getNumber());
	}
	
	public String useUpdatePortfolioTotalReturn(int index){
		return updatePortfolioTotalReturn(index);
	}
	
	/**
	 * Method to update the view
	 * @param arg
	 * 
	 * @author Panagiotis Vakalis
	 * @version 17-07-2015
	 */
	private void update(Object arg){
		setChanged();
		notifyObservers(arg);
	}
	
//	private void changePortfolioName(Portfolio portfolio, String newName){
//		Database.useUpdatePortfolioName(portfolio.getNumber(), investor.getEmail(), newName);
//	}
//	
//	public String useChangePortfolioName(Portfolio portfolio, String newName){
//		if(!Database.usePortfolioNameExists(investor.getEmail(), newName)){
//			changePortfolioName(portfolio, newName);
//			return "You have changed the portfolio name";
//		}
//		else{
//			return "This name already exists";
//		}
//	}
	
	private double getAllLotsProfitLoss(int portfolioNumber){
		double profitLossSum = 0;
		//Retrieve the lots
		allLots = Database.useGetLotsForASpecificPortfolio(portfolioNumber);
		
		for(Lot lot: allLots){
			profitLossSum += lot.getCurrentProfitLoss();
		}
		
		return profitLossSum;
	}
	
	public double useGetAllLotsProfitLoss(int portfolioNumber){
		return getAllLotsProfitLoss(portfolioNumber);
	}
	
	private BigDecimal totalBalance(Portfolio portfolio){
//		return portfolio.getBalance() + portfolio.getInvestedMoney() + portfolio.getProfitLoss();
		return portfolio.getBalance().add(portfolio.getInvestedMoney()).add(new BigDecimal(getAllLotsProfitLoss(portfolio.getNumber())));
	}
	
	public BigDecimal getTotalBalance(Portfolio portfolio){
		return totalBalance(portfolio);
	}
	
//	private String getTotalReturn(Portfolio portfolio){
//		BigDecimal balanceOnStart = null;
//		BigDecimal balanceOnEnd = null;
//		
//		balanceOnStart = Database.useGetTheTotalBalanceOnTheFirstDate(portfolio.getNumber());
//		balanceOnEnd = Database.useGetTheTotalBalanceOnTheLastDate(portfolio.getNumber());
//		
//		return "The total return for this portfolio is " + (balanceOnEnd.subtract(balanceOnStart)).toString();
//	}
	
//	public String useGetTotalReturn(Portfolio portfolio){
//		return getTotalReturn(portfolio);
//	}
	
	public BigDecimal getInitialBalance(int portfolioNumber){
		return Database.useRetrievePortfolioInitialBalance(portfolioNumber);
	}
	
	public BigDecimal getBalanceNow(int portfolioNumber){
		return Database.useRetrievePortfolioBalance(portfolioNumber);
	}
	
	public BigDecimal getTotalWithdraws(int portfolioNumber){
		return Database.useRetrieveTotalWithdraws(portfolioNumber);
	}
	
	public BigDecimal getTotalDeposits(int portfolioNumber){
		return Database.useRetrieveTotalDeposits(portfolioNumber);
	}
	
	private BigDecimal calculateTotalReturn(int portfolioNumber){
		
		/*
		 * The total return can be calculated using the following formula:
		 * totalReturn = balanceNow - initialBalance + sum(withdraws) - sum(deposits)
		 */
//		balanceNow = Database.useRetrievePortfolioBalance(portfolio.getNumber());
		balanceNow = getBalanceNow(portfolioNumber);
//		initialBalance = Database.useRetrievePortfolioInitialBalance(portfolio.getNumber());
		initialBalance = getInitialBalance(portfolioNumber);
//		totalWithdraws = Database.useRetrieveTotalWithdraws(portfolio.getNumber());
		totalWithdraws = getTotalWithdraws(portfolioNumber);
//		totalDeposits = Database.useRetrieveTotalDeposits(portfolio.getNumber());
		totalDeposits = getTotalDeposits(portfolioNumber);
		
		totalReturn = balanceNow.subtract(initialBalance).add(totalWithdraws).subtract(totalDeposits);
		return totalReturn;
	}
	
	public String retrieveTotalReturn(int portfolioNumber){
//		calculateTotalReturn(portfolioNumber);
		
//		return "Your balance now is: " + balanceNow + ", your initial balance was: " + initialBalance + ", your total withdraws are: " + totalWithdraws + ", your total deposits are: " + totalDeposits +". Your total return is: " + totalReturn; 
		return View.currencyFormat(calculateTotalReturn(portfolioNumber));
	}
	
	/**
	 * Method to get all the lots the portfolio has
	 * 
	 * @author Panagiotis Vakalis
	 * @version 23-07-2015
	 */
	private void getAllLots(Portfolio portfolio) {
		allLots = Database.useGetLotsForASpecificPortfolio(portfolio.getNumber());
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
			lotsDetails[i][1] = View.currencyFormat(new BigDecimal(allLots.get(i).getBoughtPrice()));
			lotsDetails[i][2] = String.valueOf(allLots.get(i).getBoughtShares());
			lotsDetails[i][3] = View.currencyFormat(new BigDecimal(allLots.get(i).getAmount()));
			lotsDetails[i][4] = View.currencyFormat(new BigDecimal(allLots.get(i).getCurrentPrice()));
			lotsDetails[i][5] = View.currencyFormat(new BigDecimal(allLots.get(i).getCurrentAmount()));
			lotsDetails[i][6] = View.currencyFormat(new BigDecimal(allLots.get(i).getCurrentProfitLoss()));
			lotsDetails[i][7] = String.valueOf(allLots.get(i).getDate());
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
	
	private void portfolioChanged(int index){
		initialBalance = getInitialBalance(portfolios.get(index).getNumber());
		balanceNow = getBalanceNow(portfolios.get(index).getNumber());
		investedMoney = getPortfolioInvestedMoney(portfolios.get(index).getNumber());
		lotsProfitLoss = getAllLotsProfitLoss(portfolios.get(index).getNumber());
		totalWithdraws = getTotalWithdraws(portfolios.get(index).getNumber());
		totalDeposits = getTotalDeposits(portfolios.get(index).getNumber());
		totalReturn = calculateTotalReturn(portfolios.get(index).getNumber());
		getAllLots(portfolios.get(index));
		
		update(initialBalance);
		update(balanceNow);
		update(investedMoney);
		update(lotsProfitLoss);
		update(totalWithdraws);
		update(totalDeposits);
		update(totalReturn);
		update(allLots);
	}
	
	public void usePortfolioChanged(int index){
		portfolioChanged(index);
	}
}
