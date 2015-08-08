package pxv425;

import java.sql.SQLException;

public class TestDatabaseManagement {

	public static void main(String[] args) {
		try {
			Database.useConnectToDatabase();
//			DatabaseManagement.usePopulateStockTable("/Users/pani/Desktop/FTSE100.csv");
//			Database.insertStock("ULVR.L", "Unilever PLC", null, 0);
			DatabaseManagement.useUpdateStockPrices();
//			System.out.println(DatabaseManagement.getFTSE100Stocks());
//			DatabaseManagement.useGetStockPrices();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
