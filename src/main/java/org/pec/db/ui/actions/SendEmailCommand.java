package org.pec.db.ui.actions;

import java.io.File;

import javax.mail.Multipart;

import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.HtmlEmail;
import org.apache.commons.mail.MultiPartEmail;
import org.pec.db.ui.PecApplication;
import org.pec.db.ui.SendEmailForm;
import org.pec.db.ui.SendEmailWindow;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;

@Singleton
public class SendEmailCommand implements Command<Object>{

	private final GenerateExcelCommand generateExcelCmd;
	private final Provider<SendEmailForm> emailForm;
	private final Provider<SendEmailWindow> emaProvider;
	private final Provider<PecApplication> appP;
	@Inject
	public SendEmailCommand(GenerateExcelCommand generateExcelCmd,Provider<SendEmailForm> emailForm,Provider<SendEmailWindow> emailWindow,Provider<PecApplication> appP) {
		this.generateExcelCmd = generateExcelCmd;
		this.emailForm = emailForm;
		this.emaProvider = emailWindow;
		this.appP = appP;
	}
	@Override
	public Object execute() {
		SendEmailForm form = emailForm.get();
		File file = generateExcelCmd.execute();		
		try {
			MultiPartEmail email = new MultiPartEmail();
			// configuring client
			email.setHostName("smtp.gmail.com");
			email.setSmtpPort(465);
			email.setSSL(true);
			email.setAuthentication("lorenapec@gmail.com", "28171024");
			//Attachement
			EmailAttachment attach = new EmailAttachment();
			attach.setPath(file.getAbsolutePath());
			attach.setName(form.getFileName()+".xls");
			attach.setDescription(EmailAttachment.ATTACHMENT);
			email.attach(attach);
			// send email information
			email.addTo(form.getTo());
			email.setFrom(form.getFrom());
			email.setSubject(form.getSubject());
			email.addPart(form.getMessage(), MultiPartEmail.TEXT_HTML);
			email.send();
			file.delete();
			appP.get().getMainWindow().removeWindow(emaProvider.get());
			emaProvider.get().setVisible(false);
		} catch (Exception e) {
			throw new Error(e);
		}
		return null;
	}

}
