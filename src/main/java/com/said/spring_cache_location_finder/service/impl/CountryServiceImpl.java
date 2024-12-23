package com.said.spring_cache_location_finder.service.impl;

import com.said.spring_cache_location_finder.entity.Country;
import com.said.spring_cache_location_finder.repository.CountryRepository;
import com.said.spring_cache_location_finder.service.CountryService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryServiceImpl implements CountryService {

    private final CountryRepository countryRepository;

    public CountryServiceImpl(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    @Cacheable(value = "all_countries")
    public List<Country> getAllCountries() {
        System.out.println("Retrieve all countries from database!");
        return countryRepository.findAll();
    }

    @Override
    @Cacheable(value = "country", key = "#name")
    public Country getCountryByName(String name) {
        System.out.println("Retrieve country by name from database!");
        return countryRepository.findByName(name);
    }

    @Override
    @CachePut(value = "all_countries", key = "#country.name")
    public Country insertNewCountry(Country country) {
        return countryRepository.save(country);
    }

    @Override
    @Caching(
            cacheable = {@Cacheable(value = "all_countries")},
            evict = {@CacheEvict(value = "all_countries", key = "#countryName")}
    )
    public Country updateCountry(String countryName, Country country) {
        return countryRepository.save(country);
    }

    @Override
    @CacheEvict(value = "all_countries")
    public boolean deleteByName(String countryName) {
        countryRepository.deleteByName(countryName);
        return true;
    }
}
