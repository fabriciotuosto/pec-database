package org.pec.db.ui;

import java.text.SimpleDateFormat;

import org.pec.db.entities.Person;

import com.google.inject.Inject;
import com.google.inject.servlet.SessionScoped;
import com.vaadin.data.Item;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.Label;
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
		
		addGeneratedColumn("birthDate", new ColumnGenerator() {
			@Override
			public Component generateCell(Table source, Object itemId, Object columnId) {
				Person p = (Person)itemId;
				Label date = new Label(new SimpleDateFormat("dd/MM/yyyy").format(p.getBirthDate()));
				return date;
			}
		});
		addListener(new ItemClickListener() {
			@Override
			public void itemClick(ItemClickEvent event) {
				Item item = event.getItem();
				form.setItemDataSource(item);
			}
		});
	}
}
