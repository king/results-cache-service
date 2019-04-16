// Copyright (C) king.com Ltd 2019
// https://github.com/king/results-cache-service
// License: Apache 2.0, https://raw.githubusercontent.com/king/results-cache-service/master/LICENSE-APACHE

package com.king.ctit.jobresultscache.ehcache;

import com.king.ctit.jobresultscache.dto.JobResult;
import org.ehcache.event.CacheEvent;
import org.ehcache.event.CacheEventListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Cache Event Listener to write log traces
 */
public class EventLogger implements CacheEventListener<String, JobResult> {

    private static final Logger LOGGER = LoggerFactory.getLogger(EventLogger.class);

    /**
     * Writes an info log trace on every cache event
     * @param event event
     */
    @Override
    public void onEvent(CacheEvent<? extends String, ? extends JobResult> event) {
        LOGGER.info("Event [{}] Key: '{}', old value: '{}', new value: '{}'", event.getType(), event.getKey(), event.getOldValue(), event.getNewValue());
    }
}