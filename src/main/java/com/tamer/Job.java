package com.tamer;

/**
 * A Job describes an isolated executable action.  Any action should return a
 * {@link Result} synchronously.
 * @author ssanchez
 */
public interface Job {

    /**
     * Every implementation of a Job is capable of executing independently. All
     * state of the Job should be contained and set within the implementation
     * before execution.
     * @return Once completed, a Job should return a {@link Result}.
     */
    public Result execute();
}
