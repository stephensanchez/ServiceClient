package com.tamer.service.scheduler;

import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Data Structure designed to maintain an order for all jobs requested by any
 * number of clients to a particular {@link SchedulerService}.  By design this
 * is intended to prioritize based on the last time any given client was updated,
 * and the order in which the jobs for that client were requested; prioritizing
 * the most neglected client foremost, and earliest submitted job therein.
 */
public class SynchronizedSchedule<T,V>  implements Schedule<T, V>{

    // Performance hit, but we don't want sorting and cleanup occur on retrieval,
    // so we want to ensure that add() and next() do not have concurrency issues.
    private List<WeightedClient<T, V>> clients =
            Collections.synchronizedList(new ArrayList<WeightedClient<T, V>>());

    public void add(T t, V v) {
        WeightedClient<T, V> wClient = new WeightedClient<T, V>(t, v);
        if (clients.contains(wClient)) {
            clients.get(clients.indexOf(wClient)).addJob(t);
        } else {
            clients.add(wClient);
        }
    }

    // No client will be inserted into the Schedule without a job, and any
    // Client without a remaining job is removed.
    public boolean hasNext() {
        return !clients.isEmpty();
    }

    public Task<T, V> next() {
        WeightedClient<T, V> wClient = clients.remove(0);
        T nextJob = wClient.nextJob();

        // Knowing we just pulled a job, update the time it was last pulled.
        // If the client no longer has any jobs, remove it from the schedule.
        if (wClient.hasJobs())
            clients.add(wClient);

        return new Task<T, V>(nextJob, wClient.client);
    }

    private class WeightedClient<T,V> {

        private final V client;
        private Queue<T> jobs = new ConcurrentLinkedQueue<T>();

        /**
         * Every Client added to the schedule must be associated with at least
         * a single job.
         * @param client The associated, actual client
         * @param job The first job in a new Weighted Client.
         */
        protected WeightedClient(T job, V client) {
            this.client = client;
            addJob(job);
        }

        protected void addJob(T job) {
            jobs.offer(job);
        }

        protected T nextJob() {
            return jobs.poll();
        }

        protected boolean hasJobs() {
            return !jobs.isEmpty();
        }

        @Override
        public boolean equals(Object o) {
            return o instanceof WeightedClient && ((WeightedClient<T, V>)o).client == client;
        }
    }

}
