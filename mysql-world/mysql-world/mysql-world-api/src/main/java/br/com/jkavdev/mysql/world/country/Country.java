package br.com.jkavdev.mysql.world.country;

import br.com.jkavdev.mysql.world.builders.CountryBuilder;
import br.com.jkavdev.mysql.world.csv.CountryHeaders;
import br.com.jkavdev.mysql.world.language.CountryLanguage;
import br.com.jkavdev.mysql.world.utils.NumberUtils;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.apache.commons.csv.CSVRecord;

import org.apache.commons.lang3.StringUtils;

@Entity
public class Country {

	@Id
	@Column(name = "Code", columnDefinition = "char(3) DEFAULT ''", length = 3, nullable = false)
	private String code = "";

	@Column(name = "Name", columnDefinition = "char(52) DEFAULT ''", length = 52, nullable = false)
	private String name = "";

	@Column(name = "Continent", nullable = false, 
		columnDefinition = "enum('Asia','Europe','North America','Africa','Oceania','Antarctica','South America')  DEFAULT 'Asia'")
	private Continent continent = Continent.defaultContinent;

	@Column(name = "Region", columnDefinition = "char(26) DEFAULT ''", length = 26, nullable = false)
	private String region = "";

	@Column(name = "SurfaceArea", columnDefinition = "float(10,2)  DEFAULT 0.00", nullable = false, precision = 10, scale = 2)
	private Float surfaceArea = 0.0f;

	@Column(name = "IndepYear", columnDefinition = "smallint(6)")
	private Integer independeceYear;

	@Column(name = "Population", columnDefinition = "int(11) DEFAULT 0", nullable = false)
	private Integer population = 0;

	@Column(name = "LifeExpectancy", columnDefinition = "float(3,1)", precision = 3, scale = 1)
	private Float lifeExpectancy;

	@Column(name = "GNP", columnDefinition = "float(10,2)", precision = 10, scale = 2)
	private Float gnp;

	@Column(name = "GNPOld", columnDefinition = "float(10,2)", precision = 10, scale = 2)
	private Float gnpOld;

	@Column(name = "LocalName", columnDefinition = "char(45) DEFAULT ''", length = 45, nullable = false)
	private String localName = "";

	@Column(name = "GovernmentForm", columnDefinition = "char(45) DEFAULT ''", length = 45, nullable = false)
	private String governmentForm = "";

	@Column(name = "HeadOfState", columnDefinition = "char(60)", length = 60)
	private String headOfState;

	@Column(name = "Capital")
	private Integer capital;

	@Column(name = "Code2", columnDefinition = "char(2) DEFAULT ''", length = 2, nullable = false)
	private String code2 = "";
	
	@OneToMany(mappedBy = "country", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
	private Set<CountryLanguage> languages = new HashSet<>();
	
	protected Country() {}

	public Country(String code, String name, Continent continent) {
		this(code);
		this.name = name;
		this.continent = continent;
	}
	
	public Country(String code) {
		if(StringUtils.isNotBlank(code)) throw new IllegalArgumentException("Country invalid!");
		//como usar o builder aqui?
		this.code = code;
		this.setName(null);
		this.setContinent(null);
		this.setRegion(null);
		this.setSurfaceArea(null);
		this.setIndependeceYear(null);
		this.setPopulation(null);
		this.setLifeExpectancy(null);
		this.setGnp(null);
		this.setGnpOld(null);
		this.setLocalName(null);
		this.setGovernmentForm(null);
		this.setHeadOfState(null);
		this.setCapital(null);
		this.setCode2(null);
	}

	public static Country from(CSVRecord line) {
		if(line == null) return null;
		
		CountryBuilder builder = new CountryBuilder(line.get(CountryHeaders.Code), line.get(CountryHeaders.Name), Continent.from(line.get(CountryHeaders.Continent)));
		builder
			.region(line.get(CountryHeaders.Region))
			.surfaceArea(NumberUtils.getFloat(line.get(CountryHeaders.SurfaceArea)))
			.independeceYear(NumberUtils.getInteger(line.get(CountryHeaders.IndepYear)))
			.population(NumberUtils.getInteger(line.get(CountryHeaders.Population)))
			.lifeExpectancy(NumberUtils.getFloat(line.get(CountryHeaders.LifeExpectancy)))
			.gnp(NumberUtils.getFloat(line.get(CountryHeaders.GNP)))
			.gnpOld(NumberUtils.getFloat(line.get(CountryHeaders.GNPOld)))
			.localName(line.get(CountryHeaders.LocalName))
			.governmentForm(line.get(CountryHeaders.GovernmentForm))
			.headOfState(line.get(CountryHeaders.HeadOfState))
			.capital(NumberUtils.getInteger(line.get(CountryHeaders.Capital)))
			.code2(line.get(CountryHeaders.Code2));
		
		return builder.get();
	}
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Continent getContinent() {
		return continent;
	}

	public void setContinent(Continent continent) {
		this.continent = continent;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public Float getSurfaceArea() {
		return surfaceArea;
	}

	public void setSurfaceArea(Float surfaceArea) {
		this.surfaceArea = surfaceArea;
	}

	public Integer getIndependeceYear() {
		return independeceYear;
	}

	public void setIndependeceYear(Integer independeceYear) {
		this.independeceYear = independeceYear;
	}

	public Integer getPopulation() {
		return population;
	}

	public void setPopulation(Integer population) {
		this.population = population;
	}

	public Float getLifeExpectancy() {
		return lifeExpectancy;
	}

	public void setLifeExpectancy(Float lifeExpectancy) {
		this.lifeExpectancy = lifeExpectancy;
	}

	public Float getGnp() {
		return gnp;
	}

	public void setGnp(Float gnp) {
		this.gnp = gnp;
	}

	public Float getGnpOld() {
		return gnpOld;
	}

	public void setGnpOld(Float gnpOld) {
		this.gnpOld = gnpOld;
	}

	public String getLocalName() {
		return localName;
	}

	public void setLocalName(String localName) {
		this.localName = localName;
	}

	public String getGovernmentForm() {
		return governmentForm;
	}

	public void setGovernmentForm(String governmentForm) {
		this.governmentForm = governmentForm;
	}

	public String getHeadOfState() {
		return headOfState;
	}

	public void setHeadOfState(String headOfState) {
		this.headOfState = headOfState;
	}

	public Integer getCapital() {
		return capital;
	}

	public void setCapital(Integer capital) {
		this.capital = capital;
	}

	public String getCode2() {
		return code2;
	}

	public void setCode2(String code2) {
		this.code2 = code2;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Country [code=");
		builder.append(code);
		builder.append(", name=");
		builder.append(name);
		builder.append(", continent=");
		builder.append(continent);
		builder.append(", region=");
		builder.append(region);
		builder.append(", surfaceArea=");
		builder.append(surfaceArea);
		builder.append(", independeceYear=");
		builder.append(independeceYear);
		builder.append(", population=");
		builder.append(population);
		builder.append(", lifeExpectancy=");
		builder.append(lifeExpectancy);
		builder.append(", gnp=");
		builder.append(gnp);
		builder.append(", gnpOld=");
		builder.append(gnpOld);
		builder.append(", localName=");
		builder.append(localName);
		builder.append(", governmentForm=");
		builder.append(governmentForm);
		builder.append(", headOfState=");
		builder.append(headOfState);
		builder.append(", capital=");
		builder.append(capital);
		builder.append(", code2=");
		builder.append(code2);
		builder.append("]");
		return builder.toString();
	}

	public void addLanguage(String language) {
		if(CountryLanguage.isNameLanguageNotValid(language)) throw new NullPointerException();
		languages.add(CountryLanguage.notOfficial(this, language));
	}
	
	public Set<CountryLanguage> getLanguages() {
		return Collections.unmodifiableSet(languages);
	}

	public void addOfficialLanguage(String language) {
		if(CountryLanguage.isNameLanguageNotValid(language)) throw new NullPointerException();
		languages.add(CountryLanguage.official(this, language));
	}
	
	public List<String> getLanguageNames() {
		return languages.stream().map(CountryLanguage::getLanguage).collect(Collectors.toList());
	}
	
}
