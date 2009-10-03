package org.pec.db.ui.fields;

import org.pec.db.entities.Status;

import com.vaadin.data.Item;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.DefaultFieldFactory;
import com.vaadin.ui.Field;

class StatusFieldFactory extends DefaultFieldFactory{
	private static final long serialVersionUID = 1L;
	public final static String HANDLED = "status";
	@Override
	public Field createField(Item item, Object propertyId, Component uiContext) {
		ComboBox statuses = new ComboBox("Estado");
		statuses.setReadOnly(true);
		for(Status s : Status.values()){
			statuses.addItem(s);
		}
		return statuses;
	}

}
