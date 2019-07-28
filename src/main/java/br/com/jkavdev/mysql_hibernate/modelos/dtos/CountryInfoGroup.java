package br.com.jkavdev.mysql_hibernate.modelos.dtos;

import java.util.ArrayList;
import java.util.List;

public class CountryInfoGroup {

	private String Code;

	private String name;

	private List<String> languages = new ArrayList<>();

	private List<String> cities = new ArrayList<>();

	public CountryInfoGroup(String code, String name, List<String> languages, List<String> cities) {
		Code = code;
		this.name = name;
		this.languages = languages;
		this.cities = cities;
	}
	
	public static CountryInfoGroup empty() {
		return new CountryInfoGroup(null, null, new ArrayList<>(), new ArrayList<>());
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

	public List<String> getLanguages() {
		return languages;
	}

	public void setLanguages(List<String> languages) {
		this.languages = languages;
	}

	public List<String> getCities() {
		return cities;
	}

	public void setCities(List<String> cities) {
		this.cities = cities;
	}
	
	public void addCity(String city) {
		cities.add(city);
	}
	
	public void addLanguage(String language) {
		languages.add(language);
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
