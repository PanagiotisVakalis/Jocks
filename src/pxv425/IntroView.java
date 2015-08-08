package pxv425;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * This class contains the InroView which will be the first
 * Panel which will be shown
 * 
 * @author Panagiotis Vakalis
 * @version 14-07-2015
 *
 */
public class IntroView extends View implements Observer, ActionListener{
	private JPanel buttons;
	private JButton login;
	private JButton register;
	private ImageIcon loginIcon;
	private JLabel loginLabel;
	private String command;
	private IntroModel introModel;

	private static final long serialVersionUID = 1L;
	
	/**
	 * Constructor of the class
	 * @param introModel
	 * 
	 * @author Panagiotis Vakalis
	 * @version 14-07-2015
	 */
	public IntroView(IntroModel introModel){
		super(introModel);
		
		this.introModel = introModel;
		
		introModel.addObserver(this);
		
		frameSetup();
	}
	
	/**
	 * Method which builds the frame
	 * 
	 * @author Panagiotis Vakalis
	 * @version 14-07-2015
	 */
	private void frameSetup(){

		Dimension size = new Dimension(750, 600);
		setPreferredSize(size);
		//Layout for Panel
		setLayout(new BorderLayout());
		
		//Construct panel for buttons
		buttons = new JPanel(new FlowLayout());
		//Construct buttons
		login = new JButton("Login");
		login.setActionCommand("login");
		login.addActionListener(this);
		
		register = new JButton("Register");
		register.setActionCommand("register");
		register.addActionListener(this);
		
		//Add buttons on the buttons' panel
		buttons.add(register);
		buttons.add(login);
		
		//Construct the loginIcon using a downloaded image
		loginIcon = new ImageIcon(".//images//LoginViewImage.jpg");
		loginLabel = new JLabel(loginIcon);
		
		
		//Add components on the main panel
		add(loginLabel, BorderLayout.CENTER);
		add(buttons, BorderLayout.SOUTH);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		command = e.getActionCommand();
		
		if(command.equals("login")){
			introModel.useChangeToLoginView(new LoginView(new LoginModel(introModel.getClient())));
		}
		if(command.equals("register")){
			introModel.useChangeToRegisterView(new RegisterView(new RegisterModel(introModel.getClient())));
//			JOptionPane.showMessageDialog(this, new RegisterView(new RegisterModel(introModel.getClient())));
		}
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}
}
