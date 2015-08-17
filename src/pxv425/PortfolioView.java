package pxv425;

import java.awt.Color;
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
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

public class PortfolioView extends View implements ActionListener, ItemListener, Observer {
	
	private PortfolioModel portfolioModel;
	private static Portfolio portfolioSelection;
	private JPanel portfoliosPanel;
	private JLabel portfolioSelectionLabel;
	private JComboBox<String> portfolios;
	private JPanel lotsPanel;
	private JLabel lotsLabel;
	private JTable lots;
	private JScrollPane lotsScroll;
	private JPanel historicalInformationPanel;
	private JPanel currentInformationPanel;
	private JPanel portfolioInformationPanel;
	private JLabel initialBalance;
	private JPanel initialBalancePanel;
	private JTextArea initialBalanceArea;
	private JPanel initialBalanceAreaPanel;
	private JLabel totalWithdraws;
	private JPanel totalWithdrawsPanel;
	private JTextArea totalWithdrawsArea;
	private JPanel totalWithdrawsAreaPanel;
	private JLabel totalDeposits;
	private JPanel totalDepositsPanel;
	private JTextArea totalDepositsArea;
	private JPanel totalDepositsAreaPanel;
	private JLabel balanceNow;
	private JPanel balanceNowPanel;
	private JTextArea balanceNowArea;
	private JPanel balanceNowAreaPanel;
	private JLabel investedMoney;
	private JPanel investedMoneyPanel;
	private JTextArea investedMoneyArea;
	private JPanel investedMoneyAreaPanel;
	private JLabel profitLossLots;
	private JPanel profitLossLotsPanel;
	private JTextArea profitLossLotsArea;
	private JPanel profitLossLotsAreaPanel;
	private JPanel totalReturnPanel;
	private JLabel totalReturn;
	private JTextArea totalReturnArea;
	private JButton deposit;
	private JButton withdraw;
	private JPanel options;
	private JButton continueButton;
	private JPanel continueButtonPanel;
	private String command;
	private DefaultTableCellRenderer rightAlign;
	private DefaultTableCellRenderer centerAlign;
	private JPanel totalBalancePanel;
	private JLabel totalBalance;
	private JTextArea totalBalanceArea;
	private JPanel total;

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

	private void frameSetup() {
		setLayout(new GridLayout(6, 1));
		setPreferredSize(new Dimension(900, 800));
		setBackground(getWindowColor());

		// Portfolios panel
		portfoliosPanel = new JPanel(new GridLayout(2, 1));
		portfoliosPanel.setBackground(getWindowColor());
		// Portfolio selection
		portfolioSelectionLabel = new JLabel("Select portfolio");
		portfolios = new JComboBox<String>(portfolioModel.getPortfolioNames());
		portfolios.setMaximumRowCount(3);
		portfolios.addItemListener(this);

		// Get the first portfolio as default
		portfolioSelection = portfolioModel.getPortfolios()[portfolios
				.getSelectedIndex()];
		
		portfoliosPanel.add(portfolioSelectionLabel);
		portfoliosPanel.add(portfolios);
		
		// Lots panel
		lotsPanel = new JPanel(new GridLayout(2, 1));
		lotsPanel.setBackground(getWindowColor());

		lotsLabel = new JLabel(
				"These are your lots which are contained in the selected portfolio.");
		// Call method in order to get the lots
		portfolioModel.useGetAllLots(portfolioModel.useGetPortfolio(portfolios
				.getSelectedIndex()));

		// Title for the table's columns
		String[] tableTitle = { "Symbol", "Price bought", "Shares",
				"Purchase amount", "Current price", "Current value",
				"Profit / Loss", "Purchase date" };

		lots = new JTable();
		lots.setModel(new NonEditableTable(portfolioModel.useLotsDetails(),
				tableTitle));
		lots.setRowSelectionAllowed(false);
		
		//Align money to the right
		rightAlign = new DefaultTableCellRenderer();
		rightAlign.setHorizontalAlignment(SwingConstants.RIGHT);
		lots.getColumnModel().getColumn(1).setCellRenderer(rightAlign);
		lots.getColumnModel().getColumn(2).setCellRenderer(rightAlign);
		lots.getColumnModel().getColumn(3).setCellRenderer(rightAlign);
		lots.getColumnModel().getColumn(4).setCellRenderer(rightAlign);
		lots.getColumnModel().getColumn(5).setCellRenderer(rightAlign);
		lots.getColumnModel().getColumn(6).setCellRenderer(rightAlign);

		lotsScroll = new JScrollPane(lots);
		
		lotsPanel.add(lotsLabel);
		lotsPanel.add(lotsScroll);
		
		//Initial balance
		initialBalance = new JLabel("Initial balance", JLabel.LEFT);
//		initialBalancePanel = new JPanel();
//		initialBalancePanel.add(initialBalance, JPanel.LEFT_ALIGNMENT);
		initialBalanceArea = new JTextArea(currencyFormat(portfolioModel.getInitialBalance(portfolioModel.useGetPortfolio(portfolios.getSelectedIndex()).getNumber())));
		initialBalanceArea.setEditable(false);
		initialBalanceArea.setBackground(getWindowColor());
//		initialBalanceAreaPanel = new JPanel();
//		initialBalanceAreaPanel.add(initialBalanceArea, JPanel.RIGHT_ALIGNMENT);
		
		//Total withdraws
		totalWithdraws = new JLabel("Total withdraws", JLabel.LEFT);
//		totalWithdrawsPanel = new JPanel();
//		totalWithdrawsPanel.add(totalWithdraws, JPanel.LEFT_ALIGNMENT);
		totalWithdrawsArea = new JTextArea(currencyFormat(portfolioModel.getTotalWithdraws(portfolioModel.useGetPortfolio(portfolios.getSelectedIndex()).getNumber())));
		totalWithdrawsArea.setEditable(false);
		totalWithdrawsArea.setBackground(getWindowColor());
//		totalWithdrawsAreaPanel = new JPanel();
//		totalWithdrawsAreaPanel.add(totalWithdrawsArea, JPanel.RIGHT_ALIGNMENT);
		
		//Total deposits
		totalDeposits = new JLabel("Total deposits", JLabel.LEFT);
//		totalDepositsPanel = new JPanel();
//		totalDepositsPanel.add(totalDeposits, JPanel.LEFT_ALIGNMENT);
		totalDepositsArea = new JTextArea(currencyFormat(portfolioModel.getTotalDeposits(portfolioModel.useGetPortfolio(portfolios.getSelectedIndex()).getNumber())));
		totalDepositsArea.setEditable(false);
		totalDepositsArea.setBackground(getWindowColor());
//		totalDepositsAreaPanel = new JPanel();
//		totalDepositsAreaPanel.add(totalDepositsArea, JPanel.RIGHT_ALIGNMENT);
		
		//Historical information panel
		historicalInformationPanel = new JPanel(new GridLayout(3, 3));
		
		historicalInformationPanel.setBackground(getWindowColor());
		
		historicalInformationPanel.add(initialBalance);
		historicalInformationPanel.add(initialBalanceArea);
		historicalInformationPanel.add(totalWithdraws);
		historicalInformationPanel.add(totalWithdrawsArea);
		historicalInformationPanel.add(totalDeposits);
		historicalInformationPanel.add(totalDepositsArea);
		
		//Balance now
		balanceNow = new JLabel("Balance now", JLabel.LEFT);
//		balanceNowPanel = new JPanel();
//		balanceNowPanel.add(balanceNow, JPanel.LEFT_ALIGNMENT);
		balanceNowArea = new JTextArea(currencyFormat(portfolioModel.getBalanceNow(portfolioModel.useGetPortfolio(portfolios.getSelectedIndex()).getNumber())));
		balanceNowArea.setEditable(false);
		balanceNowArea.setBackground(getWindowColor());
//		balanceNowAreaPanel = new JPanel();
//		balanceNowAreaPanel.add(balanceNowArea, JPanel.RIGHT_ALIGNMENT);
		
		//Invested money
		investedMoney = new JLabel("Invested money", JLabel.LEFT);
//		investedMoneyPanel = new JPanel();
//		investedMoneyPanel.add(investedMoney, JPanel.LEFT_ALIGNMENT);
		investedMoneyArea = new JTextArea(currencyFormat(portfolioModel.getPortfolioInvestedMoney(portfolioModel.useGetPortfolio(portfolios.getSelectedIndex()).getNumber())));
		investedMoneyArea.setEditable(false);
		investedMoneyArea.setBackground(getWindowColor());
//		investedMoneyAreaPanel = new JPanel();
//		investedMoneyAreaPanel.add(investedMoneyArea, JPanel.RIGHT_ALIGNMENT);
		
		//Lots profit loss
		profitLossLots = new JLabel("Lots' profit/loss", JLabel.LEFT);
//		profitLossLotsPanel = new JPanel();
//		profitLossLotsPanel.add(profitLossLots, JPanel.LEFT_ALIGNMENT);
		profitLossLotsArea = new JTextArea(currencyFormat(new BigDecimal(portfolioModel.useGetAllLotsProfitLoss(portfolioModel.useGetPortfolio(portfolios.getSelectedIndex()).getNumber()))));
		profitLossLotsArea.setEditable(false);
		profitLossLotsArea.setBackground(getWindowColor());
//		profitLossLotsAreaPanel = new JPanel();
//		profitLossLotsAreaPanel.add(profitLossLotsArea, RIGHT_ALIGNMENT);
		
		//Current information panel
		currentInformationPanel = new JPanel(new GridLayout(3, 3));
		
		currentInformationPanel.setBackground(getWindowColor());
		
		currentInformationPanel.add(balanceNow);
		currentInformationPanel.add(balanceNowArea);
		currentInformationPanel.add(investedMoney);
		currentInformationPanel.add(investedMoneyArea);
		currentInformationPanel.add(profitLossLots);
		currentInformationPanel.add(profitLossLotsArea);
		
		//Total return panel
		totalReturnPanel = new JPanel(new FlowLayout());
		
		totalReturn = new JLabel("Total return");
		totalReturnArea = new JTextArea(portfolioModel.retrieveTotalReturn(portfolioModel.useGetPortfolio(portfolios.getSelectedIndex()).getNumber()));
		totalReturnArea.setEditable(false);
		totalReturnArea.setBackground(getWindowColor());
		
		totalReturnPanel.add(totalReturn, LEFT_ALIGNMENT);
		totalReturnPanel.add(totalReturnArea, RIGHT_ALIGNMENT);
		
		//Total balance panel
		totalBalancePanel = new JPanel(new FlowLayout());
		totalBalance = new JLabel("Total balance");
		totalBalance.setToolTipText("Balance now plus the invested money");
		totalBalanceArea = new JTextArea(currencyFormat(portfolioModel.getTotalBalance(portfolios.getSelectedIndex())));
		totalBalanceArea.setEditable(false);
		totalBalanceArea.setBackground(getWindowColor());
		
		totalBalancePanel.add(totalBalance, LEFT_ALIGNMENT);
		totalBalancePanel.add(totalBalanceArea, RIGHT_ALIGNMENT);
		
		totalBalancePanel.setBackground(getWindowColor());
		
		totalReturnPanel.setBackground(getWindowColor());
		
		//Total panel
		total = new JPanel(new GridLayout(2, 1));
		total.add(totalBalancePanel);
		total.add(totalReturnPanel);
		
		//Portfolio information panel
		portfolioInformationPanel = new JPanel(new GridLayout(1, 2));
		
		portfolioInformationPanel.setBackground(getWindowColor());
		
		portfolioInformationPanel.add(historicalInformationPanel, JPanel.LEFT_ALIGNMENT);
		portfolioInformationPanel.add(currentInformationPanel, JPanel.RIGHT_ALIGNMENT);
//		portfolioInformationPanel.add(totalBalancePanel, CENTER_ALIGNMENT);
//		portfolioInformationPanel.add(totalReturnPanel, CENTER_ALIGNMENT);
		
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
		options.setBackground(getWindowColor());
		
		options.setPreferredSize(new Dimension(1000, 50));

		options.add(withdraw);
		options.add(deposit);
		
		//Continue button
		continueButton = new JButton("Main screen");
		continueButton.setActionCommand("continue");
		continueButton.setToolTipText("Press to go to main screen");
		continueButton.addActionListener(this);
		
		continueButtonPanel = new JPanel(new FlowLayout());
		
		continueButtonPanel.setBackground(getWindowColor());
		continueButtonPanel.add(continueButton);
		
		add(portfoliosPanel);
		add(lotsPanel);
		add(portfolioInformationPanel);
		add(total, CENTER_ALIGNMENT);
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
			totalBalanceArea.setText(currencyFormat(portfolioModel.getTotalBalance(portfolios.getSelectedIndex())));
			totalReturnArea.setText(portfolioModel.useUpdatePortfolioTotalReturn(portfolios.getSelectedIndex()));
			String[] tableTitle = { "Symbol", "Price bought", "Shares bought",
					"Purchase amount", "Current price", "Current value",
					"Profit / Loss", "Purchase date" };
			lots.setModel(new NonEditableTable(portfolioModel.useLotsDetails(),
					tableTitle));
		}
		
	}


	@Override
	public void itemStateChanged(ItemEvent e) {
		if(e.getStateChange() == ItemEvent.SELECTED){
			portfolioModel.usePortfolioChanged(portfolios.getSelectedIndex());
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
}
