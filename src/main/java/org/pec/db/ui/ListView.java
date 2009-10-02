package org.pec.db.ui;

import com.vaadin.ui.Component;
import com.vaadin.ui.SplitPanel;

public class ListView extends SplitPanel implements Component{

	private static final long serialVersionUID = 1L;

	public ListView(Component first,Component second) {
		addStyleName("view");
		setCaption("Todas las personas");
		setFirstComponent(first);
		setSecondComponent(second);
	}
}
