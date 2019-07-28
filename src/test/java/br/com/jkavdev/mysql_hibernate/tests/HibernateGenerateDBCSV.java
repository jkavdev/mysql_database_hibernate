package br.com.jkavdev.mysql_hibernate.tests;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.apache.commons.csv.CSVRecord;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.mysql.cj.util.StringUtils;

import br.com.jkavdev.mysql_hibernate.modelos.Country;
import br.com.jkavdev.mysql_hibernate.modelos.CountryHeaders;
import br.com.jkavdev.mysql_hibernate.utils.FileUtils;
import br.com.jkavdev.mysql_hibernate.utils.JpaCommonActions;

public class HibernateGenerateDBCSV {

	EntityManager manager;
	FileUtils fileUtis;
	String countryInsert;
	String countryLanguageInsert;
	String cityInsert;
	String oneLinePrefix = "db-data/hibernate-generate/csv";

	@BeforeClass
	public static void setUp() {
		JpaCommonActions.createFactory("world-hibernate-pu");
	}

	@Before
	public void init() {
		manager = JpaCommonActions.geEntityManager();
		fileUtis = new FileUtils();
		countryInsert = oneLinePrefix + "/bcountries.csv";
		countryLanguageInsert = oneLinePrefix + "/insert-countrylanguage-one-line.sql";
		cityInsert = oneLinePrefix + "/insert-city-one-line.sql";
	}

	@Test
	public void countryInsertData() {
		Iterable<CSVRecord> parser = fileUtis.getRecordsCsvWithHeaders(countryInsert, CountryHeaders.class);

		List<Country> countries = new ArrayList<>();

		for (CSVRecord line : parser) {
			countries.add(Country.from(line));
		}
		JpaCommonActions.beginTransaction();
		countries.forEach(c -> {
			manager.persist(c);
		});
		JpaCommonActions.commitTransaction();

	}

	public Integer getInteger(String value) {
		return StringUtils.isEmptyOrWhitespaceOnly(value) ? 0 : Integer.valueOf(value);
	}

	public Float getFloat(String value) {
		return StringUtils.isEmptyOrWhitespaceOnly(value) ? 0 : Float.valueOf(value);
	}

	@AfterClass
	public static void closeAll() {
		JpaCommonActions.closeAll();
	}

}
