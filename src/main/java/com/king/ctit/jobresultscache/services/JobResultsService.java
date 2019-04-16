// Copyright (C) king.com Ltd 2019
// https://github.com/king/results-cache-service
// License: Apache 2.0, https://raw.githubusercontent.com/king/results-cache-service/master/LICENSE-APACHE

package com.king.ctit.jobresultscache.services;

import com.king.ctit.jobresultscache.dto.JobResult;
import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PreDestroy;

/**
 * Job Results Service. It manages the service business with the EHCache instance
 */
@Service
public class JobResultsService {

    private CacheManager cacheManager;
    private Cache<String, JobResult> cache;

    @Autowired
    public JobResultsService(CacheManager cacheManager) {
        this.cacheManager = cacheManager;
        this.cache = cacheManager.getCache("job-results", String.class, JobResult.class);
    }

    /**
     * Destroys the cache before shutdown the service
     */
    @PreDestroy
    public void tearDown() {
        cacheManager.close();
    }

    /**
     * Gets a cached result from the cache. Returns NOT_BUILD if not found.
     * @param hash job hash
     * @return cached result from the cache. NOT_BUILD if not found.
     */
    public JobResult getJobResult(String hash) {
        JobResult jobResult = cache.containsKey(hash) ? cache.get(hash) : JobResult.NOT_BUILT;
        return jobResult;
    }

    /**
     * Adds or Updates a results for a job
     * @param hash job hash
     * @param result job esult
     */
    public void addJobResult(String hash, JobResult result) {
        cache.put(hash, result);
    }

    /**
     * Cleans the cache
     */
    public void clearCache() {
        cache.clear();
    }
}
