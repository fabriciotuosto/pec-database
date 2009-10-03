package org.pec.db.ui;

import org.pec.db.ui.actions.Command;
import org.pec.db.ui.actions.ShowAllCommand;
import org.pec.db.ui.actions.ShowSearchCommand;

import com.google.inject.Inject;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.ui.Tree;

public class NavigationTree extends Tree {

	private static final long serialVersionUID = 1L;
	
	private final TreeItem showAll;
	private final TreeItem search;
	
	@Inject
	public NavigationTree(ShowAllCommand showAllCmd,
						  ShowSearchCommand showSearch) {
		showAll = new TreeItem("Mostrar todo",showAllCmd);
		search = new TreeItem("Buscar",showSearch);
		addItem(showAll);
		this.setChildrenAllowed(showAll, false);
		
		addItem(search);		
		/*
		* We want items to be selectable but do not want the user to be able to
		* de-select an item.
		*/
		setSelectable(true);
		// Make application handle item click events
		addListener(new ItemClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void itemClick(ItemClickEvent event) {
				TreeItem item = (TreeItem) event.getItemId();
				item.command.execute();
			}
		});
	}
	
	private static class TreeItem{
		private final String label;
		private final Command<Object> command;

		public TreeItem(String label,Command<Object> command) {
			this.label = label;
			this.command = command;
		}
		@Override
		public String toString() {
			return label;
		}
	}
	
}
