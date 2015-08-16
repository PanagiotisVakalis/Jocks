package pxv425;

import java.sql.SQLException;
import java.util.ArrayList;

public class DatabaseMain {

	public static void main(String[] args) {
		
		try {
			Database.useConnectToDatabase();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ArrayList<String> symbols = Database.useGetStockSymbols();
		ArrayList<Thread> threadsStockPrice = new ArrayList<Thread>();
		ArrayList<Thread> threadsHistoricalPrices = new ArrayList<Thread>();
//		Thread portfolioBalanceInsertion = new Thread(new UpdatePortfolioBalance());
//		portfolioBalanceInsertion.start();
//		try {
//			portfolioBalanceInsertion.sleep(3600000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

		for(int i = 0; i < symbols.size(); i++){
//			Thread updateStockThread = new Thread(new UpdateStock(symbols.get(i)));
			threadsStockPrice.add(new Thread(new UpdateStock(symbols.get(i))));
			threadsHistoricalPrices.add(new Thread(new UpdateHistoricalPrices(symbols.get(i))));
		}
		
		for(Thread t : threadsStockPrice){
			t.start();
		}
		for(Thread t : threadsHistoricalPrices){
			t.start();
		}
		
		
	}

}
