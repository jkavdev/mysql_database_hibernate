package br.com.jkavdev.algaworks.jpaliveclass.modelos;

public enum Continent {
	
	ASIA("Asia"),
	EUROPE("Europe"),
	NORTH_AMERICA("North America"),
	AFRICA("Africa"),
	OCEANIA("Oceania"),
	ANTARCTICA("Antarctica"),
	SOUTH_AMERICA("South America");
	
	public static Continent defaultContinent = Continent.ASIA;
	
	private String name;

	private Continent(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

}
