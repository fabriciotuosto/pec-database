package org.pec.db.ui.fields;

import org.pec.db.Repository;
import org.pec.db.entities.Neighborhood;

import com.google.inject.Inject;
import com.vaadin.data.Item;
import com.vaadin.ui.Component;
import com.vaadin.ui.DefaultFieldFactory;
import com.vaadin.ui.Field;
import com.vaadin.ui.Select;

class NeighborhoodFieldFactory extends DefaultFieldFactory{
	private static final long serialVersionUID = 1L;
	public final static String HANDLED = "neighborhood";
	private final Repository repo;
	
	@Inject
	public NeighborhoodFieldFactory(Repository repository) {
		this.repo = repository;
	}
	
	@Override @SuppressWarnings("serial")
	public Field createField(Item item, Object propertyId, Component uiContext) {
		Select neighborhoods = new Select("Barrio",repo.getAllByClass(Neighborhood.class)){
			@Override
			public Item addItem(Object itemId)
					throws UnsupportedOperationException {
				repo.save(new Neighborhood(itemId.toString()));
				return super.addItem(itemId);
			}		
		};
		neighborhoods.setWidth("20%");
		neighborhoods.setNewItemsAllowed(true);
		neighborhoods.setRequired(true);
		return neighborhoods;
	}
}
