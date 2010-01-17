package org.pec.db.guice;

import org.pec.db.ui.PecApplication;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;
import com.google.inject.servlet.ServletModule;
import com.google.inject.servlet.ServletScopes;
import com.vaadin.Application;

public class PecServlet extends GuiceServletContextListener{

	@Override
	protected Injector getInjector() {

		ServletModule module = new ServletModule() {
			@Override
			protected void configureServlets() {
				serve("/*").with(GuiceApplicationServlet.class);
				bind(Application.class).to(PecApplication.class).in(ServletScopes.SESSION);
			}
		};

		Injector injector = Guice.createInjector(module);

		return injector;
	}

}
