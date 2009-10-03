package org.pec.db.ui;


import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.servlet.SessionScoped;
import com.vaadin.Application;
import com.vaadin.ui.Component;
import com.vaadin.ui.SplitPanel;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

@SessionScoped
public class PecApplication extends Application{

	private static final long serialVersionUID = 1L;
	@Inject private Provider<ListView> listViewProvider;
	private SplitPanel horizontalSplit = new SplitPanel(SplitPanel.ORIENTATION_HORIZONTAL);
	
	@Inject
	public PecApplication(NavigationTree tree,ToolBar toolbar) {
		setTheme("contacts");
		Window window = new Window("PEC Database");
		VerticalLayout layout = new VerticalLayout();
		setMainWindow(window);
		layout.setSizeFull();
		// 
		layout.addComponent(toolbar);
		layout.addComponent(horizontalSplit);
		layout.setExpandRatio(horizontalSplit, 1);
		// reserve space for main menu
		horizontalSplit.setSplitPosition(200, SplitPanel.UNITS_PIXELS);
		horizontalSplit.setFirstComponent(tree);
		window.setContent(layout);
	}

	
	@Override
	public void init() {
		setMainComponent(listViewProvider.get());
	}
	
	public void setMainComponent(Component component){
		horizontalSplit.setSecondComponent(component);
	}
	
}
