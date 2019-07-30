package br.com.jkavdev.mysql_hibernate;

import java.util.List;

import javax.persistence.EntityManager;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import br.com.jkavdev.mysql_hibernate.modelos.City;
import br.com.jkavdev.mysql_hibernate.modelos.Country;
import br.com.jkavdev.mysql_hibernate.modelos.CountryLanguage;
import br.com.jkavdev.mysql_hibernate.utils.FileUtils;
import br.com.jkavdev.mysql_hibernate.utils.JpaCommonActions;

public class HibernateGenerateMultipleStatementsDBTest {

	EntityManager manager;
	FileUtils fileUtis;
	String countryInsert;
	String countryLanguageInsert;
	String cityInsert;
	String oneLinePrefix = "db-data/hibernate-generate/multiple-statements";

	@BeforeClass
	public static void setUp() {
		JpaCommonActions.createFactory("world-hibernate-pu");
	}

	@Before
	public void init() {
		manager = JpaCommonActions.geEntityManager();
		fileUtis = new FileUtils();
		countryInsert = oneLinePrefix + "/insert-country.sql";
		countryLanguageInsert = oneLinePrefix + "/insert-countrylanguage.sql";
		cityInsert = oneLinePrefix + "/insert-city.sql";
	}

	@Test
	public void countryInsertData() {
		List<String> sql = fileUtis.getLines(countryInsert);

		JpaCommonActions.beginTransaction();
		sql.forEach(l -> manager.createNativeQuery(l).executeUpdate());
		JpaCommonActions.commitTransaction();

		List<Country> countries = manager.createQuery("from Country", Country.class).getResultList();
		System.out.println(countries.size());
	}

	@Test
	public void countryLanguageInsertData() {
		List<String> sql = fileUtis.getLines(countryLanguageInsert);

		JpaCommonActions.beginTransaction();
		sql.forEach(l -> manager.createNativeQuery(l).executeUpdate());
		JpaCommonActions.commitTransaction();

		List<CountryLanguage> languages = manager.createQuery("from CountryLanguage", CountryLanguage.class)
				.getResultList();
		System.out.println(languages.size());
	}

	@Test
	public void cityInsertData() {
		List<String> sql = fileUtis.getLines(cityInsert);

		JpaCommonActions.beginTransaction();
		sql.forEach(l -> manager.createNativeQuery(l).executeUpdate());
		JpaCommonActions.commitTransaction();

		List<City> cities = manager.createQuery("from City", City.class).getResultList();
		System.out.println(cities);
	}

	@AfterClass
	public static void closeAll() {
		JpaCommonActions.closeAll();
	}

}
