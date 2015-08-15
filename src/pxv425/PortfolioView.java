package pxv425;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.math.BigDecimal;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;

public class PortfolioView extends View implements ActionListener, ItemListener, Observer {

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
	private JPanel portfoliosPanel;
	private JPanel lotsPanel;
	private JTable lots;
	private JScrollPane lotsScroll;
	private JPanel portfolioInformationPanel;
	private JPanel initialBalancePanel;
	private JLabel initialBalance;
	private JTextArea initialBalanceArea;
	private JPanel balanceNowPanel;
	private JLabel balanceNow;
	private JTextArea balanceNowArea;
	private JPanel investedMoneyPanel;
	private JLabel investedMoney;
	private JTextArea investedMoneyArea;
	private JPanel profitLossLotsPanel;
	private JLabel profitLossLots;
	private JTextArea profitLossLotsArea;
	private JPanel totalWithdrawsPanel;
	private JLabel totalWithdraws;
	private JTextArea totalWithdrawsArea;
	private JPanel totalDepositsPanel;
	private JLabel totalDeposits;
	private JTextArea totalDepositsArea;
	private JPanel totalReturnPanel;
	private JLabel totalReturn;
	private JTextArea totalReturnArea;
	private JPanel continueButtonPanel;
	private JPanel titlePanel;
	
	
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
	
	private void frameSetup(){
		setLayout(new GridLayout(5, 1));
		setPreferredSize(new Dimension(900, 800));
		
//		//Title panel
//		title = new JLabel("Hello " + portfolioModel.getClient().getInvestor().getFirstName() + " check your portfolio(s).");
//		titlePanel = new JPanel(new FlowLayout());
//		titlePanel.setPreferredSize(new Dimension(1000, 800));
//		
//		titlePanel.add(title);
		
		//Portfolios panel
		portfoliosPanel = new JPanel(new GridLayout(2, 1));
		// Portfolio selection
		portfolioSelectionLabel = new JLabel("Select portfolio");
		portfolios = new JComboBox<String>(portfolioModel.getPortfolioNames());
		portfolios.setMaximumRowCount(3);
		portfolios.addItemListener(this);
		
		//Get the first portfolio as default
		portfolioSelection = portfolioModel.getPortfolios()[portfolios.getSelectedIndex()];
		
		portfoliosPanel.add(portfolioSelectionLabel);
		portfoliosPanel.add(portfolios);
		
		//Lots panel
		lotsPanel = new JPanel(new GridLayout(2, 1));
		
		lotsLabel = new JLabel("These are your lots which are contained in the " + portfolioModel.useGetPortfolio(portfolios.getSelectedIndex()).getName() + " portfolio.");
		//Call method in order to get the lots
		portfolioModel.useGetAllLots(portfolioModel.useGetPortfolio(portfolios.getSelectedIndex()));
		
		// Title for the table's columns
		String[] tableTitle = { "Symbol", "Price bought", "Shares bought",
				"Purchase amount", "Current price", "Current value",
				"Profit / Loss", "Purchase date" };

		lots = new JTable();
		lots.setModel(new NonEditableTable(portfolioModel.useLotsDetails(),
				tableTitle));
		lots.setRowSelectionAllowed(false);

		lotsScroll = new JScrollPane(lots);
		
		lotsPanel.add(lotsLabel);
		lotsPanel.add(lotsScroll);
		
		
		//initial balance panel
		initialBalancePanel = new JPanel(new FlowLayout());
		initialBalance = new JLabel("Initial balance");
//		initialBalanceArea = new JTextArea("£" + String.valueOf(portfolioModel.getInitialBalance(portfolioModel.useGetPortfolio(portfolios.getSelectedIndex()).getNumber())));
		initialBalanceArea = new JTextArea(currencyFormat(portfolioModel.getInitialBalance(portfolioModel.useGetPortfolio(portfolios.getSelectedIndex()).getNumber())));
		initialBalanceArea.setEditable(false);
		initialBalancePanel.add(initialBalance);
		initialBalancePanel.add(initialBalanceArea);
		
		//balance today panel
		balanceNowPanel = new JPanel(new FlowLayout());
		balanceNow = new JLabel("Balance now");
//		balanceNowArea = new JTextArea("£" + String.valueOf(portfolioModel.getBalanceNow(portfolioModel.useGetPortfolio(portfolios.getSelectedIndex()).getNumber())));
		balanceNowArea = new JTextArea(currencyFormat(portfolioModel.getBalanceNow(portfolioModel.useGetPortfolio(portfolios.getSelectedIndex()).getNumber())));
		balanceNowArea.setEditable(false);
		balanceNowPanel.add(balanceNow);
		balanceNowPanel.add(balanceNowArea);
		
		//invested money panel
		investedMoneyPanel = new JPanel(new FlowLayout());
		investedMoney = new JLabel("Invested money");
//		investedMoneyArea = new JTextArea("£" + String.valueOf(portfolioModel.getPortfolioInvestedMoney(portfolioModel.useGetPortfolio(portfolios.getSelectedIndex()).getNumber())));
		investedMoneyArea = new JTextArea(currencyFormat(portfolioModel.getPortfolioInvestedMoney(portfolioModel.useGetPortfolio(portfolios.getSelectedIndex()).getNumber())));
		investedMoneyArea.setEditable(false);
		investedMoneyPanel.add(investedMoney);
		investedMoneyPanel.add(investedMoneyArea);
		
		//profit loss lots panel
		profitLossLotsPanel = new JPanel(new FlowLayout());
		profitLossLots = new JLabel("Lots' profit/loss");
//		profitLossLotsArea = new JTextArea("£" + String.valueOf(portfolioModel.useGetAllLotsProfitLoss(portfolioModel.useGetPortfolio(portfolios.getSelectedIndex()).getNumber())));
		profitLossLotsArea = new JTextArea(currencyFormat(new BigDecimal(portfolioModel.useGetAllLotsProfitLoss(portfolioModel.useGetPortfolio(portfolios.getSelectedIndex()).getNumber()))));
		profitLossLotsArea.setEditable(false);
		profitLossLotsPanel.add(profitLossLots);
		profitLossLotsPanel.add(profitLossLotsArea);
		
		//Total withdraws panel
		totalWithdrawsPanel = new JPanel(new FlowLayout());
		totalWithdraws = new JLabel("Total withdraws");
//		totalWithdrawsArea = new JTextArea("£" + String.valueOf(portfolioModel.getTotalWithdraws(portfolioModel.useGetPortfolio(portfolios.getSelectedIndex()).getNumber())));
		totalWithdrawsArea = new JTextArea(currencyFormat(portfolioModel.getTotalWithdraws(portfolioModel.useGetPortfolio(portfolios.getSelectedIndex()).getNumber())));
		totalWithdrawsArea.setEditable(false);
		totalWithdrawsPanel.add(totalWithdraws);
		totalWithdrawsPanel.add(totalWithdrawsArea);
		
		//Total deposits panel
		totalDepositsPanel = new JPanel(new FlowLayout());
		totalDeposits = new JLabel("Total deposits");
//		totalDepositsArea = new JTextArea("£" + String.valueOf(portfolioModel.getTotalDeposits(portfolioModel.useGetPortfolio(portfolios.getSelectedIndex()).getNumber())));
		totalDepositsArea = new JTextArea(currencyFormat(portfolioModel.getTotalDeposits(portfolioModel.useGetPortfolio(portfolios.getSelectedIndex()).getNumber())));
		totalDepositsArea.setEditable(false);
		totalDepositsPanel.add(totalDeposits);
		totalDepositsPanel.add(totalDepositsArea);
		
		//Total return panel
		totalReturnPanel = new JPanel(new FlowLayout());
		totalReturn = new JLabel("Total return");
		totalReturnArea = new JTextArea(portfolioModel.retrieveTotalReturn(portfolioModel.useGetPortfolio(portfolios.getSelectedIndex()).getNumber()));
		totalReturnArea.setEditable(false);
		totalReturnPanel.add(totalReturn);
		totalReturnPanel.add(totalReturnArea);
		
		//Portfolio information panel
		portfolioInformationPanel = new JPanel(new GridLayout(4, 2));
		portfolioInformationPanel.add(initialBalancePanel);
		portfolioInformationPanel.add(balanceNowPanel);
		portfolioInformationPanel.add(investedMoneyPanel);
		portfolioInformationPanel.add(profitLossLotsPanel);
		portfolioInformationPanel.add(totalWithdrawsPanel);
		portfolioInformationPanel.add(totalDepositsPanel);
		portfolioInformationPanel.add(totalReturnPanel);
		
		// Withdraw, deposit options
		deposit = new JButton("Deposit");
		deposit.setActionCommand("deposit");
		deposit.setToolTipText("Press to deposit money");
		deposit.addActionListener(this);

		withdraw = new JButton("Withdraw");
		withdraw.setActionCommand("withdraw");
		withdraw.setToolTipText("Press to withdraw money");
		withdraw.addActionListener(this);

		options = new JPanel(new FlowLayout());
		options.setPreferredSize(new Dimension(1000, 50));

		options.add(withdraw);
		options.add(deposit);
		
		continueButton = new JButton("Main screen");
		continueButton.setActionCommand("continue");
		continueButton.setToolTipText("Press to go to main screen");
		continueButton.addActionListener(this);
		
		continueButtonPanel = new JPanel(new FlowLayout());
		continueButtonPanel.add(continueButton);
		
//		add(titlePanel);
		add(portfoliosPanel);
		add(lotsPanel);
		add(portfolioInformationPanel);
		add(options);
		add(continueButtonPanel);
	}

	@Override
	public void update(Observable o, Object arg) {
		if(o instanceof PortfolioModel){
			initialBalanceArea.setText(portfolioModel.useUpdatePortfolioInitialBalanceArea(portfolios.getSelectedIndex()));
			investedMoneyArea.setText(portfolioModel.useUpdatePortfolioInvestedMoney(portfolios.getSelectedIndex()));
//			profitLossArea.setText("£" + portfolioModel.useUpdatePortfolioProfitLoss(portfolios.getSelectedIndex()));
			if(portfolioModel.useUpdatePortfolioProfitLoss(portfolios.getSelectedIndex()) != null){
				profitLossLotsArea.setText(portfolioModel.useUpdatePortfolioProfitLoss(portfolios.getSelectedIndex()));
			}
			totalWithdrawsArea.setText(portfolioModel.useUpdateTotalWithdrawsArea(portfolios.getSelectedIndex()));
			totalDepositsArea.setText(portfolioModel.useUpdateTotalDepositsArea(portfolios.getSelectedIndex()));
			balanceNowArea.setText(portfolioModel.useUpdatePortfolioBalanceArea(portfolios.getSelectedIndex()));
			totalReturnArea.setText(portfolioModel.useUpdatePortfolioTotalReturn(portfolios.getSelectedIndex()));
			String[] tableTitle = { "Symbol", "Price bought", "Shares bought",
					"Purchase amount", "Current price", "Current value",
					"Profit / Loss", "Purchase date" };
			lots.setModel(new NonEditableTable(portfolioModel.useLotsDetails(),
					tableTitle));
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		command = e.getActionCommand();

		if(command.equals("continue")){
			portfolioModel.useChangeToMainView(new MainView(new MainModel(portfolioModel.getClient(), portfolioModel.useGetPortfolio(portfolios.getSelectedIndex()))), portfolioModel.useGetPortfolio(portfolios.getSelectedIndex()));
		}
		if(command.equals("deposit")){
			String amount = JOptionPane.showInputDialog(this, "Enter the amount of £ which you want to deposit");
			if(amount != null){
				JOptionPane.showMessageDialog(this, portfolioModel.useDeposit(portfolioModel.useGetPortfolio(portfolios.getSelectedIndex()), new BigDecimal(amount)));
			}
		}
		if(command.equals("withdraw")){
			String amount = JOptionPane.showInputDialog(this, "Enter the amount of £ which you want to withdraw");
			if(amount != null){
				JOptionPane.showMessageDialog(this, portfolioModel.useWithdraw(portfolioModel.useGetPortfolio(portfolios.getSelectedIndex()), new BigDecimal(amount)));
			}
		}
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		if(e.getStateChange() == ItemEvent.SELECTED){
			portfolioModel.usePortfolioChanged(portfolios.getSelectedIndex());
		}
	}
}
