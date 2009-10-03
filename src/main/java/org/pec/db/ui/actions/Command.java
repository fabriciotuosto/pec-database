package org.pec.db.ui.actions;

public interface Command<R> {

	public R execute();
}
