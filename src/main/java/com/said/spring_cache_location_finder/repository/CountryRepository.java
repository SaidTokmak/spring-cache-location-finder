package com.said.spring_cache_location_finder.repository;

import com.said.spring_cache_location_finder.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CountryRepository extends JpaRepository<Country, UUID> {

    void deleteByName(String name);

    Country findByName(String name);
}
