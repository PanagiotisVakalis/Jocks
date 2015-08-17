package Testing;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import pxv425.Client;
import pxv425.Database;
import pxv425.Investor;
import pxv425.MainModel;
import pxv425.Portfolio;

/**
 * JUnit tests for the MainModel
 * @author Panagiotis Vakalis
 * @version 2015-08-17
 */
public class MainModelTesting {

	@Test
	/**
	 * Test the useCalculateAveragePrices method for
	 * an array of prices, for 5 days
	 */
	public void useCalculateAveragePricesTest1(){
		
		Client client = new Client();
		MainModel mainModel = new MainModel(client, null);
		
		/*
		 * The arraylist of prices
		 */
		ArrayList<Double> prices = new ArrayList<>();
		prices.add(1319.0);
		prices.add(1354.0);
		prices.add(1370.0);
		prices.add(1353.0);
		prices.add(1335.0);
		prices.add(1342.0);
		prices.add(1345.0);
		
		int days = 5;
		
		/*
		 * The expected arraylist
		 */
		ArrayList<Double> expected = new ArrayList<>();
		expected.add(1346.2);
		expected.add(1350.8);
		expected.add(1349.0);
		
		/*
		 * The result arraylist
		 */
		ArrayList<Double> result = mainModel.useCalculateAveragePrices(prices, days);
		
		assertEquals(expected, result);
	}
	
	@Test
	/**
	 * Test the useCalculateAveragePrices method for
	 * an array of prices containing only one value, for 2 days
	 */
	public void useCalculateAveragePricesTest2(){
		
		Client client = new Client();
		MainModel mainModel = new MainModel(client, null);
		
		/*
		 * The arraylist of prices
		 */
		ArrayList<Double> prices = new ArrayList<>();
		prices.add(1319.0);
		prices.add(1354.0);
		
		int days = 2;
		
		/*
		 * The expected arraylist
		 */
		ArrayList<Double> expected = new ArrayList<>();
		expected.add(1336.5);
		
		/*
		 * The result arraylist
		 */
		ArrayList<Double> result = mainModel.useCalculateAveragePrices(prices, days);
		
		assertEquals(expected, result);
	}
	
	@Test
	/**
	 * Test the useGetAllLotsProfitLoss method
	 */
	public void useGetAllLotsProfitLossTest(){
		
		Client client = new Client();
		Investor investor = (Investor) Database.useGetInvestor("pani.vak@gmail.com", "electro_PA16989");
		ArrayList<Portfolio> portfolios = Database.useGetInvestorPortfolioNumber(investor.getId());
		Portfolio portfolio = portfolios.get(0);
		MainModel mainModel = new MainModel(client, portfolio);
		
		
		double expected = -200;
		double result = mainModel.useGetAllLotsProfitLoss(portfolio);
		
		
		assertEquals(expected, result, 0.001);
	}
}
