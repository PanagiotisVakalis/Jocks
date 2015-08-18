package pxv425;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigInteger;
import java.util.Observable;
import java.util.Observer;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
/**
 * View part of the buy screen
 * 
 * @author Panagiotis Vakalis
 * @version 27-07-2015
 *
 */
public class BuyView extends View implements ActionListener, Observer {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BuyModel buyModel;
	private JLabel viewTitle;
	private JPanel stockInformation;
	private JLabel stockSymbolLabel;
	private JTextArea stockSymbol;
	private JLabel stockNameLabel;
	private JTextArea stockName;
	private JLabel priceLabel;
	private JTextArea price;
	private JPanel sharesPanel;
	private JLabel sharesLabel;
	private JTextField shares;
	private JPanel portfolioInformations;
	private JLabel newInvestedMoneyLabel;
	private JTextArea newInvestedMoney;
	private JLabel newBalanceLabel;
	private JTextArea newBalance;
	private JLabel afterBought;
	private String command;
	private JButton check;
	
	/**
	 * Constructor of the class
	 * @param buyModel
	 * 
	 * @author Panagiotis Vakalis
	 * @version 27-07-2015
	 */
	public BuyView(BuyModel buyModel){
		super(buyModel);
		
		this.buyModel = buyModel;
		
		buyModel.addObserver(this);
		
		frameSetup();
	}

	/**
	 * Method which builds the screen
	 * 
	 * @author Panagiotis Vakalis
	 * @version 27-07-2015
	 */
	private void frameSetup(){
		setLayout(new GridLayout(6, 1));
		
		viewTitle = new JLabel("Buy");
	
		//Panel for information regarding stock
		stockInformation = new JPanel(new GridLayout(3, 2));
		
		stockSymbolLabel = new JLabel("Stock symbol");
		stockSymbol = new JTextArea();
		stockSymbol.setBackground(getPopUpWindowColor());
		stockSymbol.setEditable(false);
		stockSymbol.setText(buyModel.getStockSymbol());
		
		stockNameLabel = new JLabel("Stock name");
		stockName = new JTextArea();
		stockName.setBackground(getPopUpWindowColor());
		stockName.setEditable(false);
		stockName.setText(buyModel.getStockName());
		
		priceLabel = new JLabel("Price");
		price = new JTextArea();
		price.setBackground(getPopUpWindowColor());
		price.setEditable(false);
		price.setText("£" + String.valueOf(buyModel.getStockPrice()));
		
		stockInformation.add(stockSymbolLabel);
		stockInformation.add(stockSymbol);
		stockInformation.add(stockNameLabel);
		stockInformation.add(stockName);
		stockInformation.add(priceLabel);
		stockInformation.add(price);
		
		//Panel regarding the amount of shares which the investor wants to buy
		sharesPanel = new JPanel(new FlowLayout());
		sharesPanel.setBackground(getPopUpWindowColor());
		
		sharesLabel = new JLabel("Shares");
	
		shares = new JTextField(5);
		shares.setBackground(getAreaColor());
		
		sharesPanel.add(sharesLabel);
		sharesPanel.add(shares);
		
		afterBought = new JLabel("After trade:");
		
		check = new JButton("Check");
		check.setActionCommand("check");
		check.addActionListener(this);
		
		//Panel regarding the portfolio information
		portfolioInformations = new JPanel(new GridLayout(2, 2));
		
		newInvestedMoneyLabel = new JLabel("Invested money");
		newInvestedMoney = new JTextArea();
		newInvestedMoney.setBackground(getPopUpWindowColor());
		newInvestedMoney.setEditable(false);
		//Initialy will have the same money
		newInvestedMoney.setText(currencyFormat(buyModel.getInvestedMoney()));
		
		newBalanceLabel = new JLabel("Balance");
		newBalance = new JTextArea();
		newBalance.setBackground(getPopUpWindowColor());
		newBalance.setEditable(false);
		//Initially will have the same balance
		newBalance.setText(currencyFormat(buyModel.getBalance()));
		
		portfolioInformations.add(newInvestedMoneyLabel);
		portfolioInformations.add(newInvestedMoney);
		portfolioInformations.add(newBalanceLabel);
		portfolioInformations.add(newBalance);
		
		
		add(viewTitle);
		add(stockInformation);
		add(sharesPanel);
		add(afterBought);
		add(check);
		add(portfolioInformations);
	}
	
	/**
	 * Method to get the shares
	 * @return number of shares
	 * 
	 * @author Panagiotis Vakalis
	 * @version 27-07-2015
	 */
	public int getShares(){
		return Integer.parseInt(shares.getText());
	}
	
	public void setShares(int shares){
		this.shares.setText(String.valueOf(shares));
	}
	
	@Override
	public void update(Observable o, Object arg) {
		if(o instanceof BuyModel){
			newInvestedMoney.setText(currencyFormat(buyModel.useUpdateInvesteMoneyArea()));
			newBalance.setText(currencyFormat(buyModel.useUpdateBalanceArea()));
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		command = e.getActionCommand();
		if(command.equals("check")){
			if(!shares.getText().equals("")){
				buyModel.useUpdateNewBalanceAndNewInvestedMoney(buyModel.getStockPrice(), new BigInteger((shares.getText())));
			}
		}
	}

}
