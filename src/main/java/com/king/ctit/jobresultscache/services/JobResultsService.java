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

    private final CacheManager cacheManager;
    private final Cache<String, JobResult> cache;

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
     * Gets a cached job result from the cache. Returns {@link JobResult#EMPTY_RESULT} if not found.
     * @param hash job hash
     * @return cached job result
     */
    public JobResult getJobResult(String hash) {
        return cache.containsKey(hash) ? cache.get(hash) : JobResult.EMPTY_RESULT;
    }

    /**
     * Adds or Updates a cached job result under a job hash
     * @param hash job hash
     * @param result job result
     */
    public void addOrUpdateJobResult(String hash, JobResult result) {
        cache.put(hash, result);
    }

    /**
     * Cleans the cache
     */
    public void clearCache() {
        cache.clear();
    }
}
