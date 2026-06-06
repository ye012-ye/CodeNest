package com.github.paicoding.forum.web.javabetter.neety.Bytebuf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;

import java.nio.charset.Charset;

public class Testslice {
    public static void main(String[] args) {
        ByteBuf buf = ByteBufAllocator.DEFAULT.buffer(20);
        buf.writeBytes(new byte[]{1,2,3,4,5,6,7,8,9,10});

        System.out.println(buf);
        // 从0开始，5个字节 没有复制效率高，在同一个内存
        ByteBuf f1 = buf.slice(0, 5);
        //计数器加1
        f1.retain();
        ByteBuf f2 = buf.slice(5, 5);
        f2.retain();

        buf.release();
        System.out.println(f1.toString());
        System.out.println(f2.toString());
    }
}
