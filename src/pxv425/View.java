package pxv425;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

/**
 * This class contains the View part of the View - Model
 * separation
 * 
 * @author Panagiotis Vakalis
 * @version 14-07-2015
 *
 */
public abstract class View extends JPanel{

	private static final long serialVersionUID = 1L;
	private Model model;
	
	/**
	 * Constructor of the class
	 * @param model
	 * 
	 * @author Panagiotis Vakalis
	 * @version 14-07-2015 
	 */
	public View(Model model){
		this.model = model;
	}
	
	public static String currencyFormat(BigDecimal bG){
		return NumberFormat.getCurrencyInstance().format(bG);
	}
}
