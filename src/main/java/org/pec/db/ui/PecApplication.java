package org.pec.db.ui;


import com.google.inject.Inject;
import com.google.inject.servlet.SessionScoped;
import com.vaadin.Application;
import com.vaadin.ui.Component;
import com.vaadin.ui.ProgressIndicator;
import com.vaadin.ui.SplitPanel;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

@SessionScoped
public class PecApplication extends Application{

	private static final long serialVersionUID = 1L;
	private final ListView listView;
	private SplitPanel horizontalSplit;
	
	@Inject
	public PecApplication(NavigationTree tree,ToolBar toolbar,ListView listView) {
		this.listView = listView;
		Window window = new Window("PEC Database");
		setMainWindow(window);
		// 
		horizontalSplit = new SplitPanel(SplitPanel.ORIENTATION_HORIZONTAL);
		horizontalSplit.setSplitPosition(200, SplitPanel.UNITS_PIXELS);
		horizontalSplit.setFirstComponent(tree);
		VerticalLayout layout = new VerticalLayout();
		window.setContent(layout);
		layout.setSizeFull();
		layout.addComponent(toolbar);
		layout.addComponent(horizontalSplit);
		layout.setExpandRatio(horizontalSplit, 1);
		// reserve space for main menu
		setTheme("contacts");
		// Enable polling to display updated content
		ProgressIndicator poller = new ProgressIndicator();
		poller.addStyleName("invisible");
		poller.setPollingInterval(2000);
		window.addComponent(poller);
	}

	
	@Override
	public void init() {
		setMainComponent(listView);
	}
	
	public void setMainComponent(Component component){
		horizontalSplit.setSecondComponent(component);
	}
	
}
