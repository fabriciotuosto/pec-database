package org.pec.db.ui.actions;

import org.pec.db.ui.PecApplication;
import org.pec.db.ui.SearchView;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;

@Singleton
public class ShowSearchCommand implements Command<Object>{

	
	private final Provider<PecApplication> appP;
	private final Provider<SearchView> searchViewP;
	
	@Inject
	public ShowSearchCommand(Provider<PecApplication> app,Provider<SearchView> searchViewP) {
		this.appP = app;
		this.searchViewP = searchViewP;
	}
	@Override
	public Object execute() {
		appP.get().setMainComponent(searchViewP.get());
		return null;
	}

}
