package pxv425;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * This class is the client part of the system.
 * 
 * @author Panagiotis Vakalis
 * @version 14-07-2015
 *
 */
public class Client extends JFrame{

	private static final long serialVersionUID = 1L;
	private IntroModel introModel;
	private Investor investor;

	/**
	 * Constructor of the class which builds the frame
	 * 
	 * @author Panagiotis Vakalis
	 * @version 14-07-2015
	 */
	public Client(){
		super("Jocks");
		setSize(350, 200);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		introModel = new IntroModel(this);
		
		useChangePanel(new IntroView(introModel));

		setVisible(true);
	}
	
	/**
	 * Method to use the investor outside the class
	 * @return inestor
	 * 
	 * @author Panagiotis Vakalis
	 * @version 18-07-2015
	 */
	public Investor getInvestor() {
		return investor;
	}

	/**
	 * Method to set an investor
	 * @param investor
	 * 
	 * @author Panagiotis Vakalis
	 * @version 18-07-2015
	 */
	public void setInvestor(Investor investor) {
		this.investor = investor;
	}

	/**
	 * Method to change panels
	 * @param panel
	 * 
	 * @author Panagiotis Vakalis
	 * @version 14-07-2015
	 */
	private void changePanel(JPanel panel){
		this.setContentPane(panel);
		this.pack();
		/*
		 * Code from Uday Reddy. This part of code
		 * places the frame into the middle of the screen
		 */
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation((int) screen.getWidth() / 2 - getWidth() / 2,
				(int) screen.getHeight() / 2 - getHeight() / 2);
	}
	
	/**
	 * Method to use changPanel method outside of the class
	 * @param panel
	 * 
	 * @author Panagiotis Vakalis
	 * @version 14-07-2015
	 */
	public void useChangePanel(JPanel panel){
		changePanel(panel);
	}
}
