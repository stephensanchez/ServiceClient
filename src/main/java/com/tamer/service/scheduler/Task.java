package com.tamer.service.scheduler;

/**
 * Simple Tuple
 */
public class Task<T, V> {

    private final V client;
    private final T job;

    public Task(V client, T job) {
        this.client = client;
        this.job = job;
    }

    public T getJob() {
        return job;
    }

    public V getClient() {
        return client;
    }
}
