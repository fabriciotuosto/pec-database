package org.pec.db.ui;

import com.google.inject.Inject;
import com.google.inject.servlet.SessionScoped;
import com.vaadin.ui.Component;
import com.vaadin.ui.SplitPanel;

@SessionScoped
public class ListView extends SplitPanel implements Component{

	private static final long serialVersionUID = 1L;

	@Inject
	public ListView(PersonList personList,PersonForm form) {
		addStyleName("view");
		setCaption("Todas las personas");
		setFirstComponent(personList);
		setSecondComponent(form);
	}
}
