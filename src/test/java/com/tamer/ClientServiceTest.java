package com.tamer;

import com.tamer.client.math.*;
import com.tamer.service.scheduler.SchedulerService;
import com.tamer.service.Service;
import junit.framework.Assert;
import org.junit.Test;

import java.util.List;

/**
 * Basic Test to cover the concepts of the Exercise itself. This is the main
 * test for demonstrating the following concept:
 *
 * 1) Multiple Clients can connect to a single Service to execute a number of
 *    jobs.
 * 2) The service will execute all jobs synchronously, returning the jobs'
 *    results to each Client.
 * 3) Prioritization of the jobs will be based on the Client (particularly the
 *    implementation).
 */
public class ClientServiceTest {

    @Test
    public void testClientService() throws Exception {
        Service service = new SchedulerService();
        MathClient clientOne = new MathClient(service, "One");
        MathClient clientTwo = new MathClient(service, "Two");

        clientOne.add(1, 2, 3, 4, 5, 6);
        clientTwo.add(7, 8, 9, 10, 11, 12);

        clientOne.multiply(1, 2, 3, 4, 5, 6);
        clientTwo.multiply(7, 8, 9, 10, 11, 12);

        List<Result> resultsOne = clientOne.getResults();
        List<Result> resultsTwo = clientTwo.getResults();

        Assert.assertEquals(21, resultsOne.get(0).getResult());
        Assert.assertEquals(720, resultsOne.get(1).getResult());

        Assert.assertEquals(57, resultsTwo.get(0).getResult());
        Assert.assertEquals(665280, resultsTwo.get(1).getResult());
    }

}
