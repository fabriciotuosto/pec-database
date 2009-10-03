package org.pec.db.ui.fields;

import org.pec.db.entities.Neighborhood;

import com.vaadin.data.Item;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.DefaultFieldFactory;
import com.vaadin.ui.Field;

class NeighborhoodFieldFactory extends DefaultFieldFactory{
	private static final long serialVersionUID = 1L;
	public final static String HANDLED = "neighborhood";
	@Override
	public Field createField(Item item, Object propertyId, Component uiContext) {
		ComboBox cities = new ComboBox("Barrio");
		cities.setNewItemsAllowed(true);
		cities.setRequired(true);
		Neighborhood laLucial = new Neighborhood();
		laLucial.setName("La Lucila");
		cities.addItem(laLucial);
		return cities;
	}
}
