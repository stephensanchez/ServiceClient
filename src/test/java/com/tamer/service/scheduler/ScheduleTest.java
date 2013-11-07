package com.tamer.service.scheduler;

import junit.framework.Assert;
import org.junit.Test;

/**
 * Test that a Schedule actually works. Outside the scope of concurrency and
 * services, clients, and that whole shebang, let us actually see if the data
 * structure is sound.
 */
public class ScheduleTest {

    @Test
    public void testSchedule() throws Exception {
        // Create a new schedule and submit a number of jobs. This will not be
        // the order in which they are executed by the Schedule.
        Schedule<Integer, String> schedule = new Schedule<Integer, String>();
        schedule.add(1, "clientOne");
        schedule.add(57, "clientOne");
        schedule.add(2, "clientTwo");
        schedule.add(210, "clientTwo");
        schedule.add(3, "clientThree");

        // The proper order of the schedule will essentially rotate the clients.
        assertNext(schedule, 1, "clientOne");
        assertNext(schedule, 2, "clientTwo");
        assertNext(schedule, 3, "clientThree");
        assertNext(schedule, 57, "clientOne");
        assertNext(schedule, 210, "clientTwo");

        // Our schedule is empty.
        Assert.assertFalse(schedule.hasNext());
    }

    private void assertNext(Schedule schedule, int job, String client) throws InterruptedException {
        Assert.assertTrue(schedule.hasNext());
        Thread.sleep(10);
        Task firstResult = schedule.next();
        Assert.assertEquals(client, firstResult.getClient());
        Assert.assertEquals(job, firstResult.getJob());
    }
}
