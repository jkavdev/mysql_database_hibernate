/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.jkavdev.mysql.world.country;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author jkavdev
 */
@RestController
@RequestMapping("/countries")
public class CountryResource {

	@Autowired
	private CountryRepository countryRepository;

	@GetMapping("/info")
	public List<CountryDto> getCountries() {
		return countryRepository.countriesInfo();
	}

}
