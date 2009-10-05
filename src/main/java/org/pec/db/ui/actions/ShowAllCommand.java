package org.pec.db.ui.actions;

import org.pec.db.ui.PersonContainer;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;

@Singleton
public class ShowAllCommand implements Command<Object>{
	
	private final Provider<PersonContainer> personP;
	private final ShowListViewCommand command;
	@Inject
	public ShowAllCommand(Provider<PersonContainer> personP,ShowListViewCommand command) {
		this.personP = personP;
		this.command = command;
	}
	
	@Override
	public Object execute() {
		personP.get().removeAllContainerFilters();
		command.execute();
		return null;
	}

}
