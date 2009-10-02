package org.pec.db.ui;


import org.pec.db.entities.Person;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.servlet.SessionScoped;
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

@SessionScoped
public class PecApplication extends Application{

	private static final long serialVersionUID = 1L;
	private final Window window;
	private Button newPerson = new Button("Agregar Persona");
	private Button search    = new Button("Buscar");
	private Button share     = new Button("Enviar");
	private Button help      = new Button("Ayuda");
	private final NavigationTree tree;
	@Inject private Provider<ListView> listViewProvider;
	@Inject private Provider<HelpWindow> helpWindowProvider;
	@Inject private Provider<PersonForm> personFormProvider;
	private SplitPanel horizontalSplit = new SplitPanel(SplitPanel.ORIENTATION_HORIZONTAL);
	
	@Inject
	public PecApplication(NavigationTree tree) {
		this.tree = tree;
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
				personFormProvider.get().setItemDataSource(person);
			}
		});
		search.setIcon(new ThemeResource("icons/32/folder-add.png"));
		help.setIcon(new ThemeResource("icons/32/help.png"));
		help.addListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				getMainWindow().addWindow(helpWindowProvider.get());
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
		setMainComponent(listViewProvider.get());
	}
	
	public void setMainComponent(Component component){
		horizontalSplit.setSecondComponent(component);
	}
	
}
