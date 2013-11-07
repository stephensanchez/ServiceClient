package com.tamer.service.scheduler;

import com.tamer.Job;
import com.tamer.client.Client;

import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Data Structure designed to maintain an order for all jobs requested by any
 * number of clients to a particular {@link SchedulerService}.  By design this
 * is intended to prioritize based on the last time any given client was updated,
 * and the order in which the jobs for that client were requested; prioritizing
 * the most neglected client foremost, and earliest submitted job therein.
 */
public class Schedule {

    List<WeightedClient> clients = new ArrayList<WeightedClient>();

    Comparator<WeightedClient> lastPulledComparator =
            new Comparator<WeightedClient>() {
        public int compare(WeightedClient weightedClient,
                           WeightedClient weightedClient2) {
            return weightedClient.lastPulled.compareTo(weightedClient2.lastPulled);
        }
    };

    public void addJob(Job job, Client client) {
        WeightedClient wClient = new WeightedClient(client, job);
        if (clients.contains(wClient)) {
            clients.get(clients.indexOf(wClient)).addJob(job);
        } else {
            clients.add(wClient);
        }
    }

    // No client will be inserted into the Schedule without a job, and any
    // Client without a remaining job is removed.
    public boolean hasJobs() {
        return !clients.isEmpty();
    }

    public Task next() {
        Collections.sort(clients, lastPulledComparator);
        WeightedClient wClient = clients.get(0);
        Job nextJob = wClient.nextJob();

        // Knowing we just pulled a job, update the time it was last pulled.
        // If the client no longer has any jobs, remove it from the schedule.
        if (wClient.hasJobs())
            wClient.lastPulled = new Date();
        else
            clients.remove(wClient);

        return new Task(wClient.client, nextJob);
    }

    private class WeightedClient {

        private final Client client;
        private Queue<Job> jobs = new ConcurrentLinkedQueue<Job>();
        private Date lastPulled = new Date(); // intentionally de-prioritize new clients.

        /**
         * Every Client added to the schedule must be associated with at least
         * a single job.
         * @param client The associated, actual client
         * @param job The first job in a new Weighted Client.
         */
        protected WeightedClient(Client client, Job job) {
            this.client = client;
            addJob(job);
        }

        protected void addJob(Job job) {
            jobs.offer(job);
        }

        protected Job nextJob() {
            return jobs.poll();
        }

        protected boolean hasJobs() {
            return !jobs.isEmpty();
        }

        @Override
        public boolean equals(Object o) {
            return o instanceof WeightedClient && ((WeightedClient)o).client == client;
        }
    }

}
