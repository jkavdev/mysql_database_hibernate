package br.com.jkavdev.mysql_hibernate.tests;

import java.io.FileReader;
import java.io.IOException;

import javax.persistence.EntityManager;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import br.com.jkavdev.mysql_hibernate.utils.FileUtils;

public class HibernateGenerateDBCSV {

	EntityManager manager;
	FileUtils fileUtis;
	String countryInsert;
	String countryLanguageInsert;
	String cityInsert;
	String oneLinePrefix = "db-data/hibernate-generate/csv";

	@BeforeClass
	public static void setUp() {
//		JpaCommonActions.createFactory("world-hibernate-pu");
	}

	@Before
	public void init() {
//		manager = JpaCommonActions.geEntityManager();
		fileUtis = new FileUtils();
		countryInsert = oneLinePrefix + "/bcountries.csv";
		countryLanguageInsert = oneLinePrefix + "/insert-countrylanguage-one-line.sql";
		cityInsert = oneLinePrefix + "/insert-city-one-line.sql";
	}

	@Test
	public void countryInsertData() {

		FileReader fileReader = fileUtis.getFileReader(countryInsert);
		try {
			for (CSVRecord line : CSVFormat.DEFAULT.parse(fileReader)) {
				System.out.println(line.get(0));
				System.out.println(line.get(1));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@AfterClass
	public static void closeAll() {
//		JpaCommonActions.closeAll();
	}

}
