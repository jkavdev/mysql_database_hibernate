package br.com.jkavdev.mysql_hibernate.tests;

import java.util.List;

import javax.persistence.EntityManager;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import br.com.jkavdev.mysql_hibernate.FileUtils;
import br.com.jkavdev.mysql_hibernate.modelos.City;
import br.com.jkavdev.mysql_hibernate.modelos.Country;
import br.com.jkavdev.mysql_hibernate.modelos.CountryLanguage;

public class HibernateGenerateDB {

	EntityManager manager;
	FileUtils fileUtis;
	String countryInsert;
	String countryLanguageInsert;
	String cityInsert;

	@BeforeClass
	public static void setUp() {
		JpaCommonActions.createFactory("world-hibernate-pu");
	}

	@Before
	public void init() {
		manager = JpaCommonActions.geEntityManager();
		fileUtis = new FileUtils();
		countryInsert = "db-data/insert-country-one-line.sql";
		countryLanguageInsert = "db-data/insert-countrylanguage-one-line.sql";
		cityInsert = "db-data/insert-city-one-line.sql";
	}

	@Test
	public void countryInsertData() {
		String sql = fileUtis.getLine(countryInsert);

		JpaCommonActions.beginTransaction();
		manager.createNativeQuery(sql).executeUpdate();
		JpaCommonActions.commitTransaction();

		List<Country> countries = manager.createQuery("from Country", Country.class).getResultList();
		System.out.println(countries.size());
	}
	@Test
	public void countryLanguageInsertData() {
		String sql = fileUtis.getLine(countryLanguageInsert);
		
		JpaCommonActions.beginTransaction();
		manager.createNativeQuery(sql).executeUpdate();
		JpaCommonActions.commitTransaction();
		
		List<CountryLanguage> languages = manager.createQuery("from CountryLanguage", CountryLanguage.class).getResultList();
		System.out.println(languages.size());
	}
	@Test
	public void cityInsertData() {
		String sql = fileUtis.getLine(cityInsert);
		
		JpaCommonActions.beginTransaction();
		manager.createNativeQuery(sql).executeUpdate();
		JpaCommonActions.commitTransaction();
		
		List<City> cities = manager.createQuery("from City", City.class).getResultList();
		System.out.println(cities);
	}

	@AfterClass
	public static void closeAll() {
		JpaCommonActions.closeAll();
	}

}
