package pxv425;

import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * Class which is used in order to send an email This JavaMail API has been used
 * 
 * The class has been developed based on the following tutorial:
 * http://www.oracle
 * .com/webfolder/technetwork/tutorials/obe/java/javamail/javamail.html
 * 
 *
 * @version 29-07-2015
 */
public class Email {
	private int port;
	private String host;
	private String from;
	private boolean auth;
	private final String USERNAME = "jocks.project";
	private final String PASSWORD = "electro_PA16989";
	private Protocol protocol;
	private boolean debug;

	private String investorFirstName;
	private String investorLastName;
	private String investorEmail;

	private final String DEVELOPER_EMAIL = "pani.vak@gmail.com";

	/**
	 * Constructor of the class
	 * 
	 * @param investorFirstName
	 * @param investorLastName
	 * @param investorEmail
	 * 
	 * @version 29-07-2015
	 */
	public Email(String investorFirstName, String investorLastName,
			String investorEmail) {
		this.investorFirstName = investorFirstName;
		this.investorLastName = investorLastName;
		this.investorEmail = investorEmail;

		/*
		 * http://www.serversmtp.com/en/smtp-yahoo
		 */
		port = 465;
		host = "smtp.mail.yahoo.com";
		from = "jocks.project@yahoo.com";
		auth = true;
		protocol = Protocol.SMTPS;
		debug = true;
	}

	/**
	 * Method to send the email
	 * 
	 * @param body
	 * 
	 * @version 29-07-2015
	 */
	private void sendEmail(String body) {
		Properties props = new Properties();

		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", port);

		switch (protocol) {
		case SMTPS:
			props.put("mail.smtp.ssl.enable", true);
			break;
		case TLS:
			props.put("mail.smtp.starttls.enable", true);
			break;
		}

		Authenticator authenticator = null;
		if (auth) {
			props.put("mail.smtp.auth", true);
			authenticator = new Authenticator() {
				private javax.mail.PasswordAuthentication pa = new javax.mail.PasswordAuthentication(
						USERNAME, PASSWORD);

				@Override
				public javax.mail.PasswordAuthentication getPasswordAuthentication() {
					return pa;
				}
			};
		}

		Session session = Session.getInstance(props, authenticator);
		session.setDebug(debug);

		MimeMessage message = new MimeMessage(session);

		try {
			message.setFrom(new InternetAddress(from));
			InternetAddress address = new InternetAddress(DEVELOPER_EMAIL);
			message.setRecipient(Message.RecipientType.TO, address);
			message.setSubject("Investor: " + investorFirstName + " "
					+ investorLastName + " needs help");
			message.setSentDate(new Date());
			message.setText("Investor's email: " + investorEmail + "\n" + body);
			Transport.send(message);
		} catch (MessagingException e) {
			System.out.println("Message has not been sent to " + investorFirstName + " " + investorLastName);
		}
	}

	/**
	 * Method to use the sendEmail method
	 * 
	 * @param body
	 * 
	 * @author Panagiotis Vakalis
	 * @version 29-07-2015
	 */
	public void useSendEmail(String body) {
		sendEmail(body);
	}
}
