package com.github.paicoding.forum.web.javabetter.thread1;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 自定义RejectedExecutionHandler包含几种常见的拒绝策略。
 */
public class CustomRejectedExecutionHandler {

    /**
     * AbortPolicy在任务被拒绝时抛出RuntimeException。
     */
    public static class AbortPolicy implements RejectedExecutionHandler {
        public void rejectedExecution(Runnable r, ThreadPoolExecutor e) {
            throw new RuntimeException("Task " + r.toString() + " rejected from " + e.toString());
        }
    }

    /**
     *丢弃策略默默地丢弃被拒绝的任务。
     */
    public static class DiscardPolicy implements RejectedExecutionHandler {
        public void rejectedExecution(Runnable r, ThreadPoolExecutor e) {
            // Do nothing
        }
    }

    /**
     * 被叫方运行策略会在被叫线程中重新执行被拒绝的任务。
     */
    public static class CallerRunsPolicy implements RejectedExecutionHandler {
        public void rejectedExecution(Runnable r, ThreadPoolExecutor e) {
            if (!e.isShutdown()) {
                r.run();
            }
        }
    }
}