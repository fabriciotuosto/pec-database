package org.pec.db;

public interface RepositoryListener {

	public void saveNotification(Object element,Object caller);
	public void removeNotification(Object element,Object caller);
}
