package org.pec.db.ui.actions;

import org.pec.db.entities.SearchFilter;
import org.pec.db.ui.PersonContainer;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;

@Singleton
public class SearchCommand {

	private final ShowListViewCommand showListViewCmd;
	private final Provider<PersonContainer> container;

	@Inject
	public SearchCommand(Provider<PersonContainer> container,
			             ShowListViewCommand showListView) {
		this.container = container;
		this.showListViewCmd = showListView;
	}
	
	public void doSearch(SearchFilter search){
		container.get().addContainerFilter(
				search.getPropertyId(), search.getTerm(), false, false);
		showListViewCmd.execute();
	}
}
