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
import javax.swing.JOptionPane;
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
//	private JButton upButton;
//	private JButton downButton;
	private ImageIcon upImage;
	private ImageIcon downImage;
//	private JTextArea shares;
	private JTextField shares;
	private JPanel portfolioInformations;
	private JLabel newInvestedMoneyLabel;
	private JTextArea newInvestedMoney;
	private JLabel newBalanceLabel;
	private JTextArea newBalance;
	private JPanel buttons;
	private JButton confirmButton;
	private JButton backButton;
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
		setLayout(new GridLayout(7, 1));
		
		viewTitle = new JLabel("Buy");
	
		//Panel for information regarding stock
		stockInformation = new JPanel(new GridLayout(3, 2));
		
		stockSymbolLabel = new JLabel("Stock symbol");
		stockSymbol = new JTextArea();
		stockSymbol.setEditable(false);
		stockSymbol.setText(buyModel.getStockSymbol());
		
		stockNameLabel = new JLabel("Stock name");
		stockName = new JTextArea();
		stockName.setEditable(false);
		stockName.setText(buyModel.getStockName());
		
		priceLabel = new JLabel("Price");
		price = new JTextArea();
		price.setEditable(false);
		price.setText(String.valueOf(buyModel.getStockPrice()));
		
		stockInformation.add(stockSymbolLabel);
		stockInformation.add(stockSymbol);
		stockInformation.add(stockNameLabel);
		stockInformation.add(stockName);
		stockInformation.add(priceLabel);
		stockInformation.add(price);
		
		//Panel regarding the amount of shares which the investor wants to buy
		sharesPanel = new JPanel(new FlowLayout());
		
		sharesLabel = new JLabel("Shares");
		
////		upImage = new ImageIcon(".//images//UpButtonImage.jpeg");
//		upButton = new JButton("Increase");
//		upButton.setActionCommand("increase");
//		upButton.addActionListener(this);
		
////		downImage = new ImageIcon(".//images//DownButtonImage.jpeg");
//		downButton = new JButton("Decrease");
//		downButton.setActionCommand("decrease");
//		downButton.addActionListener(this);
		
//		shares = new JTextArea();
//		shares.setEditable(false);
//		shares.setText(String.valueOf(buyModel.getSharesCount()));
		
		shares = new JTextField(5);
		
		sharesPanel.add(sharesLabel);
//		sharesPanel.add(upButton);
//		sharesPanel.add(downButton);
		sharesPanel.add(shares);
		
		afterBought = new JLabel("After trade:");
		
		check = new JButton("Check");
		check.setActionCommand("check");
		check.addActionListener(this);
		
		//Panel regarding the portfolio information
		portfolioInformations = new JPanel(new GridLayout(2, 2));
		
		newInvestedMoneyLabel = new JLabel("Invested money");
		newInvestedMoney = new JTextArea();
		newInvestedMoney.setEditable(false);
		//Initialy will have the same money
		newInvestedMoney.setText(String.valueOf(buyModel.getInvestedMoney()));
		
		newBalanceLabel = new JLabel("Balance");
		newBalance = new JTextArea();
		newBalance.setEditable(false);
		//Initially will have the same balance
		newBalance.setText(String.valueOf(buyModel.getBalance()));
		
		portfolioInformations.add(newInvestedMoneyLabel);
		portfolioInformations.add(newInvestedMoney);
		portfolioInformations.add(newBalanceLabel);
		portfolioInformations.add(newBalance);
		
		//Panel for the bottom buttons
		buttons = new JPanel(new FlowLayout());
		
		backButton = new JButton("Back");
		backButton.setActionCommand("back");
		backButton.addActionListener(this);
		
		confirmButton = new JButton("Confirm");
		confirmButton.setActionCommand("confirm");
		confirmButton.addActionListener(this);
		
		buttons.add(backButton);
		buttons.add(confirmButton);
		
		add(viewTitle);
		add(stockInformation);
		add(sharesPanel);
		add(afterBought);
		add(check);
		add(portfolioInformations);
		add(buttons);
	}
	
	@Override
	public void update(Observable o, Object arg) {
		if(o instanceof BuyModel){
//			shares.setText(String.valueOf(arg));
//			newInvestedMoney.setText(String.valueOf(buyModel.useUpdateInvestedMoney(buyModel.getStockPrice(), Integer.parseInt(shares.getText()))));
//			newInvestedMoney.setText(buyModel.useUpdateInvesteMoneyArea(buyModel.getStockPrice(), Integer.parseInt(shares.getText())));
			newInvestedMoney.setText(buyModel.useUpdateInvesteMoneyArea());
			newBalance.setText(buyModel.useUpdateBalanceArea());
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		command = e.getActionCommand();
		
//		if(command.equals("increase")){
//			buyModel.useIncreaseSharesCount();
//		}
//		if(command.equals("decrease")){
//			buyModel.useDecreaseSharesCount();
//		}
		if(command.equals("back")){
			buyModel.useChangeToMainView(new MainView(new MainModel(buyModel.getClient(), buyModel.getPortfolio())));
		}
		if(command.equals("confirm")){
			JOptionPane.showMessageDialog(this, buyModel.useBuyStock(Integer.parseInt(shares.getText())));
//			portfolioModel.useChangeToMainView(new MainView(new MainModel(portfolioModel.getClient(), portfolioModel.useGetPortfolio(portfolios.getSelectedIndex()))), portfolioModel.useGetPortfolio(portfolios.getSelectedIndex()));
			buyModel.useChangeToMainView(new MainView(new MainModel(buyModel.getClient(), buyModel.getPortfolio())));
		}
		if(command.equals("check")){
			if(!shares.getText().equals("")){
				buyModel.useUpdateInvestedMoney(buyModel.getStockPrice(), new BigInteger(shares.getText()));
				buyModel.useUpdateBalance(buyModel.getStockPrice(), new BigInteger(shares.getText()));
			}
		}
	}

}
