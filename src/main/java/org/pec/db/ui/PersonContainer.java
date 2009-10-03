package org.pec.db.ui;

import org.pec.db.Repository;
import org.pec.db.entities.Person;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;

@Singleton
public class PersonContainer extends BeanItemContainer<Person> {

	private static final long serialVersionUID = 1L;
	private final Repository repository;
	
	@Inject
	public PersonContainer(Repository repository) {
		super(Person.class);
		this.repository = repository;
		
		for(Person p : repository.getAllByClass(Person.class)){
			addItem(p);
		}
	}

	@Override
	public BeanItem addItem(Object itemId) throws UnsupportedOperationException {
		repository.save(itemId);
		return super.addItem(itemId);
	}

	@Override
	public boolean removeItem(Object itemId)
			throws UnsupportedOperationException {
		repository.remove(itemId);
		return super.removeItem(itemId);
	}
}
