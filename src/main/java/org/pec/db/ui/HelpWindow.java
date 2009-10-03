package org.pec.db.ui;

import com.google.inject.Singleton;
import com.vaadin.ui.Label;
import com.vaadin.ui.Window;

@Singleton
public class HelpWindow extends Window {
	
	private static final long serialVersionUID = 1L;
	
	private static final String HELP_HTML_SNIPPET = "Ventana de ayuda.";

	public HelpWindow() {
		setCaption("Ayuda PEC base de datos");
		setModal(true);
		setWidth("50%");
		center();
		addComponent(new Label(HELP_HTML_SNIPPET, Label.CONTENT_XHTML));
	}
}
