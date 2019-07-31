package br.com.jkavdev.mysql_hibernate.modelos.h2;

import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum Official {

	T("true"), 
	F("false");

	private String description;

	private static Map<String, Official> continentMap;

	static {
		continentMap = Stream.of(values())
				.collect(Collectors.toMap(Official::name, Function.identity()));
	}

	private Official(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public static Official from(String name) {
		Objects.requireNonNull(name);
		Official continent = continentMap.get(name);
		if (continent == null)
			throw new IllegalArgumentException("Official " + name + " doesnt exist!");
		return continent;
	}

}
