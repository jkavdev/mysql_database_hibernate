package br.com.jkavdev.mysql.world.language;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class CountryLanguagePK implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "CountryCode", columnDefinition = "char(3) default ''", nullable = true)
	private String country;

	@Column(name = "Language", columnDefinition = "char(30)", nullable = false)
	private String language;

	protected CountryLanguagePK() {
	}

	public CountryLanguagePK(String country, String language) {
		this.country = country;
		this.language = language;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	@Override
	public int hashCode() {
		return Objects.hash(country, language);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CountryLanguagePK other = (CountryLanguagePK) obj;
		return Objects.equals(country, other.country) && Objects.equals(language, other.language);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("[country=");
		builder.append(country);
		builder.append(", language=");
		builder.append(language);
		builder.append("]");
		return builder.toString();
	}

}
