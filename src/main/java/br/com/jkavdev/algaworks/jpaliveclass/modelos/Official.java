package br.com.jkavdev.algaworks.jpaliveclass.modelos;

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
