package com.github.paicoding.forum.web.javabetter.neety.future;

import java.util.concurrent.*;

public class JdkFuture {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(2);
        Future<Integer> future = service.submit(() -> {
            Thread.sleep(1000);
            return 50;
        });

        //主线程同步等待
        future.get();
    }

}
