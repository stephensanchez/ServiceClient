package com.tamer.service.scheduler;

import com.tamer.Job;
import com.tamer.Result;
import com.tamer.client.Client;
import com.tamer.service.Service;
import com.tamer.service.ServiceException;

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

    private Schedule schedule = new Schedule();

    public SchedulerService() {
        // TODO: Set up a slave thread to execute all jobs.
    }

    public void queueJob(Job job, Client client) throws ServiceException {
        schedule.addJob(job, client);

        // TODO: Remove this once slave thread is created.
        Result result = job.execute();
        client.addResult(result);
    }


}
