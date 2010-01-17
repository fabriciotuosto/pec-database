package org.pec.db.ui.actions;

import org.pec.db.ui.PersonForm;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;

@Singleton
public class AddPersonCommand implements Command<Object>{
	
	private final Provider<PersonForm> personFormProvider;
	private ShowListViewCommand showAll;
	
	@Inject
	public AddPersonCommand(Provider<PersonForm> personFormProvider,ShowListViewCommand showAll) {
		this.showAll = showAll;
		this.personFormProvider = personFormProvider;
	}
	
	@Override
	public Object execute() {
		showAll.execute();
		personFormProvider.get().addPerson();
		return null;
	}

}
