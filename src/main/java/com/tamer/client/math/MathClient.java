package com.tamer.client.math;

import com.tamer.Job;
import com.tamer.Result;
import com.tamer.client.Client;
import com.tamer.service.Service;
import com.tamer.service.ServiceException;

import java.util.ArrayList;
import java.util.List;

/**
 * Client that takes in math requests. Everyone likes math requests.
 * @author ssanchez
 */
public class MathClient implements Client {

    private String ID;
    private Service service;
    private List<Result> results = new ArrayList<Result>();

    public MathClient(Service service, String ID) {
        this.service = service;
        this.ID = ID;
    }

    public String getID() {
        return ID;
    }

    public void add(int ... values) throws ServiceException {
        Job addJob = new AdditionJob(values);
        service.queueJob(addJob, this);
    }

    public void multiply(int ... values) throws ServiceException {
        Job multiplyJob = new MultiplicationJob(values);
        service.queueJob(multiplyJob, this);
    }

    public void addResult(Result result) {
        results.add(result);
    }

    public List<Result> getResults() {
        return results;
    }
}
