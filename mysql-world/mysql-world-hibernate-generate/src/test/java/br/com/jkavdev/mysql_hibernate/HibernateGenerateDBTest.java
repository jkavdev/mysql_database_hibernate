package br.com.jkavdev.mysql_hibernate;

import javax.persistence.EntityManager;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import br.com.jkavdev.mysql_hibernate.utils.FileUtils;
import br.com.jkavdev.mysql_hibernate.utils.JpaCommonActions;

public class HibernateGenerateDBTest {

	EntityManager manager;
	FileUtils fileUtis;
	String countryInsert;
	String countryLanguageInsert;
	String cityInsert;
	String oneLinePrefix = "db-data/hibernate-generate/one-line";

	@BeforeClass
	public static void setUp() {
		JpaCommonActions.createFactory("world-hibernate-pu");
	}

	@Before
	public void init() {
		manager = JpaCommonActions.geEntityManager();
		fileUtis = new FileUtils();
		countryInsert = oneLinePrefix+"/insert-country-one-line.sql";
		countryLanguageInsert = oneLinePrefix+"/insert-countrylanguage-one-line.sql";
		cityInsert = oneLinePrefix+"/insert-city-one-line.sql";
	}

	@Test
	public void countryInsertData() {
		String sql = fileUtis.getLine(countryInsert);

		JpaCommonActions.beginTransaction();
		manager.createNativeQuery(sql).executeUpdate();
		JpaCommonActions.commitTransaction();

	}

	@Test
	public void countryLanguageInsertData() {
		String sql = fileUtis.getLine(countryLanguageInsert);

		JpaCommonActions.beginTransaction();
		manager.createNativeQuery(sql).executeUpdate();
		JpaCommonActions.commitTransaction();

	}

	@Test
	public void cityInsertData() {
		String sql = fileUtis.getLine(cityInsert);

		JpaCommonActions.beginTransaction();
		manager.createNativeQuery(sql).executeUpdate();
		JpaCommonActions.commitTransaction();

	}

	@AfterClass
	public static void closeAll() {
		JpaCommonActions.closeAll();
	}

}
