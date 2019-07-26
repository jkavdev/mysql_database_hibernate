package br.com.jkavdev.mysql_hibernate.modelos;

public enum Official {
	
	T("true"),
	F("false");
	
	private String description;

	private Official(String description) {
		this.description = description;
	}
	
	public String getDescription() {
		return description;
	}

}
