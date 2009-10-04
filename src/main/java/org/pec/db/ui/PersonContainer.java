package org.pec.db.ui;

import org.pec.db.Repository;
import org.pec.db.RepositoryListener;
import org.pec.db.entities.Person;

import com.google.inject.Inject;
import com.google.inject.servlet.SessionScoped;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;

@SessionScoped
public class PersonContainer extends BeanItemContainer<Person> {

	private static final long serialVersionUID = 1L;
	private final Repository repository;
	
	@Inject
	public PersonContainer(Repository repository) {
		super(Person.class);
		this.repository = repository;
		repository.addListener(new PersonRepositoryListener(this));
		for(Person p : repository.getAllByClass(Person.class)){
			addItem(p);
		}
	}

	@Override
	public BeanItem addItem(Object itemId) throws UnsupportedOperationException {
		repository.save(itemId,this);
		return super.addItem(itemId);
	}
	
	private BeanItem _addItem(Object itemId) throws UnsupportedOperationException {
		int index = indexOfId(itemId);
		super.removeItem(itemId);
		return super.addItemAt(index, itemId);
	}

	@Override
	public boolean removeItem(Object itemId)
			throws UnsupportedOperationException {
		repository.remove(itemId,this);
		return super.removeItem(itemId);
	}

	private static class PersonRepositoryListener implements RepositoryListener{
		
		private final PersonContainer p;
		
		public PersonRepositoryListener(PersonContainer p) {
			this.p = p;
		}
		
		@Override
		public void removeNotification(Object element, Object caller) {
			if(p != caller && element instanceof Person){
				p.removeItem(element);
			}
		}

		@Override
		public void saveNotification(Object element, Object caller) {
			if(p != caller && element instanceof Person){
				p._addItem(element);
			}
		}
	}
}
