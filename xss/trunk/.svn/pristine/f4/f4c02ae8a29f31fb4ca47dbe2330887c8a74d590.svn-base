package com.xp.brushms.client.utils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 线程池管理工具类
 */
public class ThreadUtil {
    /**
     * 非固定数量线程池
     */
    private static ExecutorService moreExecutorService = Executors.newCachedThreadPool();

    /**
     * 非固定数量线程池
     * @param command
     */
    public static void executeMore(Runnable command) {
        moreExecutorService.execute(command);
    }
}
