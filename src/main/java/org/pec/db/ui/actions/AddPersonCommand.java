package org.pec.db.ui.actions;

import org.pec.db.entities.Person;
import org.pec.db.ui.PersonForm;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;
import com.vaadin.data.util.BeanItem;

@Singleton
public class AddPersonCommand implements Command<Object>{
	
	private final Provider<PersonForm> personFormProvider;
	@Inject private Provider<ShowAllCommand> showAll;
	
	@Inject
	public AddPersonCommand(Provider<PersonForm> personFormProvider) {
		this.personFormProvider = personFormProvider;
	}
	
	@Override
	public Object execute() {
		showAll.get().execute();
		BeanItem person = new BeanItem(new Person());
		personFormProvider.get().setItemDataSource(person);
		return null;
	}

}
