package org.pec.db.ui;

import org.pec.db.Repository;
import org.pec.db.entities.Neighborhood;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;

@Singleton
public class NeighborhoodContainer extends BeanItemContainer<Neighborhood> {

	private static final long serialVersionUID = 1L;
	private final Repository repository;

	@Inject
	public NeighborhoodContainer(Repository repository) {
		super(Neighborhood.class);
		this.repository = repository;
		
		for(Neighborhood p : repository.getAllByClass(Neighborhood.class)){
			addItem(p);
		}
	}

	@Override
	public BeanItem addItem(Object itemId) throws UnsupportedOperationException {
		repository.save(new Neighborhood(itemId.toString()),this);
		return super.addItem(itemId);
	}

	@Override
	public boolean removeItem(Object itemId)
			throws UnsupportedOperationException {
		repository.remove(new Neighborhood(itemId.toString()),this);
		return super.removeItem(itemId);
	}
	
	
	

}
