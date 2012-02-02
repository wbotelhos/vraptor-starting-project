package br.com.wbotelhos.starting.util;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

import org.apache.commons.mail.HtmlEmail;

import br.com.wbotelhos.starting.exception.CommonException;

public class Email {

	private static final String SMTP = "smtp.gmail.com";
	private static final String SMTP_USUARIO = "USER@gmail.com";
	private static final String SMTP_SENHA = "PASSWORD";

	public static void enviar(String to, String subject, String body) throws CommonException {
		try {
			HtmlEmail email = new HtmlEmail();

			Authenticator auth = new Authenticator() {
				@Override
				public PasswordAuthentication getPasswordAuthentication() {
			    	return new PasswordAuthentication(SMTP_USUARIO, SMTP_SENHA);
			    }
			};

			email.setAuthenticator(auth);
			email.setHostName(SMTP);
			email.setSSL(true);
			email.setSmtpPort(465);

			email
			.setHtmlMsg(body)
			.setFrom(SMTP_USUARIO)
			.addTo(to)
			.setSubject(subject)
			.send();
		} catch (Exception e) {
			e.printStackTrace();
			throw new CommonException("erro.enviar.email", e);
		}
	}
	
}
