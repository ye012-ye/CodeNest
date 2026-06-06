package com.github.paicoding.forum.web.javabetter.neety;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.embedded.EmbeddedChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

public class TestLengthFieldDecoder {

    public static void main(String[] args) {
        // 专门用于测试的 EmbeddedChannel
        EmbeddedChannel channel = new EmbeddedChannel(
                new LengthFieldBasedFrameDecoder(1024,0,4,1,4),
                new LoggingHandler(LogLevel.DEBUG)
        );

        ByteBuf buf = ByteBufAllocator.DEFAULT.buffer();
        send(buf,"hello, world");
        send(buf,"he");
        send(buf,"hello");
        channel.writeInbound(buf);
    }

    private static void send(ByteBuf buf,String str) {
        byte[] bytes = str.getBytes();
        int length = str.length();
        buf.writeInt(length);
        buf.writeByte(1);
        buf.writeBytes(bytes);
    }

}
