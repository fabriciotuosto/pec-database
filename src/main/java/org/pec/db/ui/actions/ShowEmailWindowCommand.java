package org.pec.db.ui.actions;

import org.pec.db.ui.PecApplication;
import org.pec.db.ui.SendEmailWindow;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;

@Singleton
public class ShowEmailWindowCommand implements Command<Object>{

	private final Provider<PecApplication> appP;
	private final Provider<SendEmailWindow> emailWindowP;
	@Inject
	public ShowEmailWindowCommand(Provider<PecApplication> app,Provider<SendEmailWindow> emailWindowP) {
		this.appP = app;
		this.emailWindowP = emailWindowP;
	}
	@Override
	public Object execute() {
		appP.get().getMainWindow().addWindow(emailWindowP.get());
		emailWindowP.get().setVisible(true);
		return null;
	}

}
