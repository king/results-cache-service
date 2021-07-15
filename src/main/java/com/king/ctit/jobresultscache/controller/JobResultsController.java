// Copyright (C) king.com Ltd 2019
// https://github.com/king/results-cache-service
// License: Apache 2.0, https://raw.githubusercontent.com/king/results-cache-service/master/LICENSE-APACHE

package com.king.ctit.jobresultscache.controller;

import com.king.ctit.jobresultscache.Application;
import com.king.ctit.jobresultscache.dto.JobResult;
import com.king.ctit.jobresultscache.services.JobResultsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Job Results Controller. It manages the HTTP Rest layer
 */
@Api(tags = {Application.JOB_RESULTS_TAG})
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
     * Gets a cached job result from the cache
     * @param hash job hash
     * @return a jenkins job cached result
     */
    @ApiOperation("Gets a cached job result from the cache")
    @GetMapping(value = "/{hash}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody JobResult getResult(@ApiParam(value = "Job hash", required = true) @PathVariable("hash") String hash) {
        logger.debug("Asking for hash: {}", hash);
        JobResult result = service.getResult(hash);
        logger.debug("Result for hash: {} is {}", hash, result);
        return result;
    }

    /**
     * Adds or Updates a job result in the cache
     * @param hash job hash
     * @param result job result
     */
    @ApiOperation("Adds or Updates a job result in the cache")
    @PostMapping(value = "/{hash}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void postResult(@ApiParam(value = "Job hash to identify the job in the cache", required = true) @PathVariable("hash") String hash,
                           @RequestBody JobResult result) {
        logger.debug("Adding cached result {} to hash {}", result, hash);
        service.addOrUpdateResult(hash, result);
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
