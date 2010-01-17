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
	
	@Inject
	public PersonContainer(Repository repository) {
		super(Person.class);
		repository.addListener(new PersonRepositoryListener(this));
		for(Person p : repository.getAllByClass(Person.class)){
			addItem(p);
		}
	}

	@Override
	public BeanItem addItem(Object itemId) throws UnsupportedOperationException {
		return super.addItem(itemId);
	}
	
	@Override
	public boolean removeItem(Object itemId)
			throws UnsupportedOperationException {
		return super.removeItem(itemId);
	}

	private static class PersonRepositoryListener implements RepositoryListener{
		
		private final PersonContainer p;
		
		public PersonRepositoryListener(PersonContainer p) {
			this.p = p;
		}
		
		@Override
		public void removeNotification(Object element) {
			if(element instanceof Person){
				p.removeItem(element);
			}
		}

		@Override
		public void saveNotification(Object element) {
			if(element instanceof Person){
				p.addItem(element);
			}
		}
	}
}
