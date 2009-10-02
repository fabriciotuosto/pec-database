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
		/*
		* Make the window modal, which will disable all other components while
		* it is visible
		*/
		setModal(true);
		/* Make the sub window 50% the size of the browser window */
		setWidth("50%");
		/*
		* Center the window both horizontally and vertically in the browser
		* window
		*/
		center();
		addComponent(new Label(HELP_HTML_SNIPPET, Label.CONTENT_XHTML));
	}
}
