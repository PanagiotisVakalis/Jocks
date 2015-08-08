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
	
	private ArrayList<Portfolio> portfolios = super.getClient().getInvestor().getPortfolios();
	private Portfolio[] portfoliosArray = new Portfolio[portfolios.size()];
	private String[] portfolioNames = new String[portfolios.size()];
	private MainModel mainModel;
	private LotsModel lotsModel;
	private Investor investor;
	private ArrayList<Lot> allLots;
	private BigDecimal totalReturn;
	private BigDecimal balanceNow;
	private BigDecimal initialBalance;
	private BigDecimal totalWithdraws;
	private BigDecimal totalDeposits;
	
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
	private BigDecimal getPortfolioInvestedMoney(int index){
		return portfoliosArray[index].getInvestedMoney();
	}
	
	/**
	 * Method to use the getPortfolioInvestedMoney method outside the class
	 * @param index of the array
	 * @return portfolio invested money
	 * 
	 * @author Panagiotis Vakalis
	 * @version 18-07-2015
	 */
	public String useGetPortfolioInvestedMoney(int index){
		return String.valueOf(getPortfolioInvestedMoney(index));
	}
	
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
		Database.useInsertDeposit(portfolio.getNumber(), amount);
		updatePortfolioBalance(portfolio.getNumber());
		return "You have deposited " + String.valueOf(amount) + " into your portfolio.";
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
			Database.useInsertWithdraw(portfolio.getNumber(), amount);
			updatePortfolioBalance(portfolio.getNumber());
			return "You have withrawn " + String.valueOf(amount) + " from your portfolio.";
		}
		else{
			return "The available balance is less than the amount you want to withdraw";
		}
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
		return String.valueOf(portfolioBalance);
	}
	
	public String useUpdatePortfolioBalanceArea(int index){
		return updatePortfolioBalanceArea(index);
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
	
	private BigDecimal totalBalance(Portfolio portfolio){
//		return portfolio.getBalance() + portfolio.getInvestedMoney() + portfolio.getProfitLoss();
		return portfolio.getBalance().add(portfolio.getInvestedMoney()).add(new BigDecimal(getAllLotsProfitLoss(portfolio)));
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
	
	private void calculateTotalReturn(Portfolio portfolio){
		
		/*
		 * The total return can be calculated using the following formula:
		 * totalReturn = balanceNow - initialBalance + sum(withdraws) - sum(deposits)
		 */
		balanceNow = Database.useRetrievePortfolioBalance(portfolio.getNumber());
		initialBalance = Database.useRetrievePortfolioInitialBalance(portfolio.getNumber());
		totalWithdraws = Database.useRetrieveTotalWithdraws(portfolio.getNumber());
		totalDeposits = Database.useRetrieveTotalDeposits(portfolio.getNumber());
		
		totalReturn = balanceNow.subtract(initialBalance).add(totalWithdraws).subtract(totalDeposits);
	}
	
	public String retrieveTotalReturn(Portfolio portfolio){
		calculateTotalReturn(portfolio);
		
		return "Your balance now is: " + balanceNow + ", your initial balance was: " + initialBalance + ", your total withdraws are: " + totalWithdraws + ", your total deposits are: " + totalDeposits +". Your total return is: " + totalReturn; 
	}
}
