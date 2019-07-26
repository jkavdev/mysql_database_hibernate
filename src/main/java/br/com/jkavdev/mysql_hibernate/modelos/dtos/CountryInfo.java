package br.com.jkavdev.mysql_hibernate.modelos.dtos;

public class CountryInfo {

	private String Code;

	private String name;

	private String languages;

	private String cities;

	public CountryInfo(String code, String name, String languages, String cities) {
		this.Code = code;
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

	public String getLanguages() {
		return languages;
	}

	public void setLanguages(String languages) {
		this.languages = languages;
	}

	public String getCities() {
		return cities;
	}

	public void setCities(String cities) {
		this.cities = cities;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("[Code=");
		builder.append(Code);
		builder.append(", name=");
		builder.append(name);
		builder.append(", languages=");
		builder.append(languages);
		builder.append(", cities=");
//		builder.append(", \n\tlanguages=");
//		builder.append(languages);
//		builder.append(", \n\tcities=");
		builder.append(cities);
		builder.append("]");
		return builder.toString();
	}

}
