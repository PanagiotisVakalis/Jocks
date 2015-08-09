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
import javax.swing.JTextArea;
import javax.swing.JTextField;
/**
 * Class which contains the view part of the reset password process
 * @author Panagiotis Vakalis
 * @version 17-07-2015
 *
 */
public class ChangePasswordViewOld extends View implements ActionListener, Observer {
	
	private static final long serialVersionUID = 1L;
	private ChangePasswordModel changePasswordModel;
	private JLabel emailLabel;
	private JTextField emailInput;
	private JButton submitButton;
	private JPanel emailPanel;
	private JPanel securityQuestionAnswerPanel;
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
	
	
	/**
	 * Constructor of the class
	 * @param resetPasswordModel
	 * 
	 * @author Panagiotis Vakalis
	 * @version 17-07-2015
	 */
	public ChangePasswordViewOld(ChangePasswordModel changePasswordModel) {
		super(changePasswordModel);
		this.changePasswordModel = changePasswordModel;
		
		changePasswordModel.addObserver(this);
		
		frameSetup();
	}
	
	/**
	 * Method to build the frame
	 * 
	 * @author Panagiotis Vakalis
	 * @version 17-07-2015
	 */
	private void frameSetup(){
		setSize(350, 250);
		
		setLayout(new BorderLayout());
		
		//Panel for email and submit button
		emailPanel = new JPanel(new BorderLayout());
		emailLabel = new JLabel("Email");
		emailInput = new JTextField();
		
		submitButton = new JButton("Submit");
		submitButton.setActionCommand("submit");
		submitButton.addActionListener(this);
		
		emailPanel.add(emailLabel, BorderLayout.NORTH);
		emailPanel.add(emailInput, BorderLayout.CENTER);
		emailPanel.add(submitButton, BorderLayout.SOUTH);
		
		//Panel for security question, answer and new password
		securityQuestionAnswerPanel = new JPanel(new GridLayout(7, 1));
		securityQuestion = new JTextArea("Security question");
		securityQuestion.setEditable(false);
		securityAnswerLabel = new JLabel("Enter your security answer");
		securityAnswer = new JPasswordField();
		newPasswordLabel = new JLabel("Enter you new password");
		newPassword = new JPasswordField();
		reEnterNewPasswordLabel = new JLabel("Re - enter password");
		reEnterNewPassword = new JPasswordField();
		
		securityQuestionAnswerPanel.add(securityQuestion);
		securityQuestionAnswerPanel.add(securityAnswerLabel);
		securityQuestionAnswerPanel.add(securityAnswer);
		securityQuestionAnswerPanel.add(newPasswordLabel);
		securityQuestionAnswerPanel.add(newPassword);
		securityQuestionAnswerPanel.add(reEnterNewPasswordLabel);
		securityQuestionAnswerPanel.add(reEnterNewPassword);
		
		//Panel for buttons
		buttonsPannel = new JPanel(new FlowLayout());
		
		backButton = new JButton("Back");
		backButton.setActionCommand("back");
		backButton.addActionListener(this);
		
		changeButton = new JButton("Change");
		changeButton.setActionCommand("change");
		changeButton.addActionListener(this);
		
		buttonsPannel.add(backButton);
		buttonsPannel.add(changeButton);
		
		//Add panels to the main panel
		add(emailPanel, BorderLayout.NORTH);
		add(securityQuestionAnswerPanel, BorderLayout.CENTER);
		add(buttonsPannel, BorderLayout.SOUTH);
	}
	
	/**
	 * Method to get the email of the JTextField
	 * @return email
	 * 
	 * @author Panagiotis Vakalis
	 * @version 17-07-2015
	 */
	private String getEmail(){
		//Convert email to lowercase
		String emailLower = emailInput.getText().toLowerCase();
//		String emailLower;
//		if(emailInput.getText().isEmpty()){
//			emailLower = "empty";
//		}
//		else{
//			emailLower = emailInput.getText().toLowerCase();
//		}
//		return String.valueOf(emailLower);
		return emailLower;
	}
	
	/**
	 * Method to get the security answer of the JTextField
	 * @return security answer
	 * 
	 * @author Panagiotis Vakalis
	 * @version 17-07-2015
	 */
	private String getSecurityAnswer(){
		return String.valueOf(securityAnswer.getPassword());
	}

	/**
	 * Method to get the new password of the JPasswordField
	 * @return new password
	 * 
	 * @author Panagiotis Vakalis
	 * @version 17-07-2015
	 */
	private String getNewPassword(){
		return String.valueOf(newPassword.getPassword());
	}
	
	private String getReEnterNewPassword(){
		return String.valueOf(reEnterNewPassword.getPassword());
	}

	@Override
	public void update(Observable o, Object arg) {
		if(o instanceof ChangePasswordModel){
//			securityQuestion.setText(resetPasswordModel.useRetrieveSecurityQuestion(getEmail()));
			securityQuestion.setText(changePasswordModel.getSecurityQuestion());
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		command = e.getActionCommand();
		
		if(command.equals("submit")){
//			resetPasswordModel.useSubmitEmail(getEmail());
//			resetPasswordModel.getSecurityQuestion();
			if(getEmail().length() != 0){
				if(changePasswordModel.useValidEmail(getEmail())){
//					changePasswordModel.useSubmitEmail(getEmail());
//					changePasswordModel.getSecurityQuestion();
					if(changePasswordModel.useEmailIsUsed(getEmail())){
						changePasswordModel.useSubmitEmail(getEmail());
//						changePasswordModel.getSecurityQuestion();
					}
					else{
						JOptionPane.showMessageDialog(this, "This email is not used", "No such email", JOptionPane.INFORMATION_MESSAGE);
					}
				}
				else{
					JOptionPane.showMessageDialog(this, "The email is not valid", "Email problem", JOptionPane.WARNING_MESSAGE);
				}
			}
			else{
				JOptionPane.showMessageDialog(this, "Enter your email first");
			}
		}
		if(command.equals("back")){
			changePasswordModel.useChangeToLoginView(new LoginView(new LoginModel(changePasswordModel.getClient())));
		}
		if(command.equals("change")){
//			JOptionPane.showMessageDialog(null, changePasswordModel.useChangePassword(getEmail(), getSecurityAnswer(), getNewPassword()));
//			int answer = JOptionPane.showConfirmDialog(null, changePasswordModel.useChangePassword(getEmail(), getSecurityAnswer(), getNewPassword()));
//			int answer = JOptionPane.showConfirmDialog(this, changePasswordModel.useChangePassword(getEmail(), getSecurityAnswer(), getNewPassword()), "Change password", );
			String[] option = {"OK", "BACK"};
			int answer = JOptionPane.showOptionDialog(this, changePasswordModel.useChangePassword(getEmail(), getSecurityAnswer(), getNewPassword(), getReEnterNewPassword()), "Change password", JOptionPane.PLAIN_MESSAGE, JOptionPane.OK_CANCEL_OPTION, null, option, option[0]);
			if(answer == JOptionPane.OK_OPTION){
				changePasswordModel.useChangeToLoginView(new LoginView(new LoginModel(changePasswordModel.getClient())));
			}
//			if(answer == JOptionPane.CANCEL_OPTION){
//				JOptionPane.getRootFrame();
//			}
		}

	}

}
