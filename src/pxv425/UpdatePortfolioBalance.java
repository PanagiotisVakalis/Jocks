package pxv425;

import java.math.BigDecimal;
import java.util.ArrayList;

public class UpdatePortfolioBalance implements Runnable {
	
	private BigDecimal totalBalance;
	private int portfolioNumber;
	private ArrayList<Integer> portfolioNumbers;
	private ArrayList<BigDecimal> balanceAndInvestedMoney;
	private ArrayList<Lot> allLots;
	private double profitLossSum;
	private ArrayList<Double> profitLoss;
	
//	public UpdatePortfolioBalance(int portfolioNumber, BigDecimal totalBalance) {
//		this.portfolioNumber = portfolioNumber;
//		this.totalBalance = totalBalance;
//	}
	
	private void getPortfolioNumbers(){
		portfolioNumbers = new ArrayList<>();
		portfolioNumbers = Database.useGetPortfolioNumbers();
	}
	
	private void getBalanceAndInvestedForPortfolios(){
		balanceAndInvestedMoney = new ArrayList<>();
		for(Integer portfolioNumber : portfolioNumbers){
			BigDecimal totalBalance = Database.useGetBalance(portfolioNumber);
			BigDecimal totalInvestedMoney = Database.useGetInvestedMoney(portfolioNumber);
			balanceAndInvestedMoney.add(totalBalance.add(totalInvestedMoney)); 
		}
	}
		
	private void getAllLotsProfitLoss(){
		profitLoss = new ArrayList<>();
		//Retrieve the lots
		for(Integer portfolioNumber : portfolioNumbers){
			profitLossSum = 0;
			allLots = Database.useGetLotsForASpecificPortfolio(portfolioNumber);
			
			for(Lot lot: allLots){
				profitLossSum += lot.getCurrentProfitLoss();
			}
			profitLoss.add(profitLossSum);
		}
	}
	
	private BigDecimal getTotal(int portfolioNumber){
//		for(Integer portfolioNumber : portfolioNumbers){
		getBalanceAndInvestedForPortfolios();
		getAllLotsProfitLoss();
		totalBalance = balanceAndInvestedMoney.get(portfolioNumber).add(new BigDecimal(profitLoss.get(portfolioNumber)));
//		}
		return totalBalance;
	}

	@Override
	public void run() {
		while(true){
			getPortfolioNumbers();
			for(Integer portfolioNumber : portfolioNumbers){
				Database.useInsertIntoPortfolioBalance(portfolioNumber, getTotal(portfolioNumber-1));
			}
			try {
				System.out.println("UpdatePortfolioBalance is sleeping");
				Thread.sleep(3600000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
