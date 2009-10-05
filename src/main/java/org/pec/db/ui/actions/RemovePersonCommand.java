package org.pec.db.ui.actions;

import org.pec.db.entities.Person;
import org.pec.db.ui.PersonContainer;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.servlet.SessionScoped;

@SessionScoped
public class RemovePersonCommand implements Command<Object>{
	
	private final Provider<PersonContainer> personContainer;
	private ShowListViewCommand showAll;
	private Person item;
	
	@Inject
	public RemovePersonCommand(Provider<PersonContainer> perconContainer,ShowListViewCommand showAll) {
		this.showAll = showAll;
		this.personContainer = perconContainer;
	}
	
	@Override
	public Object execute() {
		showAll.execute();
		personContainer.get().removeItem(item);
		return null;
	}
	
	public void selectedItem(Person item){
		this.item = item;
	}

}
