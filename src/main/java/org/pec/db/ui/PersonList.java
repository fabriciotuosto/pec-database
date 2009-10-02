package org.pec.db.ui;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.servlet.SessionScoped;
import com.vaadin.data.Item;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.ui.Table;

@SessionScoped
public class PersonList extends Table implements ValueChangeListener{

	public static final String[] NATURAL_COL_ORDER = new String[] {
		"firstName", "lastName", "dni", "cuil","status" };
		public static final String[] COL_HEADERS = new String[] {
		"Nombre", "Apellido", "DNI", "CUIL","Estado"};
	private static final long serialVersionUID = 1L;
	
	@SuppressWarnings("serial")
	@Inject
	public PersonList(final Provider<PersonForm> formP) {
		setSizeFull();
		setSelectable(true);
		setImmediate(true);
		setContainerDataSource(PersonContainer.createWithTestData());
		setVisibleColumns(NATURAL_COL_ORDER);
		setColumnHeaders(COL_HEADERS);
		setColumnCollapsingAllowed(true);
		setColumnReorderingAllowed(true);
		addListener(new ItemClickListener() {
			@Override
			public void itemClick(ItemClickEvent event) {
				Item item = event.getItem();
				formP.get().setItemDataSource(item);
			}
		});
	}
}
