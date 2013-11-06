package com.tamer;

/**
 * A Result will be returned from a Job after being executed within a
 * {@link com.tamer.service.Service}. For this example, I have only created two
 * possible conditions, but you could imagine any number of failure states.
 */
public enum Result {

    PASS,
    FAIL;

    private Object result;

    public void setResult(Object result) {
        this.result = result;
    }

    public Object getResult() {
        return result;
    }
}
