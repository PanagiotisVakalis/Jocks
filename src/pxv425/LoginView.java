package pxv425;


//import RegisterView;
//import ResetPasswordView;
/**
 * 
 */
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
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 * Class which contain the View part of the Login process
 * 
 * @author Panagiotis Vakalis
 * @version 16-07-2015
 *
 */
public class LoginView extends View implements Observer, ActionListener {
	
	private LoginModel loginModel;
	private JLabel emailLabel;
	private JTextField emailInput;
	private JLabel passwordLabel;
	private JPasswordField passwordInput;
	private JPanel inputs;
	private JButton changePasswordButton;
	private JButton loginButton;
	private JButton exitButton;
	private JPanel buttons;
	private JLabel title;
	private String command;

	/**
	 * Constructor of the class
	 * @param loginModel
	 * 
	 * @author Panagiotis Vakalis
	 * @version 16-07-2015
	 */
	public LoginView(LoginModel loginModel) {
		super(loginModel);
		this.loginModel = loginModel;
		
		loginModel.addObserver(this);
		
		frameSetup();
	}

	/**
	 * Method which builds the frame
	 * 
	 * @author Panagiotis Vakalis
	 * @version 16-07-2015
	 */
	private void frameSetup(){
		setSize(350, 200);
		
		setLayout(new BorderLayout());
		
		// TextFields for email and password
		emailLabel = new JLabel("email");
		emailInput = new JTextField();
		passwordLabel = new JLabel("password");
		passwordInput = new JPasswordField();
		
		// Panel for inputs (email, password)
		inputs = new JPanel(new GridLayout(4, 0));
		inputs.add(emailLabel);
		inputs.add(emailInput);
		inputs.add(passwordLabel);
		inputs.add(passwordInput);
		
		// Buttons
		changePasswordButton = new JButton("Change password");
		changePasswordButton.setActionCommand("change");
		changePasswordButton.addActionListener(this);

		loginButton = new JButton("Login");
		loginButton.setActionCommand("login");
		loginButton.addActionListener(this);
				
		exitButton = new JButton("Exit");
		exitButton.setActionCommand("exit");
		exitButton.addActionListener(this);
		
		// Pannel for buttons
		buttons = new JPanel(new FlowLayout());
		buttons.add(exitButton);
		buttons.add(changePasswordButton);
		buttons.add(loginButton);
		
		title = new JLabel("Login to your account");
		
		// Add panels on layout
		add(title, BorderLayout.NORTH);
		add(inputs, BorderLayout.CENTER);
		add(buttons, BorderLayout.SOUTH);
	}
	
	/**
	 * Method to get the email
	 * @return email
	 * 
	 * @author Panagiotis Vakalis
	 * @version 16-07-2015
	 */
	private String getEmail(){
		//Convert email to lower case
//		return String.valueOf(emailInput.getText());
		String emailLower = emailInput.getText().toLowerCase();
		
//		return String.valueOf(emailLower);
		return emailLower;
	}
	
	/**
	 * Method to get the password
	 * @return password
	 * 
	 * @author Panagiotis Vakalis
	 * @version 16-07-2015
	 */
	private String getPassword(){
		return String.valueOf(passwordInput.getPassword());
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		command = e.getActionCommand();
		
		if(command.equals("exit")){
			loginModel.useExitProgramme();
		}
		if (command.equals("change")) {
			loginModel.useChangeToChangePasswordView(new ChangePasswordView(new ChangePasswordModel(loginModel.getClient())));
		}
		if (command.equals("login")) {
//			JOptionPane.showMessageDialog(null, loginModel.useLoginUser(getEmail(), getPassword()));
//			loginModel.useLoginUser(getEmail(), getPassword());
			if(!getEmail().isEmpty() && !getPassword().isEmpty()){
				if(loginModel.useValidEmail(getEmail())){
					if((loginModel.useLoginUser(getEmail(), getPassword())).equals("ok")){
						loginModel.useChangeToPortfolioView(new PortfolioView(new PortfolioModel(loginModel.getClient())));
					}
					else{
						JOptionPane.showMessageDialog(this, "Wrong email or password");
//						loginModel.useChangeToLoginView(new LoginView(new LoginModel(loginModel.getClient())));
					}
				}
				else{
					JOptionPane.showMessageDialog(this, "Email is not valid", "Error in login", JOptionPane.ERROR_MESSAGE);
				}
			}
			else{
				JOptionPane.showMessageDialog(this, "Enter email and password", "Error in login", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub

	}

}
