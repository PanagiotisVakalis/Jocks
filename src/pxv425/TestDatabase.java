package pxv425;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;


public class TestDatabase {

	public static void main(String[] args) {
//		Database.registerInvestor("pani.vak@gmail.com", "electro_PA16989", "Panagiotis", "Vakalis", "Which is your nickname", "Pani");
//		Database.registerInvestor("dokoux@gmail.com", "sangallo69", "Xanthi", "Dokou", "Which is your son's nickname", "Pani");
//		Database.createPortfolio("My portfolio", "pani.vak@gmail.com", 10000);
//		Database.createPortfolio("My portfolio", "dokoux@gmail.com", 15000);
//		Database.createPortfolio("My portfolio", "dokoux@gmail.com", 5000);
//		Database.signInInvestor("pani.vak@gmail.com", "electro_pa");
//		Database.updatePortfolioBalance("My portfolio", "pani.vak@gmail.com", -3000);
//		Database.updatePortfolioName("Xanthi's portfolio", "dokoux@gmail.com", "Xanthi");
//		Database.updatePortfolioName("Xanthi", "pani.vak@gmail.com", "Pani");
//		Database.updatePortfolioInvestedMoney("Xanthi", "dokoux@gmail.com", 3000.0);
//		Database.updatePortfolioInvestedMoney("Pani", "pani.vak@gmail.com", 5000.0);
//		Database.updatePortfolioProfitLoss("Pani", "pani.vak@gmail.com", 3.0);
//		Database.updatePortfolioProfitLoss("Xanthi", "dokoux@gmail.com", -3.0);
//		Database.insertStock("GOOG", "Google", "Technology", 519.00);
//		Database.insertStock("MSFT", "Microsoft", "Technology", 44.31);
//		Database.updateStockPrice("MSFT", 50);
//		Database.insertWatch("pani.vak@gmail.com", "MSFT");
//		Database.insertWatch("dokoux@gmail.com", "GOOG");
//		Database.deleteWatch("pani.vak@gmail.com", "MSFT");
//		Database.resetInvestorPassword("pani.vak@gmail.com", "electro_pa");
//		Database.registerInvestor("exs406@student.bham.ac.uk", "exs", "Elena", "Stylianou", "Where are you from", "Cyprus");
//		Database.insertLot("pani.vak@gmail.com", "Panagiotis Vakalis", "ABF.L", 1);
//		Database.insertLot("dokoux@gmail.com", "Xanthi Dokou", "ARM.L", 1);
//		String date = "2015-07-11 14:12:03.941";
//		Database.deleteLot("pani.vak@gmail.com", "Pani", "GOOG", Timestamp.valueOf(date));
//		Database.insertBuy("GOOG", "pani.vak@gmail.com", "Panagiotis Vakalis", 2);
//		Database.insertSell("ADF.L", "pani.vak@gmail.com", "Panagiotis Vakalis", 1);
//		Database.retrieveBuys("pani.vak@gmail.com", "Pani");
//		Database.retrieveSells("pani.vak@gmail.com", "Pani");
//		Database.retrievePortfolio("pani.vak@gmail.com", "Pani");
		try {
			Database.useConnectToDatabase();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		Database.useUpdateStockPrice("PIH", 350.3);
//		Database.deleteStock("COO");
	}

}