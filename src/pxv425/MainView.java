package pxv425;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.jfree.chart.ChartPanel;
/**
 * Class which contains the view of the main screen
 * 
 * @author Panagiotis Vakalis
 * @version 20-07-2015	
 *
 */
public class MainView extends View implements ListSelectionListener, ActionListener, Observer {
	
	private MainModel mainModel;
	private Dimension screenSize;
	private double screenWidth;
	private double screenHeight;
	private JButton portfolio;
	private JButton lots;
	private JButton trades;
	private JButton crossSMA;
	private JButton help;
	private JButton watchlist;
	private JButton logout;
	private JPanel buttonsMenu;
	private JPanel stock;
	private JTextArea stockSymbol;
	private JTextArea stockName;
	private JTextArea stockPrice;
	private JButton buyButton;
	private JButton addToWatchilistButton;
	private JPanel stocksTablePanel;
	private JScrollPane stocksTableScrolled;
	private JTable stocksTable;
	private JPanel buttons;
	private JPanel portfoliosInformation;
	private JLabel numberOfPortfioliosLabel;
	private JTextArea numberOfPortfolios;
	private JLabel availableBalanceLabel;
	private JTextArea availableBalance;
	private JLabel investedMoneyLabel;
	private JTextArea investedMoney;
	private JLabel profitLossLabel;
	private JTextArea profitLoss;
	private String command;
	private JButton createANewPortfolio;
	private JPanel chart;
	private JButton updateInformation;
	private JPanel updateInformationPanel;
	private JPanel informationPanelWithUpdateButton;
	private JPanel stockTableAndButtonsPanel;
	
	
	/**
	 * Constructor of the class
	 * @param mainModel
	 * 
	 * @author Panagiotis Vakalis
	 * @version 20-07-2015	
	 */
	public MainView(MainModel mainModel) {
		
		
		super(mainModel);
		this.mainModel = mainModel;
		
		mainModel.addObserver(this);
		
		frameSetup();
	}
	
	/**
	 * Method to build the frame
	 * 
	 * @author Panagiotis Vakalis
	 * @version 20-07-2015	
	 */
	private void frameSetup(){
		screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		
		screenWidth = screenSize.getWidth();
		
		screenHeight = screenSize.getHeight();
		
		setSize((int)screenWidth, (int)screenHeight);
		
		setLayout(new GridLayout(4,  1));
		
		// Setup buttons
		portfolio = new JButton("Portfolio");
		portfolio.setActionCommand("portfolio");
		portfolio.addActionListener(this);

		lots = new JButton("Lots");
		lots.setActionCommand("lots");
		lots.addActionListener(this);

	    trades = new JButton("Trades");
		trades.setActionCommand("trades");
		trades.addActionListener(this);
		
		createANewPortfolio = new JButton("Create new portfolio");
		createANewPortfolio.setActionCommand("create");
		createANewPortfolio.addActionListener(this);

		crossSMA = new JButton("Cross SMAs");
		crossSMA.setActionCommand("cross");
		crossSMA.addActionListener(this);

		help = new JButton("Help");
		help.setActionCommand("help");
		help.addActionListener(this);

		watchlist = new JButton("Watchlist");
		watchlist.setActionCommand("watchlist");
		watchlist.addActionListener(this);

		logout = new JButton("Logout");
		logout.setActionCommand("logout");
		logout.addActionListener(this);
		
		// Panel for buttons
		buttonsMenu = new JPanel(new FlowLayout());
		// add buttons on panel
		buttonsMenu.add(portfolio);
		buttonsMenu.add(lots);
		buttonsMenu.add(trades);
		buttonsMenu.add(createANewPortfolio);
		buttonsMenu.add(crossSMA);
		buttonsMenu.add(help);
		buttonsMenu.add(watchlist);
		buttonsMenu.add(logout);
		
//		//Panel for each stock
//		stock = new JPanel(new GridLayout(4, 1));
//		stockSymbol = new JTextArea();
		
		//Panel for all the stocks
//		mainModel.useGetAllStocks();
//		allTheStocks = new JPanel(new GridLayout(1, mainModel.getStocks().size()));
//		allTheStocks = new JPanel(new FlowLayout());
//		allTheStocks.setSize(300, 400);
		
//		stockSymbol = new JTextArea();
//		stockSymbol.setEditable(false);
//		
//		stockName = new JTextArea();
//		stockName.setEditable(false);
//		
//		stockPrice = new JTextArea();
//		stockPrice.setEditable(false);
		
		buyButton = new JButton("Buy");
		buyButton.setActionCommand("buy");
		buyButton.addActionListener(this);
		
		addToWatchilistButton = new JButton("Add to watchlist");
		addToWatchilistButton.setActionCommand("add watchlist");
		addToWatchilistButton.addActionListener(this);
		
		buttons = new JPanel(new FlowLayout());
		buttons.add(addToWatchilistButton);
		buttons.add(buyButton);
		
		mainModel.useGetAllStocks();
		String[] title = {"Symbol", "Name", "Price"};
		stocksTable = new JTable();
		//Set the model in oreder to make the table non editable
		stocksTable.setModel(new NonEditableTable(mainModel.useStocksDetails(), title));
//		stocksTable.setFocusable(false);
		stocksTable.setRowSelectionAllowed(true);
		//Select only one
		stocksTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		stocksTable.getSelectionModel().addListSelectionListener(this);
		stocksTable.setPreferredScrollableViewportSize(stocksTable.getPreferredSize());
		stocksTable.setFillsViewportHeight(true);
		stocksTableScrolled = new JScrollPane(stocksTable);
		
		
		stockTableAndButtonsPanel = new JPanel(new GridLayout(2, 1));
		stockTableAndButtonsPanel.add(stocksTableScrolled);
		stockTableAndButtonsPanel.add(buttons);
		
		
		chart = new JPanel();
		chart.setLayout(new BorderLayout());
//		ChartPanel chartPanel =  mainModel.useDrawChart(0);
		
		//Portfolio informations
		
		//Call model's method in order to get portfolios' informations
		mainModel.usePortfoliosDetails();
		
		updateInformation = new JButton("Update information");
		updateInformation.setActionCommand("update");
		updateInformation.addActionListener(this);
		updateInformation.setToolTipText("Press to update your portfolio's information");
		
		updateInformationPanel = new JPanel();
		updateInformationPanel.add(updateInformation);
		
		portfoliosInformation = new JPanel(new GridLayout(2, 4));
		
		numberOfPortfioliosLabel = new JLabel("Number of porftolios");
		numberOfPortfolios = new JTextArea();
		numberOfPortfolios.setEditable(false);
		numberOfPortfolios.setText(mainModel.usePortfoliosAmount());
		availableBalanceLabel = new JLabel("Available balance");
		availableBalance = new JTextArea();
		availableBalance.setEditable(false);
		availableBalance.setText("£" + mainModel.useTotalBalance());
		investedMoneyLabel = new JLabel("Invested money");
		investedMoney = new JTextArea();
		investedMoney.setEditable(false);
		investedMoney.setText("£" + mainModel.useInvestedMoney());
		profitLossLabel = new JLabel("Lots Profit / Loss");
		profitLoss = new JTextArea();
		profitLoss.setEditable(false);
//		profitLoss.setText(mainModel.useProfitLoss());
		profitLoss.setText("£" + String.valueOf(mainModel.useGetAllLotsProfitLoss(mainModel.getPortfolio())));
		
		portfoliosInformation.add(numberOfPortfioliosLabel);
		portfoliosInformation.add(availableBalanceLabel);
		portfoliosInformation.add(investedMoneyLabel);
		portfoliosInformation.add(profitLossLabel);
		portfoliosInformation.add(numberOfPortfolios);
		portfoliosInformation.add(availableBalance);
		portfoliosInformation.add(investedMoney);
		portfoliosInformation.add(profitLoss);
		
		informationPanelWithUpdateButton = new JPanel(new GridLayout(2, 1));
//		informationPanelWithUpdateButton.add(updateInformationPanel);
		informationPanelWithUpdateButton.add(portfoliosInformation);
		informationPanelWithUpdateButton.add(updateInformationPanel);
		
		
		add(buttonsMenu);
		add(stockTableAndButtonsPanel);
		add(chart);
		add(informationPanelWithUpdateButton);
	}

	@Override
	public void update(Observable o, Object arg) {
		if(o instanceof MainModel){
			
			if(mainModel.getUpdatedProfitLossSum(mainModel.getPortfolio()).equals("null") || mainModel.getUpdatedTotalInvestedMoney().equals("null")){
				/*
				 * I have used this if statement because every time
				 * that a stock is selected it draws the chart and updates
				 * the panel. So, it changes the values into null.
				 */
			}
			else{
				profitLoss.setText("£" + mainModel.getUpdatedProfitLossSum(mainModel.getPortfolio()));
				investedMoney.setText("£" + mainModel.getUpdatedTotalInvestedMoney());
				availableBalance.setText("£" + mainModel.getUpdatedAvailableBalance());
			}
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		command = e.getActionCommand();
		
		if(command.equals("buy")){
//			mainModel.useBuyStock(stocksTable.getSelectedRow());
//			JOptionPane.showInternalConfirmDialog(this, new BuyView(new BuyModel(mainModel.getClient(), mainModel.getPortfolio(), mainModel.useSelectStock(stocksTable.getSelectedRow()))));
			if(stocksTable.getSelectedRow() != -1){
				//If a stock has been selected
				/*
				 * Initialize the BuyView using the stock which
				 * has been selected
				 */
				mainModel.useInitializeBuyView(mainModel.useSelectStock(stocksTable.getSelectedRow()));
				//The two buttons
				String[] options = {"Confirm", "Back"};
				int answer = JOptionPane.showOptionDialog(this, mainModel.getBuyView(), "Buy stock", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
				/*
				 * Confirm = 0
				 * Back = 1
				 */
				if(answer == 0){
					/*
					 * If user has pressed confirm
					 */
//					mainModel.getBuyModel().useBuyStock(mainModel.getBuyView().getShares());
					JOptionPane.showMessageDialog(this, mainModel.getBuyModel().useBuyStock(mainModel.getBuyView().getShares()));
				}
				if(answer == 1){
					
				}
			}
			else{
				JOptionPane.showMessageDialog(this, "You should select a stock from the table", "Error in buy", JOptionPane.WARNING_MESSAGE);
			}
		}
		if(command.equals("portfolio")){
			mainModel.useChangeToPortfolioView(new PortfolioView(new PortfolioModel(mainModel.getClient())));
		}
		if(command.equals("lots")){
//			mainModel.useChangeToLotsView(new LotsView(new LotsModel(mainModel.getClient(), mainModel.getPortfolio())), mainModel.getPortfolio());
			mainModel.useInitializeLotsView();
			if(!mainModel.getLotsModel().getLots().isEmpty()){
				//The two buttons
				String[] options = {"Sell Stock", "Continue"};
				
				int answer = JOptionPane.showOptionDialog(this, mainModel.getLotsView(), "Lots", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
				
				/*
				 * Sell = 0
				 * Continue = 1
				 */
				if(answer == 0){
//					JOptionPane.showMessageDialog(this, mainModel.getLotsModel().getSellModel().useSellStock(mainModel.getLotsModel().getSellView().getShares()));
					if(mainModel.getLotsView().getLotsTable().getSelectedRow() != -1){
						mainModel.getLotsModel().useInitializeSellView(mainModel.getLotsModel().useSelectLot(mainModel.getLotsView().getLotsTable().getSelectedRow()));
						//The two buttons
						String[] optionsInSell = {"Confirm", "Back"};
						int answerInSell = JOptionPane.showOptionDialog(mainModel.getLotsView(), mainModel.getLotsModel().getSellView(), "Sell lot", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, optionsInSell, optionsInSell[0]);
						
						/*
						 * Confirm = 0
						 * Back = 1
						 */
						if(answerInSell == 0){
							/*
							 * If user has pressed confirm
							 */
//							mainModel.getBuyModel().useBuyStock(mainModel.getBuyView().getShares());
//							JOptionPane.showMessageDialog(this, lotsModel.getSellModel().useSellStock(lotsModel.getSellView().getShares()));
							JOptionPane.showMessageDialog(mainModel.getLotsView(), mainModel.getLotsModel().getSellModel().useSellStock(mainModel.getLotsModel().getSellView().getShares()));
						}
						if(answerInSell == 1){
							
						}
					}
					else{
						JOptionPane.showMessageDialog(this, "You should select a stock from the table", "Error in sell", JOptionPane.WARNING_MESSAGE);
					}
				}
			}
			else{
				JOptionPane.showMessageDialog(this, "You have no lots", "Lots warning", JOptionPane.WARNING_MESSAGE);
			}
			
		}
		if(command.equals("trades")){
//			mainModel.useChangeToTradesView(new TradesView(new TradesModel(mainModel.getClient(), mainModel.getPortfolio())), mainModel.getPortfolio());
//			JOptionPane.showMessageDialog(this, new TradesView(new TradesModel(mainModel.getClient(), mainModel.getPortfolio())));
			JOptionPane.showMessageDialog(this, new TradesView(new TradesModel(mainModel.getClient(), mainModel.getPortfolio())), "Trades", JOptionPane.PLAIN_MESSAGE);
		}
		if(command.equals("create")){
			String name = JOptionPane.showInputDialog(this, "Enter the name for your new portfolio");
			String amount = JOptionPane.showInputDialog(this, "Enter the intitial amount of money");
			mainModel.useCreatePortfolio(name, Double.parseDouble(amount));
		}
		if(command.equals("cross")){
			String shortPeriod = JOptionPane.showInputDialog(this, "Enter the short period (days)");
			String longPeriod = JOptionPane.showInputDialog(this, "Enter the long period (days)");
			if(shortPeriod != null && longPeriod != null){
				if(!shortPeriod.isEmpty() && !longPeriod.isEmpty()){
					ChartPanel crossSMAPanel = new ChartPanel(mainModel.useDrawCrossSMA(stocksTable.getSelectedRow(), Integer.parseInt(shortPeriod), Integer.parseInt(longPeriod)));
//					JOptionPane.showMessageDialog(this, crossSMAPanel);
					JOptionPane.showMessageDialog(this, crossSMAPanel, "Cross SMA", JOptionPane.PLAIN_MESSAGE);
				}
				else{
					JOptionPane.showMessageDialog(this, "You have to enter both periods", "Period input warning", JOptionPane.WARNING_MESSAGE);
				}
			}
			else{
				JOptionPane.showMessageDialog(this, "You have to enter both periods", "Period input warning", JOptionPane.WARNING_MESSAGE);
			}
		}
		if(command.equals("help")){
			String message = JOptionPane.showInputDialog(this, "Enter your message");
			if(message != null){
				if(!message.isEmpty()){
					JOptionPane.showMessageDialog(this, mainModel.useRequestHelp(message));
				}
				else{
					JOptionPane.showMessageDialog(this, "You have to enter a message", "No message sent", JOptionPane.WARNING_MESSAGE);
				}
			}
		}
		if(command.equals("watchlist")){
//			mainModel.useChangeToWatchilistView(new WatchlistView(new WatchlistModel(mainModel.getClient(), mainModel.getInvestor())), mainModel.getInvestor());
			mainModel.useInitializeWatchlistView();
			String[] options = {"Delete", "Buy", "Back"};
			int answer = JOptionPane.showOptionDialog(this, mainModel.getWatchlistView(), "Watchlist", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[1]);
			
			if(answer == 0){
				JOptionPane.showMessageDialog(this, mainModel.getWatchlistModel().useDeleteAWatch(mainModel.getWatchlistModel().useSelectStock(mainModel.getWatchlistView().getWatchlistTable().getSelectedRow()).getSymbol()));
				mainModel.getWatchlistView().getWatchlistTableModel().removeRow(mainModel.getWatchlistView().getWatchlistTable().getSelectedRow());
			}
			if(answer == 1){
				/*
				 * Initialize the BuyView using the stock which
				 * has been selected
				 */
				mainModel.useInitializeBuyView(mainModel.getWatchlistModel().useSelectStock(mainModel.getWatchlistView().getWatchlistTable().getSelectedRow()));
				//The two buttons
				String[] optionsInBuy = {"Confirm", "Back"};
				int answerInBuy = JOptionPane.showOptionDialog(this, mainModel.getBuyView(), "Buy stock", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, optionsInBuy, optionsInBuy[0]);
				/*
				 * Confirm = 0
				 * Back = 1
				 */
				if(answerInBuy == 0){
					/*
					 * If user has pressed confirm
					 */
//					mainModel.getBuyModel().useBuyStock(mainModel.getBuyView().getShares());
					JOptionPane.showMessageDialog(this, mainModel.getBuyModel().useBuyStock(mainModel.getBuyView().getShares()));
				}
			}
		}
		if(command.equals("logout")){
//			JOptionPane.showConfirmDialog(this, "Are you sure you want to logout?", "Logout", )
			int answer = JOptionPane.showConfirmDialog(this, "Are you sure you want to logout?", "Logout", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
			if(answer == JOptionPane.OK_OPTION){
//				mainModel.useChangeToIntroView(new IntroView(new IntroModel(mainModel.getClient())));
				mainModel.useLogout();
			}
			if(answer == JOptionPane.CANCEL_OPTION){
				//Close the JOptionPane
				JOptionPane.getRootFrame().dispose();
			}
		}
		if(command.equals("add watchlist")){
			JOptionPane.showMessageDialog(this, mainModel.useAddToWatchlist(mainModel.useSelectStock(stocksTable.getSelectedRow())));
		}
		if(command.equals("update")){
			mainModel.useUpdateProfitLossSum(mainModel.getPortfolio());
			mainModel.useUpdateTotalInvestedMoney();
			mainModel.useUpdateAvailableBalance();
		}
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
//		mainModel.useDrawChart(stocksTable.getSelectedRow());
		//Chart
		ChartPanel chartPanel = new ChartPanel(mainModel.useDrawChart(stocksTable.getSelectedRow()));
		chart.add(chartPanel, BorderLayout.CENTER);
		chart.validate();
	}

}
