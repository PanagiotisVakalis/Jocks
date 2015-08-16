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
	private Color buttonColor;
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
		windowColor = new Color(9, 123, 189);
		buttonColor = new Color(9, 137, 210);
		areaColor = new Color(81, 178, 235);
		popUpWindowColor = new Color(62, 124, 159);
//		optionPaneBackground();
	}
	
	public static String currencyFormat(BigDecimal bG){
		return NumberFormat.getCurrencyInstance().format(bG);
	}
	
	private void optionPaneBackground(){
		UIManager UIManager = new UIManager();
		UIManager.put("OptionPane.background", popUpWindowColor);
		UIManager.put("Panel.background", popUpWindowColor);
	}
	
	public void changeOptionPaneBackground(){
		optionPaneBackground();
	}
	
	public Color getWindowColor(){
		return windowColor;
	}
	
	public Color getButtonColor(){
		return buttonColor;
	}
	
	public Color getAreaColor(){
		return areaColor;
	}
	
	public Color getPopUpWindowColor(){
		return popUpWindowColor;
	}
}
