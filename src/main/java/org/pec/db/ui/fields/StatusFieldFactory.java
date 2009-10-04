package org.pec.db.ui.fields;

import org.pec.db.Repository;
import org.pec.db.entities.Status;

import com.google.inject.Inject;
import com.vaadin.data.Item;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.DefaultFieldFactory;
import com.vaadin.ui.Field;

class StatusFieldFactory extends DefaultFieldFactory{
	private static final long serialVersionUID = 1L;
	public final static String HANDLED = "status";
	private final Repository repo;
	
	@Inject
	public StatusFieldFactory(Repository repository) {
		this.repo = repository;
	}
	

	@Override
	@SuppressWarnings("serial")
	public Field createField(Item item, Object propertyId, Component uiContext) {
		ComboBox statuses = new ComboBox("Estado",repo.getAllByClass(Status.class)){
			@Override
			public Item addItem(Object itemId)
					throws UnsupportedOperationException {
				repo.save(new Status(itemId.toString()),this);
				return super.addItem(itemId);
			}		
		};
		statuses.setNewItemsAllowed(true);
		return statuses;
	}

}
