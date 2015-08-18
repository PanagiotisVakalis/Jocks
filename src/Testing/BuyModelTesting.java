package Testing;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;

import org.junit.Test;

import pxv425.BuyModel;
import pxv425.BuyView;
import pxv425.Client;
import pxv425.Database;
import pxv425.Investor;
import pxv425.Portfolio;
import pxv425.Stock;

/**
 * JUnit tests for the BuyModel
 * @author Panagiotis Vakalis
 * @version 2015-08-17
 */
public class BuyModelTesting {

	@Test
	/**
	 * Test the useUpdateBalanceNowAndNewInvestedMoney method
	 */
	public void useUpdateBalanceNowAndNewInvestedMoneyTest1(){
		Client client = new Client();
		Investor investor = (Investor) Database.useGetInvestor("pani.vak@gmail.com", "electro_PA16989");
		ArrayList<Portfolio> portfolios = Database.useGetInvestorPortfolioNumber(investor.getId());
		Portfolio portfolio = portfolios.get(0);
		ArrayList<Stock> stocks = Database.useGetAllStocks();
		Stock stock = stocks.get(0);
		BuyModel buyModel = new BuyModel(client, portfolio, stock);
		BuyView buyView = new BuyView(buyModel);
		
		buyView.setShares(10);
		
		buyModel.useUpdateNewBalanceAndNewInvestedMoney(buyModel.getStockPrice(), new BigInteger((String.valueOf(buyView.getShares()))));
		
		BigDecimal expectedBalance = new BigDecimal(929.50).setScale(2, BigDecimal.ROUND_DOWN);
		BigDecimal expectedInvestedMoney = new BigDecimal(6056.17).setScale(2, BigDecimal.ROUND_DOWN);
		
		BigDecimal resultBalance = buyModel.useUpdateBalanceArea();
		BigDecimal resultInvestedMoney = buyModel.useUpdateInvesteMoneyArea();
		
		assertEquals(expectedBalance, resultBalance);
		assertEquals(expectedInvestedMoney, resultInvestedMoney);
	}
	
	@Test
	/**
	 * Test the useBuyStock method when shares are less than 0
	 */
	public void useBuyStockTest1(){
		Client client = new Client();
		Investor investor = (Investor) Database.useGetInvestor("pani.vak@gmail.com", "electro_PA16989");
		ArrayList<Portfolio> portfolios = Database.useGetInvestorPortfolioNumber(investor.getId());
		Portfolio portfolio = portfolios.get(0);
		ArrayList<Stock> stocks = Database.useGetAllStocks();
		Stock stock = stocks.get(0);
		BuyModel buyModel = new BuyModel(client, portfolio, stock);
		BuyView buyView = new BuyView(buyModel);
		
		buyView.setShares(-1);
		
		String expected = "You have not selected the amount of shares";
		String result = buyModel.useBuyStock(buyView.getShares());
		
		assertEquals(expected, result);
	}
	
	@Test
	/**
	 * Test the useBuyStock method when shares are 0
	 */
	public void useBuyStockTest2(){
		Client client = new Client();
		Investor investor = (Investor) Database.useGetInvestor("pani.vak@gmail.com", "electro_PA16989");
		ArrayList<Portfolio> portfolios = Database.useGetInvestorPortfolioNumber(investor.getId());
		Portfolio portfolio = portfolios.get(0);
		ArrayList<Stock> stocks = Database.useGetAllStocks();
		Stock stock = stocks.get(0);
		BuyModel buyModel = new BuyModel(client, portfolio, stock);
		BuyView buyView = new BuyView(buyModel);
		
		buyView.setShares(0);
		
		String expected = "You have not selected the amount of shares";
		String result = buyModel.useBuyStock(buyView.getShares());
		
		assertEquals(expected, result);
	}
	
	@Test
	/**
	 * Test the useBuyStock method when balance is not enough
	 */
	public void useBuyStockTest3(){
		Client client = new Client();
		Investor investor = (Investor) Database.useGetInvestor("pani.vak@gmail.com", "electro_PA16989");
		ArrayList<Portfolio> portfolios = Database.useGetInvestorPortfolioNumber(investor.getId());
		Portfolio portfolio = portfolios.get(0);
		ArrayList<Stock> stocks = Database.useGetAllStocks();
		Stock stock = stocks.get(0);
		BuyModel buyModel = new BuyModel(client, portfolio, stock);
		BuyView buyView = new BuyView(buyModel);
		
		buyView.setShares(10000);
		
		String expected = "You do not have enough balance";
		String result = buyModel.useBuyStock(buyView.getShares());
		
		assertEquals(expected, result);
	}
	
	@Test
	/**
	 * Test the useBuyStock method when balance is enough
	 */
	public void useBuyStockTest4(){
		Client client = new Client();
		Investor investor = (Investor) Database.useGetInvestor("pani.vak@gmail.com", "electro_PA16989");
		ArrayList<Portfolio> portfolios = Database.useGetInvestorPortfolioNumber(investor.getId());
		Portfolio portfolio = portfolios.get(0);
		ArrayList<Stock> stocks = Database.useGetAllStocks();
		Stock stock = stocks.get(0);
		BuyModel buyModel = new BuyModel(client, portfolio, stock);
		BuyView buyView = new BuyView(buyModel);
		
		buyView.setShares(10);
		
		String expected = "You have bought " + buyView.getShares() + " of " + stock.getName() + " stock.";
		String result = buyModel.useBuyStock(buyView.getShares());
		
		assertEquals(expected, result);
	}
}
