package org.pec.db.ui;

import org.pec.db.ui.actions.AddPersonCommand;
import org.pec.db.ui.actions.RemovePersonCommand;
import org.pec.db.ui.actions.ShowEmailWindowCommand;
import org.pec.db.ui.actions.ShowHelpWindowCommand;
import org.pec.db.ui.actions.ShowSearchCommand;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.servlet.SessionScoped;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

@SessionScoped
public class ToolBar extends HorizontalLayout{

	private static final long serialVersionUID = 1L;
	private Button newPerson = new Button("Agregar Persona");
	private Button rmPerson = new Button("Eliminar Persona");
	private Button search    = new Button("Buscar");
	private Button share     = new Button("Enviar");
	private Button help      = new Button("Ayuda");
	private final Provider<AddPersonCommand> addPersonCmdProvider;
	private final Provider<ShowHelpWindowCommand> helpWindowProvider;
	private final Provider<ShowSearchCommand> showSearchCommand;
	private final Provider<ShowEmailWindowCommand> showEmailCommand;
	private final Provider<RemovePersonCommand> rmCmd;
	
	@Inject
	public ToolBar(Provider<AddPersonCommand> addCmd,
			       Provider<ShowHelpWindowCommand> showHelpCmd,
			       Provider<ShowSearchCommand> showSearchCmd,
			       Provider<ShowEmailWindowCommand> genExcelCmd,
			       Provider<RemovePersonCommand> rmCmd) {
		this.addPersonCmdProvider = addCmd;
		this.helpWindowProvider = showHelpCmd;
		this.showSearchCommand = showSearchCmd;
		this.showEmailCommand = genExcelCmd;
		this.rmCmd = rmCmd;
		addComponent(newPerson);
		addComponent(rmPerson);
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
		rmPerson.setIcon(new ThemeResource("icons/32/document-delete.png"));
		search.setIcon(new ThemeResource("icons/32/folder-add.png"));
		help.setIcon(new ThemeResource("icons/32/help.png"));
		share.setIcon(new ThemeResource("icons/32/email.png"));
		
		newPerson.addListener(new ClickListener(){
			@Override
			public void buttonClick(ClickEvent event) {
				addPersonCmdProvider.get().execute();
			}
		});
		
		rmPerson.addListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				rmCmd.get().execute();
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
		
		share.addListener(new ClickListener() {		
			@Override
			public void buttonClick(ClickEvent event) {
				showEmailCommand.get().execute();
			}
		});
	}
}
