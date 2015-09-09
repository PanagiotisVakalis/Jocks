package pxv425;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.math.BigDecimal;
import java.util.Observable;
import java.util.Observer;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 * Class which contains the view part of the 
 * register screen
 * 
 * @author Panagiotis Vakalis
 * @version 14-07-2015
 *
 */
public class RegisterView extends View implements ActionListener, Observer,
		FocusListener {

	private static final long serialVersionUID = 1L;
	private RegisterModel registerModel;
	private JLabel title;
	private JTextField emailInput;
	private JLabel passwordLabel;
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
	private JPanel buttons;
	private String command;
	private JPanel emailPanel;
	private JLabel emailCorrect;
	private ImageIcon emailCorrectIcon;
	private JPanel passwordPanel;
	private JPanel namePanel;
	private JPanel securityQuestionPanel;

	/**
	 * Constructor of the class
	 * 
	 * @param registermodel
	 * 
	 * @author Panagiotis Vakalis
	 * @version 14-07-2015
	 */
	public RegisterView(RegisterModel registermodel) {
		super(registermodel);
		this.registerModel = registermodel;

		registermodel.addObserver(this);

		frameSetup();
	}

	/**
	 * Method which builds the frame
	 * 
	 * @author Panagiotis Vakalis
	 * @version 14-07-2015
	 */
	private void frameSetup() {

		setLayout(new GridLayout(6, 1));
		setBackground(getWindowColor());

		title = new JLabel("Register");
		title.setBackground(getWindowColor());

		// Panels for inputs

		// Email panel
		emailPanel = new JPanel(new FlowLayout());
		emailPanel.setBackground(getWindowColor());
		emailInput = new JTextField("Email", 15);
		emailInput.addFocusListener(this);
		emailCorrectIcon = new ImageIcon();
		emailCorrect = new JLabel(emailCorrectIcon);

		emailPanel.add(emailInput);
		emailPanel.add(emailCorrect);

		// Password panel
		passwordPanel = new JPanel(new GridLayout(2, 2));
		passwordPanel.setBackground(getWindowColor());
		passwordLabel = new JLabel("Password");
		passwordInput = new JPasswordField(16);
		passwordInput
				.setToolTipText("Password should contain 8 to 16 characters, \nat least one number \nand one of the following symbols: ! @ # $ % ^ & * - _");
		reEnterPasswordLabel = new JLabel("Re-enter password");
		reEnterPasswordInput = new JPasswordField(16);

		passwordPanel.add(passwordLabel);
		passwordPanel.add(reEnterPasswordLabel);
		passwordPanel.add(passwordInput);
		passwordPanel.add(reEnterPasswordInput);

		// Name panel
		namePanel = new JPanel(new GridLayout(2, 2));
		namePanel.setBackground(getWindowColor());
		firstNameLabel = new JLabel("First name");
		firstNameInput = new JTextField(16);
		lastNameLabel = new JLabel("Last name");
		lastNameInput = new JTextField(16);

		namePanel.add(firstNameLabel);
		namePanel.add(lastNameLabel);
		namePanel.add(firstNameInput);
		namePanel.add(lastNameInput);

		// Security question panel
		securityQuestionPanel = new JPanel(new GridLayout(2, 2));
		securityQuestionPanel.setBackground(getWindowColor());
		securityQuestionLabel = new JLabel("Security question");
		securityQuestionInput = new JTextField(16);
		securityQuestionInput.setToolTipText("do not include ?");
		securityAnswerLabel = new JLabel("Security answer");
		securityAnswerInput = new JPasswordField(16);

		securityQuestionPanel.add(securityQuestionLabel);
		securityQuestionPanel.add(securityAnswerLabel);
		securityQuestionPanel.add(securityQuestionInput);
		securityQuestionPanel.add(securityAnswerInput);

		// Buttons
		buttons = new JPanel(new FlowLayout());
		buttons.setBackground(getWindowColor());

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

		// Add components to the main panel
		add(title);
		add(emailPanel);
		add(passwordPanel);
		add(namePanel);
		add(securityQuestionPanel);
		add(buttons);
	}

	/**
	 * Method to get the email which investor entered
	 * 
	 * @return email
	 * 
	 * @author Panagiotis Vakalis
	 * @version 14-07-2015
	 */
	private String getEmail() {
		return String.valueOf(emailInput.getText());
	}

	/**
	 * Method to get the password which investor entered
	 * 
	 * @return password
	 * 
	 * @author Panagiotis Vakalis
	 * @version 14-07-2015
	 */
	private String getPassword() {
		return String.valueOf(passwordInput.getPassword());
	}

	/**
	 * Method to get the password which investor entered for second time
	 * 
	 * @return password
	 * 
	 * @author Panagiotis Vakalis
	 * @version 14-07-2015
	 */
	private String getPassword2() {
		return String.valueOf(reEnterPasswordInput.getPassword());
	}

	/**
	 * Method to get the first name which investor entered
	 * 
	 * @return first name
	 * 
	 * @author Panagiotis Vakalis
	 * @version 14-07-2015
	 */
	private String getFirstName() {
		return String.valueOf(firstNameInput.getText());
	}

	/**
	 * Method to get the last name which investor entered
	 * 
	 * @return last name
	 * 
	 * @author Panagiotis Vakalis
	 * @version 14-07-2015
	 */
	private String getLastName() {
		return String.valueOf(lastNameInput.getText());
	}

	/**
	 * Method to get the security question which investor entered
	 * 
	 * @return security question
	 * 
	 * @author Panagiotis Vakalis
	 * @version 14-07-2015
	 */
	private String getSecurityQuestion() {
		return String.valueOf(securityQuestionInput.getText());
	}

	/**
	 * Method to get the security answer which investor entered
	 * 
	 * @return security answer
	 * 
	 * @author Panagiotis Vakalis
	 * @version 14-07-2015
	 */
	private String getSecurityAnswer() {
		return String.valueOf(securityAnswerInput.getPassword());
	}

	@Override
	public void update(Observable o, Object arg) {
		/*
		 * No body
		 */

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		changeOptionPaneBackground();
		command = e.getActionCommand();

		if (command.equals("back")) {
			registerModel.useChangeToIntroView(new IntroView(new IntroModel(
					registerModel.getClient())));
		}
		if (command.equals("clear")) {
			registerModel.useClearFields(this);
		}
		if (command.equals("register")) {
			String register = registerModel.useRegisterUser(getEmail(),
					getPassword(), getPassword2(), getFirstName(),
					getLastName(), getSecurityQuestion(), getSecurityAnswer());
			JOptionPane.showMessageDialog(this, register);

			if (register.equals("Register succesfull")) {
				String initialBalance = null;
				do {
					initialBalance = JOptionPane
							.showInputDialog(
									this,
									"Enter the initial balance for your portfolio in £.",
									"Create a portfolio",
									JOptionPane.PLAIN_MESSAGE);
					if (initialBalance != null) {
						JOptionPane.showMessageDialog(this, registerModel
								.useCreateFirstPortfolio(new BigDecimal(
										initialBalance)), "Portfolio created",
								JOptionPane.PLAIN_MESSAGE);
						;
						break;
					}
					JOptionPane.showMessageDialog(this,
							"You have to enter an initial balance",
							"Creating portfolio problem",
							JOptionPane.WARNING_MESSAGE);
				} while (initialBalance == null);
				registerModel.useChangeToLoginView(new LoginView(
						new LoginModel(registerModel.getClient())));
			}
		}
	}

	@Override
	public void focusGained(FocusEvent e) {
		if (e.getSource().equals(emailInput)) {
			emailInput.setText("");
		}
	}

	@Override
	public void focusLost(FocusEvent e) {
		/*
		 * No body
		 */
	}
}
