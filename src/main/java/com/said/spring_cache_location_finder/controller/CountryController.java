package com.said.spring_cache_location_finder.controller;

import com.said.spring_cache_location_finder.entity.Country;
import com.said.spring_cache_location_finder.service.CountryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/countries")
public class CountryController {

    private final CountryService countryService;

    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping
    public List<Country> getAllCounties() {
        return countryService.getAllCountries();
    }

    @GetMapping("/{name}")
    public Country getCountryByName(@PathVariable String name) {
        return countryService.getCountryByName(name);
    }

    @PostMapping
    public Country insert(@RequestBody Country country) {
        return countryService.insertNewCountry(country);
    }

    @PutMapping("/{name}")
    public Country update(@PathVariable String name, @RequestBody Country country) {
        return countryService.updateCountry(name, country);
    }

    @DeleteMapping("/{name}")
    public boolean delete(@PathVariable String name) {
        return countryService.deleteByName(name);
    }
}
