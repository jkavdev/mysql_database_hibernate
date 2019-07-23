package br.com.jkavdev.algaworks.jpaliveclass.modelos;

public enum Continent {
	
	ASIA("Asia"),
	EUROPE("Europe"),
	NORTH_AMERICA("North America"),
	AFRICA("Africa"),
	OCEANIA("Oceania"),
	ANTARCTICA("Antarctica"),
	SOUTH_AMERICA("South America");
	
	private String name;

	private Continent(String name) {
		this.name = name;
	}

}
