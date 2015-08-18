package pxv425;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;
import javax.swing.UIManager;

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
	private Color windowColor;
	private Color areaColor;
	private Color popUpWindowColor;
	
	/**
	 * Constructor of the class
	 * @param model
	 * 
	 * @author Panagiotis Vakalis
	 * @version 14-07-2015 
	 */
	public View(Model model){
		this.model = model;
		/*
		 * Enter the colors for the windows,
		 * areas and popup windows
		 */
		windowColor = new Color(9, 123, 189);
		areaColor = new Color(81, 178, 235);
		popUpWindowColor = new Color(62, 124, 159);
	}
	
	/**
	 * Method which converts the money into currency
	 * 
	 * @author Panagiotis Vakalis
	 * @version 13-07-2015
	 */
	public static String currencyFormat(BigDecimal bG){
		return NumberFormat.getCurrencyInstance().format(bG);
	}
	
	/**
	 * Method to set the background for the optionPanes
	 * 
	 * @author Panagiotis Vakalis
	 * @version 13-07-2015
	 */
	private void optionPaneBackground(){
		UIManager UIManager = new UIManager();
		UIManager.put("OptionPane.background", popUpWindowColor);
		UIManager.put("Panel.background", popUpWindowColor);
	}
	
	/**
	 * Method to set the background for the optionPanes
	 * 
	 * @author Panagiotis Vakalis
	 * @version 13-07-2015
	 */
	public void changeOptionPaneBackground(){
		optionPaneBackground();
	}
	
	/**
	 * Method to get the windows color outside the class
	 * @return windowColor
	 * 
	 * @author Panagiotis Vakalis
	 * @version 13-07-2015
	 */
	public Color getWindowColor(){
		return windowColor;
	}
	
	/**
	 * Method to get the area color outside the class
	 * @return area color
	 * 
	 * @author Panagiotis Vakalis
	 * @version 13-07-2015
	 */
	public Color getAreaColor(){
		return areaColor;
	}
	
	/**
	 * Method to get the popup windows color outside the class
	 * @return pop up window color
	 * 
	 * @author Panagiotis Vakalis
	 * @version 13-07-2015
	 */
	public Color getPopUpWindowColor(){
		return popUpWindowColor;
	}
}
