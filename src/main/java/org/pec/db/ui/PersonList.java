package org.pec.db.ui;

import com.google.inject.Inject;
import com.google.inject.servlet.SessionScoped;
import com.vaadin.data.Item;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.ui.Table;

@SessionScoped
public class PersonList extends Table implements ValueChangeListener{

	public static final String[] NATURAL_COL_ORDER = new String[] {
		"firstName", "lastName", "birthDate","dni", "cuil","neighborhood","status" };
		public static final String[] COL_HEADERS = new String[] {
		"Nombre", "Apellido", "Fecha de nacimiento","DNI", "CUIL","Barrio","Estado"};
	private static final long serialVersionUID = 1L;
	
	@Inject @SuppressWarnings("serial")
	public PersonList(final PersonForm form,PersonContainer container) {
		setSizeFull();
		setSelectable(true);
		setImmediate(true);
		setContainerDataSource(container);
		setVisibleColumns(NATURAL_COL_ORDER);
		setColumnHeaders(COL_HEADERS);
		setColumnCollapsingAllowed(true);
		setColumnReorderingAllowed(true);
		addListener(new ItemClickListener() {
			@Override
			public void itemClick(ItemClickEvent event) {
				Item item = event.getItem();
				form.setItemDataSource(item);
			}
		});
	}
}
