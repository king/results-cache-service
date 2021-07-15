// Copyright (C) king.com Ltd 2019
// https://github.com/king/results-cache-service
// License: Apache 2.0, https://raw.githubusercontent.com/king/results-cache-service/master/LICENSE-APACHE

package com.king.ctit.jobresultscache.dto;

/**
 * Job result values
 * @see <a href="https://javadoc.jenkins-ci.org/hudson/model/Result.html">hudson.model.Result</a>
 */
public enum Result {
    SUCCESS, UNSTABLE, FAILURE, NOT_BUILT, ABORTED
}
