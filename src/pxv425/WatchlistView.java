package pxv425;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

/**
 * Class which contains the view part of the watchlist screen
 * 
 * @author Panagiotis Vakalis
 * @version 26-07-2015
 *
 */
public class WatchlistView extends View implements ActionListener, Observer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private WatchlistModel watchlistModel;
	private JLabel title;
	private JTable watchlistTable;
	private JScrollPane watchlistTableScroll;
	private NonEditableTable watchlistTableModel;
	private DefaultTableCellRenderer rightAlign;

	/**
	 * Constructor of the class
	 * 
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
	 * Method to get the watchlist table out of the class
	 * 
	 * @return watchlist table
	 * 
	 * @author Panagiotis Vakalis
	 * @version 26-07-2015
	 */
	public JTable getWatchlistTable() {
		return watchlistTable;
	}

	/**
	 * Method to get the non editable model table out of the class
	 * 
	 * @return non editable model table
	 * 
	 * @author Panagiotis Vakalis
	 * @version 26-07-2015
	 */
	public NonEditableTable getWatchlistTableModel() {
		return watchlistTableModel;
	}

	/**
	 * Method to build the screen
	 * 
	 * @author Panagiotis Vakalis
	 * @version 26-07-2015
	 */
	private void frameSetup() {
		setLayout(new GridLayout(2, 1));
		setPreferredSize(new Dimension(600, 400));

		title = new JLabel("Your watchlist");

		String[] tableTitle = { "Stock symbol", "Stock name", "Price" };

		watchlistTable = new JTable();
		watchlistTableModel = new NonEditableTable(
				watchlistModel.useWatchesDetails(), tableTitle);
		watchlistTable.setModel(watchlistTableModel);

		// Align money to the right
		rightAlign = new DefaultTableCellRenderer();
		rightAlign.setHorizontalAlignment(SwingConstants.RIGHT);
		watchlistTable.getColumnModel().getColumn(2)
				.setCellRenderer(rightAlign);

		watchlistTable.setRowSelectionAllowed(true);
		watchlistTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		watchlistTableScroll = new JScrollPane(watchlistTable);

		add(title);
		add(watchlistTableScroll);
	}

	@Override
	public void update(Observable o, Object arg) {
		/*
		 * No body
		 */
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		/*
		 * No body
		 */
	}

}
