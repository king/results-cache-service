// Copyright (C) king.com Ltd 2019
// https://github.com/king/results-cache-service
// License: Apache 2.0, https://raw.githubusercontent.com/king/results-cache-service/master/LICENSE-APACHE

package com.king.ctit.jobresultscache;

import com.google.common.base.Predicates;
import org.ehcache.CacheManager;
import org.ehcache.config.Configuration;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.xml.XmlConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.net.URL;

@SpringBootApplication
@EnableSwagger2
public class Application {

    public static final String JOB_RESULTS_TAG = "job-results";

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

    /**
     * EHCache configuration
     * @return cache manager
     */
    @Bean
    public CacheManager getCacheManager() {
        URL myUrl = getClass().getResource("/cache.xml");
        Configuration xmlConfig = new XmlConfiguration(myUrl);
        CacheManager cacheManager = CacheManagerBuilder.newCacheManager(xmlConfig);
        cacheManager.init();
        return cacheManager;
    }

    /**
     * Swagger configuration
     * @return Docket
     */
    @Bean
    public Docket swaggerSpringMvcPlugin() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(Predicates.not(RequestHandlerSelectors.basePackage("org.springframework.boot")))
                .paths(PathSelectors.any())
                .build()
                .tags(new Tag(JOB_RESULTS_TAG, "Job Results API"));
    }
}
