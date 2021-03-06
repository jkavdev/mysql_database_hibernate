package br.com.jkavdev.mysql_hibernate.modelos.h2;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.apache.commons.csv.CSVRecord;

import com.mysql.cj.util.StringUtils;

import br.com.jkavdev.mysql_hibernate.modelos.h2.Country;
import br.com.jkavdev.mysql_hibernate.modelos.h2.CountryLanguagePK;
import br.com.jkavdev.mysql_hibernate.csv.CountryLanguageHeaders;
import br.com.jkavdev.mysql_hibernate.utils.NumberUtils;

@Entity
public class CountryLanguage {

	@EmbeddedId
	private CountryLanguagePK id;

	//caso uma busca pelo idioma
	//nao trara o objeto pais
	//mantera apenas um objeto proxiado
	//caso acesso ao objeto pais, ai necessario consulta 
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CountryCode", insertable = false, updatable = false, foreignKey = @ForeignKey(name = "countryLanguage_ibfk_1"))
	private Country country;

	@Enumerated(EnumType.STRING)
	@Column(name = "IsOfficial")
	private Official official = Official.F;

	@Column(name = "Percentage")
	private Float percentage = 0.0f;

	protected CountryLanguage() {

	}

	public CountryLanguage(Country country, String language) {
		id = new CountryLanguagePK(country.getCode(), language);
	}

	public static CountryLanguage notOfficial(Country country, String language) {
		CountryLanguage lang = new CountryLanguage(country, language);
		return lang;
	}
	
	public static CountryLanguage from(CSVRecord line) {
		if(line == null) return null;
		
		Country country = new Country(line.get(CountryLanguageHeaders.CountryCode));
		CountryLanguage language = new CountryLanguage(country, line.get(CountryLanguageHeaders.Language));
		language.setOfficial(Official.from(line.get(CountryLanguageHeaders.IsOfficial)));
		language.setPercentage(NumberUtils.getFloat(line.get(CountryLanguageHeaders.Percentage)));
		
		return language;
	}

	public static CountryLanguage official(Country country, String language) {
		CountryLanguage lang = new CountryLanguage(country, language);
		lang.setOfficial(Official.T);
		return lang;
	}

	public static boolean isNameLanguageNotValid(String language) {
		return StringUtils.isEmptyOrWhitespaceOnly(language);
	}

	public Country getCountry() {
		return country;
	}

	public Official getOfficial() {
		return official;
	}

	public void setOfficial(Official official) {
		this.official = official;
	}

	public Float getPercentage() {
		return percentage;
	}

	public void setPercentage(Float percentage) {
		this.percentage = percentage;
	}

	public String getLanguage() {
		return id.getLanguage();
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CountryLanguage other = (CountryLanguage) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(" [country=");
		builder.append(id.getCountry());
		builder.append(", language=");
		builder.append(id.getLanguage());
		builder.append(", official=");
		builder.append(official);
		builder.append(", percentage=");
		builder.append(percentage);
		builder.append("]");
		return builder.toString();
	}

}
