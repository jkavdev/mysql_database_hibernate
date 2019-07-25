package br.com.jkavdev.algaworks.jpaliveclass.modelos;

import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum Continent {
	
	ASIA("Asia"),
	EUROPE("Europe"),
	NORTH_AMERICA("North America"),
	AFRICA("Africa"),
	OCEANIA("Oceania"),
	ANTARCTICA("Antarctica"),
	SOUTH_AMERICA("South America");
	
	public static Continent defaultContinent = Continent.ASIA;
	
	private static Map<String, Continent> continentMap;
	
	static {
		 continentMap = Stream.of(values())
							.collect(Collectors.toMap(Continent::getName, Function.identity()));
	}
	
	private String name;

	private Continent(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

	public static Continent from(String name) {
		Objects.requireNonNull(name);
		Continent continent = continentMap.get(name);
		if(continent == null) throw new IllegalArgumentException("Continent " + name + " doesnt exist!");
		return continent;
	}

}
