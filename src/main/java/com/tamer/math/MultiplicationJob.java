package com.tamer.math;

import com.tamer.Job;
import com.tamer.Result;

/**
 * A {@link Job} that multiples values.
 */
public class MultiplicationJob implements Job {
    private int[] values;

    public MultiplicationJob(int ... values) {
        this.values = values;
    }

    public Result execute() {
        if (values == null) {
            return new Result(Result.Status.FAIL);
        }

        int total = 1;
        for (int value : values) {
            total = total * value;
        }

        return new Result(Result.Status.PASS, total);
    }
}
