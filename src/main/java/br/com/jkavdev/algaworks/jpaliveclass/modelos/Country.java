package br.com.jkavdev.algaworks.jpaliveclass.modelos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

@Entity
public class Country {

	@Id
	@Column(name = "Code", length = 3, nullable = false)
	private String code;

	@Column(name = "Name", length = 52, nullable = false)
	private String name;

	@Enumerated(EnumType.STRING)
	@Column(name = "Continent", nullable = false, columnDefinition = "enum('Asia','Europe','North America','Africa','Oceania','Antarctica','South America')")
	private Continent continent;

	@Column(name = "Region", length = 26, nullable = false)
	private String region;

	@Column(name = "SurfaceArea", nullable = false, precision = 10, scale = 2)
	private Float surfaceArea;

	@Column(name = "IndepYear")
	private Integer independeceYear;

	@Column(name = "Population", nullable = false)
	private Integer population;

	@Column(name = "LifeExpectancy", precision = 3, scale = 1)
	private Float lifeExpectancy;

	@Column(name = "GNP", precision = 10, scale = 2)
	private Float gnp;

	@Column(name = "GNPOld", precision = 10, scale = 2)
	private Float gnpOld;

	@Column(name = "LocalName", length = 45, nullable = false)
	private String localName;

	@Column(name = "GovernmentForm", length = 45, nullable = false)
	private String governmentForm;

	@Column(name = "HeadOfState", length = 60)
	private String headOfState;

	@Column(name = "Capital")
	private Integer capital;

	@Column(name = "Code2", length = 2, nullable = false)
	private String code2;

}
