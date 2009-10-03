package org.pec.db.ui;

import com.google.inject.Inject;
import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.NativeSelect;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;

public class SearchView extends Panel{

	private static final long serialVersionUID = 1L;

	private TextField tf;
	private NativeSelect fieldToSearch;
	private CheckBox saveSearch;
	private TextField searchName;
	
	@Inject
	public SearchView() {
		addStyleName("view");
		setCaption("Buscar personas");
		FormLayout layout = new FormLayout();
		setContent(layout);
		tf = new TextField("Valor de busqueda") ;
		fieldToSearch = new NativeSelect("Capo de busqueda");
		searchName = new TextField("Nombre de busqueda");
		saveSearch = new CheckBox("Guardar busqueda");
		Button search = new Button("Search");
		for(int i = 0; i < PersonList.NATURAL_COL_ORDER.length;i++){
			fieldToSearch.addItem(PersonList.NATURAL_COL_ORDER[i]);
			fieldToSearch.setItemCaption(
					PersonList.NATURAL_COL_ORDER[i],
					PersonList.COL_HEADERS[i]);
		}
		
		fieldToSearch.setValue(PersonList.NATURAL_COL_ORDER[0]);
		fieldToSearch.setNullSelectionAllowed(false);
		saveSearch.setValue(true);
		
		addComponent(tf);
		addComponent(fieldToSearch);
		addComponent(saveSearch);
		addComponent(searchName);
		addComponent(search);
	}
}
