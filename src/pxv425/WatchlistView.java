package pxv425;

import java.awt.BorderLayout;
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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.TableModel;

/**
 * Class which contains the view part of the watchlist screen
 * 
 * @author Panagiotis Vakalis
 * @version 26-07-2015
 *
 */
public class WatchlistView extends View implements ActionListener, Observer {
	
	private WatchlistModel watchlistModel;
	private JLabel title;
	private JTable watchlistTable;
	private JScrollPane watchlistTableScroll;
	private JPanel buttons;
	private JButton deleteStock;
	private JButton buyStock;
	private JButton backButton;
	private String command;
	private NonEditableTable watchlistTableModel;

	/**
	 * Constrictor of the class
	 * @param watchlistModel
	 * 
	 * @author Panagiotis Vakalis
	 * @version 26-07-2015
	 */
	public WatchlistView(WatchlistModel watchlistModel) {
		super(watchlistModel);
		
		this.watchlistModel = watchlistModel;
		
		watchlistModel.addObserver(this);
		
		frameSetup();
	}
	
	/**
	 * Method to build the screen
	 * 
	 * @author Panagiotis Vakalis
	 * @version 26-07-2015
	 */
	private void frameSetup(){
		setLayout(new GridLayout(4, 1));
		
		title = new JLabel("Your watchlist");
		
		String[] tableTitle = {"Stock symbol", "Stock name", "Price"};
		
		
		
		watchlistTable = new JTable();
		watchlistTableModel = new NonEditableTable(watchlistModel.useWatchesDetails(), tableTitle);
		watchlistTable.setModel(watchlistTableModel);
		
		watchlistTable.setRowSelectionAllowed(true);
		watchlistTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		watchlistTableScroll = new JScrollPane(watchlistTable);
		
		deleteStock = new JButton("Delete");
		deleteStock.setActionCommand("delete");
		deleteStock.addActionListener(this);
		
		buyStock = new JButton("Buy");
		buyStock.setActionCommand("buy");
		buyStock.addActionListener(this);
		
		buttons = new JPanel(new FlowLayout());
		
		buttons.add(deleteStock);
		buttons.add(buyStock);
		
		backButton = new JButton("Back");
		backButton.setActionCommand("back");
		backButton.addActionListener(this);
		
		add(title);
		add(watchlistTableScroll);
		add(buttons);
		add(backButton);
	}

	@Override
	public void update(Observable o, Object arg) {
//		if(o instanceof WatchlistModel){
//			watchlistTableModel.fireTableDataChanged();
//			
//		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		command = e.getActionCommand();

		if(command.equals("back")){
			watchlistModel.useChangeToMainView(new MainView(new MainModel(watchlistModel.getClient(), PortfolioView.getPortfolioSelection())), PortfolioView.getPortfolioSelection());
		}
		if(command.equals("delete")){
			if(watchlistTable.getSelectedRow() != -1){
				JOptionPane.showMessageDialog(this, watchlistModel.useDeleteAWatch(watchlistModel.useSelectStock(watchlistTable.getSelectedRow()).getSymbol()), "Delete a stock", JOptionPane.INFORMATION_MESSAGE);
//				watchlistModel.useGetUpdatedWatchlist();
				watchlistTableModel.removeRow(watchlistTable.getSelectedRow());
			}
			else{
				JOptionPane.showMessageDialog(this, "You should select a stock from the table", "Error in deletion", JOptionPane.WARNING_MESSAGE);
			}
		}
		if(command.equals("buy")){
			if(watchlistTable.getSelectedRow() != -1){
				watchlistModel.useChangeToBuyView(new BuyView(new BuyModel(watchlistModel.getClient(), PortfolioView.getPortfolioSelection(), watchlistModel.useSelectStock(watchlistTable.getSelectedRow()))), PortfolioView.getPortfolioSelection(), watchlistModel.useSelectStock(watchlistTable.getSelectedRow()));
			}
			else{
				JOptionPane.showMessageDialog(this, "You should select a stock from the table", "Error in buy", JOptionPane.WARNING_MESSAGE);
			}
		}
	}

}
