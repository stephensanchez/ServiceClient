package com.tamer.service;

import com.tamer.Job;
import com.tamer.Result;
import com.tamer.client.Client;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple scheduling service for executing jobs with a particular prioritization
 * based on the frequency in which any particular client has been served.
 * Any client which has been waiting longest will have highest priority within
 * the queue, while the order in which its internal jobs have been requested
 * is maintained.
 * @author ssanchez
 */
public class SchedulerService implements Service {

    // TODO: This submission will begin with a dummy class to instantiate the test.

    // TODO: Really, really replace this.
    private List<Job> jobs = new ArrayList<Job>();

    public SchedulerService() {
        // TODO: Set up a slave thread to execute all jobs.
    }

    public void queueJob(Job job, Client client) throws ServiceException {
        jobs.add(job);

        // TODO: Remove this once slave thread is created.
        Result result = job.execute();
        client.addResult(result);
    }


}
