package pxv425;
/**
 * Enum which is used in the email class
 * 
 * The Enum has been developed based on the following tutorial:
 * http://www.oracle.com/webfolder/technetwork/tutorials/obe/java/javamail/javamail.html
 * 
 *
 * @version 29-07-2015
 */
public enum Protocol {
	
	/*
	 * enum has been used because the:
	 * SMTP, SMTPS, TLS
	 * are a list of values which they do not have
	 * data stored in it
	 */
	SMTP,
	SMTPS,
	TLS
}
