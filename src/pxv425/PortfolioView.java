package pxv425;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 * Class which contains the View part of the portfolio
 * 
 * @author Panagiotis Vakalis
 * @version 18-07-2015
 */
public class PortfolioView extends View implements ActionListener, Observer {
	
	private PortfolioModel portfolioModel;
	private JLabel title;
	private JLabel lotsLabel;
	private JLabel portfolioSelectionLabel;
	private JComboBox<String> portfolios;
	private JLabel portfolioTitle;
	private JButton lotsButton;
	private JPanel portfolioInformation;
	private JLabel balanceLabel;
	private JTextArea balanceArea;
	private JLabel investedMoneyLabel;
	private JTextArea investedMoneyArea;
	private JLabel profitLossLabel;
	private JTextArea profitLossArea;
	private JButton continueButton;
	private String command;
	private static Portfolio portfolioSelection;
	private JButton deposit;
	private JButton withdraw;
	private JPanel options;
	private JButton changeName;
	private JLabel totalBalance;
	private JTextArea totalBalanceArea;
	private JPanel totalBalancePanel;
	private JButton totalReturn;

	/**
	 * Constructor of the class
	 * @param portfolioModel
	 * 
	 * @author Panagiotis Vakalis
	 * @version 18-07-2015
	 */
	public PortfolioView(PortfolioModel portfolioModel) {
		super(portfolioModel);
		this.portfolioModel = portfolioModel;
		
		portfolioModel.addObserver(this);
		
		frameSetup();
		Database.useUpdateLotsWithCurrentPrice(PortfolioView.getPortfolioSelection().getNumber());
	}
	
	public static Portfolio getPortfolioSelection(){
		return portfolioSelection;
	}
	
	/**
	 * Method to build the panel
	 * 
	 * @author Panagiotis Vakalis
	 * @version 18-07-2015
	 */
	private void frameSetup(){
		
		setLayout(new GridLayout(10, 1));
		//Title of the panel
//		title = new JLabel("Hello " + portfolioModel.getPortfolios()[0].getInvestor().getFirstName() + " this is your portfolio.");
		title = new JLabel("Hello " + portfolioModel.getClient().getInvestor().getFirstName() + " check your portfolio(s).");

		
		//Portfolio selection
		portfolioSelectionLabel = new JLabel("Select portfolio");
//		portfolios = new JComboBox<Portfolio>(portfolioModel.getPortfolios());
		portfolios = new JComboBox<String>(portfolioModel.getPortfolioNames());
		portfolios.setMaximumRowCount(3);
		
		portfolioSelection = portfolioModel.getPortfolios()[portfolios.getSelectedIndex()];
		
//		portfolioTitle = new JLabel("These are the details from the " + portfolioModel.getPortfolios()[portfolios.getSelectedIndex()].getName() + " portfolio.");
		portfolioTitle = new JLabel("These are the details from the " + portfolioSelection.getName() + " portfolio.");
		
		lotsButton = new JButton("Watch lots");
		lotsButton.setActionCommand("lots");
		lotsButton.addActionListener(this);
		
		//Panel for the portfolio's information
		portfolioInformation = new JPanel(new FlowLayout());
		
		balanceLabel = new JLabel("Balance: ");
		balanceArea = new JTextArea();
		balanceArea.setText(portfolioModel.useGetPortfolioBalance(portfolios.getSelectedIndex()));
		balanceArea.setEditable(false);
		investedMoneyLabel = new JLabel("Invested money ");
		investedMoneyArea = new JTextArea();
		investedMoneyArea.setText(portfolioModel.useGetPortfolioInvestedMoney(portfolios.getSelectedIndex()));
		investedMoneyArea.setEditable(false);
		profitLossLabel = new JLabel("Lots Profit/Loss: ");
		profitLossArea = new JTextArea();
//		profitLossArea.setText(portfolioModel.useGetPortfolioProfitLoss(portfolios.getSelectedIndex()));
		profitLossArea.setText(String.valueOf(portfolioModel.useGetAllLotsProfitLoss(portfolioModel.useGetPortfolio(portfolios.getSelectedIndex()))));
		profitLossArea.setEditable(false);
		
		portfolioInformation.add(balanceLabel);
		portfolioInformation.add(balanceArea);
		portfolioInformation.add(investedMoneyLabel);
		portfolioInformation.add(investedMoneyArea);
		portfolioInformation.add(profitLossLabel);
		portfolioInformation.add(profitLossArea);
		
		//Withdraw, deposit or create a new portfolio options
		deposit = new JButton("Deposit");
		deposit.setActionCommand("deposit");
		deposit.addActionListener(this);
		
		withdraw = new JButton("Withdraw");
		withdraw.setActionCommand("withdraw");
		withdraw.addActionListener(this);
		
//		changeName = new JButton("Change name");
//		changeName.setActionCommand("change");
//		changeName.addActionListener(this);
		
		options = new JPanel(new FlowLayout());
		
		options.add(withdraw);
		options.add(deposit);
		
		totalReturn = new JButton("Total return");
		totalReturn.setActionCommand("return");
		totalReturn.addActionListener(this);
		
		continueButton = new JButton("Continue");
		continueButton.setActionCommand("continue");
		continueButton.addActionListener(this);
		
		totalBalancePanel = new JPanel(new GridLayout(2, 1));
		totalBalance = new JLabel("Total balance");
		totalBalanceArea = new JTextArea(String.valueOf(portfolioModel.getTotalBalance(portfolioModel.useGetPortfolio(portfolios.getSelectedIndex()))));
		totalBalancePanel.add(totalBalance);
		totalBalancePanel.add(totalBalanceArea);
		
		add(title);
		add(portfolioSelectionLabel);
		add(portfolios);
//		add(changeName);
		add(portfolioTitle);
		add(lotsButton);
		add(portfolioInformation);
		add(totalBalancePanel);
		add(options);
		add(totalReturn);
		add(continueButton);
	}

	@Override
	public void update(Observable o, Object arg) {
		if(o instanceof PortfolioModel){
			balanceArea.setText(portfolioModel.useUpdatePortfolioBalanceArea(portfolios.getSelectedIndex()));
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		command = e.getActionCommand();

		if(command.equals("continue")){
			portfolioModel.useChangeToMainView(new MainView(new MainModel(portfolioModel.getClient(), portfolioModel.useGetPortfolio(portfolios.getSelectedIndex()))), portfolioModel.useGetPortfolio(portfolios.getSelectedIndex()));
		}
		if(command.equals("lots")){
			portfolioModel.useChangeToLotsView(new LotsView(new LotsModel(portfolioModel.getClient(), portfolioModel.useGetPortfolio(portfolios.getSelectedIndex()))), portfolioModel.useGetPortfolio(portfolios.getSelectedIndex()));
		}
		if(command.equals("deposit")){
			String amount = JOptionPane.showInputDialog(this, "Enter the amount of money which you want to deposit");
//			portfolioModel.useDeposit(portfolioModel.useGetPortfolio(portfolios.getSelectedIndex()), Double.parseDouble(amount));
			if(amount != null){
//				portfolioModel.useDeposit(portfolioModel.useGetPortfolio(portfolios.getSelectedIndex()), Double.parseDouble(amount));
//				JOptionPane.showMessageDialog(this, portfolioModel.useDeposit(portfolioModel.useGetPortfolio(portfolios.getSelectedIndex()), Double.parseDouble(amount)));
//				JOptionPane.showMessageDialog(this, portfolioModel.useDeposit(portfolioModel.useGetPortfolio(portfolios.getSelectedIndex()), BigDecimal.valueOf(Double.parseDouble(amount))));
				JOptionPane.showMessageDialog(this, portfolioModel.useDeposit(portfolioModel.useGetPortfolio(portfolios.getSelectedIndex()), new BigDecimal(amount)));
//				portfolioModel.useUpdatePortfolioBalance(portfolios.getSelectedIndex());
			}
		}
		if(command.equals("withdraw")){
			String amount = JOptionPane.showInputDialog(this, "Enter the amount of money which you want to withdraw");
//			portfolioModel.useWithdraw(portfolioModel.useGetPortfolio(portfolios.getSelectedIndex()), Double.parseDouble(amount));
			if(amount != null){
//				portfolioModel.useWithdraw(portfolioModel.useGetPortfolio(portfolios.getSelectedIndex()), Double.parseDouble(amount));
//				JOptionPane.showMessageDialog(this, portfolioModel.useWithdraw(portfolioModel.useGetPortfolio(portfolios.getSelectedIndex()), Double.parseDouble(amount)));
//				JOptionPane.showMessageDialog(this, portfolioModel.useWithdraw(portfolioModel.useGetPortfolio(portfolios.getSelectedIndex()), BigDecimal.valueOf(Double.parseDouble(amount))));
				JOptionPane.showMessageDialog(this, portfolioModel.useWithdraw(portfolioModel.useGetPortfolio(portfolios.getSelectedIndex()), new BigDecimal(amount)));
//				portfolioModel.useUpdatePortfolioBalance(portfolios.getSelectedIndex());
			}
		}
		if(command.equals("return")){
//			portfolioModel.useGetTotalReturn(portfolioModel.useGetPortfolio(portfolios.getSelectedIndex()));
//			JOptionPane.showMessageDialog(this, portfolioModel.useGetTotalReturn(portfolioModel.useGetPortfolio(portfolios.getSelectedIndex())), "Total return", JOptionPane.INFORMATION_MESSAGE);
			JOptionPane.showMessageDialog(this, portfolioModel.retrieveTotalReturn(portfolioModel.useGetPortfolio(portfolios.getSelectedIndex())), "Total return", JOptionPane.INFORMATION_MESSAGE);
		}
//		if(command.equals("change")){
//			String newName = JOptionPane.showInputDialog("Enter the new name");
//			if(newName != null){
//				JOptionPane.showMessageDialog(this, portfolioModel.useChangePortfolioName(portfolioModel.useGetPortfolio(portfolios.getSelectedIndex()), newName));
//				portfolios = new JComboBox<String>(portfolioModel.getPortfolioNames());
//			}
//		}
	}

}
