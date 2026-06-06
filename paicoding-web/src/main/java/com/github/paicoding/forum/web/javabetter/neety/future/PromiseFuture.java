package com.github.paicoding.forum.web.javabetter.neety.future;

import io.netty.channel.EventLoop;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.util.concurrent.DefaultPromise;

import java.util.concurrent.ExecutionException;

public class PromiseFuture {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        EventLoop eventLoop = new NioEventLoopGroup().next();
        DefaultPromise<Integer> promise = new DefaultPromise<>(eventLoop);
        new Thread(()->{
            System.out.println("开始计算");
            try {
                int i = 1/0;
                Thread.sleep(1000);
                promise.setSuccess(40);
            } catch (InterruptedException e) {
                promise.setFailure(e);
                throw new RuntimeException(e);
            }
        }).start();

        System.out.println("等待结果");
        System.out.println(promise.get());
    }

}
