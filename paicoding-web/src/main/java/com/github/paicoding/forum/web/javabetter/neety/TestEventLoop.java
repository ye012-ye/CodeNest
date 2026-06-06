package com.github.paicoding.forum.web.javabetter.neety;

import io.netty.channel.DefaultEventLoop;
import io.netty.channel.nio.NioEventLoopGroup;
import org.apache.tomcat.util.net.Nio2Channel;

import java.util.concurrent.TimeUnit;

public class TestEventLoop {

    public static void main(String[] args) {
        NioEventLoopGroup group = new NioEventLoopGroup(3);
        DefaultEventLoop defaultGroup = new DefaultEventLoop();

        group.next().submit(()->{
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("ok");
        });
        System.out.println("main");
        group.next().scheduleAtFixedRate(()->{
            System.out.println("每1秒执行");
        },0,1, TimeUnit.SECONDS);
    }

}
