package org.pec.db.ui.fields;

import org.pec.db.entities.Neighborhood;
import org.pec.db.ui.NeighborhoodContainer;

import com.google.inject.Inject;
import com.vaadin.data.Item;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.DefaultFieldFactory;
import com.vaadin.ui.Field;

class NeighborhoodFieldFactory extends DefaultFieldFactory{
	private static final long serialVersionUID = 1L;
	public final static String HANDLED = "neighborhood";
	private final NeighborhoodContainer container;
	
	@Inject
	public NeighborhoodFieldFactory(NeighborhoodContainer container) {
		this.container = container;
	}
	
	@Override @SuppressWarnings("serial")
	public Field createField(Item item, Object propertyId, Component uiContext) {
		ComboBox neighborhoods = new ComboBox("Barrio",container){
			@Override
			public Item addItem(Object itemId)
					throws UnsupportedOperationException {
				container.addItem(new Neighborhood(itemId.toString()));
				return super.addItem(itemId);
			}		
		};
		neighborhoods.setNewItemsAllowed(true);
		neighborhoods.setRequired(true);
		return neighborhoods;
	}
}
