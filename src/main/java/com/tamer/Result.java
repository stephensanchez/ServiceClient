package com.tamer;

/**
 * A Result will be returned from a Job after being executed within a
 * {@link com.tamer.service.Service}. For this example, I have only created two
 * possible conditions, but you could imagine any number of failure states.
 */
public class Result {

    public enum Status {
        PASS,
        FAIL
    }

    private Status status;
    private Object result;

    public Result(Status status) {
        this(status, null);
    }

    public Result(Status status, Object result) {
        this.status = status;
        this.result = result;
    }

    public Object getResult() {
        return result;
    }

    public Object getStatus() {
        return status;
    }
}
