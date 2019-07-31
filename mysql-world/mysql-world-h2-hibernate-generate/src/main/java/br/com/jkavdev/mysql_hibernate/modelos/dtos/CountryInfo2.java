package br.com.jkavdev.mysql_hibernate.modelos.dtos;

import java.util.Set;

public class CountryInfo2 {
	
	private String Code;
	
	private String name;
	
	private Set<String> languages;
	
	private Set<String> cities;

	public CountryInfo2(String code, String name, Set<String> languages, Set<String> cities) {
		Code = code;
		this.name = name;
		this.languages = languages;
		this.cities = cities;
	}

	public String getCode() {
		return Code;
	}

	public void setCode(String code) {
		Code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<String> getLanguages() {
		return languages;
	}

	public void setLanguages(Set<String> languages) {
		this.languages = languages;
	}

	public Set<String> getCities() {
		return cities;
	}

	public void setCities(Set<String> cities) {
		this.cities = cities;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("[Code=");
		builder.append(Code);
		builder.append(", name=");
		builder.append(name);
		builder.append(", \n\tlanguages=");
		builder.append(languages);
		builder.append(", \n\tcities=");
		builder.append(cities);
		builder.append("]");
		return builder.toString();
	}
	
}
