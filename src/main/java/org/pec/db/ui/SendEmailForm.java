package org.pec.db.ui;

import org.pec.db.ui.actions.SendEmailCommand;
import org.pec.db.ui.actions.ShowNotification;

import com.google.inject.Inject;
import com.google.inject.servlet.SessionScoped;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Form;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.RichTextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

@SessionScoped
public class SendEmailForm extends Form{

	private static final long serialVersionUID = 1L;
	private TextField from    =  null;
	private TextField to      = null;
	private TextField subject = null;
	private TextField fileName = null;
	private RichTextArea message = null;

	@SuppressWarnings("serial")
	@Inject
	public SendEmailForm(final SendEmailCommand command,final ShowNotification notification) {
		from     = new TextField("Desde");
		from.setSizeFull();
		to       = new TextField("A");
		to.setSizeFull();
		subject  = new TextField("Asunto");
		subject.setSizeFull();
		fileName = new TextField("Nombre Adjunto");
		message  = new RichTextArea();
		Button ok = new Button("Enviar");
		
		ok.addListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				command.execute();
				SendEmailForm.this.discard();
				notification.show("E-Mail enviado correctamente");
				from.setValue("");
				to.setValue("");
				subject.setValue("");
				fileName.setValue("");
				message.setValue("");
			}
		});
		
		FormLayout layout = new FormLayout();
		setLayout(layout);
		layout.addComponent(from);
		layout.addComponent(to);
		layout.addComponent(subject);
		layout.addComponent(fileName);
		layout.addComponent(message);
		HorizontalLayout footer = new HorizontalLayout();
		setFooter(footer);
		footer.addComponent(ok);
		footer.setComponentAlignment(ok, Alignment.MIDDLE_RIGHT);
	}
	
	public String getFrom() {
		return from.toString();
	}
	public String getTo() {
		return to.toString();
	}
	public String getSubject() {
		return subject.toString();
	}
	public String getFileName() {
		return fileName.toString();
	}
	public String getMessage() {
		return message.toString();
	}
}
