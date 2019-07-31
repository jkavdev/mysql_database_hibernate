package br.com.jkavdev.mysql_hibernate.h2;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.apache.commons.csv.CSVRecord;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import br.com.jkavdev.mysql_hibernate.csv.CityHeaders;
import br.com.jkavdev.mysql_hibernate.csv.CountryHeaders;
import br.com.jkavdev.mysql_hibernate.csv.CountryLanguageHeaders;
import br.com.jkavdev.mysql_hibernate.modelos.h2.City;
import br.com.jkavdev.mysql_hibernate.modelos.h2.Country;
import br.com.jkavdev.mysql_hibernate.modelos.h2.CountryLanguage;
import br.com.jkavdev.mysql_hibernate.utils.FileUtils;
import br.com.jkavdev.mysql_hibernate.utils.JpaCommonActions;

public class HibernateGenerateDBCSVTest {

    EntityManager manager;
    FileUtils fileUtis;
    String countryInsert;
    String countryLanguageInsert;
    String cityInsert;
    String oneLinePrefix = "db-data/hibernate-generate/csv";

    @BeforeClass
    public static void setUp() {
        JpaCommonActions.createFactory("mysql-world-h2-pu");
    }

    @Before
    public void init() {
        manager = JpaCommonActions.geEntityManager();
        fileUtis = new FileUtils();
        countryInsert = oneLinePrefix + "/bcountries_.csv";
        countryLanguageInsert = oneLinePrefix + "/blanguages_.csv";
        cityInsert = oneLinePrefix + "/bcities.csv";
    }

    @Test
    public void countryInsertData() {
        Iterable<CSVRecord> parser = fileUtis.getRecordsCsvWithHeadersAndComaAndSingleCote(countryInsert, CountryHeaders.class);

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

    @Test
    public void countryLanguageInsertData() {
        Iterable<CSVRecord> parser = fileUtis.getRecordsCsvWithHeadersAndComaAndSingleCote(countryLanguageInsert, CountryLanguageHeaders.class);

        List<CountryLanguage> languages = new ArrayList<>();

        for (CSVRecord line : parser) {
            languages.add(CountryLanguage.from(line));
        }
        JpaCommonActions.beginTransaction();
        languages.forEach(c -> {
            manager.persist(c);
        });
        JpaCommonActions.commitTransaction();

    }

//    @Test
    public void citiesInsertData() {
        Iterable<CSVRecord> parser = fileUtis.getRecordsCsvWithHeadersAndComaAndSingleCote(cityInsert, CityHeaders.class);

        List<City> cities = new ArrayList<>();

        for (CSVRecord line : parser) {
            cities.add(City.from(line));
        }

        JpaCommonActions.beginTransaction();
        cities.forEach(c -> {
            c.setCountry(manager.find(Country.class, c.getCountry().getCode()));
            manager.persist(c);
        });
        JpaCommonActions.commitTransaction();

    }

    @AfterClass
    public static void closeAll() {
        JpaCommonActions.closeAll();
    }

}
