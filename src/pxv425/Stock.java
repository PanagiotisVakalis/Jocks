package pxv425;
/**
 * Class which contains the stock's details
 * 
 * @author Panagiotis Vakalis
 * @version 20-07-2015
 *
 */
public class Stock {

	private String symbol;
	private String name;
	private String industry;
	private double price;
	
	/**
	 * Constructor of the class
	 * @param symbol
	 * @param name
	 * @param industry
	 * @param price
	 * 
	 * @author Panagiotis Vakalis
	 * @version 20-07-2015
	 */
	public Stock(String symbol, String name, String industry, double price){
		this.symbol = symbol;
		this.name = name;
		this.industry = industry;
		this.price = price;
	}

	/**
	 * Method to get the symbol outside the class
	 * @return symbol
	 * 
	 * @author Panagiotis Vakalis
	 * @version 20-07-2015
	 */
	public String getSymbol() {
		return symbol;
	}

	/**
	 * Method to set the symbol outside the class
	 * @param symbol
	 * 
	 * @author Panagiotis Vakalis
	 * @version 20-07-2015
	 */
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	/**
	 * Method to get the name outside the class
	 * @return name
	 * 
	 * @author Panagiotis Vakalis
	 * @version 20-07-2015
	 */
	public String getName() {
		return name;
	}

	/**
	 * Method to set the name outside the class
	 * @param name
	 * 
	 * @author Panagiotis Vakalis
	 * @version 20-07-2015
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Method to get the industry outside the class
	 * @return industry
	 * 
	 * @author Panagiotis Vakalis
	 * @version 20-07-2015
	 */
	public String getIndustry() {
		return industry;
	}

	/**
	 * Method to set the industry outside the class
	 * @param industry
	 * 
	 * @author Panagiotis Vakalis
	 * @version 20-07-2015
	 */
	public void setIndustry(String industry) {
		this.industry = industry;
	}

	/**
	 * Method to get the price outside the class
	 * @return price
	 * 
	 * @author Panagiotis Vakalis
	 * @version 20-07-2015
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * Method to set the price outside the class
	 * @param price
	 * 
	 * @author Panagiotis Vakalis
	 * @version 20-07-2015
	 */
	public void setPrice(double price) {
		this.price = price;
	}
}
