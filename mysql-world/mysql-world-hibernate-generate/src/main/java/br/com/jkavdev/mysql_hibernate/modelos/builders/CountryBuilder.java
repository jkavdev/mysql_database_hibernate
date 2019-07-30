package br.com.jkavdev.mysql_hibernate.modelos.builders;

import br.com.jkavdev.mysql_hibernate.modelos.Continent;
import br.com.jkavdev.mysql_hibernate.modelos.Country;

public class CountryBuilder {

	private Country country;

	public Country get() {
		return country;
	}

	public CountryBuilder(String code, String name, Continent continent) {
		this.country = new Country(code, name, continent);
	}

	public CountryBuilder(String code) {
		this.country = new Country(code);

		region(null);
		surfaceArea(null);
		independeceYear(null);
		population(null);
		lifeExpectancy(null);
		gnp(null);
		gnpOld(null);
		localName(null);
		governmentForm(null);
		headOfState(null);
		capital(null);
		code2(null);
	}

	public CountryBuilder region(String region) {
		this.country.setRegion(region);
		return this;
	}

	public CountryBuilder surfaceArea(Float surfaceArea) {
		this.country.setSurfaceArea(surfaceArea);
		return this;
	}

	public CountryBuilder independeceYear(Integer independeceYear) {
		this.country.setIndependeceYear(independeceYear);
		return this;
	}

	public CountryBuilder population(Integer population) {
		this.country.setPopulation(population);
		return this;
	}

	public CountryBuilder lifeExpectancy(Float lifeExpectancy) {
		this.country.setLifeExpectancy(lifeExpectancy);
		return this;
	}

	public CountryBuilder gnp(Float gnp) {
		this.country.setGnp(gnp);
		return this;
	}

	public CountryBuilder gnpOld(Float gnpOld) {
		this.country.setGnpOld(gnpOld);
		return this;
	}

	public CountryBuilder localName(String localName) {
		this.country.setLocalName(localName);
		return this;
	}

	public CountryBuilder governmentForm(String governmentForm) {
		this.country.setGovernmentForm(governmentForm);
		return this;
	}

	public CountryBuilder headOfState(String headOfState) {
		this.country.setHeadOfState(headOfState);
		return this;
	}

	public CountryBuilder capital(Integer capital) {
		this.country.setCapital(capital);
		return this;
	}

	public CountryBuilder code2(String code2) {
		this.country.setCode2(code2);
		return this;
	}

}
