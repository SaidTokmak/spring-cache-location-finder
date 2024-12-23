package com.said.spring_cache_location_finder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class SpringCacheLocationFinderApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCacheLocationFinderApplication.class, args);
	}

}
