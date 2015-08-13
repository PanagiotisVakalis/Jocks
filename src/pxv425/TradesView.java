package pxv425;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
/**
 * Class which contains the view part of the trades screen
 * @author Panagiotis Vakalis
 * @version 24-07-2015
 *
 */
public class TradesView extends View implements ActionListener, Observer {
	
	private TradesModel tradesModel;
	private JTable buysTable;
	private JScrollPane buysTableScroll;
	private JTable sellsTable;
	private JScrollPane sellsTableScroll;
	private String command;
	private JLabel buysLabel;
	private JLabel sellsLabel;
//	private JButton backButton;
//	private JPanel backButtonPanel;
	
	/**
	 * Constructor of the class
	 * @param tradesModel
	 * 
	 * @author Panagiotis Vakalis
	 * @version 24-07-2015
	 */
	public TradesView(TradesModel tradesModel) {
		super(tradesModel);
		
		this.tradesModel = tradesModel;
		
		tradesModel.addObserver(this);
		
		frameSetup();
	}
	
	/**
	 * Method to build the screen
	 * 
	 * @author Panagiotis Vakalis
	 * @version 24-07-2015
	 */
	private void frameSetup(){
		setLayout(new GridLayout(4, 1));
		setPreferredSize(new Dimension(800, 400));
		
		//Buys label
		buysLabel = new JLabel("Your buys");
		
		//Buys table
		String[] buysTitle = {"Stock symbol", "Price", "Shares", "Amount", "Date"};
		
		//Method to get the buys from database
		tradesModel.useGetAllBuys();
		buysTable = new JTable();
		buysTable.setModel(new NonEditableTable(tradesModel.useBuysDetails(), buysTitle));
		buysTable.setRowSelectionAllowed(false);
		
		buysTableScroll = new JScrollPane(buysTable);
		
		//Sells label
		sellsLabel = new JLabel("Your sells");
		
		//Sells table
		String[] sellsTitle = {"Stock symbol", "Price", "Shares", "Amount", "Profit/Loss", "Date"};
		
		//Method to get all the sells from the database
		tradesModel.useGetAllSells();
		sellsTable = new JTable();
		sellsTable.setModel(new NonEditableTable(tradesModel.useSellsDetails(), sellsTitle));
		sellsTable.setRowSelectionAllowed(false);
		
		sellsTableScroll = new JScrollPane(sellsTable);
		
//		backButton = new JButton("Back");
//		backButton.setActionCommand("back");
//		backButton.addActionListener(this);
		
		add(buysLabel);
		add(buysTableScroll);
		add(sellsLabel);
		add(sellsTableScroll);
//		add(backButton);
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		command = e.getActionCommand();
		
//		if(command.equals("back")){
//			tradesModel.useChangeToMainView(new MainView(new MainModel(tradesModel.getClient(), tradesModel.getPortfolio())), tradesModel.getPortfolio());
//		}

	}

}
