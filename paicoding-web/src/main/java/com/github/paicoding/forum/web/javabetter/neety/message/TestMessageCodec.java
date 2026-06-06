package com.github.paicoding.forum.web.javabetter.neety.message;

import io.netty.channel.embedded.EmbeddedChannel;


public class TestMessageCodec {


    public static void main(String[] args) {

        EmbeddedChannel channel = new EmbeddedChannel(new MessageCodec());


    }

}

