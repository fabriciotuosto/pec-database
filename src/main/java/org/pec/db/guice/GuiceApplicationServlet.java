package org.pec.db.guice;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;
import com.vaadin.Application;
import com.vaadin.terminal.gwt.server.AbstractApplicationServlet;

@Singleton
public class GuiceApplicationServlet extends AbstractApplicationServlet {

	private static final long serialVersionUID = 1L;

	protected final Provider<Application> applicationProvider;

	
	@Inject
	public GuiceApplicationServlet(Provider<Application> appProvider) {
		super();
		applicationProvider = appProvider;
	}

	@Override
	protected Class<?> getApplicationClass() throws ClassNotFoundException {
		return Application.class;
	}

	@Override
	protected Application getNewApplication(HttpServletRequest request)
			throws ServletException {
		return applicationProvider.get();
	}

}
