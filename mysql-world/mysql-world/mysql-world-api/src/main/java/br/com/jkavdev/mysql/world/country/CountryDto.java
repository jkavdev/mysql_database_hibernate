/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.jkavdev.mysql.world.country;

/**
 *
 * @author jkavdev
 */
public class CountryDto {

    private String code;
    private String name;
    private String region;
    private Integer population;
    private Integer independenceYear;
    private Float surfaceArea;

    public CountryDto(String code, String name, String region, Integer population, Integer independenceYear, Float surfaceArea) {
        this.code = code;
        this.name = name;
        this.region = region;
        this.population = population;
        this.independenceYear = independenceYear;
        this.surfaceArea = surfaceArea;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public Integer getPopulation() {
        return population;
    }

    public void setPopulation(Integer population) {
        this.population = population;
    }

    public Integer getIndependenceYear() {
        return independenceYear;
    }

    public void setIndependenceYear(Integer independenceYear) {
        this.independenceYear = independenceYear;
    }

    public Float getSurfaceArea() {
        return surfaceArea;
    }

    public void setSurfaceArea(Float surfaceArea) {
        this.surfaceArea = surfaceArea;
    }

    @Override
    public String toString() {
        return "Country{" + "code=" + code + ", name=" + name + ", region=" + region + ", population=" + population + ", indepYear=" + independenceYear + ", area=" + surfaceArea + '}';
    }

}
