package com.woaiqw.base.core;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by haoran on 2018/9/11.
 */
public class ThreadPool {

    private static final ThreadPool ourInstance = new ThreadPool();
    private ExecutorService pool;

    public static ThreadPool getInstance() {
        return ourInstance;
    }

    private ThreadPool() {
        TimeUnit KEEP_ALIVE_TIME_UNIT = TimeUnit.SECONDS;
        int NUMBER_OF_CORES = Runtime.getRuntime().availableProcessors();
        this.pool = new ThreadPoolExecutor(NUMBER_OF_CORES, NUMBER_OF_CORES * 4, 1L, KEEP_ALIVE_TIME_UNIT, new LinkedBlockingDeque<Runnable>(), new ThreadPoolExecutor.AbortPolicy());
    }

    public ExecutorService getPool() {
        return this.pool;
    }

    public void shutDown() {
        this.pool.shutdown();
    }
}
