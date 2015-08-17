package pxv425;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

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
			if(isValidDayAndTime()){
				int i;
				for(i = 0; i < stockPrice.size() && i < historicalPrices.size(); i++){
					stockPrice.get(i).useUpdateStockPrices();
					historicalPrices.get(i).useGetStockPrices();
				}
				i = 0;
			}
			else{
				System.out.println("Market closed");
			}
		}
		
		
	}
	private static boolean isValidDayAndTime(){
		Calendar calendar = Calendar.getInstance();
		int today = calendar.get(Calendar.DAY_OF_WEEK);
		SimpleDateFormat time = new SimpleDateFormat("HH:mm:ss");
		if(today == Calendar.SATURDAY || today == Calendar.SUNDAY){
			return false;
		}
		else{
			if(time.format(calendar.getTime()).compareTo("16:29:59") == 1 || time.format(calendar.getTime()).compareTo("09:59:59") == -1){
				return false;
			}
			else{
				return true;
			}
		}
	}
}
