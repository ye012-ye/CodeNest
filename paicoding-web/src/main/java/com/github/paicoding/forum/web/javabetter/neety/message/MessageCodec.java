package com.github.paicoding.forum.web.javabetter.neety.message;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageCodec;
import lombok.extern.slf4j.Slf4j;

import javax.mail.Message;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

@Slf4j
public class MessageCodec extends ByteToMessageCodec {

    /**
     * 编码
     * @param ctx
     * @param msg
     * @param out
     * @throws Exception
     */
    @Override
    protected void encode(ChannelHandlerContext ctx, Object msg, ByteBuf out) throws Exception {
        //魔数 4
        out.writeBytes(new byte[]{1,2,3,4});
        //版本 5
        out.writeByte(1);
        //虚拟化 0 jdk 1 json 6
        out.writeByte(0);
        //字节指令类型 业务类型 0 登录 1 注册 7
        out.writeByte(0);
        //请求序号 11
        out.writeBytes(new byte[]{1,2,3,4});
        //无意义对齐填充
        out.writeByte(0xff);

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(msg);
        byte[] bytes = bos.toByteArray();

        //正文长度 15
        out.writeByte(bytes.length);
        //正文
        out.writeBytes(bytes);
    }

    /**
     * 解码
     * @param ctx
     * @param in
     * @param out
     * @throws Exception
     */
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List out) throws Exception {
        int msgicNum = in.readInt();
        byte version = in.readByte();
        byte serializerType = in.readByte();
        byte messageType = in.readByte();
        int sequenceId = in.readInt();
        in.readByte();
        int length = in.readInt();
        byte[] bytes = new byte[length];
        in.readBytes(bytes,0,length);
        Message message;
        if (serializerType == 0){
            ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(bytes));
            message = (Message) ois.readObject();
        }
        log.debug("{},{},{},{},{},{}", msgicNum, version, serializerType, messageType, sequenceId, length);
        log.debug("{}", message);
        out.add(message);
    }
}
