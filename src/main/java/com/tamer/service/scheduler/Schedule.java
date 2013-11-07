package com.tamer.service.scheduler;

/**
 * Basic interface for a schedule.
 */
public interface Schedule<T, V> {

    public void add(T t, V v);

    public Task<T, V> next();

    public boolean hasNext();
}
