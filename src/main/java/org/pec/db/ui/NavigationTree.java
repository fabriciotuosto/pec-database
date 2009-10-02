package org.pec.db.ui;

import java.util.concurrent.Callable;

import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.ui.Tree;

public class NavigationTree extends Tree {

	private static final long serialVersionUID = 1L;
	
	public final TreeItem showAll;
	public final TreeItem search;
	
	public NavigationTree(final PecApplication app) {
		showAll = new TreeItem("Mostrar todo",new Callable<Object>() {
			@Override
			public Object call() throws Exception {
				app.setMainComponent(app.getListView());
				return null;
			}
		});
		search = new TreeItem("Buscar",new Callable<Object>() {
			@Override
			public Object call() throws Exception {
				app.setMainComponent(app.getSearchView());
				return null;
			}
		});
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
				try {
					item.callable.call();
				} catch (Exception e) {
					throw new Error(e);
				}
				
			}
		});
	}
	
	private static class TreeItem{
		private final String label;
		private final Callable<Object> callable;

		public TreeItem(String label,Callable<Object> callable) {
			this.label = label;
			this.callable = callable;
		}
		@Override
		public String toString() {
			return label;
		}
	}
	
}
