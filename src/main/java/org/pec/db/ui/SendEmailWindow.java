package org.pec.db.ui;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.vaadin.ui.Window;

@Singleton
public class SendEmailWindow extends Window {
	
	private static final long serialVersionUID = 1L;

	@Inject
	public SendEmailWindow(SendEmailForm form) {
		setCaption("Enviar email con base de datos");
		setModal(true);
		setWidth("50%");
		center();
		addComponent(form);
	}
}
