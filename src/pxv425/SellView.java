package pxv425;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * Class which contains the view part of the sell screen
 * 
 * @author Panagiotis Vakalis
 * @version 28-07-2015
 *
 */
public class SellView extends View implements ActionListener, Observer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private SellModel sellModel;
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
	private JLabel afterSell;
	private String command;
	private JButton check;
	private JLabel sharesBoughtLabel;
	private JTextArea sharesBoughtArea;

	/**
	 * Constructor of the class
	 * 
	 * @param sellModel
	 * 
	 * @author Panagiotis Vakalis
	 * @version 28-07-2015
	 */
	public SellView(SellModel sellModel) {
		super(sellModel);

		this.sellModel = sellModel;

		sellModel.addObserver(this);

		frameSetup();
	}

	/**
	 * Method which builds the screen
	 * 
	 * @author Panagiotis Vakalis
	 * @version 28-07-2015
	 */
	private void frameSetup() {
		setLayout(new GridLayout(6, 1));

		viewTitle = new JLabel("Sell");

		// Panel for information regarding stock
		stockInformation = new JPanel(new GridLayout(4, 2));

		stockSymbolLabel = new JLabel("Stock symbol");
		stockSymbol = new JTextArea();
		stockSymbol.setBackground(getPopUpWindowColor());
		stockSymbol.setEditable(false);
		stockSymbol.setText(sellModel.getStockSymbol());

		stockNameLabel = new JLabel("Stock name");
		stockName = new JTextArea();
		stockName.setBackground(getPopUpWindowColor());
		stockName.setEditable(false);
		stockName.setText(sellModel.getStockName());

		sharesBoughtLabel = new JLabel("Your shares");
		sharesBoughtArea = new JTextArea();
		sharesBoughtArea.setBackground(getPopUpWindowColor());
		sharesBoughtArea.setEditable(false);
		sharesBoughtArea.setText(String.valueOf(sellModel.getBoughtShares()));

		priceLabel = new JLabel("Price");
		price = new JTextArea();
		price.setBackground(getPopUpWindowColor());
		price.setEditable(false);
		price.setText("£" + String.valueOf(sellModel.getStockPrice()));

		stockInformation.add(stockSymbolLabel);
		stockInformation.add(stockSymbol);
		stockInformation.add(stockNameLabel);
		stockInformation.add(stockName);
		stockInformation.add(sharesBoughtLabel);
		stockInformation.add(sharesBoughtArea);
		stockInformation.add(priceLabel);
		stockInformation.add(price);

		// Panel regarding the amount of shares which the investor wants to buy
		sharesPanel = new JPanel(new FlowLayout());
		sharesPanel.setBackground(getPopUpWindowColor());

		sharesLabel = new JLabel("Shares");

		shares = new JTextField(5);
		shares.setBackground(getAreaColor());

		sharesPanel.add(sharesLabel);
		sharesPanel.add(shares);

		afterSell = new JLabel("After trade:");

		check = new JButton("Check");
		check.setActionCommand("check");
		check.addActionListener(this);

		// Panel regarding the portfolio information
		portfolioInformations = new JPanel(new GridLayout(2, 2));
		portfolioInformations.setBackground(getPopUpWindowColor());

		newInvestedMoneyLabel = new JLabel("Invested money");
		newInvestedMoney = new JTextArea();
		newInvestedMoney.setEditable(false);
		// Initially will have the same money
		newInvestedMoney.setText(currencyFormat(sellModel.getInvestedMoney()));
		newInvestedMoney.setBackground(getPopUpWindowColor());

		newBalanceLabel = new JLabel("Balance");
		newBalance = new JTextArea();
		newBalance.setEditable(false);
		// Initially will have the same balance
		newBalance.setText(currencyFormat(sellModel.getBalance()));
		newBalance.setBackground(getPopUpWindowColor());

		portfolioInformations.add(newInvestedMoneyLabel);
		portfolioInformations.add(newInvestedMoney);
		portfolioInformations.add(newBalanceLabel);
		portfolioInformations.add(newBalance);

		add(viewTitle);
		add(stockInformation);
		add(sharesPanel);
		add(afterSell);
		add(check);
		add(portfolioInformations);
	}

	/**
	 * Method to get the shares outside of the class
	 * 
	 * @return number of shares
	 * @return 0, if the shares is not a number or if the textfield is equal to
	 *         ""
	 * 
	 * @author Panagiotis Vakalis
	 * @version 28-07-2015
	 */
	public int getShares() {
		if (!shares.getText().equals("")) {
			try {
				return Integer.parseInt(shares.getText());
			} catch (NumberFormatException e) {
				/*
				 * If user enters a string
				 */
				return 0;
			}
		} else {
			return 0;
		}
	}

	/**
	 * Method to set the shares outside of the class
	 * 
	 * @param number
	 *            of shares
	 * 
	 * @author Panagiotis Vakalis
	 * @version 28-07-2015
	 */
	public void setShares(int shares) {
		this.shares.setText(String.valueOf(shares));
	}

	@Override
	public void update(Observable o, Object arg) {
		if (o instanceof SellModel) {
			newInvestedMoney.setText(sellModel.useUpdateInvesteMoneyArea());
			newBalance.setText(sellModel.useUpdateBalanceArea());
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		command = e.getActionCommand();
		if (command.equals("check")) {
			if (!shares.getText().equals("")) {
				try{
					sellModel.useUpdateNewBalanceAndNewInvestedMoney(
							sellModel.getStockPrice(),
							Integer.parseInt(shares.getText()));
				} catch (NumberFormatException nFE){
					JOptionPane.showMessageDialog(this, "You have to enter an integer", "Check warning", JOptionPane.WARNING_MESSAGE);
				}
			}
		}
	}

}
