package br.com.jkavdev.mysql_hibernate;

import java.util.List;

import javax.persistence.EntityManager;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

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

	}

	@Test
	public void countryLanguageInsertData() {
		List<String> sql = fileUtis.getLines(countryLanguageInsert);

		JpaCommonActions.beginTransaction();
		sql.forEach(l -> manager.createNativeQuery(l).executeUpdate());
		JpaCommonActions.commitTransaction();

	}

	@Test
	public void cityInsertData() {
		List<String> sql = fileUtis.getLines(cityInsert);

		JpaCommonActions.beginTransaction();
		sql.forEach(l -> manager.createNativeQuery(l).executeUpdate());
		JpaCommonActions.commitTransaction();

	}

	@AfterClass
	public static void closeAll() {
		JpaCommonActions.closeAll();
	}

}
