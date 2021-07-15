// Copyright (C) king.com Ltd 2019
// https://github.com/king/results-cache-service
// License: Apache 2.0, https://raw.githubusercontent.com/king/results-cache-service/master/LICENSE-APACHE

package com.king.ctit.jobresultscache.controller.v2;

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
@Api(tags = {Application.JOB_RESULTS_V2_TAG})
@RestController
@RequestMapping("/job-results/v2")
public class JobResultsControllerV2 {

    private static final Logger logger = LoggerFactory.getLogger(JobResultsControllerV2.class);

    private JobResultsService service;

    @Autowired
    public JobResultsControllerV2(JobResultsService service) {
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
        JobResult result = service.getJobResult(hash);
        logger.debug("Job result for hash: {} is {}", hash, result);
        return result;
    }

    /**
     * Adds or Updates a job result in the cache
     * @param hash job hash
     * @param jobResult job result
     */
    @ApiOperation("Adds or Updates a job result in the cache")
    @PostMapping(value = "/{hash}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void postResult(@ApiParam(value = "Job hash to identify the job in the cache", required = true) @PathVariable("hash") String hash,
                           @RequestBody JobResult jobResult) {
        logger.debug("Adding cached job result {} to hash {}", jobResult, hash);
        service.addOrUpdateJobResult(hash, jobResult);
    }
}
