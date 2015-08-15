package pxv425;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
/**
 * Class which contains the view part of the lots screen
 * @author Panagiotis Vakalis
 * @version 23-07-2015	
 *
 */
public class LotsView extends View implements Observer, ActionListener{
	
	private LotsModel lotsModel;
	private JLabel lotsLabel;
	private JTable lotsTable;
	private JScrollPane lotsTableScroll;
	private JButton continueButton;
	private JButton sellButton;
	private String command;
	private JPanel buttons;

	/**
	 * Constructor of the class
	 * @param lotsModel
	 * 
	 * @author Panagiotis Vakalis
	 * @version 23-07-2015	
	 */
	public LotsView(LotsModel lotsModel) {
		super(lotsModel);
		
		this.lotsModel = lotsModel;
		
		lotsModel.addObserver(this);
		
		frameSetup();
	}

	/**
	 * Method to buid the frame
	 * 
	 * @author Panagiotis Vakalis
	 * @version 23-07-2015	
	 */
	private void frameSetup(){
		//Set the layout
		setLayout(new GridLayout(2, 1));
		
		setPreferredSize(new Dimension(600, 400));
		
		lotsLabel = new JLabel("These are your lots which are contained in the " + lotsModel.useGetPortfolioName() + " portfolio.");
		
		//Call method in order to get the lots
		lotsModel.useGetAllLots();
		
		//Title for the table's columns
		String[] title = {"Symbol", "Price bought", "Shares bought", "Purchase amount", "Current price", "Current value", "Profit / Loss", "Purchase date"};
		
		lotsTable = new JTable();
		lotsTable.setModel(new NonEditableTable(lotsModel.useLotsDetails(), title));
		lotsTable.setRowSelectionAllowed(true);
		lotsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		lotsTableScroll = new JScrollPane(lotsTable);
		
//		sellButton = new JButton("Sell stock");
//		sellButton.setActionCommand("sell");
//		sellButton.addActionListener(this);
//		
//		continueButton = new JButton("Continue");
//		continueButton.setActionCommand("continue");
//		continueButton.addActionListener(this);
		
		
		add(lotsLabel);
		add(lotsTableScroll);
//		add(sellButton);
//		add(continueButton);
	}
	
	public JTable getLotsTable(){
		return lotsTable;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		command = e.getActionCommand();
		
		if(command.equals("sell")){
//			lotsModel.useChangeToSellView(new SellView(new SellModel(lotsModel.getClient(), lotsModel.getPortfolio(), lotsModel.useSelectLot(lotsTable.getSelectedRow()))), lotsModel.getPortfolio(), lotsModel.useSelectLot(lotsTable.getSelectedRow()));
			if(lotsTable.getSelectedRow() != -1){
//				lotsModel.useChangeToSellView(new SellView(new SellModel(lotsModel.getClient(), lotsModel.getPortfolio(), lotsModel.useSelectLot(lotsTable.getSelectedRow()))), lotsModel.getPortfolio(), lotsModel.useSelectLot(lotsTable.getSelectedRow()));
				lotsModel.useInitializeSellView(lotsModel.useSelectLot(lotsTable.getSelectedRow()));
				//The two buttons
				String[] options = {"Confirm", "Back"};
				int answer = JOptionPane.showOptionDialog(this, lotsModel.getSellView(), "Sell lot", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
				/*
				 * Confirm = 0
				 * Back = 1
				 */
				if(answer == 0){
					/*
					 * If user has pressed confirm
					 */
//					mainModel.getBuyModel().useBuyStock(mainModel.getBuyView().getShares());
					JOptionPane.showMessageDialog(this, lotsModel.getSellModel().useSellStock(lotsModel.getSellView().getShares()));
				}
				if(answer == 1){
					
				}
			}
			else{
				JOptionPane.showMessageDialog(this, "You should select a stock from the table", "Error in buy", JOptionPane.WARNING_MESSAGE);
			}
		}
		if(command.equals("continue")){
			lotsModel.useChangeToMainView(new MainView(new MainModel(lotsModel.getClient(), lotsModel.getPortfolio())), lotsModel.getPortfolio());

		}
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}

	
}
