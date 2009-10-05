package org.pec.db.ui.actions;

import org.pec.db.ui.PecApplication;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;
import com.vaadin.ui.Window.Notification;

@Singleton
public class ShowNotification {

	private final Provider<PecApplication> app;

	@Inject
	public ShowNotification(Provider<PecApplication> app) {
		this.app = app;
	}
	
	public void show(String notification){
		app.get().getMainWindow().showNotification(notification,Notification.TYPE_TRAY_NOTIFICATION);
	}
}
