package com.github.paicoding.forum.web.javabetter.neety;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

import java.net.InetSocketAddress;
import java.util.Scanner;

public class HelloClient {

    public static void main(String[] args) throws InterruptedException {
        //异步处理
        NioEventLoopGroup group = new NioEventLoopGroup();
        ChannelFuture future = new Bootstrap()
                .group(group)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new LoggingHandler(LogLevel.DEBUG));
                        ch.pipeline().addLast(new StringEncoder());
                    }
                })
                .connect(new InetSocketAddress("localhost", 8080));
        //使用sync同步
        Channel channel = future.sync().channel();
        new Thread(()->{
           Scanner scanner = new Scanner(System.in);
           while (true){
               String line = scanner.nextLine();
               if ("q".equals(line)){
                   //这个是异步调用
                   channel.close();
                   break;
               }
           }
        },"input").start();

        ChannelFuture closeFuture = channel.closeFuture();
        closeFuture.sync();
        System.out.println("关闭了");
        group.shutdownGracefully();

        future.addListener(new ChannelFutureListener() {
            @Override
            //在nio连接建立好之后 异步处理
            public void operationComplete(ChannelFuture future) throws Exception {
                Channel channel = future.channel();
                System.out.println("连接成功");
                channel.writeAndFlush("hello,word");
            }
        });



    }
}
