package pxv425;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.nio.ByteOrder;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.jdesktop.swingx.JXLabel;

/**
 * The GUI part of the register process
 * 
 * @author Panagiotis Vakalis
 * @version 14-07-2015
 *
 */
public class RegisterViewOld extends View implements ActionListener, Observer {

	private static final long serialVersionUID = 1L;
	private RegisterModel registerModel;
	
	//Instance variables for frame
	private JLabel title;
	private JLabel emailLabel;
	private JTextField emailInput;
	private JLabel passwordLabel;
	private JXLabel passwordRequirements;
	private JPasswordField passwordInput;
	private JLabel reEnterPasswordLabel;
	private JPasswordField reEnterPasswordInput;
	private JLabel firstNameLabel;
	private JTextField firstNameInput;
	private JLabel lastNameLabel;
	private JTextField lastNameInput;
	private JLabel securityQuestionLabel;
	private JTextField securityQuestionInput;
	private JLabel securityAnswerLabel;
	private JPasswordField securityAnswerInput;
	private JPanel inputs;
	private JPanel buttons;
	private String command;
	
	/**
	 * Constructor of the class
	 * @param registermodel
	 * 
	 * @author Panagiotis Vakalis
	 * @version 14-07-2015
	 */
	public RegisterViewOld(RegisterModel registermodel) {
		super(registermodel);
		this.registerModel = registermodel;
		
		registermodel.addObserver(this);
		
		frameSetup();
	}
	
	/**
	 * Method to build the frame
	 * 
	 * @author Panagiotis Vakalis
	 * @version 14-07-2015
	 */
	private void frameSetup(){
		setSize(400, 500);
		
		setLayout(new BorderLayout());
		
		// Panel for inputs
		inputs = new JPanel(new GridLayout(15, 0));
		
		// Textfields and Labels for inputs
		title = new JLabel("Register");
		emailLabel = new JLabel("Email");
		emailInput = new JTextField();
		passwordLabel = new JLabel("Password");
		/*
		 * I have used swingX library in order to use the JXLabel to
		 * extend the text of the label
		 * extend into a new line
		 */
//		passwordRequirements = new JLabel("<html>(8 to 16 characters,<br>lowercase or uppercase<br>at least one number<br>and one of the following symbols:<br>! @ # $ % ^ & * - _</html>");
		passwordRequirements = new JXLabel("Password should contain 8 to 16 characters, \nat least one number \nand one of the following symbols: ! @ # $ % ^ & * - _");
		passwordRequirements.setLineWrap(true);
		passwordInput = new JPasswordField();
		reEnterPasswordLabel = new JLabel("Re-enter password");
		reEnterPasswordInput = new JPasswordField();
		firstNameLabel = new JLabel("First name");
		firstNameInput = new JTextField();
		lastNameLabel = new JLabel("Last name");
		lastNameInput = new JTextField();
		securityQuestionLabel = new JLabel("Security question (do not include ?)");
		securityQuestionInput = new JTextField();
		securityAnswerLabel = new JLabel("Security answer");
		securityAnswerInput = new JPasswordField();
		
		inputs.add(emailLabel);
		inputs.add(emailInput);
		inputs.add(passwordLabel);
		inputs.add(passwordRequirements);
		inputs.add(passwordInput);
		inputs.add(reEnterPasswordLabel);
		inputs.add(reEnterPasswordInput);
		inputs.add(firstNameLabel);
		inputs.add(firstNameInput);
		inputs.add(lastNameLabel);
		inputs.add(lastNameInput);
		inputs.add(securityQuestionLabel);
		inputs.add(securityQuestionInput);
		inputs.add(securityAnswerLabel);
		inputs.add(securityAnswerInput);
		
		// Buttons
		buttons = new JPanel(new FlowLayout());

		JButton back = new JButton("Back");
		back.setActionCommand("back");
		back.addActionListener(this);
				
		JButton clear = new JButton("Clear");
		clear.setActionCommand("clear");
		clear.addActionListener(this);
				
		JButton register = new JButton("Register");
		register.setActionCommand("register");
		register.addActionListener(this);

		buttons.add(back);
		buttons.add(clear);
		buttons.add(register);
		
		add(title, BorderLayout.NORTH);
		add(inputs, BorderLayout.CENTER);
		add(buttons, BorderLayout.SOUTH);
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub

	}

	/**
	 * Method to get the email which investor entered
	 * @return email
	 * 
	 * @author Panagiotis Vakalis
	 * @version 14-07-2015
	 */
	private String getEmail(){
		return String.valueOf(emailInput.getText());
	}
	
	/**
	 * Method to get the password which investor entered
	 * @return password
	 * 
	 * @author Panagiotis Vakalis
	 * @version 14-07-2015
	 */
	private String getPassword(){
		return String.valueOf(passwordInput.getPassword());
	}
	
	/**
	 * Method to get the password which investor entered for second time
	 * @return password
	 * 
	 * @author Panagiotis Vakalis
	 * @version 14-07-2015
	 */
	private String getPassword2(){
		return String.valueOf(reEnterPasswordInput.getPassword());
	}
	
	/**
	 * Method to get the first name which investor entered
	 * @return first name
	 * 
	 * @author Panagiotis Vakalis
	 * @version 14-07-2015
	 */
	private String getFirstName(){
		return String.valueOf(firstNameInput.getText());
	}
	
	/**
	 * Method to get the last name which investor entered
	 * @return last name
	 * 
	 * @author Panagiotis Vakalis
	 * @version 14-07-2015
	 */
	private String getLastName(){
		return String.valueOf(lastNameInput.getText());
	}
	
	/**
	 * Method to get the security question which investor entered
	 * @return security question
	 * 
	 * @author Panagiotis Vakalis
	 * @version 14-07-2015
	 */
	private String getSecurityQuestion(){
		return String.valueOf(securityQuestionInput.getText());
	}
	
	/**
	 * Method to get the security answer which investor entered
	 * @return security answer
	 * 
	 * @author Panagiotis Vakalis
	 * @version 14-07-2015
	 */
	private String getSecurityAnswer(){
		return String.valueOf(securityAnswerInput.getPassword());
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		command = e.getActionCommand();
		
		if(command.equals("back")){
			registerModel.useChangeToIntroView(new IntroView(new IntroModel(registerModel.getClient())));
		}
		if(command.equals("clear")){
			registerModel.useClearFields(this);
		}
		if(command.equals("register")){
			String register = registerModel.useRegisterUser(getEmail(), getPassword(), getPassword2(), getFirstName(), getLastName(), getSecurityQuestion(), getSecurityAnswer());
//			JOptionPane.showMessageDialog(null, registerModel.useRegisterUser(getEmail(), getPassword(), getPassword2(), getFirstName(), getLastName(), getSecurityQuestion(), getSecurityAnswer()));
			JOptionPane.showMessageDialog(this, register);
//			registerModel.useChangeToIntroView(new IntroView(new IntroModel(registerModel.getClient())));
//			registerModel.useClearFields(this);
//			registerModel.useChangeToLoginView(new LoginView(new LoginModel(registerModel.getClient())));
			if(register.equals("Register succesfull")){
				String initialBalance = null;
//				registerModel.useCreateFirstPortfolio(new BigDecimal(initialBalance));
				do{
					initialBalance = JOptionPane.showInputDialog(this, "Enter the initial balance for your portfolio", "Create a portfolio", JOptionPane.PLAIN_MESSAGE);
					if(initialBalance != null){
						JOptionPane.showMessageDialog(this, registerModel.useCreateFirstPortfolio(new BigDecimal(initialBalance)), "Portfolio created", JOptionPane.PLAIN_MESSAGE);;
						break;
					}
					JOptionPane.showMessageDialog(this, "You have to enter an initial balance", "Creating portfolio problem", JOptionPane.WARNING_MESSAGE);
				}while(initialBalance == null);
				registerModel.useChangeToLoginView(new LoginView(new LoginModel(registerModel.getClient())));
			}
		}
	}

}
