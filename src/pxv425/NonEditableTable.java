package pxv425;

import javax.swing.table.DefaultTableModel;

/**
 * Class to make the table which has been used non editable
 * 
 * @author Panagiotis Vakalis
 * @version 20-07-2015	 
 *
 */
public class NonEditableTable extends DefaultTableModel {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Constructor of the class
	 * @param data
	 * @param columnNames
	 * 
	 * @author Panagiotis Vakalis
	 * @version 20-07-2015	
	 */
	public NonEditableTable(Object[][] data, String[] columnNames) {
		super(data, columnNames);
	}
	
	@Override
	public boolean isCellEditable(int row, int column){
		return false;
	}

}
