package com.github.paicoding.forum.web.javabetter.neety.Bytebuf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;

public class TestByteBuf {
    public static void main(String[] args) {
        ByteBuf buf = ByteBufAllocator.DEFAULT.buffer();
        System.out.println(buf.getClass());
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 300; i++) {
            builder.append(i+"a");
        }
        buf.writeBytes(builder.toString().getBytes());
        System.out.println(buf);

    }
}
