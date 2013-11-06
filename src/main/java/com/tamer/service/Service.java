package com.tamer.service;

import com.tamer.Job;
import com.tamer.client.Client;

/**
 * A service takes any number of jobs from a {@link Client} and queue them for
 * execution.  By contract, all jobs will be returned in the order in which
 * they were received by any given client.  The priority in which one
 * client's jobs are executed over another is relative to the implementation of
 * the Service.
 * @author ssanchez
 */
public interface Service {

    /**
     * A service is responsible for queuing jobs for execution.
     * @param job The job that needs to be executed by the client.
     * @param client The client requesting this particular job.
     * @throws ServiceException If a particular job or client is rejected,
     *                          an exception is throw.
     */
    public void queueJob(Job job, Client client) throws ServiceException;
}
