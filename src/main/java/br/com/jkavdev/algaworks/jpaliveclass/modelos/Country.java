package br.com.jkavdev.algaworks.jpaliveclass.modelos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

@Entity
@TypeDef(
		name = "continent_enum",
		typeClass = ContinentEnumType.class
)
public class Country {

	@Id
	@Column(name = "Code", columnDefinition = "char(3)", length = 3, nullable = false)
	private String code = "";

	@Column(name = "Name", columnDefinition = "char(52)", length = 52, nullable = false)
	private String name = "";

	@Enumerated(EnumType.STRING)
	@Column(name = "Continent", nullable = false, 
		columnDefinition = "enum('Asia','Europe','North America','Africa','Oceania','Antarctica','South America')")
	@Type(type = "continent_enum")
	private Continent continent = Continent.defaultContinent;

	@Column(name = "Region", columnDefinition = "char(26)", length = 26, nullable = false)
	private String region = "";

	@Column(name = "SurfaceArea", columnDefinition = "float(10,2)", nullable = false, precision = 10, scale = 2)
	private Float surfaceArea = 0.0f;

	@Column(name = "IndepYear", columnDefinition = "smallint(6)")
	private Integer independeceYear;

	@Column(name = "Population", nullable = false)
	private Integer population = 0;

	@Column(name = "LifeExpectancy", columnDefinition = "float(3,1)", precision = 3, scale = 1)
	private Float lifeExpectancy;

	@Column(name = "GNP", columnDefinition = "float(10,2)", precision = 10, scale = 2)
	private Float gnp;

	@Column(name = "GNPOld", columnDefinition = "float(10,2)", precision = 10, scale = 2)
	private Float gnpOld;

	@Column(name = "LocalName", columnDefinition = "char(45)", length = 45, nullable = false)
	private String localName = "";

	@Column(name = "GovernmentForm", columnDefinition = "char(45)", length = 45, nullable = false)
	private String governmentForm = "";

	@Column(name = "HeadOfState", columnDefinition = "char(60)", length = 60)
	private String headOfState;

	@Column(name = "Capital")
	private Integer capital;

	@Column(name = "Code2", columnDefinition = "char(2)", length = 2, nullable = false)
	private String code2 = "";

	public Country(String code, String name, Continent continent) {
		this.code = code;
		this.name = name;
		this.continent = continent;
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
	
}
