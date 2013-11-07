package com.tamer.service.scheduler;

import com.tamer.Job;
import com.tamer.client.Client;

/**
 * Simple Tuple
 */
public class Task {

    private final Client client;
    private final Job job;

    public Task(Client client, Job job) {
        this.client = client;
        this.job = job;
    }
}
