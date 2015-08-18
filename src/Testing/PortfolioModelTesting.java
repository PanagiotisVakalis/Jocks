package Testing;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;

import org.junit.Test;

import pxv425.Client;
import pxv425.Database;
import pxv425.Investor;
import pxv425.Model;
import pxv425.Portfolio;
import pxv425.PortfolioModel;
import pxv425.View;

/**
 * JUnit tests for the PorfolioModel
 * 
 * @author Panagiotis Vakalis
 * @version 2015-08-17
 */
public class PortfolioModelTesting {

	@Test
	/**
	 * Test the useDeposit method with 0 amount
	 */
	public void useDepositTest1() {
		Client client = new Client();
		Investor investor = (Investor) Database.useGetInvestor(
				"pani.vak@gmail.com", "electro_PA16989");
		client.setInvestor(investor);
		PortfolioModel portfolioModel = new PortfolioModel(client);
		BigDecimal amount = new BigDecimal(0);
		Portfolio[] portfoliosArray = portfolioModel.getPortfolios();
		Portfolio portfolio = portfolioModel.useGetPortfolio(0);

		portfolioModel.useDeposit(portfolio, amount);

		String expected = "£10,000.00";
		String result = View
				.currencyFormat(portfolioModel.useGetBalanceNow(41));

		assertEquals(expected, result);
	}

	@Test
	/**
	 * Test the useDeposit method with 5000 amount
	 */
	public void useDepositTest2() {
		Client client = new Client();
		Investor investor = (Investor) Database.useGetInvestor(
				"pani.vak@gmail.com", "electro_PA16989");
		client.setInvestor(investor);
		PortfolioModel portfolioModel = new PortfolioModel(client);
		BigDecimal amount = new BigDecimal(5000);
		Portfolio[] portfoliosArray = portfolioModel.getPortfolios();
		Portfolio portfolio = portfolioModel.useGetPortfolio(0);

		portfolioModel.useDeposit(portfolio, amount);

		String expected = "£15,000.00";
		String result = View
				.currencyFormat(portfolioModel.useGetBalanceNow(41));

		assertEquals(expected, result);
	}

	@Test
	/**
	 * Test the useWithdraw method with 0 amount
	 */
	public void useWithdrawTest1() {
		Client client = new Client();
		Investor investor = (Investor) Database.useGetInvestor(
				"pani.vak@gmail.com", "electro_PA16989");
		client.setInvestor(investor);
		PortfolioModel portfolioModel = new PortfolioModel(client);
		BigDecimal amount = new BigDecimal(0);
		Portfolio[] portfoliosArray = portfolioModel.getPortfolios();
		Portfolio portfolio = portfolioModel.useGetPortfolio(0);

		portfolioModel.useWithdraw(portfolio, amount);

		String expected = "£15,000.00";
		String result = View
				.currencyFormat(portfolioModel.useGetBalanceNow(41));

		assertEquals(expected, result);
	}

	@Test
	/**
	 * Test the useWithdraw method with 5000 amount
	 */
	public void useWithdrawTest2() {
		Client client = new Client();
		Investor investor = (Investor) Database.useGetInvestor(
				"pani.vak@gmail.com", "electro_PA16989");
		client.setInvestor(investor);
		PortfolioModel portfolioModel = new PortfolioModel(client);
		BigDecimal amount = new BigDecimal(5000);
		Portfolio[] portfoliosArray = portfolioModel.getPortfolios();
		Portfolio portfolio = portfolioModel.useGetPortfolio(0);

		portfolioModel.useWithdraw(portfolio, amount);

		String expected = "£10,000.00";
		String result = View
				.currencyFormat(portfolioModel.useGetBalanceNow(41));

		assertEquals(expected, result);
	}

	@Test
	/**
	 * Test the useWithdraw method with amount more than the balance
	 */
	public void useWithdrawTest3() {
		Client client = new Client();
		Investor investor = (Investor) Database.useGetInvestor(
				"pani.vak@gmail.com", "electro_PA16989");
		client.setInvestor(investor);
		PortfolioModel portfolioModel = new PortfolioModel(client);
		BigDecimal amount = new BigDecimal(500000);
		Portfolio[] portfoliosArray = portfolioModel.getPortfolios();
		Portfolio portfolio = portfolioModel.useGetPortfolio(0);

		String expected = "The available balance is less than the amount you want to withdraw";
		String result = portfolioModel.useWithdraw(portfolio, amount);

		assertEquals(expected, result);
	}

	@Test
	/**
	 * Test the retrieveTotalReturn method
	 */
	public void retrieveTotalReturnTest() {
		/*
		 * I have changed the values in database. I did the following: initial
		 * balance = 10000 balance now = 5000 withdraw = 1000 withdraw = 2000
		 * deposit = 3000 deposit = 5000 invested money = 2000
		 * 
		 * totalReturn = 5000 - 10000 + 3000 - 8000 + 2000 = -8000
		 */
		Client client = new Client();
		Investor investor = (Investor) Database.useGetInvestor(
				"pani.vak@gmail.com", "electro_PA16989");
		client.setInvestor(investor);
		PortfolioModel portfolioModel = new PortfolioModel(client);
		Portfolio[] portfoliosArray = portfolioModel.getPortfolios();
		Portfolio portfolio = portfolioModel.useGetPortfolio(0);

		String expected = "-£8,000.00";
		String result = portfolioModel.retrieveTotalReturn(portfolio
				.getNumber());

		assertEquals(expected, result);
	}
}
