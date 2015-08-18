package Testing;

import static org.junit.Assert.assertEquals;
import java.util.ArrayList;

import org.junit.Test;

import pxv425.Client;
import pxv425.Database;
import pxv425.Investor;
import pxv425.Lot;
import pxv425.Portfolio;
import pxv425.SellModel;
import pxv425.SellView;

/**
 * JUnit tests for the SellModel
 * @author Panagiotis Vakalis
 * @version 2015-08-17
 */
public class SellModelTesting {

	@Test
	/**
	 * Test the useUpdateBalanceNowAndNewInvestedMoney method
	 */
	public void useUpdateBalanceNowAndNewInvestedMoneyTest1(){
		Client client = new Client();
		Investor investor = (Investor) Database.useGetInvestor("pani.vak@gmail.com", "electro_PA16989");
		ArrayList<Portfolio> portfolios = Database.useGetInvestorPortfolioNumber(investor.getId());
		Portfolio portfolio = portfolios.get(0);
		ArrayList<Lot> lots = Database.useGetLotsForASpecificPortfolio(portfolio.getNumber());
		Lot lot = lots.get(0);
		SellModel sellModel = new SellModel(client, portfolio, lot);
		SellView sellView = new SellView(sellModel);
		
		sellView.setShares(10);
		
		sellModel.useUpdateNewBalanceAndNewInvestedMoney(sellModel.getStockPrice(), sellView.getShares());
		
		String expectedBalance = "£1,003.43";
		String expectedInvestedMoney = "£5,982.09";
		
		String resultBalance = sellModel.useUpdateBalanceArea();
		String resultInvestedMoney = sellModel.useUpdateInvesteMoneyArea();
		
		assertEquals(expectedBalance, resultBalance);
		assertEquals(expectedInvestedMoney, resultInvestedMoney);
	}
	
	@Test
	/**
	 * Test the useSellStock method when shares are less than 0
	 */
	public void useSellStockTest1(){
		Client client = new Client();
		Investor investor = (Investor) Database.useGetInvestor("pani.vak@gmail.com", "electro_PA16989");
		ArrayList<Portfolio> portfolios = Database.useGetInvestorPortfolioNumber(investor.getId());
		Portfolio portfolio = portfolios.get(0);
		ArrayList<Lot> lots = Database.useGetLotsForASpecificPortfolio(portfolio.getNumber());
		Lot lot = lots.get(0);
		SellModel sellModel = new SellModel(client, portfolio, lot);
		SellView sellView = new SellView(sellModel);
		
		sellView.setShares(-1);
		
		String expected = "You have not selected the amount of shares";
		String result = sellModel.useSellStock(sellView.getShares());
		
		assertEquals(expected, result);
	}
	
	@Test
	/**
	 * Test the useSellStock method when shares are 0
	 */
	public void useSellStocTest2(){
		Client client = new Client();
		Investor investor = (Investor) Database.useGetInvestor("pani.vak@gmail.com", "electro_PA16989");
		ArrayList<Portfolio> portfolios = Database.useGetInvestorPortfolioNumber(investor.getId());
		Portfolio portfolio = portfolios.get(0);
		ArrayList<Lot> lots = Database.useGetLotsForASpecificPortfolio(portfolio.getNumber());
		Lot lot = lots.get(0);
		SellModel sellModel = new SellModel(client, portfolio, lot);
		SellView sellView = new SellView(sellModel);
		
		sellView.setShares(0);
		
		String expected = "You have not selected the amount of shares";
		String result = sellModel.useSellStock(sellView.getShares());
		
		assertEquals(expected, result);
	}
	
	@Test
	/**
	 * Test the useSellStock method when shares are 0
	 */
	public void useSellStocTest3(){
		Client client = new Client();
		Investor investor = (Investor) Database.useGetInvestor("pani.vak@gmail.com", "electro_PA16989");
		ArrayList<Portfolio> portfolios = Database.useGetInvestorPortfolioNumber(investor.getId());
		Portfolio portfolio = portfolios.get(0);
		ArrayList<Lot> lots = Database.useGetLotsForASpecificPortfolio(portfolio.getNumber());
		Lot lot = lots.get(0);
		SellModel sellModel = new SellModel(client, portfolio, lot);
		SellView sellView = new SellView(sellModel);
		
		sellView.setShares(100000);
		
		String expected = "You are trying to sell amount of shares which you do not have.";
		String result = sellModel.useSellStock(sellView.getShares());
		
		assertEquals(expected, result);
	}
	
	@Test
	/**
	 * Test the useSellStock method when the shares are enough
	 */
	public void useSellStocTest4(){
		Client client = new Client();
		Investor investor = (Investor) Database.useGetInvestor("pani.vak@gmail.com", "electro_PA16989");
		ArrayList<Portfolio> portfolios = Database.useGetInvestorPortfolioNumber(investor.getId());
		Portfolio portfolio = portfolios.get(0);
		ArrayList<Lot> lots = Database.useGetLotsForASpecificPortfolio(portfolio.getNumber());
		Lot lot = lots.get(0);
		SellModel sellModel = new SellModel(client, portfolio, lot);
		SellView sellView = new SellView(sellModel);
		
		sellView.setShares(10);
		
		String expected = "You have sold " + sellView.getShares() + " of " + sellModel.getStockName() + " stock.";
		String result = sellModel.useSellStock(sellView.getShares());
		
		assertEquals(expected, result);
	}
}
