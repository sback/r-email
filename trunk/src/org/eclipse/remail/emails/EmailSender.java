package org.eclipse.remail.emails;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.eclipse.remail.Activator;

/**
 * This class is used to send emails
 * 
 * @author Lorenzo Baracchi <lorenzo.baracchi@usi.ch>
 * 
 */
public class EmailSender {

	private String from;
	private String to;
	private String cc;
	private String bcc;
	private String subject;
	private String mailContent;
	
	private SMTPAccount account;

	/**
	 * Basic constructor
	 * 
	 * @param from
	 *            who send the email
	 * @param to
	 *            the email's receiver
	 * @param subject
	 *            the email's subject
	 * @param mailContent
	 *            the message
	 */
	public EmailSender(String from, String to, String subject, String mailContent) {
		this.from = from;
		this.to = to;
		this.subject = subject;
		this.mailContent = mailContent.replace("\n", "<br>");;

		this.cc = null;
		this.bcc = null;
		
		setAccount();
	}

	/**
	 * Complete constructor If one of cc or bcc are empty (equals to "") they
	 * are set to null
	 * 
	 * @param from
	 *            who send the email
	 * @param to
	 *            the email's receiver
	 * @param cc
	 *            the email's "carbon copy"
	 * @param bcc
	 *            the email's "blind carbon copy"
	 * @param subject
	 *            the email's subject
	 * @param mailContent
	 *            the message
	 */
	public EmailSender(String from, String to, String cc, String bcc, String subject,
			String mailContent) {
		this.from = from;
		this.to = to;
		this.cc = cc;
		this.bcc = bcc;
		this.subject = subject;
		this.mailContent = mailContent.replace("\n", "<br>");

		if (cc.equals(""))
			this.cc = null;
		if (bcc.equals(""))
			this.bcc = null;
		
		setAccount();
	}
	
	/**
	 * Retrieve the selected account form the properties
	 */
	private void setAccount(){
		String s = Activator.getAccounts();
		ListSMTPAccount storedAccounts;
		if (s.equals("") || s.equals(Activator.DEFAULT_ACCOUNTS_SMTP))
			storedAccounts = new ListSMTPAccount();
		else
			storedAccounts = ListSMTPAccount.fromString(s);
		for(SMTPAccount a : storedAccounts)
			if(a.getMailAddress().equals(from))
				account=a;
	}

	/**
	 * Send the email
	 */
	public void send() {
		//creates the properties
		Properties props = new Properties();
		props.put("mail.smtp.host", account.getServer());
		props.put("mail.smtp.auth", "true");
		props.put("mail.debug", "true");
		props.put("mail.smtp.port", account.getPort());
		props.put("mail.smtp.socketFactory.port", account.getPort());
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.socketFactory.fallback", "false");
		props.put("mail.smtp.starttls.enable", "true");

		//Print out configurations
		System.out.println("Server: -"+ account.getServer()+"-");
		System.out.println("Port: -"+ account.getPort()+"-");
		System.out.println("Username: -"+account.getUsername()+"-");
		System.out.println("Password: -"+account.getPassword()+"-");
		
		//username and password
		Session mailSession = Session.getDefaultInstance(props, new javax.mail.Authenticator() {

			protected PasswordAuthentication getPasswordAuthentication() {	
				return new PasswordAuthentication(account.getUsername(), account.getPassword());
			}
		});
		mailSession.setDebug(true);
		Message msg = new MimeMessage(mailSession);
		try{
		
		//Add recipients
		String[] recipients = EmailSender.splitMailAddress(to);
		InternetAddress[] addressTo = new InternetAddress[recipients.length];
			for (int i = 0; i < recipients.length; i++) {
				addressTo[i] = new InternetAddress(recipients[i]);
			}
		msg.setRecipients(Message.RecipientType.TO, addressTo);

		// Setting the Subject and Content Type
		msg.setSubject(subject);
		msg.setContent(mailContent, "text/html");
		Transport.send(msg);
		}catch (AddressException e) {
			// TODO: handle exception
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	/**
	 * Split the string containing email address into an array of email address
	 * @param s a string in the form "foo@bar.com, foo@foobar.org"
	 * @return an array like ["foo@bar.com", "foo@foobar.org"]
	 */
	public static String[] splitMailAddress(String s){
		return s.split(", ");
	}
}
