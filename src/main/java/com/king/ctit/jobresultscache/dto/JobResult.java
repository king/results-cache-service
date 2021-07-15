// Copyright (C) king.com Ltd 2019
// https://github.com/king/results-cache-service
// License: Apache 2.0, https://raw.githubusercontent.com/king/results-cache-service/master/LICENSE-APACHE

package com.king.ctit.jobresultscache.dto;

import java.io.Serializable;

public class JobResult implements Serializable {
    private static final long serialVersionUID = 1L;

    public static final JobResult EMPTY_RESULT = new JobResult(Result.NOT_BUILT, -1);

    private Result result;
    private Integer build;

    private JobResult() {}

    public JobResult(Result result, Integer build) {
        this.result = result == null ? Result.NOT_BUILT : result;
        this.build = build == null ? -1 : build;
    }

    public Result getResult() { return result; }
    public Integer getBuild() { return build; }

    @Override
    public String toString() {
        return "JobResult{" +
                "result=" + result +
                ", build=" + build +
                '}';
    }
}
