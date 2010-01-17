package org.pec.db.ui;

import org.pec.db.Repository;
import org.pec.db.entities.SearchFilter;
import org.pec.db.ui.actions.SearchCommand;

import com.google.inject.Inject;
import com.google.inject.servlet.SessionScoped;
import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.NativeSelect;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

@SessionScoped
public class SearchView extends Panel{

	private static final long serialVersionUID = 1L;

	private TextField tf;
	private NativeSelect fieldToSearch;
	private CheckBox saveSearch;
	private TextField searchName;
	private Button search; 
	private final Repository repo;
	private final SearchCommand searchCmd;
	@Inject
	public SearchView(Repository repo,SearchCommand searchCmd) {
		this.repo = repo;
		this.searchCmd = searchCmd;
		addStyleName("view");
		setCaption("Buscar personas");
		FormLayout layout = new FormLayout();
		setContent(layout);
		tf = new TextField("Valor de busqueda") ;
		fieldToSearch = new NativeSelect("Capo de busqueda");
		searchName = new TextField("Nombre de busqueda");
		saveSearch = new CheckBox("Guardar busqueda");
		search = new Button("Buscar");
		addAction();
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
	
	
	@SuppressWarnings("serial")
	private void addAction() {
		search.addListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				SearchFilter filter = new SearchFilter(fieldToSearch.getValue().toString(),
													   tf.getValue().toString(),
													   searchName.getValue().toString());
				if (saveSearch.booleanValue()){
					repo.save(filter);
				}
				searchCmd.doSearch(filter);
			}
		});
	}
}
