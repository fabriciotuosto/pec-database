package org.pec.db.ui.actions;

import org.pec.db.ui.ListView;
import org.pec.db.ui.PecApplication;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;

@Singleton
public class ShowAllCommand implements Command<Object>{
	
	private final Provider<PecApplication> appP;
	private final Provider<ListView> listViewP;
	@Inject
	public ShowAllCommand(Provider<PecApplication> app,Provider<ListView> listViewP) {
		this.appP = app;
		this.listViewP = listViewP;
	}
	
	@Override
	public Object execute() {
		appP.get().setMainComponent(listViewP.get());
		return null;
	}

}
