package org.pec.db.entities;


public class Neighborhood {

	private String name;
	
	public Neighborhood() {
		super();
	}
	
	public Neighborhood(String name) {
		this();
		setName(name);
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return name;
	}

}
