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
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 * Class which contains the View part of the Login process
 * 
 * @author Panagiotis Vakalis
 * @version 16-07-2015
 *
 */
public class LoginView extends View implements Observer, ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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
	 * 
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
	private void frameSetup() {
		setSize(350, 200);
		setBackground(getWindowColor());

		setLayout(new BorderLayout());

		// TextFields for email and password
		emailLabel = new JLabel("email");
		emailInput = new JTextField();
		passwordLabel = new JLabel("password");
		passwordInput = new JPasswordField();

		// Panel for inputs (email, password)
		inputs = new JPanel(new GridLayout(4, 0));
		inputs.setBackground(getWindowColor());
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
		buttons.setBackground(getWindowColor());
		buttons.add(exitButton);
		buttons.add(changePasswordButton);
		buttons.add(loginButton);

		title = new JLabel("Login to your account");
		title.setBackground(getWindowColor());

		// Add panels on layout
		add(title, BorderLayout.NORTH);
		add(inputs, BorderLayout.CENTER);
		add(buttons, BorderLayout.SOUTH);
	}

	/**
	 * Method to get the email
	 * 
	 * @return email
	 * 
	 * @author Panagiotis Vakalis
	 * @version 16-07-2015
	 */
	private String getEmail() {
		// Convert email to lower case
		String emailLower = emailInput.getText().toLowerCase();
		return emailLower;
	}

	/**
	 * Method to get the password
	 * 
	 * @return password
	 * 
	 * @author Panagiotis Vakalis
	 * @version 16-07-2015
	 */
	private String getPassword() {
		return String.valueOf(passwordInput.getPassword());
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		changeOptionPaneBackground();
		command = e.getActionCommand();

		if (command.equals("exit")) {
			loginModel.useExitProgramme();
		}
		if (command.equals("change")) {
			loginModel.useChangeToChangePasswordView(new ChangePasswordView(
					new ChangePasswordModel(loginModel.getClient())));
		}
		if (command.equals("login")) {
			if (!getEmail().isEmpty() && !getPassword().isEmpty()) {
				/*
				 * If email and password are not empty
				 */
				if (loginModel.useValidEmail(getEmail())) {
					/*
					 * If the email which the user has typed is valid
					 */
					if ((loginModel.useLoginUser(getEmail(), getPassword()))
							.equals("ok")) {
						/*
						 * If the user has been found in database
						 */
						JOptionPane.showMessageDialog(this, "Hello "
								+ loginModel.getClient().getInvestor()
										.getFirstName(), "Welcome",
								JOptionPane.PLAIN_MESSAGE);
						if (!loginModel.useIsValidDayAndTime()) {
							/*
							 * If the stock market is closed
							 */
							JOptionPane.showMessageDialog(this,
									"The stock market is currently closed",
									"Closed stock market",
									JOptionPane.WARNING_MESSAGE);
						}
						loginModel.useChangeToPortfolioView(new PortfolioView(
								new PortfolioModel(loginModel.getClient())));
					} else {
						JOptionPane.showMessageDialog(this,
								"Wrong email or password");
					}
				} else {
					JOptionPane.showMessageDialog(this, "Email is not valid",
							"Error in login", JOptionPane.ERROR_MESSAGE);
				}
			} else {
				JOptionPane.showMessageDialog(this, "Enter email and password",
						"Error in login", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	@Override
	public void update(Observable o, Object arg) {
		/*
		 * No body
		 */
	}

}
