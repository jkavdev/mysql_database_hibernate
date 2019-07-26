package br.com.jkavdev.mysql_hibernate.modelos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class City {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Integer id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CountryCode", nullable = false)
	private Country country;
	
	@Column(name = "Name", nullable = false, columnDefinition = "char(35)")
	private String name;
	
	@Column(name = "District", nullable = false, columnDefinition = "char(20)")
	private String district;
	
	@Column(name = "Population", nullable = false, columnDefinition = "int(11) default 0")
	private Integer population = 0;
	
	protected City() {}

	public City(Country country, String name, String district) {
		this.country = country;
		this.name = name;
		this.district = district;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public Integer getPopulation() {
		return population;
	}

	public void setPopulation(Integer population) {
		this.population = population;
	}

	public Integer getId() {
		return id;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("[id=");
		builder.append(id);
//		builder.append(", country=");
//		builder.append(country.getCode());
		builder.append(", name=");
		builder.append(name);
		builder.append(", district=");
		builder.append(district);
		builder.append(", population=");
		builder.append(population);
		builder.append("]");
		return builder.toString();
	}
	
}
