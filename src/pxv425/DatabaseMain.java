package pxv425;

import java.sql.SQLException;
import java.util.ArrayList;

public class DatabaseMain {

	public static void main(String[] args) {
		
//		try {
//			Database.useConnectToDatabase();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		ArrayList<String> symbols = Database.useGetStockSymbols();
		ArrayList<UpdateStock> stockPrice = new ArrayList<>();
		ArrayList<UpdateHistoricalPrices> historicalPrices = new ArrayList<>();


		for(int i = 0; i < symbols.size(); i++){
//			threadsStockPrice.add(new Thread(new UpdateStock(symbols.get(i))));
//			threadsHistoricalPrices.add(new Thread(new UpdateHistoricalPrices(symbols.get(i))));
			stockPrice.add(new UpdateStock(symbols.get(i)));
			historicalPrices.add(new UpdateHistoricalPrices(symbols.get(i)));
		}
		
		while(true){
			if(Model.useIsValidDayAndTime()){
				int i;
				for(i = 0; i < stockPrice.size() && i < historicalPrices.size(); i++){
					stockPrice.get(i).useUpdateStockPrices();
					historicalPrices.get(i).useGetStockPrices();
				}
				i = 0;
			}
		}
		
//		for(Thread t : threadsStockPrice){
//			t.start();
//		}
//		for(Thread t : threadsHistoricalPrices){
////			t.start();
//		}
		
		
	}

}
