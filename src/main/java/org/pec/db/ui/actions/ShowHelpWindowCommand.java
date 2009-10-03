package org.pec.db.ui.actions;

import org.pec.db.ui.HelpWindow;
import org.pec.db.ui.PecApplication;

import com.google.inject.Inject;
import com.google.inject.Provider;

public class ShowHelpWindowCommand implements Command<Object>{
	
	private final Provider<PecApplication> appP;
	private final Provider<HelpWindow> helpWindowP;
	
	@Inject
	public ShowHelpWindowCommand(Provider<PecApplication> app,Provider<HelpWindow> helpWindowP) {
		this.appP = app;
		this.helpWindowP = helpWindowP;
	}
	@Override
	public Object execute() {
		appP.get().getMainWindow().addWindow(helpWindowP.get());
		return null;
	}
}
