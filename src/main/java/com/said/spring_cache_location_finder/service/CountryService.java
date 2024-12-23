package com.said.spring_cache_location_finder.service;

import com.said.spring_cache_location_finder.entity.Country;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

public interface CountryService {

    List<Country> getAllCountries();

    @Cacheable(value = "country", key = "#name")
    Country getCountryByName(String name);

    Country insertNewCountry(Country country);

    Country updateCountry(String countryName, Country country);

    boolean deleteByName(String countryName);
}
