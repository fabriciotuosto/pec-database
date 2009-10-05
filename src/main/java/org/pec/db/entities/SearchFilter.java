package org.pec.db.entities;

public class SearchFilter {


	private final String term;
	private final Object propertyId;
	private String searchName;

	public SearchFilter(Object propertyId, String searchTerm, String name) {
		this.propertyId = propertyId;
		this.term = searchTerm;
		this.searchName = name;
	}

	public String getTerm() {
		return term;
	}
	
	public Object getPropertyId() {
		return propertyId;
	}

	public String getSearchName() {
		return searchName;
	}

	public void setSearchName(String searchName) {
		this.searchName = searchName;
	}
}