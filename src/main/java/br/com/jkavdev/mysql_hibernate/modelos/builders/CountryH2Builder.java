package br.com.jkavdev.mysql_hibernate.modelos.builders;

import br.com.jkavdev.mysql_hibernate.modelos.Continent;
import br.com.jkavdev.mysql_hibernate.modelos.h2.Country;

public class CountryH2Builder {

	private Country country;

	public Country get() {
		return country;
	}

	public CountryH2Builder(String code, String name, Continent continent) {
		this.country = new Country(code, name, continent);
	}

	public CountryH2Builder(String code) {
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

	public CountryH2Builder region(String region) {
		this.country.setRegion(region);
		return this;
	}

	public CountryH2Builder surfaceArea(Float surfaceArea) {
		this.country.setSurfaceArea(surfaceArea);
		return this;
	}

	public CountryH2Builder independeceYear(Integer independeceYear) {
		this.country.setIndependeceYear(independeceYear);
		return this;
	}

	public CountryH2Builder population(Integer population) {
		this.country.setPopulation(population);
		return this;
	}

	public CountryH2Builder lifeExpectancy(Float lifeExpectancy) {
		this.country.setLifeExpectancy(lifeExpectancy);
		return this;
	}

	public CountryH2Builder gnp(Float gnp) {
		this.country.setGnp(gnp);
		return this;
	}

	public CountryH2Builder gnpOld(Float gnpOld) {
		this.country.setGnpOld(gnpOld);
		return this;
	}

	public CountryH2Builder localName(String localName) {
		this.country.setLocalName(localName);
		return this;
	}

	public CountryH2Builder governmentForm(String governmentForm) {
		this.country.setGovernmentForm(governmentForm);
		return this;
	}

	public CountryH2Builder headOfState(String headOfState) {
		this.country.setHeadOfState(headOfState);
		return this;
	}

	public CountryH2Builder capital(Integer capital) {
		this.country.setCapital(capital);
		return this;
	}

	public CountryH2Builder code2(String code2) {
		this.country.setCode2(code2);
		return this;
	}

}
