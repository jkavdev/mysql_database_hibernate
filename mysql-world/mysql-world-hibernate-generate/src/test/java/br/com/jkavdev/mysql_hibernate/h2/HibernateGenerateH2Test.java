package br.com.jkavdev.mysql_hibernate.h2;

import br.com.jkavdev.mysql_hibernate.modelos.City;
import br.com.jkavdev.mysql_hibernate.modelos.Country;
import br.com.jkavdev.mysql_hibernate.modelos.CountryLanguage;
import br.com.jkavdev.mysql_hibernate.utils.FileUtils;
import br.com.jkavdev.mysql_hibernate.utils.JpaCommonActions;
import java.util.List;
import javax.persistence.EntityManager;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class HibernateGenerateH2Test {

    EntityManager manager;
    FileUtils fileUtis;
    String countryInsert;
    String countryLanguageInsert;
    String cityInsert;
    String oneLinePrefix = "db-data/hibernate-generate/one-line";

    @BeforeClass
    public static void setUp() {
        JpaCommonActions.createFactory("mysql-world-h2-pu");
    }

    @Before
    public void init() {
        manager = JpaCommonActions.geEntityManager();
        fileUtis = new FileUtils();
        countryInsert = oneLinePrefix + "/insert-country-one-line.sql";
        countryLanguageInsert = oneLinePrefix + "/insert-countrylanguage-one-line.sql";
        cityInsert = oneLinePrefix + "/insert-city-one-line.sql";
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

//    @Test
    public void countryLanguageInsertData() {
        String sql = fileUtis.getLine(countryLanguageInsert);

        JpaCommonActions.beginTransaction();
        manager.createNativeQuery(sql).executeUpdate();
        JpaCommonActions.commitTransaction();

        List<CountryLanguage> languages = manager.createQuery("from CountryLanguage", CountryLanguage.class).getResultList();
        System.out.println(languages.size());
    }

//    @Test
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
