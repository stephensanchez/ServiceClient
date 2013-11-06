package com.tamer.client.math;

import com.tamer.Job;
import com.tamer.Result;

/**
 * Simple job to add values together.
 * @author ssanchez
 */
public class AdditionJob implements Job {

    int[] values;

    public AdditionJob(int ... values) {
        this.values = values;
    }
    public Result execute() {
        if (values == null) {
            return new Result(Result.Status.FAIL);
        }

        int total = 0;
        for (int value : values) {
            total += value;
        }

        return new Result(Result.Status.PASS, total);
    }
}
