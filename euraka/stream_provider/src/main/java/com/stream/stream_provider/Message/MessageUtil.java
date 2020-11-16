package com.stream.stream_provider.Message;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

/**
 * @program: springCloud
 * @description: 实现消息中间件工具类
 *  使用stream实现消息中间消费消息，必须定义个channel，stream内置了一个channel，可以直接使用它，也可以自定义一个channel
 *  @EnableBinding: 绑定通道
 *  MessageChannel: Source内置的发送消息的通道，可以使用它进行消息发送
 * @create: 2020-11-15 11:31
 **/
@Slf4j
@Component
@EnableBinding({Source.class,CustomChannel.class})
public class MessageUtil {

    @Autowired
    @Qualifier(value = "output")
    private MessageChannel output;

    @Autowired
    @Qualifier(value = "custom_output")
    private MessageChannel cusOutput;

    public void output(String index){
        log.info("Stream send start");
        output.send(MessageBuilder.withPayload("Hello Word!! output").build());
        log.info("Stream send end");
    }

    public void cusOutput(String index){
        log.info("Stream send start");
        cusOutput.send(MessageBuilder.withPayload("Hello Word!! cusOutput  "+index).build());
        log.info("Stream send end");
    }

    /**
     * 分区测试
     */
    public void partition(String index){
        log.info("Stream send start");
        output.send(MessageBuilder.withPayload(index).build());
        log.info("Stream send end");
    }

}
