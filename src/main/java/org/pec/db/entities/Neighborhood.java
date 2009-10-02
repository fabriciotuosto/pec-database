package org.pec.db.entities;

import java.util.List;

public class Neighborhood {

	private String name;
	private List<Person> integrant;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Person> getIntegrant() {
		return integrant;
	}

	public void setIntegrant(List<Person> integrant) {
		this.integrant = integrant;
	}

}
