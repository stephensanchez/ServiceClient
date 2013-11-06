package com.tamer.client;

import com.tamer.Job;
import com.tamer.Result;
import com.tamer.service.Service;

import java.util.List;

/**
 * The Client is responsible for creating any number of jobs to send to the
 * {@link Service}. </br>
 * The Client will eventually be returned results of these {@link Job}s in the
 * order in which they were created by the Client.
 */
public interface Client {

    /**
     * Returns a result from the {@link Service}
     * @param result The result of a {@link Job} performed by the Service.
     */
    public void addResult(Result result);

    /**
     * The Client performs any number of operations based on the implementation.
     * Any client will be able to return its result accordingly.
     * @return The results of the client's process.
     */
    public List<Result> getResults();
}
