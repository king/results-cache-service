// Copyright (C) king.com Ltd 2019
// https://github.com/king/results-cache-service
// License: Apache 2.0, https://raw.githubusercontent.com/king/results-cache-service/master/LICENSE-APACHE

package com.king.ctit.jobresultscache.controller;

import com.king.ctit.jobresultscache.dto.JobResult;
import com.king.ctit.jobresultscache.services.JobResultsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * Job Results Controller. It manages the HTTP Rest layer
 */
@Api(value = "job-results", description = "Job Results API")
@RestController
@RequestMapping("/job-results")
public class JobResultsController {

    private static final Logger logger = LoggerFactory.getLogger(JobResultsController.class);

    private JobResultsService service;

    @Autowired
    public JobResultsController(JobResultsService service) {
        this.service = service;
    }

    /**
     * Gets a cached result from the cache
     * @param hash job hash
     * @return a jenkins job cached result
     */
    @ApiOperation("Gets a cached result from the cache")
    @GetMapping(value = "/{hash}", produces = MediaType.TEXT_PLAIN_VALUE)
    public @ResponseBody String getCachedResult(@ApiParam(value = "Job hash", required = true) @PathVariable("hash") String hash) {
        logger.debug("Asking for hash: {}", hash);
        JobResult result = service.getJobResult(hash);
        logger.debug("Result for hash: {} is {}", hash, result);
        return result.name();
    }

    /**
     * Adds or Updates a job result in the cache
     * @param hash job hash
     * @param result job result
     */
    @ApiOperation("Adds or Updates a result in the cache")
    @PostMapping(value = "/{hash}/{result}")
    public void addOrUpdateResult(@ApiParam(value = "Job hash to identify it in the cache", required = true) @PathVariable("hash") String hash,
                                  @ApiParam(value = "Job result to add or update", required = true, allowableValues = "SUCCESS, UNSTABLE, FAILURE, NOT_BUILT, ABORTED") @PathVariable("result") JobResult result) {
        logger.debug("Adding result {} to hash {}", result.name(), hash);
        service.addJobResult(hash, result);
    }

    /**
     * Clears the cache
     */
    @ApiOperation("Clears the cache")
    @DeleteMapping(value = "/clear")
    public void clearCache() {
        logger.debug("Cleaning cache...");
        service.clearCache();
    }
}
