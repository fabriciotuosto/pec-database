package org.pec.db.ui;

import com.vaadin.data.Item;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.ui.Table;

public class PersonList extends Table implements ValueChangeListener{

	public static final String[] NATURAL_COL_ORDER = new String[] {
		"firstName", "lastName", "dni", "cuil","status" };
		public static final String[] COL_HEADERS = new String[] {
		"Nombre", "Apellido", "DNI", "CUIL","Estado"};
	private static final long serialVersionUID = 1L;
	
	@SuppressWarnings("serial")
	public PersonList(PersonContainer dataSource,final PersonForm form) {
		setSizeFull();
		setSelectable(true);
		setImmediate(true);
		setContainerDataSource(dataSource);
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
