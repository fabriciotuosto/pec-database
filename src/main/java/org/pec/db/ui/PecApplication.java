package org.pec.db.ui;


import org.pec.db.entities.Person;

import com.vaadin.Application;
import com.vaadin.data.util.BeanItem;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.SplitPanel;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

public class PecApplication extends Application{

	private static final long serialVersionUID = 1L;
	private final Window window;
	private Button newPerson = new Button("Agregar Persona");
	private Button search    = new Button("Buscar");
	private Button share     = new Button("Enviar");
	private Button help      = new Button("Ayuda");
	private NavigationTree tree;
	private volatile ListView listView = null;
	private volatile SearchView searchView = null;
	private volatile HelpWindow helpWindow = null;
	private PersonList personList = null;
	private PersonForm personForm = null;
	private SplitPanel horizontalSplit = new SplitPanel(SplitPanel.ORIENTATION_HORIZONTAL);
	private PersonContainer dataSource = PersonContainer.createWithTestData();
	
	public PecApplication() {
		setTheme("contacts");
		customizeButtons();
		window = new Window("PEC Database");
		setMainWindow(window);
		VerticalLayout layout = new VerticalLayout();
		layout.setSizeFull();
		// 
		layout.addComponent(createToolbar(newPerson,search,share,help));
		layout.addComponent(horizontalSplit);
		layout.setExpandRatio(horizontalSplit, 1);
		// reserve space for main menu
		horizontalSplit.setSplitPosition(200, SplitPanel.UNITS_PIXELS);
		tree = new NavigationTree(this);
		horizontalSplit.setFirstComponent(tree);
		window.setContent(layout);
	}

	
	@SuppressWarnings("serial")
	private void customizeButtons() {
		newPerson.setIcon(new ThemeResource("icons/32/document-add.png"));
		newPerson.addListener(new ClickListener(){
			@Override
			public void buttonClick(ClickEvent event) {
				BeanItem person = new BeanItem(new Person());
				personForm.setItemDataSource(person);
			}
		});
		search.setIcon(new ThemeResource("icons/32/folder-add.png"));
		help.setIcon(new ThemeResource("icons/32/help.png"));
		help.addListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				getMainWindow().addWindow(getHelpWindow());
			}
		});
		share.setIcon(new ThemeResource("icons/32/users.png"));
	}

	private Component createToolbar(Component...components) {
		HorizontalLayout layout = new HorizontalLayout();
		for(Component c : components){
			layout.addComponent(c);
		}
		layout.setWidth("100%");
		Embedded em = new Embedded("", new ThemeResource("images/logo.png"));
		layout.addComponent(em);
		layout.setComponentAlignment(em, Alignment.MIDDLE_RIGHT);
		layout.setExpandRatio(em, 1);
		layout.setStyleName("toolbar");
		return layout;
	}
	
	@Override
	public void init() {
		setMainWindow(window);
		setMainComponent(getListView());
	}
	
	public void setMainComponent(Component component){
		horizontalSplit.setSecondComponent(component);
	}

	public PersonContainer getDataSource() {
		return dataSource;
	}
	// Correct lazy load of listview
	public ListView getListView() {
		ListView local = listView;
		if (local == null){
			synchronized (this) {
				local = listView;
				if (local == null){
					personForm = new PersonForm();
					personList = new PersonList(getDataSource(),personForm);
					listView = local = new ListView(personList,personForm);
				}
			}
		}
		return local;
	}
	
	// Correct lazy load of searchView
	public SearchView getSearchView() {
		SearchView local = searchView;
		if (local == null){
			synchronized (this) {
				local = searchView;
				if (local == null){
					searchView = local = new SearchView(this);
				}
			}
		}
		return local;
	}
	
	public Window getHelpWindow() {
		HelpWindow local = helpWindow;
		if (local == null){
			synchronized (this) {
				local = helpWindow;
				if (local == null){
					helpWindow = local = new HelpWindow();
				}
			}
		}
		return local;
	}
		
}
