/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.jkavdev.mysql.world.country;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author jkavdev
 */
@Repository
public interface CountryRepository extends JpaRepository<Country, String> {

    @Query(value = "select new br.com.jkavdev.mysql.world.country.CountryDto("
            + " c.code, c.name, c.region, c.population, "
            + " c.independeceYear, c.surfaceArea) from Country c")
    List<CountryDto> countriesInfo();

}
