package pxv425;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ChangePasswordView extends View implements ActionListener,
		Observer, FocusListener {
	private static final long serialVersionUID = 1L;
	private ChangePasswordModel changePasswordModel;
	private JTextField emailInput;
	private JButton submitButton;
	private JPanel emailPanel;
	private JTextArea securityQuestion;
	private JPasswordField securityAnswer;
	private JPanel buttonsPannel;
	private JButton backButton;
	private JButton changeButton;
	private String command;
	private JPasswordField newPassword;
	private JLabel newPasswordLabel;
	private JLabel securityAnswerLabel;
	private JLabel reEnterNewPasswordLabel;
	private JPasswordField reEnterNewPassword;
	private JPanel newPasswordPanel;
	private JLabel title;
	private JPanel submitButtonPanel;
	private JPanel securityAnswerPanel;
	private JPanel securityQuestionPanel;

	/**
	 * Constructor of the class
	 * 
	 * @param resetPasswordModel
	 * 
	 * @author Panagiotis Vakalis
	 * @version 17-07-2015
	 */
	public ChangePasswordView(ChangePasswordModel changePasswordModel) {
		super(changePasswordModel);
		this.changePasswordModel = changePasswordModel;

		changePasswordModel.addObserver(this);

		frameSetup();
	}

	/**
	 * Method which builds the frame
	 * 
	 * @author Panagiotis Vakalis
	 * @version 17-07-2015
	 */
	private void frameSetup() {
		setLayout(new GridLayout(7, 1));

		setBackground(getWindowColor());

		title = new JLabel("Change your password");
		title.setBackground(getWindowColor());

		// Email
		emailPanel = new JPanel(new FlowLayout());
		emailPanel.setBackground(getWindowColor());
		emailInput = new JTextField("Email", 16);
		emailInput.addFocusListener(this);

		emailPanel.add(emailInput);

		// Submit button
		submitButton = new JButton("Submit");
		submitButton.setActionCommand("submit");
		submitButton.addActionListener(this);
		submitButton.setPreferredSize(new Dimension(100, 30));
		submitButton
				.setToolTipText("Press submit in order to retrieve youe security question");

		submitButtonPanel = new JPanel(new FlowLayout());
		submitButtonPanel.setBackground(getWindowColor());
		submitButtonPanel.add(submitButton);

		// Security question area
		securityQuestionPanel = new JPanel(new FlowLayout());
		securityQuestionPanel.setBackground(getWindowColor());
		securityQuestion = new JTextArea("Security question");
		securityQuestion.setBackground(getAreaColor());
		securityQuestion.setEditable(false);
		securityQuestion.setLineWrap(true);
		securityQuestion.setPreferredSize(new Dimension(500, 30));
		securityQuestionPanel.add(securityQuestion);

		// Security answer panel
		securityAnswerPanel = new JPanel(new GridLayout(2, 1));
		securityAnswerPanel.setBackground(getWindowColor());

		securityAnswerLabel = new JLabel("Enter your security answer");
		securityAnswer = new JPasswordField(16);

		securityAnswerPanel.add(securityAnswerLabel);
		securityAnswerPanel.add(securityAnswer);

		// Panel for the new password
		newPasswordPanel = new JPanel(new GridLayout(2, 2));
		newPasswordPanel.setBackground(getWindowColor());
		newPasswordLabel = new JLabel("Enter you new password");
		reEnterNewPasswordLabel = new JLabel("Re - enter password");
		newPassword = new JPasswordField(16);
		newPassword
				.setToolTipText("Password should contain 8 to 16 characters, \nat least one number \nand one of the following symbols: ! @ # $ % ^ & * - _");
		reEnterNewPassword = new JPasswordField(16);

		newPasswordPanel.add(newPasswordLabel);
		newPasswordPanel.add(reEnterNewPasswordLabel);
		newPasswordPanel.add(newPassword);
		newPasswordPanel.add(reEnterNewPassword);

		// Panel for buttons
		buttonsPannel = new JPanel(new FlowLayout());
		buttonsPannel.setBackground(getWindowColor());

		backButton = new JButton("Back");
		backButton.setActionCommand("back");
		backButton.addActionListener(this);

		changeButton = new JButton("Change");
		changeButton.setActionCommand("change");
		changeButton.addActionListener(this);

		buttonsPannel.add(backButton);
		buttonsPannel.add(changeButton);

		add(title);
		add(emailPanel);
		add(submitButtonPanel);
		add(securityQuestionPanel);
		add(securityAnswerPanel);
		add(newPasswordPanel);
		add(buttonsPannel);
	}

	/**
	 * Method to get the email of the JTextField
	 * 
	 * @return email
	 * 
	 * @author Panagiotis Vakalis
	 * @version 17-07-2015
	 */
	private String getEmail() {
		// Convert email to lowercase
		String emailLower = emailInput.getText().toLowerCase();
		return emailLower;
	}

	/**
	 * Method to get the security answer of the JTextField
	 * 
	 * @return security answer
	 * 
	 * @author Panagiotis Vakalis
	 * @version 17-07-2015
	 */
	private String getSecurityAnswer() {
		return String.valueOf(securityAnswer.getPassword());
	}

	/**
	 * Method to get the new password of the JPasswordField
	 * 
	 * @return new password
	 * 
	 * @author Panagiotis Vakalis
	 * @version 17-07-2015
	 */
	private String getNewPassword() {
		return String.valueOf(newPassword.getPassword());
	}

	private String getReEnterNewPassword() {
		return String.valueOf(reEnterNewPassword.getPassword());
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

	@Override
	public void update(Observable o, Object arg) {
		if (o instanceof ChangePasswordModel) {
			securityQuestion.setText(changePasswordModel.getSecurityQuestion());
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		command = e.getActionCommand();

		if (command.equals("submit")) {
			if (getEmail().length() != 0 && !getEmail().equals("email")) {
				/*
				 * If user has entered an email
				 */
				if (changePasswordModel.useValidEmail(getEmail())) {
					/*
					 * If the email is valid
					 */
					if (changePasswordModel.useEmailIsUsed(getEmail())) {
						/*
						 * If email exists
						 */
						changePasswordModel.useSubmitEmail(getEmail());
					} else {
						JOptionPane.showMessageDialog(this,
								"This email is not used", "No such email",
								JOptionPane.INFORMATION_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(this,
							"The email is not valid", "Email problem",
							JOptionPane.WARNING_MESSAGE);
				}
			} else {
				JOptionPane.showMessageDialog(this, "Enter your email first");
			}
		}
		if (command.equals("back")) {
			changePasswordModel.useChangeToLoginView(new LoginView(
					new LoginModel(changePasswordModel.getClient())));
		}
		if (command.equals("change")) {
			String[] option = { "OK", "BACK" };
			int answer = JOptionPane.showOptionDialog(this, changePasswordModel
					.useChangePassword(getEmail(), getSecurityAnswer(),
							getNewPassword(), getReEnterNewPassword()),
					"Change password", JOptionPane.PLAIN_MESSAGE,
					JOptionPane.OK_CANCEL_OPTION, null, option, option[0]);
			if (answer == JOptionPane.OK_OPTION) {
				/*
				 * If user has pressed ok
				 */
				changePasswordModel.useChangeToLoginView(new LoginView(
						new LoginModel(changePasswordModel.getClient())));
			}
		}

	}
}
