package org.pec.db.ui;

import org.pec.db.ui.actions.AddPersonCommand;
import org.pec.db.ui.actions.ShowHelpWindowCommand;
import org.pec.db.ui.actions.ShowSearchCommand;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

@Singleton
public class ToolBar extends HorizontalLayout{

	private static final long serialVersionUID = 1L;
	private Button newPerson = new Button("Agregar Persona");
	private Button search    = new Button("Buscar");
	private Button share     = new Button("Enviar");
	private Button help      = new Button("Ayuda");
	@Inject private Provider<AddPersonCommand> addPersonCmdProvider;
	@Inject private Provider<ShowHelpWindowCommand> helpWindowProvider;
	@Inject private Provider<ShowSearchCommand> showSearchCommand;
	
	@Inject
	public ToolBar() {
		addComponent(newPerson);
		addComponent(search);
		addComponent(share);
		addComponent(help);
		setWidth("100%");
		setStyleName("toolbar");
		setComponentAlignment(help, Alignment.MIDDLE_RIGHT);
		setExpandRatio(help, 1);
		customizeButtons();
	}
	
	@SuppressWarnings("serial")
	private void customizeButtons() {
		newPerson.setIcon(new ThemeResource("icons/32/document-add.png"));
		search.setIcon(new ThemeResource("icons/32/folder-add.png"));
		help.setIcon(new ThemeResource("icons/32/help.png"));
		share.setIcon(new ThemeResource("icons/32/users.png"));
		
		newPerson.addListener(new ClickListener(){
			@Override
			public void buttonClick(ClickEvent event) {
				addPersonCmdProvider.get().execute();
			}
		});
		
		search.addListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				showSearchCommand.get().execute();
			}
		});
		
		help.addListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				helpWindowProvider.get().execute();
			}
		});
	}
}
