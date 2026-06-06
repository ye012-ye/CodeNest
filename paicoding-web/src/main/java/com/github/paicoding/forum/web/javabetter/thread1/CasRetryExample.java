package com.github.paicoding.forum.web.javabetter.thread1;

import liquibase.pro.packaged.S;

import java.util.concurrent.atomic.AtomicInteger;

class CasRetryExample {
    private static AtomicInteger counter = new AtomicInteger(0);
    private static final int MAX_RETRIES = 5;
    private static int currentValue;
    public static void main(String[] args) {
        currentValue = counter.get();
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                extracted();
            }).start();
        }

    }

    private static void extracted() {
            boolean updated = counter.compareAndSet(currentValue, currentValue + 1);
            if (updated) {
                System.out.println(Thread.currentThread().getName() + "更新成功，当前值: " + counter.get());
            }else {
                System.out.println(Thread.currentThread().getName() + ": 更新失败，正在重试...");
            }
    }
}