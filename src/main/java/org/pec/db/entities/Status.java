package org.pec.db.entities;

public enum Status {
	
	WORKING("Alta"),
	WAITING("Espera"),
	BAJA("Baja");
	
	private final String message;
	private Status(String message){
		this.message = message;
	}
	@Override
	public String toString() {
		return message;
	}
}
