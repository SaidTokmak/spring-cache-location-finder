package com.said.spring_cache_location_finder.service.impl;

import com.said.spring_cache_location_finder.config.CacheConfig;
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

    public CountryServiceImpl(CountryRepository countryRepository, CacheConfig cacheConfig) {
        this.countryRepository = countryRepository;
    }

    @Override
    @Cacheable(value = "all_countries", key = "#root.methodName")
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
    @Caching(
            put = {@CachePut(value = "country", key = "#country.name")},
            evict = {@CacheEvict(value = "all_countries", allEntries = true)}
    )
    public Country insertNewCountry(Country country) {
        return countryRepository.save(country);
    }

    @Override
    @Caching(
            put = {@CachePut(value = "country", key = "#countryName")},
            evict = {@CacheEvict(value = "all_countries", allEntries = true)}
    )
    public Country updateCountry(String countryName, Country country) {
        return countryRepository.save(country);
    }

    @Override
    @Caching(
            evict = {
                    @CacheEvict(value = "all_countries", allEntries = true),
                    @CacheEvict(value = "country", key = "#countryName")
            }
    )
    public boolean deleteByName(String countryName) {
        Country byName = countryRepository.findByName(countryName);
        countryRepository.deleteById(byName.getId());
        return true;
    }
}
